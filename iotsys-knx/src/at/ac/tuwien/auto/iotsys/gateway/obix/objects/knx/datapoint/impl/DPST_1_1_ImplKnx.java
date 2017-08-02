package at.ac.tuwien.auto.iotsys.gateway.obix.objects.knx.datapoint.impl;

import java.util.logging.Logger;

import at.ac.tuwien.auto.calimero.GroupAddress;
import at.ac.tuwien.auto.calimero.dptxlator.DPTXlatorBoolean;
import at.ac.tuwien.auto.calimero.exception.KNXException;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.general.datapoint.impl.DPST_1_1_Impl;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.general.encoding.EncodingOnOff;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.general.encoding.impl.EncodingsImpl;
import at.ac.tuwien.auto.iotsys.gateway.connectors.knx.KNXConnector;
import at.ac.tuwien.auto.iotsys.gateway.connectors.knx.KNXWatchDog;
import obix.Obj;

public class DPST_1_1_ImplKnx extends DPST_1_1_Impl {
	private static final Logger log = Logger.getLogger(DPST_1_1_ImplKnx.class.getName());

	private GroupAddress groupAddress;
	private KNXConnector connector;

	public DPST_1_1_ImplKnx(KNXConnector connector, GroupAddress groupAddress, String name, String displayName,
			String display, boolean writable, boolean readable) {
		super(name, displayName, display, writable, readable);

		this.groupAddress = groupAddress;
		this.connector = connector;

		this.createWatchDog();
	}

	public DPST_1_1_ImplKnx(KNXConnector connector, DataPointInit dataPointInit) {
		this(connector, dataPointInit.getGroupAddress(), dataPointInit.getName(), dataPointInit.getDisplayName(),
				dataPointInit.getDisplay(), dataPointInit.isWritable(), dataPointInit.isReadable());
	}

	public void createWatchDog() {
		if (connector != null && groupAddress != null) {
			connector.addWatchDog(groupAddress, new KNXWatchDog() {
				@Override
				public void notifyWatchDog(byte[] apdu) {
					try {
						DPTXlatorBoolean x = new DPTXlatorBoolean(DPTXlatorBoolean.DPT_SWITCH);
						// ProcessCommunicatorImpl.extractGroupASDU(apdu, x);
						x.setData(apdu, 0); // apdu is different!

						log.info("Switch for " + DPST_1_1_ImplKnx.this.getHref() + " now " + x.getValueBoolean());

						value().set(x.getValueBoolean());
						value().setNull(false);
						encoding().set(EncodingsImpl.getInstance().getEncoding(EncodingOnOff.HREF).getName(value()));
						encoding().setNull(false);
					} catch (KNXException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	@Override
	public void refreshObject() {
		// here we need to read from the bus, only if the read flag is set at
		// the data point
		if (this.value().isReadable()) {
			boolean value = connector.readBool(groupAddress);

			this.value().set(value);
			this.value().setNull(false);
			this.encoding().setNull(false);
		}

		// run refresh from super class
		super.refreshObject();
	}

	@Override
	public void writeObject(Obj obj) {
		if (this.value().isWritable()) {
			// always pass the writeObject call to the super method (triggers,
			// oBIX related internal services like watches, alarms, ...)
			// also the internal instance variables get updated
			super.writeObject(obj);

			// set isNull to false
			this.value().setNull(false);
			this.encoding().setNull(false);

			// now write this.value to the KNX bus
			connector.write(groupAddress, this.value().get());
		}
	}
}
