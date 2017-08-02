package javax.jmdns.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import at.ac.tuwien.auto.iotsys.commons.PropertiesLoader;

/**
 * @author Nam Giang - zang at kaist dot ac dot kr
 * 
 */
public class TestDiscoverService {

	public static void main(String[] args) throws UnknownHostException, IOException {
		JmDNS jmdns = JmDNS.create(InetAddress.getByName(PropertiesLoader.getInstance().getProperties()
				.getProperty("iotsys.gateway.authNsAddr6", "fe80::acbc:b659:71db:5cb7%20")));
		jmdns.addServiceListener("_obix._soap."
				+ PropertiesLoader.getInstance().getProperties().getProperty("iotsys.gateway.authDomain", "local."),
				new SampleListener());
	}

	static class SampleListener implements ServiceListener {

		@Override
		public void serviceAdded(ServiceEvent event) {
			/// When a PTR is responded
			System.out.println("Service added   : " + event.getName() + "." + event.getType());
		}

		@Override
		public void serviceRemoved(ServiceEvent event) {
			System.out.println("Service removed : " + event.getName() + "." + event.getType());
		}

		@Override
		public void serviceResolved(ServiceEvent event) {
			/// Only when DNS records other than PTR (SRV, AAAA, TXT) are found
			/// then serviceResolved is called.
			System.out.println("Service resolved: " + event.getInfo());
		}
	}

}
