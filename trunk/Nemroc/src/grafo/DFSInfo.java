package grafo;

import java.awt.Color;

public class DFSInfo {
	private int d;

	private int f;

	private Color color;

	private Vertice pi;

	public DFSInfo() {
		d = -1;
		f = -1;
		color = Color.WHITE;
		pi = null;
	}

	public void setDiscoveryTime(int time) {
		d = time;
	}

	public int getDiscoveryTime() {
		return d;
	}

	public void setFinishTime(int time) {
		f = time;
	}

	public int getFinishTime() {
		return f;
	}

	public void setColor(Color c) {
		color = c;
	}

	public Color getColor() {
		return color;
	}

	public void setPredecessor(Vertice v) {
		pi = v;
	}

	public Vertice getPredecessor() {
		return pi;
	}

	public String toString() {
		String parentName;

		if (pi == null)
			parentName = "(null)";
		else
			parentName = pi.getName();

		return "d = " + d + ", f = " + f + ", pi = " + parentName;
	}
}