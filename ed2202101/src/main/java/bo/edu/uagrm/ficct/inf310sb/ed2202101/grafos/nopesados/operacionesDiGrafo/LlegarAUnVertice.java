package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesDiGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DFS;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DiGrafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LlegarAUnVertice {
    private DiGrafo diGrafo;
    private DFS recorrido;

    public LlegarAUnVertice(DiGrafo unDiGrafo) {
        this.diGrafo = unDiGrafo;
    }


    //5.
    //Para un grafo dirigido solo usando como base la lógica de un recorrido (dfs o bfs) encuentre
    //desde que vértices se puede llegar a un vértice a, sin importar las veces que ejecute el
    //recorrido elegido

    public List<Integer> llegarAUnVertice(int posVertiecObjetivo) {
        List<Integer> listaDeVertice = new LinkedList<>();
        for (int i = 0; i < diGrafo.cantidadDeVertices(); i++) {
            recorrido = new DFS(diGrafo, i);
            if (recorrido.hayCaminoAVertice(posVertiecObjetivo)) {
                listaDeVertice.add(i);
            }
        }
        return  listaDeVertice;
    }


    /*
    public List<Integer> llegarAUnVerticeSinRecorrido(int posVerticeObjetivo) {
        List<Integer> listaDeVertice = new LinkedList<>();
        for (int i = 0; i < diGrafo.cantidadDeVertices(); i++) {
            List<Integer> adyacentesDeUnVertice = (List<Integer>) diGrafo.adyacentesDeVertice(i);
            for (Integer adyacenDeVertice : adyacentesDeUnVertice) {
                if (posVerticeObjetivo == adyacenDeVertice) {
                    listaDeVertice.add(i);
                }
            }
        }
        for (int i = 0; i < diGrafo.cantidadDeVertices(); i++) {
            List<Integer> adyacentesDeUnVertice = (List<Integer>) diGrafo.adyacentesDeVertice(i);
            for (Integer adyacenDeVertice : adyacentesDeUnVertice) {
                if (listaDeVertice.contains(adyacenDeVertice) && !listaDeVertice.contains(i)) {
                    listaDeVertice.add(i);
                }
            }
        }
        return listaDeVertice;
    }*/



    //4.
    //Para un grafo dirigido implementar un método o clase para determinar desde que vértices
    //se puede llegar a un vértice, pero sin ejecutar más de una vez un recorrido.

    public List<Integer> llegarAUnVerticeSinRecorrido(int posVerticeObjetivo) {
        Wharshall wharshall = new Wharshall(diGrafo);
        List<Integer> listaDeVerticesConLlegada = new ArrayList<>();
        int matriz[][] = wharshall.retonarMatrizDeCaminos();
        for (int i = 0; i < diGrafo.cantidadDeVertices(); i++) {
            if (matriz[i][posVerticeObjetivo] == 1) {
                listaDeVerticesConLlegada.add(i);
            }
        }
        return listaDeVerticesConLlegada;
    }
}
