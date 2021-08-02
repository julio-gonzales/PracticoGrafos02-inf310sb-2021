package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.Grafo;

import java.util.Collections;
import java.util.List;

public class DiGrafoPesado extends GrafoPesado {
    public DiGrafoPesado() {
        super();
    }

    public DiGrafoPesado(int nroInicialDeVertices) throws ExcepcionNroVerticesInvalido {
        super(nroInicialDeVertices);
    }

    @Override
    public int gradoDeVertice(int posVertice) {
        throw new UnsupportedOperationException("metodo no soportado en grafos dirigidos");
    }

    @Override
    public void insertarAristas(int posVerticeOrigen, int posVerticeDestino, double peso) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenciaAlOrigen = new AdyacenteConPeso(posVerticeDestino, peso);
        adyacentesDelOrigen.add(adyacenciaAlOrigen);
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public int cantidadDeAristas() {

        int cantidad = 0;
        for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
            List<AdyacenteConPeso> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
            cantidad = cantidad + adyacentesDeUnVertice.size();
        }
        return cantidad;
    }

    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }

        List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenciaAlOrigen = new AdyacenteConPeso(posVerticeDestino, 0);
        int posicionDelDestino = adyacentesDelOrigen.indexOf(adyacenciaAlOrigen);
        adyacentesDelOrigen.remove(posicionDelDestino);
    }

    public int gradoDeEntradaDeVertice( int posVertice){
        super.validarVertice(posVertice);
        int entradasDeVertice = 0;
        for (List<AdyacenteConPeso> adyacentesDeUnVertrice : super.listaDeAdyacencias) {
            for (AdyacenteConPeso posDeAdyacente : adyacentesDeUnVertrice) {
                if (posDeAdyacente.getIndiceVertice() == posVertice) {
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
