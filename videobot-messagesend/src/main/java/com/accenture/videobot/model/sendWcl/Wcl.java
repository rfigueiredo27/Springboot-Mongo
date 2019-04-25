package com.accenture.videobot.model.sendWcl;

public class Wcl {
	private String phone;
	private String externalRefId = "76o87868768";
	private boolean waitingReply = false;
	private String priority = "low";
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getExternalRefId() {
		return externalRefId;
	}
	public void setExternalRefId(String externalRefId) {
		this.externalRefId = externalRefId;
	}
	public boolean isWaitingReply() {
		return waitingReply;
	}
	public void setWaitingReply(boolean waitingReply) {
		this.waitingReply = waitingReply;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
