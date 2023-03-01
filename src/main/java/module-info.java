module mastermindgui {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens mastermindgui to javafx.fxml;
    exports mastermindgui;
}
