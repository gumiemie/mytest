/*
package itextpdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.font.FontProvider;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

*/
/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/7/20 14:48$
 *//*

public class Html2Pdf {
    public static final String sourceFolder = "C:\\Users\\Administrator\\Desktop\\新建文本文档.html";
    public static final String destinationFolder = "C:\\Users\\Administrator\\Desktop\\itext";


    public void export() {


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String htmlSource = sourceFolder;
        String resourceFolder = sourceFolder;
        String pdfDest = destinationFolder + ".pdf";
        File file = new File(pdfDest);

        System.out.println("Parsing: " + htmlSource);
        file.getParentFile().mkdirs();

        new Html2Pdf().createPdf(htmlSource, pdfDest, resourceFolder);
    }

    public void createPdf(String src, String dest, String resources) throws IOException {
        try {
            FileOutputStream outputStream = new FileOutputStream(dest);

            WriterProperties writerProperties = new WriterProperties();
            //Add metadata
            writerProperties.addXmpMetadata();

            PdfWriter pdfWriter = new PdfWriter(outputStream, writerProperties);

            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE,new WaterMark7());
            //pdfDoc.getCatalog().setLang(new PdfString("en-US"));
            //Set the document to be tagged
            pdfDoc.setTagged();
            pdfDoc.getCatalog().setViewerPreferences(new PdfViewerPreferences().setDisplayDocTitle(true));

            //Set meta tags
            PdfDocumentInfo pdfMetaData = pdfDoc.getDocumentInfo();
            pdfMetaData.setAuthor("Samuel Huylebroeck");
            pdfMetaData.addCreationDate();
            pdfMetaData.getProducer();
            pdfMetaData.setCreator("iText Software");
            pdfMetaData.setKeywords("example, accessibility");
            pdfMetaData.setSubject("PDF accessibility");
            //Title is derived from html

            // pdf conversion
            ConverterProperties props = new ConverterProperties();
            FontProvider fp = new FontProvider();
            //获取字体文件目录
            String fontDir = this.getClass().getClassLoader().getResource("font").getFile();
            //注册字体文件
            fp.addDirectory(fontDir);
            fp.addSystemFonts();

            props.setFontProvider(fp);
            props.setBaseUri(resources);
            //Setup custom tagworker factory for better tagging of headers
            */
/*DefaultTagWorkerFactory tagWorkerFactory = new AccessibilityTagWorkerFactory();
            props.setTagWorkerFactory(tagWorkerFactory);*//*


            String s = "哈哈俣";
            HtmlConverter.convertToPdf(new ByteArrayInputStream(s.getBytes("utf-8")), pdfDoc, props);

            pdfDoc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
*/
