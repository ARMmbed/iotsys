/*******************************************************************************
 * Copyright (c) 2013, Automation Systems Group, TU Wien.
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

package at.ac.tuwien.auto.iotsys.commons.obix.objects.general.enumeration.impl;

import at.ac.tuwien.auto.iotsys.commons.obix.objects.general.contracts.impl.RangeImpl;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.general.enumeration.EnumWeatherSymbol;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.weatherforecast.WeatherSymbol;
import obix.Contract;
import obix.Uri;

public class EnumWeatherSymbolImpl extends RangeImpl implements EnumWeatherSymbol {
	public EnumWeatherSymbolImpl() {
		super(new Uri(EnumWeatherSymbol.HREF));

		this.setOf(new Contract("obix:int"));
	}

	protected void initValues() {
		addElement(new IntElement(WeatherSymbol.NAME_UNKNOWN, WeatherSymbol.ID_UNKNOWN));
		addElement(new IntElement(WeatherSymbol.NAME_SUN, WeatherSymbol.ID_SUN));
		addElement(new IntElement(WeatherSymbol.NAME_FAIR, WeatherSymbol.ID_FAIR));
		addElement(new IntElement(WeatherSymbol.NAME_PARTLY_CLOUDY, WeatherSymbol.ID_PARTLY_CLOUDY));
		addElement(new IntElement(WeatherSymbol.NAME_CLOUDY, WeatherSymbol.ID_CLOUDY));
		addElement(new IntElement(WeatherSymbol.NAME_RAIN_SHOWERS, WeatherSymbol.ID_RAIN_SHOWERS));
		addElement(new IntElement(WeatherSymbol.NAME_RAIN_SHOWERS_THUNDER, WeatherSymbol.ID_RAIN_SHOWERS_THUNDER));
		addElement(new IntElement(WeatherSymbol.NAME_SLEET_SHOWERS, WeatherSymbol.ID_SLEET_SHOWERS));
		addElement(new IntElement(WeatherSymbol.NAME_SNOW_SHOWERS, WeatherSymbol.ID_SNOW_SHOWERS));
		addElement(new IntElement(WeatherSymbol.NAME_RAIN, WeatherSymbol.ID_RAIN));
		addElement(new IntElement(WeatherSymbol.NAME_HEAVY_RAIN, WeatherSymbol.ID_HEAVY_RAIN));
		addElement(new IntElement(WeatherSymbol.NAME_HEAVY_RAIN_THUNDER, WeatherSymbol.ID_HEAVY_RAIN_THUNDER));
		addElement(new IntElement(WeatherSymbol.NAME_SLEET, WeatherSymbol.ID_SLEET));
		addElement(new IntElement(WeatherSymbol.NAME_SNOW, WeatherSymbol.ID_SNOW));
		addElement(new IntElement(WeatherSymbol.NAME_SNOW_THUNDER, WeatherSymbol.ID_SNOW_THUNDER));
		addElement(new IntElement(WeatherSymbol.NAME_FOG, WeatherSymbol.ID_FOG));
		addElement(new IntElement(WeatherSymbol.NAME_SLEET_SHOWERS_THUNDER, WeatherSymbol.ID_SLEET_SHOWERS_THUNDER));
		addElement(new IntElement(WeatherSymbol.NAME_SNOW_SHOWERS_THUNDER, WeatherSymbol.ID_SNOW_SHOWERS_THUNDER));
		addElement(new IntElement(WeatherSymbol.NAME_RAIN_THUNDER, WeatherSymbol.ID_RAIN_THUNDER));
		addElement(new IntElement(WeatherSymbol.NAME_SLEET_THUNDER, WeatherSymbol.ID_SLEET_THUNDER));
	}
}
