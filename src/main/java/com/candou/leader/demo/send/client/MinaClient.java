package com.candou.leader.demo.send.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.candou.leader.demo.send.handler.ClientHandler;

/**
 * Note: Client端, 发送消息, 接收消息
 * Author: JiangChao
 * Date: 5/31/13
 * Email: jiangchao1987@gmail.com
 */
public class MinaClient {
    NioSocketConnector connector;
    ConnectFuture future;

    public MinaClient() {
        connector = new NioSocketConnector();
    }

    boolean connect() {
        try {
            connector.getFilterChain().addLast("objectFilter",
                    new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
            connector.setHandler(new ClientHandler());
            connector.setConnectTimeoutCheckInterval(30);
            future = connector.connect(new InetSocketAddress("localhost", 9999));
            future.awaitUninterruptibly();
            future.getSession().getCloseFuture().awaitUninterruptibly();
            connector.dispose();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        MinaClient client = new MinaClient();
        client.connect();
    }
}
