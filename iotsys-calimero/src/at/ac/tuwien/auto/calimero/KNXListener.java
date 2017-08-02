/*
    Calimero - A library for KNX network access
    Copyright (C) 2006-2008 W. Kastner

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package at.ac.tuwien.auto.calimero;

import java.util.EventListener;

/**
 * The base listener interface to receive events of the communication with a KNX
 * network.
 * <p>
 */
public interface KNXListener extends EventListener {
	/**
	 * Arrival of a new KNX message frame.
	 * <p>
	 * 
	 * @param e
	 *            frame event object
	 */
	void frameReceived(FrameEvent e);

	/**
	 * The connection has been closed.
	 * <p>
	 * 
	 * @param e
	 *            connection close event object
	 */
	void connectionClosed(CloseEvent e);
}
