package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte bt = 127;
        short sh = (short) 32767;
        int num = 214;
        long lng = 2147483649L;
        float fl = 50.0F;
        double db = 7.62;
        boolean torl = true;
        char ch = 67;
        LOG.debug(
                "byte : {}, short : {}, int : {}, long : {}, float : {}, double : {}, boolean : {}, char : {}",
                bt, sh, num, lng, fl, db, torl, ch);
    }
}