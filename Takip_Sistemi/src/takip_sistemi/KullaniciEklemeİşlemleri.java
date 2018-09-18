
package takip_sistemi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class KullaniciEklemeİşlemleri {
    
    public Image msjimg_Bas = new Image(getClass().getResource("Images/ysltk.png").toExternalForm());
    public Image msjimg_Uyr = new Image(getClass().getResource("Images/uyari.png").toExternalForm());
    public Image msjimg_Hata = new Image(getClass().getResource("Images/carpı.png").toExternalForm());
    private PreparedStatement pst=null;
    
    //Kullanıcı Ekleme
    public void KulaniciEkle(String Kul_Ad,String Kul_Soyad,String Kul_TC,String Kul_Tlf,String Kul_DogmTrh,String Kul_ElytTip,String Kul_Adress,String Kul_VerAracTip,String Kul_KiraGünSay,Connection con){
    
        String sqlKul="insert into tkp_kullanici_bilgileri_Table(kul_Ad,kul_Soyad,kul_TcNo,kul_Tlf,kul_DgmTrh,kul_EhliyetTip,kul_Adress,kul_VerilmisArac,kul_KiraGünSay) "
             + "Values ('"+Kul_Ad+"','"+Kul_Soyad+"','"+Kul_TC+"','"+Kul_Tlf+"','"+Kul_DogmTrh+"','"+Kul_ElytTip+"','"+Kul_Adress+"','"+Kul_VerAracTip+"','"+Kul_KiraGünSay+"');";
        
        try {
             Statement stm = (Statement) con.createStatement();         
             int executeUpdate = stm.executeUpdate(sqlKul);
             
             if (executeUpdate==1) {
                
                 Notifications.create()
                        .title("KAYIT DURUMU")
                        .text("Kayıt Başarılı. Yeni Kullanıcı Eklendi.")
                        .graphic(new ImageView(msjimg_Bas))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_CENTER)
                         .show();              
            }
             
        } catch (Exception e) {
            Notifications.create()
                        .title("KAYIT DURUMU")
                        .text("Kayıt Başarısız. Yeni Kullanıcı Eklenirken Hata Oluştu..")
                        .graphic(new ImageView(msjimg_Hata))
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_CENTER)
                         .show();
            e.printStackTrace();
        }
    }
    
    //Kullınıcı bilgilerini günceleme
    public void KulaniciGüncelle(int id,String Kul_Ad,String Kul_Soyad,String Kul_TC,String Kul_Tlf,String Kul_DogmTrh,String Kul_ElytTip,String Kul_Adress,String Kul_VerAracTip,String Kul_KiraGünSay,Connection con){
    
        String sql_update="Update tkp_kullanici_bilgileri_Table Set kul_Ad=?, kul_Soyad = ? ,kul_TcNo = ? ,kul_Tlf = ? ,kul_DgmTrh = ? ,kul_EhliyetTip = ? ,kul_Adress = ?, kul_VerilmisArac = ?, kul_KiraGünSay = ? where kul_id = ? ";
             
        try {           
                pst=con.prepareStatement(sql_update);
                    pst.setString(1, Kul_Ad);
                    pst.setString(2, Kul_Soyad);
                    pst.setString(3, Kul_TC);
                    pst.setString(4, Kul_Tlf);
                    pst.setString(5, Kul_DogmTrh);
                    pst.setString(6, Kul_ElytTip);
                    pst.setString(7, Kul_Adress);
                    pst.setString(8, Kul_VerAracTip);
                    pst.setString(9, Kul_KiraGünSay);
                    pst.setInt(10, id);
                    
                    int upi= pst.executeUpdate();
                    
                     if (upi==1) {
                    Notifications.create()
                            .title("GÜNCELLEME DURUMU")
                            .text("Güncelleme Başarılı. Kullanıcı Bilgileri Güncellendi.")
                            .graphic(new ImageView(msjimg_Bas))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_CENTER)
                            .show();  
            }
            
        } catch (Exception e) {
             Notifications.create()
                            .title("GÜNCELLEME DURUMU")
                            .text("Güncelleme Başarısız. Kullanıcı Bilgileri Güncellenirke Hata Oluştu.")
                            .graphic(new ImageView(msjimg_Hata))
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.BOTTOM_CENTER)
                            .show();
             e.printStackTrace();
        }
    }

    public void KulaniciSil(int id,Connection con){
     String sql_delet="Delete from  tkp_kullanici_bilgileri_Table Where kul_id = ? ";
        try {
             pst=con.prepareStatement(sql_delet);
                    pst.setInt(1, id);
                     int upi= pst.executeUpdate();
                        System.out.println("upi = " + upi);               
                        if (upi==1) {
                        Notifications.create()
                                .title("SİLME DURUMU")
                                .text("Silme Başarılı. Kullanıcı Silindi.")
                                .graphic(new ImageView(msjimg_Bas))
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.BOTTOM_CENTER)
                                .show();
                       
                    }
        } catch (Exception ex) {
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
    
    public void KulaniciTümüSil(Connection con){
    
        String sql_alldelet="Delete from  tkp_kullanici_bilgileri_Table";       
                try {
                    pst=con.prepareStatement(sql_alldelet);           
                    int upi= pst.executeUpdate();
                    if (upi==1) {
                        Notifications.create()
                                .title("SİLME DURUMU")
                                .text("Silme Başarılı.Tüm Kullanıcı Silindi.")
                                .graphic(new ImageView(msjimg_Bas))
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.BOTTOM_CENTER)
                                .show();
                    }
                } catch (Exception ex) {
                    Notifications.create()
                                .title("SİLME DURUMU")
                                .text("Silme Başarısız. Tüm Kullanıcılar Silinirken Hata Oluştu.")
                                .graphic(new ImageView(msjimg_Hata))
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.BOTTOM_CENTER)
                                 .show(); 
                   ex.printStackTrace();
                }
    }

}
