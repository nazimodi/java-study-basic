package com.nazimodi.java.study.basic.IO;

import java.io.*;

/**
 * @author renqingwang on 2017/8/20.
 * @version 1.0
 */
public class FileCopy {
    public static void fileCopyByCharacter(String fileName) throws Exception {
        Long currentTime = System.nanoTime();
        char[] chars = new char[1024];
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(fileName);
            fileWriter = new FileWriter(fileName + "bychar.pdf");

            int i = 0;
            while ((i = fileReader.read(chars)) != -1) {
                fileWriter.write(chars, 0, i);
            }
            fileWriter.flush();
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (null != fileWriter) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("bu text copy " + fileName + " consume:" + (System.nanoTime() - currentTime) / 1000 / 1000 + "ms");
    }

    public static void fileCopyByByte(String fileName) {
        long currentTime = System.nanoTime();
        byte[] bytes = new byte[1024];
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(fileName));
            fileOutputStream = new FileOutputStream(new File(fileName + "bybyte.pdf"));
            int length = 0;
            while ((length = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.flush();
            fileInputStream.close();
        } catch (FileNotFoundException fileNotFound) {
            fileNotFound.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("by binary copy " + fileName + " consume:" + (System.nanoTime() - currentTime) / 1000 / 1000 + "ms");
    }

    public static void bufferedTextCopy(String fileName) {
        long currentTime = System.nanoTime();
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            bufferedWriter = new BufferedWriter(new FileWriter(fileName + "bufferText"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bufferedWriter) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("buffered text copy " + fileName + " consume: " + (System.nanoTime() - currentTime) / 1000 / 1000 + "ms");
    }

    public static void bufferedBinaryCopy(String fileName) {
        long currentTime = System.nanoTime();
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName + "bufferText"));
            byte[] chars = new byte[1024];
            while (bufferedInputStream.read(chars) != -1) {
                bufferedOutputStream.write(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedOutputStream) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bufferedInputStream) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("buffered binary copy " + fileName + " consume: " + (System.nanoTime() - currentTime) / 1000 / 1000 + "ms");
    }

    public static void main(String[] args) {
        String filePdf = "E:\\study2017\\2017年学习资料\\ALI\\Java性能权威指南.pdf";
        String fileText = "E:\\study2017\\2017年学习资料\\ALI\\题目-我的理解.txt";
        String fileImg = "E:\\study2017\\2017年学习资料\\ALI\\一次面试提.jpg";
        try {
            fileCopyByCharacter(filePdf);
            fileCopyByByte(filePdf);
            bufferedTextCopy(filePdf);
            bufferedBinaryCopy(filePdf);

            fileCopyByCharacter(fileText);
            fileCopyByByte(fileText);
            bufferedTextCopy(fileText);
            bufferedBinaryCopy(fileText);

            fileCopyByCharacter(fileImg);
            fileCopyByByte(fileImg);
            bufferedTextCopy(fileImg);
            bufferedBinaryCopy(fileImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
