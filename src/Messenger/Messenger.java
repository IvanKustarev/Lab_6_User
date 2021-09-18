package Messenger;

import WorkWithConsole.ConsoleWorker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Messenger {

    private ConsoleWorker consoleWorker;
    private SocketChannel socketChannel;
    SocketChannel connectionSocketChannel;

    public Messenger(ConsoleWorker consoleWorker) {
        this.consoleWorker = consoleWorker;
    }

    public boolean sendRequest(Request request) {
        byte[] bytes = null;
        try {
            bytes = Serializer.serialize(request);
        } catch (Exception e) {
            consoleWorker.write("Не сериализуемый объект");
            return false;
        }
        try {
            sendMessage(socketChannel, bytes);
        } catch (Exception e) {
            e.printStackTrace();
            consoleWorker.write("Проблема с отправеой сообщения серверу!");
            return false;
        }

        return true;
    }

    public Response getResponse() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[1000]);
        int size = 0;
        try {
//            socketChannel.socket().getInputStream().available();
            size = socketChannel.read(byteBuffer);
            byte[] bytes = Arrays.copyOf(byteBuffer.array(), size);
            return (Response) Serializer.deSerializer(bytes);

        } catch (Exception e) {
            consoleWorker.write("Проблема с получением ответа от сервера!");
        }
        return new Response("Возникла ошибка!");
    }

    public void connect(String host, int port) throws IOException {
        connectionSocketChannel = SocketChannel.open(new InetSocketAddress(host, port));
        Socket socket = connectionSocketChannel.socket();
        socket.close();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(socket.getLocalSocketAddress());
        socketChannel = serverSocketChannel.accept();
    }

    public void sendMessage(SocketChannel socketChannel, byte[] bytes) throws IOException {
        socketChannel.write(ByteBuffer.wrap(bytes));
    }

    public byte[] receiveMessage() {
        return null;
    }
}
