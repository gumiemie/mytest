package com.guyang.sources.itextpdf.itext7;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 用一句话描述这个类
 * @date 2018/10/10 14:44$
 */
public class WatermarkingEventHandler implements IEventHandler {
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDocument = documentEvent.getDocument();
        PdfPage pdfPage = documentEvent.getPage();

        String markImagePath = this.getClass().getClassLoader().getResource("font").getFile() + "/logo.png";
        try {
            ImageData imageData = ImageDataFactory.create(markImagePath);
            Image image = new Image(imageData);
            float plainWidth = image.getImageWidth() * 0.4f;
            float plainHeight = image.getImageHeight() * 0.4f;
            Document document = new Document(pdfDocument);

            PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
            PdfExtGState pdfExtGState = new PdfExtGState();
            pdfExtGState.setFillOpacity(0.4f);
            pdfCanvas.setExtGState(pdfExtGState);
            for (int k = 0; k < 5; k++) {
                for (int j = 0; j < 6; j++) {
                    float pageHeight = pdfDocument.getDefaultPageSize().getHeight();
                    float pageWidth = pdfDocument.getDefaultPageSize().getWidth();
                    image.setFixedPosition(k * pageWidth * 0.32f + 60, j * pageHeight * 0.2f + 60); // 水印的位置
                    image.setWidth(plainWidth);
                    image.setHeight(plainHeight);
                    image.setRotationAngle(25.57);
                    document.add(image);
                }
            }
        } catch (Exception e) {

        }

    }
}
