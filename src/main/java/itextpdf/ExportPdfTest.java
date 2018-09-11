package itextpdf;

import com.alibaba.fastjson.JSON;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static itextpdf.DrawSealUtil.drawCircularSeal;


public class ExportPdfTest {

    @Test
    public void export() throws Exception {

        //创建document
        Document document = new Document(PageSize.A4);
        //设置要导出的文件名
        File file = new File("C:\\Users\\Administrator\\Desktop\\test.pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        String markImagePath = this.getClass().getClassLoader().getResource("font").getFile() + "/logo.png";
        Image instance = Image.getInstance(markImagePath);
        //开始写
        writer.setPageEvent(new Watermark(instance));//水印内容
        document.open();

        //获取字体文件目录
        String fontDir = this.getClass().getClassLoader().getResource("font").getFile();
        //注册字体文件
        XMLWorkerFontProvider xmlWorkerFontProvider = new XMLWorkerFontProvider(fontDir);
        //获取某个字体文件
        Font font12 = xmlWorkerFontProvider.getFont("微软雅黑", 12);

        //设置中文字体，本文举例使用的是仿宋
        //BaseFont baseFont = BaseFont.createFont("font/simfang.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //Font font12 = new Font(baseFont, 12);

        //页眉，是可以双层或更多。取决于放置的坐标
        PdfContentByte cb = writer.getDirectContent();
        //页眉左上
        //Phrase leftp1 = new Phrase("页眉左上", font12);
        //ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, leftp1, document.left(), document.top() + 13, 0);
        //页眉左下
        //Phrase leftp2 = new Phrase("页眉左下:", font12);
        //ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, leftp2, document.left(), document.top(), 0);
        //页眉右上
        //Phrase rightp1 = new Phrase("页眉右上:", font12);
        //float rightp1WidthPoint = baseFont.getWidthPoint(rightp1.getContent(), 12);
        //ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, rightp1, document.right() - rightp1WidthPoint, document.top() + 13, 0);
        //页眉右下
        //Phrase rightp2 = new Phrase("页眉右下:", font12);
        //float rightp2WidthPoint = baseFont.getWidthPoint(rightp2.getContent(), 12);
        //ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, rightp2, document.right() - rightp2WidthPoint, document.top(), 0);

        //下划线
//        PdfContentByte canvas = writer.getDirectContent();
//        CMYKColor magentaColor = new CMYKColor(1.f, 1.f, 1.f, 1.f);
//        canvas.setColorStroke(magentaColor);
//        canvas.moveTo(document.left(), document.top() - 4);
//        canvas.lineTo(document.right(), document.top() - 4);
//        canvas.closePathStroke();

        /*//标题
        Font h1 = new Font(baseFont, 13.5f, Font.BOLD);//创建全文标题字体，参数分别是字体类别,大小(pt),效果
        Paragraph paragraphTitle = new Paragraph("标题测试", h1);//设置文本内容和要使用的字体
        paragraphTitle.setAlignment(Element.ALIGN_CENTER);//设置居中
        document.add(paragraphTitle);*/

        //正文标题1
        /*Font h3 = new Font(baseFont, 14, Font.BOLD);//创建标题字体
        Paragraph paragraphH1 = new Paragraph("正文标题1", h3);
        document.add(paragraphH1);*/

        //正文1 一个空白行
        Paragraph paragraphText1 = new Paragraph(" ", font12);
        document.add(paragraphText1);

        //正文2
        String content = "<table border=\"0\" cellspacing=\"0\" style=\"width:553.5pt;border:0\" class=\"ke-zeroborder\">\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:0pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">1<span>、招标条件</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">项目概况：北京必联电子<span>2018</span><span>年中第一批发电机项目招标项目</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">资金到位或资金来源落实情况：已具备</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">项目已具备招标条件的说明：已具备</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:0pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">2<span>、招标内容：</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标项目编号：<span>8821-184TESTtHI07/02</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标项目名称：北京必联电子<span>2018</span><span>年中第一批发电机项目招标项目</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">项目实施地点：中国北京市</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标产品列表<span>(</span><span>主要设备</span><span>)</span><span>：</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<table border=\"0\" cellspacing=\"0\" style=\"width:475.5pt;border:0\" class=\"ke-zeroborder\">\n" +
                "\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td width=\"33\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;background:#F0F7FE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">序号</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td width=\"262\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;background:#F0F7FE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">产品名称</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td width=\"41\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;background:#F0F7FE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">数量</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td width=\"262\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;background:#F0F7FE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">简要技术规格</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td width=\"33\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;background:#F0F7FE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">备注</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td width=\"33\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\" align=\"center\" style=\"margin-left:0.0000pt;text-align:center;\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">1</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td width=\"262\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\" align=\"center\" style=\"margin-left:0.0000pt;text-align:center;\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">CNC cylindrical grinding machine</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td width=\"41\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\" align=\"center\" style=\"margin-left:0.0000pt;text-align:center;\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">2sets</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td width=\"262\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\" align=\"center\" style=\"margin-left:0.0000pt;text-align:center;\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">as shown in the bidding document</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td width=\"33\" valign=\"center\" style=\"border:1.0000pt solid #E0E7EE;\">\n" +
                "\t\t\t\t\t\t\t\t<p class=\"MsoNormal\" align=\"center\" style=\"margin-left:0.0000pt;text-align:center;\">\n" +
                "\t\t\t\t\t\t\t\t\t<span style=\"font-family:宋体;font-size:12pt;\">32</span><span style=\"font-family:Calibri;font-size:10.5pt;\"></span>\n" +
                "\t\t\t\t\t\t\t\t</p>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<p class=\"MsoNormal\" align=\"center\" style=\"margin-left:0.0000pt;text-align:center;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">&nbsp;</span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:0pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">3<span>、投标人资格要求</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">投标人应具备的资格或业绩：投标人应具备的资格或业绩：<span>1</span><span>合格的投标人应具有圆满履行合同的能力，具体应符合下列条件： </span><span>5</span><span>．</span><span>1</span><span>．</span><span>1</span><span>具有独立订立合同的权利。 </span><span>5</span><span>．</span><span>1</span><span>．</span><span>2</span><span>在专业技术、设备设施、人员组织、业绩经验等方面具有设计、制造、质量控制、经营管理的相应的资格和能力。 </span><span>5</span><span>．</span><span>1</span><span>．</span><span>3</span><span>具有完善的质量保证体系。 </span><span>5</span><span>．</span><span>1</span><span>．</span><span>4</span><span>业绩 </span><span>5</span><span>．</span><span>1</span><span>．</span><span>4</span><span>．</span><span>1</span><span>具有设计、制造与招标设备相同</span><span>/</span><span>相近设备</span><span>1</span><span>到</span><span>2</span><span>台套</span><span>2</span><span>年以上良好的运行经验，在安装调试运行中未发现重大的设备质量问题或已有有效的改进措施； </span><span>5</span><span>．</span><span>1</span><span>．</span><span>4</span><span>．</span><span>2</span><span>或主机设备有相应业绩厂商的技术合作或技术支持。 </span><span>5</span><span>．</span><span>1</span><span>．</span><span>5</span><span>具有良好的银行资信和商业信誉，没有处于被责令停业，财产被接管、冻结、破产状态。 </span><span>5</span><span>．</span><span>1</span><span>．</span><span>6</span><span>电力工业部和机械工业部共同认定的主机设备制造厂商；电力工业部成套设备局与电力工业部电力规划设计总院共同发布，或由国家电力公司安全运行与发输电部颁发人网许可证的厂商。</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">是否接受联合体投标：不接受</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">未领购招标文件是否可以参加投标：不可以</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:0pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">4<span>、招标文件的获取</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标文件领购开始时间：<span>2018-07-19</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标文件领购结束时间：<span>2018-07-26</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">是否在线售卖标书：是</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">获取招标文件方式：现场领购</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标文件领购地点：北京市海淀区紫竹院路<span>81</span><span>号院</span><span>3</span><span>号楼北方地产大厦</span><span>4</span><span>层</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标文件售价：免费</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:0pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">5<span>、投标文件的递交</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">投标截止时间（开标时间）：<span>2018-08-08 23:59</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">投标文件送达地点：北京市海淀区紫竹院路<span>81</span><span>号院</span><span>3</span><span>号楼北方地产大厦</span><span>4</span><span>层</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">开标地点：北京市海淀区紫竹院路<span>81</span><span>号院</span><span>3</span><span>号楼北方地产大厦</span><span>4</span><span>层</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:0pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">6<span>、投标人在投标前需在中国国际招标网上完成注册。评标结果将在中国国际招标网公示。</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:0pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">7<span>、联系方式</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标人：中国长科学和器材有限公司</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">地址：北京市海淀区紫竹院路<span>81</span><span>号院</span><span>3</span><span>号楼北方地产大厦</span><span>4</span><span>层</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">联系人：必联网 客服部</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">联系方式 ：<span>400-0606-000222</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标代理机构：<span>testdm4</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">地址：北京市海淀区紫竹院路<span>81</span><span>号院</span><span>3</span><span>号楼北方地产大厦</span><span>4</span><span>层</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">联系人：必联网 客服部</span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">联系方式 ：<span>400-0606-222</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:0pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">8<span>、汇款方式</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标代理机构开户银行<span>(</span><span>人民币</span><span>): </span><span>中国 银行</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">招标代理机构开户银行<span>(</span><span>美元</span><span>): The bank of China,</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">账号<span>(</span><span>人民币</span><span>): 6217000210001314649</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td width=\"738\" valign=\"center\" style=\"border:0\">\n" +
                "\t\t\t\t<p class=\"MsoNormal\" style=\"text-indent:15.75pt;\">\n" +
                "\t\t\t\t\t<span style=\"font-family:Arial;color:#666666;font-size:9pt;\">账号<span>(</span><span>美元</span><span>): 6217000210001314649</span></span><span style=\"font-family:Arial;color:#666666;font-size:9pt;\"></span>\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</td>\n" +
                "\t\t</tr>\n" +
                "\t</tbody>\n" +
                "</table>\n" +
                "<p class=\"MsoNormal\">\n" +
                "\t&nbsp;\n" +
                "</p>";
        //正文2内容是html,需要将html转换成普通文字,方法如下:
        /*org.jsoup.nodes.Document contentDoc = Jsoup.parseBodyFragment(content);
        org.jsoup.nodes.Document.OutputSettings outputSettings = new org.jsoup.nodes.Document.OutputSettings();
        outputSettings.syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        contentDoc.outputSettings(outputSettings);
        String parsedHtml = contentDoc.outerHtml();*/

        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("<html><body>").append(content).append("</body></html>");

        //将html按样式转换成普通文字
        InputStream cssIs = new ByteArrayInputStream("* {font-family: Microsoft YaHei;}".getBytes("UTF-8"));
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(stringBuilder.toString().getBytes()), cssIs, xmlWorkerFontProvider);


        //页脚
        /*PdfContentByte cj = writer.getDirectContent();
        Phrase leftp3 = new Phrase("页脚左侧", font12);
        ColumnText.showTextAligned(cj, Element.ALIGN_LEFT, leftp3, document.left(), document.bottom(), 0);
        Phrase rightp3 = new Phrase("页脚右侧", font12);
        final float rightp3WidthPoint = baseFont.getWidthPoint(rightp3.getContent(), 12);
        ColumnText.showTextAligned(cj, Element.ALIGN_LEFT, rightp3, document.right() - rightp3WidthPoint, document.bottom(), 0)*/;
        String path=drawCircularSeal("D:/","宋体","公告签章","必联北京电子商务科技有限公司公司",null,"华文行楷","宋明轩");
        Image img = Image.getInstance(path);
        float plainHeight = img.getPlainHeight();
        float plainWidth = img.getPlainWidth();
        img.scaleAbsolute(plainHeight*0.5f,plainWidth*0.5f);
        float height = document.getPageSize().getHeight();
        float width = document.getPageSize().getWidth();
        img.setAbsolutePosition(width-100,height-100);
        document.add(img);
        //执行关闭操作，将pdf内容写出
        document.close();
        //byte[] bytes = byteArrayOutputStream.toByteArray();
        //outputStream.write(bytes);

    }
/*
    public void read(File file) throws Exception {
        PdfReader pdfReader = new FdfReader(file.getPath(), "PDF".getBytes());
        FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\testpdf.pdf"));
        PdfStamper stamp = new PdfStamper(pdfReader, outputStream);

    }

    @Test
    public void execute1() throws Exception {
        File file = new File("C:\\Users\\Administrator\\Desktop\\test.pdf");
        read(file);
    }*/


