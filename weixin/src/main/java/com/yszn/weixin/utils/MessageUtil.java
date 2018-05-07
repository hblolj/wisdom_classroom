package com.yszn.weixin.utils;

import com.thoughtworks.xstream.XStream;
import com.yszn.weixin.dto.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hblolj
 * @Date: 2018/5/2 17:09
 * @Description: 微信消息工具类
 * @Version: 1.0
 **/
public class MessageUtil {

    /**
     * 将xml转为map集合
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        //从request中获取输入流
        InputStream ins = request.getInputStream();
        //读取输入流
        Document doc = reader.read(ins);
        //获取xml的根元素
        Element root = doc.getRootElement();
        //根元素的所有节点放入list集合中
        List<Element> list = root.elements();
        for(Element e:list){
            map.put(e.getName(),e.getText());
        }
        ins.close();
        return map;
    }

    /**
     * 将text消息转为xml类型
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 关注后弹出的消息
     * @return
     */
    public static String menuText(){
        // TODO: 2018/5/7 抽取成配置内容
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照菜单提示进行操作：\n");
        sb.append("1、i课教室介绍\n");
        sb.append("2、i课系统入口\n");
        sb.append("3、更多功能，请回复：“？”了解");
        return sb.toString();
    }

    public static String initText(String toUserName,String fromUserName,String content){
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType("text");
        text.setCreateTime(System.currentTimeMillis());
        text.setContent(content);
        return MessageUtil.textMessageToXml(text);
    }

    public static String firstMene(){
        StringBuffer sb = new StringBuffer();
        sb.append("i课教室是智能教室的一种体现。");
        return sb.toString();
    }

    public static String secondMene(String ip){
        StringBuffer sb = new StringBuffer();
        sb.append(ip + "/html/home.html");
        return sb.toString();
    }
}
