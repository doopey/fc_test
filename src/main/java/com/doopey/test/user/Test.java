package com.doopey.test.user;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by fangchen on 16-11-21.
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        System.out.println(test());

    }

    public static int test() {
        int x = 1;
        try {
            return x++;
        } finally {
            ++x;
        }
    }
}
