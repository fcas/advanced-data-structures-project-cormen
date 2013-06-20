package grafo;

import java.util.Iterator;


public interface IteradorArestasPesadas extends Iterator {

	public double getWeight();
	
	public void setWeight(double weight);
}