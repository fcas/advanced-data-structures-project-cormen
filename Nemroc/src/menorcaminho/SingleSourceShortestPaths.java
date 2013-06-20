package menorcaminho;

import grafo.Vertex;
import grafo.WeightedAdjacencyListGraph;

import java.util.Iterator;


abstract public class SingleSourceShortestPaths {

	public WeightedAdjacencyListGraph g;

	protected boolean noNegWeightCycle;

	private ShortestPathInfo[] spInfo;

	protected SingleSourceShortestPaths(WeightedAdjacencyListGraph theGraph) {
		g = theGraph;
		noNegWeightCycle = true; // haven't found one yet
		spInfo = new ShortestPathInfo[g.getCardV()];
	}

	abstract public void computeShortestPaths(Vertex s);

	public void initializeSingleSource(Vertex s) {
		for (int i = 0; i < spInfo.length; i++)
			spInfo[i] = newShortestPathInfo();

		getShortestPathInfo(s).setEstimate(0);
	}

	protected ShortestPathInfo newShortestPathInfo() {
		return new ShortestPathInfo();
	}

	public boolean hasNoNegativeWeightCycle() {
		return noNegWeightCycle;
	}

	public ShortestPathInfo getShortestPathInfo(Vertex v) {
		return getShortestPathInfo(v.getIndex());
	}

	public ShortestPathInfo getShortestPathInfo(int v) {
		return spInfo[v];
	}
}