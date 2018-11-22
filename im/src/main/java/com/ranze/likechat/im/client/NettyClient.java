package com.ranze.likechat.im.client;


import com.ranze.likechat.im.client.handler.LoginResponseHandler;
import com.ranze.likechat.im.client.handler.MessageResponseHandler;
import com.ranze.likechat.im.codec.PacketDecoder;
import com.ranze.likechat.im.codec.PacketEncoder;
import com.ranze.likechat.im.codec.Spliter;
import com.ranze.likechat.im.proto.LoginProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new Spliter());
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);

    }

    public static void connect(final Bootstrap bootstrap, final String host, final int port, final int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("---开始登录---");
                System.out.print("账号：");
                Scanner sc = new Scanner(System.in);
                String userId = sc.nextLine();
                System.out.print("密码：");
                String password = sc.next();

                LoginProto.LoginRequest.Builder loginRequestBuilder = LoginProto.LoginRequest.newBuilder();
                loginRequestBuilder.setPhoneNum(userId);
                loginRequestBuilder.setPassword(password);

                Channel channel = ((ChannelFuture) future).channel();
                channel.writeAndFlush(loginRequestBuilder.build());
            } else if (retry == 0) {
                System.out.println("重试次数已用完，放弃连接！");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连......");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }


}
