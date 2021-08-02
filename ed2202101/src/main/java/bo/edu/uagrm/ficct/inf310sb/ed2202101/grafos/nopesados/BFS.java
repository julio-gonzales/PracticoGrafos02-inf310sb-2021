package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private UtilsRecorridos controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;

    public BFS (Grafo unGrafo, int posVerticePartida){
        this.grafo = unGrafo;
        grafo.validarVertice(posVerticePartida);
        recorrido = new ArrayList<>();
        controlDeMarcados = new UtilsRecorridos(this.grafo.cantidadDeVertices());
        ejecutarBFS(posVerticePartida);

    }


    private void ejecutarBFS(int posVertice){
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(posVertice);
        controlDeMarcados.marcarVertice(posVertice);
        do {
            int posVerticeEnTurno = cola.poll();
            recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacentesDeVerticeEnTurno = grafo.adyacentesDeVertice(posVerticeEnTurno);
            for (Integer posVerticeAdyacente: adyacentesDeVerticeEnTurno) {
                if (!controlDeMarcados.estaVerticeMarcado(posVerticeAdyacente)) {
                    cola.offer(posVerticeAdyacente);
                    controlDeMarcados.marcarVertice(posVerticeAdyacente);
                }
            }
        }
        while (!cola.isEmpty());
    }

    public boolean hayCaminoAVertice(int posVertice) {
        grafo.validarVertice(posVertice);
        return controlDeMarcados.estaVerticeMarcado(posVertice);
    }

    public Iterable<Integer> elRecorrido() {
        return this.recorrido;
    }

    public boolean hayCaminoATodos() {
        return controlDeMarcados.estanTodosMarcado();
    }

}
