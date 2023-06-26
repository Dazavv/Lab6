package me.dasha.lab6.utility;

import me.dasha.lab6.console.Console;
import me.dasha.lab6.dtp.Request;
import me.dasha.lab6.dtp.Response;
import me.dasha.lab6.dtp.ResponseStatus;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Objects;

public class Client {
    private String host;
    private int port;
    private int reconnectionTimeout;
    private int reconnectionAttempts;
    private int maxReconnectionAttempts;
    private Console console;
    private SocketChannel socket;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;
    private boolean firstExecution = true;


    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts, Console console) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
        this.console = console;
    }

    public Response sendAndAskResponse(Request request) throws IOException {
        while (true) {
            try {
                if(Objects.isNull(serverWriter) || Objects.isNull(serverReader)) throw new IOException();
                if (request.isEmpty()) return new Response(ResponseStatus.WRONG_ARGUMENTS, "Запрос пустой!");
                serverWriter.writeObject(request);
                serverWriter.flush();
                Response response = (Response) serverReader.readObject();
                //this.disconnectFromServer();//на этом моменте разрывает соединение
                reconnectionAttempts = 0;
                return response;
            } catch (IOException e) {
                if (reconnectionAttempts == 0){
                    connectToServer();
                    connectToServer();
                    reconnectionAttempts++;
                    continue;
                } else {
                    console.printError("Соединение с сервером разорвано\n");
                    //disconnectFromServer();
                }
                try {
                    reconnectionAttempts++;
                    if (reconnectionAttempts >= maxReconnectionAttempts) {
                        console.printError("Превышено максимальное количество попыток соединения с сервером\n");
                        return new Response(ResponseStatus.EXIT);
                    }
                    console.write("Повторная попытка через " + reconnectionTimeout / 1000 + " секунд\n");
                    Thread.sleep(reconnectionTimeout);
                    connectToServer();
                } catch (Exception exception) {
                    console.printError("Попытка соединения с сервером неуспешна\n");
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (socket != null) socket.close();
            console.write("Работа клиента завершена\n");
        }
    }

    public void connectToServer(){
        try{
            if (reconnectionAttempts > 0)
                console.write("Попытка повторного подключения\n");
            socket = SocketChannel.open(new InetSocketAddress(host, port));
            if (firstExecution) {
                console.write("Подключение успешно восстановлено\n");
                console.write("Обмен пакетами разрешен\n");
                firstExecution = false;
            }
            this.serverWriter = new ObjectOutputStream(socket.socket().getOutputStream());
            this.serverReader = new ObjectInputStream(socket.socket().getInputStream());

        } catch (IllegalArgumentException e){
            console.printError("Адрес сервера введен некорректно\n");
        } catch (IOException e) {
            console.printError("Произошла ошибка при соединении с сервером\n");}
        }

}