package menorcaminho;

import grafo.GrafoMatrizAdjacencia;
import grafo.GrafoMatrizAdjacenciaPesada;

public class FloydWarshall extends APSP {
	public double[][] computeShortestPaths(GrafoMatrizAdjacenciaPesada g) {
		int n = g.getCardV();

		double d[][][] = new double[n + 1][n][n];

		d[0] = graphToMatrix(g);

		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					d[k][i][j] = Math.min(d[k - 1][i][j], d[k - 1][i][k - 1]
							+ d[k - 1][k - 1][j]);
		}

		return d[n];
	}

	public boolean[][] computeTransitiveClosure(GrafoMatrizAdjacencia g) {
		int n = g.getCardV();
		boolean t[][][] = new boolean[n + 1][n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				t[0][i][j] = (i == j) || g.edgeExists(i, j);

		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					t[k][i][j] = t[k - 1][i][j]
							|| (t[k - 1][i][k - 1] && t[k - 1][k - 1][j]);
		}

		return t[n];
	}
}