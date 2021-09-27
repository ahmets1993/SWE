package at.monsterclient;

import at.monsterserver.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    private static String[] cards = {"Yasuo", "Zed", "Amumu", "Zyra"};
    private static String[] power = {"100", "150", "120", "115"};
    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);


    }
    public void run() {
    String userInfo = "Ahmet 1234";
        try {
            while (true) {
                String request = in.readLine();
                if (request.equals(userInfo) ){
                        out.println("1");
                } else {
                    out.println("0");
                }
            }
        } catch(IOException e){
            System.err.println("IO Exception in client handler");
            System.err.println(e.getStackTrace());
        }finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static String getRandomName(){

        String card = cards[(int)(Math.random()*cards.length)];
        String adj = power[(int)(Math.random()*power.length)];
        return card + "'s attack power is " + adj;


    }
}
