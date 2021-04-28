package omt.webbrowser20210427;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class PrimaryController {
    private final static String HOME_URL = "https://bing.com";
    @FXML
    private TextField txtUrl;

    @FXML
    private Button btnGo;
    
    @FXML
    private WebView webView1;
    
    private WebEngine webEngine;

    @FXML
    void initialize() {
        System.out.println("In init...");
        webEngine = webView1.getEngine();
        webEngine.load(HOME_URL);
        
        webEngine.locationProperty().addListener(location -> {
            System.out.println("Location changed: " + location);
            txtUrl.setText(webEngine.getLocation());
        });
    }
    
    @FXML
    void handleGoBtn(ActionEvent event) {
        String url = txtUrl.getText().trim();
        System.out.println("URL is " + url);
        loadUrl(url);
    }
    
    @FXML
    void handleNextBtn(ActionEvent event) {
        try {
            webEngine.getHistory().go(1);
        }
        catch (Exception ex) {
            System.out.println("Browser cannot go forward. Err: " + ex.getMessage());
        }
    }

    @FXML
    void handlePrevBtn(ActionEvent event) {
        try {
            webEngine.getHistory().go(-1);
        }
        catch (Exception ex) {
            System.out.println("Browser cannot go to the previous page. Err: " + ex.getMessage());
        }
    }
    
    @FXML
    void handleImageClick(MouseEvent event) {
        // Here, whenever the logo clicked we would like to go to the 
        // home URL (this could be anything)
        loadUrl(HOME_URL);
    }
    
    
    //--- Private Methods ---
    
    /**
     * This will load the provided URL to the web engine therefore 
     * the WebView will update with the new site.
     * @param url 
     */
    private void loadUrl(String url) {
        if (url.equals("")) {
            return;
        }
        
//        if (!url.contains("http://") && !url.contains("https://")) {
//            url = "http://" + url;
//        }
        
        // We could use regular expression as well!
        if (!url.matches("(http|https)://.*")) {
            url = "http://" + url;
        }
        
        System.out.println("In loadUrl() URL is " + url);
        
        webEngine.load(url);
    }
}
