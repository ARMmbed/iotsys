package an.datatype;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class base64Binary {
	private String base64Value;
	private byte[] value;
	private static Decoder decoder = Base64.getDecoder();
	private static Encoder encoder = Base64.getEncoder();
	private int hashCode;

	public base64Binary(String base64Value) throws IOException {
		this.base64Value = base64Value;
		this.value = decoder.decode(base64Value);
		this.hashCode = base64Value.hashCode();
	}

	public base64Binary(byte[] value) {
		this.value = value;
		this.base64Value = new String(encoder.encode(value), StandardCharsets.UTF_8);
		this.hashCode = base64Value.hashCode();
	}

	public static base64Binary valueOf(String base64Value) throws IOException {
		return new base64Binary(base64Value);
	}

	public byte[] getValue() {
		return value;
	}

	public String getBase64Value() {
		return base64Value;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o != null && o.getClass() == this.getClass()) {
			byte[] otherValue = ((base64Binary) o).value;
			if (value.length != otherValue.length) {
				return false;
			}
			for (int i = 0; i < value.length; i++) {
				if (value[i] != otherValue[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public String toString() {
		return getBase64Value();
	}

	public int hashCode() {
		return hashCode;
	}
}