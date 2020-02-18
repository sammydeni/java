import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStr {
    String nomeServer = "localhost"; //indirizzo del server locale
    int portaServer = 6789; //porta per servizio
    Socket miosocket;
    BufferedReader tastiera; //buffer per l'input da tastiera
    String stringaUtente; //stringa inserita dall'utente
    String stringaRicevutaDalServer; //stringa ricevuta dal server
    DataOutputStream outVersoServer; //stream di output
    BufferedReader inDalServer; //stream di input
    public Socket connetti() {
        System.out.println("2. Client partito in esecuzione");
        try {
            tastiera= new BufferedReader(new InputStreamReader(System.in));
            // per l'input da tastiera
            miosocket = new Socket(nomeServer,portaServer);
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
            //associo due oggetti al soxket per effettuare la scrittura e la lettura
        }
        catch(UnknownHostException e) {
            System.out.println("Host sconosciuto");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }
    public void comunica() {
        try {
            System.out.println("4. inserisci la stringa da trasmettere al server:"+'\n');
            stringaUtente = tastiera.readLine();
            //la spedisco al server
            System.out.println("5. invio la stringa al server e attendo");
            outVersoServer.writeBytes(stringaUtente+'\n');
            //leggo la risposta dal server
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8. risposta dal server"+'\n'+stringaRicevutaDalServer);
            //chiudo la connessione
            System.out.println("9. client: termina elaborazione, chiude la connessione");
            miosocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1);
    }
}
}
