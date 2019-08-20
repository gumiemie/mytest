package com.guyang.sources.itextpdf;

import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@SuppressWarnings("all")
public class DrawSealUtil {

    private final static int DIAMETER = 400;

    private final static String FOLDER = "seal";

    private final static String fontPath = "";

    /**
     * 画圆形签章
     *
     * @param filePath     签章图片存放位置
     * @param fontName     签章的字体
     * @param sealName     签章的名字
     * @param name         说明信息
     * @param numCode      数字编码
     * @param singFontName 签名字体
     * @param sign         签名信息
     * @return
     */
    public static String drawCircularSeal(String filePath, String fontName, String sealName, String name, String numCode, String singFontName, String sign) {
        if (StringUtils.isBlank(filePath))
            return null;
        String imgFile = null;
        //创建缓存
        BufferedImage bufImg = new BufferedImage(DIAMETER + 14, DIAMETER + 14, BufferedImage.TYPE_INT_RGB);
        //获得画布
        Graphics2D gs = bufImg.createGraphics();
        //设置背景透明    
        bufImg = gs.getDeviceConfiguration().createCompatibleImage(DIAMETER + 14, DIAMETER + 16, Transparency.TRANSLUCENT);
        gs.dispose();
        gs = bufImg.createGraphics();
        //设置画笔颜色
        gs.setColor(new Color(204, 41, 41));
        //设置画笔宽度
        gs.setStroke(new BasicStroke(14));
        //设置锯齿圆滑  
        gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //画圆形边框
        gs.drawOval(6, 6, DIAMETER, DIAMETER);
        //字体
        String usedFontName = findSystemFont(fontName);
        //画五角星
        drawFiveStar(gs, DIAMETER / 2 + 6, DIAMETER / 2 + 5, DIAMETER / 5 - 4);
        //画说明数据
        drawSealMessage(gs,usedFontName, sealName, DIAMETER/2+6, DIAMETER * 8/9);        
        //画上方的环形字
        drawSealName(gs, usedFontName, name, DIAMETER / 2 + 6, DIAMETER / 2 + 6, DIAMETER / 2 - 10);
        //画下方的环形数字
        drawRealNumber(gs, usedFontName, numCode, DIAMETER / 2 + 5, DIAMETER / 2 + 5, DIAMETER / 2);
        //签名
        if (StringUtils.isNotBlank(sign)) {
            drawSignName(gs, singFontName, sign);
        }
        //释放此图形的上下文以及它使用的所有系统资源
        gs.dispose();
        //输出图片
        try {
            StringBuffer fileDir = new StringBuffer();
            fileDir.append(Calendar.getInstance().get(Calendar.YEAR)).append(File.separator).append(FOLDER).append(File.separator);
            File sealFile = new File(filePath + fileDir.toString());
            if (!sealFile.exists()) {
                sealFile.mkdirs();
            }
            imgFile = filePath + fileDir.toString() + UUID.randomUUID() + ".png";
            ImageIO.write(bufImg, "png", new File(imgFile));
            bufImg.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgFile;
    }

    /**
     * 单独生成文字图片
     *
     * @param text
     * @param fontName
     * @return
     */
    public static String drawText(String filePath, String text, String fontName) {
        String imgFile = null;
        if (StringUtils.isNotBlank(text)) {
            int height = 60;
            int width = text.length() * height;
            //创建缓存
            BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //获得画布
            Graphics2D gs = bufImg.createGraphics();
            //设置背景透明
            bufImg = gs.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            gs.dispose();
            gs = bufImg.createGraphics();
            //设置颜色
            gs.setColor(Color.BLACK);
            //设置字体
            gs.setFont(new Font(findSystemFont(fontName), Font.PLAIN, 60));
            //设置锯齿圆滑
            gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gs.drawString(text, 0, 50);
            gs.dispose();
            //输出图片
            try {
                StringBuffer fileDir = new StringBuffer();
                fileDir.append(Calendar.getInstance().get(Calendar.YEAR)).append(File.separator).append(FOLDER).append(File.separator);
                File sealFile = new File(filePath + fileDir.toString());
                if (!sealFile.exists()) {
                    sealFile.mkdirs();
                }
                imgFile = filePath + fileDir.toString() + UUID.randomUUID() + ".png";
                ImageIO.write(bufImg, "png", new File(imgFile));
                bufImg.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgFile;
    }

    /**
     * 说明信息
     */
    private static void drawSealMessage(Graphics2D gs, String fontName, String message, int x, int y) {
        if (StringUtils.isNotBlank(message)) {
            gs.setFont(new Font(fontName, Font.PLAIN, 48));
            // 根据输入字符串得到字符数组
            String[] sourceMessages = message.split("", 0);
            String[] messages = new String[sourceMessages.length - 1];
            System.arraycopy(sourceMessages, 1, messages, 0, sourceMessages.length - 1);
            // 输入的字数
            int ilength = messages.length;
            Font f = gs.getFont();
            FontRenderContext context = gs.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(message, context);
            // 字符宽度＝字符串长度/字符数
            double char_interval = (bounds.getWidth() / ilength) - 6;            
            int first = 0, second = 0;
            boolean odd = false;
            if (ilength % 2 == 1) {
                first = (ilength - 1) / 2;
                odd = true;
            } else {
                first = (ilength) / 2 - 1;
                second = (ilength) / 2;
                odd = false;
            }
            if (odd) {
                gs.drawString(messages[first], (float) (x - char_interval / 2 - char_interval / 10), (float) y);
                // 中心点的右边
                for (int i = first + 1; i < ilength; i++) {
                    double x0 = x + (i - first - 0.6) * char_interval;
                    gs.drawString(messages[i], (float) x0, (float) y);
                }
                // 中心点的左边
                for (int i = first - 1; i > -1; i--) {
                    double x0 = x - (first - i + 0.6) * char_interval;
                    gs.drawString(messages[i], (float) x0, (float) y);
                }
            } else {
                // 中心点的右边
                for (int i = second; i < ilength; i++) {
                    double x0 = x + (i - second - 0.1) * char_interval;
                    gs.drawString(messages[i], (float) x0, (float) y);
                }
                // 中心点的左边
                for (int i = first; i > -1; i--) {
                    double x0 = x - (first + 1 - i + 0.1) * char_interval;
                    gs.drawString(messages[i], (float) x0, (float) y);
                }

            }
        }
    }

    /**
     * 画上方圆弧的汉字
     */
    private static void drawSealName(Graphics2D gs, String fontName, String message, int x, int y, int r) {
        if (StringUtils.isNotBlank(message)) {
            gs.setFont(new Font(fontName, Font.PLAIN, 59));
            int len = message.length();
            if (len > 10) {
                int fontSize = 61 - 3 * (len - 10);
                if (len >= 18)
                    fontSize = 43 - (len - 18);
                //设置字体
                gs.setFont(new Font(fontName, Font.PLAIN, fontSize));
            }
            // 根据输入字符串得到字符数组
            String[] sourceMessages = message.split("", 0);
            String[] messages = new String[sourceMessages.length - 1];
            System.arraycopy(sourceMessages, 1, messages, 0, sourceMessages.length - 1);
            // 输入的字数
            int ilength = messages.length;
            Font f = gs.getFont();
            FontRenderContext context = gs.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(message, context);
            // 字符宽度＝字符串长度/字符数
            double char_interval = (bounds.getWidth() / ilength) - 6;
            // 上坡度
            double ascent = -bounds.getY() - 2;
            int first = 0, second = 0;
            boolean odd = false;
            //字数奇偶数
            if (ilength % 2 == 1) {
                first = (ilength - 1) / 2;
                odd = true;
            } else {
                first = (ilength) / 2 - 1;
                second = (ilength) / 2;
                odd = false;
            }
            double r2 = r - ascent;
            double x0 = x;
            double y0 = y - r + ascent;
            // 旋转角度
            double a = 2 * Math.asin(char_interval / (2 * r2));

            if (odd) {
                gs.drawString(messages[first], (float) (x0 - char_interval / 2), (float) y0 + 6);
                // 中心点的右边
                for (int i = first + 1; i < ilength; i++) {
                    double aa = (i - first) * a;
                    double ax = r2 * Math.sin(aa);
                    double ay = r2 - r2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(aa);
                    Font f2 = f.deriveFont(transform);
                    gs.setFont(f2);
                    gs.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa) - 4), (float) (y0 + ay - char_interval / 2 * Math.sin(aa) + 6));
                }
                // 中心点的左边
                for (int i = first - 1; i > -1; i--) {
                    double aa = (first - i) * a;
                    double ax = r2 * Math.sin(aa);
                    double ay = r2 - r2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(-aa);
                    Font f2 = f.deriveFont(transform);
                    gs.setFont(f2);
                    gs.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa) + 4), (float) (y0 + ay + char_interval / 2 * Math.sin(aa) + 6));
                }
            } else {
                // 中心点的右边
                for (int i = second; i < ilength; i++) {
                    double aa = (i - second + 0.5) * a;
                    double ax = r2 * Math.sin(aa);
                    double ay = r2 - r2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(aa);
                    Font f2 = f.deriveFont(transform);
                    gs.setFont(f2);
                    gs.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa) - 4), (float) (y0 + ay - char_interval / 2 * Math.sin(aa) + 6));
                }
                // 中心点的左边
                for (int i = first; i > -1; i--) {
                    double aa = (first - i + 0.6) * a;
                    double ax = r2 * Math.sin(aa);
                    double ay = r2 - r2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(-aa);
                    Font f2 = f.deriveFont(transform);
                    gs.setFont(f2);
                    gs.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa) + 4), (float) (y0 + ay + char_interval / 2 * Math.sin(aa) + 6));
                }
            }
        }
    }

    /**
     * 画下方圆弧数字编码
     */
    private static void drawRealNumber(Graphics2D gs, String fontName, String message, int x, int y, int r) {
        if (StringUtils.isNotBlank(message)) {
            gs.setFont(new Font(fontName, Font.PLAIN, 9));
            // 根据输入字符串得到字符数组
            String[] sourceMessages = message.split("", 0);
            String[] messages = new String[sourceMessages.length - 1];
            System.arraycopy(sourceMessages, 1, messages, 0, sourceMessages.length - 1);
            // 输入的字数
            int ilength = messages.length;
            Font f = gs.getFont();
            FontRenderContext context = gs.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(message, context);
            // 字符宽度＝字符串长度/字符数
            double char_interval = (bounds.getWidth() / ilength) + 4;
            // 上坡度
            double ascent = -bounds.getY();
            int first = 0, second = 0;
            boolean odd = false;
            if (ilength % 2 == 1) {
                first = (ilength - 1) / 2;
                odd = true;
            } else {
                first = (ilength) / 2 - 1;
                second = (ilength) / 2;
                odd = false;
            }
            double r2 = r - ascent;
            double x0 = x;
            double y0 = y - r + ascent;
            // 旋转角度
            double a = 2 * Math.asin(char_interval / (2 * r2));
            if (odd) {
                // 中心点的右边
                for (int i = first + 1; i < ilength; i++) {
                    double aa = (i - first + 0.5) * a;
                    double ax = r2 * Math.sin(aa);
                    double ay = r2 - r2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(2 * Math.PI - aa);
                    Font f2 = f.deriveFont(transform);
                    gs.setFont(f2);
                    gs.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)), 2 * y - (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
                }
                // 中心点的左边
                for (int i = first; i > -1; i--) {
                    double aa = (first - i - 0.5) * a;
                    double ax = r2 * Math.sin(aa);
                    double ay = r2 - r2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(2 * Math.PI + aa);
                    Font f2 = f.deriveFont(transform);
                    gs.setFont(f2);
                    gs.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)), 2 * y - (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
                }
            } else {
                // 中心点的右边
                for (int i = second; i < ilength; i++) {
                    double aa = (i - second + 1) * a;
                    double ax = r2 * Math.sin(aa);
                    double ay = r2 - r2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(2 * Math.PI - aa);
                    Font f2 = f.deriveFont(transform);
                    gs.setFont(f2);
                    gs.drawString(messages[i], (float) (x0 + ax - char_interval / 2 * Math.cos(aa)), 2 * y - (float) (y0 + ay - char_interval / 2 * Math.sin(aa)));
                }
                // 中心点的左边
                for (int i = first; i > -1; i--) {
                    double aa = (first - i) * a;
                    double ax = r2 * Math.sin(aa);
                    double ay = r2 - r2 * Math.cos(aa);
                    AffineTransform transform = AffineTransform.getRotateInstance(2 * Math.PI + aa);
                    Font f2 = f.deriveFont(transform);
                    gs.setFont(f2);
                    gs.drawString(messages[i], (float) (x0 - ax - char_interval / 2 * Math.cos(aa)), 2 * y - (float) (y0 + ay + char_interval / 2 * Math.sin(aa)));
                }
            }
        }
    }

    /**
     * 画章中间的五角星
     */
    private static void drawFiveStar(Graphics2D g, int x, int y, int r) {
        double ch = 72 * Math.PI / 180;
        int x1 = x,
                x2 = (int) (x - Math.sin(ch) * r),
                x3 = (int) (x + Math.sin(ch) * r),
                x4 = (int) (x - Math.sin(ch / 2) * r),
                x5 = (int) (x + Math.sin(ch / 2) * r);
        int y1 = y - r,
                y2 = (int) (y - Math.cos(ch) * r),
                y3 = y2,
                y4 = (int) (y + Math.cos(ch / 2) * r),
                y5 = y4;
        int bx = (int) (x + Math.cos(ch) * Math.tan(ch / 2) * r);
        int by = y2;

        Polygon a = new Polygon();
        Polygon b = new Polygon();
        a.addPoint(x2, y2);
        a.addPoint(x5, y5);
        a.addPoint(bx, by);
        b.addPoint(x1, y1);
        b.addPoint(bx, by);
        b.addPoint(x3, y3);
        b.addPoint(x4, y4);
        g.fillPolygon(a);
        g.fillPolygon(b);
    }

    /**
     * 签章上添加签名
     *
     * @param g
     * @param fontName
     * @param sign
     */
    private static void drawSignName(Graphics2D g, String fontName, String sign) {
        if (StringUtils.isNotBlank(sign)) {
            //设置颜色
            g.setColor(Color.BLACK);
            //设置字体
            g.setFont(new Font(findSystemFont(fontName), Font.BOLD, 62));
            g.drawString(sign, DIAMETER * 1 / 4, DIAMETER * 3 / 4);
        }
    }

    /**
     * 查询字体
     *
     * @param fontName
     * @return
     */
    private static String findSystemFont(String fontName) {
        //查看系统字体
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontList = ge.getAvailableFontFamilyNames();
        //默认字体
        String usedFont = fontList[fontList.length - 1];
        //是否支持选择字体
        if (StringUtils.isNotBlank(fontName)) {
            for (String font : fontList) {
                if (StringUtils.equals(font, fontName)) {
                    usedFont = fontName;
                    break;
                }
            }
        }
        return usedFont;
    }

    public static void main(String[] args) {
        try {
            String path = drawCircularSeal("D:/", "宋体", "公告签章", "必联北京电子商务科技有限公司公司公司公司", null, null, null);
            System.out.print(path);
            drawText("d:/", "李劼榕", "仿宋");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
