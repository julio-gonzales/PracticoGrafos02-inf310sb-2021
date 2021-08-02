package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesDiGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DiGrafo;

import java.util.List;


public class Wharshall {
    public   int a[][];
    public int m;
    private  DiGrafo grafo;

    public Wharshall(DiGrafo unDigrafo) {
        grafo = unDigrafo;
        this.a = new int[unDigrafo.cantidadDeVertices()][unDigrafo.cantidadDeVertices()];
        this.m = unDigrafo.cantidadDeVertices();
        for (int i = 0; i < unDigrafo.cantidadDeVertices(); i++) {
            List<Integer> adyacentesDeUnVertice = (List<Integer>) unDigrafo.adyacentesDeVertice(i);
            for (Integer posicion : adyacentesDeUnVertice) {
                a[i][posicion] = 1;
            }
        }

    }
    //11. Para un grafo dirigido implementar el algoritmo de Wharsall, que luego muestre entre que
    //vértices hay camino

    public void matrizDeCaminos() {
        for (int k = 0; k < grafo.cantidadDeVertices(); k++) {
            for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
                for (int j = 0; j < grafo.cantidadDeVertices(); j++) {
                    a[i][j] = a[i][j] | (a[i][k] & a[k][j]);
                }
            }
        }
    }

    public String entreQueVerticesHayCamino() {
        String cadena ="HA CAMINO ENTRE: \n";
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            boolean sw = false;
            for (int j = 0; j < grafo.cantidadDeVertices(); j++) {
                if (a[i][j] != 0) {
                    cadena += i + "->" + j + " ; ";
                    sw = true;
                }
            }
            if (sw) {
                cadena += "\n";
            }
        }
        return cadena;
    }

    public int[][] retonarMatrizDeCaminos() {
        return a;
    }

}
