package proyecto;

public class Punto {

	private int x;
	private int y;
	private Float dist;
	
	public Punto(int x, int y, Float dist) {
		
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
	public Punto(Punto other) {
		this.x = other.x;
		this.y = other.y;
		this.dist = other.dist;
	}
	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Punto(this.x,this.y, this.dist);
	}

	public boolean equals(Object obj) {
		
		return(((Punto) obj).getX() == x && ((Punto) obj).getY() == y) || ((((Punto) obj).getX() == y && ((Punto) obj).getY() == x));
	}

	public Float getDist() {
		return dist;
	}

	public void setDist(Float dist) {
		this.dist = dist;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public String toString() {
		return "(x= " + x + ", y= " + y + "), Dist= " + dist;
	}
	
	
}
