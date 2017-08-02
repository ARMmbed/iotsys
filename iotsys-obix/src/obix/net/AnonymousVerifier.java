/*
 * This code licensed to public domain
 */
package obix.net;

import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * HostnameVerifier implementation for ObixSession that enables anonymous SSL
 * sessions.
 * 
 * @author Aaron Hansen
 * @creation 10 Nov 06
 * @version $Revision$ $Date$
 */
public class AnonymousVerifier implements HostnameVerifier {
	public AnonymousVerifier(URLConnection conn) {
		if (conn instanceof HttpsURLConnection) {
			((HttpsURLConnection) conn).setHostnameVerifier(this);
		}
	}

	public boolean verify(String s, SSLSession x) {
		return true;
	}
}
