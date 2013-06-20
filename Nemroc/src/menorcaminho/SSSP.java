package menorcaminho;

import grafo.Vertice;
import grafo.GrafoListaAdjacenciaPesada;

import java.util.Iterator;


abstract public class SSSP {

	public GrafoListaAdjacenciaPesada g;

	protected boolean noNegWeightCycle;

	private MenorCaminhoInfo[] spInfo;

	protected SSSP(GrafoListaAdjacenciaPesada theGraph) {
		g = theGraph;
		noNegWeightCycle = true; // haven't found one yet
		spInfo = new MenorCaminhoInfo[g.getCardV()];
	}

	abstract public void computeShortestPaths(Vertice s);

	public void initializeSingleSource(Vertice s) {
		for (int i = 0; i < spInfo.length; i++)
			spInfo[i] = newShortestPathInfo();

		getShortestPathInfo(s).setEstimate(0);
	}

	protected MenorCaminhoInfo newShortestPathInfo() {
		return new MenorCaminhoInfo();
	}

	public boolean hasNoNegativeWeightCycle() {
		return noNegWeightCycle;
	}

	public MenorCaminhoInfo getShortestPathInfo(Vertice v) {
		return getShortestPathInfo(v.getIndex());
	}

	public MenorCaminhoInfo getShortestPathInfo(int v) {
		return spInfo[v];
	}
}