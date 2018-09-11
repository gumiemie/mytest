package itextpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


public class Watermark extends PdfPageEventHelper {
	
	private Image image;
	private float plainWidth;
	private float plainHeight;
	final float IMAGE_SIZE = 0.4f;//图片缩放比例
	final float IMAGE_GSTATE = 0.4f;//图片透明度
	
	public Watermark(Image img) {
		// TODO Auto-generated constructor stub
		this.image = img;
		plainWidth = image.getPlainWidth() * IMAGE_SIZE;
		plainHeight = image.getPlainHeight() * IMAGE_SIZE;
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document){
		try {
			PdfContentByte content = writer.getDirectContent();
			content.beginText();
			if(image != null){
				PdfGState gs = new PdfGState();
				gs.setFillOpacity(IMAGE_GSTATE);// 设置透明度为
				content.setGState(gs);
				for(int k=0;k<5;k++){
					for (int j = 0; j <6; j++) {
						float pageHeight = document.getPageSize().getHeight();
						float pageWidth = document.getPageSize().getWidth();
				        image.setAbsolutePosition(k*pageWidth*0.32f+60, j*pageHeight*0.2f+60); // 水印的位置
				        image.scaleAbsolute(plainWidth, plainHeight);//设置图片大小
						//image.scaleToFit(103, 48);
				        image.setRotationDegrees(25);// 旋转度
				        content.addImage(image);
					}
				}
			}
			content.endText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
