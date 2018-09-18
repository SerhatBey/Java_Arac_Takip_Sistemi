package dbe;

//import java.sql.Connection;
//import com.mysql.jdbc.Connection;
import java.sql.*;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class DBConnection {
    public static Connection TkpSqlConn(){
    
        Connection con=null;
        try {
            con=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Serhat\\Documents\\NetBeansProjects\\Takip_Sistemi\\tkp_prg_arc.mdb");
            System.out.println("Bağlantı yapıldı");
        } catch (Exception e) {
             e.printStackTrace();
        }
//        
        return con;
    }
}
