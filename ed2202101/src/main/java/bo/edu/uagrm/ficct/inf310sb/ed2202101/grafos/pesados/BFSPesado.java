package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.Grafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.UtilsRecorridos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSPesado {
    private UtilsRecorridos controlDeMarcados;
    private GrafoPesado grafoPesado;
    private List<Integer> recorrido;

    public BFSPesado(GrafoPesado unGrafo, int posVerticePartida){
        this.grafoPesado = unGrafo;
        grafoPesado.validarVertice(posVerticePartida);
        recorrido = new ArrayList<>();
        controlDeMarcados = new UtilsRecorridos(this.grafoPesado.cantidadDeVertices());
        ejecutarBFS(posVerticePartida);

    }


    private void ejecutarBFS(int posVertice){
        Queue<AdyacenteConPeso> cola = new LinkedList<>();
        AdyacenteConPeso nuevoAdyacente = new AdyacenteConPeso(posVertice);
        cola.offer(nuevoAdyacente);
        controlDeMarcados.marcarVertice(posVertice);
        do {
            int posVerticeEnTurno = cola.poll().getIndiceVertice();
            recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacentesDeVerticeEnTurno = grafoPesado.adyacentesDeVertice(posVerticeEnTurno);
            for (Integer posVerticeAdyacente: adyacentesDeVerticeEnTurno) {
                if (!controlDeMarcados.estaVerticeMarcado(posVerticeAdyacente)) {
                    nuevoAdyacente = new AdyacenteConPeso(posVerticeAdyacente);
                    cola.offer(nuevoAdyacente);
                    controlDeMarcados.marcarVertice(posVerticeAdyacente);
                }
            }
        }
        while (!cola.isEmpty());
    }

    public boolean hayCaminoAVertice(int posVertice) {
        grafoPesado.validarVertice(posVertice);
        return controlDeMarcados.estaVerticeMarcado(posVertice);
    }

    public Iterable<Integer> elRecorrido() {
        return this.recorrido;
    }

    public boolean hayCaminoATodos() {
        return controlDeMarcados.estanTodosMarcado();
    }

}
