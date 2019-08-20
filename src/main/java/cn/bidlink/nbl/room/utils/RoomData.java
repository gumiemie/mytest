package cn.bidlink.nbl.room.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import cn.bidlink.nbl.room.model.Bideval;
import cn.bidlink.nbl.room.model.Room;
import cn.bidlink.nbl.room.model.RoomUser;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/2/28 15:31$
 */
public class RoomData {

    private static NutDao biddingTestDao;
    private static NutDao biddingDao;

    static {
        //nbl_bidding测试库
        DruidDataSource expertTestDruidDataSource = new DruidDataSource();
        expertTestDruidDataSource.setUrl("jdbc:mysql://10.1.1.68:3306/nbl_bidding?useUnicode=true");
        expertTestDruidDataSource.setUsername("jiaoyipt");
        expertTestDruidDataSource.setPassword("8kS#0(M");
        biddingTestDao = new NutDao(expertTestDruidDataSource);
    }

    @Test
    public void excute() {

        //1.先查询出所有评标room对象
        List<Room> rooms = biddingTestDao.query(Room.class, Cnd.where("roomType", "=", "2"));
        List<String> ps = new ArrayList<String>();
        //遍历room对象
        if (rooms != null && rooms.size() > 0) {
            for (Room r : rooms) {
                //2,根据room对象中的PACKAGE_GROUP_ID属性去packages表中查询记录,主要获取packageid
                if (StringUtils.isNotBlank(r.getPackageGroupId())) {

                    List<Record> records = biddingTestDao.query("packages", Cnd.where("PACKAGE_GROUP_ID", "=", r.getPackageGroupId()));
                    List<Room> rooms1 = biddingTestDao.query(Room.class, Cnd.where("packageId", "=", r.getPackageId()).and("ROOM_TYPE","=",2));
                    int size = 0;
                    if (records != null) {
                        size = records.size();
                    }
                    if (rooms1 != null && rooms1.size() > 1 && size > 1) {
                        ps.add(r.getPackageGroupId());
                        for (int i = 1; i < rooms1.size(); i++) {

                            if (i>=size){
                                biddingTestDao.delete(rooms1.get(i));
                                continue;
                            }
                            Room room = rooms1.get(i);
                            room.setPackageId(records.get(i).getString("id"));
                            biddingTestDao.update(room);
                            Bideval temp = biddingTestDao.fetch(Bideval.class, Cnd.where("packageId", "=", r.getPackageId()));
                            if (temp != null) {
                                temp.setRoomId(room.getId());
                                biddingTestDao.update(temp);
                            }
                        }
                    }
                }
            }

            for (String s:ps){
                System.out.println("需要人工核对的package_group_id");
                System.out.println(s);
            }
        }
    }

    @Test
    public void  execute2() throws Exception{
        String fileName ="C:\\Users\\Administrator\\Desktop\\工作簿1.xlsx";
        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        List<String> aaaa= new ArrayList<String>();
        for (int i=0;i<lastRowNum;i++){
            String packageGroupId = sheet.getRow(i).getCell(0).getStringCellValue();
            List<Room> rooms = biddingTestDao.query(Room.class, Cnd.where("packageGroupId", "=", packageGroupId).and("roomType","=",2));
            int jj=0;
            String re = packageGroupId+"--------------";

            if (rooms!=null&&rooms.size()>0){
                for (Room r:rooms){
                    List<RoomUser> roomUsers = biddingTestDao.query(RoomUser.class, Cnd.where("roomId", "=", r.getId()));
                    if (roomUsers!=null&&roomUsers.size()>0){
                        jj++;
                        re=re+r.getId()+"_____";
                    }

                }
            }
            if (rooms.size()!=jj&&jj>0){
                aaaa.add(re);
            }
        }

        for (String s:aaaa){
            System.out.println(s);
        }
    }

}
