package imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Passager {
	private Arret arretFinal;
	private Arret arretDepart;
	private ArrayList<Arret> listeArretParcours;
	private int indiceArretCourantPassager,argentTransport;
	private Transport transport;
	
	public Passager(Arret arretDepart){
		indiceArretCourantPassager = 0;
		argentTransport = 30;
		listeArretParcours = new ArrayList<Arret>();
		Random rnd = new Random();
		this.arretDepart = arretDepart;
		do{
			int numArretFinal = rnd.nextInt(ReseauUrbain.getInstance().getListeArretReseau().size());
			this.arretFinal = ReseauUrbain.getInstance().getListeArretReseau().get(numArretFinal);	
		}while(this.arretDepart == arretFinal);
		dijkstra();
	}
	
	
	public Transport getTransport() { return transport; }

	public void setTransport(Transport transport) { this.transport = transport; }
	
	public void setIndiceArretCourantPassager(int indiceArretCourantPassager) { this.indiceArretCourantPassager = indiceArretCourantPassager; }

	public int getIndiceArretCourantPassager() { return indiceArretCourantPassager; }
	
	public ArrayList<Arret> getListeArretParcours() { return listeArretParcours; }
	
	// Dijkstra adapte pour note code
	public void dijkstra(){ 
	    HashMap<Arret,StructDjikstra> listeDjikstra = new HashMap<Arret,StructDjikstra>();
	    HashMap<Arret,Arret> predecesseur = new HashMap<Arret,Arret>();
	    for(Arret a : ReseauUrbain.getInstance().getListeArretReseau()){
	    	if(arretDepart == a){
	    		listeDjikstra.put(a,new StructDjikstra(0));
	    	} else {
	    		 listeDjikstra.put(a,new StructDjikstra(-1));
	    	}
	    }
    	Arret arretTemp = null;
    	while(arretTemp != arretFinal){
    		double tempCout = Double.MAX_VALUE;
    		//modelise les couples (clé, valeur) d'une table de hachage
		    for(Entry<Arret,StructDjikstra> entry : listeDjikstra.entrySet()){
		    	if(tempCout > entry.getValue().getDistance() && entry.getValue().getParcouru() == false && entry.getValue().getDistance() != -1){
		    		arretTemp = entry.getKey();
		    		tempCout = entry.getValue().getDistance();	
		    	}	
		    }
			listeDjikstra.get(arretTemp).setParcouru(true);
			Noeud noeudPere = ReseauUrbain.getInstance().getGraphe().parcourirGraphe(ReseauUrbain.getInstance().getGraphe().getNoeudDepart(),arretTemp);
			for(Noeud noeudFils : noeudPere.getListeFilsNoeud()){
				if(listeDjikstra.get(noeudFils.getArret()).getParcouru() == false){
					ReseauUrbain.getInstance();
					if(listeDjikstra.get(noeudFils.getArret()).getDistance() == -1 || listeDjikstra.get(noeudFils.getArret()).getDistance() > listeDjikstra.get(noeudPere.getArret()).getDistance() + ReseauUrbain.calculDistance(noeudFils.getArret().getPosition(),noeudPere.getArret().getPosition())){
						ReseauUrbain.getInstance();
						listeDjikstra.get(noeudFils.getArret()).setDistance(listeDjikstra.get(noeudPere.getArret()).getDistance() + ReseauUrbain.calculDistance(noeudFils.getArret().getPosition(),noeudPere.getArret().getPosition()));
						predecesseur.put(noeudFils.getArret(),noeudPere.getArret());
						
					}
				}
			}
    	}
    	//ca permet de creer le parcours du passager mais ca ajoute avec les predecesseur du dernier arret au premier
    	Arret arretPredecesseur = arretFinal;
    	do{
    		listeArretParcours.add(arretPredecesseur); 		
    	}while((arretPredecesseur = predecesseur.get(arretPredecesseur)) != null);
    	Collections.reverse(listeArretParcours);
	}
	
	
	public boolean prendsTransport(){
		//Pour les transports disponible a l'arret
		for(Transport t : listeArretParcours.get(indiceArretCourantPassager).getListeTransportDisponibleArret()){
			if(t.getCapaciterActuelle() < t. getCapaciterMax()){
				//si l'arret suivant est l'arret suivant du futur passager
				if(t.getLigne().getListeArret().get(t.getIndexListeArret()) == listeArretParcours.get(indiceArretCourantPassager+1)){ 					
					t.setCapaciterActuelle(t.getCapaciterActuelle()+1);
					t.getListepassagerTransport().add(this);
					transport = t;
					indiceArretCourantPassager = indiceArretCourantPassager+1;
					this.transport.payerTicket();
					System.out.println(this.transport.getSousGenerer());
					return true; 
				}
			}
		}
		return false;
	}
	/*	
	
	la fonction sert a voir si le passager doit descendre du transport, 
	parce qu'il a atteint son arret final ou parce que l'arret que va deservir le transport
	 dans lequel il se trouve n'est pas sur le chemin du pasager
	pas seulement si c'est le dernier arret du passager	
	
	*/
	
	public boolean descendsTransport(){
		//if(transport.getLigne().getListeArret().get(transport.getIndexListeArret()) == arretFinal){
		Arret arret = indiceArretCourantPassager+1 < listeArretParcours.size() ? listeArretParcours.get(indiceArretCourantPassager) : null; //operateur ternaire pour eviter les repetitions de code : si l'arret suivant est l'arret final, alors arret = null sinon arret = arretSuivant
		if(transport.getLigne().getListeArret().get(transport.getIndexListeArret()) != arret){
			transport.setCapaciterActuelle(transport.getCapaciterActuelle()-1);
			transport = null;
			
			if(listeArretParcours.get(indiceArretCourantPassager) != arretFinal){
				listeArretParcours.get(indiceArretCourantPassager).getListePassagerArret().add(this);
			}
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
}



