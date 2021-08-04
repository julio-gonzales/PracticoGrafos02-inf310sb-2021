package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;

import java.util.ArrayList;
import java.util.List;

public class Floyd {
    private double INFINITO = (1.0/0.0);
    private DiGrafoPesado grafo;
    private int cantidadDeVertices;
    public List<List<Double>> matrizDeCaminos;
    public List<List<Integer>> matrizDePredecesores;


    public Floyd(DiGrafoPesado grafoPesado) throws ExcepcionAristaNoExiste {
        this.grafo = grafoPesado;
        cantidadDeVertices = this.grafo.cantidadDeVertices();
        matrizDeCaminos = new ArrayList<>();
        matrizDePredecesores = new ArrayList<>();
        for (int i = 0; i < cantidadDeVertices; i++) {
            matrizDeCaminos.add(new ArrayList<Double>());
            matrizDePredecesores.add(new ArrayList<>());
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
                matrizDePredecesores.get(i).add(-1);
            }
        }

        for (int i = 0; i < cantidadDeVertices; i++) {
            for (int j = 0; j < cantidadDeVertices; j++) {
                for (int k = 0; k < cantidadDeVertices; k++) {
                    double costoCandidato = matrizDeCaminos.get(j).get(i)+ matrizDeCaminos.get(i).get(k) ;
                    double comparar = matrizDeCaminos.get(j).get(k);
                    if (costoCandidato < comparar) {
                        matrizDeCaminos.get(j).set(k, costoCandidato);
                        matrizDePredecesores.get(j).set(k, i);
                    }

                }
            }
        }

    }

    public List<Integer> listaDeCaminos(int posVerticeOrigen, int posVerticeDestino) {
        List<Integer> listaDeCaminos = new ArrayList<>();
        if (matrizDeCaminos.get(posVerticeOrigen).get(posVerticeDestino) != INFINITO) {
            listaDeCaminos.add(posVerticeOrigen);
            setListaDeCaminos(listaDeCaminos, posVerticeOrigen, posVerticeDestino);
            listaDeCaminos.add(posVerticeDestino);
        }
        return listaDeCaminos;
    }

    private void setListaDeCaminos(List<Integer> listaDeCaminos, int posVerticeOrigen, int posVerticeDestino) {
        if (matrizDePredecesores.get(posVerticeOrigen).get(posVerticeDestino) != - 1) {
            int medio = matrizDePredecesores.get(posVerticeOrigen).get(posVerticeDestino);
            setListaDeCaminos(listaDeCaminos, posVerticeOrigen, medio);
            listaDeCaminos.add(medio);
            setListaDeCaminos(listaDeCaminos, medio, posVerticeDestino);
        }
    }

    public double costoMinimo(int posVerticeOrigen, int posVerticeDestino) {
        return matrizDeCaminos.get(posVerticeOrigen).get(posVerticeDestino);
    }
    //12. Para un grafo dirigido usando la implementación del algoritmo de Floyd-Wharsall encontrar
    //los caminos de costo mínimo entre un vértice a y el resto. Mostrar los costos y cuáles son
    //los caminos
    public String desdeAPuedoLlegar(int posVertice) {
        String retorno = "";
        for (int i = 0; i < cantidadDeVertices; i++) {
            if (matrizDeCaminos.get(posVertice).get(i) != INFINITO && i != posVertice) {
                retorno = retorno + posVertice + " -> " + i + " con un costo de:  " + costoMinimo(posVertice, i) + " y el camino es: "
                    + listaDeCaminos(posVertice, i) + "\n";
            }
        }
        return retorno;
    }

}
