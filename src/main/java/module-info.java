module com.example.raamatud_kasutajaliides {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.raamatud_kasutajaliides to javafx.fxml;
    exports com.example.raamatud_kasutajaliides;
}