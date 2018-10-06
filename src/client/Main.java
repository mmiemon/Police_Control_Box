package client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.NetworkUtil;
import util.Person;

import java.util.ArrayList;

public class Main extends Application {

    Stage stage;
    HomeController homeControl;
    myPostsController PostControl;
    developerController developerControl;
    noticeController noticeControl;
    contactsController contactsControl;
    signUpController signUpControl;
    logInController logInControl;
    profileController profileControl;
    public ArrayList<String> message=new ArrayList<String>();
    public Client client;
    public Person p=new Person();
    public NetworkUtil nc;
    public boolean hasgot=false;
    public boolean a=false;
    public String s="login";
    public String mainnotice;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            String serverAddress="127.0.0.1";
            int serverPort=33333;
            NetworkUtil n1 = new NetworkUtil(serverAddress,serverPort);
            new ReadThread(n1,this);
            nc=n1;
            stage = primaryStage;
            showLogIn();
        } catch(Exception e) {
            System.out.println (e+"main");
        }
    }

    public void showLogIn() throws Exception {
        //nc.write("ami");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("logIn.fxml"));
        Parent root = loader.load();
        logInController controller = loader.getController();
        //scontroller=controller;
        stage.setTitle("Client");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
        stage.setResizable(false);
        controller.setMain(this);

    }

    public void showSignUp() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Signup.fxml"));
        Parent root = loader.load();
        signUpController controller = loader.getController();
        //mcontroller=controller;
        stage.setTitle("Client");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
        stage.setResizable(false);
        controller.setMain(this);
    }
    public void showHome() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        controller.notice.setText(mainnotice);
        homeControl=controller;
        stage.setTitle("Client");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
        stage.setResizable(false);
        controller.setMain(this);
    }
    public void showMyPosts() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("myPosts.fxml"));
        Parent root = loader.load();

        // Loading the controller
        myPostsController controller = loader.getController();
        controller.setMain(this);
        PostControl=controller;

        // Set the primary stage
        stage.setTitle("Police Control Box");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
        stage.setResizable(false);
    }

    public void showContacts() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("contacts.fxml"));
        Parent root = loader.load();

        // Loading the controller
        contactsController controller = loader.getController();
        controller.setMain(this);
        contactsControl=controller;

        // Set the primary stage
        stage.setTitle("Police Control Box");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
        stage.setResizable(false);
    }

    public void showDevelopers() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("developer.fxml"));
        Parent root = loader.load();

        // Loading the controller
        developerController controller = loader.getController();
        controller.setMain(this);
        developerControl=controller;

        // Set the primary stage
        stage.setTitle("Police Control Box");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
        stage.setResizable(false);
    }

    public void showNotice() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("notice.fxml"));
        Parent root = loader.load();

        // Loading the controller
        noticeController controller = loader.getController();
        controller.setMain(this);
        noticeControl=controller;

        // Set the primary stage
        stage.setTitle("Police Control Box");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
        stage.setResizable(false);
    }

    public void showProfile() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("profile.fxml"));
        Parent root = loader.load();
        profileController controller = loader.getController();
        controller.setMain(this);
        profileControl=controller;
        stage.setTitle("Client");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
        stage.setResizable(false);
        profileControl.name.setText(p.getName());
        profileControl.fName.setText(p.getfName());
        profileControl.mName.setText(p.getmName());
        profileControl.mobileNo.setText(p.getMobileNo());
        profileControl.permanentAddress.setText(p.getPerAddress());
        profileControl.presentAddress.setText(p.getPresentAddress());
        profileControl.voterID.setText(p.getVoterId());
        Integer i=p.allposts.size();
        String s=Integer.toString(i);
        profileControl.totalPosts.setText(s);
    }


    public static void main(String[] args) {
        launch(args);
    }
}