import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO extends DAO<Utilisateur> {
    public UtilisateurDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean update(Utilisateur obj) {
        return false;
    }

    @Override
    public Utilisateur find(int id) {
        Utilisateur utilisateur = new Utilisateur();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM user WHERE User_id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
}
