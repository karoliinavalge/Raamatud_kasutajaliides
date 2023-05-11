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
         SLVbox.setSpacing(10);
         SLVbox.setPadding(new Insets(30, 30, 30, 30));
         Text sisestaNimi = new Text("Sisestage oma nimi:");
         TextField sisend = new TextField();
         Button kinnita = new Button("Logi sisse");
        SLVbox.getChildren().addAll(sisestaNimi, sisend, kinnita);
        SLgrupp.getChildren().add(SLVbox);
        Scene algus = new Scene(SLgrupp, 300, 150);

        // Listeneri kasutamine vastavalt stage'i laiuse muutmisele
        primaryStage.widthProperty().addListener(((observable, oldValue, newValue) -> {
            sisend.setPrefWidth(newValue.doubleValue() - 150);
        }));


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
            // Kasutame setResizable(false), et poleks võimalik muudes kohtades ekraani suurust muuta
            primaryStage.setResizable(false);
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

        //raamatu lisamine
        Text RLvali = new Text("Millisesse nimekirja soovite raamatu lisada?");
        Button lisanHetkel = new Button("Hetkel loen");
        Button lisanLoetud = new Button("Loetud raamatud");
        Button lisanTahan = new Button("Tahan lugeda");

        Button[] nimekirjaNupud = {lisanHetkel, lisanLoetud, lisanTahan};

        //Nuppude vormindus
        for (Button nupp : nimekirjaNupud) {
            nupp.setPrefWidth(200);
            nupp.setPrefHeight(30);
        }

        VBox RLVbox = new VBox();
        RLVbox.getChildren().addAll(RLvali,lisanHetkel,lisanLoetud,lisanTahan);

        //VBoxi vormindus
        RLVbox.setSpacing(10);
        RLVbox.setPadding(new Insets(30, 30, 30, 30));

        Scene LRstseen = new Scene(RLVbox);


        //Hetkel loetavate raamatute tekstid
        TextField tfLisaHetkelPealkiri = new TextField();
        TextField tfLisaHetkelAutor = new TextField();
        TextField tfLisaHetkelLehekülgi = new TextField();
        TextField tfLoetudLehekülgi = new TextField();

        //Hetkel loetavad raamatud
        VBox LHVbox = new VBox();
        LHVbox.setSpacing(10);
        LHVbox.setPadding(new Insets(30, 30, 30, 30));
        Button kinnitaLisanH = new Button("Lisa");
        LHVbox.getChildren().addAll(new Text("Pealkiri: "),tfLisaHetkelPealkiri,new Text("Autor: "),tfLisaHetkelAutor,
                new Text("Lehekülgi: "),tfLisaHetkelLehekülgi,new Text("Loetud lehekülgi: "),tfLoetudLehekülgi,kinnitaLisanH);
        Scene lisanHstseen = new Scene(LHVbox);

        //Loetud raamatute tekstid
        TextField tfLisaLoetudPealkiri = new TextField();
        TextField tfLisaLoetudAutor = new TextField();
        TextField tfLisaLoetudLehekülgi = new TextField();
        Text sHinnang = new Text("Hinnang:");
        TextField tfHinnang = new TextField();

        //Loetud raamatud
        VBox LLVbox = new VBox();
        LLVbox.setSpacing(10);
        LLVbox.setPadding(new Insets(30, 30, 30, 30));
        Button kinnitaLisanL = new Button("Lisa");
        LLVbox.getChildren().addAll(new Text("Pealkiri: "),tfLisaLoetudPealkiri,new Text("Autor: "),tfLisaLoetudAutor,
                new Text("Lehekülgi: "),tfLisaLoetudLehekülgi,sHinnang,tfHinnang,kinnitaLisanL);
        Scene lisanLstseen = new Scene(LLVbox);

        //Tahan lugeda tekstid
        Text sPealkiri = new Text("Pealkiri:");
        TextField tfPealkiri = new TextField();
        Text sAutor = new Text("Autor:");
        TextField tfAutor = new TextField();
        Text sLehekülgi = new Text("Lehekülgi:");
        TextField tfLehekülgi = new TextField();

        //Tahan lugeda raamatud
        VBox LTVbox = new VBox();
        LTVbox.setSpacing(10);
        LTVbox.setPadding(new Insets(30, 30, 30, 30));
        Button kinnitaLisanT = new Button("Lisa");
        LTVbox.getChildren().addAll(sPealkiri,tfPealkiri,sAutor,tfAutor,sLehekülgi,tfLehekülgi,kinnitaLisanT);
        Scene lisanTstseen = new Scene(LTVbox);

        //nuppude funktsionaalsused
        lisaRaamat.setOnMouseClicked(e -> {
            primaryStage.setScene(LRstseen);
            primaryStage.setWidth(LRstseen.getWidth());
            primaryStage.setHeight(LRstseen.getHeight());
        });
        lisanHetkel.setOnMouseClicked(e -> {
            primaryStage.setScene(lisanHstseen);
            primaryStage.setWidth(lisanHstseen.getWidth());
            primaryStage.setHeight(lisanHstseen.getHeight());
        });
        lisanLoetud.setOnMouseClicked(e -> {
            primaryStage.setScene(lisanLstseen);
            primaryStage.setWidth(lisanLstseen.getWidth());
            primaryStage.setHeight(lisanLstseen.getHeight());
        });
        lisanTahan.setOnMouseClicked(e -> {
            primaryStage.setScene(lisanTstseen);
            primaryStage.setWidth(lisanTstseen.getWidth());
            primaryStage.setHeight(lisanTstseen.getHeight());
        });

        //Tahan lugeda
        kinnitaLisanT.setOnMouseClicked(e-> {
            try {
                String pealkiri = tfPealkiri.getText();
                String autor = tfAutor.getText();
                int lehekülgi = Integer.parseInt(tfLehekülgi.getText());
                tahanLugeda.lisaRaamat(pealkiri,autor,lehekülgi);
                teade("Raamat \"" + pealkiri + "\" on lisatud nimekirja 'Tahan lugeda'", primaryStage, scene);
            }
            catch (NumberFormatException erind){
                Alert valeSisend = new Alert(Alert.AlertType.ERROR);
                valeSisend.setContentText("Lehekülgede arv peab olema täisarv!");
                valeSisend.show();
            }
        });
        //lisa loetud raamatute nimekirja
        kinnitaLisanL.setOnMouseClicked(e -> {
            try {
                String pealkiri = tfLisaLoetudPealkiri.getText();
                String autor = tfLisaLoetudAutor.getText();
                int lehekülgi = Integer.parseInt(tfLisaLoetudLehekülgi.getText());
                int hinnang = Integer.parseInt(tfHinnang.getText());
                if (hinnang < 1 || hinnang > 5) throw new FormaadiErind("");
                loetudRaamatud.lisaRaamat(pealkiri,autor,lehekülgi,hinnang);
                teade("Raamat \"" + pealkiri + "\" on lisatud nimekirja 'Loetud raamatud'", primaryStage, scene);
            }
            catch (NumberFormatException erind){
                Alert valeSisend = new Alert(Alert.AlertType.ERROR);
                valeSisend.setContentText("Lehekülgede arv ja hinnang peavad olema täisarvud!");
                valeSisend.show();
            }
            catch (RuntimeException erind){
                Alert valeSisend = new Alert(Alert.AlertType.ERROR);
                valeSisend.setContentText("Hinnang peab olema täisarv 1-st 5-ni!");
                valeSisend.show();
            }
        });






        //Lugesin lõpuni tseen
        Group LLgrupp = new Group();
        VBox LLVBox = new VBox();

        //Vormindus
        LLVBox.setPadding(new Insets(30, 30, 30, 30));
        LLVBox.setSpacing(10);

        //hinnangu andmise ekraan
        /*
        Group hinnangGrupp = new Group();
        VBox HGVbox = new VBox();
        Text sisestaHinnang = new Text("Sisestage hinnang loetud raamatule:");
        TextField Hsisend = new TextField();
        Button Hkinnita = new Button("Sisesta");
        HGVbox.getChildren().addAll(sisestaHinnang,Hsisend, Hkinnita);
        hinnangGrupp.getChildren().add(SLVbox);
        Scene hinnanguAndmine = new Scene(SLgrupp, 500, 500);

         */


        //lugesinLõpuni nupu funktsionaalsus
        lugesinLõpuni.setOnMouseClicked(e -> {
            LLVBox.getChildren().add(new Text("Millise raamatu lõpetasid?"));
            for (Raamat loetavRaamat : hetkelLoen.raamatud) {
                Button loetavaRaamatuNupp = new Button("\"" + loetavRaamat.getPealkiri() + "\" - " + loetavRaamat.getAutor());
                loetavaRaamatuNupp.setOnMouseClicked(event -> {
                    String pealkiri = loetavRaamat.getPealkiri();
                    lugesinLõpuni(pealkiri, hetkelLoen, loetudRaamatud);
                    teade("Raamat on lisatud õigesse nimekrija", primaryStage, scene);
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

        //Vali juhuslik raamat
        Group JRgrupp = new Group();
        VBox JRVBox = new VBox();
        Text valiNimekiri = new Text("Millisest nimekirjast soovite raamatut valida?");
        Button valiHetkel = new Button("Hetkel loen");
        Button valiLoetud = new Button("Loetud raamatud");
        Button valiTahan = new Button("Tahan lugeda");
        Text juhuslik = new Text("");
        JRVBox.getChildren().addAll(valiNimekiri, valiHetkel, valiLoetud, valiTahan, juhuslik);
        valiHetkel.setOnMouseClicked(e -> {
            juhuslik.setText("Juhuslikult valitud raamat on \"" + hetkelLoen.valiSuvaline().getPealkiri() + "\"" );
        });
        valiLoetud.setOnMouseClicked(e -> {
            juhuslik.setText("Juhuslikult valitud raamat on \"" + loetudRaamatud.valiSuvaline().getPealkiri() + "\"" );
        });
        valiTahan.setOnMouseClicked(e -> {
            juhuslik.setText("Juhuslikult valitud raamat on \"" + tahanLugeda.valiSuvaline().getPealkiri() + "\"" );
        });
        JRgrupp.getChildren().add(JRVBox);
        Scene JRstseen = new Scene(JRgrupp);
        valiJuhuslik.setOnMouseClicked(e -> primaryStage.setScene(JRstseen));

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

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            try {
                loetudRaamatud.kirjutaFaili("loetud.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                hetkelLoen.kirjutaFaili("hetkel.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                tahanLugeda.kirjutaFaili("tahan.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        primaryStage.setScene(algus);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(300);
        primaryStage.show();


    }

    public void lugesinLõpuni(String pealkiri, Nimekiri hetkelLoen, Nimekiri loetudRaamatud){
        for (Raamat raamat : hetkelLoen.raamatud) {
            if (raamat.getPealkiri().equals(pealkiri)) {
                hetkelLoen.eemaldaRaamat(raamat);
                String hinnang = "-1";

                raamat.setHinnang(Integer.parseInt(hinnang));
                loetudRaamatud.lisaRaamat(raamat);
                break;
            }
        }
    }

    public void teade(String sõnum, Stage primaryStage, Scene stseen){
        Alert kinnitus = new Alert(Alert.AlertType.CONFIRMATION);
        kinnitus.setContentText(sõnum);
        kinnitus.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                primaryStage.setScene(stseen);
                //primaryStage.setMinWidth(stseen.getWidth());
                //primaryStage.setMinHeight(stseen.getHeight());
                primaryStage.setWidth(260);
                primaryStage.setHeight(350);
                primaryStage.setResizable(false);

            }
        });
    }

}
