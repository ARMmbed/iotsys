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

package at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.entity.impl;

import java.util.ArrayList;

import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.datapoint.EnoceanDPT;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.datapoint.impl.EnoceanDPTImpl;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.entity.EnoceanEntity;
import obix.Contract;
import obix.List;
import obix.Obj;
import obix.Str;
import obix.Uri;

public class EnoceanEntityImpl extends Obj implements EnoceanEntity {
	protected List list;
	protected ArrayList<EnoceanDPT> datapoints;

	public EnoceanEntityImpl(String name, String displayName, String display, String manufacturer) {
		super();

		this.setName(name);
		this.setDisplay(display);
		this.setDisplayName(displayName);
		this.setIs(new Contract(EnoceanEntity.CONTRACT));
		this.setHidden(true);

		if (manufacturer != null) {
			Str man = new Str();
			man.setName("manufacturer");
			man.setHref(new Uri("manufacturer"));
			man.set(manufacturer);
			this.add(man);
		}
	}

	public void addDatapoint(EnoceanDPTImpl datapoint) {
		if (this.datapoints == null) {
			this.list = new List("datapoints", new Contract(new String[] { "obix:ref", EnoceanDPT.CONTRACT }));
			this.list.setHref(new Uri("datapoints"));
			this.add(this.list);

			this.datapoints = new ArrayList<EnoceanDPT>();
		}

		this.list.add(datapoint);
		this.list.add(datapoint.getReference());

		for (Obj child : datapoint.list()) {
			this.add(child);
		}
		this.datapoints.add(datapoint);
	}
}
