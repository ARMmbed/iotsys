package obix.contracts;

import obix.IObj;
import obix.Real;
import obix.Str;

/**
 * Unit
 *
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface Unit extends IObj {

	public static final String CONTRACT = "obix:Unit";

	public Str symbol();

	public static final String dimensionContract = "<obj name='dimension' is='obix:Dimension'/>";

	public Dimension dimension();

	public static final String scaleContract = "<real name='scale' val='1.0'/>";

	public Real scale();

	public Real offset();

}
