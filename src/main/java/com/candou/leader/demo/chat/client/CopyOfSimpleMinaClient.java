package com.candou.leader.demo.chat.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.candou.leader.demo.chat.filter.MyTextLineCodecFactory;
import com.candou.leader.demo.chat.handler.ClientHandler;

public class CopyOfSimpleMinaClient {
    public SocketConnector connector = null;
    public ConnectFuture future;
    public IoSession session = null;
    private ChatPanel messagePanel = null;

    CopyOfSimpleMinaClient() {

    }

    CopyOfSimpleMinaClient(ChatPanel messagePanel) {
        this.messagePanel = messagePanel;
    }

    boolean connect(){
        try{
            connector = new NioSocketConnector();
            connector.setConnectTimeoutMillis(3000);
            connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new MyTextLineCodecFactory()));
            LoggingFilter log = new LoggingFilter();
            log.setMessageReceivedLogLevel(LogLevel.INFO);
            connector.getFilterChain().addLast("logger", log);
            connector.setHandler(new ClientHandler(messagePanel));
            
            future = connector.connect(new InetSocketAddress("127.0.0.1" , 9999));
            future.awaitUninterruptibly();
            session = future.getSession();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
    }

    public void setAttribute(Object key, Object value) {
        session.setAttribute(key, value);
    }

    void sentMsg(String message) {
        session.write(message);
    }

    boolean close() {
        CloseFuture future = session.getCloseFuture();
        future.awaitUninterruptibly(1000);
        connector.dispose();
        return true;
    }

    public SocketConnector getConnector() {
        return connector;
    }

    public IoSession getSession() {
        return session;
    }

    public static void main(String[] args) {
        CopyOfSimpleMinaClient client = new CopyOfSimpleMinaClient();
        if (client.connect()) {
            client.sentMsg("client-2: hello , sever !");
            client.close();
        }
    }
}
