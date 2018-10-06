package server;
import java.io.*;

public class OzekiSms2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            String host = "127.0.0.1";
            int port = 9876;
            String username = "admin";
            String password = "Tanveer@81";

            OzekiSms osc = new OzekiSms(host, port);
            osc.login(username, password);


            String line = "Hello World";

            System.out.println("SMS message:");

            if(osc.isLoggedIn()) {
                osc.sendMessage("+551122334455", line);
                System.out.println("done");
                osc.logout();
            }


        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}