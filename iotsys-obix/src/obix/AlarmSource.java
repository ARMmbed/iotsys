package obix;

import java.util.LinkedList;

import at.ac.tuwien.auto.iotsys.obix.observer.Subject;
import obix.contracts.Alarm;

public interface AlarmSource extends Subject {
	public void setOffNormal(Alarm alarm);

	public void setToNormal(Alarm alarm);

	public void alarmAcknowledged(Alarm alarm);

	public boolean inAlarmState();

	public LinkedList<Alarm> getAlarms();
}
