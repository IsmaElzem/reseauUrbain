package imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import display.Displayable;

abstract public class Transport implements Displayable {
	protected Ligne ligne;
    protected String nom;
    protected int capaciterMax,vitesse,sens,indexArretCourant,capaciterActuelle,sousGenerer,vitesseMin,vitesseMax;
    protected TypeTransport type;
    protected Position position;
    protected ArrayList<Passager> listepassagerTransport;
	protected boolean enStop = true;
    protected long varTemp;
    protected Arret arretQuitte;
    protected long tempsGenerationVitesse,varTempsActuel,tempsGenerationPanne,tempsPanne;
    protected int prixTicket;
    protected boolean enPanne;
 
    public Transport(String nom,int sens,int vitesseMin,int vitesseMax,long tempsGenerationVitesse,long tempsGenerationPanne){
        this.nom = nom;
        this.sens = sens;
        this.vitesseMin = vitesseMin;
        this.vitesseMax = vitesseMax; 
        this.tempsGenerationVitesse = tempsGenerationVitesse*1000000;
        this.tempsGenerationPanne = tempsGenerationPanne*1000000;
        sousGenerer = 0;
        capaciterMax = 50;
        capaciterActuelle = 0;
        vitesse = 1;     
        type = TypeTransport.NonDefini;
        indexArretCourant = -1;
        listepassagerTransport = new ArrayList<Passager>();
        varTempsActuel = System.nanoTime();
        prixTicket = 1; 
        enPanne = false;
        this.tempsPanne = 20000*1000000;
      }
    
    public ArrayList<Passager> getListepassagerTransport() { return listepassagerTransport; }
  
    public int getCapaciterMax() { return capaciterMax; }
    
    public int getCapaciterActuelle() { return capaciterActuelle; }
    
    public int getSens() { return sens; }
    
    public int getIndexListeArret() { return indexArretCourant; }
    
    public Ligne getLigne(){ return ligne; }
    
    public int getSousGenerer(){ return sousGenerer; }
    

	public void setCapaciterMax(int capaciterMax) { this.capaciterMax = capaciterMax; }

	public void setCapaciterActuelle(int capaciterActuelle) { this.capaciterActuelle = capaciterActuelle; }

	public void setIndexListeArret(int indexListeDepart) { this.indexArretCourant = indexListeDepart; }

	public void setSousGenerer(int sousGenerer){ this.sousGenerer = sousGenerer; }
	

    public void generationVitesse(){
    	if(System.nanoTime() - varTempsActuel > tempsGenerationVitesse){
    		varTempsActuel = System.nanoTime();
    		Random rnd = new Random();
    		int newVitesse = vitesseMin + rnd.nextInt(vitesseMax + 1 - vitesseMin);
    		this.vitesse = newVitesse;
    	}
    }
  
	//Fonction permettant d'attribuer une ligne a un transport, et placant ce transport aleatoirement sur un arret de cette ligne
	public boolean attribuerLigne(Ligne ligne){
	  if(ligne.getType() == type){
		  this.ligne = ligne;
		  int arretAleatoire = ligne.getListeArret().size();
		  Random rnd = new Random();
		  int nbreAleatoire = rnd.nextInt(arretAleatoire);
		  indexArretCourant = nbreAleatoire;
	      arretQuitte = ligne.getListeArret().get(indexArretCourant);
		  position = new Position(ligne.getListeArret().get(indexArretCourant).getPosition().getPosX(),ligne.getListeArret().get(indexArretCourant).getPosition().getPosY());
		  return true;
	   }
	   System.out.println("Impossible d'attribuer cette ligne de type " + ligne.getType() +" pour le transport de type " + type);
	   return false;
  }
  
  // Permet de verifier si le transport arrive au bout de la liste ou bien au debut 
	//selon le sens dans lequel il va (ca fait +1 aux arrets et si c'est la fin des arrets ca fait demi tour)
  
  private void deplacementTransport(){
	  if(indexArretCourant + sens >= ligne.getListeArret().size() || indexArretCourant + sens < 0){
		  sens = -sens;
	  }
	  System.out.println("Nom du transport :" + nom + "\n");
	  System.out.println(ligne.getListeArret().get(indexArretCourant).getNom());
	  indexArretCourant += sens;
  }
   
