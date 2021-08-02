package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionNroVerticesInvalido;
import com.sun.corba.se.pept.transport.ContactInfoList;
import com.sun.deploy.cache.BaseLocalApplicationProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grafo {

    public List<List<Integer>> listaDeAdyacencias;

    public Grafo() {
        this.listaDeAdyacencias = new ArrayList<>();
    }
    public Grafo( int nroInicialDeVertices) throws ExcepcionNroVerticesInvalido {
        if (nroInicialDeVertices <= 0 ) {
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listaDeAdyacencias = new ArrayList<>();
        for (int i = 0; i < nroInicialDeVertices; i++) {
            this.insertarVertice();
        }
    }

    public void insertarVertice( ) {
        List<Integer> adyacenciasDeNuevoVerticce = new ArrayList<>();
        this.listaDeAdyacencias.add(adyacenciasDeNuevoVerticce);
    }

    public int cantidadDeVertices() {
        return this.listaDeAdyacencias.size();
    }

    public int gradoDeVertice(int posVertice) {
        validarVertice(posVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posVertice);
        return adyacentesDelVertice.size();
    }

    public void validarVertice(int posicionDeVertice) {
        if (posicionDeVertice < 0 || posicionDeVertice >= this.cantidadDeVertices()) {
            throw new IllegalArgumentException("No existe vertice en la " +
                    "posicion " + posicionDeVertice + " en este grafo" );
        }
    }

    public void insertarAristas(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
        if (posVerticeOrigen != posVerticeDestino) {
            List<Integer> adyacentesDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            adyacentesDelDestino.add(posVerticeOrigen);
            Collections.sort(adyacentesDelDestino);
        }
    }

    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino){
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacenteDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        return adyacenteDelOrigen.contains(posVerticeDestino);
    }

    public Iterable<Integer> adyacentesDeVertice(int posVertice) {
        validarVertice(posVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posVertice);
        Iterable<Integer> iterableDeAdyacentes = adyacentesDelVertice;
        return iterableDeAdyacentes;
    }

    public int cantidadDeAristas() {
        int cantDeAristas = 0;
        int cantDeLazos = 0;
        for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
            List<Integer> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
            for (Integer posDeAdyacente : adyacentesDeUnVertice) {
                if (i == posDeAdyacente) {
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

        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        int posicionDelDestino = adyacentesDelOrigen.indexOf(posVerticeDestino);
        adyacentesDelOrigen.remove(posicionDelDestino);
        if (posVerticeOrigen != posVerticeDestino) {
            List<Integer>adyacentesDelDestino = this.listaDeAdyacencias.get(posicionDelDestino);
            int posicionDelOrigen = adyacentesDelDestino.indexOf(posVerticeOrigen);
            adyacentesDelDestino.remove(posicionDelOrigen);
        }
    }


    //1.
    //Para un grafo no dirigido implementar el método de eliminar vértice.
    public void eliminarVertice( int posVertice) {
        validarVertice(posVertice);
        this.listaDeAdyacencias.remove(posVertice);
        for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
            List<Integer> adyacentesDeVertice = this.listaDeAdyacencias.get(i);
            int posicionDeVerticeEnAdy = adyacentesDeVertice.indexOf(posVertice);
            if (posicionDeVerticeEnAdy >= 0) {
                adyacentesDeVertice.remove(posicionDeVerticeEnAdy);
            }
            for (int j = 0; j < adyacentesDeVertice.size(); j++) {
                if (adyacentesDeVertice.get(j) > posVertice ) {
                    adyacentesDeVertice.set(j, adyacentesDeVertice.get(j) - 1);
                }
            }

        }
    }



}
