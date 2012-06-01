package com.candou.leader.demo.chat.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.candou.leader.demo.chat.filter.MyTextLineCodecFactory;
import com.candou.leader.demo.chat.handler.ServerHandler;

/**
 * Note: Server通讯层
 * Author: JiangChao
 * Date: 5/31/14
 * Email: jiangchao1987@gmail.com
 */
public class SimpleMinaServer {
    SocketAcceptor acceptor = null;

    SimpleMinaServer() {
        acceptor = new NioSocketAcceptor();
    }

    public boolean bind(){
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineCodecFactory())); //配置CodecFactory
        LoggingFilter log = new LoggingFilter();
        log.setMessageReceivedLogLevel(LogLevel.INFO);
        acceptor.getFilterChain().addLast("logger", log);
        acceptor.setHandler(new ServerHandler());     //配置handler
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
        
        try {
            acceptor.bind(new InetSocketAddress(9999));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public static void main(String args[]) {
        SimpleMinaServer server = new SimpleMinaServer();
        if (!server.bind()) {
            System.out.println("服务器启动失败");
        }
        else {
            System.out.println("服务器启动成功");
        }
    }
}
