package com.ldw.talkFrame;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/19.
 */
@ServerEndpoint("/hello")
public class talkFrame {
    @OnOpen
    public void onopen(Session session){
        System.out.println("连接成功");
        try {
            session.getBasicRemote().sendText("hello client...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnClose
    public void onclose(Session session){
        System.out.println("close....");
    }
    @OnMessage
    public void onsend(Session session,String msg){
        try {
            session.getBasicRemote().sendText("client"+session.getId()+"say:"+msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
