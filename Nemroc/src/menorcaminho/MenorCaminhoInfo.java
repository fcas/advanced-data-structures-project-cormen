package menorcaminho;

import grafo.Vertice;

import java.util.ArrayList;


public class MenorCaminhoInfo {
	ArrayList<Vertice> caminho = new ArrayList<Vertice>();

	private double d;

	private Vertice pi;

	public MenorCaminhoInfo() {
		d = Double.POSITIVE_INFINITY;
		pi = null;
	}

	public void setEstimate(double newEstimate) {
		d = newEstimate;
	}

	public double getEstimate() {
		return d;
	}

	public void setPredecessor(Vertice v) {
		caminho.add(v);
		pi = v;
	}

	public ArrayList<Vertice> getCaminho() {
		return caminho;
	}

	public Vertice getPredecessor() {
		return pi;
	}

	public boolean relax(Vertice u, double du, double w) {
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