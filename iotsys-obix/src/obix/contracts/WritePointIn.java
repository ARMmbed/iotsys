package obix.contracts;

import obix.IObj;
import obix.Obj;

/**
 * WritePointIn
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface WritePointIn extends IObj {
	public static final String CONTRACT = "obix:WritePointIn";

	public Obj value();

}
