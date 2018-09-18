/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takip_sistemi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import jdk.nashorn.internal.parser.JSONParser;
import org.controlsfx.control.Notifications;

  
public class LoginController implements Initializable {
    
    private Image msjimg_Hata = new Image(getClass().getResource("Images/carpı.png").toExternalForm());
    private Image Login_Icon = new Image(getClass().getResource("Images/icon3.png").toExternalForm());
   @FXML
   private Label label,lbl_Durum1,lbl_Durum2;   
   @FXML
   private JFXButton btn_Eposta_ile,btn_Kul_Ad_ile,btn_exit,btn_Giris_KulAd,btn_Giris_Eposta;
   @FXML
   private AnchorPane pnl_Eposta_Grs,pnl_Kul_Ad_Grs;
   @FXML
   private JFXTextField txt_Kul_Ad,txt_Eposta;
   @FXML
   private JFXPasswordField txt_Kul_Pass_KulAd,txt_Kul_Pass_Eposta;
   
   
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
       
        if (event.getSource()==btn_Kul_Ad_ile) {
            pnl_Kul_Ad_Grs.toFront();
        }
        else if(event.getSource()==btn_Eposta_ile){
            pnl_Eposta_Grs.toFront();
        }  
        else if (event.getSource()==btn_exit) {
            Exit();
        }
        else if (event.getSource()==btn_Giris_KulAd) {            
          Login_Kul_Ad();                     
        }
        else if (event.getSource()==btn_Giris_Eposta) {           
           Login_Eposta();
        }
    }
    public static RentUsersModels rt;
    public void Login_Kul_Ad() throws IOException{
            try {
       
               ArrayList<String> ad_list = GetUsersControl.rentusersmodels.getKul_Ad();
               ArrayList<String> pass_list1 = GetUsersControl.rentusersmodels.getKul_Pass();

               String kull_Ad=txt_Kul_Ad.getText().toString();
               String kull_Pass1=txt_Kul_Pass_KulAd.getText().toString();

               // System.out.println(GetUsersControl.rentusersmodels.getAd());

                if (ad_list.contains(kull_Ad) && pass_list1.contains(kull_Pass1) )
                {          
                    rt= new RentUsersModels();
                    rt.setKullaniciadi(kull_Ad);
                    
                    lbl_Durum1.setText("Giriş Başarılı.");

                    Anasayfa();
                    

                    txt_Kul_Ad.setText("");
                    txt_Kul_Pass_KulAd.setText("");
                    lbl_Durum1.setText("");
                    Exit();

                }   
                else
                {
                   lbl_Durum1.setText("Giriş Başarısız !");
                }
                 
            } catch (Exception e) {
           
                  Notifications.create()
                            .title("Hata Oluştu")
                            .text(" Lütfen internet bağlantısını kontrol edin ve ardından programı tekrar çalıştırın\n Eğer farklı bir hata varsa teknik destek için\n (by.dvlpr@gmail.com) adresiyle iletişime geçin.")
                            .graphic(new ImageView(msjimg_Hata))
                            .hideAfter(Duration.seconds(7))
                            .position(Pos.CENTER)
                            .show();
                  e.printStackTrace();
                
        }
    }// END Login_Kul_Ad()
    
     public void Login_Eposta() throws IOException{
           ArrayList<String> eposta_list = GetUsersControl.rentusersmodels.getEposta();
           ArrayList<String> pass_list2 = GetUsersControl.rentusersmodels.getKul_Pass();
          
           String kull_Eposta=txt_Eposta.getText().toString();
           String kull_Pass2=txt_Kul_Pass_Eposta.getText().toString();
           
            if (eposta_list.contains(kull_Eposta) && pass_list2.contains(kull_Pass2)) {
                lbl_Durum2.setText("Giriş Başarılı.");
                
                Anasayfa();
                
                txt_Eposta.setText("");
                txt_Kul_Pass_Eposta.setText("");
                lbl_Durum2.setText("");
                Exit();
            }
            else
            {
                lbl_Durum2.setText("Giriş Başarısız.");
            }
     }//END Login_Eposta()
       
    public void Anasayfa() throws IOException{
        
        FXMLLoader fxmlUserPage = new FXMLLoader(getClass().getResource("FXML_Anasayfa.fxml"));
        Parent root1 =(Parent)fxmlUserPage.load();
        Stage stage = new Stage();
        stage.getIcons().add(Login_Icon);
        stage.initStyle(StageStyle.TRANSPARENT);                
        stage.setScene(new Scene(root1));
        stage.setMaximized(true);
        stage.show();
    }// END Anasayfa()
    
    public void Exit(){
    Stage stage = (Stage) btn_exit.getScene().getWindow();
            stage.close();
    }// END Exit()
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    
}
