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

package at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.datapoint.impl;

import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.datapoint.EnoceanDPT;
import at.ac.tuwien.auto.iotsys.commons.obix.objects.enocean.entity.impl.EnoceanEntityImpl;
import at.ac.tuwien.auto.iotsys.commons.util.UriEncoder;
import obix.Contract;
import obix.Obj;
import obix.Str;
import obix.Uri;

public abstract class EnoceanDPTImpl extends Obj implements EnoceanDPT {
	protected EnoceanEntityImpl entity;
	protected Str function = new Str();
	protected Str unit = new Str();

	public EnoceanDPTImpl(String name, String displayName, String display, EnoceanEntityImpl entity) {
		// attributes
		this.setName(name);
		this.setDisplay(display);
		this.setDisplayName(displayName);
		this.setHidden(false);

		if (entity != null) {
			this.entity = entity;
		}

		if (displayName != null)
			this.setHref(new Uri(UriEncoder.getEscapedUri(displayName)));
		else
			this.setHref(new Uri(UriEncoder.getEscapedUri(name)));

		// contracts
		this.addIs(new Contract(EnoceanDPT.CONTRACT));
	}

	public void addIs(Contract is) {
		if (this.getIs() != null) {
			Uri[] uris = new Uri[is.size() + this.getIs().size()];
			int i = 0;

			for (Uri uri : is.list()) {
				uris[i++] = uri;
			}

			for (Uri uri : this.getIs().list()) {
				uris[i++] = uri;
			}

			this.setIs(new Contract(uris));
		} else {
			this.setIs(is);
		}
	}

	public boolean isValueWritable() {
		return false;
	}

	public boolean isValueReadable() {
		return false;
	}

	abstract public void setValue(Obj value);

	abstract public Obj getValue();
}
