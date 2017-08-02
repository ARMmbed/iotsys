package an.xacml.policy;

import java.net.URI;

import an.xacml.DefaultXACMLElement;

public class Defaults extends DefaultXACMLElement {
	// the xpathVersion may be null.
	private URI xpathVersion;

	public Defaults(URI xpathVersion) {
		this.xpathVersion = xpathVersion;
	}

	public URI getXPathVersion() {
		return xpathVersion;
	}
}