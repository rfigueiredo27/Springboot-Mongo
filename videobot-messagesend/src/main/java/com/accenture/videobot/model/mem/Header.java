package com.accenture.videobot.model.mem;

import java.util.Date;

public class Header {
	private Date timeModified = new Date();
	private String requestSystem = "MEM";
	private String tenant = "x";
	private String objectModelName = "VIDEOBOT";
	private String groupId = "200";
	private String eventId = "1";
	
	public Date getTimeModified() {
		return timeModified;
	}
	public void setTimeModified(Date timeModified) {
		this.timeModified = timeModified;
	}
	public String getRequestSystem() {
		return requestSystem;
	}
	public void setRequestSystem(String requestSystem) {
		this.requestSystem = requestSystem;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	public String getObjectModelName() {
		return objectModelName;
	}
	public void setObjectModelName(String objectModelName) {
		this.objectModelName = objectModelName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
