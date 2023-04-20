package com.example.raamatud_kasutajaliides;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
         Text sisestaNimi = new Text(50, 50, "Sisestage oma nimi:");
         TextField sisend = new TextField();
         Button kinnita = new Button("Logi sisse");
         SLgrupp.getChildren().add(sisestaNimi);
         SLgrupp.getChildren().add(sisend);
         SLgrupp.getChildren().add(kinnita);
         Scene algus = new Scene(SLgrupp, 500, 500);





        //peaekraan
        Group grupp = new Group();

        GridPane ruudustik = new GridPane();
        ruudustik.setVgap(3);
        ruudustik.setHgap(3);
        Button loetud = new Button("Loetud raamatute nimekiri");
        Button loen = new Button("Lugemisel raamatute nimekiri");
        ruudustik.add(loen, 2, 2);
        ruudustik.add(loetud, 2, 3);
        grupp.getChildren().addAll(ruudustik);
        Scene scene = new Scene(grupp, 500, 500);

        //tegevused
        kinnita.setOnMouseClicked(e -> primaryStage.setScene(scene));
        primaryStage.setScene(algus);
        primaryStage.show();
    }
}
