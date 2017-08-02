/*
 * This code licensed to public domain
 */
package obix.ui.fields;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import obix.Int;
import obix.Obj;
import obix.ui.ObjField;

/**
 * IntField
 *
 * @author Brian Frank
 * @creation 26 Sept 05
 * @version $Revision$ $Date$
 */
public class IntField extends ObjField {

	////////////////////////////////////////////////////////////////
	// Constructor
	////////////////////////////////////////////////////////////////

	public IntField() {
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
		Int v = (Int) val;
		textField.setText(String.valueOf(v.get()));
	}

	protected void doSave(Obj val) {
		Int v = (Int) val;
		v.set(Long.parseLong(textField.getText()));
	}

	////////////////////////////////////////////////////////////////
	// Fields
	////////////////////////////////////////////////////////////////

	JTextField textField = new JTextField("", 10);

}