package util;

import java.io.*;
import java.net.Socket;

/**
 * Created by emon on 11/28/2015.
 */
public class NetworkUtil
{
    public Socket socket;
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    public FileInputStream fis = null;
    public BufferedInputStream bis = null;
    public OutputStream os = null;
    public FileOutputStream fos = null;
    public BufferedOutputStream bos = null;
    public InputStream is = null;


    public NetworkUtil(String s, int port) {
        try {
            this.socket=new Socket(s,port);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("In NetworkUtil : " + e.toString());
        }
    }

    public NetworkUtil(Socket s) {
        try {
            this.socket = s;
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("In NetworkUtil : " + e.toString());
        }
    }

    public Object read() {
        Object o = null;
        try {
            o=ois.readObject();
        } catch (Exception e) {
            System.out.println("Reading Error in network : " + e.toString());
        }
        return o;
    }

    public void write(Object o) {
        try {
            oos.writeObject(o);
        } catch (IOException e) {
            System.out.println("Writing  Error in network : " + e.toString());
        }
    }

    public void closeConnection() {
        try {
            ois.close();
            oos.close();
        } catch (Exception e) {
            System.out.println("Closing Error in network : "  + e.toString());
        }
    }
}
