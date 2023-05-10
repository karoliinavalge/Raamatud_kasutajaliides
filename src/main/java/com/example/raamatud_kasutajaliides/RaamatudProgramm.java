package com.example.raamatud_kasutajaliides;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


public class RaamatudProgramm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Raamatute listid
        LoetudRaamatud loetudRaamatud = new LoetudRaamatud();
        TahanLugeda tahanLugeda = new TahanLugeda();
        HetkelLoen hetkelLoen = new HetkelLoen();

        //Test
        /*
        Raamat raamat1 = new Raamat("Tõde ja õigus I", "A. H. Tammsaare", 467, 350);
        Raamat raamat2 = new Raamat("Kevade", "Oskar Luts", 314, 288);
        hetkelLoen.lisaRaamat(raamat1);
        hetkelLoen.lisaRaamat(raamat2);

         */

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
        peaBorder.setCenter(tegevusedVBox);
        grupp.getChildren().add(peaBorder);
        Scene scene = new Scene(grupp);

        //sisselogimise ekraani tegevused
        kinnita.setOnMouseClicked(e -> {
            try {
                loetudRaamatud.loeFailist("loetud.txt");
                System.out.println("lugesin loetud sisse");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                hetkelLoen.loeFailist("hetkel.txt");
                System.out.println("lugesin hetkel sisse");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                tahanLugeda.loeFailist("tahan.txt");
                System.out.println("lugesin tahan sisse");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //Lugeja kasutaja = new Lugeja(kasutajanimi.get(), loetudRaamatud, tahanLugeda, hetkelLoen);


            primaryStage.setScene(scene);
            primaryStage.setMinWidth(260);
            primaryStage.setMinHeight(350);
        });


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
        Button lisaLehekülgi = new Button("Lisa lehekülgi");
        Button hakkasinLugema = new Button("Hakkasin lugema");
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

        //Lugesin lõpuni tseen
        Group LLgrupp = new Group();
        VBox LLVBox = new VBox();

        //Vormindus
        LLVBox.setPadding(new Insets(30, 30, 30, 30));
        LLVBox.setSpacing(10);


        //lugesinLõpuni nupu funktsionaalsus
        lugesinLõpuni.setOnMouseClicked(e -> {
            LLVBox.getChildren().add(new Text("Millise raamatu lõpetasid?"));
            for (Raamat loetavRaamat : hetkelLoen.raamatud) {
                Button loetavaRaamatuNupp = new Button("\"" + loetavRaamat.getPealkiri() + "\" - " + loetavRaamat.getAutor());
                loetavaRaamatuNupp.setOnMouseClicked(event -> {
                    String pealkiri = loetavRaamat.getPealkiri();
                    lugesinLõpuni(pealkiri, hetkelLoen, loetudRaamatud);
                    System.out.println("tegin ära lugesinLõpuni");
                    Alert kinnitus = new Alert(Alert.AlertType.CONFIRMATION);
                    kinnitus.setContentText("Lisasin raamatu õigesse nimekirja");
                    kinnitus.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            primaryStage.setScene(scene);
                        }
                    });


                });
                LLVBox.getChildren().add(loetavaRaamatuNupp);
            }
            LLgrupp.getChildren().add(LLVBox);
            Scene LLtseen = new Scene(LLgrupp);

            primaryStage.setScene(LLtseen);
            primaryStage.setHeight(LLVBox.getHeight() + 40);
            primaryStage.setWidth(LLVBox.getWidth());
            primaryStage.setResizable(false);
        });

        //Kuva nimekirjad tseen
        Group KNgrupp = new Group();
        HBox KNHBox = new HBox();
        VBox tahanLugedaVBox = new VBox();
        VBox hetkelLoenVBox = new VBox();
        VBox loetudVBox = new VBox();

        VBox[] raamatuteVBoxid = {tahanLugedaVBox, hetkelLoenVBox, loetudVBox};

        //Vormindus
        KNHBox.setSpacing(10);
        KNHBox.setPadding(new Insets(30, 40, 30, 30));
        for (VBox vbox : raamatuteVBoxid) {
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(10, 10, 10, 10));
        }

        //Lisab nimekirjade pealkirjad VBoxidesse
        tahanLugedaVBox.getChildren().add(new Text("Tahan lugeda:"));
        hetkelLoenVBox.getChildren().add(new Text("Hetkel loetavad raamatud:"));
        loetudVBox.getChildren().add(new Text("Loetud raamatud:"));

        //Lisab VBoxidesse raamatud


        //Kuva nupu funktsionaalsus
        kuva.setOnMouseClicked(e -> {
            for (Raamat raamat : tahanLugeda.raamatud) {
                Text raamatuNimi = new Text("\"" + raamat.getPealkiri() + "\" - " + raamat.getAutor());
                tahanLugedaVBox.getChildren().add(raamatuNimi);
            }
            for (Raamat raamat : hetkelLoen.raamatud) {
                Text raamatuNimi = new Text("\"" + raamat.getPealkiri() + "\" - " + raamat.getAutor());
                hetkelLoenVBox.getChildren().add(raamatuNimi);
            }
            for (Raamat raamat : loetudRaamatud.raamatud) {
                Text raamatuNimi = new Text("\"" + raamat.getPealkiri() + "\" - " + raamat.getAutor());
                loetudVBox.getChildren().add(raamatuNimi);
            }

            //Kui nimekirjad on tühjad kuvab vastava teate ekraanile
            if (tahanLugeda.raamatud.isEmpty() && hetkelLoen.raamatud.isEmpty() && loetudRaamatud.raamatud.isEmpty()) {
                Text tühjadNimekirjad = new Text("Hetkel on kõik nimekirjad tühjad!");
                KNHBox.getChildren().add(tühjadNimekirjad);
            }
            //Kui nimekirjad ei ole tühjad, kuvab nimekirjad
            else {
                KNHBox.getChildren().addAll(tahanLugedaVBox, hetkelLoenVBox, loetudVBox);
            }
            KNgrupp.getChildren().add(KNHBox);
            Scene KNtseen = new Scene(KNgrupp);


            primaryStage.setScene(KNtseen);
            primaryStage.setHeight(KNHBox.getHeight() + 40);
            primaryStage.setWidth(KNHBox.getWidth());
            primaryStage.setResizable(false);
        });

        /*
        ruudustik.add(loen, 2, 2);
        ruudustik.add(loetud, 2, 3);
        grupp.getChildren().addAll(ruudustik);
         */

        //peaHBox.getChildren().add(tegevusedVBox);







        primaryStage.setScene(algus);
        primaryStage.show();


    }

    public void lugesinLõpuni(String pealkiri, Nimekiri hetkelLoen, Nimekiri loetudRaamatud){
        for (Raamat raamat : hetkelLoen.raamatud) {
            if (raamat.getPealkiri().equals(pealkiri)) {
                hetkelLoen.eemaldaRaamat(raamat);
                String hinnang = "-1";
                //Küsib kasutajalt hinnangut seni kuni kasutaja sisestab täisarvu vahemikus 1-5
                /**
                 while (Integer.parseInt(hinnang) < 0 || Integer.parseInt(hinnang) > 5)
                 hinnang = JOptionPane.showInputDialog(null, "Sisestage hinnang loetud raamatule (1-5) ", "Andmete sisestamine",
                 JOptionPane.QUESTION_MESSAGE);
                 */
                raamat.setHinnang(Integer.parseInt(hinnang));
                loetudRaamatud.lisaRaamat(raamat);
                break;
            }
        }
    }

}
