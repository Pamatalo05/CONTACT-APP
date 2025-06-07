module com.espol.group04.grupo_04 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.espol.group04.grupo_04 to javafx.fxml;
    exports com.espol.group04.grupo_04;
}
