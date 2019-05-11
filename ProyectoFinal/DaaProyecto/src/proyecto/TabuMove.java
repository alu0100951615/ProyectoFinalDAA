package proyecto;

public class TabuMove {

	int p1,p2;
	
	public TabuMove(int p1, int p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	
	@Override
	protected TabuMove clone(){
		return new TabuMove(this.p1,this.p2);
	}
	
	
	public String toString() {
		return "[" + p1 + " , " + p2 + "]";
	}
	


	@Override
	public boolean equals(Object obj) {
		
		return (p1==((TabuMove) obj).getP1() && p2==((TabuMove) obj).getP2()) || (p2==((TabuMove) obj).getP1() && p1==((TabuMove) obj).getP2());
	}


	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}
	
	
}
