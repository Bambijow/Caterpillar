import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Serveur extends Thread{
    private ServerSocket serverSocket;
    private Socket sendSocket;
    private int port;
    private boolean hasConnection;

    public Serveur(int port) {
        this.port = port;
        this.hasConnection = false;
        enregistrementService();
    }

    public boolean getHasConnection() { return hasConnection; }

    public void enregistrementService() {
        try {
            serverSocket = new ServerSocket(this.port, 4);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossible de créer un serveur sur le port : "+this.port);
            System.exit(1);
        }
        System.out.println("*** SERVEUR à l'écoute du port :"+this.port+" ***");
    }

    public void nouvelleConnexion(){
        System.out.println("Serveur en attente d'une connexion");
        Socket socket = null;
        while(!hasConnection) {
            try {
                socket = serverSocket.accept();
                System.out.println("Nouvelle connexion");
                this.hasConnection = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Problème lors de la connexion");
                System.exit(1);
            }
        }
        this.sendSocket = socket;
    }

    public void sendNewChar(Character c) {
        PrintWriter pw = null;
        System.out.println("Je send " + c);
        pw = fluxSortant(sendSocket);
        pw.println(c);
        pw.flush();
    }


    public PrintWriter fluxSortant(Socket socket){
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e){
            e.printStackTrace();
            System.err.println("Erreur lors de la récupération du flux sortant");
        }
        return pw;
    }


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public void run() {
        nouvelleConnexion();
    }

    public static void main(String[] args){
        //arg 0 nombre de machines (donc de threads), arg 1 port par défaut (+1 à chaque nouveau thread), arg 2 délais en secondes, arg 3 le mot à afficher
        if (args.length!=4) {
            System.out.println("Nombre d'arguments invalides");
            System.exit(1);
        }

        int nbThreads = Integer.parseInt(args[0]);
        int port = Integer.parseInt(args[1]);
        int delais = Integer.parseInt(args[2]);
        String mot = args[3];
        boolean canStart = false;

        Serveur[] listeThreads = new Serveur[nbThreads];
        for(int i = 0; i < listeThreads.length; i++)
            listeThreads[i] = new Serveur(port++);

        // On prépare les différents caractères à envoyer aux clients
        ArrayList<Character> lettres = new ArrayList<>();
        for(int i = 0; i < mot.length(); i++)
            lettres.add(mot.charAt(i));
        lettres.add(' ');
        System.out.println(lettres.get(2));

        for(Serveur s : listeThreads) s.start();
        while(!canStart)
            for(int i = 0; i < listeThreads.length; i++)
                canStart = listeThreads[i].getHasConnection();

        ArrayList<Character> tempLettres = new ArrayList<>(lettres);
        while(true) {
            for(Serveur s : listeThreads) {
                if(tempLettres.size() == 0) s.sendNewChar(' ');
                else {
                    s.sendNewChar(tempLettres.get(0));
                    tempLettres.remove(0);
                }
            }
            Character temp = lettres.get(lettres.size() - 1);
            lettres.remove(lettres.size() - 1);
            lettres.add(0, temp);
            tempLettres.addAll(lettres);
            try {
                Thread.sleep(delais*1000);
            } catch(Exception e) {
                e.printStackTrace();
                System.err.print("Impossible de mettre le thread en pause.");
            }
        }

    }
}