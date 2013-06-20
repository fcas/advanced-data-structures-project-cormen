package project;

import java.util.ArrayList;

public class ShortestPathInfo {
	ArrayList<Vertex> caminho = new ArrayList<Vertex>();

	private double d;

	private Vertex pi;

	public ShortestPathInfo() {
		d = Double.POSITIVE_INFINITY;
		pi = null;
	}

	public void setEstimate(double newEstimate) {
		d = newEstimate;
	}

	public double getEstimate() {
		return d;
	}

	public void setPredecessor(Vertex v) {
		caminho.add(v);
		pi = v;
	}

	public ArrayList<Vertex> getCaminho() {
		return caminho;
	}

	public Vertex getPredecessor() {
		return pi;
	}

	public boolean relax(Vertex u, double du, double w) {
		double newWeight = du + w;
		if (newWeight < d) {
			d = newWeight;
			pi = u;
			return true;
		} else
			return false;
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