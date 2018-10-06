package server;

import util.NetworkUtil;
import util.Person;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;


public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil nc;
    Main main;
    public final static int FILE_SIZE = 6022386;
    int bytesRead;
    int current = 0;
    public final static String FILE_TO_RECEIVED = "F:\\Java\\JavaFX\\JavaFX\\JavaFX Simple Login\\JavaFXLoginSimple\\src\\sample\\images2.jpg";

    public ReadThreadServer(NetworkUtil nc, Main main) {
        this.nc = nc;
        this.thr = new Thread(this);
        this.main=main;
        thr.start();
    }

    public void run() {
        try {
            while(true) {
                Object o=nc.read();

                if(o!= null) {
                    if(o instanceof util.Person) {
                        Person obj=(Person)o;
                        obj.setNotice(main.mainnotice);
                        main.hashtable.put(obj.getName(), nc);
                        System.out.println(obj.getName());
                        if(obj.getMood().equals("file")){
                            obj.setMood("pathao");
                            nc.write(obj);

                            while(true){
                            try {
                                nc.is = nc.socket.getInputStream();
                            } catch (IOException e1) {

                            }
                            if(nc.is!=null){
                                try {
                                    //sock = new Socket(SERVER, SOCKET_PORT);
                                    //System.out.println("Connecting...");

                                    // receive file
                                    byte [] mybytearray  = new byte [FILE_SIZE];
                                    //InputStream is = sock.getInputStream();
                                    try {
                                        nc.fos = new FileOutputStream(FILE_TO_RECEIVED);
                                    } catch (FileNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                    nc.bos = new BufferedOutputStream(nc.fos);
                                    try {
                                        bytesRead = nc.is.read(mybytearray,0,mybytearray.length);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    current = bytesRead;

                                    do {
                                        try {
                                            bytesRead =
                                                    nc.is.read(mybytearray, current, (mybytearray.length-current));
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                        if(bytesRead >= 0) current += bytesRead;
                                    } while(bytesRead > -1);

                                    try {
                                        nc.bos.write(mybytearray, 0 , current);
                                        nc.bos.flush();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }

                                    System.out.println("File " + FILE_TO_RECEIVED
                                            + " downloaded (" + current + " bytes read)");
                                }
                                finally {
                                     if (nc.socket != null) try {
                                    if (nc.fos != null) nc.fos.close();
                                    if (nc.bos != null) nc.bos.close();
                                    nc.socket.close();
                                    } catch (IOException e1) {
                                     e1.printStackTrace();
                                     }
                                    break;

                                }
                            }

                        }}
                        else if(obj.getMood().equals("signup")){
                            //main.usersdata.add(new userData(obj.getName()));
                            //main.homeControl.usersonline.setItems(main.usersdata);
                            int i=main.getCnt();
                                i++;
                            main.addCnt(i);
                            main.addClient(i,obj.getName(),obj.getEmail(),obj.getfName(),obj.getmName(),obj.getPresentAddress(),
                                    obj.getPerAddress(),obj.getVoterId(),obj.getMobileNo(),"2012-02-13");
                            main.listClient();
                            main.w.add(new allUsers(obj.getName(),obj.getVoterId()));
                            main.noticeControl.Allusers.setItems(main.w);
                        }
                        else if(obj.getMood().equals("login")) {
                            MySQLConnect oc=new MySQLConnect("localhost","PCB","root","Tanveer@81");
                            String query = "select * from client";
                            ResultSet rs = oc.searchDB(query);
                            while(rs.next())
                            {
                                if(rs.getString("vooterID").equals(obj.getName())){
                                        if(rs.getString("passWord").equals(obj.getPassword())){
                                            obj.setValidlogin(true);
                                           //main.usersdata.add(new userData(obj.getName()));
                                            //main.homeControl.usersonline.setItems(main.usersdata);
                                        }
                                        else obj.setValidlogin(false);
                                        break;
                            }
                            }
                        }
                        else if(obj.getMood().equals("post")) {
                            main.addPost(obj.getName(),obj.getPost());
                            main.data.add(new Data(obj.getName(),obj.getPost()));
                           main.homeControl.allPosts.setItems(main.data);
                        }
                        //obj.setNotice(main.P.getNotice());
                        nc.write(obj);
                    }
                }
                main.hasGot=true;
            }

        } catch(Exception e) {
            System.out.println (e);
        }
        nc.closeConnection();
    }
}

