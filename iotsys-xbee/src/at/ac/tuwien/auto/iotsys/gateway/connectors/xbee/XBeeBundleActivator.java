package at.ac.tuwien.auto.iotsys.gateway.connectors.xbee;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import at.ac.tuwien.auto.iotsys.commons.DeviceLoader;
import at.ac.tuwien.auto.iotsys.commons.ObjectBroker;
import at.ac.tuwien.auto.iotsys.commons.persistent.models.Connector;

public class XBeeBundleActivator implements BundleActivator, ServiceListener {
	private static final Logger log = Logger.getLogger(XBeeBundleActivator.class.getName());

	private DeviceLoader deviceLoader = new XBeeDeviceLoaderImpl();
	private ArrayList<Connector> connectors = null;

	private volatile boolean registered = false;

	private BundleContext context = null;

	public void start(BundleContext context) throws Exception {
		log.info("Starting XBee connector");
		this.context = context;
		ServiceReference serviceReference = context.getServiceReference(ObjectBroker.class.getName());
		if (serviceReference == null) {
			log.info("Could not find a running object broker to register devices! Waiting for service announcement.");

		} else {
			synchronized (this) {
				log.info("Initiating XBee devices.");
				ObjectBroker objectBroker = (ObjectBroker) context.getService(serviceReference);
				connectors = deviceLoader.initDevices(objectBroker);
				objectBroker.addConnectors(connectors);

				registered = true;
			}

		}

		context.addServiceListener(this);
	}

	public void stop(BundleContext context) throws Exception {
		log.info("Stopping XBee connector");
		ServiceReference serviceReference = context.getServiceReference(ObjectBroker.class.getName());
		if (serviceReference == null) {
			log.severe("Could not find a running object broker to unregister devices!");
		} else {
			log.info("Removing XBee Devices.");
			ObjectBroker objectBroker = (ObjectBroker) context.getService(serviceReference);
			deviceLoader.removeDevices(objectBroker);
			if (connectors != null) {
				objectBroker.removeConnectors(connectors);
				for (Connector connector : connectors) {
					try {
						connector.disconnect();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");

		if (event.getType() == ServiceEvent.REGISTERED) {
			if (objectClass[0].equals(ObjectBroker.class.getName())) {

				synchronized (this) {
					log.info("ObjectBroker detected.");

					if (!registered) {
						ObjectBroker objectBroker = (ObjectBroker) context.getService(event.getServiceReference());
						try {
							connectors = deviceLoader.initDevices(objectBroker);
							objectBroker.addConnectors(connectors);

							registered = true;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
