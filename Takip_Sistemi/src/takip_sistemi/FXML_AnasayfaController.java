
package takip_sistemi;

import com.jfoenix.controls.JFXButton; 
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import static com.lynden.gmapsfx.javascript.JavascriptRuntime.engine;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsGeocodedWaypoint;
import com.lynden.gmapsfx.service.directions.DirectionsLeg;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsRoute;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.DirectionsSteps;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.shapes.ArcBuilder;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import java.awt.Graphics;
import javafx.scene.image.Image ;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.omg.PortableInterceptor.LOCATION_FORWARD;



public class FXML_AnasayfaController extends Thread implements Initializable, MapComponentInitializedListener,DirectionsServiceCallback{

    
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    protected DirectionsRenderer directionsRenderer = null;
    private DecimalFormat formatter = new DecimalFormat("###.00000");
    GoogleMap map=null;
    private int zoom = 10;
    private Connection con=null;
    private Image background = null;
    private Image msjimg_Bas = new Image(getClass().getResource("Images/ysltk.png").toExternalForm());
    private Image msjimg_Uyr = new Image(getClass().getResource("Images/uyari.png").toExternalForm());
    private Image msjimg_Hata = new Image(getClass().getResource("Images/carpı.png").toExternalForm());
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private ObservableList<CarDataList> car_data_list;
    private ObservableList<UserDataList> user_data_list;
    private CategoryAxis xAxis = null;        
    private NumberAxis yAxis = null; 
      
    
    @FXML
    private Text Lbl_Home_Username,LblLatUst,LblLonUst,txt_data1,txt_Arc_id,txt_ToplamAracSayısı,txt_users_id;
    @FXML
    private JFXTextField txtBitis,txtBaslangc,txt_Arac_Plaka,txt_Arac_Renk,txt_Arac_Marka,txt_kul_ad,txt_kul_soyad,txt_kul_Tcno,txt_kul_Telefon; 
     @FXML
    private Label Lbl_Car1;   
    @FXML
    private JFXButton btn_Home_Kullanicilar,btn_Win_Min,btn_Exit,btn_Home_Araclar,btn_Home_Harita,btn_Home_Anasayfa,btn_Home_Destek,btn_Canli,btn_Stop,btn_Git,btn_AracKaydet,btn_AracGuncelle,btn_AracSil,btn_TümAracSil,btn_MailGönder,btn_Kul_Tüm_Sil;
    @FXML
    private AnchorPane pnl_home_anasayfa,pnl_Home_Harita,pnl_Home_Cars,pnl_Home_Kullanclar,pnl_Home_TeknikDestek;  
    @FXML
    private GoogleMapView mapView;
    @FXML
    private JFXComboBox<String> cmboBox_KazaDurumu,comboBox_kul_EhliyetTip,comboBox_kul_VerilecekArac,comboBox_kul_KiraldgGünSay; 
    @FXML
    private RadioButton rdyBtn_SfrArac,rdyBtn_2El;
    @FXML
    private JFXDatePicker datepicker_AracKytTrh,datePicker_kul_dogumTarihi;
    @FXML
    private TableView<CarDataList> Table_AracBilgileri;
    @FXML
    private TableView<UserDataList> TableKullaniciBilgileri; 
    @FXML
    private TableColumn<?, ?> TableClmn_Plaka,TableClmn_Renk,TableClmn_Marka,TableClmn_Durum,TableClmn_TopKm,TableClmn_KazaDurumu,TableClmn_AracKulMuSays,TableClmn_AracKytTrh,TableClmn_Arc_id;
    @FXML
    private PieChart PieChart_ArcKm;
    @FXML
    private AreaChart<?, ?> AreaChart_MusteriSayisiGrfk;
    @FXML
    private LineChart<?, ?> LineChart_AylikGelir;
    @FXML
    private CategoryAxis LineChart_x;
    @FXML
    private NumberAxis LineChart_y;
    @FXML
    private JFXTextArea txt_MailMesaj,txt_kul_kul_adress;
    @FXML
    private JFXButton btn_YeniKullaniciEkleKAYDET,btn_YeniKullaniciEkleGÜNCELLE,btn_YeniKullaniciEkleSİL;
    
     @FXML
    private TableColumn<?, ?> TableClmn_Kul_id,TableClmn_Kul_ad,TableClmn_Kul_soyad,TableClmn_Kul_tc,TableClmn_Kul_tlf,TableClmn_Kul_dgmtrh,TableClmn_Kul_adress,TableClmn_Kul_ehlyt,TableClmn_Kul_kiraarac,TableClmn_Kul_topgün;

   
    ObservableList<String> comboKazaDurumlist=FXCollections.observableArrayList(
               "Kazası Var","Kazası Yok"
    );
    ObservableList<String> comboEhliyetTiplist=FXCollections.observableArrayList(
               "B","BE","C1","C1E","C","CE","D1","D1E","D","DE","F","G"
    );
    ObservableList<String> comboAracTiplist=FXCollections.observableArrayList(
               "Bmw","Audi A3","Mercedes","Toyota","Nisan"
    );
    ObservableList<String> comboKiraGünlist=FXCollections.observableArrayList(
               "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"
    );

