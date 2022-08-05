package com.cuukenn.openstudysource.sample.jdk.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author changgg
 */
public class NioServer {
    private static int timeout = 1000;

    @SneakyThrows
    public static void main(String[] args) {
        Selector selector = Selector.open();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(7000));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for (; ; ) {
            if (selector.select(timeout) <= 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectedKey = iterator.next();
                if (selectedKey.isAcceptable()) {
                    @SuppressWarnings("resource")
                    ServerSocketChannel channel = (ServerSocketChannel) selectedKey.channel();
                    SocketChannel acceptChannel = channel.accept();
                    System.out.println("channel:" + acceptChannel);
                    acceptChannel.configureBlocking(false);
                    acceptChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectedKey.isReadable()) {
                    try (SocketChannel channel = (SocketChannel) selectedKey.channel()) {
                        System.out.println("channel:" + channel);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        StringBuilder stringBuilder = new StringBuilder();
                        int len;
                        while ((len = channel.read(byteBuffer)) > 0) {
                            byte[] bytes = new byte[len];
                            byteBuffer.flip();
                            byteBuffer.get(bytes);
                            stringBuilder.append(new String(bytes));
                            byteBuffer.clear();
                        }
                        System.out.println(stringBuilder);
                        channel.write(ByteBuffer.wrap((
                                "HTTP/1.1 200 OK\n" +
                                        "Date: Tue, 08 Jul 2014 05:28:43 GMT\r\n" +
                                        "Server: Apache/2\n" +
                                        "Last-Modified: Wed, 01 Sep 2004 13:24:52 GMT\r\n" +
                                        "ETag: \"40d7-3e3073913b100\"\r\n" +
                                        "Accept-Ranges: bytes\r\n" +
                                        "Content-Length: 16599\r\n" +
                                        "Cache-Control: max-age=21600\r\n" +
                                        "Expires: Tue, 08 Jul 2014 11:28:43 GMT\r\n" +
                                        "P3P: policyref=\"http://www.w3.org/2001/05/P3P/p3p.xml\"\r\n" +
                                        "Content-Type: text/html; charset=iso-8859-1\r\n" +
                                        "\r\n" +
                                        "{\"name\": \"qiu\", \"age\": 25}"
                        ).getBytes(StandardCharsets.UTF_8)));
                    }
                }
                //需要移除，某则会多次触发
                iterator.remove();
            }
        }
    }
}
