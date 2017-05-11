package test;


import java.awt.Color;
import java.util.HashSet;

import display.GUI_for_Displayable;
import imp.Arc;
import imp.Arret;
import imp.Ligne;
import imp.ReseauUrbain;
import imp.Transport;


class Main {
   
    public static void main(String[] args) {

        ReseauUrbain r = ReseauUrbain.getInstance();
        
        GUI_for_Displayable gui = new GUI_for_Displayable("gui",1000,800,new Color(169,234,254));
    	
    	
    	for(Arret a : r.getListeArretReseau()){
    		gui.addDisplayable(a);
    	}
    	
    	HashSet<Ligne> set = new HashSet<Ligne>();
        for(Transport transport : r.getListeTransportReseau())
        	set.add(transport.getLigne());
        
        for(Ligne l : set){
        	
        	for(int i=0;i<l.getListeArret().size()-1;i++){
        		
        		gui.addDisplayable(new Arc(l.getListeArret().get(i),l.getListeArret().get(i+1),l.getNumLigne()));
        	}
        }

        while(true){
        	try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	r.calculGenerationArret();
        	for(Transport transport : r.getListeTransportReseau()){
        		gui.repaint();
        		gui.removeDisplayable(transport);
        		transport.deplacerTransportPixel();
        		gui.addDisplayable(transport);
        	}
        	
        	for(Arret a : r.getListeArretReseau()){
        		a.appelFonctionPrendsTransport();        		
        	}
        	for(Transport t : r.getListeTransportReseau()){
        		t.generationVitesse();
        		t.transportEnPanne();
        	}
        }
    }
}
