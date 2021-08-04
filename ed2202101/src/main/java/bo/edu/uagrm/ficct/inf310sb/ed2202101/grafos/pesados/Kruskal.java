package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.UtilsRecorridos;

import java.util.ArrayList;
import java.util.List;
public class Kruskal {
    public static final double INF = 63000.00;
    private List<List<AdyacenteConPeso>> adyacencia;
    private List<List<AdyacenteConPeso>> arbol;
    private List<Integer> pertenece;
    private int N;

    public Kruskal(GrafoPesado G) {
        N = G.cantidadDeVertices();
        adyacencia = G.listaDeAdyacencias;
        arbol = new ArrayList<>();
        pertenece = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            arbol.add(new ArrayList<AdyacenteConPeso>());
            pertenece.add(i);
        }
    }

    public void run() {
        int verticeA = 0, verticeB = 0;
        int arcos = 1;
        while(arcos < N) {
            double min = INF;
            for(int i = 0; i < adyacencia.size(); i++) {
                for(int j = 0; j < adyacencia.get(i).size(); j++) {
                    int verticeJ = adyacencia.get(i).get(j).getIndiceVertice();
                    double pesoIJ = adyacencia.get(i).get(j).getPeso();
                    if(pesoIJ < min && pertenece.get(i) != pertenece.get(verticeJ)) {
                        min = pesoIJ;
                        verticeA = i;
                        verticeB = verticeJ;
                    }
                }
            }

            if(pertenece.get(verticeA) != pertenece.get(verticeB)) {
                arbol.get(verticeA).add(new AdyacenteConPeso(verticeB, min));
                arbol.get(verticeB).add(new AdyacenteConPeso(verticeA, min));

                int temp = pertenece.get(verticeB);
                pertenece.set(verticeB, pertenece.get(verticeA));
                for(int k = 0; k < N; k++) {
                    if(pertenece.get(k) == temp)
                        pertenece.set(k, pertenece.get(verticeA));
                }
                arcos++;
            }
        }
    }

    public GrafoPesado getGrafoKruskal() {
        GrafoPesado gk = new GrafoPesado();
        gk.listaDeAdyacencias = arbol;
        return gk;
    }


    public String mostrarArbol() {
        String s = "";
        for(int i = 0; i < arbol.size(); i++) {
            for(int j = 0; j < arbol.get(i).size(); j++) {
                s = s + i + " - " + arbol.get(i).get(j).getIndiceVertice()
                        + " = " + arbol.get(i).get(j).getPeso() + "\n";
            }
        }
        return s;
    }


}
