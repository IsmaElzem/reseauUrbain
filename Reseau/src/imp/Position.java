package imp;

class Position {
    private double posX,posY;  
 
  public Position(double posX,double posY){
    this.setPosX(posX);  
    this.setPosY(posY);
  }

	public double getPosY() { return posY; }
	
	public void setPosY(double posY) { this.posY = posY; }
	
	public double getPosX() { return posX; }
	
	public void setPosX(double posX) { this.posX = posX; }
 
}