package grafo;

import java.awt.Color;
import java.util.Iterator;

import fila.FilaLista;

public class BFS {

	private BFSInfo[] bfsInfo;

	public void search(GrafoListaAdjacencia g, Vertice s) {

		bfsInfo = new BFSInfo[g.getCardV()];
		for (int i = 0; i < bfsInfo.length; i++)
			bfsInfo[i] = new BFSInfo();

		BFSInfo sInfo = getBFSInfo(s);
		sInfo.setColor(Color.GRAY);
		sInfo.setDistance(0);

		FilaLista q = new FilaLista();
		q.enqueue(s);

		while (!q.isEmpty()) {
			Vertice u = (Vertice) q.dequeue();
			BFSInfo uInfo = getBFSInfo(u);
			int uDistance = uInfo.getDistance();

			Iterator iter = g.edgeIterator(u);

			while (iter.hasNext()) {
				Vertice v = (Vertice) iter.next();
				BFSInfo vInfo = getBFSInfo(v);

				if (vInfo.getColor() == Color.WHITE) {
					vInfo.setColor(Color.GRAY);
					vInfo.setDistance(uDistance + 1);
					vInfo.setPredecessor(u);
					q.enqueue(v);
				}
			}

			uInfo.setColor(Color.BLACK);
		}
	}

	public BFSInfo getBFSInfo(Vertice v) {
		return getBFSInfo(v.getIndex());
	}

	public BFSInfo getBFSInfo(int v) {
		return bfsInfo[v];
	}
}