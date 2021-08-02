package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DFS;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DiGrafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.Grafo;

public class CantidadDeIslas {
    private DFS recorrido;
    private Grafo grafo;
    private DiGrafo diGrafo;

    public CantidadDeIslas(Grafo unGrafo) {
        grafo = unGrafo;
        recorrido = new DFS(unGrafo, 0);
    }

    public CantidadDeIslas(DiGrafo unDiGrafo) {
        this.diGrafo = unDiGrafo;
        recorrido = new DFS(unDiGrafo, 0);
    }

    public int cantidadDeIslas() {
        int cantidad = 1;
        for (int i = 0; i < grafo.cantidadDeVertices(); i++ ) {
            if (!recorrido.hayCaminoAVertice(i)){
                cantidad++;
                recorrido.procesarDFS(i);
                if (recorrido.hayCaminoATodos()) {
                    return cantidad;
                }
            }
        }
        return cantidad;
    }

    public int cantidadDeIslasDiGrafo() {
        int cantidad = 1;

        for (int i = 0; i < this.diGrafo.cantidadDeVertices(); i++) {
            if (!recorrido.hayCaminoATodos()) {
                if (!recorrido.hayCaminoAVertice(i)) {
                    Iterable<Integer> adyacentesDeUnVertice = diGrafo.adyacentesDeVertice(i);
                    boolean hayVerticeMarcado = false;
                    for ( Integer unVertice : adyacentesDeUnVertice) {
                        if (recorrido.hayCaminoAVertice(unVertice) && !hayVerticeMarcado) {
                            recorrido.procesarDFS(i);
                            hayVerticeMarcado = true;
                        }
                    }
                    if (!hayVerticeMarcado) {
                        cantidad++;
                        recorrido.procesarDFS(i);
                    }
                }
            }
        }
        return cantidad;
    }


}