    @FXML
    private void handleAnasayfaButtonAction(ActionEvent event) throws IOException {
      
        if (event.getSource()==btn_Exit) {
            Stage stage = (Stage) btn_Exit.getScene().getWindow();
            stage.close();
        }
        else if (event.getSource()==btn_Win_Min) {
            Stage stage = (Stage) btn_Win_Min.getScene().getWindow();
            stage.setIconified(true);
        }
        else if (event.getSource()==btn_Home_Anasayfa) {
            pnl_home_anasayfa.toFront();
        }
        else if (event.getSource()==btn_Home_Harita) {
            pnl_Home_Harita.toFront();
            Stage stage = (Stage) btn_Home_Harita.getScene().getWindow();
            stage.setIconified(false);           
        }
        else if (event.getSource()==btn_Home_Araclar) {
            pnl_Home_Cars.toFront();
        }
        else if (event.getSource()==btn_Git) {
         DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
         directionsRenderer = new DirectionsRenderer(true, mapView.getMap(), directionsPane);
         directionsService.getRoute(request,this, directionsRenderer);
        }
        else if (event.getSource()==btn_Canli) { //YENİLE
//           mapInitialized();
        }
        else if (event.getSource()==btn_AracKaydet) {
            try {
             
                String plaka,renk,marka,arc_drm=null,kaza_drm=null,arc_kyt=null;

                 plaka=txt_Arac_Plaka.getText();
                 renk=txt_Arac_Renk.getText();
                 marka=txt_Arac_Marka.getText();

                if (rdyBtn_2El.isSelected()) {
                    arc_drm="2.El";
                }
                else if (rdyBtn_SfrArac.isSelected()) {
                    arc_drm="Sıfır";
                }
                kaza_drm=cmboBox_KazaDurumu.getValue();
                arc_kyt=datepicker_AracKytTrh.getValue().toString();

                if (plaka.isEmpty() && renk.isEmpty() && marka.isEmpty()) {

                    System.out.println("SORUN YOK-----------------------");

                    Notifications.create()
                            .title("ALAN DURUMU")
                            .text("Lütfen Tüm Alanları Doldurun.")
                            .graphic(new ImageView(msjimg_Uyr))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_CENTER)
                             .show();
                }
                else{
                    Ekle(plaka, renk, marka, arc_drm, kaza_drm, arc_kyt);
                    LoadCarsData();
                    TextTemizle();
                    DataTableCount(); // Kayıt sayısı kontrolü
                }
               
            } catch (Exception e) {
                Notifications.create()
                            .title("ALAN DURUMU")
                            .text("Lütfen Tüm Alanları Doldurun.")
                            .graphic(new ImageView(msjimg_Uyr))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_CENTER)
                            .show();
            }
        }
        else if (event.getSource()==btn_AracSil) {
            AracDeletData();
            DataTableCount(); // Kayıt sayısı kontrolü
        }
        else if (event.getSource()==btn_AracGuncelle) {
            AracUpdateDate();
            DataTableCount(); // Kayıt sayısı kontrolü
        }
        else if (event.getSource()==btn_TümAracSil) {
            AracALLDeletData();
            DataTableCount(); // Kayıt sayısı kontrolü
            LoadCarsData();
            TextTemizle();
        }
        
        else if (event.getSource()==btn_Home_Kullanicilar) {
            pnl_Home_Kullanclar.toFront();
        }
        else if (event.getSource()==btn_Home_Destek) {
            pnl_Home_TeknikDestek.toFront();
        }
        else if (event.getSource()==btn_MailGönder) {
            MailGönder();
        }
    }
    
    private String kulAd,kulSoyad,kulTc,kulTlf,kulDogmTrh,kulEhliyet,kulAdress,kulVerilecekArac,kulKiralayacagiGünSay;
    private int kul_id;
    KullaniciEklemeİşlemleri kul=new KullaniciEklemeİşlemleri();
    
    @FXML
    private void yeniKullaniciEkleme(ActionEvent event){
        
       if (event.getSource()==btn_YeniKullaniciEkleKAYDET) {
            if (txt_kul_ad.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "Lütfen Tüm Boş Alanları Doldurunuz.", "Uyarı", -1, 1);
            }
            else if(txt_kul_soyad.getText().isEmpty()){
                JOptionPane.showConfirmDialog(null, "Lütfen Tüm Boş Alanları Doldurunuz.", "Uyarı", -1, 1);
            }
            else if(txt_kul_Tcno.getText().isEmpty()){
                JOptionPane.showConfirmDialog(null, "Lütfen Tüm Boş Alanları Doldurunuz.", "Uyarı", -1, 1);
            }
            else{
                   kulAd=txt_kul_ad.getText();
                   kulSoyad=txt_kul_soyad.getText();
                   kulTc=txt_kul_Tcno.getText();
                   kulTlf=txt_kul_Telefon.getText();
                   kulDogmTrh=datePicker_kul_dogumTarihi.getValue().toString();
                   kulEhliyet=comboBox_kul_EhliyetTip.getValue();
                   kulAdress=txt_kul_kul_adress.getText();
                   kulVerilecekArac=comboBox_kul_VerilecekArac.getValue();
                   kulKiralayacagiGünSay=comboBox_kul_KiraldgGünSay.getValue();
                 
                   kul.KulaniciEkle(kulAd, kulSoyad, kulTc, kulTlf, kulDogmTrh, kulEhliyet, kulAdress, kulVerilecekArac, kulKiralayacagiGünSay,con);

                   TextTemizleKulSayifasi();
                   LoadUsersData();
                }
       }
       else if (event.getSource()==btn_YeniKullaniciEkleGÜNCELLE) {
           
               int yanit_update= JOptionPane.showConfirmDialog(null, "Kayıtlı kullanıcı bilgileri güncellensin mi ?", "DİKKAT !!!", 2, 2);
                if(yanit_update == JOptionPane.YES_OPTION){
                   kulAd=txt_kul_ad.getText();
                   kulSoyad=txt_kul_soyad.getText();
                   kulTc=txt_kul_Tcno.getText();
                   kulTlf=txt_kul_Telefon.getText();
                   kulDogmTrh=datePicker_kul_dogumTarihi.getValue().toString();
                   kulEhliyet=comboBox_kul_EhliyetTip.getValue();
                   kulAdress=txt_kul_kul_adress.getText();
                   kulVerilecekArac=comboBox_kul_VerilecekArac.getValue();
                   kulKiralayacagiGünSay=comboBox_kul_KiraldgGünSay.getValue();
                   kul_id=Integer.parseInt(txt_users_id.getText());

                   kul.KulaniciGüncelle(kul_id, kulAd, kulSoyad, kulTc, kulTlf, kulDogmTrh, kulEhliyet, kulAdress, kulVerilecekArac, kulKiralayacagiGünSay, con);
                   TextTemizleKulSayifasi();
                   LoadUsersData();   
        
           
           }
        
       }
       else if (event.getSource()==btn_YeniKullaniciEkleSİL) {
           if (txt_users_id.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "Seçilmiş Kullanıcı Yok", "BİLGİ", -1, 1);
           }else{
              int yanit2= JOptionPane.showConfirmDialog(null, "Kayıtlı aracı silmek istiyor musunuz?", "DİKKAT !!!", 2, 2);
               if(yanit2 == JOptionPane.YES_OPTION){
                   kul_id=Integer.parseInt(txt_users_id.getText());
                   kul.KulaniciSil(kul_id, con);
                   TextTemizleKulSayifasi();
                   LoadUsersData();
               }              
           }
       }
       else if (event.getSource()==btn_Kul_Tüm_Sil) {
            if (user_data_list.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "Kayıtlı Kullanıcı Yok", "BİLGİ", -1, 1);
            }else{
                int yanit2= JOptionPane.showConfirmDialog(null, "Kayıtlı Tüm Kullanıcıları Silmek İstiyor musunuz?", "DİKKAT !!!", 2, 2);
                if(yanit2 == JOptionPane.YES_OPTION){
                   kul.KulaniciTümüSil(con);
                   TextTemizleKulSayifasi();
                   LoadUsersData();
                }
            }
            
        }
    }
    
    private void TextTemizleKulSayifasi(){ //Kullanıcı Ekleme sayfası temizleme.
      txt_kul_ad.clear();
      txt_kul_soyad.clear();
      txt_kul_Tcno.clear();
      txt_kul_Telefon.clear();
      datePicker_kul_dogumTarihi.setValue(LocalDate.now());
      comboBox_kul_EhliyetTip.setValue("");
      txt_kul_kul_adress.clear();
      comboBox_kul_VerilecekArac.setValue("");
      comboBox_kul_KiraldgGünSay.setValue("");
      txt_users_id.setText(null);
    }
    
    private void SetCellTableUsers(){
    
       TableClmn_Kul_id.setCellValueFactory(new PropertyValueFactory<>("dt_kul_id"));
       TableClmn_Kul_ad.setCellValueFactory(new PropertyValueFactory<>("dt_kul_ad"));
       TableClmn_Kul_soyad.setCellValueFactory(new PropertyValueFactory<>("dt_kul_soyad"));
       TableClmn_Kul_tc.setCellValueFactory(new PropertyValueFactory<>("dt_kul_tc"));
       TableClmn_Kul_tlf.setCellValueFactory(new PropertyValueFactory<>("dt_kul_tlf"));
       TableClmn_Kul_dgmtrh.setCellValueFactory(new PropertyValueFactory<>("dt_kul_dgmtrh"));
       TableClmn_Kul_ehlyt.setCellValueFactory(new PropertyValueFactory<>("dt_kul_ehlyt"));
       TableClmn_Kul_adress.setCellValueFactory(new PropertyValueFactory<>("dt_kul_adress"));
       TableClmn_Kul_kiraarac.setCellValueFactory(new PropertyValueFactory<>("dt_kul_verarc"));
       TableClmn_Kul_topgün.setCellValueFactory(new PropertyValueFactory<>("dt_kul_günsay"));
    }
    
    private void SetCellTableCars(){
       TableClmn_Arc_id.setCellValueFactory(new PropertyValueFactory<>("dt_arcid"));
       TableClmn_Plaka.setCellValueFactory(new PropertyValueFactory<>("dt_plaka"));
       TableClmn_Renk.setCellValueFactory(new PropertyValueFactory<>("dt_renk"));
       TableClmn_Marka.setCellValueFactory(new PropertyValueFactory<>("dt_marka"));
       TableClmn_Durum.setCellValueFactory(new PropertyValueFactory<>("dt_arc_yeni_drm"));
       TableClmn_TopKm.setCellValueFactory(new PropertyValueFactory<>("dt_arc_topkm"));
       TableClmn_KazaDurumu.setCellValueFactory(new PropertyValueFactory<>("dt_arc_kazadurumu"));
       TableClmn_AracKulMuSays.setCellValueFactory(new PropertyValueFactory<>("dt_arc_kulsay"));
       TableClmn_AracKytTrh.setCellValueFactory(new PropertyValueFactory<>("dt_arc_kyttrh"));
    }
    
     private void LoadUsersData(){ // Dbden dataları çekmek için
        user_data_list.clear();
        try {
            pst=con.prepareStatement("Select * From tkp_kullanici_bilgileri_Table");
            rs=pst.executeQuery();
            while(rs.next()){
                user_data_list.add(new UserDataList(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXML_AnasayfaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableKullaniciBilgileri.setItems(user_data_list);
    }
    
    private void LoadCarsData(){ // Dbden dataları çekmek için
        car_data_list.clear();
        try {
            pst=con.prepareStatement("Select * From tkp_arclar_Table");
            rs=pst.executeQuery();
            while(rs.next()){
                car_data_list.add(new CarDataList(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXML_AnasayfaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Table_AracBilgileri.setItems(car_data_list);
    }
    
     private void CellDataUsersAttractText(){ //Tıklanma ile veriyi textlere çekmek için
         TableKullaniciBilgileri.setOnMouseClicked(new EventHandler<MouseEvent>(){
           
             @Override
               public void handle(MouseEvent event) {
                UserDataList cdusers=TableKullaniciBilgileri.getItems().get(TableKullaniciBilgileri.getSelectionModel().getSelectedIndex());
                txt_users_id.setText(Integer.toString(cdusers.getDt_kul_id()));
                txt_kul_ad.setText(cdusers.getDt_kul_ad());
                txt_kul_soyad.setText(cdusers.getDt_kul_soyad());
                txt_kul_Tcno.setText(cdusers.getDt_kul_tc());
                txt_kul_Telefon.setText(cdusers.getDt_kul_tlf());
                datePicker_kul_dogumTarihi.setValue(LocalDate.parse(cdusers.getDt_kul_dgmtrh()));
                comboBox_kul_EhliyetTip.setValue(cdusers.getDt_kul_ehlyt());
                txt_kul_kul_adress.setText(cdusers.getDt_kul_adress());
                comboBox_kul_VerilecekArac.setValue(cdusers.getDt_kul_verarc());
                comboBox_kul_KiraldgGünSay.setValue(cdusers.getDt_kul_günsay());
               }
           });
     }
   
    private void CellDataCarsAttractText(){ //Tıklanma ile veriyi textlere çekmek için
       Table_AracBilgileri.setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               CarDataList cdl=Table_AracBilgileri.getItems().get(Table_AracBilgileri.getSelectionModel().getSelectedIndex());
               txt_Arc_id.setText(Integer.toString(cdl.getDt_arcid()));
               txt_Arac_Plaka.setText(cdl.getDt_plaka());
               txt_Arac_Renk.setText(cdl.getDt_renk());
               txt_Arac_Marka.setText(cdl.getDt_marka());
               String drm_knt=cdl.getDt_arc_yeni_drm();    
                switch(drm_knt){
                case "2.El":
                      rdyBtn_2El.setSelected(true);
                      break;
                case "Sıfır":
                      rdyBtn_SfrArac.setSelected(true);
                      break;                                        
                }
               cmboBox_KazaDurumu.setValue(cdl.getDt_arc_kazadurumu());
               datepicker_AracKytTrh.setValue(LocalDate.parse(cdl.getDt_arc_kyttrh()));
                     
           }
       
       });
    }
     
    private void Ekle(String plaka,String renk,String marka,String arc_drm,String kaza_drm,String kyt_trh)  throws IOException{
    // Araçlar verilerini kaydetme için     
        String topyol="60",kul_mus="10";
        String sql="insert into tkp_arclar_Table(arc_plaka,arc_renk,arc_marka,arc_yeni_drm,arc_toplam_yol,arc_kaza_durumu,arc_kullan_mstr_sys,arc_kyt_trh) "
             + "Values ('"+plaka+"','"+renk+"','"+marka+"','"+arc_drm+"','"+topyol+"','"+kaza_drm+"','"+kul_mus+"','"+kyt_trh+"');";
        try {       
            Statement stm = (Statement) con.createStatement();         
            int executeUpdate = stm.executeUpdate(sql);
            
            if (executeUpdate==1) {
                
                 Notifications.create()
                        .title("KAYIT DURUMU")
                        .text("Kayıt Başarılı. Araç Eklendi.")
                        .graphic(new ImageView(msjimg_Bas))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_CENTER)
                         .show();
              
            }
            else{
             
                Notifications.create()
                        .title("KAYIT DURUMU")
                        .text("Kayıt Başarısız. Araç Eklenirken Hata Oluştu.")
                        .graphic(new ImageView(msjimg_Hata))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_CENTER)
                         .show();
            }
        } catch (Exception e) {
            Notifications.create()
                        .title("KAYIT DURUMU")
                        .text("Kayıt Başarısız. Araç Eklenirken Hata Oluştu..")
                        .graphic(new ImageView(msjimg_Hata))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_CENTER)
                         .show();
            e.printStackTrace();
        }
    }
    
    private void AracUpdateDate(){ //Araclar için günceleştirme.
   int yanit_update= JOptionPane.showConfirmDialog(null, "Kayıtlı aracı bilgileri güncellensin mi ?", "DİKKAT !!!", 2, 2);
        if(yanit_update == JOptionPane.YES_OPTION){
        
            String sql_update="Update tkp_arclar_Table Set arc_plaka=?, arc_renk = ? ,arc_marka = ? ,arc_yeni_drm = ? ,arc_toplam_yol = ? ,arc_kaza_durumu = ? ,arc_kullan_mstr_sys = ?, arc_kyt_trh = ? where arc_id = ? ";
            try {
                String plaka,renk,marka,arc_drm=null,kaza_drm=null,arc_kyt=null;
                     String aid=txt_Arc_id.getText();
                     plaka=txt_Arac_Plaka.getText();
                     renk=txt_Arac_Renk.getText();
                     marka=txt_Arac_Marka.getText();
                        if (rdyBtn_2El.isSelected()) {
                            arc_drm="2.El";
                        }
                        else if (rdyBtn_SfrArac.isSelected()) {
                            arc_drm="Sıfır";
                        }
                    kaza_drm=cmboBox_KazaDurumu.getValue();
                    arc_kyt=datepicker_AracKytTrh.getValue().toString();                
                    String topyol="100",kulsay="10";
    //                System.out.println(aid+" "+plaka+" "+renk+" "+marka+" "+arc_drm+" "+topyol+" "+kaza_drm+" "+kulsay+" "+arc_kyt);

                    pst=con.prepareStatement(sql_update);
                    pst.setString(1, plaka);
                    pst.setString(2, renk);
                    pst.setString(3, marka);
                    pst.setString(4, arc_drm);
                    pst.setString(5, topyol);
                    pst.setString(6, kaza_drm);
                    pst.setString(7, kulsay);
                    pst.setString(8, arc_kyt);
                    pst.setInt(9, Integer.parseInt(aid));

                    int upi= pst.executeUpdate();
                    System.out.println("upi = " + upi);

                    if (upi==1) {
                    Notifications.create()
                            .title("GÜNCELLEME DURUMU")
                            .text("Güncelleme Başarılı. Araç Bilgileri Güncellendi.")
                            .graphic(new ImageView(msjimg_Bas))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_CENTER)
                            .show();
                    LoadCarsData();
                    
            }
            } catch (Exception e) {
                Notifications.create()
                            .title("GÜNCELLEME DURUMU")
                            .text("Güncelleme Başarısız. Araç Bilgileri Güncellenirken Hata Oluştu.")
                            .graphic(new ImageView(msjimg_Hata))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_CENTER)
                             .show(); 
               e.printStackTrace();
            }
        }
    
    }
    
    private void AracDeletData(){
        if (txt_Arc_id.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "Seçilmiş Araç Yok", "BİLGİ", -1, 1);
        }
        else{
            int yanit2= JOptionPane.showConfirmDialog(null, "Kayıtlı aracı silmek istiyor musunuz?", "DİKKAT !!!", 2, 2);
            if(yanit2 == JOptionPane.YES_OPTION){
                String aid=txt_Arc_id.getText();
                String sql_delet="Delete from  tkp_arclar_Table Where arc_id = ? ";
                try {
                    pst=con.prepareStatement(sql_delet);
                    pst.setInt(1, Integer.parseInt(aid));
                     int upi= pst.executeUpdate();
                        System.out.println("upi = " + upi);               
                        if (upi==1) {
                        Notifications.create()
                                .title("SİLME DURUMU")
                                .text("Silme Başarılı. Araç Silindi.")
                                .graphic(new ImageView(msjimg_Bas))
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.BOTTOM_CENTER)
                                .show();
                        LoadCarsData();
                        TextTemizle();
                    }
                } catch (SQLException ex) {
                     Notifications.create()
                                .title("SİLME DURUMU")
                                .text("Silme Başarısız. Araç Silinirken Hata Oluştu.")
                                .graphic(new ImageView(msjimg_Hata))
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.BOTTOM_CENTER)
                                 .show(); 
                   ex.printStackTrace();
                }
                
           }
        }
    }
    
    private void AracALLDeletData(){//Tüm Araçları Silmek için
        if (car_data_list.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "Kayıtlı Araç Yok", "BİLGİ", -1, 1);
        }
        else{    
           int yanit2= JOptionPane.showConfirmDialog(null, "Kayıtlı tüm araçları silmek istiyor musunuz?", "DİKKAT !!!", 2, 2);
           if(yanit2 == JOptionPane.YES_OPTION){

                String sql_alldelet="Delete from  tkp_arclar_Table";       
                try {
                    pst=con.prepareStatement(sql_alldelet);           
                    int upi= pst.executeUpdate();
                    if (upi==1) {
                        Notifications.create()
                                .title("SİLME DURUMU")
                                .text("Silme Başarılı.Tüm Araçlar Silindi.")
                                .graphic(new ImageView(msjimg_Bas))
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.BOTTOM_CENTER)
                                .show();
                    }
                } catch (Exception e) {
                     Notifications.create()
                                .title("SİLME DURUMU")
                                .text("Silme Başarısız. Tüm Araçlar Silinirken Hata Oluştu.")
                                .graphic(new ImageView(msjimg_Hata))
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.BOTTOM_CENTER)
                                 .show(); 
                    e.printStackTrace();
                }

            }
        }
       
    }
    
    private void DataTableCount(){ //Kayıtlı araç sayısı için
      
        try {
                 
            Statement st = con.createStatement();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM tkp_arclar_Table");
            
            while (rs.next()) {
                String arc_count = rs.getString(1);
                System.out.println(arc_count);
                txt_ToplamAracSayısı.setText(arc_count);
            }
            
        } catch (SQLException ex) {
            Notifications.create()
                        .title("ARAÇ SAYISI DURUMU")
                        .text("Araç Sayısı Hesaplanırken Hata Oluştu.")
                        .graphic(new ImageView(msjimg_Hata))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_CENTER)
                         .show(); 
            ex.printStackTrace();
        }    
    }
    
    private void TextTemizle(){ //Textbox temizleme
        txt_Arac_Plaka.clear();
        txt_Arac_Renk.clear();
        txt_Arac_Marka.clear();
        cmboBox_KazaDurumu.setValue("");
        datepicker_AracKytTrh.setValue(LocalDate.now());
        txt_Arc_id.setText(null);
    }
    
    private void PieC_ArcKm(){ // Araç Km Bilgisi
      ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Mercedes",13),
                new PieChart.Data("Bmw",20),
                new PieChart.Data("Audi A3",5),
                new PieChart.Data("Toyota",30),
                new PieChart.Data("Nisan",25));
      PieChart_ArcKm.setData(pieChartData);
              
    }
    
    private void AreaChart_MusSayGrfk_SetData(){ //Araç Sayısı Aylık Grafik.
      XYChart.Series series= new XYChart.Series();
      series.setName("Müşteri Sayısı");
      series.getData().add(new XYChart.Data("1.hafta",20));
      series.getData().add(new XYChart.Data("2.hafta",5));
      series.getData().add(new XYChart.Data("3.hafta",10));
      series.getData().add(new XYChart.Data("4.hafta",30));
      AreaChart_MusteriSayisiGrfk.getData().add(series);
      
    }
    
    private void LineChrt_AylikGelir(){ // Aylık Gelir Grafiği
         XYChart.Series series_linechrt= new XYChart.Series();
          series_linechrt.setName("Aylık Gelir");
          series_linechrt.getData().add(new XYChart.Data("1.Ay",1200));
          series_linechrt.getData().add(new XYChart.Data("2.Ay",8000));
          series_linechrt.getData().add(new XYChart.Data("3.Ay",6000));
          series_linechrt.getData().add(new XYChart.Data("4.Ay",5500));
          series_linechrt.getData().add(new XYChart.Data("5.Ay",3500));
          series_linechrt.getData().add(new XYChart.Data("6.Ay",6455));
          series_linechrt.getData().add(new XYChart.Data("7.Ay",5482));
          series_linechrt.getData().add(new XYChart.Data("8.Ay",2155));
          series_linechrt.getData().add(new XYChart.Data("9.Ay",3245));
          series_linechrt.getData().add(new XYChart.Data("10.Ay",9554));
          series_linechrt.getData().add(new XYChart.Data("11.Ay",6211));
          series_linechrt.getData().add(new XYChart.Data("12.Ay",4154));
          LineChart_AylikGelir.getData().add(series_linechrt);
    }
    
    private void MailGönder(){ //Mail Gönderme İçin
        if (txt_MailMesaj.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "Lütfen Mesaj Alanını Doldurun.", "BİLGİ", -1, 1);
        }else{
            Properties ozellik = new Properties();
            ozellik.put("mail.transport.protocol", "smtp");
            ozellik.put("mail.smtp.host", "smtp.gmail.com");
            ozellik.put("mail.smtp.auth", "true");
            ozellik.put("mail.smtp.port","587");
            ozellik.put("mail.smtp.socketFactory.post", "587");
            ozellik.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            ozellik.put("mail.smtp.starttls.enable", "true");

            MailKullaniciBilgi kullaniciBilgi = new MailKullaniciBilgi();
            Session mailSession = Session.getDefaultInstance(ozellik,kullaniciBilgi);
            try{
                Transport transport = mailSession.getTransport();

                MimeMessage mail = new MimeMessage(mailSession);
                mail.setSubject("SER-CAR");
                mail.setContent(txt_MailMesaj.getText()+"\n\n\n ___SAR-CAR___", "text/plain; charset=utf-8");
                mail.setFrom(new InternetAddress("serhad12365@gmail.com"));
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress("by.dvlpr@gmail.com"));
                transport.connect();
                transport.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                transport.close();
                System.out.println("Mail Gönderme Başarılı");
                Notifications.create()
                            .title("MESAJ DURUMU")
                            .text("Mesajınız Gönderildi.")
                            .graphic(new ImageView(msjimg_Bas))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_CENTER)
                             .show();

                txt_MailMesaj.clear();

            }
            catch(Exception hata){
                System.out.println(hata.getMessage());
                System.out.println("Mail Gönderme Başarısız");
                Notifications.create()
                            .title("MESAJ DURUMU")
                            .text("Mesajınız Gönderilirken Hata Oluştu.")
                            .graphic(new ImageView(msjimg_Hata))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_CENTER)
                             .show();
            }
         }
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
       Lbl_Home_Username.setText(LoginController.rt.getKullaniciadi());
        
        cmboBox_KazaDurumu.setItems(comboKazaDurumlist);
        comboBox_kul_EhliyetTip.setItems(comboEhliyetTiplist);
        comboBox_kul_KiraldgGünSay.setItems(comboKiraGünlist);
        comboBox_kul_VerilecekArac.setItems(comboAracTiplist);
        
        mapView.addMapInitializedListener(this);
        to.bindBidirectional(txtBaslangc.textProperty());
        from.bindBidirectional(txtBitis.textProperty());
        
        con=dbe.DBConnection.TkpSqlConn();
        car_data_list=FXCollections.observableArrayList();
        user_data_list=FXCollections.observableArrayList();
        SetCellTableCars();
        SetCellTableUsers();
        LoadCarsData();
        LoadUsersData();
        CellDataUsersAttractText();
        CellDataCarsAttractText();
        
        DataTableCount(); // Kayıt sayısı kontrolü
        PieC_ArcKm();//Arac Km Grfk
        AreaChart_MusSayGrfk_SetData();//Musteri Sayısı Grfk
        LineChrt_AylikGelir();
        
    }

    @Override
    public void mapInitialized() {
       
        MapOptions options = new MapOptions();       
        options.center(new LatLong(37.928, 41.940))
                .zoomControl(true)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoom(12)                
                .overviewMapControl(false)                
                .mapType(MapTypeIdEnum.ROADMAP);
        map = mapView.createMap(options,false);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        
        ////Arac marker icon import-------------------------car1
        String path = "file:///C:/Users/Serhat/Documents/NetBeansProjects/Takip_Sistemi/src/takip_sistemi/Images/cars/car_sar2.png";      
        String imgpath = MarkerImageFactory.createMarkerImage(path, "png");
        MarkerOptions markerOptions = new MarkerOptions();
          markerOptions.visible(Boolean.TRUE)
         .title("Kullanıcı: Sedat Yoğurtçuoğlu, Araba: Mercedes");
         Marker marker1 = new Marker(markerOptions.icon(imgpath));
         marker1.setPosition(new LatLong(LocationGetData.r_u_models.getLati(),LocationGetData.r_u_models.getLongi()));                                       
         map.addMarker(marker1);
        //Arac marker icon import-------------------------car2
        String path2 = "file:///C:/Users/Serhat/Documents/NetBeansProjects/Takip_Sistemi/src/takip_sistemi/Images/cars/car_krmz2.png";      
        String imgpath2 = MarkerImageFactory.createMarkerImage(path2, "png");    
         MarkerOptions markerOptions2 = new MarkerOptions();
         markerOptions2.position( new LatLong(37.95178, 41.87939) )
        .visible(Boolean.TRUE)
        .title("Kullanıcı: Berkay Mimaroğlu, Araba: Audi A3")
        .icon(imgpath2);                  
        Marker marker2 = new Marker( markerOptions2);
        map.addMarker(marker2);
        //Arac marker icon import-------------------------car3
        String path3 = "file:///C:/Users/Serhat/Documents/NetBeansProjects/Takip_Sistemi/src/takip_sistemi/Images/cars/car_mv2.png";      
        String imgpath3 = MarkerImageFactory.createMarkerImage(path3, "png");    
         MarkerOptions markerOptions3 = new MarkerOptions();
         markerOptions3.position( new LatLong(37.92101, 41.92349) )
        .visible(Boolean.TRUE)
        .title("Kullanıcı: Şevket Güler, Araba: Toyota")
        .icon(imgpath3);                  
        Marker marker3 = new Marker( markerOptions3);
        map.addMarker(marker3);
        //Arac marker icon import-------------------------car4
        String path4 = "file:///C:/Users/Serhat/Documents/NetBeansProjects/Takip_Sistemi/src/takip_sistemi/Images/cars/car_mor2.png";      
        String imgpath4 = MarkerImageFactory.createMarkerImage(path4, "png");    
         MarkerOptions markerOptions4 = new MarkerOptions();
         markerOptions4.position( new LatLong(37.92926, 41.94194) )
        .visible(Boolean.TRUE)
        .title("Kullanıcı: Musa Atas, Araba: Nisan")
        .icon(imgpath4);                  
        Marker marker4 = new Marker( markerOptions4);
        map.addMarker(marker4);
        //Arac marker icon import-------------------------car5
         String path5 = "file:///C:/Users/Serhat/Documents/NetBeansProjects/Takip_Sistemi/src/takip_sistemi/Images/cars/car_ysl2.png";      
        String imgpath5 = MarkerImageFactory.createMarkerImage(path5, "png");    
         MarkerOptions markerOptions5 = new MarkerOptions();
         markerOptions5.position( new LatLong(37.97952, 41.85041) )
        .visible(Boolean.TRUE)
        .title("Kullanıcı: Mustafa Asiroğlu, Araba: Bmw")
        .icon(imgpath5);                  
        Marker marker5 = new Marker( markerOptions5);
        map.addMarker(marker5);
         
        SpeedCalculate sp= new SpeedCalculate();
        
//       /*
        //Arac marker icon SHOW-------------------------cars
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {                  
               if (map != null) {                   
                   
                   //ilk aracın markerini  basmak.
                    marker1.setPosition(new LatLong(LocationGetData.r_u_models.getLati2(),LocationGetData.r_u_models.getLongi2()));
                    
                    String zmn1=LocationGetData.r_u_models.getKonum_zamani_oneone(); // tarih bilgilarini threaden çekme.
                    String zmn2=LocationGetData.r_u_models.getKonum_zamani_oneone2();
                    double delta_t=sp.getTimeDifference(zmn1, zmn2); // zaman farkı 
                  //  System.out.println("Zaman1: "+zmn1+"-----"+zmn2+" ::::: "+delta_t);
                    
                    double start_Lat= LocationGetData.r_u_models.getLati();// locasyon bilgilarini threaden çekme.
                    double start_Long= LocationGetData.r_u_models.getLongi();
                    double final_lat = LocationGetData.r_u_models.getLati2();
                    double final_long = LocationGetData.r_u_models.getLongi2();
                     
                   // System.out.println(final_lat+"/////////////////"+start_Lat);                  
                    //----------------------İki konum arasındaki uzaklığı bulma                    
                    double uzaklik=UzaklıkHesapla(start_Lat, start_Long, final_lat, final_long); //iki konum arası uzaklık hesabı(km)
                    System.out.println("İki konum arası uzaklık: "+uzaklik+" km");
                    double speedcar1=(uzaklik/1000)/(delta_t/3600); // V=X/t
                    String kes=Double.toString(speedcar1);
                    String dgr[]=kes.split("\\.");
                    Lbl_Car1.setText(dgr[0]);
                    System.out.println("Hız: "+dgr[0]+" Km/Sa");
                    //-------------------------------------------------------------
                    
                    LatLong latlot_start= new LatLong(start_Lat, start_Long);
                    LatLong latlot_stop= new LatLong(final_lat,final_long); 
                    LatLong[] ary = new LatLong[]{latlot_start,latlot_stop};
                    
                    MVCArray mvc_array=new MVCArray(ary);
                    PolylineOptions polylineOptions = new PolylineOptions()
                            .strokeColor("#E3CD05");
                    Polyline P_LİNE=new Polyline(polylineOptions);
                    P_LİNE.setPath(mvc_array);
                    map.addMapShape(P_LİNE);
                  
                }               
                else{
                    System.out.println("MAP BOŞŞ");
                }                
            }
        }.start();
