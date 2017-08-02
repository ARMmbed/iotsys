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

package at.ac.tuwien.auto.iotsys.commons.obix.objects.iot.logic;

import obix.Bool;
import obix.IObj;
import obix.Real;

public interface TemperatureController extends IObj {
	public static final String CONTRACT = "iot:TemperatureController";

	public static final String setpointContract = "<real name='setpoint' href='setpoint' val='0'/>";

	public Real setpoint();

	public static final String temperatureContract = "<real name='temperature' href='temperature' val='0'/>";

	public Real temperature();

	public static final String controlValueContract = "<real name='controlValue' href='controlValue' val='0'/>";

	public Real controlValue();

	public static final String enabledContract = "<bool name='enabled' href='enabled' val='false'/>";

	public Bool enabled();

	public static final String toleranceContract = "<real name='tolerance' href='tolerance' val='0'/>";

	public Real tolerance();

	public static final String setpointAdjustmentContract = "<real name='setpointAdjustment' href='setpointAdjustment' val='0'/>";

	public Real setpointAdjustment();

	public static final String saveEnergyFactor = "<real name='saveEnergy' href='tolercance' val='0'/>";

	public Real saveEnergyFactor();

	public static final String saveEnergyEnabled = "<bool name='saveEnergyEnabled' href='saveEnergyEnabled' val='false'/>";

	public Bool saveEnergyEnabled();
}
