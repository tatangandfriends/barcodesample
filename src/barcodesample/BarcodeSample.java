/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barcodesample;

import barcodesample.main.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hadouken
 */
public class BarcodeSample extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        MainView mainView = new MainView();                
        Parent root = mainView.getView();        
        Scene scene = new Scene(root);
        stage.setTitle("Barcode barcode barcode barcode");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
