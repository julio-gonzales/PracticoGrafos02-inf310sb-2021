package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesDiGrafo;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DFS;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DiGrafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.Grafo;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

public class CantidadDeIslasDiGrafo {

    private DFS recorrido;
    private DiGrafo diGrafo;
    private Grafo grafoConvertido;

    public CantidadDeIslasDiGrafo(DiGrafo unDiGrafo) {
        this.diGrafo = unDiGrafo;
    }

    public Grafo convertirDiGrafo(DiGrafo unDiGrafo) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        Grafo grafo = new Grafo(unDiGrafo.cantidadDeVertices());
        for (int i = 0; i < unDiGrafo.cantidadDeVertices(); i++) {
            Iterable<Integer> adyacentesDeUnVertice = unDiGrafo.adyacentesDeVertice(i);
            for (Integer adyacente : adyacentesDeUnVertice ) {
                if (!grafo.existeAdyacencia(i,adyacente)) {
                    grafo.insertarAristas(i, adyacente);
                }
            }
        }
        return grafo;
    }

    //10. Para un grafo dirigido implementar un algoritmo para encontrar el número de islas que hay
    //en el grafo

    public int cantidadDeIslasDiGrafo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        int cantidad = 1;
        Grafo diGrafoConvertido = this.convertirDiGrafo(diGrafo);
        recorrido = new DFS(diGrafoConvertido, 0);

        for (int i = 0; i < diGrafoConvertido.cantidadDeVertices(); i++ ) {
            if (!recorrido.hayCaminoAVertice(i)){
                cantidad++;
                recorrido.procesarDFS(i);
                if (recorrido.hayCaminoATodos()) {
                    return cantidad;
                }
            }
        }
        return cantidad;
    }

    //3.
    //Para un grafo dirigido implementar un método o clase que sea capas de retornar los
    //componentes de las islas que existen en dicho digrafo
    public List<List<Integer>> componentesDeIslas() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {
        List<List<Integer>> componentes = new ArrayList<>();
        List<Integer> componenteDeIsla = new ArrayList<>();
        grafoConvertido = this.convertirDiGrafo(diGrafo);
        recorrido = new DFS(grafoConvertido, 0);
        DFS recorridoAuxiliar = new DFS(grafoConvertido, 0);
        componenteDeIsla = (List<Integer>) recorridoAuxiliar.elRecorrido();
        componentes.add(componenteDeIsla);
        while (!recorrido.hayCaminoATodos()) {
            int verticeNoMarcado = this.buscarVerticeNoMarcado();
            recorrido.procesarDFS(verticeNoMarcado);
            recorridoAuxiliar = new DFS(grafoConvertido, verticeNoMarcado);
            componentes.add((List<Integer>) recorridoAuxiliar.elRecorrido());
        }
        return componentes;
    }

    private int buscarVerticeNoMarcado() {
        for (int i = 0; i < diGrafo.cantidadDeVertices(); i++) {
            if (!recorrido.hayCaminoAVertice(i)) {
                return i;
            }
        }
        return -1;
    }


    //PLANTEEMOS ESTE METODO DE LA SIGUIENTE MANERA
    /*
    MODIFIQUEMOS EL BUSCAR VERTICE NO MARCADO AL IGUAL DEL AUXILIAR DEL DEBILMENTE CONEXO
    PARA QUE BUSQUE EN EL VERTICE UN VERTICE NO MARCADO CON ALGUN ADYACENTE MARCADO
     */
    public int cantidadDeIslasMejorado() {
        int contadorDeIslas = 1;
        this.recorrido = new DFS(this.diGrafo, 0);
        while (!recorrido.hayCaminoATodos()) {
            int posVerticeNoMarcado = this.hayVerticeNoMarcadoConAdyacenteMarcado(diGrafo);
            if (posVerticeNoMarcado != -1) {
                recorrido.procesarDFS(posVerticeNoMarcado);
            } else {
                posVerticeNoMarcado =this.buscarVerticeNoMarcado();
                recorrido.procesarDFS(posVerticeNoMarcado);
                contadorDeIslas++;
            }
        }
        return contadorDeIslas;
    }

    private int hayVerticeNoMarcadoConAdyacenteMarcado(DiGrafo grafo) {
        for (int i = 0; i < grafo.cantidadDeVertices(); i++) {
            if (!recorrido.hayCaminoAVertice(i)) {
                Iterable<Integer> adyacentesDeUnVertice = grafo.adyacentesDeVertice(i);
                for (Integer adyacente : adyacentesDeUnVertice) {
                    if (recorrido.hayCaminoAVertice(adyacente)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }


}
