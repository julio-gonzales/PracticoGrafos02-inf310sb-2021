package bo.edu.uagrm.ficct.inf310sb.ed2202101.grafos.nopesados;

import java.util.ArrayList;
import java.util.List;

public class UtilsRecorridos {
    private List<Boolean> marcados;


    public UtilsRecorridos(int nroVertices) {
        marcados = new ArrayList<>();
        for (int i = 0; i < nroVertices; i++) {
            marcados.add(Boolean.FALSE);
        }
    }

    public void marcarVertice(int posVertice) {
        marcados.set(posVertice, Boolean.TRUE);
    }

    public boolean estaVerticeMarcado(int posVertice) {
        return marcados.get(posVertice);
    }

    public void desmarcarTodos() {
        for (int i = 0; i < marcados.size(); i++) {
            marcados.add(Boolean.FALSE);
        }
    }

    public boolean estanTodosMarcado() {
        for (Boolean marcado : marcados ) {
            if (!marcado) {
                return false;
            }
        }
        return  true;
    }

}
