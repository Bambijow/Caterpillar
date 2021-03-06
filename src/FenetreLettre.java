

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Classe correspondant à des objets permettant d'aouvrir à des fenêtres affichant une lettre avec un fond coloré.
 * 
 * @author Jean-François Condotta, 2017.
 *
 */
public class FenetreLettre extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private char lettre;   // La lettre à afficher.
	private JLabel label;  // Le label pour l'affichage de la lettre.

	/**
	 * Permet de créer une nouvelle fenêtre pour afficher une lettre.
	 */
	public FenetreLettre(){
		super((JFrame)null,"HotTypingGame2017",true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		label=new JLabel("");
		label.setOpaque(true);
		Font fontLabel=new Font("Serif",Font.BOLD,450);
		label.setFont(fontLabel);
		getContentPane().add(label);
		
		addKeyListener(new KeyAdapter(){
	         public void keyReleased(KeyEvent e) {
	         gestionClavier(e);
	        }
		});
		int width=810;
	    int height=550;
	    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	    int x=(screenSize.width/2)-(width/2);
	    int y=(screenSize.height/2)-(height/2);
	    setLocation(x,y);
		setSize(width,height);
		setResizable(false); 
		setVisible(false);
		setModal(false);
	}
	
	
	private void gestionClavier(KeyEvent e){
		char c=Character.toUpperCase(e.getKeyChar());
		System.out.println("*** La lettre "+c+" a été tapée ... ***");
		if (c==lettre)
			setVisible(false);
	}
	
	/**
	 * Affiche une lettre avec une couleur en fond. Lorsque la lettre est tapée au clavier la fenêtre est cachée.
	 * @param lettre Un caractère correspondant à lettre.
	 * @param couleur Un entier correspondant à une couleur (0:rouge, 1:jaune, 2:bleu).
	 */
	public void afficherLettre(char lettre,int couleur){
		this.lettre=Character.toUpperCase(lettre);
		label.setText(" "+this.lettre);
		switch(couleur){
		case 0:label.setBackground(Color.RED);break;
		case 1:label.setBackground(Color.YELLOW);break;
		case 2:label.setBackground(Color.BLUE);break;
		default : label.setBackground(Color.WHITE);
		}
		setVisible(true);
	}
	
	/**
	 * Ferme et détruit la fenêtre.
	 */
	public void fermer(){
		this.dispose();
	}
	public static void main(String[] args) {
		FenetreLettre fenetreLettre=new FenetreLettre();
		Random generateurNombreAleatoire=new Random((new Date()).getTime());
		for (int i=0;i<5;i++){
			int n=generateurNombreAleatoire.nextInt(26);
			char nouvelleLettre=(char)((int)'A'+n);
			int nouvelleCouleur=generateurNombreAleatoire.nextInt(3);
			fenetreLettre.afficherLettre(nouvelleLettre,nouvelleCouleur);
		}
		fenetreLettre.fermer();
		 
	}

	
}
