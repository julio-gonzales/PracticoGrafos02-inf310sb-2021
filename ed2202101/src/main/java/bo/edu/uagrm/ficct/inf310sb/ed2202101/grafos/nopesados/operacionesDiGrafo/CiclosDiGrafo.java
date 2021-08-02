package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesDiGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DiGrafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.UtilsRecorridos;

public class CiclosDiGrafo {
    private DiGrafo grafo;
    private UtilsRecorridos controlDeMarcados;

    public CiclosDiGrafo(DiGrafo unDigrafo) {
        this.grafo = unDigrafo;
    }

    //2.
    //Para un grafo dirigido implementar método o clase para encontrar si hay ciclos sin usar
    //matriz de caminos.
    public boolean hayCiclos() {
        boolean pilloCiclo = false;
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            this.controlDeMarcados = new UtilsRecorridos(grafo.cantidadDeVertices());
            pilloCiclo = hayCiclos(i,i);
            if (pilloCiclo) {
                return true;
            }
        }
        return  false;
    }

    //metodo auxiliar para determinar si hay ciclos
    private boolean hayCiclos(int posVertice, int verticePivote) {
        controlDeMarcados.marcarVertice(posVertice);
        Iterable<Integer> adyacentesDeVerticeEnTurno = grafo.adyacentesDeVertice(posVertice);
        boolean encontreCiclo = false;
        for (Integer posVerticeAdyacente : adyacentesDeVerticeEnTurno) {
            if (!controlDeMarcados.estaVerticeMarcado(posVerticeAdyacente)) {
                if (encontreCiclo) {
                    return true;
                }
                encontreCiclo = hayCiclos(posVerticeAdyacente, verticePivote);
            } else {
                if (posVerticeAdyacente == verticePivote) {
                    return true;
                }
            }
        }
        return encontreCiclo;
    }


    //6.
    //Para un grafo dirigido implementar un algoritmo para encontrar si el grafo dirigido tiene
    //ciclos
    public boolean  hayCiclosConMatrizDeCaminos() {
        Wharshall wharshall = new Wharshall(grafo);
        wharshall.matrizDeCaminos();
        int matriz [][]  = wharshall.a;
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            if (wharshall.a[i][i] == 1) {
                return true;
            }
        }
        return false;
    }

}
