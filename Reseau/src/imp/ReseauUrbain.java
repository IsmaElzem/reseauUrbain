package imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;




public class ReseauUrbain {
	private ArrayList<Transport> listeTransportReseau;
	private static ReseauUrbain INSTANCE = null;
	private ArrayList<Arret> listeArretReseau;
	private Graphe graphe = new Graphe();
	
	public Graphe getGraphe() { return graphe; }

	private ReseauUrbain(){ // CONSTRUCTEUR
		   	
		
			
		  	listeTransportReseau = new ArrayList<Transport>();
		  	listeArretReseau = new ArrayList<Arret>();
		  		  
		     Arret a1 = new Arret("Arret 1",50,10,1,10,500,11000);
		     Arret a2 = new Arret("Arret 2",200,10,1,10,500,12000);
		     Arret a3 = new Arret("Arret 3",350,10,1,10,500,13000);
		     Arret a4 = new Arret("Arret 4",500,10,1,10,500,14000);
		     Arret a5 = new Arret("Arret 5",650,10,1,10,500,15000);
		     Arret a6 = new Arret("Arret 6",800,10,1,10,500,11000);
		     
		     Arret a7 = new Arret("Arret 7",50,300,1,10,500,12000);
		     Arret a8 = new Arret("Arret 8",200,300,1,10,500,13000);
		     Arret a9 = new Arret("Arret 9",350,300,1,10,500,14000);
		     Arret a10 = new Arret("Arret 10",500,300,1,10,500,15000);
		     Arret a11= new Arret("Arret 11",650,300,1,10,500,11000);
		     
		     Arret a12= new Arret("Arret 12",50,600,1,10,500,12000);
		     Arret a13= new Arret("Arret 13",200,600,1,10,500,13000);
		     Arret a14= new Arret("Arret 14",350,600,1,10,500,14000);
		     Arret a15= new Arret("Arret 15",500,600,1,10,500,15000);
		     
		     
		     
		    /* Arret a16= new Arret("Arret 16",500,400,1,10,500,5000);
		     Arret a17= new Arret("Arret 17",650,400,1,10,500,5000);
		     Arret a18= new Arret("Arret 18",800,400,1,10,500,5000);
		     Arret a19= new Arret("Arret 19",750,600,1,10,500,5000);		     
		     listeArretReseau.add(a16);
		     listeArretReseau.add(a17);
		     listeArretReseau.add(a18);
		     listeArretReseau.add(a19);	
		     Ligne ligne5 = new Ligne(TypeTransport.Tram,5);
		     
		     ligne5.ajoutArret(a10);
		     ligne5.ajoutArret(a16);
		     ligne5.ajoutArret(a17);
		     ligne5.ajoutArret(a18);
		     ligne5.ajoutArret(a19);
		     Tram t2 = new Tram("Tram 2",1,1,3,5000);
		     t2.attribuerLigne(ligne5);
		     listeTransportReseau.add(t2);*/
		     
		     listeArretReseau.add(a1);
		     listeArretReseau.add(a2);
		     listeArretReseau.add(a3);
		     listeArretReseau.add(a4);
		     listeArretReseau.add(a5);
		     listeArretReseau.add(a6);
		     listeArretReseau.add(a7);
		     listeArretReseau.add(a8);
		     listeArretReseau.add(a9);
		     listeArretReseau.add(a10);
		     
		     listeArretReseau.add(a11);
		     listeArretReseau.add(a12);
		     listeArretReseau.add(a13);
		     listeArretReseau.add(a14);
		     listeArretReseau.add(a15);
		     
		     

		     Ligne ligne1 = new Ligne(TypeTransport.Train,1);
		     Ligne ligne2 = new Ligne(TypeTransport.Tram,2);
		     Ligne ligne3 = new Ligne(TypeTransport.Bus,3);
		     Ligne ligne4 = new Ligne(TypeTransport.Bus,4);
		     
		    

		     ligne1.ajoutArret(a1);
		     ligne1.ajoutArret(a2);
		     ligne1.ajoutArret(a3);
		     ligne1.ajoutArret(a4);
		     ligne1.ajoutArret(a5);
		     ligne1.ajoutArret(a6);
		     
		     ligne2.ajoutArret(a7);
		     ligne2.ajoutArret(a8);
		     ligne2.ajoutArret(a9);
		     ligne2.ajoutArret(a10);
		     ligne2.ajoutArret(a11);
		     
		     ligne3.ajoutArret(a5);
		     ligne3.ajoutArret(a4);
		     ligne3.ajoutArret(a9);
		     ligne3.ajoutArret(a12);
		     ligne3.ajoutArret(a13);
		     ligne3.ajoutArret(a14);
		     ligne3.ajoutArret(a15);
		     
		     ligne4.ajoutArret(a3);
		     ligne4.ajoutArret(a9);
		     ligne4.ajoutArret(a12);
		     


		     //Bus b = new Bus("Bus 1",1,1,2,5000);
		     //Bus b2 = new Bus("Bus 2",1);
		     Bus b3 = new Bus("Bus 3",1,1,2,5000,20000);
		     Bus b4 = new Bus("Bus 4",1,1,2,5000,20000);
		     Train train1 = new Train("train 1",1,1,4,5000,20000);
		     Tram t1 = new Tram("Tram 1",1,1,3,5000,20000);
		     
		     train1.attribuerLigne(ligne1);
		     t1.attribuerLigne(ligne2);
		     b3.attribuerLigne(ligne3);
		     b4.attribuerLigne(ligne4);
		     
		  
		     listeTransportReseau.add(train1);
		     listeTransportReseau.add(t1);
		     listeTransportReseau.add(b3);
		     listeTransportReseau.add(b4);
		     
		     
		     automatisationGraphe();
	     
	}
	

