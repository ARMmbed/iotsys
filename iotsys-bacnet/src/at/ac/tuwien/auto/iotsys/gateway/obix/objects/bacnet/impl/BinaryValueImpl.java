/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2013 
 * Institute of Computer Aided Automation, Automation Systems Group, TU Wien.
 * All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package at.ac.tuwien.auto.iotsys.gateway.obix.objects.bacnet.impl;

import at.ac.tuwien.auto.iotsys.commons.obix.objects.bacnet.BinaryValue;
import at.ac.tuwien.auto.iotsys.gateway.connectors.bacnet.BACnetConnector;
import at.ac.tuwien.auto.iotsys.gateway.connectors.bacnet.BacnetDataPointInfo;
import obix.Contract;

public class BinaryValueImpl extends BinaryBacnetObj implements BinaryValue {
	public BinaryValueImpl(BACnetConnector bacnetConnector, BacnetDataPointInfo dataPointInfo) {
		super(bacnetConnector, dataPointInfo);

		setIs(new Contract(BinaryValue.CONTRACT));
		value().setWritable(true);
	}

	@Override
	protected void refreshWritable() {
		// Value Objects are writable if Out_Of_Service is True
		// or if Present_Value is commandable (PriorityArray and
		// RelinquishDefault exist)
		value().setWritable(isOutOfService() || isValueCommandable());
	}
}
