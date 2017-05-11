package imp;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;


public class Train extends Transport {
  public Train(String nom,int sens,int vitesseMin,int vitesseMax,long tempsGenerationVitesse,long tempsGenerationPanne){
	super(nom,sens,vitesseMin,vitesseMax,tempsGenerationVitesse,tempsGenerationPanne);
    this.capaciterMax = 100;
    type = TypeTransport.Train;
    this.vitesse = 3;
    this.prixTicket = 4;
  }


	@Override
	public Shape getShape() {
		return new Rectangle(((int)position.getPosX()),((int)position.getPosY()),60,20);
	}
	
	@Override
	public Color getColor() {
		return new Color(223,109,20);
	}
	
	@Override
	public String getString() {
		return nom + " " + capaciterActuelle;
	}
	
	@Override
	public Point getStringPosition() {
		return new Point(((int)position.getPosX()),((int)position.getPosY()));
	}
}