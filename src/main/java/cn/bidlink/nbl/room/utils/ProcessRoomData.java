package cn.bidlink.nbl.room.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import cn.bidlink.nbl.room.model.Bideval;
import cn.bidlink.nbl.room.model.Room;
import cn.bidlink.nbl.room.model.RoomUser;

import java.util.List;
import java.util.UUID;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/2/24 16:07$
 */
public class ProcessRoomData {
    private static NutDao biddingTestDao;
    private static NutDao biddingDao;

    static {
        //nbl_bidding测试库
        DruidDataSource expertTestDruidDataSource = new DruidDataSource();
        expertTestDruidDataSource.setUrl("jdbc:mysql://10.1.1.68:3306/nbl_bidding?useUnicode=true");
        expertTestDruidDataSource.setUsername("jiaoyipt");
        expertTestDruidDataSource.setPassword("8kS#0(M");
        //biddingTestDao = new NutDao(expertTestDruidDataSource);

        //nbl_bidding正式库
        DruidDataSource userDruidDataSource = new DruidDataSource();
        userDruidDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/nbl_bidding?useUnicode=true");
        userDruidDataSource.setUsername("ebnewuser");
        userDruidDataSource.setPassword("ebnewuserbid6link!@#$");
        biddingDao = new NutDao(userDruidDataSource);
    }

    @Test
    public void excute() {

        //1.先查询出所有评标room对象(status为2)
        List<Room> rooms = biddingDao.query(Room.class, Cnd.where("roomType", "=", "2"));
        //遍历room对象
        if (rooms != null && rooms.size() > 0) {
            for (Room r : rooms) {
                //2,根据room对象中的PACKAGE_GROUP_ID属性去packages表中查询记录,主要获取packageid
                if (StringUtils.isNotBlank(r.getPackageGroupId())) {
                    List<Record> records = biddingDao.query("packages", Cnd.where("PACKAGE_GROUP_ID", "=", r.getPackageGroupId()));

                    Bideval bideval1 = null;//存放查询出的bideval对象以备后续使用.
                    List<RoomUser> users = null;//存放查询出的roomUser集合以备后续复制使用.

                    //如果packages表中记录数大于0,则遍历
                    if (records!=null&&records.size()> 0) {
                        for (int i = 0; i < records.size(); i++) {
                            //获取packageid属性
                            String packageId = records.get(i).getString("id");

                            //第一个包对应的Room,如果packageid为空则填充
                            if (i == 0) {
                                if (StringUtils.isBlank(r.getPackageId())){
                                    //将packageid属性写入到room对象中
                                    r.setPackageId(packageId);
                                    //更新room
                                    biddingDao.update(r);
                                }

                                bideval1 = biddingDao.fetch(Bideval.class, Cnd.where("ROOM_ID", "=", r.getId()));

                                //如果包组下的包多于1个,查询room对应的bideval对象/以及RoomUser对象的集合,以备插入时使用
                                if (records.size() > 1) {
                                    users = biddingDao.query(RoomUser.class, Cnd.where("ROOM_ID", "=", r.getId()));
                                }

                                //更新bideval
                                if (bideval1 != null&&StringUtils.isBlank(bideval1.getPackageId())) {
                                    bideval1.setPackageId(packageId);
                                    biddingDao.update(bideval1);
                                }

                                //更新roomUser.
                                //biddingTestDao.update("room_user", Chain.make("IS_SYSTEM_USER",0),Cnd.where("room_id","=", r.getId()));

                            //第2-n个包,向room,room_user,bideval三个表中插入数据
                            } else {
                                //新增room
                                r.setId(UUID.randomUUID().toString().replace("-", ""));
                                r.setPackageId(packageId);
                                biddingDao.insert(r);

                                //新增bideval
                                if (bideval1!=null){
                                    bideval1.setId(UUID.randomUUID().toString().replace("-", ""));
                                    bideval1.setPackageId(packageId);
                                    bideval1.setRoomId(r.getId());
                                    biddingDao.insert(bideval1);
                                }

                                //新增roomUser
                                for (RoomUser ru:users){
                                    ru.setId(UUID.randomUUID().toString().replace("-", ""));
                                    ru.setRoomId(r.getId());
                                    biddingDao.insert(ru);
                                }
                            }
                        }
                    }
                }
            }
        }

    }


}
