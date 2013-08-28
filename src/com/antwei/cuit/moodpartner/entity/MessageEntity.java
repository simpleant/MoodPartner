package com.antwei.cuit.moodpartner.entity;

public class MessageEntity {

	private String name;
	private String date;
	private String text;
	private boolean from = true;
	
	public MessageEntity(){
		
	}
	public MessageEntity(String name, String date, String text, boolean from) {
		super();
		this.name = name;
		this.date = date;
		this.text = text;
		this.from = from;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean getMsgType() {
		return from;
	}
	public void setFrom(boolean from) {
		this.from = from;
	}
	
	
	
	
}
