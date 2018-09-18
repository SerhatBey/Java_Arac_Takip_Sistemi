/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takip_sistemi;

import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static takip_sistemi.GetUsersControl.rentusersmodels;

/**
 *
 * @author Serhat
 */
public class OneByOneGetData extends Thread{
     
       ArrayList<String> zaman_list; 
       ArrayList<String> lat_list ;
       ArrayList<String> long_list; 
       
        
     @Override
    public void run() {
        
        zaman_list=LocationGetData.r_u_models.getKonum_zamani();
         System.out.println(zaman_list.toString());
         
        lat_list=LocationGetData.r_u_models.getLat();
        System.out.println(lat_list.toString());
        
        long_list=LocationGetData.r_u_models.getLong();
        System.out.println(long_list.toString());
        
             for (int i = 0; i < lat_list.size(); i++) {            
                   String  konm_zamani=zaman_list.get(i);
                   String konm_zamani2=zaman_list.get(i+1);
                   double latitude=Double.parseDouble(lat_list.get(i));
                   double longitude=Double.parseDouble(long_list.get(i));
                   double latitude2=Double.parseDouble(lat_list.get(i+1));
                   double longitude2=Double.parseDouble(long_list.get(i+1));
                   
                   LocationGetData.r_u_models.setKonum_zamani_oneone(konm_zamani);
                   LocationGetData.r_u_models.setKonum_zamani_oneone2(konm_zamani2);
                   LocationGetData.r_u_models.setLati(latitude);
                   LocationGetData.r_u_models.setLongi(longitude);
                   LocationGetData.r_u_models.setLati2(latitude2);
                   LocationGetData.r_u_models.setLongi2(longitude2);

                  //   System.out.println(latitude+" ::: "+longitude); 
                    try {
                        Thread.sleep(1300);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OneByOneGetData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
             }       
    }
}
