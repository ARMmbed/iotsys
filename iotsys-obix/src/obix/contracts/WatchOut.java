package obix.contracts;

import obix.IObj;
import obix.List;

/**
 * WatchOut
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface WatchOut extends IObj {
	public static final String CONTRACT = "obix:WatchOut";

	public List values();

}
