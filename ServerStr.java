import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStr {
    ServerSocket server= null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata= null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi() {
        try {
            System.out.println("1. server partito in esecuzione...");
            server =  new ServerSocket(6789); //creo un server sulla porta 6789
            client = server.accept(); //rimane in attesa di un client
            server.close(); //chiudo il server per inibire altri client
            //associo due oggetti al socket del client per effettuare la scrittura e la lettura
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }

    public void comunica() {
        try {
            //rimango in attesa della riga trasmessa dal client
            System.out.println("3. benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("6. ricevuta la stringa dal client:"+stringaRicevuta);
            //la modifico e la rispedisco al client
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7. invio la stringa modificata al client...");
            outVersoClient.writeBytes(stringaModificata+'\n');
            //termina elaborazione sul server: chiudo la connessione del client
            System.out.println("9. server: fine elaborazione");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
    }

}