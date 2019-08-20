package com.guyang.sources.itextpdf.itext7;

import com.alibaba.fastjson.JSON;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.DefaultTagWorkerFactory;
import com.itextpdf.html2pdf.css.apply.impl.DefaultCssApplierFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.property.TextAlignment;
import com.guyang.sources.itextpdf.DrawSealUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class ExportPdfTest {

    @Test
    public void export() throws Exception {
        //设置要导出的文件名
        File file = new File("C:\\Users\\Administrator\\Desktop\\test.pdf");
        OutputStream outputStream = new FileOutputStream(file);
        //创建document
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        WriterProperties writerProperties = new WriterProperties();
        writerProperties.addXmpMetadata();

        PdfWriter pdfWriter = new PdfWriter(outputStream, writerProperties);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);

        pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new WatermarkingEventHandler());

        //开始写
        ConverterProperties props = new ConverterProperties();
        FontProvider fp = new FontProvider();
        fp.addSystemFonts();
        fp.addStandardPdfFonts();

        //获取字体文件目录
        String fontDir = this.getClass().getClassLoader().getResource("font").getFile();
        fp.addDirectory(fontDir);

        props.setFontProvider(fp);

        //正文2
        String content = "<h2 style=\"text-align:center;\">\n" +
                "\t1126TEXMXJCGXM/001===项目名称招标采购项目竞争性磋商结果公告\n" +
                "</h2>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t（招标编号：1126TEXMXJCGXM/001）\n" +
                "</p>\n" +
                "<!--\n" +
                "<p>\n" +
                "\t招标项目所在地区： 辽宁省-营口市\n" +
                "</p>\n" +
                "-->\n" +
                "<p>\n" +
                "\t本  1126TEXMXJCGXM/001===项目名称招标采购项目  （招标项目编号：  1126TEXMXJCGXM/001  ），确定标段(包)[001]1126TEXMXJCGXM/001===项目名称招标采购项目的中标人如下：\n" +
                "</p>\n" +
                "<p style=\"font-size:14px;\">\n" +
                "\t一、中标人信息\n" +
                "</p>\n" +
                "<!--\n" +
                "<p>\n" +
                "\t标段（包）编号+标段包名称：1126TEXMXJCGXM/001+ 1126TEXMXJCGXM/001&#x3D;&#x3D;&#x3D;项目名称招标采购项目\n" +
                "</p>\n" +
                "-->\n" +
                "<p>\n" +
                "\t标段(包)[001]1126TEXMXJCGXM/001===项目名称招标采购项目：\n" +
                "</p>\n" +
                "<p>\n" +
                "\t中标人：新华制药股份有限公司、 山东新华东风化工有限公司\n" +
                "</p>\n" +
                "<p style=\"font-size:16px;\">\n" +
                "\t<b>二、其他公示内容</b>\n" +
                "</p>\n" +
                "<p>\n" +
                "\t无\n" +
                "</p>\n" +
                "<p style=\"font-size:14px;font-weight:700;\">\n" +
                "\t三、监督部门\n" +
                "</p>\n" +
                "<p>\n" +
                "\t本招标项目的监督部门为：监督部门 。\n" +
                "</p>\n" +
                "<p style=\"font-size:14px;font-weight:700;\">\n" +
                "\t四、联系方式\n" +
                "</p>\n" +
                "<p>\n" +
                "\t招 标 人：私有云迁移单实例九三测试\n" +
                "</p>\n" +
                "<p>\n" +
                "\t地    址：jsjtcgb\n" +
                "</p>\n" +
                "<p>\n" +
                "\t联 系 人：jsjtcgb\n" +
                "</p>\n" +
                "<p>\n" +
                "\t电    话：18500328327\n" +
                "</p>\n" +
                "<p>\n" +
                "\t电子邮件：jsjtcgb@asdf.ll\n" +
                "</p>\n" +
                "<p>\n" +
                "\t电子邮件：jsjtcgb@asdf.ll\n" +
                "</p>\n" +
                "<p>\n" +
                "\t电子邮件：jsjtcgb@asdf.ll\n" +
                "</p>\n" +
                "<p>\n" +
                "\t电子邮件：jsjtcgb@asdf.ll\n" +
                "</p>\n" +
                "<p>\n" +
                "\t电子邮件：jsjtcgb@asdf.ll\n" +
                "</p>\n" +
                "<p>\n" +
                "\t电子邮件：jsjtcgb@asdf.ll\n" +
                "</p>\n" +
                "<p>\n" +
                "\t电子邮件：jsjtcgb@asdf.ll\n" +
                "</p>\n" +
                "<p>\n" +
                "\t招标代理机构：测试用工程造价咨询有限公司\n" +
                "</p>\n" +
                "<p>\n" +
                "\t地    址：\n" +
                "</p>\n";
        DefaultTagWorkerFactory tagWorkerFactory = new DefaultTagWorkerFactory();
        props.setTagWorkerFactory(tagWorkerFactory);
        props.setFontProvider(fp);
        props.setCssApplierFactory(new DefaultCssApplierFactory());

        StringBuilder stringBuilder = new StringBuilder("<div style=\"font-size:13.5pt;text-align:center\"><b>").append("公告标题").append("</b><br></div>");
        stringBuilder.append("<html><body style=\"font-family:Microsoft YaHei;font-size:14px;\">").append(content).append("</body></html>");

        //将html按样式转换成普通文字
        Document document2 = HtmlConverter.convertToDocument(new ByteArrayInputStream(stringBuilder.toString().getBytes("utf-8")), pdfDocument, props);
        PdfFontFactory.registerDirectory(fontDir);
        PdfFont pdfFont = PdfFontFactory.createRegisteredFont("微软雅黑", PdfEncodings.IDENTITY_H);
        //空行
        Paragraph paragraph = new Paragraph("\n");
        document2.add(paragraph);
        int pageNumber1 = document2.getRenderer().getCurrentArea().getPageNumber();
        float tempHeight1 = document2.getRenderer().getCurrentArea().getBBox().getHeight();
        document2.add(paragraph);
        float tempHeight2 = document2.getRenderer().getCurrentArea().getBBox().getHeight();
        int pageNumber2 = document2.getRenderer().getCurrentArea().getPageNumber();
        float singleColumnHeight = 0;
        if (pageNumber1 == pageNumber2) {
            singleColumnHeight = tempHeight1 - tempHeight2;
        } else {
            document2.add(paragraph);
            float tempHeight3 = document2.getRenderer().getCurrentArea().getBBox().getHeight();
            singleColumnHeight = tempHeight2 - tempHeight3;
        }

        float pageHeight = pdfDocument.getDefaultPageSize().getHeight();
        float currentHeight = document2.getRenderer().getCurrentArea().getBBox().getHeight();
        float marginBottom = 36f;
        if (currentHeight < singleColumnHeight * 5) {
            document2.add(paragraph);
            document2.add(paragraph);
            document2.add(paragraph);
            document2.add(paragraph);
        }

        Paragraph paragraphs1 = new Paragraph("招标人或其招标代理机构主要负责人（项目负责人）：_______________（签名）");
        paragraphs1.setFont(pdfFont).setFontSize(10);
        paragraphs1.setTextAlignment(TextAlignment.RIGHT);//设置居右
        document2.add(paragraphs1);

        float width = pdfDocument.getDefaultPageSize().getWidth();
        String nameSeal = DrawSealUtil.drawText("d:/", "顾洋", "仿宋");
        ImageData nameSealImageData = ImageDataFactory.create(nameSeal);
        float width1 = nameSealImageData.getWidth();
        float height1 = nameSealImageData.getHeight();
        currentHeight = document2.getRenderer().getCurrentArea().getBBox().getHeight();
        Image nameSealImage = new Image(nameSealImageData);
        nameSealImage.setWidth(width1 * 0.23f);
        nameSealImage.setHeight(height1 * 0.23f);
        nameSealImage.setFixedPosition(width - 135, currentHeight > marginBottom ? currentHeight + singleColumnHeight * 1.4f : pageHeight - marginBottom - singleColumnHeight / 1.5f);
        document2.add(nameSealImage);

        //增加一个空行
        document2.add(paragraph);
        document2.add(paragraph);
        String path = DrawSealUtil.drawCircularSeal("D:/", "宋体", "公告签章", "必联北京电子商务科技有限公司公司", null, "", "");
        ImageData imageData = ImageDataFactory.create(path);
        Image img = new Image(imageData);
        img.scaleAbsolute(100, 100);
        currentHeight = document2.getRenderer().getCurrentArea().getBBox().getHeight();
        img.setFixedPosition(width - 170, currentHeight - 45);
        document2.add(img);
        Paragraph paragraphs2 = new Paragraph("招标人或其招标代理机构：___________________（盖章）");
        paragraphs2.setFont(pdfFont).setFontSize(10).setTextAlignment(TextAlignment.RIGHT);
        document2.add(paragraphs2);

        document2.close();
        //执行关闭操作，将pdf内容写出
        pdfDocument.close();
        //byte[] bytes = byteArrayOutputStream.toByteArray();
        //outputStream.write(bytes);

    }

    /*private static void addEpic(PdfWriter writer, PdfDocument pdfDocument, FontProvider fontProvider, String imgUrl, String nameUrl) throws Exception {
        Paragraph paragraphText1 = new Paragraph(" ");
        float widths = pdfDocument.getDefaultPageSize().getWidth();
        float heights = pdfDocument.getDefaultPageSize().getHeight();
        Method getPdfDocument = writer.getClass().getDeclaredMethod("getPdfDocument");
        getPdfDocument.setAccessible(true);
        PdfDocument pdfD = (PdfDocument) getPdfDocument.invoke(writer);
        Field getHeight = pdfD.getClass().getDeclaredField("currentHeight");
        getHeight.setAccessible(true);
        // 获取内容的高度
        float currentHeight = getHeight.getFloat(pdfD);
        System.out.println("-------------------------" + (heights - currentHeight));
        if (heights - currentHeight < 216) {
            Document document1 = new Document(pdfDocument);
            document1.add(paragraphText1);
            document1.add(paragraphText1);
            document1.add(paragraphText1);
            document1.add(paragraphText1);
            document1.add(paragraphText1);
            document1.add(paragraphText1);
        }
        PdfFont font = PdfFontFactory.createFont("微软雅黑");
        font.
        Paragraph paragraphs1 = new Paragraph("招标人或其招标代理机构主要负责人（项目负责人）：_______________（签名）").setFont();
        paragraphs1.setAlignment(Element.ALIGN_RIGHT);//设置居中
        document.add(paragraphText1);
        document.add(paragraphText1);
        document.add(paragraphs1);
        //增加一个空行
        document.add(paragraphText1);
        document.add(paragraphText1);
        Paragraph paragraphs2 = new Paragraph("招标人或其招标代理机构：___________________（盖章）", seal1);
        paragraphs2.setAlignment(Element.ALIGN_RIGHT);//设置居中
        document.add(paragraphs2);

        int n = writer.getPageNumber();
        Image img = Image.getInstance(imgUrl);
        Image nameImg = Image.getInstance(nameUrl);
        currentHeight = getHeight.getFloat(pdfD);
        img.setAbsolutePosition(widths - 170, heights - currentHeight - 90);
        img.setAlignment(Image.ALIGN_RIGHT);
        img.scaleAbsolute(100, 100);
        nameImg.setAbsolutePosition(widths - 145, heights - currentHeight + 18);
        nameImg.setAlignment(Image.ALIGN_RIGHT);
        nameImg.scaleAbsolute(nameImg.getPlainWidth() * 0.23f, nameImg.getPlainHeight() * 0.23f);
        if (n > 0) {
            writer.getDirectContentUnder().addImage(img);
            writer.getDirectContentUnder().addImage(nameImg);
        }

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
