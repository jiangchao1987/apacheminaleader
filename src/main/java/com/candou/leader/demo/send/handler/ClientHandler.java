package com.candou.leader.demo.send.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.candou.leader.demo.send.pojo.MsgBody;

/**
 * Note: Client业务逻辑处理层
 * Author: JiangChao
 * Date: 5/31/13
 * Email: jiangchao1987@gmail.com
 */
public class ClientHandler extends IoHandlerAdapter {
    
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened: 客户端打开连接!");
        MsgBody msg = new MsgBody(1, "jiangchao");
        session.write(msg);
    }

    @Override
    public void sessionClosed(IoSession session) {
        System.out.println("sessionClosed: 客户端关闭连接!");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        MsgBody msg = (MsgBody) message;
        System.out.println("messageReceived: " + msg.getName());
    }
}
