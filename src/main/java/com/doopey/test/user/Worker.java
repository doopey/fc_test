package com.doopey.test.user;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fc on 16-11-15.
 */
public class Worker {

    private static Map<String, Object> userProfile = new HashMap<String, Object>();
    private static Logger logger = LoggerFactory.getLogger(Worker.class);

    public static boolean loadModel(String modelPath) {
        Configuration conf = new Configuration();
//        conf.addDefaultResource("core-site.xml");
//        conf.addDefaultResource("hdfs-site.xml");
        Path path = new Path(modelPath);
        FileSystem fs = null;
        try {
            fs = path.getFileSystem(conf);
            FileStatus[] listStatus = fs.listStatus(path);
            if (listStatus == null || listStatus.length == 0) {
                logger.error("model path is dir, but hdfs patition path is null");
                return false;
            }
            logger.info("list file status successfully");
            return true;
        } catch (IOException e) {
            logger.error("failed to read hdfs", e);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("--------------------start worker------------------");
        if (loadModel("/user/h_o2o/reco/hot_items")) {
            System.out.println("load model successfully");
        } else {
            System.out.println("failed to load model");
        }
        System.out.println("--------------------stop worker------------------");
    }

}
