package at.ac.tuwien.auto.iotsys.control;

import at.ac.tuwien.auto.iotsys.commons.ObjectBroker;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.actuators.BoilerActuator;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.actuators.HeatPumpActuator;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.actuators.TemperatureControlActuator;
import at.ac.tuwien.auto.iotsys.obix.observer.Observer;
import at.ac.tuwien.auto.iotsys.obix.observer.Subject;
import obix.Obj;
import obix.Uri;

/**
 * Basic client interaction with the oBIX object broker.
 * 
 * @author Markus Jung
 */
public class TestClient implements Observer {
	private ObjectBroker objectBroker;

	public TestClient(ObjectBroker objectBroker) {
		this.objectBroker = objectBroker;
	}

	public void runTests() {
		System.out.println("\n\nAvailable named objects: ");
		for (Obj o : objectBroker.pullObj(new Uri("/"), true).list()) {
			if (o.getName() != null && !o.getName().isEmpty())
				System.out.println("  " + o.getName());
		}

		System.out.println("\nObserving all objects.");
		for (Obj o : objectBroker.pullObj(new Uri("/"), true).list()) {
			if (o.getName() != null && !o.getName().isEmpty())
				o.attach(this);
		}

		System.out.println("\nReading kuelhaus_hesselbachweg_5.");

		Obj obj = objectBroker.pullObj(new Uri("/kuehlhaus_hesselbachweg_5"), true);

		if (obj instanceof TemperatureControlActuator) {
			TemperatureControlActuator kuehlhaus = (TemperatureControlActuator) obj;
			System.out.println("\nKï¿½hlhaus Hesselbachweg 5.");

			System.out.println("  Vorgabe Raumtemperatur-Sollwert: " + kuehlhaus.targetValue());
			System.out.println("  Aktueller Raumtemperatur-Istwert: " + kuehlhaus.actualValue());
			System.out.println("  Aktueller Raumtemperatur-Sollwert: " + kuehlhaus.actualTargetValue());
			System.out.println("  Aktueller Zustand Freigabe Kï¿½ltemaschine: " + kuehlhaus.active());

			System.out.println("Setting new target temperature value for Kuehlhaus.");
			kuehlhaus.targetValue().set(-10);
		}

		obj = objectBroker.pullObj(new Uri("/boiler_hesselbachweg_3"), true);

		if (obj instanceof BoilerActuator) {
			BoilerActuator boiler = (BoilerActuator) obj;
			System.out.println("\nElektroboiler Hesselbachweg 3.");

			System.out.println("  Freigabe Elektro-Heizstab: " + boiler.enabled());

			System.out.println("\nEnabling boiler.");
			boiler.enabled().set(true);
		}

		obj = objectBroker.pullObj(new Uri("/linbachstrasse_4_room_1"), true);

		if (obj instanceof TemperatureControlActuator) {
			TemperatureControlActuator electroHeating = (TemperatureControlActuator) obj;
			System.out.println("\nElektro-Direktheizung Linbachstrasse 4.");

			System.out.println("  Vorgabe Raumtemperatur-Sollwert: " + electroHeating.targetValue());
			System.out.println("  Aktueller Raumtemperatur-Istwert: " + electroHeating.actualValue());
			System.out.println("  Aktueller Raumtemperatur-Sollwert: " + electroHeating.actualTargetValue());
			System.out.println("  Aktueller Zustand Heizung (ein/aus): " + electroHeating.active());
		}

		obj = objectBroker.pullObj(new Uri("/heatpump_lienbachstrasse_5"), true);

		if (obj instanceof HeatPumpActuator) {
			HeatPumpActuator heatPump = (HeatPumpActuator) obj;
			System.out.println("\nWï¿½rmepumpe Lienbachstraï¿½e 5.");

			System.out.println("  Sperre Wï¿½rmepumpe: " + heatPump.disabled());
			System.out.println("  Sollwertbeeinflussung: " + heatPump.targetValueInfluence());

			System.out.println("\nDisabling heat pump and enabling target value influence.");
			heatPump.disabled().set(true);
			heatPump.targetValueInfluence().set(true);
		}
	}

	@Override
	public void update(Object state) {
		Obj obj = (Obj) state;
		System.out.println("\nObserved update on: " + obj.getName());

		if (obj.getName().equals("kuehlhaus_hesselbachweg_5")) {
			if (obj instanceof TemperatureControlActuator) {
				TemperatureControlActuator kuehlhaus = (TemperatureControlActuator) obj;
				System.out.println("\nKï¿½hlhaus Hesselbachweg 5 (Observed).");

				System.out.println("  Vorgabe Raumtemperatur-Sollwert: " + kuehlhaus.targetValue());
				System.out.println("  Aktueller Raumtemperatur-Istwert: " + kuehlhaus.actualValue());
				System.out.println("  Aktueller Raumtemperatur-Sollwert: " + kuehlhaus.actualTargetValue());
				System.out.println("  Aktueller Zustand Freigabe Kï¿½ltemaschine: " + kuehlhaus.active());
			}
		}

		if (obj instanceof BoilerActuator) {
			BoilerActuator boiler = (BoilerActuator) obj;
			System.out.println("\n" + boiler.getName() + " (Observed).");
			System.out.println("  Freigabe Elektro-Heizstab: " + boiler.enabled());
		}

		if (obj.getName().equals("linbachstrasse_4_room_1")) {
			if (obj instanceof TemperatureControlActuator) {
				TemperatureControlActuator kuehlhaus = (TemperatureControlActuator) obj;
				System.out.println("\nKï¿½hlhaus Hesselbachweg 5 (Observed).");

				System.out.println("  Vorgabe Raumtemperatur-Sollwert: " + kuehlhaus.targetValue());
				System.out.println("  Aktueller Raumtemperatur-Istwert: " + kuehlhaus.actualValue());
				System.out.println("  Aktueller Raumtempratur-Sollwert: " + kuehlhaus.actualTargetValue());
				System.out.println("  Aktueller Zustand Heizung (ein/aus): " + kuehlhaus.active());
			}
		}

		if (obj instanceof HeatPumpActuator) {
			HeatPumpActuator heatPump = (HeatPumpActuator) obj;
			System.out.println("  Sperre Wï¿½rmepumpe: " + heatPump.disabled());
			System.out.println("  Sollwertbeeinflussung: " + heatPump.targetValueInfluence());
		}
	}

	@Override
	public void setSubject(Subject object) {
		// leave empty
	}

	@Override
	public Subject getSubject() {
		// leave empty - multiple objects observed
		return null;
	}

}
