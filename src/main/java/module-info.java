module mastermindgui {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.base;


    opens mastermindgui to javafx.fxml;
    exports mastermindgui;
}
