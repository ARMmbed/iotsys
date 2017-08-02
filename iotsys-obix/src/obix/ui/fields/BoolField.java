/*
 * This code licensed to public domain
 */
package obix.ui.fields;

import java.awt.BorderLayout;

import javax.swing.JComboBox;

import obix.Bool;
import obix.Obj;
import obix.ui.ObjField;

/**
 * BoolField
 *
 * @author Brian Frank
 * @creation 26 Sept 05
 * @version $Revision$ $Date$
 */
public class BoolField extends ObjField {

	////////////////////////////////////////////////////////////////
	// Constructor
	////////////////////////////////////////////////////////////////

	public BoolField() {
		combo.addItem("false");
		combo.addItem("true");
		add(combo, BorderLayout.CENTER);
		registerForChanged(combo);
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	protected void doSetEditable(boolean editable) {
		combo.setEnabled(editable);
	}

	protected void doLoad(Obj val) {
		Bool v = (Bool) val;
		boolean b = v.get();
		combo.setSelectedIndex(b ? 1 : 0);
	}

	protected void doSave(Obj val) {
		Bool v = (Bool) val;
		boolean b = combo.getSelectedIndex() == 1;
		v.set(b);
	}

	////////////////////////////////////////////////////////////////
	// Fields
	////////////////////////////////////////////////////////////////

	JComboBox combo = new JComboBox();

}