package com.example.javahomework;
public class message {
	private String id;
	private String text;
	private String date;
	public message(String id, String text, String date){
		this.id = id;
		this.text = text;
		this.date = date;
	}
	public String getid(){
		return id;
	}
	public String gettext(){
		return text;
	}
	public String getdate(){
		return date;
	}

}
