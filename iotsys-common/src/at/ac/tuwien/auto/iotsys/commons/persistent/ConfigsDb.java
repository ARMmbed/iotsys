/*
  	Copyright (c) 2013 - IotSyS KNX Connector
 	Institute of Computer Aided Automation, Automation Systems Group, TU Wien.
  	All rights reserved.

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
package at.ac.tuwien.auto.iotsys.commons.persistent;

import java.util.List;

import org.ektorp.DocumentOperationResult;

import com.fasterxml.jackson.databind.JsonNode;

import at.ac.tuwien.auto.iotsys.commons.persistent.models.Connector;
import at.ac.tuwien.auto.iotsys.commons.persistent.models.Device;

/**
 * @author Nam Giang - zang at kaist dot ac dot kr
 *
 */
public interface ConfigsDb {

	public List<JsonNode> getAllConnectors();

	public List<JsonNode> getConnectors(String technology);

	public JsonNode getConnector(String connectorId);

	public JsonNode getConnectorByName(String connectorName);

	public int countConnectors();

	public int countConnectorsByTechnology(String technology);

	public void addConnector(Connector c) throws Exception;

	public List<DocumentOperationResult> addBulkConnectors(List<Connector> cs) throws Exception;

	public void updateConnector(Connector c);

	public void deleteConnector(Connector c);

	public void deleteConnector(String connectorName);

	public void deleteAllConnectors(String technology);

	public List<Device> getAllDevices();

	public List<Device> getDevices(String connectorId);

	public Device getDevice(String id);

	public int countDevices();

	public int countDevicesByTechnology(String technology);

	public void addDevice(Device d);

	public void addBulkDevices(List<Device> ds);

	public void updateDevice(Device d);

	public void deleteDevice(Device d);

	public void deleteDevice(String id);

	public void deleteAllDevices(String connectorName);

	public String getDeviceLoader(int no);

	public int getDeviceLoader(String name);

	public String[] getAllDeviceLoader();

	public void addDeviceLoader(String deviceLoader) throws Exception;

	public void addBulkDeviceLoaders(List<String> ds) throws Exception;

	public void deleteDeviceLoader(int no);

	public void deleteDeviceLoader(String deviceLoader);

	public void deleteAllDeviceLoader();

	public void updateDeviceLoader(int no, String deviceLoader);

	public void updateDeviceLoader(String oldDeviceLoader, String newDeviceLoader);

	public void clear();

	public void prepareDevice(String connectorName, Device d);

	public void prepareDeviceLoader(String deviceLoaderName);

	public void prepareConnectors(List<Connector> connectors);

	public void migrate(List<Connector> connectors);

	public void migrate();

	public boolean isMigrating();

	public void setMigrating(boolean migrating);

	public void compactDb();
}
