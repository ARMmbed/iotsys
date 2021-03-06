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
package com.serotonin.bacnet4j.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SequenceDefinition implements Serializable {
	private static final long serialVersionUID = 6464244006575549887L;

	private final List<ElementSpecification> elements;

	public SequenceDefinition(ElementSpecification... specs) {
		elements = new ArrayList<>();
		for (ElementSpecification spec : specs)
			elements.add(spec);
	}

	public SequenceDefinition(List<ElementSpecification> elements) {
		this.elements = elements;
	}

	public List<ElementSpecification> getElements() {
		return elements;
	}

	public static class ElementSpecification {
		private final String id;
		private final Class<? extends Encodable> clazz;
		private final int contextId;
		private final boolean sequenceOf;
		private final boolean optional;

		public ElementSpecification(String id, Class<? extends Encodable> clazz, boolean sequenceOf, boolean optional) {
			this.id = id;
			this.clazz = clazz;
			this.contextId = -1;
			this.sequenceOf = sequenceOf;
			this.optional = optional;
		}

		public ElementSpecification(String id, Class<? extends Encodable> clazz, int contextId, boolean sequenceOf,
				boolean optional) {
			this.id = id;
			this.clazz = clazz;
			this.contextId = contextId;
			this.sequenceOf = sequenceOf;
			this.optional = optional;
		}

		public String getId() {
			return id;
		}

		public Class<? extends Encodable> getClazz() {
			return clazz;
		}

		public int getContextId() {
			return contextId;
		}

		public boolean isOptional() {
			return optional;
		}

		public boolean isSequenceOf() {
			return sequenceOf;
		}

		public boolean hasContextId() {
			return contextId != -1;
		}
	}
}
