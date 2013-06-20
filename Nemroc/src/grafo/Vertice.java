package grafo;

public class Vertice {

	public static final int UNKNOWN_INDEX = -1;

	private int index;

	private String name;

	public Vertice(String name) {
		index = UNKNOWN_INDEX;
		this.name = name;
	}

	public Vertice(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name + " (indice = " + index + ")";
	}
}