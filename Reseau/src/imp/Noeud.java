package imp;

import java.util.ArrayList;

public class Noeud {
	private Arret arret;
	private ArrayList<Noeud> listeFilsNoeud;

	public Noeud(Arret arret){
		this.setArret(arret);
		listeFilsNoeud = new ArrayList<Noeud>();
	}
	
	public ArrayList<Noeud> getListeFilsNoeud() { return listeFilsNoeud;}

	public Arret getArret() { return arret; }
	
	public void setArret(Arret arret) { this.arret = arret; }
	
}
