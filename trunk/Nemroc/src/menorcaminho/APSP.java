package menorcaminho;

import grafo.GrafoMatrizAdjacenciaPesada;

abstract public class APSP {

	abstract public double[][] computeShortestPaths(
			GrafoMatrizAdjacenciaPesada g);

	protected double[][] graphToMatrix(GrafoMatrizAdjacenciaPesada g) {
		int n = g.getCardV();

		double[][] w = new double[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (i == j)
					w[i][j] = 0;
				else
					w[i][j] = g.getWeight(i, j);
			}

		return w;
	}

	public static void printMatrix(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++)
				System.out.print(matrix[i][j] + "  ");
			System.out.println();
		}
	}

	public static void printMatrix(boolean[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++)
				System.out.print((matrix[i][j] ? 1 : 0) + "  ");
			System.out.println();
		}
	}
}