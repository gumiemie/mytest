package shjd;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 导出上海机电数据库中的专家
 * @date 2017/3/28 16:56$
 */
public class ExportData {

    private static NutDao shDao;

    static {
        DruidDataSource shjdDataSource = new DruidDataSource();
        shjdDataSource.setUrl("jdbc:mysql://222.66.64.102:3306/meetc-official?useUnicode=true");
        shjdDataSource.setUsername("meetcuser");
        shjdDataSource.setPassword("bU01l@w0%Il");
        shDao = new NutDao(shjdDataSource);
    }

    @Test
    /**
     * @Description: 导专家
     * @param: []
     * @return: void
     * @Date: 2017/7/10 14:07
     */
    public void exportExperts() throws Exception {

        List<Record> experts = shDao.query("bm_experts", null);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("专家");
        HSSFRow row = sheet.createRow(0);
        String[] headers = new String[]{"ID", "专家编号", "姓名", "出生年月", "性别", "学历", "学位", "毕业院校", "学校专业", "所在地", "证件类型", "身份证号码",
                "专业", "就职单位", "职务", "职称", "传真", "电话", "地址", "邮箱", "手机", "家庭电话", "简历", "专家级别", "专家类别", "验证状态", "验证时间", "验证意见", "验证人"};
        String[] colums = new String[]{"id", "experts_id", "name", "birth_date", "sex", "max_degree", "degree", "school_name", "profession", "region", "card_type", "card_number",
                "industy_name", "company_name", "position", "zhi_cheng", "fax", "telephone", "address", "email", "mobile", "home_phone", "resume", "level", "role_flag", "valid_status", "valid_time", "valid_remark", "valid_name"};
        for (int j = 0; j < headers.length; j++) {
            row.createCell(j).setCellValue(headers[j]);
        }
        int size = experts.size();
        if (experts != null && size > 0) {
            for (int i = 0; i < size; i++) {
                HSSFRow row1 = sheet.createRow(i + 1);
                for (int j = 0; j < colums.length; j++) {
                    String colum = colums[j];
                    String value = experts.get(i).getString(colum);
                    if (colum.equals("sex")) {
                        if (value.equals("1")) {
                            value = "男";
                        } else if (value.equals("2")) {
                            value = "女";
                        } else {
                            value = "未填";
                        }
                    } else if (colum.equals("role_flag")) {
                        if (value.equals("1")) {
                            value = "专家";
                        } else if (value.equals("2")) {
                            value = "招标人代表";
                        } else if (value.equals("3")) {
                            value = "专家、招标人代表双重身份";
                        } else {
                            value = "未设置";
                        }
                    } else if (colum.equals("valid_status")) {
                        if (value.equals("0")) {
                            value = "未通过";
                        } else if (value.equals("1")) {
                            value = "通过";
                        } else if (value.equals("-1")) {
                            value = "停用";
                        } else {
                            value = "未设置";
                        }
                    }
                    row1.createCell(j).setCellValue(value);
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\bm_experts1.xls"));
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    @Test
    /**
     * @Description: 导开标记录
     * @param: []
     * @return: void
     * @Date: 2017/7/10 14:08
     */
    public void exportBidOpenRecord() {

    }

    /**
     * @Description: 导上海机电的招标台帐
     * @param:
     * @return:
     * @Date: 2017/7/31 9:09
     */
    @Test
    public void exportBidAccount() throws Exception {
        //要导出的台帐和时间范围
        String starttime = "2017-06-30 00:00:00";
        String endtime = "2017-06-30 23:59:59";

        //sql语句.
        String sql = "select right(a.parent_project_number,4) as sub_project_xuhao,a.project_number,"
                + "a.creator_dept_name,a.project_name,a.tender_names as tender_name,a.tender_mode,"
                + "a.project_nature,a.industry_code,a.project_amount_usd,a.project_amount_rmb,"
                + "e.bid_doc_start_time,a.project_type1,f.start_time as open_time,"
                + "c.bidder_name,c.bid_us_price,c.bid_price,a.notice_issue_time,a.create_user_name,"
                + "d.service_fee,d.check_time as check_time1,"
                + "b.check_time as check_time2,datediff(b.check_time,max(a.notice_issue_time)) as cycles,"
                + "a.archive_file_count,a.archive_year_limit,"
                + "a.state,a.archive_file_type,a.create_time,a.kind,a.create_flag,a.region_code," +
                "a.funds_source,a.funds_source1,g.bid_money_rmb,g.contract_type,g.notice_time  "
                + "from bm_project a  "
                + " left join bm_project_archive_apply b on b.project_number=left(a.project_number,length(b.project_number))"
                + " left join bm_project_result_bidder c on c.project_number = left(a.project_number,length(c.project_number)) "
                + " left join bm_finance_servicefee d on a.project_number=d.project_number "
                + " left join bm_tender_notice e on a.project_number=e.project_number "
                + " left join bm_project_open f on a.project_number=f.project_number "
                + " left join bm_project_result g on a.project_number=g.project_number "
                + "where a.sub_project_number in "
                + "( "
                + "select sub_project_number "
                + "from bm_project  "
                + "where (( is_project_directory=1  and project_number=sub_project_number)"
                + " or (is_project_directory=2  and parent_project_number<>sub_project_number)) "
                + ") "
                + "and a.archive_file_type != 2 and a.is_project_directory=1  and a.create_time "
                + "between '"
                + starttime
                + "' and '"
                + endtime
                + "'"
                + " group by a.project_number order by a.create_time asc";
        Sql sql1 = Sqls.create(sql);
        sql1.setCallback(new SqlCallback() {
            public java.lang.Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                ArrayList<Object[]> resultList = new ArrayList<Object[]>();
                while (rs.next()) {
                    Object[] oo = new Object[35];
                    for (int i = 0; i < 35; i++) {
                        oo[i] = rs.getObject(i+1);//
                    }
                    resultList.add(oo);
                }
                return resultList;
            }
        });
        shDao.execute(sql1);
        List<Object[]> oList = sql1.getList(Object[].class);
        //遍历结果集并写入到excel中.
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow headerRow = sheet.createRow(0);
        String s = "序号,子项序号,招标编号,包件号,部门,项目名称,项目单位,招标方式,项目性质,项目类型,资金来源,行业,委托金额(万美元),委托金额(万元),公告发布时间,公告媒体,开标日期,中标单位,中标金额(万美元),中标金额(万元),中标日期,合同类型,项目负责人,服务费收入(万元),收入日期,资料归档,归档周期,卷数,保存年限,项目状态,项目状况,项目所在地,备注";
        String[] headers = s.split(",");
        for (int i=0;i<headers.length;i++){
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        if ((oList != null) && (oList.size() > 0)) {
            for (int i = 0; i < oList.size(); i++) {
                XSSFRow row = sheet.createRow(i + 1);
                Object[] obj = oList.get(i);

                // 公告媒体
                String bulletin_media = "";
                if (ConvertUtils.convert((Integer) obj[11]) != 0) {
                    if ((Integer) obj[11] == 1) {
                        bulletin_media = "中国国际招标网";
                    } else {
                        if (ConvertUtils.convert((String) obj[6])
                                .toString().indexOf("2") != -1) {
                            bulletin_media = "上海政府采购网";
                        } else {
                            bulletin_media = "中国采购与招标网";
                        }
                    }
                } else {
                    bulletin_media = "";
                }
                // 招标方式
                String tender_mode = "",fund_source = "" , contract_type="";
                int ptype = (Integer) obj[11];
                switch(ptype){
                    case 1 :
                        tender_mode = ConvertUtils.convert((Integer)obj[5]+"", "1,2,3", "国际公开,国际邀请,国际比选");
                        fund_source = ConvertUtils.convert((Integer)obj[31]+"", "1,2,3,4,5,6", "现汇,世行,亚行,日贷,外国政府贷款,国内资金");
                        break;
                    case 2 :
                        tender_mode = ConvertUtils.convert((Integer)obj[5]+"", "1,2,3", "国内公开,国内邀请,国内比选");
                        fund_source = ConvertUtils.convert((Integer)obj[30]+"", "1,2,3", "自筹资金,财政拨款,其他");
                        break;
                    case 3 :
                        tender_mode = ConvertUtils.convert((Integer)obj[5]+"", "1,2,3", "建设工程公开,建设工程邀请,直接发包");
                        fund_source = ConvertUtils.convert((Integer)obj[30]+"", "1,2,3", "自筹资金,财政拨款,其他");
                        break;
                    case 4 :
                        tender_mode = ConvertUtils.convert((Integer)obj[5]+"", "1,2,3,4,5","政采公开,政采邀请,政采询价,政采竞争性谈判,政采单一来源");
                        fund_source = ConvertUtils.convert((Integer)obj[30]+"", "1,2,3", "自筹资金,财政拨款,其他");
                        break;
                };

                contract_type = ConvertUtils.convert((Integer)obj[33]+"", "0,1,2,3,4", "总价,总价,入围,单价,其他");

                // 项目状况
                String archive_file_type = "";
                if (ConvertUtils.convert((Integer) obj[25]) != 0) {
                    if ((Integer) obj[25] == 1) {
                        archive_file_type = "正常归档";
                    } else if ((Integer) obj[25] == 2) {
                        archive_file_type = "重新招标";
                    } else if ((Integer) obj[25] == 3) {
                        archive_file_type = "招标无效";
                    } else if ((Integer) obj[25] == 4) {
                        archive_file_type = "撤项";
                    } else if ((Integer) obj[25] == 5) {
                        archive_file_type = "无资格预审合格人";
                    } else if ((Integer) obj[25] == 6) {
                        archive_file_type = "无中标候选人";
                    }else if ((Integer) obj[25] == 7) {
                        archive_file_type = "无中标人";
                    } else if ((Integer) obj[25] == 8) {
                        archive_file_type = "中止招标";
                    }
                } else {
                    archive_file_type = "无值";
                }

                // 项目性质
                String val = "";
                if (!("").equals(ConvertUtils.convert((String) obj[6]))) {
                    val = (String) obj[6];
                    val = val.replace("1", "机电产品").replace("2", "中央投资")
                            .replace("3", "建设工程").replace("4", "政府采购")
                            .replace("5", "其他").replace(',', '、');
                } else {
                    val = "";
                }

                // 项目状态
                String status = ConvertUtils.convert(
                        (Integer) obj[24]+"",
                        "0,1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20",
                        "初始,项目建档,资格预审,招标公告,项目开标,项目评标,中标候选人,项目公示,确定中标人,平台待归档,重新招标,平台已归档,已撤项,申请归档,机电归档,已整理,已借阅,申请销毁,待销毁,已销毁");


                //项目类型
                String kind = "";
                if (ConvertUtils.convert((Integer) obj[27]) != 0) {
                    if ((Integer) obj[27] == 1) {
                        kind = "货物";
                    } else if ((Integer) obj[27] == 2) {
                        kind = "工程";
                    } else if ((Integer) obj[27] == 3) {
                        kind = "服务";
                    }
                } else {
                    kind = "";
                }

                String project_number = ConvertUtils
                        .convert((String) obj[1]);
                int create_flag = (Integer) obj[28];
                int index = project_number.indexOf("(");
                project_number = (index!=-1?project_number.substring(0,index):project_number);
                String pack_num = project_number.indexOf("/")!=-1?(
                        project_number.substring(
                                project_number.length() - 2,
                                project_number.length())):"";
                if (create_flag == 3) {
                    project_number = project_number + "(重招)";
                };
                String result = "";

                result +=(i + 1)//序号
                        + ","
                        + ConvertUtils.convert((String) obj[0])//子项序号
                        + ","
                        + project_number//招标编号
                        + ","
                        + pack_num//包件号
                        + ","
                        + ConvertUtils.convert((String) obj[2]).replaceAll(",", "，")//部门
                        + ","
                        + ConvertUtils.convert((String) obj[3]).replaceAll(",", "，")//项目名称
                        + ","
                        + ConvertUtils.convert((String) obj[4]).replaceAll(",", "，")//项目单位
                        + ","
                        + tender_mode//招标方式
                        + ","
                        + val//项目性质
                        + ","
                        + kind + ","//项目类型
                        + fund_source + ","//资金来源
                        + ConvertUtils.convert((String) obj[7]).replaceAll(",", "，")//行业
                        + ","
                        +  ((Integer) obj[11] == 2?0:ConvertUtils.convert(obj[8]!=null?((BigDecimal) obj[8]).doubleValue():0))//委托金额(万美元)
                        + ","
                        +  ((Integer) obj[11] == 1?0:ConvertUtils.convert(obj[9]!=null?((BigDecimal) obj[9]).doubleValue():0))//委托金额(万元)
                        + ","
                        + ConvertUtils.convert((Date) obj[10], true)+ ","//公告发布时间
                        + bulletin_media + ","//公告媒体
                        //开标日期
                        + ConvertUtils.convert((Date) obj[12], true) + ","
                        //中标单位
                        + ConvertUtils.convert((String) obj[13]).replaceAll(",", "，") + ","
                        //国际标中标金额
                        + ConvertUtils.convert(obj[14]!=null?((BigDecimal) obj[14]).doubleValue():0) + ","
                        //国内，建设工程，政府采购合同金额
                        + ConvertUtils.convert(obj[32]!=null?((BigDecimal) obj[32]).doubleValue():0) + "," //国内标使用合同金额
                        //中标日期
                        + ConvertUtils.convert((Date) obj[16]==null?(Date) obj[34]:(Date) obj[16], true) + ","
                        //合同类型
                        + contract_type + ","
                        + ConvertUtils.convert((String) obj[17]) + ","//项目负责人
                        + ConvertUtils.convert(obj[18]!=null?((BigDecimal) obj[18]).doubleValue():0) + ","//服务费收入(万元)
                        + ConvertUtils.convert((Date) obj[19], true) + ","//收入日期
                        + ConvertUtils.convert((Date) obj[20], true) + ","//资料归档
                        + ConvertUtils.convert((Integer) obj[21]) + ","//归档周期
                        + ConvertUtils.convert((Integer) obj[22]) + ","//卷数
                        + ConvertUtils.convert((Integer) obj[23]) + ","//保存年限
                        + status + "," //项目状态
                        + archive_file_type + ","//项目状况
                        + ConvertUtils.convert((String) obj[29])//项目所在地
                ;
                String[] ss = result.split(",");
                for (int k=0;k<ss.length;k++){
                    XSSFCell cell = row.createCell(k);
                    Pattern floatNum = Pattern.compile("^(-?\\d+)(\\.\\d+)?");
                    Pattern intNum = Pattern.compile("^-?\\d+$");
                    String temp = ss[k];
                    if (floatNum.matcher(temp).matches()){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(Double.valueOf(temp));
                    }else if (intNum.matcher(temp).matches()){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(Integer.valueOf(temp));
                    }else {
                        cell.setCellValue(temp);
                    }
                }
            }
        }

        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\招标台帐.xls"));
        workbook.write(fos);
        fos.flush();
        fos.close();
    }


}
