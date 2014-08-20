/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barcodesample.main;

import barcodesample.entity.BoomKunana;
import barcodesample.service.EntityService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Hadouken
 */
public class MainPresenter implements Initializable {
    @FXML
    private ImageView barcodeImage;
    @FXML
    private Button generateButton;
    @FXML
    private TextField textf;
    @FXML
    private Button findButton;
    @FXML
    private Label employeeNameLabel;
    @FXML
    private Label barcodeMessageLabel;
    @FXML
    private Label errorLabel,challenger;
    @FXML
    private AnchorPane contentPane;

    ObjectProperty<BoomKunana>  bukanana;
    StringProperty challengeMe;
    
    @Inject
    EntityService es;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.bukanana = new SimpleObjectProperty<>(new BoomKunana());
        this.challengeMe = new SimpleStringProperty("!");
        StringBuilder sb = new StringBuilder();
        contentPane.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {                       
            if(event.getCode() == KeyCode.ALPHANUMERIC){
                sb.append(event.getText()); 
                System.out.println(event.getText());
                event.consume();
            }
        });     
        
        contentPane.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            sb.append(event.getText());            
            if(event.getCode() == KeyCode.ENTER){
                challenger.setText(sb.toString());                
                sb.setLength(0);
                event.consume();
            }
        });           

        textf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!newValue.isEmpty()){
                errorLabel.setText("");
            }else{
                errorLabel.setText("Enter something bitch please");
            }
        });

    }    

    @FXML
    private void generate(ActionEvent event){
        if(!textf.getText().isEmpty()){
            this.bukanana.get().setId(randomString(9));
            this.bukanana.get().setEmployeeName(textf.textProperty().get());
            es.save(bukanana.get());
            fillLabels(bukanana.get().getId(), bukanana.get().getEmployeeName());
            putToImageView(bukanana.get().getId());            
        }
    }
    
    private void fillLabels(String id, String employeeName){
        employeeNameLabel.setText(employeeName);
        barcodeMessageLabel.setText(id);
    }

    private void putToImageView(String text){
        BitMatrix bitMatrix;
        BufferedImage bf;
        WritableImage wr;
        try {
            bitMatrix = new Code128Writer().encode(text, BarcodeFormat.CODE_128, 300, 120);
            MatrixToImageWriter.writeToStream(bitMatrix, "jpeg", new FileOutputStream(new File("w.jpg")));
            bf = MatrixToImageWriter.toBufferedImage(bitMatrix);
            wr = new WritableImage(bf.getWidth(), bf.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bf.getWidth(); x++) {
                for (int y = 0; y < bf.getHeight(); y++) {
                    pw.setArgb(x, y, bf.getRGB(x, y));
                }
            }
            barcodeImage.setImage(wr);
        } catch (WriterException | IOException ex) {
            Logger.getLogger(MainPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void findBarcode(ActionEvent event) {
        if(!textf.getText().isEmpty()){
            try {
                this.bukanana.set(es.find(textf.textProperty().get()));
                fillLabels(bukanana.get().getId(), bukanana.get().getEmployeeName());
                putToImageView(bukanana.get().getId());
            } catch (Exception e) {
                System.out.println("Entity SEOD TON TSIXE");
            }
            
        }
    }
    
    public String randomString(int length){
       Random random = new Random();
       String code = "123456789";
       StringBuilder sb = new StringBuilder( length );
       for( int i = 0; i < length; i++ ) 
          sb.append( code.charAt( random.nextInt(code.length()) ) );
       return sb.toString();
    }
    
}
