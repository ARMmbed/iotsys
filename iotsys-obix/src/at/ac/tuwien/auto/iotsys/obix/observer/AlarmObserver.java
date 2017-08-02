package at.ac.tuwien.auto.iotsys.obix.observer;

import java.util.TimeZone;

import obix.Abstime;
import obix.AlarmCondition;
import obix.AlarmSource;
import obix.contracts.Alarm;
import obix.contracts.AlarmSubject;
import obix.contracts.StatefulAlarm;

/**
 * Observes an AlarmSource for alarming conditions and generates Alarms
 *
 */
public abstract class AlarmObserver implements Observer {
	private AlarmSource source, target;
	private AlarmSubject alarmSubject;
	private Alarm currentAlarm;
	private boolean stateful, acked;
	private AlarmCondition alarmCondition;

	public AlarmObserver(AlarmSubject alarmSubject, AlarmCondition alarmCondition, boolean stateful, boolean acked) {
		this.alarmSubject = alarmSubject;
		this.alarmCondition = alarmCondition;
		this.stateful = stateful;
		this.acked = acked;
	}

	/**
	 * @param source
	 *            the alarm source to be checked for an alarm condition
	 * @return <code>true</code> if <code>source</code> is currently in an alarm
	 *         condition, <code>false</code> otherwise
	 */
	private boolean inAlarmCondition() {
		if (alarmCondition == null)
			return false;
		return alarmCondition.inAlarmCondition(source);
	}

	/**
	 * Generates a new alarm
	 * 
	 * @return the generated alarm
	 */
	public abstract Alarm generateAlarm();

	private void notifyAlarmSubject(Alarm alarm) {
		if (alarmSubject != null)
			alarmSubject.addAlarm(alarm);
	}

	@Override
	public synchronized void update(Object state) {
		if (inAlarmCondition()) {
			this.setOffNormal();
		} else if (currentAlarm != null) {
			this.setNormal();
		}
	}

	/**
	 * Called when the observed alarm source goes into alarm condition.
	 * Generates an alarm and notifies the alarm subject.
	 */
	public void setOffNormal() {
		if (currentAlarm == null) {
			currentAlarm = generateAlarm();
			notifyAlarmSubject(currentAlarm);
		}

		getTarget().setOffNormal(currentAlarm);
	}

	/**
	 * Called when the observed alarm source goes out of alarm condition. Sets
	 * the normal timestamp on stateful alarms.
	 */
	public void setNormal() {
		getTarget().setToNormal(currentAlarm);

		if (currentAlarm instanceof StatefulAlarm) {
			setNormalTimestamp((StatefulAlarm) currentAlarm);
		}

		currentAlarm = null;
	}

	private void setNormalTimestamp(StatefulAlarm alarm) {
		Abstime normalTimestamp = alarm.normalTimestamp();
		if (normalTimestamp != null) {
			normalTimestamp.set(System.currentTimeMillis(), TimeZone.getDefault());
			normalTimestamp.setNull(false);
		}
	}

	public AlarmSource getTarget() {
		if (target == null)
			return source;

		return target;
	}

	/**
	 * Sets the target. Generated Alarms should have their source set to this
	 * target.
	 * 
	 * @param target
	 *            an AlarmSource, that functions as source for generated alarms
	 * @return
	 */
	public AlarmObserver setTarget(AlarmSource target) {
		this.target = target;
		return this;
	}

	public void setSubject(Subject object) {
		if (object instanceof AlarmSource) {
			if (source != null && source != object)
				source.detach(this);

			source = (AlarmSource) object;
		}
	}

	public Subject getSubject() {
		return source;
	}

	public AlarmSubject getAlarmSubject() {
		return alarmSubject;
	}

	public AlarmObserver setAlarmSubject(AlarmSubject alarmSubject) {
		this.alarmSubject = alarmSubject;
		return this;
	}

	public boolean isStateful() {
		return stateful;
	}

	public AlarmObserver setStateful(boolean stateful) {
		this.stateful = stateful;
		return this;
	}

	public boolean isAcked() {
		return acked;
	}

	public AlarmObserver setAcked(boolean acked) {
		this.acked = acked;
		return this;
	}
}
