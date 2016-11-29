package com.doopey.test.user;

import com.xiaomi.data.recommend.common.model.RankerItem;

import java.util.*;

/**
 * Created by doopey on 16-11-21.
 */
public class Test {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i ++) {
            list.add(i);
        }

        Collections.shuffle(list);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
