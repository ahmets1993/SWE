package at.monsterserver;

import at.monsterclient.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final int PORT = 9091;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4000);

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);

        while (true) {
            System.out.println("[Server]: Waiting for Client Connection.");
            Socket client = listener.accept();
            System.out.println("[Server]: A Client is Connected.");
            ClientHandler clientTread = new ClientHandler(client);
            clients.add(clientTread);
            pool.execute(clientTread);
        }
    }
}
