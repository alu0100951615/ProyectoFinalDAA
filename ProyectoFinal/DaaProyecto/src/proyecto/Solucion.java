package proyecto;

import java.util.ArrayList;
import java.util.Collections;

public class Solucion extends ArrayList<Integer> {

	public boolean add(Integer punto) {
		
		if(!contains(punto))
			super.add(punto);
		
		return true;
	}
	
	public Solucion(Solucion other) {
		
		for(Integer punto : other) {
			this.add(punto);
		}
		
	}
	
	public Solucion() {
		super();
	}
	
	
	public Solucion sort() {
		
		Collections.sort(this);
		return this;
		
	}

	@Override
	public Solucion clone() {
		return new Solucion(this);
	}
	

}
