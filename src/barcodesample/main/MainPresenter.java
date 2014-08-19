/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barcodesample.main;

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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;





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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }    

    @FXML
    private void generate(ActionEvent event) throws WriterException, IOException{
    String text = "1111111111111";
    int width = 1000;
    int height = 600;
    String imageFormat = "jpeg"; // could be "gif", "tiff", "jpeg" 
    BitMatrix bitMatrix = new Code128Writer().encode(text, BarcodeFormat.CODE_128, width, height);
    MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, new FileOutputStream(new File("w.jpg")));
    BufferedImage bf = MatrixToImageWriter.toBufferedImage(bitMatrix);
    WritableImage wr = null;
        if (bf != null) {
            wr = new WritableImage(bf.getWidth(), bf.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bf.getWidth(); x++) {
                for (int y = 0; y < bf.getHeight(); y++) {
                    pw.setArgb(x, y, bf.getRGB(x, y));
                }
            }
        }
    barcodeImage.setImage(wr);
    }
    
    private void saveToDB(){
        
    }
}
