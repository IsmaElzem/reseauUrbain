package imp;


import java.util.ArrayList;

public class Ligne {
	private ArrayList<Arret> listeArretLigne;
    private int numLigne;
	private TypeTransport type;
    
  public Ligne(TypeTransport type,int numLigne){
    this.listeArretLigne = new ArrayList<Arret>();  
    this.numLigne = numLigne;
    this.type = type;
  }
  
  public int getNumLigne() { return numLigne; }
  
  public TypeTransport getType(){ return type; }
  
  public ArrayList<Arret> getListeArret(){ return listeArretLigne; }
  
  public void ajoutArret(Arret a){
	  listeArretLigne.add(a);
  }
}