package project;

import java.awt.Color;

public class BFSInfo {

	private int d;

	private Color color;

	private Vertex pi;

	public BFSInfo() {
		d = Integer.MAX_VALUE;
		color = Color.WHITE;
		pi = null;
	}

	public void setDistance(int distance) {
		d = distance;
	}

	public int getDistance() {
		return d;
	}

	public void setColor(Color c) {
		color = c;
	}

	public Color getColor() {
		return color;
	}

	public void setPredecessor(Vertex v) {
		pi = v;
	}

	public Vertex getPredecessor() {
		return pi;
	}

	public String toString() {
		String parentName;

		if (pi == null)
			parentName = "(null)";
		else
			parentName = pi.getName();

		return "d = " + d + ", pi = " + parentName;
	}
}