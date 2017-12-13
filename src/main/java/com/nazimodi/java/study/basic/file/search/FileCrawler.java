package com.nazimodi.java.study.basic.file.search;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author renqingwang on 2017/12/10.
 * @version 1.0
 */
public class FileCrawler implements Runnable {
    private final BlockingDeque<File> fileQueue;
    private final FileFilter fileFilter;
    private final File file;

    public FileCrawler(BlockingDeque<File> fileQueue, FileFilter fileFilter, File file) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.file = file;
    }

    @Override
    public void run() {

    }

    private void crawl(File root) {
        Executors.newFixedThreadPool(1, Executors.defaultThreadFactory());
        File[] entries = root.listFiles();
        if (entries != null) {
            for(File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                }
            }
        }
    }
}
