
package imp;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

import display.*;

public class Arc implements Displayable{
	private Arret arretDebut;
	private Arret arretFin;
	//private int numLigne;
	
	public Arc(Arret arretDebut,Arret arretFin,int numLigne){
		this.arretDebut = arretDebut;
		this.arretFin = arretFin;
		//this.numLigne = numLigne;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Line2D.Double(arretDebut.getPosition().getPosX(),arretDebut.getPosition().getPosY(),arretFin.getPosition().getPosX(),arretFin.getPosition().getPosY());
	}

	@Override
	public Color getColor() {
		return new Color(0,0,0);
	}

	@Override
	public String getString() {
		return "";
	}

	@Override
	public Point getStringPosition() {
		return new Point(((int)arretDebut.getPosition().getPosX()),((int)arretDebut.getPosition().getPosY()));
	}
}
