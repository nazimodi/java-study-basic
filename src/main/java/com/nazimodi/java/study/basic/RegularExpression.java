package com.nazimodi.java.study.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * regular expression
 * @author renqingwang on 2017/8/13.
 * @version 1.0
 */
public class RegularExpression {
    private static final String IP_ADDRESS_REGEX = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
            "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
            "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
            "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

    public static boolean checkIpAddress(String ipAddress) {
        return ipAddress.matches(IP_ADDRESS_REGEX);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        System.out.print("please input ip address:\n");
        while (!"over".equals(input = bufferedReader.readLine())) {
            System.out.print(input + " is valid ip address:" + checkIpAddress(input) + "\n");
        }
        bufferedReader.close();
    }
}
