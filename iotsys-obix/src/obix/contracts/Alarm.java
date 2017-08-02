package obix.contracts;

import obix.Abstime;
import obix.IObj;
import obix.Ref;

/**
 * Alarm
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface Alarm extends IObj {
	public static final String CONTRACT = "obix:Alarm";

	public Ref source();

	public Abstime timestamp();

}
