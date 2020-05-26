package com.ubtech.base_lib.utils;

import android.util.Log;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.ubtrobot.log.ALog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by albert.wu on 2018/11/20.
 */

public class XmlUtils {

    private static final String TAG = "XmlUtils";

    /**
     * XStream解析xml 反序列化成对象
     * @param xmlPath xml文件路径
     * @param clazz 使用注解的类, 如根类对应的是使用的是注释的方式, 则其包含的子类不需要指定，否则需要指定子类
     * @return
     */
    public static Object parse(String xmlPath, Class... clazz) {
        File xmlFile = new File(xmlPath);
        if(xmlFile.exists()) {
            XStream xStream = new XStream(new FieldDefaultProvider());
            xStream.ignoreUnknownElements();
//            xStream.autodetectAnnotations(true);
            xStream.processAnnotations(clazz);
            try {
                Object result = xStream.fromXML(xmlFile);
                return result;
            }catch (Exception e){
                e.printStackTrace();
                ALog.tag("XmlUtils").e("xml parse exception msg: %s", e.getMessage());
            }
        }
        return null;
    }

    public static void saveXml(Object data, String savePath, Class... childClazz) {
        Log.d(TAG, "saveXml");
        File xmlFile = new File(savePath);
        File dir = xmlFile.getParentFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(xmlFile)){
            XStream xStream = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
//            xStream.autodetectAnnotations(true);
            xStream.processAnnotations(childClazz);
            xStream.setMode(XStream.NO_REFERENCES);
            xStream.toXML(data, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