	//genere des passagers pours les arrets
	 public void calculGenerationArret(){
		 for(Arret arret : listeArretReseau)
			   arret.generationPassager();
	 }

	
	// Construit le graphe a l'aide des arrets 
	private void automatisationGraphe(){
		HashSet<Ligne> setLigne = new HashSet<Ligne>(); //hashSet pour eviter les doublons de ligne
		for(Transport tr : listeTransportReseau){
			setLigne.add(tr.getLigne());
		}
		while(!setLigne.isEmpty()){
			for(Iterator<Ligne> itr = setLigne.iterator(); itr.hasNext();){ 
				Ligne l = itr.next();
				//"indice" permet de savoir a quel endroit dans la liste la liaison s'est faite
				//pour pouvoir parcourir ensuite lier les arrets avant/apres depuis cette indice
				int indice = -1;
				//ce for creer les nouds pour les arrets dans la liste > indice
				for(int i = 0;i<l.getListeArret().size()-1;i++){
					if(graphe.ajouterNoeudGraphe(l.getListeArret().get(i),l.getListeArret().get(i+1))){
						if(indice ==-1){
					       indice = i;
					    }
					}
				}
				//si les arrets de liaison sont a chaque fois a la fin de la ligne ( si l'arret de liaison est le dernier element)
				if(graphe.ajouterNoeudGraphe(l.getListeArret().get(l.getListeArret().size()-1),l.getListeArret().get(l.getListeArret().size()-2))){ 
					if(indice ==-1){
						indice = l.getListeArret().size()-1;
					}
				}
				//ce for crer les nouds pour les arrets dans la liste < indice
				for(int j = indice;j>0;j--){
					graphe.ajouterNoeudGraphe(l.getListeArret().get(j),l.getListeArret().get(j-1));
				}
				if(indice != -1){
					itr.remove();
				}
			}
		}	
	}
	
	/* Pour le dernier for : 
 		s'il y a les arret : 1 2 3 4 5 6
		et que l'arret de liaison c'est le 5
		ca va donc marcher pour faire ajouterNoeud(5,6)
		mais apres faut faire 5 4 ; 4 3 ; 3 2 ; 2 1
	 */
	
	//Calcul la distance entre 2 positions
	public static double calculDistance(Position pos1,Position pos2){
		return Math.sqrt(Math.pow(pos2.getPosX() - pos1.getPosX(), 2) + Math.pow((pos2.getPosY() - pos1.getPosY()), 2));
	 }
	
	public static ReseauUrbain getInstance() {			
		if(INSTANCE == null){
			INSTANCE = new ReseauUrbain();	
		}
		return INSTANCE;
	}

	public ArrayList<Arret> getListeArretReseau() { return listeArretReseau; }
	
	public ArrayList<Transport> getListeTransportReseau() { return listeTransportReseau; }
}



