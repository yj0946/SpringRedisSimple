package com.spring.redis.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        // 其中s是输入的原数据类型
        //将日期字符串转换成日期对象 返回
        String pattern = "yyyy-MM-dd";
        if (s.contains("-")) {
            pattern = "yyyy-MM-dd";
        } else if (s.contains("/")) {
            pattern = "yyyy/MM/dd";
        } else if (s.contains("年") && s.contains("月") && s.contains("日")) {
            pattern = "yyyy年MM月dd日";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
