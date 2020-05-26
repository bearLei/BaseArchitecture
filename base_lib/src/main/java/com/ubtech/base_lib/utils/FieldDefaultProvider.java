package com.ubtech.base_lib.utils;

import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.core.util.Fields;

import java.lang.reflect.Field;

/**
 * Created by albert.wu on 2019/1/28.
 * 用于XStream解析时，去除xml中不存在的值赋值为null的逻辑
 */

public class FieldDefaultProvider extends PureJavaReflectionProvider {

    @Override
    public void writeField(Object object, String fieldName, Object value, Class definedIn) {
        super.writeField(object, fieldName, value, definedIn);
        Field field = fieldDictionary.field(object.getClass(), fieldName, definedIn);
        validateFieldAccess(field);
        if(value instanceof String) {
            String trim = ((String)value).trim();
            if(trim.length() > 0) {
                Fields.write(field, object, trim);
            }
        }else {
            Fields.write(field, object, value);
        }
    }
}
