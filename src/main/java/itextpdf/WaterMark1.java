/*
package itextpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;




public class WaterMark1 extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        //获取水印图片的路径
        String markImagePath = this.getClass().getClassLoader().getResource("font").getFile() + "/logo.png";
        float pageHeight = document.getPageSize().getHeight();
        float pageWidth = document.getPageSize().getWidth();
        int xNum = 3;//横向多少个水印
        int yNum = 5;//竖向多少个水印
        int marginLeft = 30;//图片左边距
        int marginBottom = 80;//图片底边距

        try {

            Image img = Image.getInstance(markImagePath);//生成水印图片
            final float IMAGE_SIZE = 0.5f;//图片缩放比例，大小0
            float plainWidth = img.getPlainWidth() * IMAGE_SIZE;
            float plainHeight = img.getPlainHeight() * IMAGE_SIZE;
            img.scaleAbsolute(plainWidth, plainHeight);//设置图片大小
            img.setAlignment(Image.UNDERLYING); // 在字下面
            //设置水印图片的坐标。
            //image.setRotation(-30);//设置旋转 弧度
            img.setRotationDegrees(15);//设置旋转 角度
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.8f);// 设置透明度为0.5
            PdfContentByte content = writer.getDirectContent();
            content.setGState(gs);
            //image.scalePercent(50);//设置依照比例缩放，与设置大小作用相同
            float paddingx = (pageWidth - xNum * plainWidth - marginLeft) / xNum;//计算水平间距
            float paddingy = (pageHeight - yNum * plainHeight - marginBottom) / yNum;//计算垂直间距

            for (int x = 0; x < xNum; x++) {
                for (int y = 0; y < yNum; y++) {
                    //设置图片坐标
                    img.setAbsolutePosition(marginLeft + x * (paddingx + plainWidth) + ((y + (yNum + 1) % 2) % 2) * paddingx, marginBottom + y * (paddingy + plainHeight));
                    //将水印图片加入到文档中,可使用循环添加多个，添加多个时注意每个图片的坐标等属性
                    document.add(img);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
*/
