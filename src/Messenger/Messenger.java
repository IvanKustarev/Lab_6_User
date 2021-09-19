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
import Main.Main;

public class Messenger {

    private ConsoleWorker consoleWorker;
    private SocketChannel socketChannel;
    SocketChannel connectionSocketChannel;

    public Messenger(ConsoleWorker consoleWorker) {
        this.consoleWorker = consoleWorker;
    }

    public boolean sendRequest(Request request) throws IOException{
        byte[] bytes = null;
        try {
            bytes = Serializer.serialize(request);
        } catch (Exception e) {
            consoleWorker.write("Не сериализуемый объект");
            e.printStackTrace();
            return false;
        }
//        try {
            sendMessage(socketChannel, bytes);
//        } catch (Exception e) {
//            consoleWorker.write("Проблема с отправеой сообщения серверу!");
//            return false;
//        }

        return true;
    }

    public Response getResponse() throws IOException{
        byte[] result = new byte[0];
//        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[1000]);
                int size = socketChannel.read(byteBuffer);

                byte[] bytes = Arrays.copyOf(byteBuffer.array(), size);
                result = concat(result, bytes);
                try {
                    Response response = (Response) Serializer.deSerializer(result);
                    break;
                } catch (Exception e) {
                    continue;
                }
            }
            try {
                return (Response) Serializer.deSerializer(result);
            }catch (Exception e){
                consoleWorker.write("Проблемма с десеиализацией объекта!");
                e.printStackTrace();
            }

//        } catch (Exception e) {
//            consoleWorker.write("Проблема с получением ответа от сервера!");
//        }
        return new Response("Возникла ошибка!");
    }

    private static byte[] concat(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        int i = 0;
        for (i = 0; i < first.length; i++) {
            result[i] = first[i];
        }
        for (; i < result.length; i++) {
            result[i] = second[i - first.length];
        }
            return result;
        }

        public void connect (String host,int port) throws IOException {
            connectionSocketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            Socket socket = connectionSocketChannel.socket();
            socket.close();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(socket.getLocalSocketAddress());
            socketChannel = serverSocketChannel.accept();
        }

        public void sendMessage (SocketChannel socketChannel,byte[] bytes) throws IOException {
            try {
                socketChannel.write(ByteBuffer.wrap(bytes));
            }catch (Exception e){
                consoleWorker.write("Потеряно соединение с сервером!");
            }
        }

        public byte[] receiveMessage () {
            return null;
        }
    }
