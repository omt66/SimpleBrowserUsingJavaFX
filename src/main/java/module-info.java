module omt.webbrowser20210427 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web; 

    opens omt.webbrowser20210427 to javafx.fxml;
    exports omt.webbrowser20210427;
}
