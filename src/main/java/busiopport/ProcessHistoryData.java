package busiopport;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import utils.DBUtils;

import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 处理历史数据.
 * @date 2017/8/9 10:27$
 */
public class ProcessHistoryData {
    private DBUtils dbUtils = new DBUtils();
    @Test
    public void excute(){
        NutDao hadesDao = dbUtils.getHadesDao();
//        List<MailcustomDto> mcs = hadesDao.query(MailcustomDto.class, null);
        List<MailCustomHistoryDto> mchs = hadesDao.query(MailCustomHistoryDto.class, Cnd.where("id","<",4640001).orderBy("id","DESC"));

        /*for (MailcustomDto mailcustomDto:mcs){
            mailcustomDto.setInfoClassCodes(processInfoClassCodes(mailcustomDto.getInfoClassCodes()));
            mailcustomDto.setBidModels(processBidModels(mailcustomDto.getDocSources()));
            hadesDao.update(mailcustomDto);
        }*/
        for (MailCustomHistoryDto historyDto:mchs){
            if (org.apache.commons.lang3.StringUtils.isBlank(historyDto.getBidModels())){
                historyDto.setBidModels(processBidModels(historyDto.getDocSources()));
                historyDto.setInfoClassCodes(processInfoClassCodes(historyDto.getInfoClassCodes()));
                hadesDao.update(historyDto);
            }
        }
    }

    private String processInfoClassCodes(String source){
        if (StringUtils.isNotBlank(source)){
            source = source.replace("0105", "0103,0105");
            source = source.replace("0106", "0104,0106");
            source = source.replace("0107,0109", "0107");
        }
        return source;
    }

    private String processBidModels(String docSource){
        if (StringUtils.isNotBlank(docSource)){
            if (docSource.contains("90")&&docSource.contains("130")){
                return "0101,0301,0102,0302,04,05,06,10";
            }else if (docSource.contains("90")){
                return "0101,0301";
            }else {
                return "0102,0302,04,05,06,10";
            }
        }
        return "";
    }
}
