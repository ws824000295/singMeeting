package com.boot.common.util;

import java.beans.PropertyEditorSupport;

public class StringEscapeEditor extends PropertyEditorSupport {

	private boolean escapeScript;

	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeScript) {

		super();

		this.escapeScript = escapeScript;

	}

	@Override

	public void setAsText(String text) {

		if (text == null) {

			setValue(null);

		} else {

			String value = text;

			if (escapeScript) {
				value = StringUtil.escapeScript(value);
			}
			setValue(value);
		}

	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

}
