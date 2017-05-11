package imp;
 
import java.util.ArrayList;
 
 
public class Graphe {
    private Noeud noeudDepart;
    private ArrayList<Noeud> listProvisoire = new ArrayList<Noeud>();

   
    public Graphe(){
    }
   
    public Noeud getNoeudDepart() { return noeudDepart; }
   
  //la fonction parcourt le graphe et renvoie le noeud qui contient l'arret passe en parametre
 
    private Noeud parcoursGraphe(Noeud n, Arret a){
    	listProvisoire.add(n);
    	if(n.getArret() == a){
    		return n;
    	}
    	Noeud noeudFound = null;
    	for(Noeud node : n.getListeFilsNoeud()){
    		if(!listProvisoire.contains(node)){
    			noeudFound = parcoursGraphe(node,a);
    			if(noeudFound != null){
    				return noeudFound;
    			}
    		}      
    	}
    	return null;
	}
    
    //if(((!n1.getListeFilsNoeud().contains(n2)) && (!n2.getListeFilsNoeud().contains(n1)))
    
   public void lierNoeud(Noeud n1, Noeud n2){
	   if(!n1.getListeFilsNoeud().contains(n2)){
		   n1.getListeFilsNoeud().add(n2);
	   }
	   if(!n2.getListeFilsNoeud().contains(n1)){
		   n2.getListeFilsNoeud().add(n1);
	   }
   }
    	
	//Permet de lier 2 noeuds
	 private boolean ajouterNoeud(Arret arretLiaison, Arret arretALier){
		 //S'il n'y a aucun noeud , on cree les 2 premiers noeud et on les lies
		 if(noeudDepart == null){
			 noeudDepart = new Noeud(arretLiaison);
			 Noeud nouveauNoeud = new Noeud(arretALier);
			 lierNoeud(noeudDepart, nouveauNoeud);
			 return true;
		 }
		 //Sinon, on cherche le noeud qui contient l'arretLiaison
		 Noeud n = parcoursGraphe(noeudDepart,arretLiaison);  
		 if(n == null){ //Il existe pas
			 return false;
		 }
		 //S'il existe, on parcours tout ses fils
		 for(Noeud no : n.getListeFilsNoeud()){
			 //Si c'est le noeud qui contient l'arret que l'on cherche alors on le lie avec arretLiaison
			 if(no.getArret() == arretALier){
				 lierNoeud(no, n);
				 return true;
			 }
		 }
		 //quand l'arret qu'on cherche a placer n'existe pas dans le graphe
		 Noeud nouveauNoeud = new Noeud(arretALier);
		 lierNoeud(nouveauNoeud, n);
		 return true;    
	}
	 
	 
	public void resetParcour(){ listProvisoire.clear(); }
	 
	 
	public boolean ajouterNoeudGraphe(Arret arretLiaison, Arret arretALier){
		resetParcour();
	    return ajouterNoeud(arretLiaison, arretALier);
	} 
	
	public Noeud parcourirGraphe(Noeud n, Arret a){
		resetParcour();
		return parcoursGraphe(n, a);
	}
		
}


