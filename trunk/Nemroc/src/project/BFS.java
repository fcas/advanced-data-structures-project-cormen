package project;

import java.awt.Color;
import java.util.Iterator;

public class BFS {

	private BFSInfo[] bfsInfo;

	public void search(AdjacencyListGraph g, Vertex s) {

		bfsInfo = new BFSInfo[g.getCardV()];
		for (int i = 0; i < bfsInfo.length; i++)
			bfsInfo[i] = new BFSInfo();

		BFSInfo sInfo = getBFSInfo(s);
		sInfo.setColor(Color.GRAY);
		sInfo.setDistance(0);

		QueueList q = new QueueList();
		q.enqueue(s);

		while (!q.isEmpty()) {
			Vertex u = (Vertex) q.dequeue();
			BFSInfo uInfo = getBFSInfo(u);
			int uDistance = uInfo.getDistance();

			Iterator iter = g.edgeIterator(u);

			while (iter.hasNext()) {
				Vertex v = (Vertex) iter.next();
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

	public BFSInfo getBFSInfo(Vertex v) {
		return getBFSInfo(v.getIndex());
	}

	public BFSInfo getBFSInfo(int v) {
		return bfsInfo[v];
	}
}