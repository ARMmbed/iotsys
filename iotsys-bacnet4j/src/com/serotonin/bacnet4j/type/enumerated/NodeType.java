/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2011 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
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
package com.serotonin.bacnet4j.type.enumerated;

import com.serotonin.bacnet4j.type.primitive.Enumerated;
import com.serotonin.util.queue.ByteQueue;

/**
 * @author Matthew Lohbihler
 */
public class NodeType extends Enumerated {
	private static final long serialVersionUID = -1462203629019212150L;
	public static final NodeType unknown = new NodeType(0);
	public static final NodeType system = new NodeType(1);
	public static final NodeType network = new NodeType(2);
	public static final NodeType device = new NodeType(3);
	public static final NodeType organizational = new NodeType(4);
	public static final NodeType area = new NodeType(5);
	public static final NodeType equipment = new NodeType(6);
	public static final NodeType point = new NodeType(7);
	public static final NodeType collection = new NodeType(8);
	public static final NodeType property = new NodeType(9);
	public static final NodeType functional = new NodeType(10);
	public static final NodeType other = new NodeType(11);

	public NodeType(int value) {
		super(value);
	}

	public NodeType(ByteQueue queue) {
		super(queue);
	}
}
