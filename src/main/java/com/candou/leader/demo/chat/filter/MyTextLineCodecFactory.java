package com.candou.leader.demo.chat.filter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Note: 协议解析层, 通讯层和业务层的桥梁
 * Author: JiangChao
 * Date: 5/31/14
 * Email: jiangchao1987@gmail.com
 */
public class MyTextLineCodecFactory implements ProtocolCodecFactory {

    @Override
    public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
        return new MyTextLineDecoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
        return new MyTextLineEncoder();
    }
}
