package com.boot.common.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateEditor extends PropertyEditorSupport{

	public DateEditor() {
		super();
	}


	@Override
	public void setAsText(String text) {

		if (text == null||"".equals(text)) {
			setValue(null);
		} else {
			String value = text;
			SimpleDateFormat sdf = null;
			if(!value.contains(":")){
				if(value.contains("-")){
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}else if(value.contains("/")){
					sdf = new SimpleDateFormat("yyyy/MM/dd");
				}
			}else{
				if(value.contains("-")){
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				}else if(value.contains("/")){
					sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				}
			}
			try {
				setValue(sdf.parse(value));
			} catch (ParseException e) {
				setValue(value);
				e.printStackTrace();
			}
		}

	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

}
