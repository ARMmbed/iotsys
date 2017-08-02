package obix.contracts;

import obix.IObj;
import obix.Str;

/**
 * AckAlarmIn
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface AckAlarmIn extends IObj {
	public static final String CONTRACT = "obix:AckAlarmIn";

	public static final String ackUserContract = "<str name='ackUser' val='' null='true'/>";

	public Str ackUser();

}
