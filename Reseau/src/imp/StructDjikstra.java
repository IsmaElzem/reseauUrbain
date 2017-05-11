package imp;

public class StructDjikstra {
	private double distance;
	private boolean parcouru;
	
	public StructDjikstra(double distance){
		this.distance = distance;
		parcouru = false;
	  }

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean getParcouru() {
		return parcouru;
	}

	public void setParcouru(boolean parcouru) {
		this.parcouru = parcouru;
	}
}
