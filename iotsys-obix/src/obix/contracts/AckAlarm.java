package obix.contracts;

import obix.Abstime;
import obix.IObj;
import obix.Op;
import obix.Str;

/**
 * AckAlarm
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface AckAlarm extends IObj, Alarm {
	public static final String CONTRACT = "obix:AckAlarm";

	public static final String ackTimestampContract = "<abstime name='ackTimestamp' val='1969-12-31T19:00:00.000-05:00' null='true'/>";

	public Abstime ackTimestamp();

	public static final String ackUserContract = "<str name='ackUser' val='' null='true'/>";

	public Str ackUser();

	public static final String ackContract = "<op name='ack' in='" + AckAlarmIn.CONTRACT + "' out='"
			+ AckAlarmOut.CONTRACT + "'/>";

	public Op ack();

}
