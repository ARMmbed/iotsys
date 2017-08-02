/*
 * This code licensed to public domain
 */
package obix.ui.fields;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import obix.Obj;
import obix.Uri;
import obix.ui.ObjField;

/**
 * UriField
 *
 * @author Brian Frank
 * @creation 26 Sept 05
 * @version $Revision$ $Date$
 */
public class UriField extends ObjField {

	////////////////////////////////////////////////////////////////
	// Constructor
	////////////////////////////////////////////////////////////////

	public UriField() {
		add(textField, BorderLayout.CENTER);
		registerForChanged(textField);
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	protected void doSetEditable(boolean editable) {
		textField.setEditable(editable);
	}

	protected void doLoad(Obj val) {
		Uri v = (Uri) val;
		textField.setText(v.get());
	}

	protected void doSave(Obj val) {
		Uri v = (Uri) val;
		v.set(textField.getText());
	}

	////////////////////////////////////////////////////////////////
	// Fields
	////////////////////////////////////////////////////////////////

	JTextField textField = new JTextField("", 50);

}