//*/
 
        //Mouse ile konum alma-----------------------------
        map.addMouseEventHandler(UIEventType.mousemove, (GMapMouseEvent event) -> {
            LatLong latLong = event.getLatLong();
            LblLatUst.setText(formatter.format(latLong.getLatitude()));
            LblLonUst.setText(formatter.format(latLong.getLongitude()));    
        });
    }
     //-------------------Uzaklık Hesabı için------------------------------------------------------
    private static double DereceToRadyan(double deg) //Uzaklık Hesabı için
    {
            return (deg * Math.PI / 180.0);
    }
    
    public double UzaklıkHesapla( double lat1, double lon1, double lat2, double lon2)
    {
            // Radius of earth in meters 
            double R = 6371 * 1000;

            double deltaLat = DereceToRadyan(lat2 - lat1);
            double deltaLon = DereceToRadyan(lon2 - lon1);
            double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) 
                    + Math.cos(DereceToRadyan(lat1)) * Math.cos(DereceToRadyan(lat2)) 
                    * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
            double c = 2 * Math.asin( Math.min(1, Math.sqrt(a)) );
            double d = R * c;
            return d;
    }

    //-------------------Uzaklık Hesabı için The End------------------------------------------------
    
    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        System.out.println("zoom = " + map.getZoom());
        if (status == status.OK) {
            System.out.println("ok");
            for (DirectionsGeocodedWaypoint geocodedWaypoint : results.getGeocodedWaypoints()) {
                System.out.println("geocodedWaypoint = " + geocodedWaypoint.getPlaceId());
            }
            for (DirectionsRoute route : results.getRoutes()) {
                System.out.println("route = " + route.getOverviewPath());
                List<LatLong> lst=route.getOverviewPath();
                List<Marker> markerList=new ArrayList();
                System.out.println("number of markers = " + lst.size());
                for (LatLong latLong : lst) {
                    Marker marker=new Marker(new MarkerOptions().position(latLong));
                    markerList.add(marker);
                   // map.addMarker(marker);
                }               
            }
            for (DirectionsRoute route : results.getRoutes()) {
                
                List<DirectionsLeg> lst=route.getLegs();
                System.out.println("number of legs = " + lst.size());
                for (DirectionsLeg leg : lst) {                    
                    List<DirectionsSteps> drs=leg.getSteps();
                    System.out.println("number of steps = " + drs.size());
                    int distance=0;
                    int duration=0;
                    String instructions="";
                    for (DirectionsSteps dr : drs) {
                        System.out.println("dr = " + dr.getDistance().getValue());
                        distance+=dr.getDistance().getValue();
                        try {
                            duration+=dr.getDuration().getValue();
                        } catch (Exception ex) {
                        }                       
                    }
                    System.out.println("distance = " + distance/1000.0+" km");
                    System.out.println("duration = " + duration);
                }
                
            }
        }
    }
    
   
    
    
}// FXML_AnasayfaController THE END
