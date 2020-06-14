package com.boot.common.util;

@SuppressWarnings("serial")
public class FuLiShareExecuteException extends Exception{

	private int code;
	private String text;
	private String info;
	
	public FuLiShareExecuteException(int code) {
		this.setCode(code);
	}
	public FuLiShareExecuteException(int code, String text) {
		this.setCode(code);
		this.setText(text);
	}
	public FuLiShareExecuteException(int code, String text,String info) {
		this.setCode(code);
		this.setText(text);
		this.setInfo(info);
	}
	
	
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "FuLiExecuteException [code=" + code + ", text=" + text + ", info=" + info + "]";
	}

}
