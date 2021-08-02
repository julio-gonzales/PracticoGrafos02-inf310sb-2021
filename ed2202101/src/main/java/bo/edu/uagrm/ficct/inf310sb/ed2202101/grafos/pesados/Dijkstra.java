package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.UtilsRecorridos;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dijkstra {
    private static double INFINITO = 99999999;
    private DiGrafoPesado grafoPesado;
    private List<Double> listaDeCostos;
    private List<Integer> listaDePredecesores;
    private UtilsRecorridos marcados;
    public List<List<Integer>> listaDeListas;
    public List<Double> listasDeCostos;

    public Dijkstra(DiGrafoPesado unGrafo) {
        this.grafoPesado = unGrafo;

    }

    private void caminoMinimo(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        this.listaDeCostos = new ArrayList<>();
        this.listaDePredecesores = new ArrayList<>();
        //primero inicializamos la lista de costos con infinito menos el vertice de partida
        for (int i = 0; i < grafoPesado.cantidadDeVertices(); i++) {
            this.listaDeCostos.add(INFINITO);
            this.listaDePredecesores.add(-1);
        }
        this.marcados = new UtilsRecorridos(grafoPesado.cantidadDeVertices());

        listaDeCostos.set(posVerticeOrigen, 0.0);
        while (!marcados.estaVerticeMarcado(posVerticeDestino)) {
            int posVerticeMenorCosto = verticeNoMarcadoMenorCosto();
            marcados.marcarVertice(posVerticeMenorCosto);
            Iterable<Integer> adyacenetesDeVertice = grafoPesado.adyacentesDeVertice(posVerticeMenorCosto);
            for (Integer adyacente : adyacenetesDeVertice) {
                if (!marcados.estaVerticeMarcado(adyacente)) {
                    if ((grafoPesado.peso(posVerticeMenorCosto, adyacente) + listaDeCostos.get(posVerticeMenorCosto))
                            < listaDeCostos.get(adyacente)) {

                        listaDeCostos.set(adyacente,
                                grafoPesado.peso(posVerticeMenorCosto, adyacente) + listaDeCostos.get(posVerticeMenorCosto));
                        listaDePredecesores.set(adyacente, posVerticeMenorCosto);
                    }
                }
            }
        }
    }

    private int verticeNoMarcadoMenorCosto() {
        int i = 0;
        double menorCosto = 0;
        int posVerticeMenorCosto = -1;
        while ( i < listaDeCostos.size()) {
            if (!marcados.estaVerticeMarcado(i)) {
                menorCosto = listaDeCostos.get(i);
                posVerticeMenorCosto = i;
                i = listaDeCostos.size();

            }
            i++;
        }
        for (int j = 0; j < listaDeCostos.size(); j++) {
            if (!marcados.estaVerticeMarcado(j) && listaDeCostos.get(j) < menorCosto) {
                posVerticeMenorCosto = j;
            }
        }
        return posVerticeMenorCosto;
    }

    public double costoMinimo(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        caminoMinimo(posVerticeOrigen, posVerticeDestino);
        return listaDeCostos.get(posVerticeDestino);
    }

    public List<Integer> caminoCostoMinimo(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        this.caminoMinimo(posVerticeOrigen, posVerticeDestino);
        int posDeVertices = this.listaDePredecesores.get(posVerticeDestino);
        List<Integer> retorno = new ArrayList<>();
        Stack<Integer> pilaDeVertices = new Stack<>();
        if (posDeVertices != -1) {
            pilaDeVertices.push(posVerticeDestino);
        }
        while (posDeVertices != -1) {
            pilaDeVertices.push(posDeVertices);
            posDeVertices = this.listaDePredecesores.get(posDeVertices);
        }

        while (!pilaDeVertices.isEmpty()) {
            retorno.add(pilaDeVertices.pop());
        }
        return retorno;
    }

    public void entreQueVerticesHayCamino(int posVerticeOrigen) throws ExcepcionAristaNoExiste {
        listaDeListas = new ArrayList<>();
        listasDeCostos = new ArrayList<>();
        for (int i = 0; i < this.grafoPesado.cantidadDeVertices(); i++) {
            double costoMinimo = costoMinimo(posVerticeOrigen, i);
            if (costoMinimo != INFINITO && costoMinimo > 0) {
                listaDeListas.add(this.caminoCostoMinimo(posVerticeOrigen, i));
                listasDeCostos.add(costoMinimo);
            }
        }
    }

}
