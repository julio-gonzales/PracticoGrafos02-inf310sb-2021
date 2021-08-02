package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesDiGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DiGrafo;

public class FloyWharshall {

    private int numNodos;
    /** Matriz de adyacencia, para almacenar los arcos del grafo */
    private int[][] arcos = new int[numNodos][numNodos];
    /** Matriz de Camino (Warshall - Path) */
    private boolean[][] warshallC = new boolean[numNodos][numNodos];
    private DiGrafo grafo;
    private static int INFINITO = 99999999;
    public FloyWharshall(DiGrafo unDiGrafo) {
        this.grafo = unDiGrafo;
        this.numNodos = unDiGrafo.cantidadDeVertices();
    }
    public void warshall() {
        int i, j, k;
        // Obtener la matriz de adyacencia en P
        for (i = 0; i < numNodos; i++)
            for (j = 0; j < numNodos; j++)
                warshallC[i][j] = (arcos[i][j] != INFINITO);

        // Iterar
        for (k = 0; k < numNodos; k++)
            for (i = 0; i < numNodos; i++)
                for (j = 0; j < numNodos; j++)
                    warshallC[i][j] = (warshallC[i][j] || (warshallC[i][k] && warshallC[k][j]));
    }
}
