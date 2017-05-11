package imp;


import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;


import java.util.Iterator;

import display.*;

public class Arret implements Displayable{
    private String nom;
    private ArrayList<Passager> listePassagerArret; // nb de personne a l'arret
	private int borneFin,borneDebut;
    private long tempsArret, tempsGenerationPassager;
    private Position position;
    private ArrayList<Transport> listeTransportDisponibleArret;
    private long varTempsActuel;

    
	public Arret(String nom,double positionX,double positionY,int borneDebut,int borneFin,long tempsArret,long tempsGenerationPassager){
      listePassagerArret = new ArrayList<Passager>();
      position = new Position(positionX,positionY);
      this.nom = nom;
      this.borneFin = borneFin;
      this.borneDebut = borneDebut;
      this.tempsArret = tempsArret*1000000; //pour etre ok avec le nanotime
      this.tempsGenerationPassager = tempsGenerationPassager*1000000;
      listeTransportDisponibleArret = new ArrayList<Transport>();
    }
	
    public ArrayList<Passager> getListePassagerArret() { return listePassagerArret; }
	
    public ArrayList<Transport> getListeTransportDisponibleArret() { return listeTransportDisponibleArret; }

	public long getTempsArret() { return tempsArret; }

	public Position getPosition() { return position; }
 
    public String getNom() { return nom; }

    //Genere un nombre aleatoire de passager dans un arret
    public void generationPassager(){   	
    	if(System.nanoTime() - varTempsActuel > tempsGenerationPassager){ // generer des passagers tous les 
    		varTempsActuel = System.nanoTime();
	    	if(borneDebut > borneFin){
	    		int temp = borneDebut;
	    		borneDebut = borneFin;
	    		borneFin = temp;
	    	}
			Random rnd = new Random();
			int nombre = borneDebut + rnd.nextInt(borneFin + 1 - borneDebut);
			for(int i = 0;i<=nombre;i++){
				Passager p = new Passager(this);
				listePassagerArret.add(p);
		    }
			varTempsActuel = System.nanoTime();
		}
    }
    

    
    
    //Appel la fonction prendsTransport dans la class passager (Pour pouvoir l'appeler dans le main)
    public void appelFonctionPrendsTransport(){
    	Iterator<Passager> it = listePassagerArret.iterator();
    	while(it.hasNext()){
    		Passager p = it.next();
    		if(p.prendsTransport()){
    			it.remove();
    		}
    	}
    }

	@Override
	public Shape getShape() {
		return new Rectangle(((int)position.getPosX()),((int)position.getPosY()),70,20);
	}
	
	@Override
	public Color getColor() {
		return new Color(0,0,0);
	}
	
	@Override
	public String getString() {
		return nom + " " + listePassagerArret.size();
	}
	
	@Override
	public Point getStringPosition() {
		return new Point(((int)position.getPosX()),((int)position.getPosY()));
	}
}

