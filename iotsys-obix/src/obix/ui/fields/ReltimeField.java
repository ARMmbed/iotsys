/*
 * This code licensed to public domain
 */
package obix.ui.fields;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import obix.Obj;
import obix.Reltime;
import obix.ui.ObjField;

/**
 * ReltimeField
 *
 * @author Brian Frank
 * @creation 26 Sept 05
 * @version $Revision$ $Date$
 */
public class ReltimeField extends ObjField {

	////////////////////////////////////////////////////////////////
	// Constructor
	////////////////////////////////////////////////////////////////

	public ReltimeField() {
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
		Reltime v = (Reltime) val;
		textField.setText(v.encodeVal());
	}

	protected void doSave(Obj val) throws Exception {
		Reltime v = (Reltime) val;
		v.decodeVal(textField.getText());
	}

	////////////////////////////////////////////////////////////////
	// Fields
	////////////////////////////////////////////////////////////////

	JTextField textField = new JTextField("", 30);

}