import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion  {

    private static final Connexion instance = new Connexion();
    private static Connect connection;

    private Connexion(){
        try {

            String url = "jdbc:mysql://localhost:3306/chatapplication_db?useUnicode=true&useJDBCComplianTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String passwd = "";

            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
