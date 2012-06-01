package com.candou.leader.demo.send.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.candou.leader.demo.send.handler.ServerHandler;

/**
 * Note: Server端, 接收消息, 处理然后发送回去
 * Author: JiangChao
 * Date: 5/31/13
 * Email: jiangchao1987@gmail.com
 */
public class MinaServer {
    SocketAcceptor acceptor = null;

    public MinaServer() {
        acceptor = new NioSocketAcceptor();
    }

    public boolean bind() {
//        acceptor.getFilterChain().addLast("stringFilter", new ProtocolCodecFilter(new MyTextLineCodecFactory())); // 设定接收字符串
        acceptor.getFilterChain().addLast("objectFilter",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory())); // 设定接收对象
        acceptor.setHandler(new ServerHandler()); // 配置handler
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);

        try {
            acceptor.bind(new InetSocketAddress(9999));
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String args[]) {
        MinaServer server = new MinaServer();
        if (!server.bind()) {
            System.out.println("服务器启动失败");
        }
        else {
            System.out.println("服务器启动成功");
        }
    }
}
