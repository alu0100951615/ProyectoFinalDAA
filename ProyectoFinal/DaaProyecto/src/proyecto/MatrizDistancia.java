package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MatrizDistancia {

	private List<List<Float>> distancias = new ArrayList<List<Float>>();
	public List<Solucion> soluciones = new ArrayList<Solucion>();

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
			for (int i = 1; i < numPuntos; i++) {
				distancias.add(new ArrayList<Float>());
			}

			while ((line = reader.readLine()) != null) {
				dummy = line.replaceAll("\\s+", " ").split(" ");
				distancias.get(Integer.parseInt(dummy[0])).add(Float.parseFloat(dummy[2]));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Float getDist(int a, int b) {
		return (a < b) ? distancias.get(a).get(Math.abs(b - a - 1)) : distancias.get(b).get(Math.abs(a - b - 1));

	}

	public Solucion generateRandomSolution() {

		Solucion solucion = new Solucion();
		Random random = new Random();

		while (solucion.size() < numSubConjuntos) {

			random.setSeed(System.currentTimeMillis());

			int randomElement = Math.abs(random.nextInt() % numPuntos);

			solucion.add(randomElement);

		}

		soluciones.add(solucion);
		return solucion;
	}

//	public Float elementsDistance(Solucion puntos) {
//
//		float distance = 0;
//		for (Integer p1 : puntos)
//			for (Integer p2 : puntos) {
//
//				if (p1 != p2)
//					distance += getDist(p1, p2);
//			}
//
//		return distance;
//	}
	public Float elementsDistance(Solucion puntos) {

        float distance = 0;
        for (int i = 0; i < puntos.size(); i++)
            for (int j = i + 1; j < puntos.size(); j++) {
                // System.out.println(i + " " + j);
                distance += getDist(puntos.get(i), puntos.get(j));
            }

        return distance;
    }

	public Solucion getBestVecinoTabu(Solucion elementos, List<TabuMove> moves) {
		Solucion bestVecino = new Solucion();
		Solucion currentVecino = new Solucion(elementos);

		float bestDistance = 0;

		Solucion posiblesPuntos = new Solucion();
		TabuMove finalMove = new TabuMove(-1, -1);

	    for (int distancia = 0; distancia < numPuntos; distancia++) {
            if (!elementos.contains(distancia))
                posiblesPuntos.add(distancia);
        }

		for (int punto = 0; punto < currentVecino.size(); punto++) {

			for (Integer posible : posiblesPuntos) {

				
				TabuMove move = new TabuMove(punto, posible);
				if(!moves.contains(move)) {
					//System.out.println(move);
					int auxPunto = currentVecino.get(punto);

					currentVecino.set(punto, posible);
					
				

					float currentDistance = elementsDistance(currentVecino);
					if (currentDistance > bestDistance) {
						bestDistance = currentDistance;
						bestVecino = currentVecino.clone();
						finalMove = move.clone();
					}

					currentVecino.set(punto, auxPunto);
				}

			}

		}

		moves.add(finalMove);
		return bestVecino;

	}

	public Solucion getBestVecino(Solucion elementos) {
		Solucion bestVecino = new Solucion();
		Solucion currentVecino = new Solucion(elementos);

		float bestDistance = 0;

		Solucion posiblesPuntos = new Solucion();

	    for (int distancia = 0; distancia < numPuntos; distancia++) {
            if (!elementos.contains(distancia))
                posiblesPuntos.add(distancia);
        }

		for (int punto = 0; punto < currentVecino.size(); punto++) {

			for (Integer posible : posiblesPuntos) {

				int auxPunto = currentVecino.get(punto);

				currentVecino.set(punto, posible);

				// System.out.println(currentVecino);

				float currentDistance = elementsDistance(currentVecino);
				if (currentDistance > bestDistance) {
					bestDistance = currentDistance;
					bestVecino = currentVecino.clone();
				}

				currentVecino.set(punto, auxPunto);
			}

		}

		return bestVecino;

	}
	
	public Solucion getRandomVecino(Solucion elementos, int k) {
		
		List<Solucion> kvecinos = new ArrayList<Solucion>();
        Solucion currentVecino = new Solucion(elementos);

        float bestDistance = 0;

        Solucion posiblesPuntos = new Solucion();

        for (int distancia = 0; distancia < numPuntos; distancia++) {
            if (!elementos.contains(distancia))
                posiblesPuntos.add(distancia);
        }

        for (int punto = 0; punto < currentVecino.size(); punto++) {

            for (Integer posible : posiblesPuntos) {

            	if (Math.abs(posible - punto) <= k) {
                    int auxPunto = currentVecino.get(punto);

                    currentVecino.set(punto, posible);
                    kvecinos.add(currentVecino.clone());
                    currentVecino.set(punto, auxPunto);
                }
            }

        }
        
        Random random = new Random();
		random.setSeed(System.currentTimeMillis());
        
        return kvecinos.get(Math.abs(random.nextInt() % kvecinos.size()));

	}
	
	public Solucion getBestVecino(Solucion elementos, int k) {
        Solucion bestVecino = new Solucion();
        Solucion currentVecino = new Solucion(elementos);

        float bestDistance = 0;

        Solucion posiblesPuntos = new Solucion();

        for (int distancia = 0; distancia < numPuntos; distancia++) {
            if (!elementos.contains(distancia))
                posiblesPuntos.add(distancia);
        }

        for (int punto = 0; punto < currentVecino.size(); punto++) {

            for (Integer posible : posiblesPuntos) {

            	if (Math.abs(posible - punto) <= k) {
                    int auxPunto = currentVecino.get(punto);

                    currentVecino.set(punto, posible);

                    

                    float currentDistance = elementsDistance(currentVecino);
                    if (currentDistance > bestDistance) {
                        bestDistance = currentDistance;
                        bestVecino = currentVecino.clone();
                    }

                    currentVecino.set(punto, auxPunto);
                }
            }

        }

        return bestVecino;

    }

	public List<List<Float>> getDistancias() {
		return distancias;
	}

	public List<Solucion> getSoluciones() {
		return soluciones;
	}

	public void setSoluciones(List<Solucion> soluciones) {
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

	public static void main(String[] args) {

		MatrizDistancia md = new MatrizDistancia("Instancias/GKD-a_1_n10_m2.txt");
		for (int i = 0; i < 1; i++)
			System.out.println(md.generateRandomSolution().sort());

		System.out.println(md.getSoluciones());

		Solucion bestVecino = md.getBestVecino(md.getSoluciones().get(0));
		System.out.println("Actual\n" + md.getSoluciones().get(0) + "\nDistancia = "
				+ md.elementsDistance(md.getSoluciones().get(0)));
		System.out.println("Mejor vecino\n" + bestVecino + "\nDistancia = " + md.elementsDistance(bestVecino));

	}

}
