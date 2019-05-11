package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MatrizDistancia {
	
	private List< List< Float> > distancias = new ArrayList< List< Float> >();
	public List<List <Punto >> soluciones = new ArrayList <List<Punto>>();

	private int numPuntos;
	private int numSubConjuntos;
	public MatrizDistancia(String file) {
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String[] dummy = reader.readLine().replaceAll("\\s+", " ").split(" ");
			numPuntos = Integer.parseInt(dummy[0]);
			numSubConjuntos = Integer.parseInt(dummy[1]);
			
			if (numSubConjuntos > numPuntos) {
				System.out.print("Error: el subconjunto no puede ser mayor que el numero de puntos");
				System.exit(-1);
			}
			
			String line = "";
			for(int i = 1 ; i < numPuntos ; i++) {
				 distancias.add(new ArrayList<Float>());
			}
			
			while((line = reader.readLine()) != null) {
				dummy = line.replaceAll("\\s+", " ").split(" ");
				distancias.get(Integer.parseInt(dummy[0])).add(Float.parseFloat(dummy[2]));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public Float getDist(int a, int b) {
		return (a<b)?distancias.get(a).get(Math.abs(b-a-1)) : distancias.get(b).get(Math.abs(a-b-1));
		//return distancias.get(a).get(Math.abs(b-a-1));
		
	}
	
	public List<Punto> generateRamdonSolution(){
		
		List<Punto> sol = new ArrayList<Punto>();
		List<Integer> puntos = new ArrayList<Integer>();
		List<Punto> estructura = new ArrayList<Punto>();
		while(puntos.size() < numSubConjuntos) {
			Random random = new Random();
			random.setSeed(System.currentTimeMillis());
			int x = Math.abs(random.nextInt() % numPuntos);
			int y = Math.abs(random.nextInt() % numPuntos);
			
			if(puntos.size() == numSubConjuntos -1) {
				//System.out.println(puntos.size());
				x = puntos.get(Math.abs(random.nextInt() % puntos.size()));
				
			}
			
			if( x!=y) {
			
			Punto dummy = new Punto(x,y,getDist(x, y));
			if( !sol.contains(dummy)) {
				
				sol.add(dummy);
				if(!puntos.contains(x))
					puntos.add(x);
				if(!puntos.contains(y))
					puntos.add(y);
				
				
			}
			}
			
			
		}
		
			soluciones.add(sol);
		//System.out.println(soluciones);
		return sol;
		
	}
	
	public List<List<Float>> getDistancias() {
		return distancias;
	}
	
	public List<List<Punto>> getSoluciones() {
		return soluciones;
	}

	public void setSoluciones(List<List<Punto>> soluciones) {
		this.soluciones = soluciones;
	}

	public void setDistancias(List<List<Float>> distancias) {
		this.distancias = distancias;
	}

	public int getNumPuntos() {
		return numPuntos;
	}

	public void setNumPuntos(int numPuntos) {
		this.numPuntos = numPuntos;
	}

	public int getNumSubConjuntos() {
		return numSubConjuntos;
	}

	public void setNumSubConjuntos(int numSubConjuntos) {
		this.numSubConjuntos = numSubConjuntos;
	}
	
	public float sumaTotalDistancias(List<Punto> abc) { //Función que suma la distancia del vector
		float suma = 0;
		for(int i = 0; i < abc.size(); i++) {
			suma+= abc.get(i).getDist();
		}
		return suma;
	}
	
	//Función que se asegura de que el número de puntos en la lista sea siempre el mismo calculando para ello el factor constante de equilibrio del subconjunto.
	public int factorRepeticion(List<Punto> ab) {
		int factor = 0;
		int contador = 0;
		ArrayList<Integer> puntosUsados = new ArrayList<Integer>();
		for (int i = 0; i < ab.size(); i++) {
			if (!puntosUsados.contains(ab.get(i).getX())) {
				puntosUsados.add(ab.get(i).getX());
		}else{
				factor++;
			}
			if(!puntosUsados.contains(ab.get(i).getY())) {
				puntosUsados.add(ab.get(i).getY());
			}else {
				factor++;
			}
			contador += 2;
		}		
		return contador -factor;
	}
	
	public List<Punto> getBestVecino(List<Punto> a){
		List<Punto> aux = new ArrayList<Punto>();			//NO SE PORQUE PUTA MIERDA SE MODIFICA LA A, BUENO SI PORQUE REFERENCIAN AL MISMO PERO YA MIRO MAÑANA
		aux = a;

		float solucionTemp = 0;
		float solucionFinal = sumaTotalDistancias(a);
		boolean bordemax = false;
		for(int i = 0; i < a.size(); i++) {
			
			
			
			aux.get(i).setX(aux.get(i).getX()+1);   						//aumentamos la primera X a 1		
			if(aux.get(i).getX() > numPuntos -1) {
				aux.get(i).setX(aux.get(i).getX()-1);
				bordemax = true;											//Si se pasa del rango de puntos no vale
			}
			if(factorRepeticion(aux) == numSubConjuntos) {					//Significa que el punto es válido y se puede intercambiar.
				solucionTemp = sumaTotalDistancias(aux);
			}
			if(solucionTemp > solucionFinal) {
				System.out.println("iteracion" + i );
				a = aux;													//Actualiza vecino y Valor de la suma
				solucionFinal = solucionTemp;
			}
			
			if(bordemax || aux.get(i).getX() == 1) {							//miramos la izq de la X, lo del 1 es para que no se salga de rango
				aux.get(i).setX(aux.get(i).getX()-1);							//el bool es por si se habia pasado antes
			}else {
				aux.get(i).setX(aux.get(i).getX()-2);
			}
			if(factorRepeticion(aux) == numSubConjuntos) {											//Significa que el punto es válido y se puede intercambiar.
				solucionTemp = sumaTotalDistancias(aux);
			}
			if(solucionTemp > solucionFinal) {
				System.out.println("iteracion" + i );
				a = aux;
				solucionFinal = solucionTemp;
			}
			
			bordemax = false;
			
			aux.get(i).setY(aux.get(i).getY()+1);   						//aumentamos la primera Y a 1
			if(aux.get(i).getY() > numPuntos -1) {
				aux.get(i).setY(aux.get(i).getY()-1);
				bordemax = true;											
			}
			if(factorRepeticion(aux) == numSubConjuntos) {	
				solucionTemp = sumaTotalDistancias(aux);
			}
			if(solucionTemp > solucionFinal) {
				System.out.println("iteracion" + i );
				a = aux;
				solucionFinal = solucionTemp;
			}
			
			if(bordemax || aux.get(i).getY() == 1) {							
				aux.get(i).setY(aux.get(i).getY()-1);							
			}else {
				aux.get(i).setY(aux.get(i).getY()-2);
			}
			if(factorRepeticion(aux) == numSubConjuntos) {	
				solucionTemp = sumaTotalDistancias(aux);
			}
			if(solucionTemp > solucionFinal) {
				System.out.println("iteracion" + i );
				a = aux;
				solucionFinal = solucionTemp;
			}
			

			
			
		}
		return a;
		
	}

	public static void main(String[] args) {
		
		MatrizDistancia md = new MatrizDistancia("GKD-a_1_n10_m2.txt");
			for(int i = 0; i <= 0; i++)
			md.generateRamdonSolution();
			System.out.println(md.getSoluciones());
			System.out.println(md.sumaTotalDistancias(md.getSoluciones().get(0)) + " Suma total");
			System.out.println(md.factorRepeticion(md.getSoluciones().get(0)) + " Equilibrio");
			System.out.println(md.getBestVecino(md.getSoluciones().get(0)) + " Suma del método del vecino");
			System.out.println(md.factorRepeticion(md.getBestVecino(md.getSoluciones().get(0))) + " Equilibrio 2");
//			System.out.println(md.distancias.get(0).get(1)+ " INDICE MATRIZ");
//			System.out.println(md.distancias.get(0) + " indice lista");
	
	}
	
	
}
