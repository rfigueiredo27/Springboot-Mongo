package com.accenture.videobot.model.sendWcl;

public class BodyWcl {
	private String recipient_type = "individual";
	private Text text;
	private Wcl wcl;
	public String getRecipient_type() {
		return recipient_type;
	}
	public void setRecipient_type(String recipient_type) {
		this.recipient_type = recipient_type;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public Wcl getWcl() {
		return wcl;
	}
	public void setWcl(Wcl wcl) {
		this.wcl = wcl;
	}
	
	
}
