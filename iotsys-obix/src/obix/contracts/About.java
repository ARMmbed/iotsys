package obix.contracts;

import obix.Abstime;
import obix.IObj;
import obix.Str;
import obix.Uri;

/**
 * About
 * 
 * @author obix.tools.Obixc
 * @creation 24 May 06
 * @version $Revision$ $Date$
 */
public interface About extends IObj {
	public static final String CONTRACT = "obix:About";

	public Str obixVersion();

	public Str serverName();

	public Abstime serverTime();

	public Abstime serverBootTime();

	public Str vendorName();

	public Uri vendorUrl();

	public Str productName();

	public Str productVersion();

	public Uri productUrl();

}
