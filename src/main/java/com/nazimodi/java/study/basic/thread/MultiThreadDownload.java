package com.nazimodi.java.study.basic.thread;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.concurrent.CountDownLatch;

/**
 * @author renqingwang on 2017/8/21.
 * @version 1.0
 */
public class MultiThreadDownload {
    public static class DownLoadThread implements Runnable {
        private CountDownLatch countDownLatch;
        private Integer threadId;
        private String url;
        private String dest;
        private long startPos;
        private long endPos;

        public DownLoadThread(int threadId, String url, String dest, long startPos, long endPos, CountDownLatch countDownLatch) {
            this.threadId = threadId;
            this.url = url;
            this.dest = dest;
            this.startPos = startPos;
            this.endPos = endPos;
            this.countDownLatch = countDownLatch;
        }

        private HttpURLConnection getHttpConnection() throws Exception {
            URL urlConn = null;
            if (this.url != null) {
                urlConn = new URL(this.url);
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConn.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");

            return httpURLConnection;
        }

        @Override
        public void run() {
            try {
                HttpURLConnection httpURLConnection = getHttpConnection();
                httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
                httpURLConnection.connect();
                RandomAccessFile randomAccessFile = new RandomAccessFile(new File(dest), "rw");
                randomAccessFile.seek(startPos);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                System.out.println("线程" + this.threadId + "期望长度:" + (endPos - startPos));
                byte[] bytes = new byte[2048];
                int length = 0;
             /*   int totalLen = (int) (endPos - startPos);*/
                while ((length = bufferedInputStream.read(bytes)) != -1) {
                  /*  totalLen -= 2048;
                    if (totalLen <= 0) {
                        break;
                    }*/
                    randomAccessFile.write(bytes, 0, length);
                }
                randomAccessFile.close();
                httpURLConnection.disconnect();
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int threadNum = 4;
        String dest = "e:\\test123.exe";
        String resourceUrl = "http://sw.bos.baidu.com/sw-search-sp/software/1f23f7071f7c1/QQ_8.9.4.21603_setup.exe";
        URL url = new URL(resourceUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        long fileLength = -1;
        int stateFlagCode = httpURLConnection.getResponseCode();
        if (stateFlagCode == 200) {
            fileLength = httpURLConnection.getContentLength();
            httpURLConnection.disconnect();
        }
        if (fileLength > 0) {
            long currentTime = System.currentTimeMillis();
            CountDownLatch countDownLatch = new CountDownLatch(threadNum);
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(dest), "rw");
            randomAccessFile.setLength(fileLength);
            randomAccessFile.close();
            long byteCounts = fileLength / threadNum;
            int count = 0;
            long startPos = 0, endPos = 0;
            while (count < threadNum) {
                startPos = count * byteCounts;
                endPos = (count + 1) * byteCounts - 1;
                if (count == threadNum - 1) {
                    endPos = fileLength;
                }

                new Thread(new DownLoadThread(count, resourceUrl, dest, startPos, endPos, countDownLatch)).start();

                count++;
            }
            countDownLatch.await();
            currentTime = System.currentTimeMillis() - currentTime;
            System.out.println("总耗时:" + currentTime / 1000 + "s");
            System.out.println("下载资源大小:" + fileLength / Math.pow(1024, 2) + "M, 速度:" + fileLength / Math.pow(1024, 3) / currentTime + "m/s");
        }

    }
}
