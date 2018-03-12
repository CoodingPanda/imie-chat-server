import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.websocket.DeploymentException;

import fr.imie.chat.specification.WebSocketServer;
import fr.imie.chat.specification.listeners.CloseWebSocketListener;
import fr.imie.chat.specification.listeners.MessageWebSocketListener;
import fr.imie.chat.specification.listeners.OpenWebSocketListener;

public class Main {
	public static void main(String[] args) throws DeploymentException, IOException {
		new Main();
	}


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
					System.out.println("Message de " + sessionId);
					System.out.println(message);
				}
		});
		
		webSocketServer.start();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Appuyez sur n'importe quelle touche (et Entrer) pour arreter le serveur...");
		reader.readLine();
	}
}