    @Test
    public void renderTemplate() throws Exception {
        Configuration cfg = Configuration.defaultConfiguration();

        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();

        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        Template t = gt.getTemplate(TEMPLATE);

        String s = "{\"agency\":\"test悦招money财运亨通科技有限公司\",\"bidBookEndTime\":1529306880000,\"bidCode\":\"gegegege\",\"createTime\":1528875193000,\"createUserId\":\"e3765d72a2bd49a1b68888bd74bfa654\",\"docKeywords\":\"\",\"docSource\":131,\"docStatus\":11,\"docTitle\":\"gegegegege\",\"endDate\":1530602880000,\"fundSource\":\"自筹资金\",\"fundSourceCode\":\"005006\",\"id\":\"272a5cf478264b6786999d3fdf861401\",\"industryCode\":\"101101104105102\",\"industryName\":\"利废建筑材料\",\"infoclass\":\"公告\",\"infoclassId\":\"028001\",\"noticeContent\":{\"content\":\"<p>\\n\\tgegegege\\n</p>\\n<p>\\n\\tgeg表林志玲\\n</p>\\n<p>\\n\\t不可思议\\n</p>\",\"createTime\":1528875193000,\"createUserId\":\"e3765d72a2bd49a1b68888bd74bfa654\",\"id\":\"50add283240b431593780eb0c0fc94b8\",\"noticeId\":\"272a5cf478264b6786999d3fdf861401\",\"systemStatus\":1,\"tenantId\":\"63b3bdc8c32c11e598bcfa163e84de1b\",\"test\":true},\"noticeFileList\":[],\"orgCode\":101003,\"procurementType\":\"公开招标\",\"procurementTypeCode\":\"023006\",\"projNature\":\"中央投资\",\"projNatureCode\":\"020002\",\"projType\":\"货物\",\"projTypeCode\":\"101\",\"provCode\":\"360700\",\"provName\":\"江西省-赣州市\",\"pubDate\":1528874880000,\"systemStatus\":1,\"tenantId\":\"63b3bdc8c32c11e598bcfa163e84de1b\",\"tenderer\":\"ge\",\"updateTime\":1528875193000}";
        Map parse = JSON.parseObject(s, HashMap.class);

        t.binding(parse);
        String content = t.render();

        System.out.println(content);

    }

