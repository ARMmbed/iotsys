package obix.contracts;

import obix.IObj;
import obix.Obj;

/**
 * Write
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface Write extends IObj {
	public static final String CONTRACT = "obix:Write";

	public Obj in();

}
