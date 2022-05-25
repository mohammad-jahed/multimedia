
package amedia;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;


public class FXMLDocumentController implements Initializable {
    
    
    private MediaPlayer mediaplayer;
   
    
    @FXML
    private MediaView mediaview;
    
    @FXML
    private Slider slider;
    
    @FXML
    private Slider seekslider;
    
    private String filepath;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       FileChooser filechooser=new FileChooser();
       FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("select a file (*.mp4)", "*.mp4");
       filechooser.getExtensionFilters().add(filter);
       File file=filechooser.showOpenDialog(null);
       filepath=file.toURI().toString();
       
     if(filepath != null) { 
       Media media = new Media(filepath);
       mediaplayer = new MediaPlayer(media);
       mediaview.setMediaPlayer(mediaplayer);
       DoubleProperty width =mediaview.fitWidthProperty();
       DoubleProperty hight = mediaview.fitHeightProperty();
       width.bind(Bindings.selectDouble(mediaview.sceneProperty(), "width"));
       hight.bind(Bindings.selectDouble(mediaview.sceneProperty(), "hight"));
       
       slider.setValue(mediaplayer.getVolume() *100);
       slider.valueProperty().addListener(new InvalidationListener(){
       

           @Override
           public void invalidated(Observable observable) {
               mediaplayer.setVolume(slider.getValue()/100);
           }
       
       });
       
       mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
           @Override
           public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
               
               seekslider.setValue(newValue.toSeconds());
           }
       });
       
       seekslider.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               mediaplayer.seek(Duration.seconds(seekslider.getValue()));
           }
       });
       
       
       mediaplayer.play();
     }
    }
    
    
    @FXML
    private void playvideo(ActionEvent event){
        mediaplayer.play();
        mediaplayer.setRate(1);
    }
    
    @FXML
    private void pausevideo(ActionEvent event){
        mediaplayer.pause();
    }
    @FXML
    private void stopvideo(ActionEvent event){
        mediaplayer.stop();
    }
    @FXML
    private void fastvideo(ActionEvent event){
        mediaplayer.setRate(1.5);
    }
    @FXML
    private void fastervideo(ActionEvent event){
        mediaplayer.setRate(2);
    }
    @FXML
    private void slowvideo(ActionEvent event){
        mediaplayer.setRate(.75);
    }
    @FXML
    private void slowervideo(ActionEvent event){
        mediaplayer.setRate(.5);
    }
    @FXML
    private void exitvideo(ActionEvent event){
        System.exit(0);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
