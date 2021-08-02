package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DFS;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.Grafo;

public class Conexo {
    private DFS unDfs;

    public Conexo(Grafo unGrafo) {
        unDfs = new DFS(unGrafo,0);
    }

    public Conexo(Grafo unGrafo, int posVertice) {
        unDfs = new DFS(unGrafo, posVertice);
    }

    public boolean esConexo() {
        return unDfs.hayCaminoATodos();
    }





}
