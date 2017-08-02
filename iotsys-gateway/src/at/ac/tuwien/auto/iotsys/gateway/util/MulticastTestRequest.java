package at.ac.tuwien.auto.iotsys.gateway.util;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.UnknownHostException;

import ch.ethz.inf.vs.californium.coap.Message.messageType;
import ch.ethz.inf.vs.californium.coap.PUTRequest;

public class MulticastTestRequest {
	public static void main(String[] args) {

		Inet6Address group = null;
		try {
			group = (Inet6Address) Inet6Address.getByName("FF15::0FF");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Object state = new Bool(true);
		for (int i = 0; i < 1000; i++) {
			PUTRequest putRequest = new PUTRequest();
			putRequest.setType(messageType.NON);
			putRequest.setURI("coap://[" + group.getHostAddress() + "]:5683/");
			if (i % 2 == 0) {
				putRequest.setPayload("<bool val=\"false\">");
			} else {
				putRequest.setPayload("<bool val=\"true\">");
			}

			// if(state instanceof Bool){
			// Bool bool = (Bool) state;
			// Bool b = new Bool();
			// b.set(bool.get());
			// try {
			// byte[] payload = EXIEncoder.getInstance().toBytes(b, true);
			// // work around application octet stream
			// putRequest.setContentType(ch.ethz.inf.vs.californium.coap.registries.MediaTypeRegistry.APPLICATION_OCTET_STREAM);
			// putRequest.setPayload(payload);
			// } catch (Exception e){
			// // fall back to XML encoding
			// e.printStackTrace();
			// String payload = ObixEncoder.toString(b);
			// putRequest.setPayload(payload);
			// }
			//
			// }
			// else if(state instanceof Real){
			// Real real = (Real) state;
			// Real r = new Real();
			// r.set(real.get());
			// try {
			// byte[] payload = EXIEncoder.getInstance().toBytes(r, true);
			// // work around application octet stream
			// putRequest.setContentType(ch.ethz.inf.vs.californium.coap.registries.MediaTypeRegistry.APPLICATION_OCTET_STREAM);
			// putRequest.setPayload(payload);
			// } catch (Exception e){
			// // fall back to XML encoding
			// e.printStackTrace();
			// String payload = ObixEncoder.toString(r);
			// putRequest.setPayload(payload);
			// }
			// } else if(state instanceof Int){
			// Int intObj = (Int) state;
			// Int i = new Int();
			// i.set(intObj.get());
			// try {
			// byte[] payload = EXIEncoder.getInstance().toBytes(i, true);
			// // work around application octet stream
			// putRequest.setContentType(ch.ethz.inf.vs.californium.coap.registries.MediaTypeRegistry.APPLICATION_OCTET_STREAM);
			// putRequest.setPayload(payload);
			// } catch (Exception e){
			// // fall back to XML encoding
			// e.printStackTrace();
			// String payload = ObixEncoder.toString(i);
			// putRequest.setPayload(payload);
			// }
			// }

			putRequest.enableResponseQueue(false);
			try {
				putRequest.execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
