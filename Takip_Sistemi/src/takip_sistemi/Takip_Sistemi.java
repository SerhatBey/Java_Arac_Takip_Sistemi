
package takip_sistemi;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.PrivateKey;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONException;


public class Takip_Sistemi extends Application {
private javafx.scene.image.Image Login_Icon = new javafx.scene.image.Image(getClass().getResource("Images/icon3.png").toExternalForm());
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);       
        scene.setFill(Color.TRANSPARENT);       
        stage.getIcons().add(Login_Icon);
        stage.setScene(scene);
        stage.show();
         
    }

    public static void main(String[] args) throws IOException, MalformedURLException, JSONException {
        GetUsersControl get_users_control=new GetUsersControl();
        LocationGetData location_get_data= new LocationGetData();
        location_get_data.GetLocData();
        get_users_control.UserControl();
        
        OneByOneGetData getdata= new OneByOneGetData();
            getdata.start();

        
        launch(args);
    }
    
}
