/*******************************************************************************
 * Copyright (c) 2013
 * Institute of Computer Aided Automation, Automation Systems Group, TU Wien.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the Institute nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE INSTITUTE AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE INSTITUTE OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * 
 * This file is part of the IoTSyS project.
 ******************************************************************************/

package at.ac.tuwien.auto.iotsys.gateway.obix.objects.iot.actuators.impl.virtual;

import java.util.logging.Logger;

import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.actuators.impl.LightSwitchActuatorImpl;
import at.ac.tuwien.auto.iotsys.gateway.connectors.virtual.VirtualConnector;
import at.ac.tuwien.auto.iotsys.gateway.obix.objects.iot.sensors.impl.virtual.TemperatureSensorImplVirtual;
import obix.Obj;

public class LightSwitchActuatorImplVirtual extends LightSwitchActuatorImpl {
	private static final Logger log = Logger.getLogger(TemperatureSensorImplVirtual.class.getName());

	private VirtualConnector virtualConnector;
	private Object busAddress; // dummy Object, modify it according to your
								// technology

	public LightSwitchActuatorImplVirtual(VirtualConnector virtualConnector) {
		this(virtualConnector, null);
	}

	// Add further constructor parameters for bus address information for this
	// temperature sensor
	public LightSwitchActuatorImplVirtual(VirtualConnector virtualConnector, Object busAddress) {
		// technology specific initialization
		this.virtualConnector = virtualConnector;
		this.busAddress = busAddress;
	}

	@Override
	public void initialize() {
		super.initialize();
		// But stuff here that should be executed after object creation
	}

	@Override
	public void writeObject(Obj input) {
		// A write on this object was received, update the according data point.
		// The base class knows how to update the internal variable and to
		// trigger
		// all the oBIX specific processing.
		super.writeObject(input);

		// write it out to the technology bus
		virtualConnector.writeBoolean(busAddress, this.value().get());
	}

	@Override
	public void refreshObject() {
		// value is the protected instance variable of the base class
		// (TemperatureSensorImpl)
		if (value != null) {
			Boolean value = virtualConnector.readBoolean(busAddress);

			// this calls the implementation of the base class, which triggers
			// als
			// oBIX services (e.g. watches, history) and CoAP observe!

			this.value().set(value);
		}
	}
}
