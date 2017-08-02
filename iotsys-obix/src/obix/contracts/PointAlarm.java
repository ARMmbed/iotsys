package obix.contracts;

import obix.IObj;
import obix.Obj;

/**
 * PointAlarm
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface PointAlarm extends IObj, Alarm {
	public static final String CONTRACT = "obix:PointAlarm";

	public Obj alarmValue();

}
