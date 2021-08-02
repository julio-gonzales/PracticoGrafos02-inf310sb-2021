package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesDiGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DFS;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DiGrafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesGrafo.Conexo;

import java.util.List;

public class FuertementeConexo {
    private Conexo grafoConexo;
    private DiGrafo diGrafo;
    private DFS dfs;

    public FuertementeConexo(DiGrafo unDiGrafo) {
        this.diGrafo = unDiGrafo;
    }

    public boolean esFuertementeConexo() {
        for (int i = 0; i < diGrafo.cantidadDeVertices(); i++) {
            this.dfs = new DFS(diGrafo, i);
            if (!grafoConexo.esConexo()) {
                return false;
            }
        }
        return true;
    }

    public boolean debilmenteConexo() {
        this.dfs = new DFS(diGrafo, 0);
        while (!dfs.hayCaminoATodos()){
            int posVerticeNoMarcado = this.hayVerticeNoMarcadoConAdyacenteMarcado(diGrafo);
            if (posVerticeNoMarcado != -1) {
                dfs.procesarDFS(posVerticeNoMarcado);
            } else {
                return false;
            }
        }
        /*
        for (int i = 0; i < diGrafo.cantidadDeVertices(); i++) {
            if (dfs.hayCaminoATodos()) {
                return true;
            }
            if (!dfs.hayCaminoAVertice(i)) {
                Iterable<Integer> adyacentesDeVertice = diGrafo.adyacentesDeVertice(i);
                boolean hayVerticeMarcado = false;
                for (Integer posVertice : adyacentesDeVertice) {
                    if (dfs.hayCaminoAVertice(posVertice) && !hayVerticeMarcado) {
                        dfs.procesarDFS(i);
                        hayVerticeMarcado = true;
                    }
                }
                if (!hayVerticeMarcado) {
                    return false;
                }
            }
        }*/
        return true;
    }

    private int hayVerticeNoMarcadoConAdyacenteMarcado(DiGrafo grafo) {
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            if (!dfs.hayCaminoAVertice(i)) {
                Iterable<Integer> adyacentesDeUnVertice = grafo.adyacentesDeVertice(i);
                for (Integer adyacente : adyacentesDeUnVertice) {
                    if (dfs.hayCaminoAVertice(adyacente)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

}
