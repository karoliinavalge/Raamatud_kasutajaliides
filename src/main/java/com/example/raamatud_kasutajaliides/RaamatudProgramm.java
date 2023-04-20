package com.example.raamatud_kasutajaliides;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RaamatudProgramm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

         //sisselogimise ekraan
         Group SLgrupp = new Group();
         VBox SLVbox = new VBox();
         Text sisestaNimi = new Text("Sisestage oma nimi:");
         TextField sisend = new TextField();
         Button kinnita = new Button("Logi sisse");
         SLVbox.getChildren().add(sisestaNimi);
        SLVbox.getChildren().add(sisend);
        SLVbox.getChildren().add(kinnita);
        SLgrupp.getChildren().add(SLVbox);
        Scene algus = new Scene(SLgrupp, 500, 500);





        //peaekraan
        Group grupp = new Group();

        /*
        GridPane ruudustik = new GridPane();
        ruudustik.setVgap(3);
        ruudustik.setHgap(3);
         */

        VBox lugemiseVBox = new VBox();
        VBox tegevusedVBox = new VBox();
        HBox peaHBox = new HBox();

        lugemiseVBox.setSpacing(7);
        tegevusedVBox.setSpacing(7);
        peaHBox.setSpacing(10);
        peaHBox.setPadding(new Insets(10, 10, 10, 10));

        Button loetud = new Button("Loetud raamatute nimekiri");
        loetud.setPrefWidth(200);
        Button loen = new Button("Lugemisel raamatute nimekiri");
        loen.setPrefWidth(200);

        Button lisaRaamat1 = new Button("Lisa raamat");
        lisaRaamat1.setPrefWidth(100);
        Button lisaRaamat2 = new Button("Lisa raamat");
        lisaRaamat2.setPrefWidth(100);

        /*
        ruudustik.add(loen, 2, 2);
        ruudustik.add(loetud, 2, 3);
        grupp.getChildren().addAll(ruudustik);
         */

        tegevusedVBox.getChildren().addAll(lisaRaamat1, lisaRaamat2);
        lugemiseVBox.getChildren().addAll(loetud, loen);
        peaHBox.getChildren().addAll(lugemiseVBox, tegevusedVBox);
        grupp.getChildren().add(peaHBox);
        Scene scene = new Scene(grupp);

        //tegevused
        kinnita.setOnMouseClicked(e -> primaryStage.setScene(scene));
        primaryStage.setScene(algus);
        primaryStage.show();
    }
}
