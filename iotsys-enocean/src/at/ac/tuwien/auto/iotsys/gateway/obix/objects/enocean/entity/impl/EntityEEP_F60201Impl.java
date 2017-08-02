/*******************************************************************************
 * Copyright (c) 2014
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

package at.ac.tuwien.auto.iotsys.gateway.obix.objects.enocean.entity.impl;

import java.util.logging.Logger;

import org.opencean.core.ESP3Host;
import org.opencean.core.EnoceanWatchdog;
import org.opencean.core.StateChanger;
import org.opencean.core.address.EnoceanId;
import org.opencean.core.common.EEPId;
import org.opencean.core.common.values.ByteStateAndStatus;
import org.opencean.core.packets.BasicPacket;
import org.opencean.core.packets.RadioPacketRPS;
import org.opencean.core.utils.Bits;

import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.datapoint.impl.EnoceanDPTBoolOnOffImpl;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.datapoint.impl.EnoceanDPTBoolPressedReleasedImpl;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.entity.EntityEEP_F60201;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.entity.impl.EnoceanEntityImpl;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.general.encoding.EncodingPressedReleased;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.general.encoding.impl.EncodingsImpl;
import obix.Bool;
import obix.Int;
import obix.Obj;
import obix.Str;

//constructor
public class EntityEEP_F60201Impl extends EnoceanEntityImpl implements EntityEEP_F60201 {
	private static Logger log = Logger.getLogger(EntityEEP_F60201Impl.class.getName());

	protected final ESP3Host esp3Host;
	protected final EnoceanId id;
	EnoceanDPTBoolOnOffImpl datapoint_lightonoff;
	EnoceanDPTBoolPressedReleasedImpl datapoint_energybow;

	public EntityEEP_F60201Impl(ESP3Host esp3Host, EnoceanId id, String name, String displayName, String display,
			String manufacturer) {
		super(name, displayName, display, manufacturer);

		this.esp3Host = esp3Host;
		this.id = id;
		this.setWritable(true);
		this.setReadable(true);

		// add datapoints of the EEP
		// Create and add new datapoint for the switch, channel B
		datapoint_lightonoff = new EnoceanDPTBoolOnOffImpl("WallTransmitterChB", "Switch, Channel B", "On/Off", this,
				true, true);
		datapoint_lightonoff.addTranslation("de-DE", TranslationAttribute.displayName, "Schalter, Kanal B");
		this.addDatapoint(datapoint_lightonoff);

		// Create and add new datapoint for the teach in mode
		datapoint_energybow = new EnoceanDPTBoolPressedReleasedImpl("WallTransmitterEnergyBow", "Energy Bow",
				"Pressed/Released", this, false, false);
		datapoint_energybow.addTranslation("de-DE", TranslationAttribute.displayName, "Energieart");
		this.addDatapoint(datapoint_energybow);

		// Add a new watchdog for value changes
		esp3Host.addWatchDog(id, new EnoceanWatchdog() {

			@Override
			public void notifyWatchDog(BasicPacket packet) {
				if (packet instanceof RadioPacketRPS) {
					RadioPacketRPS radioPacketRPS = (RadioPacketRPS) packet;
					Bool pressbit = new Bool(Bits.isBitSet(radioPacketRPS.getDataByte(), 4));
					if (radioPacketRPS.getDataByte() == ByteStateAndStatus.ON) {
						log.info("EnOcean device with ID " + radioPacketRPS.getSenderId().toString() + ": switch on");
						// set datapoint_lightonoff to ON
						datapoint_lightonoff.setValue(new Bool(true));
					} else if (radioPacketRPS.getDataByte() == ByteStateAndStatus.OFF) {
						log.info("EnOcean device with ID " + radioPacketRPS.getSenderId().toString() + ": switch off");
						// set datapoint_lightonoff to OFF
						datapoint_lightonoff.setValue(new Bool(false));
					}

					log.info("EnOcean device with ID " + radioPacketRPS.getSenderId().toString() + ": Energy bow: "
							+ EncodingsImpl.getInstance().getEncoding(EncodingPressedReleased.HREF).getName(pressbit));
					datapoint_energybow.setValue(pressbit);
					EntityEEP_F60201Impl.this.notifyObservers();
				}
			}
		});
	}

	@Override
	public void initialize() {
		super.initialize();
		// But stuff here that should be executed after object creation
	}

	@Override
	public void writeObject(Obj input) {
		super.writeObject(input);

		// check if it's possible to write a new value to the object
		if (this.isWritable()) {
			log.info("Start writing value " + input);
			String value = "OFF";

			// check the type the value of the new parameter
			if (input instanceof Str) {
				value = ((Str) input).get().equalsIgnoreCase("ON") ? "ON" : "OFF";
			} else if (input instanceof Bool) {
				value = ((Bool) input).get() ? "ON" : "OFF";
			} else if (input instanceof Int) {
				value = (((Int) input).get() != 0) ? "ON" : "OFF";
			}

			// create a new EnOcean packet with the new state
			StateChanger change = new StateChanger();
			BasicPacket packet = change.changeState(value, id, EEPId.EEP_F6_02_01.toString());
			log.info("Write: Send packet: " + packet.toString());
			esp3Host.sendRadio(packet);
		}

	}

	@Override
	public void refreshObject() {
		// here we need to read from the bus, only if the read flag is set at
		// the data point
		if (this.isReadable()) {
			// value can not be read from a wall transmitter
		}

		// run refresh from super class
		super.refreshObject();
	}

}
