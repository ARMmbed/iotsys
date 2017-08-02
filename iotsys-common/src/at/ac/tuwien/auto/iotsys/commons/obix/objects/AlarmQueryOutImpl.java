package at.ac.tuwien.auto.iotsys.commons.obix.objects;

import obix.Abstime;
import obix.Contract;
import obix.Int;
import obix.List;
import obix.Obj;
import obix.contracts.Alarm;
import obix.contracts.AlarmQueryOut;

public class AlarmQueryOutImpl extends Obj implements AlarmQueryOut {
	private Int count = new Int("count");
	private Abstime start = new Abstime("start");
	private Abstime end = new Abstime("end");
	private List data = new List("data", new Contract(Alarm.CONTRACT));

	public AlarmQueryOutImpl(java.util.List<Obj> alarms) {
		setIs(new Contract(AlarmQueryOut.CONTRACT));

		if (alarms != null) {
			for (Obj obj : alarms) {
				AlarmImpl alarm = (AlarmImpl) obj;

				try {
					data.add((Obj) alarm.clone());
					if (data.size() == 1 || start.get() > alarm.timestamp().get())
						start.set(alarm.timestamp().get(), start.getTimeZone());

					if (data.size() == 1 || end.get() < alarm.timestamp().get())
						end.set(alarm.timestamp().get(), end.getTimeZone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}

		if (data.size() == 0) {
			start.setNull(true);
			end.setNull(true);
		}

		count.set(data.size(), false);

		add(count);
		add(start);
		add(end);
		add(data);
	}

	@Override
	public Int count() {
		return count;
	}

	@Override
	public Abstime start() {
		return start;
	}

	@Override
	public Abstime end() {
		return end;
	}

	@Override
	public List data() {
		return data;
	}

}
