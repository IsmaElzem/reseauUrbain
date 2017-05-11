package imp;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Bus extends Transport {
  public Bus(String nom,int sens,int vitesseMin,int vitesseMax,long tempsGenerationVitesse,long tempsGenerationPanne){
    super(nom,sens,vitesseMin,vitesseMax,tempsGenerationVitesse,tempsGenerationPanne);
    this.capaciterMax = 40;
    type = TypeTransport.Bus;
    this.vitesse = 1; 
    this.prixTicket = 2;
  }
  

	@Override
	public Shape getShape() {
		//return new Rectangle(position.getPosX(),position.getPosY(),60,40);
	    double newX = position.getPosX() - 40 / 2.0;
	    double newY = position.getPosY() - 40 / 2.0;
		return new Ellipse2D.Double(newX,newY,80,40);
	}
	
	@Override
	public Color getColor() {
		return new Color(150,131,236);
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