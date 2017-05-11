package imp;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;


public class Tram extends Transport {
  public Tram(String nom,int sens,int vitesseMin,int vitesseMax,long tempsGenerationVitesse,long tempsGenerationPanne){
	super(nom,sens,vitesseMin,vitesseMax,tempsGenerationVitesse,tempsGenerationPanne);
    this.capaciterMax = 50;
    type = TypeTransport.Tram;
    this.vitesse = 2;
    this.prixTicket = 3;
  }
  

	@Override
	public Shape getShape() {
		return new Rectangle(((int)position.getPosX()),((int)position.getPosY()),60,20);
	}
	
	@Override
	public Color getColor() {
		return new Color(37,253,233); //turquoise
	}
	
	@Override
	public String getString(){
		return nom + " " + capaciterActuelle;
	}
	
	@Override
	public Point getStringPosition() {
		return new Point(((int)position.getPosX()),((int)position.getPosY()));
	}
}