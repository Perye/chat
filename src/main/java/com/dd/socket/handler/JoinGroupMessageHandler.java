package com.dd.socket.handler;

import com.dd.socket.MsgHandlerInterface;
import org.nutz.ioc.loader.annotation.IocBean;
import org.tio.core.ChannelContext;

/**
 *处理加群消息
 */
@IocBean
public class JoinGroupMessageHandler implements MsgHandlerInterface {
    @Override
    public Object handler(String text, ChannelContext context) {
        return null;
    }
}
