/*
 * This code licensed to public domain
 */
package obix.ui;

import obix.Abstime;
import obix.Bool;
import obix.Enum;
import obix.Int;
import obix.Obj;
import obix.Real;
import obix.Reltime;
import obix.Str;
import obix.Uri;
import obix.ui.fields.AbstimeField;
import obix.ui.fields.BoolField;
import obix.ui.fields.EnumField;
import obix.ui.fields.IntField;
import obix.ui.fields.RealField;
import obix.ui.fields.ReltimeField;
import obix.ui.fields.StrField;
import obix.ui.fields.SummaryField;
import obix.ui.fields.UriField;

/**
 * ObjField is the abstract base class for field editors designed to view and
 * edit a specific Obj type: StrField, BoolField, etc.
 *
 * @author Brian Frank
 * @creation 26 Sept 05
 * @version $Revision$ $Date$
 */
public abstract class ObjField extends Editor {

	/**
	 * Make a field editor for the specified val. This method does not
	 * automatically load it.
	 */
	public static ObjField make(Obj obj) {
		if (obj instanceof Bool)
			return new BoolField();
		if (obj instanceof Int)
			return new IntField();
		if (obj instanceof Real)
			return new RealField();
		if (obj instanceof Str)
			return new StrField();
		if (obj instanceof Enum)
			return new EnumField();
		if (obj instanceof Uri)
			return new UriField();
		if (obj instanceof Abstime)
			return new AbstimeField();
		if (obj instanceof Reltime)
			return new ReltimeField();
		return new SummaryField();
	}

}
