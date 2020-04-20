package com.blog.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对时间进行处理
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    private  String format;
    public DateJsonValueProcessor(String format){
        this.format =  format;
    }
    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if(value==null){
            return "";
        }
        //如果是时间戳则转化
        if(value instanceof Timestamp){
            String str = new SimpleDateFormat(this.format).format((Timestamp)value);
            return str;
        }
        //如果是日期则转化
        if(value instanceof Date){
            String str = new SimpleDateFormat(this.format).format((Date)value);
            return str;
        }
        return value.toString();
    }
}
