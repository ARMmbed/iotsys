package org.opencean.core;

import java.util.logging.Logger;

import org.opencean.core.common.ParameterAddress;
import org.opencean.core.common.ParameterValueChangeListener;
import org.opencean.core.common.values.Value;

public class LoggingListener implements ParameterValueChangeListener {

	private static Logger logger = Logger.getLogger(LoggingListener.class.getName());

	@Override
	public void valueChanged(ParameterAddress parameterId, Value value) {
		logger.info("Received RadioPacket with value " + value);
	}

}
