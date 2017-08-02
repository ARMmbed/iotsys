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

package at.ac.tuwien.auto.iotsys.gateway.obix.objects.iot.actuators.impl.coap;

import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.IoTSySDevice;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.actuators.LedsActuator;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.actuators.impl.LedsActuatorImpl;
import at.ac.tuwien.auto.iotsys.gateway.connectors.coap.CoapConnector;

//import java.util.logging.Logger;

import ch.ethz.inf.vs.californium.coap.Response;
import ch.ethz.inf.vs.californium.coap.ResponseHandler;
import obix.Bool;
import obix.Obj;

public class LedsActuatorImplCoap extends LedsActuatorImpl implements IoTSySDevice {
	// private static final Logger log =
	// Logger.getLogger(LedsActuatorImplCoap.class.getName());

	private CoapConnector coapConnector;
	private String busAddress;
	@SuppressWarnings("unused")
	private boolean redObserved;
	@SuppressWarnings("unused")
	private boolean blueObserved;
	@SuppressWarnings("unused")
	private boolean greenObserved;
	private boolean shouldObserve;
	private boolean forwardGroupAddress;

	public LedsActuatorImplCoap(CoapConnector coapConnector, String busAddress, boolean shouldObserve,
			boolean forwardGroupAddress) {
		// technology specific initialization
		this.coapConnector = coapConnector;
		this.busAddress = busAddress;
		this.redObserved = false;
		this.blueObserved = false;
		this.greenObserved = false;
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
		coapConnector.createWatchDog(busAddress, LED_BLUE_CONTRACT_HREF, new ResponseHandler() {
			public void handleResponse(Response response) {
				String payload = response.getPayloadString().trim();

				if (payload.equals("") || payload.equals("TooManyObservers"))
					return;

				if (payload.startsWith("Added")) {
					blueObserved = true;
					return;
				}
				boolean temp = Boolean.parseBoolean(CoapConnector.extractAttribute("bool", "val", payload));
				LedsActuatorImplCoap.this.blue().set(temp);
			}
		});

		coapConnector.createWatchDog(busAddress, LED_RED_CONTRACT_HREF, new ResponseHandler() {
			public void handleResponse(Response response) {
				String payload = response.getPayloadString().trim();

				if (payload.equals("") || payload.equals("TooManyObservers"))
					return;

				if (payload.startsWith("Added")) {
					redObserved = true;
					return;
				}
				boolean temp = Boolean.parseBoolean(CoapConnector.extractAttribute("bool", "val", payload));
				LedsActuatorImplCoap.this.red().set(temp);
			}
		});

		coapConnector.createWatchDog(busAddress, LED_GREEN_CONTRACT_HREF, new ResponseHandler() {
			public void handleResponse(Response response) {
				String payload = response.getPayloadString().trim();

				if (payload.equals("") || payload.equals("TooManyObservers"))
					return;

				if (payload.startsWith("Added")) {
					greenObserved = true;
					return;
				}
				boolean temp = Boolean.parseBoolean(CoapConnector.extractAttribute("bool", "val", payload));
				LedsActuatorImplCoap.this.green().set(temp);
			}
		});
	}

	@Override
	public void writeObject(Obj input) {
		// A write on this object was received, update the according data point.
		// The base class knows how to update the internal variable and to
		// trigger
		// all the oBIX specific processing.
		super.writeObject(input);

		// write it out to the technology bus
		// if a data point has changed only write to the data point
		String resourceUriPath = "";
		if (input.getHref() == null) {
			resourceUriPath = input.getInvokedHref().substring(input.getInvokedHref().lastIndexOf('/') + 1);
		} else {
			resourceUriPath = input.getHref().get();
		}
		if (input instanceof LedsActuator) {
			coapConnector.writeBoolean(busAddress, LED_BLUE_CONTRACT_HREF, this.blue().get());
			coapConnector.writeBoolean(busAddress, LED_RED_CONTRACT_HREF, this.red().get());
			coapConnector.writeBoolean(busAddress, LED_GREEN_CONTRACT_HREF, this.green().get());
		} else if (input instanceof Bool) {

			if (LedsActuator.LED_BLUE_CONTRACT_HREF.equals(resourceUriPath)) {
				coapConnector.writeBoolean(busAddress, LED_BLUE_CONTRACT_HREF, this.blue().get());
			} else if (LedsActuator.LED_RED_CONTRACT_HREF.equals(resourceUriPath)) {
				coapConnector.writeBoolean(busAddress, LED_RED_CONTRACT_HREF, this.red().get());
			} else if (LedsActuator.LED_GREEN_CONTRACT_HREF.equals(resourceUriPath)) {
				coapConnector.writeBoolean(busAddress, LED_GREEN_CONTRACT_HREF, this.green().get());
			}
		}
	}

	@Override
	public void refreshObject() {
		// value is the protected instance variable of the base class
		// (FanSpeedActuatorImpl)
		// if(blue != null && !blueObserved){
		// Boolean value = coapConnector.readBoolean(busAddress,
		// LED_BLUE_CONTRACT_HREF);
		// this.blue().set(value);
		// }
		// if(red != null && !redObserved){
		// Boolean value = coapConnector.readBoolean(busAddress,
		// LED_RED_CONTRACT_HREF);
		// this.red().set(value);
		// }
		// if(green != null && !greenObserved){
		// Boolean value = coapConnector.readBoolean(busAddress,
		// LED_GREEN_CONTRACT_HREF);
		// this.green().set(value);
		// }
	}

	@Override
	public String getBusAddress() {
		return busAddress;
	}

	@Override
	public boolean forwardGroupAddress() {
		return this.forwardGroupAddress;
	}

}
