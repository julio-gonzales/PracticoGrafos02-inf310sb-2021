package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;

import java.util.ArrayList;
import java.util.List;

public class Floyd {
    private double INFINITO = (1.0/0.0);
    private DiGrafoPesado grafo;
    private int cantidadDeVertices;
    List<List<Double>> matrizDeCaminos;


    public Floyd(DiGrafoPesado grafoPesado) throws ExcepcionAristaNoExiste {
        this.grafo = grafoPesado;
        cantidadDeVertices = this.grafo.cantidadDeVertices();
        matrizDeCaminos = new ArrayList<>();
        for (int i = 0; i < cantidadDeVertices; i++) {
            matrizDeCaminos.add(new ArrayList<Double>());
        }
        cargarMatriz();
    }

    public void cargarMatriz() throws ExcepcionAristaNoExiste {
        for (int i = 0; i < cantidadDeVertices; i++) {
            List<Integer> listaDeAdyacencias = (List<Integer>) grafo.adyacentesDeVertice(i);
            for (int j = 0; j < cantidadDeVertices; j++) {
                if (j == i) {
                    matrizDeCaminos.get(i).add(0.0);
                } else if(listaDeAdyacencias.contains(j)) {
                    double costoAInsertar = grafo.peso(i, j);
                    matrizDeCaminos.get(i).add(costoAInsertar);
                } else {
                    matrizDeCaminos.get(i).add(INFINITO);
                }
            }
        }

        for (int i = 0; i < cantidadDeVertices; i++) {
            for (int j = 0; j < cantidadDeVertices; j++) {
                for (int k = 0; k < cantidadDeVertices; k++) {
                    double costoCandidato = matrizDeCaminos.get(i).get(j) + matrizDeCaminos.get(i).get(k);
                    double comparar = matrizDeCaminos.get(j).get(k);
                    if (costoCandidato < comparar) {
                        matrizDeCaminos.get(j).set(k, costoCandidato);
                    }

                }
            }
        }

    }



}
