package com.nazimodi.java.study.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author renqingwang on 2017/8/13.
 * @version 1.0
 */
public class CharsetExchange {
    public static void inputDataByCharset() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String data = null;
        System.out.print("please input(close by over):");
        while (!"over".equals(data = bufferedReader.readLine())) {
            stringToHex(data, "utf8");
            stringToHex(data, "GBK");
            stringToHex(data, "gb2312");
        }
        bufferedReader.close();
    }

    public static void main(String[] args) throws Exception {
        inputDataByCharset();
    }

    private static void stringToHex(String data, String charset) throws Exception {
        System.out.print(charset + " time:\n");
        byte[] bytes = data.getBytes(charset);
        StringBuilder byteStr = new StringBuilder("byte:");
        StringBuilder hexStr = new StringBuilder("hex:");
        StringBuilder codeStr = new StringBuilder("区位code:");
        for (byte b : bytes) {
            byteStr.append(b).append(" ");
            hexStr.append(Integer.toHexString(b & 0xff)).append(" ");
            //获取区位码
            codeStr.append(Integer.parseInt(Integer.toHexString(b & 0xff), 16) - 0x80 - 0x20).append(" ");
        }
        System.out.println(byteStr.toString() + "\n" + hexStr.toString() + "\n" + codeStr + "\n");
    }
}
