package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.Grafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.UtilsRecorridos;

import java.util.ArrayList;
import java.util.List;

public class DFSPesado {
    private UtilsRecorridos controlDeMarcados;
    private GrafoPesado grafo;
    private List<Integer> recorrido;

    public DFSPesado(GrafoPesado unGrafo, int posVerticePartida) {
        this.grafo = unGrafo;
        grafo.validarVertice(posVerticePartida);
        recorrido = new ArrayList<>();
        controlDeMarcados = new UtilsRecorridos(this.grafo.cantidadDeVertices());
        procesarDFS(posVerticePartida);
    }

    public void procesarDFS(int posVertice) {
        controlDeMarcados.marcarVertice(posVertice);
        recorrido.add(posVertice);
        Iterable<Integer> adyacentesDeVerticeEnTurno = grafo.adyacentesDeVertice(posVertice);
        for (Integer posVerticeAdyacente : adyacentesDeVerticeEnTurno) {
            if (!controlDeMarcados.estaVerticeMarcado(posVerticeAdyacente)) {
                procesarDFS(posVerticeAdyacente);
            }
        }
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
