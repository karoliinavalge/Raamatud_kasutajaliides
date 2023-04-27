package com.example.raamatud_kasutajaliides;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;


public class RaamatudProgramm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

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
        BorderPane peaBorder = new BorderPane();
        VBox lugemiseVBox = new VBox();
        VBox tegevusedVBox = new VBox();
        HBox peaHBox = new HBox();

        lugemiseVBox.setSpacing(7);
        tegevusedVBox.setSpacing(10);
        tegevusedVBox.setPadding(new Insets(30, 30, 30, 30));
        //peaHBox.setSpacing(10);
        //peaHBox.setPadding(new Insets(30, 30, 30, 30));
        peaBorder.setPrefSize(250, 300);
        peaBorder.setMinWidth(250);
        peaBorder.setMinHeight(300);


        //Raamatute nimekirjad
        Button loetud = new Button("Loetud raamatute nimekiri");
        loetud.setPrefWidth(200);
        Button loen = new Button("Lugemisel raamatute nimekiri");
        loen.setPrefWidth(200);
        Button tahan = new Button("Tahan lugeda");
        tahan.setPrefWidth(200);

        lugemiseVBox.getChildren().addAll(loetud, loen, tahan);

        Text tekst = new Text("Mida soovid teha?");
        tegevusedVBox.getChildren().add(tekst);

        //Tegevused
        Button lisaRaamat = new Button("Lisa raamat");
        Button lisaLehekülgi = new Button("Lisa raamat");
        Button hakkasinLugema = new Button("Lisa raamat");
        Button lugesinLõpuni = new Button("Lugesin raamatu lõpuni");
        Button valiJuhuslik = new Button("Vali juhuslik raamat");
        Button kuva = new Button("Kuva nimekirjad");


        Button[] tegevused = {lisaRaamat, lisaLehekülgi, hakkasinLugema, lugesinLõpuni,
        valiJuhuslik, kuva};


        for (Button nupp : tegevused) {
            nupp.setPrefWidth(200);
            nupp.setPrefHeight(30);
            tegevusedVBox.getChildren().add(nupp);
        }



        /*
        ruudustik.add(loen, 2, 2);
        ruudustik.add(loetud, 2, 3);
        grupp.getChildren().addAll(ruudustik);
         */

        //peaHBox.getChildren().add(tegevusedVBox);
        peaBorder.setCenter(tegevusedVBox);
        grupp.getChildren().add(peaBorder);
        Scene scene = new Scene(grupp);


        //tegevused
        kinnita.setOnMouseClicked(e -> {
            LoetudRaamatud loetudRaamatud = new LoetudRaamatud();
            TahanLugeda tahanLugeda = new TahanLugeda();
            HetkelLoen hetkelLoen = new HetkelLoen();

            String kasutaja = sisend.getText();



            try {
                loetudRaamatud.loeFailist(kasutaja + "LoetudRaamatud.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                tahanLugeda.loeFailist(kasutaja + "TahanLugeda.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                hetkelLoen.loeFailist(kasutaja + "HetkelLoen.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            primaryStage.setScene(scene);
            primaryStage.setMinWidth(260);
            primaryStage.setMinHeight(350);
        });

        //minu osa

        primaryStage.setScene(algus);
        primaryStage.show();
    }




}
