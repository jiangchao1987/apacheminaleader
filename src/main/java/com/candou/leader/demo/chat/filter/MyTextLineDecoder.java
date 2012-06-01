package com.candou.leader.demo.chat.filter;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MyTextLineDecoder implements ProtocolDecoder {
    Charset charset = Charset.forName("UTF-8");
    IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);

    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput output) throws Exception {
        while (in.hasRemaining()) {
            byte b = in.get();
            if (b == '\n') {
                buf.flip();
                byte[] bytes = new byte[buf.limit()];
                buf.get(bytes);
                String message = new String(bytes, charset);
                buf = IoBuffer.allocate(100).setAutoExpand(true);

                output.write(message);
            }
            else {
                buf.put(b);
            }
        }
    }

    @Override
    public void dispose(IoSession arg0) throws Exception {

    }

    @Override
    public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1) throws Exception {

    }
}