    public static final String TEMPLATE = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title></title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<!-- 公告信息 -->\n" +
            "<div class=\"view-box clearfix\">\n" +
            "    <div class=\"view-left fl\">\n" +
            "        <h2 class=\"view-title\">${docTitle}</h2>\n" +
            "        <ul class=\"view-info-head mg-t20\">\n" +
            "            <li class=\"clearfix\">\n" +
            "                <span class=\"title\">采购类型：</span>\n" +
            "                <span class=\"con\">${procurementType}</span>\n" +
            "            </li>\n" +
            "            <li class=\"clearfix\">\n" +
            "                <span class=\"title\">公告类型：</span>\n" +
            "                <span class=\"con\">${infoclass}</span>\n" +
            "            </li>\n" +
            "            <li class=\"clearfix bidCode\">\n" +
            "                <span class=\"title\">项目编号：</span>\n" +
            "                <span class=\"con\">${bidCode}</span>\n" +
            "            </li>\n" +
            "            <li class=\"clearfix pubDate\">\n" +
            "                <span class=\"title\">发布时间：</span>\n" +
            "                <span class=\"con\">${pubDate}</span>\n" +
            "            </li>\n" +
            "            <%if(bidBookEndTime!=null){%>\n" +
            "            <li class=\"clearfix bidBookEndTime\">\n" +
            "                <span class=\"title\">领购截止：</span>\n" +
            "                <span class=\"con\">${bidBookEndTime}</span>\n" +
            "            </li>\n" +
            "            <% } %>\n" +
            "        </ul>\n" +
            "        <div class=\"pd-l20 pd-r20\">\n" +
            "            <h3 class=\"view-conTitle pd-t35 pd-b5\">公告内容</h3>\n" +
            "            <div class=\"view-content\">\n" +
            "                ${noticeContent.content}\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";
}
