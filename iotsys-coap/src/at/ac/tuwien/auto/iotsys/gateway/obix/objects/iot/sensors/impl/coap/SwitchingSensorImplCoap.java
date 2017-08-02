/*******************************************************************************
 * Copyright (c) 2013 - IotSys CoAP Proxy
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

package at.ac.tuwien.auto.iotsys.gateway.obix.objects.iot.sensors.impl.coap;

import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.IoTSySDevice;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.sensors.impl.SwitchingSensorImpl;
import at.ac.tuwien.auto.iotsys.gateway.connectors.coap.CoapConnector;

//import java.util.logging.Logger;

import ch.ethz.inf.vs.californium.coap.Response;
import ch.ethz.inf.vs.californium.coap.ResponseHandler;
import obix.Obj;

public class SwitchingSensorImplCoap extends SwitchingSensorImpl implements IoTSySDevice {
	// private static final Logger log =
	// Logger.getLogger(SwitchingSensorImplCoap.class.getName());

	private CoapConnector coapConnector;
	private String busAddress;
	private boolean isObserved;
	private boolean shouldObserve;
	private boolean forwardGroupAddress;

	public SwitchingSensorImplCoap(CoapConnector coapConnector, String busAddress, boolean shouldObserve,
			boolean forwardGroupAddress) {
		// technology specific initialization
		this.coapConnector = coapConnector;
		this.busAddress = busAddress;
		this.isObserved = false;
		this.shouldObserve = shouldObserve;
		this.forwardGroupAddress = forwardGroupAddress;
	}

	@Override
	public void initialize() {
		super.initialize();
		// But stuff here that should be executed after object creation
		if (shouldObserve)
			addWatchDog();
	}

	public void addWatchDog() {
		coapConnector.createWatchDog(busAddress, "value", new ResponseHandler() {
			public void handleResponse(Response response) {
				String payload = response.getPayloadString().trim();

				if (payload.equals("") || payload.equals("TooManyObservers"))
					return;

				if (payload.startsWith("Added")) {
					isObserved = true;
					return;
				}
				boolean temp = Boolean.parseBoolean(CoapConnector.extractAttribute("bool", "val", payload));
				SwitchingSensorImplCoap.this.switchOnOffValue().set(temp);

			}
		});
	}

	@Override
	public void writeObject(Obj input) {
		// not writable
	}

	@Override
	public void refreshObject() {
		// switchOnOffValue is the protected instance variable of the base class
		// (SwitchingSensorImpl)
		if (switchOnOffValue() != null && !isObserved) {
			Boolean value = coapConnector.readBoolean(busAddress, "switchOnOff");
			// this calls the implementation of the base class, which triggers
			// also
			// oBIX services (e.g. watches, history) and CoAP observe!
			this.switchOnOffValue().set(value);
		}
	}

	@Override
	public String getBusAddress() {
		return busAddress;
	}

	@Override
	public boolean forwardGroupAddress() {
		return forwardGroupAddress;
	}
}
