/*
 * File Name: com.com.huawei.m2m.nsse.j808.client.SimpleHttpServer.java
 *
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.huawei.testMessagePush;

import com.huawei.utils.ExceptionUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHttpServer {
    /**
     * 日志工具
     */

    private int port;
    private ServerSocketChannel serverSocketChannel = null;
    private ExecutorService executorService;
    private static final int POOL_MULTIPLE = 4;

    private int msgCnt = 0;

    private static String recvString = null;

    public static String getRecvString() {
        return recvString;
    }

    public SimpleHttpServer(int port) throws IOException {
        this.port = port;
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.socket().bind(new InetSocketAddress(this.port));

    }

    public void service() {
        while (true) {
            SocketChannel socketChannel = null;
            try {
                socketChannel = serverSocketChannel.accept();
                executorService.execute(new Handler(socketChannel));
            } catch (IOException e) {
                System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
            }
        }
    }

    public static void startServer(final int port) {
        System.out.println("开启服务器..， 服务端口 = " + port);
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    new SimpleHttpServer(port).service();
                } catch (Exception e) {
                    System.out.println("开启服务器失败， 服务端口 = " + port);
                    System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
                    System.out.println();
                }
            }
        });
        t.start();
    }

    public static String recvBuf;

    public static String getRecvBuf() {
        return recvBuf;
    }

    public static void setRecvBuf(String recvBuf) {
        SimpleHttpServer.recvBuf = recvBuf;
    }

    class Handler implements Runnable {
        private SocketChannel socketChannel;

        public Handler(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        public void run() {
            handle(socketChannel);
        }

        private void handle(SocketChannel socketChannel) {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(2048);
                socketChannel.read(buffer);
                buffer.flip();
                recvString = new String(buffer.array());

                // String responseHead = recvString.substring(0,
                // recvString.lastIndexOf("\n"));
                String responseJsonBody = "";

                if (recvString != null) {

                    try {
                        responseJsonBody = recvString.substring(recvString.lastIndexOf("\n"));
                    } catch (Exception e) {
                        System.out.println("get responseJsonBody fail.");
                    }
                }

                System.out.print("接收到平台推送的第" + msgCnt + "消息 :");
                System.out.println(responseJsonBody);

                msgCnt++;
                System.out.println();

            } catch (IOException e) {
                System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
            } finally {
                try {
                    if (socketChannel != null)
                        socketChannel.close();
                } catch (IOException e) {
                    System.out.println(ExceptionUtil.getBriefExceptionStackTrace(e));
                }
            }
        }

    }

}
