package at.ac.tuwien.auto.iotsys.commons.obix.objects;

import java.util.TimeZone;

import at.ac.tuwien.auto.iotsys.obix.OperationHandler;
import obix.Abstime;
import obix.AlarmSource;
import obix.Contract;
import obix.Err;
import obix.Obj;
import obix.Op;
import obix.Ref;
import obix.Str;
import obix.Uri;
import obix.contracts.AckAlarm;
import obix.contracts.AckAlarmIn;
import obix.contracts.AckAlarmOut;
import obix.contracts.Alarm;
import obix.contracts.Point;
import obix.contracts.PointAlarm;
import obix.contracts.StatefulAlarm;
import obix.contracts.WritablePoint;

public class AlarmImpl extends Obj implements Alarm, AckAlarm, StatefulAlarm, PointAlarm {
	private boolean stateful, acked, pointAlarm;

	// Alarm
	private Ref sourceRef;
	private Abstime timestamp;

	// StatefulAlarm
	private Abstime normalTimestamp;

	// AckAlarm
	private Abstime ackTimestamp;
	private Str ackUser;
	private Op ack;

	// PointAlarm
	private Obj alarmValue;

	/**
	 * Represents an Alarm generated by an Alarm Source
	 * 
	 * @param source
	 *            The object that generated the alarm
	 * @param stateful
	 *            if true, represents an StatefulAlarm
	 * @param acked
	 *            if true, represents an AckAlarm
	 */
	public AlarmImpl(AlarmSource source, boolean stateful, boolean acked) {
		this.stateful = stateful;
		this.acked = acked;

		String contract = Alarm.CONTRACT;

		sourceRef = new Ref("source", new Uri(((Obj) source).getFullContextPath()));
		add(source());
		add(timestamp());

		if (stateful) {
			add(normalTimestamp());
			contract += " " + StatefulAlarm.CONTRACT;
		}

		if (acked) {
			add(ackTimestamp());
			add(ackUser());
			add(ack());
			contract += " " + AckAlarm.CONTRACT;
		}

		this.pointAlarm = false;
		if (source instanceof Point) {
			pointAlarm = true;
		}

		Contract sourceContract = ((Obj) source).getIs();
		if (sourceContract != null) {
			pointAlarm = sourceContract.contains(new Uri(Point.CONTRACT))
					|| sourceContract.contains(new Uri(WritablePoint.CONTRACT));
		}

		if (pointAlarm) {
			add(alarmValue(source));
			contract += " " + PointAlarm.CONTRACT;
		}

		this.setIs(new Contract(contract));
	}

	public Ref source() {
		return sourceRef;
	}

	public Abstime timestamp() {
		if (timestamp == null) {
			timestamp = new Abstime("timestamp", System.currentTimeMillis());
		}
		return timestamp;
	}

	public Abstime ackTimestamp() {
		if (!isAcked())
			return null;

		if (ackTimestamp == null) {
			ackTimestamp = new Abstime("ackTimestamp");
			ackTimestamp.setNull(true);
		}
		return ackTimestamp;
	}

	public Str ackUser() {
		if (!isAcked())
			return null;

		if (ackUser == null) {
			ackUser = new Str();
			ackUser.setName("ackUser");
			ackUser.setNull(true);
		}
		return ackUser;
	}

	public Op ack() {
		if (!isAcked())
			return null;

		if (ack == null) {
			ack = new Op("ack", new Contract(AckAlarmIn.CONTRACT), new Contract(AckAlarmOut.CONTRACT));
			ack.setHref(new Uri("ack"));
			ack.setOperationHandler(new OperationHandler() {
				public Obj invoke(Obj in) {
					return ack(in);
				}
			});
		}
		return ack;
	}

	public Obj ack(Obj in) {
		if (!isAcked())
			return new Err("Cannot acknowledge alarm");
		if (!(in instanceof AckAlarmIn))
			return new Err("AckAlarmIn needed");

		AckAlarmIn ackIn = (AckAlarmIn) in;
		ack(ackIn.ackUser().get());

		return new AckAlarmOutImpl(this);
	}

	public Obj ack(String user) {
		if (!isAcked() || user == null)
			return null;

		ackUser().set(user);
		ackUser().setNull(false);
		ackTimestamp().set(System.currentTimeMillis(), TimeZone.getDefault());
		ackTimestamp().setNull(false);

		Obj source = getByHref(sourceRef.getHref());
		if (source != null)
			source.alarmAcknowledged(this);

		return new AckAlarmOutImpl(this);
	}

	public Abstime normalTimestamp() {
		if (!isStateful())
			return null;
		if (normalTimestamp == null) {
			normalTimestamp = new Abstime("normalTimestamp");
			normalTimestamp.setNull(true);
		}
		return normalTimestamp;
	}

	public Obj alarmValue() {
		return alarmValue;
	}

	private Obj alarmValue(AlarmSource source) {
		if (!isPointAlarm())
			return null;
		if (alarmValue == null) {
			alarmValue = Obj.toObj(((Obj) source).getElement());
			alarmValue.set((Obj) source);
			alarmValue.setName("alarmValue");
		}
		return alarmValue;
	}

	public boolean isStateful() {
		return stateful;
	}

	public boolean isAcked() {
		return acked;
	}

	public boolean isPointAlarm() {
		return pointAlarm;
	}

}
