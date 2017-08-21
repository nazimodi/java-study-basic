package com.nazimodi.java.study.basic.thread;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

/**
 * @author renqingwang on 2017/8/21.
 * @version 1.0
 */
public class MultiThreadDownload {
    public static class DownLoadThread implements Runnable{
        private HttpURLConnection url;
        private File dest;
        private long startPos;
        private long endPos;
        public DownLoadThread(HttpURLConnection url, File dest, long startPos, long endPos) {
            this.url = url;
            this.dest = dest;
            this.startPos = startPos;
            this.endPos = endPos;
        }
        @Override
        public void run(){
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(dest, "rw");
                randomAccessFile.seek(startPos);
                InputStream inputStream = url.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                byte[] bytes = new byte[2048];
                int length = 0;
                int totalLen = (int)(endPos - startPos);
                while ((length = bufferedInputStream.read(bytes, 0, totalLen - 2048 < 0 ? totalLen : 2048)) != -1) {
                    totalLen -= 2048;
                    randomAccessFile.write(bytes, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        int threadNum = 10;
        String dest = "e:\\test123.exe";
        String resourceUrl = "http://sw.bos.baidu.com/sw-search-sp/software/1f23f7071f7c1/QQ_8.9.4.21603_setup.exe";
        URL url = new URL(resourceUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        long fileLength = -1;
        int stateFlagCode = httpURLConnection.getResponseCode();
        if (stateFlagCode == 200) {
            fileLength = httpURLConnection.getContentLength();
            //httpURLConnection.disconnect();
        }
        if (fileLength > 0) {
            long byteCounts = fileLength / threadNum + 1;
            File file = new File(dest);
            int count = 0;
            while (count < threadNum) {
                long startPos = count * byteCounts;
                long endPos = (count + 1) * byteCounts;
                if (count == threadNum - 1) {
                    new Thread(new DownLoadThread(httpURLConnection, file, startPos, fileLength)).start();
                } else {
                    new Thread(new DownLoadThread(httpURLConnection, file, startPos, endPos)).start();
                }
                count++;
            }
        }
    }
}