  //Fonction principale du au deplacement d'un transport
  public void deplacerTransportPixel(){
	  	if(enPanne) return;
	     double angle = 0;
	     Arret arretCourant = ligne.getListeArret().get(indexArretCourant);
	     //Si le transport est a l'arret, alors on regarde en nanoTime l'heure actuelle - l'heure a laquelle il est arrive (varTemp) si c'est superieur au temps d'arret passe en parametre de l'arret (5000 par exemple)
	     if(enStop){
	         if(System.nanoTime() - varTemp > arretCourant.getTempsArret()){
	          enStop = false;
	          arretQuitte.getListeTransportDisponibleArret().remove(this); // On retire l'arret de la liste des disponibilitees des transports
	         }
	     } else {
	    	// si le x du transport et de l'arretCourant sont identiques
		     if(((arretCourant.getPosition().getPosX()) == ((int)position.getPosX()))){
		    	// si le transport est plus vers la droite que l'arret
		         if((arretCourant.getPosition().getPosY()) > ((int)position.getPosY())){      	 
		        	// on se deplace vers la droite horizontalement
		             position.setPosY(position.getPosY()+vitesse > arretCourant.getPosition().getPosY() ? arretCourant.getPosition().getPosY() : (position.getPosY()+vitesse)); 
		         } else {
		        	// on se deplace vers la gauche horizontalement
		             position.setPosY(position.getPosY()-vitesse < arretCourant.getPosition().getPosY() ? arretCourant.getPosition().getPosY() : (position.getPosY()-vitesse)); 
		         }
		     }
		  
		     else if((arretCourant.getPosition().getPosY()) == ((int)position.getPosY())){
		    	// si le transport est plus vers la gauche que l'arret
		         if((arretCourant.getPosition().getPosX()) > ((int)position.getPosX())){
		        	//on se deplace vers le haut verticalement
		             position.setPosX(position.getPosX()+vitesse > arretCourant.getPosition().getPosX() ? arretCourant.getPosition().getPosX() : (position.getPosX()+vitesse)); 
		         } else {
		        	// on se deplace vers le bas verticalement
		             position.setPosX(position.getPosX()-vitesse < arretCourant.getPosition().getPosX() ? arretCourant.getPosition().getPosX() : (position.getPosX()-vitesse)); 
		         }
		     } else { // ici on traiste les lignes obliques
		         angle = Math.atan(((arretCourant.getPosition().getPosY())-position.getPosY())/((arretCourant.getPosition().getPosX())-(position.getPosX())));
		         angle += position.getPosX() > arretCourant.getPosition().getPosX() ? Math.PI : 0;
		         Position anciennePos = new Position(position.getPosX(),position.getPosY());
		         position.setPosX(position.getPosX() + vitesse*Math.cos(angle));
		         position.setPosY(position.getPosY() + vitesse*Math.sin(angle));
		         
		         //permet de voir s'il depasse l'arret, il corrige la trajectoire et le met pile a l'arret
		         
		         //droite/gauche		         
		         
		         if(position.getPosX() > anciennePos.getPosX()){
		        	 if( arretCourant.getPosition().getPosX() < position.getPosX()){
				         position.setPosX(arretCourant.getPosition().getPosX());
			         }
		         } else if(position.getPosX() < anciennePos.getPosX()){
		        	 if(arretCourant.getPosition().getPosX() > position.getPosX()){
				         position.setPosX(arretCourant.getPosition().getPosX());
			         }
		         }
		         
		         //haut/bas
		         
		         if(position.getPosY() < anciennePos.getPosY()){
		        	 if( arretCourant.getPosition().getPosY() > position.getPosY()){
				         position.setPosY(arretCourant.getPosition().getPosY());
			         }
		         } else if(position.getPosY() > anciennePos.getPosY()){
		        	 if( arretCourant.getPosition().getPosY() < position.getPosY()){
				         position.setPosY(arretCourant.getPosition().getPosY());
			         }
		         }     
		         
		     }
		     //Si le transport est au meme x/y que l'arret
		     if((((int)position.getPosX()) == (arretCourant.getPosition().getPosX())) && ((int)(position.getPosY()) == (arretCourant.getPosition().getPosY()))){
		           deplacementTransport();
		           enStop = true;
		           varTemp = System.nanoTime();
		           arretCourant.getListeTransportDisponibleArret().add(this);
		           arretQuitte = arretCourant;
		           prevenirPassager();
		       }
	     }     
  }
  
  //Previens le passager qu'il peut descendre
  public void prevenirPassager(){
	  Iterator<Passager> it = listepassagerTransport.iterator();
	  while(it.hasNext()){
		  Passager p = it.next();
		  if(p.descendsTransport()){
			  it.remove();
		  }
	  }
  }
  
  
  public void payerTicket(){
	  Random rnd = new Random();
	  int payeTicketOuPas = rnd.nextInt(100);
	  if(payeTicketOuPas > 10){
		  this.sousGenerer = sousGenerer + prixTicket;
	  }
  }
  
	public void transportEnPanne(){
		if(enPanne){
			if(System.nanoTime() - varTempsActuel > tempsPanne){
				enPanne = false;
			} else {
				if(System.nanoTime() - varTempsActuel > tempsGenerationPanne){
						varTempsActuel = System.nanoTime();
						Random rnd = new Random();
						int estEnPanneOuPas = rnd.nextInt(10000);
						if(estEnPanneOuPas <= 50){
								enPanne = true;
								for(Iterator<Passager> itr = listepassagerTransport.iterator(); itr.hasNext();){
									Passager p = itr.next();
									this.listepassagerTransport.remove(p);
									setCapaciterActuelle(getCapaciterActuelle()-1);
									p.setTransport(null);
									if(ReseauUrbain.calculDistance(p.getListeArretParcours().get(p.getIndiceArretCourantPassager()-1).getPosition(),position) > ReseauUrbain.calculDistance(position,p.getListeArretParcours().get(p.getIndiceArretCourantPassager()).getPosition())){     
										p.getListeArretParcours().get(p.getIndiceArretCourantPassager()).getListePassagerArret().add(p);
									} else {
										p.getListeArretParcours().get(p.getIndiceArretCourantPassager()-1).getListePassagerArret().add(p);
										p.setIndiceArretCourantPassager(p.getIndiceArretCourantPassager()-1);
									}
								}
						}
				}
			}
		}
	}
}


