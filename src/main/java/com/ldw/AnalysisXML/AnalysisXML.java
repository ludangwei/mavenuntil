package com.ldw.AnalysisXML;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.applet.Main;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public class AnalysisXML {

    /**
     * 解析xml
     *
     * @param filePath
     */
    public void analysisXML(String filePath){
        InputStream resourceAsStream = getClass().getResourceAsStream(filePath);
        SAXReader reader=new SAXReader();
        try {
            Document doc = reader.read(resourceAsStream);
            Element rootElement = doc.getRootElement();
            getChildElement(rootElement);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    /**
     * 轮询当前节点的所有子节点
     *
     * @param element
     */
    public void getChildElement(Element element){
        List elementList = element.elements();
        for (int i = 0; i <elementList.size() ; i++) {
            Element element1 = (Element) elementList.get(i);
            String name = element1.getName();
            String id = element1.attribute("id")==null?"":element1.attribute("id").getValue();
            String text = element1.getText();
            System.out.println("这是第"+i+"层节点and 节点名称为："+name+"   节点id："+id+"节点文本："+text);
            getChildElement(element1);
        }
    }
    

    public static void main(String[] args) {
        new  AnalysisXML().analysisXML("practise.xml");
    }
}
