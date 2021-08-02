package bo.edu.uagrm.ficct.inf310sb.ed2202101.ui;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionNroVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.pesados.*;

public class TestGrafosPesados {
    public static void main(String argumentos[]) throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        DiGrafoPesado grafoPesado = new DiGrafoPesado(6);
        //grafoPesado.insertarAristas(0,1,50);
        grafoPesado.insertarAristas(0,2,10);
        grafoPesado.insertarAristas(0,4,60);
     //   grafoPesado.insertarAristas(0,5,100);
        //grafoPesado.insertarAristas(1,3,50);
        //grafoPesado.insertarAristas(1,4,15);
        grafoPesado.insertarAristas(2,1,5);
        //grafoPesado.insertarAristas(3,0,80);
        grafoPesado.insertarAristas(3,5,20);
        grafoPesado.insertarAristas(4,5,20);
       // grafoPesado.insertarAristas(5,1,40);
        grafoPesado.insertarAristas(5,2,70);

        //       grafoPesado.insertarAristas(0,3,25);
        //grafoPesado.toString();

        BFSPesado reco = new BFSPesado(grafoPesado, 0);

        System.out.println("recorrido:   " + reco.elRecorrido());


        DFSPesado recor = new DFSPesado(grafoPesado, 0);

        System.out.println("recorrido:   " + recor.elRecorrido());
        Dijkstra des = new Dijkstra(grafoPesado);
        double jer = des.costoMinimo(0,3);
        System.out.println("costo minimo:   " + jer);
        System.out.println("el camino es:  " + des.caminoCostoMinimo(0,3));
        des.entreQueVerticesHayCamino(1);
        System.out.println("listas de costos :" + des.listaDeListas);
        System.out.println("listas de costos :" + des.listasDeCostos);
    }
}
