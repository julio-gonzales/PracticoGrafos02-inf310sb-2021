package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DFS;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.Grafo;

import java.util.ArrayList;
import java.util.List;

public class Ciclos {
    private Grafo grafo;
    private int cantidadDeIslas;

    public Ciclos(Grafo unGrafo) {
        this.grafo = unGrafo;
        CantidadDeIslas islas = new CantidadDeIslas(unGrafo);
        this.cantidadDeIslas = islas.cantidadDeIslas();
    }

    //verificamos si hay ciclos en un Grafo
    //HAY CICLOS EN GRAFO
    //6.
    //Para un grafo dirigido implementar un algoritmo para encontrar si el grafo dirigido tiene
    //ciclos
    public boolean existeCiclo() {
        if (grafo.cantidadDeAristas() >= (grafo.cantidadDeVertices() - (cantidadDeIslas - 1))) {
            return true;
        }
        return false;
    }

    public boolean existeCicloEnVertice(int posVertice) throws ExcepcionAristaNoExiste, ExcepcionAristaYaExiste {
        if (!existeCiclo()) {
            return false;
        }
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            int adyacenteDelVertice = grafo.listaDeAdyacencias.get(posVertice).get(i);
            grafo.eliminarArista(posVertice, adyacenteDelVertice);
            DFS dfsCiclo = new DFS(grafo, adyacenteDelVertice);
            Iterable<Integer> recorrido = dfsCiclo.elRecorrido();
            for (Integer posVerticeAdyacente : recorrido) {
                if (posVerticeAdyacente == posVertice) {
                    grafo.insertarAristas(posVertice, adyacenteDelVertice);
                    return true;
                }
            }
            grafo.insertarAristas(posVertice, adyacenteDelVertice);
        }
        return false;
    }

    public List<Integer> listaDeVerticesConCiclos() throws ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        List<Integer> listaVerticesConCiclo = new ArrayList<>();
        for (int i = 0; i < grafo.listaDeAdyacencias.size(); i++) {
            if (existeCicloEnVertice(i)) {
                listaVerticesConCiclo.add(i);
            }
        }

        return listaVerticesConCiclo;
    }
}
