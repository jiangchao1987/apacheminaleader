package com.candou.leader.demo.chat.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.candou.leader.demo.chat.client.ChatPanel;

/**
 * Note: Client业务逻辑处理层
 * Author: JiangChao
 * Date: 5/31/13
 * Email: jiangchao1987@gmail.com
 */
public class ClientHandler extends IoHandlerAdapter {
    private ChatPanel messagePanel = null;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    ClientHandler() {

    }

    public ClientHandler(ChatPanel messagePanel) {
        this.messagePanel = messagePanel;
    }

    public void messageReceived(IoSession session, Object message) throws Exception {
        String messageStr = message.toString();
        logger.info("receive a message is : " + messageStr);

        if (messagePanel != null)
            messagePanel.showMsg(messageStr);
    }

    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("客户端发了一个信息：" + message.toString());
    }
}
