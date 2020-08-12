package com.axe.trace.sys.util;

/**
 * 二维码测试类
 */
public class QRCodeTest {
    public static void main(String[] args) throws Exception {
        String text = "http://112.124.22.250:8081/trace/sourceArea/getById?id=2";

        String imgPath = QRCodeTest.class.getClassLoader().getResource("dbnp.jpg").getPath();

        String destPath = "C:\\Users\\Administrator\\Desktop\\qrcode.jpg";

        QRCodeUtil.encode(text, imgPath, destPath, true);

        String str = QRCodeUtil.decode(destPath);
        System.out.println(str);
    }
}
