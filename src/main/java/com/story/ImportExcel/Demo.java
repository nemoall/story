package com.story.ImportExcel;

import org.apache.poi.ss.usermodel.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * blingbling ✨
 */
public class Demo {
    public static void main(String[] args) throws ParseException {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(format1.parse("2019-05-06").after(format1.parse("2019-05-07")));

    }
}
