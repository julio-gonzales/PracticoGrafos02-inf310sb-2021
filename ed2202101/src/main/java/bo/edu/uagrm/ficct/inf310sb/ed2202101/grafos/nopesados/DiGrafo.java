package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionNroVerticesInvalido;

import java.util.Collections;
import java.util.List;

public class DiGrafo extends Grafo{
    public DiGrafo() {
        super();
    }

    public DiGrafo (int nroInicialDeVertices) throws ExcepcionNroVerticesInvalido {
        super(nroInicialDeVertices);
    }

    @Override
    public int gradoDeVertice(int posVertice) {
        throw new UnsupportedOperationException("metodo no soportado en grafos dirigidos");
    }

    @Override
    public void insertarAristas(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public int cantidadDeAristas() {

        int cantidad = 0;
        for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
            List<Integer> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
            cantidad = cantidad + adyacentesDeUnVertice.size();
        }
        return cantidad;
    }

    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }

        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        int posicionDelDestino = adyacentesDelOrigen.indexOf(posVerticeDestino);
        adyacentesDelOrigen.remove(posicionDelDestino);

    }

    public int gradoDeEntradaDeVertice( int posVertice){
        super.validarVertice(posVertice);
        int entradasDeVertice = 0;
        for (List<Integer> adyacentesDeUnVertrice : super.listaDeAdyacencias) {
            for (Integer posDeAdyacente : adyacentesDeUnVertrice) {
                if (posDeAdyacente == posVertice) {
                    entradasDeVertice++;
                }
            }
        }

        return entradasDeVertice;
    }

    public int gradoDeSalidaDeVertice(int posVertice) {
        super.validarVertice(posVertice);
        return super.gradoDeVertice(posVertice);
    }
}
