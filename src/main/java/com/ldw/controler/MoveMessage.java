package com.ldw.controler;

import net.sf.json.JSONObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/26.
 */

@ServerEndpoint("/movemessage/{param}")
public class MoveMessage {
   public static Set<Session> set = new HashSet<Session>();

    @OnOpen
    public void onopen(@PathParam(value = "param") String username, Session session) throws IOException {
        System.out.println("lianjiezhong");
        for (Session session1:
                set) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jsonObject.put("username",username);
            jsonObject.put("content","加入群聊天室");
            session1.getBasicRemote().sendText(jsonObject.toString());
        }
        set.add(session);
    }

    @OnMessage
    public void onsend(Session session,String msg) throws IOException {
        JSONObject jsonObject=JSONObject.fromObject(msg);
        jsonObject.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        for (Session session1:set ) {
            session1.getBasicRemote().sendText(jsonObject.toString());
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
       set.remove(session);
        for (Session session1:
             set) {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            jsonObject.put("content","退出聊天室");
            session1.getBasicRemote().sendText(jsonObject.toString());
        }
    }
}
