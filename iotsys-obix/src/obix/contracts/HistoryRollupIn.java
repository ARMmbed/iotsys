package obix.contracts;

import obix.IObj;
import obix.Reltime;

/**
 * HistoryRollupIn
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface HistoryRollupIn extends IObj, HistoryFilter {
	public static final String CONTRACT = "obix:HistoryRollupIn";

	public Reltime interval();

}
