# imie-chat-server

Pour utiliser le websocket il faut installer Gradle (il faut également avoir Java JDK ou JRE de version 7 ou supérieur d'installer)

(Commande à entrer dans le GitBash)

--> java -version

Pour installer Gradle

https://gradle.org/install/

Télécharger le "Binary-only"
Créer un dossier Gradle dans le C:/
Extraire le dossier ZIP de gradle-4.6 dedans
Ensuite clic-droit sur "Ce PC" -> "Propiétés" -> "Paramètres système avancés" -> "Variables d'environnement"
Dans "Variables système", aller sur le "Path" et "Modifier" (une fenêtre "Modifier la variable d'environnement"
"Nouveau" et entrer le chemin du Gradle (Par exemple: C:\Gradle\gradle-4.6\bin)

Pour vérifier si Gradle est bien installer aller sur le GitBash et entrer gradle -v

Une fois ceci fait, dans le GitBash aller dans le dossier "websocket" avec les commandes cd

Une fois dedans entrer la commande ./gradlew tasks
Puis ./gradlew build
Et pour lancer le progamme entrer la commande gradle run

Pour envoyer un message, aller sur une page internet, ouvrir l'inspecteur d'élément, aller dans console

var server = new WebSocket("ws://localhost:8083"); //Permet de créer un nouveau WebSocket
server.send("Le message");

Et aller dans le GitBash pour voir le résultat