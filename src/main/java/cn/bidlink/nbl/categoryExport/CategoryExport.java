package cn.bidlink.nbl.categoryExport;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2017/3/1 20:21$
 */
public class CategoryExport {

    private static NutDao dao;
    private static FileOutputStream fos;

    static {
        //悦招用户正式库
        DruidDataSource userDruidDataSource = new DruidDataSource();
        userDruidDataSource.setUrl("jdbc:mysql://211.151.182.228:3306/nbl_base?useUnicode=true");
        userDruidDataSource.setUsername("ebnewuser");
        userDruidDataSource.setPassword("ebnewuserbid6link!@#$");
        dao = new NutDao(userDruidDataSource);
    }

    @Test
    public void export(){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("行业");

            List<Category> cs = dao.query(Category.class, Cnd.where("leaf", "=", 1).orderBy("level","desc"));
            for (int i=0;i<cs.size();i++){
                HSSFRow row = sheet.createRow(i + 1);
                fetchData(row,cs.get(i));
            }

            fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\行业.xls"));
            workbook.write(fos);
            fos.flush();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private HSSFRow fetchData(HSSFRow row, Category category) {
        int level = category.getLevel();
        row.createCell(level*2-1).setCellValue(category.getName());
        row.createCell(level*2).setCellValue(category.getCode());
        if (level ==1){
            return row;
        }else {
            return fetchData(row,dao.fetch(Category.class,Cnd.where("code","=",category.getPcode())));
        }
    }

}
