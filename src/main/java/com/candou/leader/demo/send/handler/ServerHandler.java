package com.candou.leader.demo.send.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.candou.leader.demo.send.pojo.MsgBody;

/**
 * Note: Server业务逻辑处理层
 * Author: JiangChao
 * Date: 5/31/13
 * Email: jiangchao1987@gmail.com
 */
public class ServerHandler extends IoHandlerAdapter {
    private static int count = 1;
    private Logger logger = Logger.getLogger(ServerHandler.class);

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.info("sessionCreated: 新客户端连接!");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("sessionOpened: 第 " + count++ + " 个 client 登陆! address： : "
                + session.getRemoteAddress());
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("sessionClosed: 第 " + (count - 1) + " 个 client 关闭! address： : "
                + session.getRemoteAddress());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info("sessionIdle: 连接空闲!");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.info("exceptionCaught: 其他方法抛出异常!");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        MsgBody msg = (MsgBody) message;
        logger.info("messageReceived: " + msg.getName());
 
        msg.setName("serverMsg");
        session.write(msg);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("messageSent: 信息已经传送给客户端");
    }
}
