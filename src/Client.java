import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket = null;
    private FenetreLettre fLettre;

    public void connexion(InetAddress adresseServeur, int portServeur)  {
        System.out.println("Tentative de connexion au serveur " + adresseServeur + ":" + portServeur);
        try {
            this.socket = new Socket(adresseServeur, portServeur);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Il y a un problème de connexion avec le serveur ! ");
            System.exit(1);
        }
        System.out.println("Client connectée");
        while(true) {
            try {
                if(fLettre == null) fLettre = new FenetreLettre();
                fLettre.afficherLettre(fluxEntrant().readLine().charAt(0), 2);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public BufferedReader fluxEntrant(){
        InputStream input=null;
        try {
            input=socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossible de créer un flux entrant pour le client ! ");
            System.exit(1);
        }
        BufferedReader entree = new BufferedReader(new InputStreamReader(input));
        return entree;
    }

    public static void main(String[] args) {
        if (args.length!= 2) {
            System.err.println("2 arguments attendus !");
            System.exit(1);
        }
        System.out.println("Les 2 arguments sont :");
        for (int i = 0; i<args.length; i++) {
            System.out.println(args[i]);
        }

        InetAddress adresseServeur = null;

        try {
            adresseServeur = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Mauvaise adresse IP !");
            System.exit(1);
        }
        int portServeur =Integer.parseInt(args[1]);
        System.out.println("Le port du serveur :"+portServeur);

        Client client = new Client();
        client.connexion(adresseServeur,portServeur);
    }
}
