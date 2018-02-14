package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.test.level30.lesson15.big01.ConsoleHelper.readInt;
import static com.javarush.test.level30.lesson15.big01.ConsoleHelper.writeMessage;
import static com.javarush.test.level30.lesson15.big01.MessageType.*;

/**
 * Created by 1 on 06.09.2017.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args)
    {
        writeMessage("Port of server: ");
        try (ServerSocket serverSocket = new ServerSocket(readInt()))
        {
            writeMessage("Server is run!");
            while (true)
            {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (Exception e)
        {
            writeMessage("Socket error");
        }
    }

    private static class Handler extends Thread{
        private Socket socket;
        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        @Override
        public void run()
        {
            String newClient = "";
            try(Connection connection = new Connection(socket))
            {
                writeMessage("Installed new connection: " + connection.getRemoteSocketAddress());
                newClient = serverHandshake(connection);
                sendBroadcastMessage(new Message(USER_ADDED, newClient));
                sendListOfUsers(connection, newClient);
                serverMainLoop(connection, newClient);
            }
            catch (IOException e)
            {
                writeMessage("an error occurred while communicating with the remote address");
            }
            catch (ClassNotFoundException e)
            {
                writeMessage("an error occurred while communicating with the remote address");
            }finally
            {
                connectionMap.remove(newClient);
                sendBroadcastMessage(new Message(USER_REMOVED, newClient));
            }
            writeMessage("The connection to the remote address closed");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            String nameClient = null;
            while (true)
            {
                connection.send(new Message(NAME_REQUEST));
                Message reply = connection.receive();
                if (reply.getType() == USER_NAME)
                {
                    nameClient = reply.getData();
                    if (reply.getData() != null && !reply.getData().equals("") && !reply.getData().isEmpty() && !connectionMap.containsKey(reply.getData()))
                    {
                        connectionMap.put(reply.getData(), connection);
                        connection.send(new Message(NAME_ACCEPTED));
                        break;
                    }
                }
            }
            return nameClient;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> entry: connectionMap.entrySet()
                 )
            {
                if (!entry.getKey().equals(userName)) {
                    connection.send(new Message(USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            String message = "";
            while (true)
            {
                Message reply = connection.receive();
                if (reply.getType() == TEXT) {
                    message = userName + ": " + reply.getData();
                    sendBroadcastMessage(new Message(TEXT, message));
                } else writeMessage("Message is not TEXT");
            }
        }

    }

    public static void sendBroadcastMessage(Message message){
        for (Map.Entry<String, Connection> entry: connectionMap.entrySet()
             )
        {
            try
            {
                entry.getValue().send(message);
            }
            catch (IOException e)
            {
                writeMessage("The message failed to send");
            }
        }
    }
}
