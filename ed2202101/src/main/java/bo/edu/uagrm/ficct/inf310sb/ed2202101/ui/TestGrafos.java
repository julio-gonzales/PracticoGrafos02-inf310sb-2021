package bo.edu.uagrm.ficct.inf310sb.ed2202101.ui;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.AVL;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.ArbolB;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.ArbolBinarioBusqueda;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.ArbolMViasBusqueda;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.*;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.BFS;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.DFS;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesDiGrafo.*;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesGrafo.CantidadDeIslas;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados.operacionesGrafo.Ciclos;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados.DiGrafoPesado;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados.Dijkstra;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados.GrafoPesado;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados.*;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class TestGrafos {
    public static void main(String argumentos[]) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        DiGrafo grafoPrueba = new DiGrafo(4);
        DiGrafo diGrafo = new DiGrafo();
        Grafo grafo = new Grafo();
        GrafoPesado grafoPesado = new GrafoPesado();
        DiGrafoPesado diGrafoPesado = new DiGrafoPesado();

        int opcion = 0;
        int cantidadDeVertices = 0;
        int tipoDeGrafo = 0;
        int subOpcion = 0;
        int posVertice = 0;
        double pesoV = 0;
        int posVerticeOrigen;
        int posVerticeDestino;
        Scanner teclado = new Scanner(in);

        out.println("GRAFOS PESADOS Y NO PESADOS");
        out.println("1.  GRAFO");
        out.println("2.  DIGRAFO");
        out.println("3.  GRAFO PESADO");
        out.println("4.  DIGRAFO PESADO");
        out.println("Seleccione con que GRAFO desea trabajar");
        tipoDeGrafo = teclado.nextInt();

        out.println("Digite la cantidad de Vertices:  ");
        cantidadDeVertices = teclado.nextInt();

        switch (tipoDeGrafo) {
            case 1:
                grafo = new Grafo(cantidadDeVertices);
                break;

            case 2:
                diGrafo = new DiGrafo(cantidadDeVertices);
                break;

            case 3:
                grafoPesado = new GrafoPesado(cantidadDeVertices);
                break;

            case 4:
                diGrafoPesado = new DiGrafoPesado(cantidadDeVertices);
                break;
        }

        do {
            System.out.println("MENU");
            System.out.println("1.  INSERTAR NUEVO VERTICE");
            System.out.println("2.  ELIMINAR VERTICE");
            System.out.println("3.  INSERTAR ARISTA");
            System.out.println("4.  ELIMINAR ARISTA");
            System.out.println("5.  RECORRIDO DFS");
            System.out.println("6.  RECORRIDO BFS");
            if (tipoDeGrafo == 1) {
                System.out.println("7.   HAY CICLO <<pregunta 6>>");
                System.out.println("8.   ENTRE QUE VERTICES HAY CICLO <<pregunta 8>>");
                System.out.println("9.   NUMERO DE ISLAS <<pregunta 9>>");

            }
            if (tipoDeGrafo == 2) {
                System.out.println("7.   HAY CICLOS <<SIN USAR MATRIZ DE CAMINOS: pregunta 2>>");
                System.out.println("8.   COMPONESTES DE LAS ISLAS <<pregunta 3>>");
                System.out.println("9.   DESDE QUE VERTICES PUEDO LLEGAR A UN VERTICE ESPECIFICO <<pregunta 4>");
                System.out.println("10.  DESDE QUE VERTICES PUEDO LLEGAR A UN VERTICE (UTILIZANDO RECORRIDOS) <<pregunta 5>>");
                System.out.println("11:  TIENE CICLOS EL GRAFO  <<pregunta 6>>");
                System.out.println("12:  ES DEBILMENTE CONEXO  <<pregunta 7>>");
                System.out.println("13:  CANTIDAD DE ISLAS  <<pregunta 10>>");
                System.out.println("14:  ENTRE QUE VERTICES HAY CAMINO  <<pregunta 11>>");
            }
            if (tipoDeGrafo == 4) {
                System.out.println("7:  COSTO MINIMO ENTRE A Y B (DIJKSTRA)  <<pregunta 14>>");
                System.out.println("8:  CAMINOS DE COSTOS MINIMOS DESDE VERTICE V (DIJKSTRA)  <<pregunta 15>>");
            }
            //SI ES QUE ES UN GRAFO PESADO SE HABILITA ESTA OPCION
            out.println("15. SALIR");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    switch (tipoDeGrafo) {
                        case 1:
                            grafo.insertarVertice();
                            break;
                        case 2:
                            diGrafo.insertarVertice();
                            break;
                        case 3:
                            grafoPesado.insertarVertice();
                            break;
                        case 4:
                            diGrafoPesado.insertarVertice();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("DIGITE EL VERTICE A ELIMINAR: ");
                    posVertice = teclado.nextInt();
                    switch (tipoDeGrafo) {
                        case 1:
                            grafo.eliminarVertice(posVertice);
                            break;
                        case 2:
                            diGrafo.eliminarVertice(posVertice);
                            break;
                        case 3:
                            grafoPesado.eliminarVertice(posVertice);
                            break;
                        case 4:
                            diGrafoPesado.eliminarVertice(posVertice);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("POSICION DE VERTICE ORIGEN->  ");
                    posVerticeOrigen = teclado.nextInt();
                    System.out.println("POSICION DE VERTICE DESTINO->  ");
                    posVerticeDestino = teclado.nextInt();
                    switch (tipoDeGrafo) {
                        case 1:
                            grafo.insertarAristas(posVerticeOrigen, posVerticeDestino);
                            break;
                        case 2:
                            diGrafo.insertarAristas(posVerticeOrigen, posVerticeDestino);
                            break;
                        case 3:
                            System.out.println("INSERTE PESO DE LA ARISTA->  ");
                            pesoV = teclado.nextDouble();
                            grafoPesado.insertarAristas(posVerticeOrigen, posVerticeDestino, pesoV);
                            break;
                        case 4:
                            System.out.println("INSERTE PESO DE LA ARISTA->  ");
                            pesoV = teclado.nextDouble();
                            diGrafoPesado.insertarAristas(posVerticeOrigen, posVerticeDestino, pesoV);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("POSICION DE VERTICE ORIGEN->  ");
                    posVerticeOrigen = teclado.nextInt();
                    System.out.println("POSICION DE VERTICE DESTINO->  ");
                    posVerticeDestino = teclado.nextInt();
                    switch (tipoDeGrafo) {
                        case 1:
                            grafo.eliminarArista(posVerticeOrigen, posVerticeDestino);
                            break;
                        case 2:
                            diGrafo.eliminarArista(posVerticeOrigen, posVerticeDestino);
                            break;
                        case 3:
                            grafoPesado.eliminarArista(posVerticeOrigen, posVerticeDestino);
                            break;
                        case 4:
                            diGrafoPesado.eliminarArista(posVerticeOrigen, posVerticeDestino);
                            break;
                    }
                    break;
                case 5:
                    switch (tipoDeGrafo) {
                        case 1:
                            System.out.println("DIGITE EL VERTICE POR DONDE EMPEZAR->  ");
                            posVerticeOrigen = teclado.nextInt();
                            DFS dfs = new DFS(grafo, posVerticeOrigen);
                            System.out.println("EL RECORRIDO:  " + dfs);
                            break;
                        case 2:
                            System.out.println("DIGITE EL VERTICE POR DONDE EMPEZAR->  ");
                            posVerticeOrigen = teclado.nextInt();
                            DFS recorrido = new DFS(diGrafo, posVerticeOrigen);
                            System.out.println("EL RECORRIDO:  " + recorrido);
                            break;
                        case 3:
                            System.out.println("DIGITE EL VERTICE POR DONDE EMPEZAR->  ");
                            posVerticeOrigen = teclado.nextInt();
                            DFSPesado dfsPesado = new DFSPesado(grafoPesado, posVerticeOrigen);
                            System.out.println("EL RECORRIDO:  " + dfsPesado.elRecorrido());
                            break;
                        case 4:
                            System.out.println("DIGITE EL VERTICE POR DONDE EMPEZAR->  ");
                            posVerticeOrigen = teclado.nextInt();
                            DFSPesado dfsPesadoD = new DFSPesado(diGrafoPesado, posVerticeOrigen);
                            System.out.println("EL RECORRIDO:  " + dfsPesadoD.elRecorrido());
                            break;
                    }
                    break;
                case 6:
                    switch (tipoDeGrafo) {
                        case 1:
                            System.out.println("DIGITE EL VERTICE POR DONDE EMPEZAR->  ");
                            posVerticeOrigen = teclado.nextInt();
                            BFS bfs = new BFS(grafo, posVerticeOrigen);
                            System.out.println("EL RECORRIDO:  " + bfs);
                            break;
                        case 2:
                            System.out.println("DIGITE EL VERTICE POR DONDE EMPEZAR->  ");
                            posVerticeOrigen = teclado.nextInt();
                            BFS recorridoB = new BFS(diGrafo, posVerticeOrigen);
                            System.out.println("EL RECORRIDO:  " + recorridoB);
                            break;
                        case 3:
                            System.out.println("DIGITE EL VERTICE POR DONDE EMPEZAR->  ");
                            posVerticeOrigen = teclado.nextInt();
                            BFSPesado bfsPesado = new BFSPesado(grafoPesado, posVerticeOrigen);
                            System.out.println("EL RECORRIDO:  " + bfsPesado.elRecorrido());
                            break;
                        case 4:
                            System.out.println("DIGITE EL VERTICE POR DONDE EMPEZAR->  ");
                            posVerticeOrigen = teclado.nextInt();
                            BFSPesado bfsPesadoD = new BFSPesado(diGrafoPesado, posVerticeOrigen);
                            System.out.println("EL RECORRIDO:  " + bfsPesadoD.elRecorrido());
                            break;
                    }
                    break;
                case 7:
                    switch (tipoDeGrafo) {
                        case 1:
                            Ciclos ciclos = new Ciclos(grafo);
                            System.out.println("HAY CICLOS EN EL GRAFO:  " + ciclos.existeCiclo());
                            break;
                        case 2:
                            CiclosDiGrafo ciclosD = new CiclosDiGrafo(diGrafo);
                            System.out.println("HAY CICLOS EN EL DIGRAFO:  " + ciclosD.hayCiclos());
                            break;
                        case 4:
                            Dijkstra dijkstra = new Dijkstra(diGrafoPesado);
                            System.out.println("DIGITE EL VERTICE DE PARTIDA: ");
                            posVerticeOrigen = teclado.nextInt();
                            System.out.println("DIGITE EL VERTICE DE DESTINO: ");
                            posVerticeDestino = teclado.nextInt();
                            System.out.println("el camino de costo minimo entre: "+ posVerticeOrigen + " y "+
                                    posVerticeDestino+ " es: " + dijkstra.costoMinimo(posVerticeOrigen, posVerticeDestino));
                            break;
                    }
                    break;
                case 8:
                    switch (tipoDeGrafo) {
                        case 1:
                            Ciclos ciclos = new Ciclos(grafo);
                            System.out.println("ENTRE QUE VERTICES HAY CICLOS:  "+ ciclos.listaDeVerticesConCiclos());
                            break;
                        case 2:
                            CantidadDeIslasDiGrafo isla = new CantidadDeIslasDiGrafo(diGrafo);
                            System.out.println("COMPONESTES DE ISLAS: ");
                            List<List<Integer>> listaDeIslas = isla.componentesDeIslas();
                            for (int i = 0; i < listaDeIslas.size(); i++){
                                System.out.println("Componente isla: "+ i + " -> "+ listaDeIslas.get(i));
                            }
                            break;
                        case 4:
                            System.out.println("DIGITE EL VERTICE ORIGEN");
                            posVerticeOrigen = teclado.nextInt();
                            Dijkstra dijkstra = new Dijkstra(diGrafoPesado);
                            dijkstra.entreQueVerticesHayCamino(posVerticeOrigen);
                            for (int i = 0; i < dijkstra.listaDeListas.size(); i++) {
                                int indiceOrigen = dijkstra.listaDeListas.get(i).get(0);
                                int indiceDestino = dijkstra.listaDeListas.get(i).get(dijkstra.listaDeListas.get(i).size() - 1);
                                System.out.println(
                                        "Hay camino entre " + indiceOrigen + " y " + indiceDestino + " y el camino es: "
                                        + dijkstra.listaDeListas.get(i)
                                );
                            }
                            break;
                    }
                    break;
                case 9:
                    switch (tipoDeGrafo) {
                        case 1:
                            CantidadDeIslas islasG = new CantidadDeIslas(grafo);
                            System.out.println("LA Cantidad de Islas en el grafo es: " + islasG.cantidadDeIslas());
                            break;
                        case 2:
                            int verticeObjetivo;
                            System.out.println("DIGITE UN VERTICE OBJETIVO: ");
                            verticeObjetivo = teclado.nextInt();
                            LlegarAUnVertice llegar = new LlegarAUnVertice(diGrafo);
                            System.out.println("DESDE ESTOS VERTICES " + llegar.llegarAUnVerticeSinRecorrido(verticeObjetivo) +
                                    "PUEDO LLEGAR A ESTE VERTICE " + verticeObjetivo);
                            break;
                    }
                    break;
                case 10:
                    switch (tipoDeGrafo) {
                        case 2:
                            int verticeObjetivo;
                            System.out.println("DIGITE UN VERTICE OBJETIVO: ");
                            verticeObjetivo = teclado.nextInt();
                            LlegarAUnVertice llegar = new LlegarAUnVertice(diGrafo);
                            System.out.println("DESDE ESTOS VERTICES " + llegar.llegarAUnVertice(verticeObjetivo) +
                                    "PUEDO LLEGAR A ESTE VERTICE " + verticeObjetivo);
                        break;
                    }
                    break;
                case 11:
                    switch (tipoDeGrafo) {
                        case 2:
                            CiclosDiGrafo ciclosD = new CiclosDiGrafo(diGrafo);
                            System.out.println("HAY CICLOS EN EL DIGRAFO:  " + ciclosD.hayCiclosConMatrizDeCaminos());
                            break;
                    }
                    break;
                case 12:
                    switch (tipoDeGrafo) {
                        case 2:
                            FuertementeConexo debil = new FuertementeConexo(diGrafo);
                            System.out.println("El DiGrafo es DEBILMENTE CONEXO  " + debil.debilmenteConexo());
                            break;
                    }
                    break;
                case 13:
                    switch (tipoDeGrafo) {
                        case 2:
                            CantidadDeIslasDiGrafo cantidadIslas = new CantidadDeIslasDiGrafo(diGrafo);
                            System.out.println("LA CANTIDAD DE ISLAS QUE HAY EN EL DIGRAFO ES: " + cantidadIslas.cantidadDeIslasMejorado());
                            break;
                    }
                    break;
                case 14:
                    switch (tipoDeGrafo) {
                        case 2:
                            Wharshall caminos = new Wharshall(diGrafo);
                            System.out.println(caminos.entreQueVerticesHayCamino());
                    }
                    break;
            }
        }
        while (opcion < 15);

    }
}
