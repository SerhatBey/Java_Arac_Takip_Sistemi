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
 
public class GetUsersControl {

    public static RentUsersModels rentusersmodels;

    public void UserControl() throws MalformedURLException, IOException, JSONException {

        try {
            String url = "web Service uzantınız (json formatında olmaıdır.)";
            
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

            ArrayList<String> Kul_Ad = new ArrayList<String>();
            ArrayList<String> Kul_Pass = new ArrayList<String>();
            ArrayList<String> Kul_Eposta = new ArrayList<String>();

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject dnm = jsonarray.getJSONObject(i);

                Kul_Ad.add(dnm.getString("rent_user_Kul_Ad"));
                Kul_Pass.add(dnm.getString("rent_user_Kul_Pass"));
                Kul_Eposta.add(dnm.getString("rent_user_Eposta"));

            }
            rentusersmodels = new RentUsersModels();
            rentusersmodels.setKul_Ad(Kul_Ad);
            rentusersmodels.setKul_Pass(Kul_Pass);
            rentusersmodels.setEposta(Kul_Eposta);
            System.out.println(rentusersmodels.getKul_Ad()+" : "+rentusersmodels.getKul_Pass()+" : "+rentusersmodels.getEposta());

        } catch (Exception er) {
            System.out.println(">>GetUsersControl da hata var>> :" + er);
        }

    }
}
