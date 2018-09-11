package busiopport;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 消息中心漏发邮件, 根据双方记录对应
 * @date 2017/5/23 14:30$
 */
public class ResendEmail {

    private static NutDao busiDao;
    static {
        DruidDataSource busiDruidDataSource = new DruidDataSource();
        busiDruidDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/hades?useUnicode=true");
        busiDruidDataSource.setUsername("ebnewuser");
        busiDruidDataSource.setPassword("ebnewuserbid6link!@#$");
        busiDao = new NutDao(busiDruidDataSource);
    }

    @Test
    public  void execute() throws Exception{
        Set<String> loginNameResult = new HashSet<String>();

        List<String> mails1 = this.findMails();

        List<MailCustomHistoryDto> historyDtos = busiDao.query(MailCustomHistoryDto.class, Cnd.where("CREATE_TIME", ">", "2017-05-22 23:59:00"));
        if (historyDtos!=null&&historyDtos.size()>0){
            for (MailCustomHistoryDto tempDto:historyDtos){
                String[] mails = tempDto.getMails().split(",");
                for (String email:mails){
                    if (!mails1.contains(email)&&!email.contains("ebnew.com")&& StringUtils.isNotBlank(email)){
                        List<MailCustomHistoryDto> query = busiDao.query(MailCustomHistoryDto.class, Cnd.where("mails", "like", "%"+email+"%").and("CREATE_TIME", ">", "2017-05-22 23:59:00"));
                        if (query!=null&&query.size()>0){
                            for (MailCustomHistoryDto dto:query){
                                loginNameResult.add(dto.getUserLoginName());
                            }
                        }
                    }
                }
            }
        }
        StringBuffer result = new StringBuffer();
        for (String s:loginNameResult){
            result.append(s+",");
        }
        System.out.print(result.toString());
    }

    //获取消息中心提供的发送记录
    public List<String> findMails() throws Exception {
        List result = new ArrayList();
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\23订阅发送.xls")));
        HSSFSheet sheet = null;
        if (workbook!=null){
            sheet = workbook.getSheetAt(0);
        }
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1;i<lastRowNum;i++){
            HSSFCell cell = sheet.getRow(i).getCell(1);
            if (cell!=null){
                String ms = cell.getStringCellValue();
                result.add(ms);
            }
        }
        return result;
    }


}

