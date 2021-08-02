package bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones;

public class ExcepcionNroVerticesInvalido extends Exception {

    public ExcepcionNroVerticesInvalido(){
        super("No se aceptan valores inferiores a 1");
    }

    public ExcepcionNroVerticesInvalido(String msg) {
        super(msg);
    }
}
