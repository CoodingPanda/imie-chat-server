import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


import javax.websocket.DeploymentException;

import Actions.Action;
import Actions.Inscription;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.imie.chat.specification.WebSocketServer;
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
				@Override
                public void onMessage(String sessionId, String message) {
                    try {
                        Action typeAction = MAPPER.readValue(message, Action.class);

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