/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones;

/**
 *
 * @author Hp 240
 */
public class ExcepcionAristaYaExiste extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionClaveNoExiste</code> without
     * detail message.
     */
    public ExcepcionAristaYaExiste() {
        super("la arista ya existe en el grafo");
    }

    /**
     * Constructs an instance of <code>ExcepcionClaveNoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionAristaYaExiste(String msg) {
        super(msg);
    }
}
