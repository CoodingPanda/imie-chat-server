import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;


import javax.websocket.DeploymentException;

import Actions.Action;
import Actions.Connexion;
import Actions.Inscription;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.chat.specification.WebSocketServer;
import fr.imie.chat.specification.exceptions.SessionNotFoundException;
import fr.imie.chat.specification.listeners.CloseWebSocketListener;
import fr.imie.chat.specification.listeners.MessageWebSocketListener;
import fr.imie.chat.specification.listeners.OpenWebSocketListener;

public class Main {


	public static void main(String[] args) throws DeploymentException, IOException {
		new Main();
	}

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.setDefaultVisibility(JsonAutoDetect.Value.construct(JsonAutoDetect.Visibility.ANY, JsonAutoDetect.Visibility.DEFAULT, JsonAutoDetect.Visibility.DEFAULT, JsonAutoDetect.Visibility.DEFAULT, JsonAutoDetect.Visibility.DEFAULT))
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	private Main() throws DeploymentException, IOException {


		WebSocketServer<String> webSocketServer = WebSocketServer.get("localhost", 8083, String.class);
		
		webSocketServer.addListener(new OpenWebSocketListener<String>() {
				@Override
				public void onOpen(String sessionId) {
					System.out.println("Open " + sessionId);
				}
		});
		
		webSocketServer.addListener(new CloseWebSocketListener<String>() {
				@Override
				public void onClose(String sessionId) {
					System.out.println("Close " + sessionId);
				}
		});
		
		webSocketServer.addListener(new MessageWebSocketListener<String>() {
            public Connection connnection;

            @Override
                public void onMessage(String sessionId, String message) {
                    try {

                        Action typeAction = MAPPER.readValue(message, Action.class);

                        System.out.println(typeAction.getType());
                        System.out.println(typeAction.getType().compareTo("Connexion"));

                        if (typeAction.getType().compareTo("Connexion") == 0) {
                            Connexion authentif = MAPPER.readValue(message, Connexion.class);
                            System.out.println("Message de " + sessionId + ": " + message);

                            System.out.println(authentif.getEmail());

                            // On se connecte à la base de données via la classe Connect
                            Connection connexion = Connect.getConnection();

                            ResultSet resultat = null;

                            // Création de l'objet gérant les requêtes
                            try {
                                Statement statement = connexion.createStatement();

                                // Exécution d'une requête de lecture
                                resultat = statement.executeQuery("SELECT User_id, Username FROM User WHERE Email='" + authentif.getEmail() + "' AND Password='" + authentif.getPassword() + "';");

                                System.out.println("Requête \"SELECT User_id, Username FROM User;\" effectuée !");

                                /* Récupération des données du résultat de la requête de lecture */

                                while (resultat.next()) {
                                    int user_id = resultat.getInt("User_id");
                                    String username = resultat.getString("Username");

                                    System.out.println("Données retournées par la requête : User_id = " + user_id + ", Username = " + username + ".");

                                    // On va créer un objet user pour stocker l'id et le pseudo
                                    User user = new User();
                                    user.setUsername(username);
                                    user.setUser_id(user_id);

                                    //String retour = MAPPER.writeValueAsString(user);

                                    try {
                                        String retour = MAPPER.writeValueAsString(user);
                                        webSocketServer.send(sessionId, retour);
                                        System.out.println("Bonjour " + username);
                                    } catch (SessionNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                                resultat.close();
                                System.out.println("Utilisateur identifié");

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        if (typeAction.getType().compareTo("Inscription") == 0) {
                            Inscription addUser = MAPPER.readValue(message, Inscription.class);
                            System.out.println("Message de "+sessionId+": "+message);

                            // On se connecte à la base de données via la méthode getConnection() de la classe Connect
                            Connection connexion = Connect.getConnection();

                            // Création de l'objet gérant les requêtes
                            try {
                                Statement statement = connexion.createStatement();

                                // Exécution de la requête d'écriture
                                int statut = statement.executeUpdate( "INSERT INTO user (Name, FirstName, City, Username, Email, Password) VALUES ('"+addUser.getName()+"', '"+addUser.getFirstName()+"', '"+addUser.getCity()+"', '"+addUser.getUsername()+"', '"+addUser.getEmail()+"', '"+addUser.getPassword()+"');" );
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
        });



		webSocketServer.start();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Appuyez sur n'importe quelle touche (et Entrer) pour arreter le serveur...");
		reader.readLine();

	}
}