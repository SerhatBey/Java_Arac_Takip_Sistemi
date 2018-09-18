/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takip_sistemi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static takip_sistemi.GetUsersControl.rentusersmodels;


public class LocationGetData {
    
    public static RentUsersModels r_u_models;
    
    public void GetLocData() throws MalformedURLException, IOException, JSONException {

        try {
            String url = "http://serhatdev.azurewebsites.net/api/Arduino";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());

            JSONArray jsonarray = new JSONArray(response.toString());
            
            ArrayList<String> lok_zaman = new ArrayList<String>();
            ArrayList<String> Lat = new ArrayList<String>();
            ArrayList<String> Long = new ArrayList<String>();
            ArrayList<String> sinyal = new ArrayList<String>();

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject dnm = jsonarray.getJSONObject(i);
                
                lok_zaman.add(dnm.getString("data_Time"));
                Lat.add(dnm.getString("data_Latitude"));
                Long.add(dnm.getString("data_Longitude"));
                sinyal.add(dnm.getString("data_Sinyal"));

            }
            r_u_models = new RentUsersModels();
            r_u_models.setKonum_zamani(lok_zaman);
            r_u_models.setLat(Lat);
            r_u_models.setLong(Long);
            r_u_models.setSinyal(sinyal);    
            
            System.out.println(r_u_models.getLat()+" : "+r_u_models.getLong()+" : "+r_u_models.getSinyal());
            System.out.println(lok_zaman);// zamanları ekaranda göstermek için
            
        } catch (Exception er) {
            System.out.println(">>LocationGetData da hata var>> :" + er);
        }

    }
}
