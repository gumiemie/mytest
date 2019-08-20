/*
package itextpdf;*//*

package itextpdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.element.Image;


public class WaterMark7 implements IEventHandler {

    private Image image;
    private float plainWidth;
    private float plainHeight;
    final float IMAGE_SIZE = 0.4f;//图片缩放比例
    final float IMAGE_GSTATE = 0.4f;//图片透明度

*/
/*
    @Override
	public void onEndPage(PdfWriter writer, Document document) {
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
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
/*
    @Override
    public void handleEvent(Event event) {
        ImageData imageData = null;
        String markImagePath = this.getClass().getClassLoader().getResource("font").getFile() + "/logo.png";
        try {
            imageData = ImageDataFactory.create(markImagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        plainWidth = imageData.getWidth() * IMAGE_SIZE;
        plainHeight = imageData.getHeight()* IMAGE_SIZE;
        PdfDocumentEvent pdfDocumentEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDocument = pdfDocumentEvent.getDocument();
        float pageHeight = pdfDocument.getDefaultPageSize().getHeight();
        float pageWidth = pdfDocument.getDefaultPageSize().getWidth();
        PdfPage page = pdfDocumentEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(),page.getResources(),pdfDocument);
        PdfExtGState pdfExtGState = new PdfExtGState();
        pdfExtGState.setFillOpacity(IMAGE_GSTATE);
        canvas.setExtGState(pdfExtGState);
        imageData.setRotation(25);
        imageData.setWidth(plainWidth);
        imageData.setHeight(plainHeight);
        for (int k = 0; k < 5; k++) {
            for (int j = 0; j < 6; j++) {
                //image.scaleToFit(103, 48);
                canvas.addImage(imageData,k * pageWidth * 0.32f + 60,j*pageHeight*0.2f+60,false);
            }
        }
    }
}

*/
