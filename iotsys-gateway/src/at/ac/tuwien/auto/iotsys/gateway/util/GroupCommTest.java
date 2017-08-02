package at.ac.tuwien.auto.iotsys.gateway.util;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import ch.ethz.inf.vs.californium.coap.Message.messageType;
import ch.ethz.inf.vs.californium.coap.PUTRequest;
import obix.Obj;
import obix.Real;
import obix.io.ObixEncoder;

public class GroupCommTest {
	public static void main(String[] args) {
		InetAddress group;
		try {
			group = Inet6Address.getByName("FF02:FFFF::1");
			PUTRequest putRequest = new PUTRequest();
			putRequest.setType(messageType.NON);
			String uri = "coap://[" + group.getHostAddress() + "]/";
			System.out.println("URI: " + uri);
			putRequest.setURI(uri);

			Obj obj = new Real(0);

			String payload = ObixEncoder.toString(obj);
			putRequest.setPayload(payload);

			putRequest.enableResponseQueue(false);
			try {
				putRequest.execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
