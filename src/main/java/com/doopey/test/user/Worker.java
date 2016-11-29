package com.doopey.test.user;

import com.xiaomi.data.recommend.common.model.User;
import com.xiaomi.data.recommend.user.AppName;
import com.xiaomi.data.spec.platform.profile.Common;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fc on 16-11-15.
 */
public class Worker {

    private static Map<String, Object> userProfile = new HashMap<String, Object>();

    private static void initUserProfileSet() {

    }

    public static void main(String[] args) {
        System.out.println("--------------------start worker------------------");
        UserBiz biz = new UserBiz();
        System.out.println(biz.getUser("660485b871c32bdd6cda1855e1b4cc84", new ArrayList<AppName>()));
//        BufferedReader reader = null;
//        BufferedWriter bw = null;
//        try {
//            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("user_features_output"))));
//            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/home/work/tmp/samples"))));
//            String line = "";
//            int cnt = 0;
//            while ((line = reader.readLine()) != null) {
//                if (line.length() != 32) {
//                    continue;
//                }
//                String content = line;
//                User user = biz.getUser(line, new ArrayList<AppName>());
//                if (user != null) {
//                    Common common = user.getRecUserInfo().getCommon();
//                    if (common != null) {
//                        content = line + "\t" + common.getUser_sex() + "\t" + common.getUser_age() + "\t" + common.getResident_city() + "\t" + common.getPhone_brand();
//                    }
//                }
//                bw.write(content + "\n");
//                if (cnt ++ % 1000 == 0) {
//                    System.out.println(cnt + " is done");
//                    Thread.sleep(1000);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                bw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
