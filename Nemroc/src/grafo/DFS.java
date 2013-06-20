package grafo;

import java.awt.Color;
import java.util.Iterator;

public class DFS {
	protected int time;

	protected DFSInfo[] dfsInfo;

	public void search(GrafoListaAdjacencia g) {
		dfsInfo = new DFSInfo[g.getCardV()];
		for (int i = 0; i < dfsInfo.length; i++)
			dfsInfo[i] = new DFSInfo();

		time = 0;

		Iterator iter = g.vertexIterator();

		while (iter.hasNext()) {
			Vertice u = (Vertice) iter.next();
			if (getDFSInfo(u).getColor() == Color.WHITE)
				dfsVisit(g, u);
		}
	}

	protected void dfsVisit(GrafoListaAdjacencia g, Vertice u) {
		DFSInfo uInfo = getDFSInfo(u);
		uInfo.setColor(Color.GRAY);
		time++;
		uInfo.setDiscoveryTime(time);
		discover(g, u);
		Iterator iter = g.edgeIterator(u);

		while (iter.hasNext()) {
			Vertice v = (Vertice) iter.next();
			DFSInfo vInfo = getDFSInfo(v);

			if (vInfo.getColor() == Color.WHITE) {
				vInfo.setPredecessor(u);
				dfsVisit(g, v);
			}
		}

		uInfo.setColor(Color.BLACK);
		time++;
		uInfo.setFinishTime(time);
		finish(g, u);
	}

	public DFSInfo getDFSInfo(Vertice v) {
		return getDFSInfo(v.getIndex());
	}

	public DFSInfo getDFSInfo(int v) {
		return dfsInfo[v];
	}

	protected void discover(GrafoListaAdjacencia g, Vertice u) {
	}

	protected void finish(GrafoListaAdjacencia g, Vertice u) {
	}
}