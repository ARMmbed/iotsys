package obix.contracts;

import obix.IObj;
import obix.Obj;

/**
 * Invoke
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface Invoke extends IObj {
	public static final String CONTRACT = "obix:Invoke";

	public Obj in();

}
