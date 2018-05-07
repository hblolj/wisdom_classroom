package com.yszn.weixin.controller;

import com.yszn.security.core.constant.WeiXinConstants;
import com.yszn.security.core.properties.base.WeiXinProperties;
import com.yszn.weixin.dto.TextMessage;
import com.yszn.weixin.utils.CheckUtil;
import com.yszn.weixin.utils.MessageUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static com.yszn.weixin.constants.WeiXinMessageConstants.*;

/**
 * @author: hblolj
 * @Date: 2018/5/2 16:51
 * @Description: 微信校验与交互控制器
 * @Version: 1.0
 **/
@RestController
public class WeiXinMessageMutual {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WeiXinProperties weiXinProperties;

    @GetMapping("/wx.do")
    public void validate(HttpServletRequest req, HttpServletResponse resp, @RequestParam String signature,
                         @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr){
        logger.info("validate weixin from wx.do get");
        PrintWriter out;
        try {
            out = resp.getWriter();
            // TODO: 2018/5/2 使用 WeiXinProperties 来配置
            String token = "";
            if(CheckUtil.checkSignature(signature,timestamp,nonce, token)){
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/wx.do")
    public void mutual(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String serverName = req.getServletContext().getContextPath();
        PrintWriter out = resp.getWriter();

        try {
            //接收用户发送的消息
            Map<String,String> map = MessageUtil.xmlToMap(req);
            String fromUserName = map.get(FROM_USER_NAME);
            String toUserName = map.get(TO_USER_NAME);
            String msgType = map.get(MSG_TYPE);
            String content = map.get(CONTENT);
            String message = null;
            //判断用户发送的消息类型，然后发送一条消息返回用户发送的内容
            if (MESSAGE_TYPE_TEXT.equals(msgType)){
                if(FIRST_MESSAGE.equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMene());
                }else if(SECOND_MESSAGE.equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName,
                            MessageUtil.secondMene(weiXinProperties.getCallBackServerAddress()));
                }else if(EN_QUESTION_MARK_MESSAGE.equals(content) ||
                        CN_QUESTION_MARK_MESSAGE.equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }else{
                    TextMessage text = new TextMessage();
                    //设置发送方账号(一个openID)
                    text.setFromUserName(toUserName);
                    //设置接收方账号
                    text.setToUserName(fromUserName);
                    //设置消息类型
                    text.setMsgType(MESSAGE_TYPE_TEXT);
                    //消息创建时间
                    text.setCreateTime(System.currentTimeMillis());
                    text.setContent("你发送的消息是: " + content);
                    message = MessageUtil.textMessageToXml(text);
                }
            }else if (MESSAGE_TYPE_EVENT.equals(msgType)){
                String eventType = map.get("Event");
                if(MESSAGE_EVENT_SUBSCRIBE.equals(eventType)){
                    // 将新关注的用户插入数据库,判断是否已经存在，如果已经存在，将用户状态置为激活
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                    logger.info(fromUserName + "关注了本公众号");
                }else if (MESSAGE_EVENT_UNSUBSCRIBE.equals(eventType)){
                    logger.info(fromUserName + "取关了本公众号");
                } else if(MESSAGE_EVENT_VIEW.equals(eventType)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
            }
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }

    }
}
