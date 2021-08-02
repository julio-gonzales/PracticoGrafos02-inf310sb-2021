package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionNroVerticesInvalido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoPesado {

    public List<List<AdyacenteConPeso>> listaDeAdyacencias;

    public GrafoPesado() {
        this.listaDeAdyacencias = new ArrayList<>();
    }
    public GrafoPesado(int nroInicialDeVertices) throws ExcepcionNroVerticesInvalido {
        if (nroInicialDeVertices <= 0 ) {
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listaDeAdyacencias = new ArrayList<>();
        for (int i = 0; i < nroInicialDeVertices; i++) {
            this.insertarVertice();
        }
    }

    public void insertarVertice( ) {
        List<AdyacenteConPeso> adyacenciasDeNuevoVerticce = new ArrayList<>();
        this.listaDeAdyacencias.add(adyacenciasDeNuevoVerticce);
    }

    public int cantidadDeVertices() {
        return this.listaDeAdyacencias.size();
    }

    public int gradoDeVertice(int posVertice) {
        validarVertice(posVertice);
        List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posVertice);
        return adyacentesDelVertice.size();
    }

    public void validarVertice(int posicionDeVertice) {
        if (posicionDeVertice < 0 || posicionDeVertice >= this.cantidadDeVertices()) {
            throw new IllegalArgumentException("No existe vertice en la " +
                    "posicion " + posicionDeVertice + " en este grafo" );
        }
    }

    public void insertarAristas(int posVerticeOrigen, int posVerticeDestino, double peso) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenciaAlOrigen = new AdyacenteConPeso(posVerticeDestino, peso);
        adyacentesDelOrigen.add(adyacenciaAlOrigen);
        Collections.sort(adyacentesDelOrigen);
        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacentesDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            AdyacenteConPeso adyacenciaAlDestino = new AdyacenteConPeso(posVerticeOrigen, peso);
            adyacentesDelDestino.add(adyacenciaAlDestino);
            Collections.sort(adyacentesDelDestino);
        }
    }

    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacenteDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenciaAlOrigen = new AdyacenteConPeso(posVerticeDestino);
        return adyacenteDelOrigen.contains(adyacenciaAlOrigen);
    }

    public Iterable<Integer> adyacentesDeVertice(int posVertice) {
        validarVertice(posVertice);
        List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posVertice);
        List<Integer> soloVertices = new ArrayList<>();
        for (AdyacenteConPeso adyacenteConPeso : adyacentesDelVertice) {
            soloVertices.add(adyacenteConPeso.getIndiceVertice());
        }
        Iterable<Integer> iterableDeAdyacentes = soloVertices;
        return iterableDeAdyacentes;
    }

    public int cantidadDeAristas() {
        int cantDeAristas = 0;
        int cantDeLazos = 0;
        for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
            List<AdyacenteConPeso> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
            for (AdyacenteConPeso posDeAdyacente : adyacentesDeUnVertice) {
                if (i == posDeAdyacente.getIndiceVertice()) {
                    cantDeLazos++;
                } else {
                    cantDeAristas++;
                }
            }//fin for each
        }//fin for
        return cantDeAristas + (cantDeLazos/2);
    }

    public void eliminarArista( int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }

        List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenciaAlOrigen = new AdyacenteConPeso(posVerticeDestino, 0);
        int posicionDelDestino = adyacentesDelOrigen.indexOf(adyacenciaAlOrigen);
        adyacentesDelOrigen.remove(posicionDelDestino);
        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso>adyacentesDelDestino = this.listaDeAdyacencias.get(posicionDelDestino);
            AdyacenteConPeso adyacenciaAlDestino = new AdyacenteConPeso(posVerticeOrigen, 0);
            int posicionDelOrigen = adyacentesDelDestino.indexOf(adyacenciaAlDestino);
            adyacentesDelDestino.remove(posicionDelOrigen);
        }
    }


    //1.
    //Para un grafo no dirigido implementar el método de eliminar vértice.
    public void eliminarVertice( int posVertice) {
        validarVertice(posVertice);
        this.listaDeAdyacencias.remove(posVertice);
        for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
            List<AdyacenteConPeso> adyacentesDeVertice = this.listaDeAdyacencias.get(i);
            AdyacenteConPeso adyacenteConPeso = new AdyacenteConPeso(posVertice);
            int posicionDeVerticeEnAdy = adyacentesDeVertice.indexOf(posVertice);
            if (posicionDeVerticeEnAdy >= 0) {
                adyacentesDeVertice.remove(posicionDeVerticeEnAdy);
            }
            for (int j = 0; j < adyacentesDeVertice.size(); j++) {
                AdyacenteConPeso posicionDeAdyacente = adyacentesDeVertice.get(j);
                if (posicionDeAdyacente.getIndiceVertice() > posVertice ) {
                    posicionDeAdyacente.setIndiceVertice(posicionDeAdyacente.getIndiceVertice() - 1);
                    //adyacentesDeVertice.set(j, adyacentesDeVertice.get(j) - 1);
                }
            }

        }
    }

    public  double peso(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (!existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso>  adyacentesDelOrigen  = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenciaAlOrigen = new AdyacenteConPeso(posVerticeDestino);
        int posicionDeLaAdyacencia = adyacentesDelOrigen.indexOf(adyacenciaAlOrigen);
        return adyacentesDelOrigen.get(posicionDeLaAdyacencia).getPeso();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < listaDeAdyacencias.size(); i++) {
            buffer.append(listaDeAdyacencias.get(i).toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }

}
