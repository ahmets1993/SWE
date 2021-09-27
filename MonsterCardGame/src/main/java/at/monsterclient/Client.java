package at.monsterclient;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9091;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        System.out.println("Welcome to Legends Arena");
        System.out.println("-----------MENU---------");
        System.out.println("1.Login");
        System.out.println("2.Register");
        System.out.println("3.Update Account");
        System.out.println("4.Delete Account");
        System.out.println("3.Exit");

        String command = keyboard.readLine();

        switch (command)
        {
            case "1":
                System.out.println("Username");
                String userName = keyboard.readLine();
                System.out.println("Password");
                String password = keyboard.readLine();
                LoginUser(userName,password);
                break;

            case "5":
                break;
        }
        socket.close();
        System.exit(0);



    }

    private static void LoginUser(String userName, String password) throws  IOException
    {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);


        String userInfo = userName + " "+ password;
        System.out.println(userInfo);
        out.println(userInfo);
        String serverResponse = input.readLine();
        if(serverResponse.equals("1"))
        {
            System.out.println("Authentication is Successful\n");
            System.out.println("Loading....");
            waitMethod(10000);
            ManageCard(userName);

        }else if (serverResponse.equals("0")){

            System.out.println("Authentication is not Successful");
            System.out.println("Username");
            String userName2 = keyboard.readLine();
            System.out.println("Password");
            String password2 = keyboard.readLine();
            LoginUser(userName2,password2);

    }


}

    private static void ManageCard(String userName) throws IOException {
        int LeagueCoin =20;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        StoryTeller(userName,LeagueCoin,1);
        String BlitzcrankOption = keyboard.readLine();
        switch (BlitzcrankOption)
        {
            case "1":
                    System.out.println("Loading....");
                    waitMethod(10000);
                    CardDeskPrinter(2,userName);
                    String HoldingDesk = keyboard.readLine();
                    System.out.println("Chosen Card Desk No : "+HoldingDesk);
                    System.out.println("Loading....");
                    waitMethod(10000);
                    GameLobby();
                break;

            case "5":
                break;
        }
    }

    public static void GameLobby() {
        System.out.println(ANSI_BLUE+"Please Enter the option number that you want to do." +
                "\n1.Create Game" +
                "\n2.Join Game" +
                "\n3.Back to Card Store" +
                "\n4.Exit "+ANSI_RESET);
    }



    private static void StoryTeller(String userName, Integer LeagueCoin,int StoryNo){
        switch (StoryNo){
            case 1:
                CoolWriter("Welcome to League of Legends Card Shop Stranger." +
                        "\nI'm POLO. I have all the Cards Collections of the Universe." +
                        "\nTell me about money and get the work.\n",ANSI_YELLOW );
                waitMethod(1000);
                CoolWriter("Hi Mr. POLO," +
                        " My name is " +userName +". I have "+LeagueCoin+" League Coin.\n",ANSI_RED );
                CoolWriter("God them you poor." +
                        "\nBut don't worry. My Autobot Blitzcrank will help you.",ANSI_YELLOW );
                System.out.println("\nLoading....");
                waitMethod(10000);
                System.out.println(ANSI_BLUE+"Please Enter the option number that you want to do." +
                        "\n1.Show my Card Packages" +
                        "\n2.Buy new Card Package" +
                        "\n3.Sell Card" +
                        "\n4.Give Tip to Blitzcrank "+ANSI_RESET);
                break;
        }
    }


    public static void CardDeskPrinter(Integer NumberOfDesk,String Holder) {

        int i=0;
            System.out.print(                "                                                    There are your Card Desks                                      \n");
        for(i=0;i<NumberOfDesk;i++){

            System.out.print(                "               *****************   *****************   *****************   *****************   *****************   \n" +
                                             "               *               *   *               *   *               *   *               *   *               *   \n" +
                                             "               *               *   *               *   *               *   *               *   *               *   \n" +
                                             "               *               *   *               *   *               *   *               *   *               *   \n" +
                                             "               *    CampName   *   *    CampName   *   *   CampName    *   *   CampName    *   *   CampName    *   \n" +
                                             "   DeskName :  *  AttackDamage *   *  AttackDamage *   * AttackDamage  *   *  AttackDamage *   * AttackDamage  *   \n" +
                                             "               *    Defence    *   *    Defence    *   *    Defence    *   *    Defence    *   *   Defence     *   \n" +
                                             "               *               *   *               *   *               *   *               *   *               *   \n" +
                                             "               *               *   *               *   *               *   *               *   *               *   \n" +
                                             "               *               *   *               *   *               *   *               *   *               *   \n" +
                                             "               *****************   *****************   *****************   *****************   *****************   \n\n\n" );


        }

    }












    public static void waitMethod(int second){

        try{
            Thread.sleep(second);
        }catch(InterruptedException e){}
    }

    public static void CoolWriter(String index, String indexColor) {

        String indexColored= indexColor + index + ANSI_RESET;
        for (int i =0; i< indexColored.length(); i++) {
            System.out.print(indexColored.charAt(i));
           waitMethod(50);
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
}
