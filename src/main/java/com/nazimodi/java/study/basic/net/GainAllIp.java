package com.nazimodi.java.study.basic.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author renqingwang on 2017/8/21.
 * @version 1.0
 */
public class GainAllIp {
    private static Map<String, Boolean> ipMap = new HashMap<>();

    public static void main(String[] args) {
        try {
            PingIpThread[] pingIpThreads = new PingIpThread[255];
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostAddress = inetAddress.getHostAddress();
            hostAddress = "192.168.2.1";
            int pos = hostAddress.lastIndexOf(".");
            String wd = hostAddress.substring(0, pos + 1);
            for (int i = 1; i <= 255; i++) {
                String ip = wd + i;
                pingIpThreads[i - 1] = new PingIpThread(ip);
                pingIpThreads[i - 1].start();
            }
            for (int i = 1; i <= 255; i++) {
                pingIpThreads[i - 1].join();
            }
            Iterator<String> ipSet = ipMap.keySet().iterator();
            StringBuilder stringBuilder = new StringBuilder("发现的ip如下 by join:\n");
            while (ipSet.hasNext()) {
                stringBuilder.append(ipSet.next()).append("\n");
            }
            System.out.println(stringBuilder.toString());

            ipMap = new HashMap<>();
            CountDownLatch countDownLatch = new CountDownLatch(255);
            for (int i = 1; i <= 255; i++) {
                pingIpThreads[i - 1] = new PingIpThread(wd + i, countDownLatch);
                pingIpThreads[i - 1].start();
            }
            countDownLatch.await();
            ipSet = ipMap.keySet().iterator();
            stringBuilder = new StringBuilder("发现的ip如下 by count down latch:\n");
            while (ipSet.hasNext()) {
                stringBuilder.append(ipSet.next()).append("\n");
            }
            System.out.println(stringBuilder.toString());
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static class PingIpThread extends Thread {
        private CountDownLatch countDownLatch;

        private String ip;

        public PingIpThread(String ip, CountDownLatch countDownLatch) {
            this.ip = ip;
            this.countDownLatch = countDownLatch;
        }

        public PingIpThread(String ip) {
            this.ip = ip;
        }

        @Override
        public void run() {
            try {
                Process process = Runtime.getRuntime().exec("ping " + ip + " -w 280 -n 1");
                InputStream inputStream = process.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = bufferedReader.readLine()) != null /*&& !"".equals(line)*/) {
                    if (!"".equals(line)
                            && (line.substring(0, 2).equals("来自")
                            || (line.length() > 10 && line.substring(0, 10).equals("Reply from")))) {
                        ipMap.put(ip, true);
                        System.out.println("what's up");
                    }
                }
                if (this.countDownLatch != null) {
                    this.countDownLatch.countDown();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
