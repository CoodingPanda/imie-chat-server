import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    private static final Connect instance = new Connect();
    private static Connection connexion;

    private Connect(){
        try {

            String url = "jdbc:mysql://localhost:3306/chatapplication_db?useUnicode=true&useJDBCComplianTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String passwd = "";

            DriverManager.registerDriver(new Driver());
            connexion = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connexion;
    }
}
