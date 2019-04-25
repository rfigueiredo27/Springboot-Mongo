package com.accenture.videobot.helper;

public enum LogType {
	SUCCESS(0), ERROR(1);
	
	private final Integer value;
	
	LogType(Integer valueType){
		value = valueType;
    }
    public Integer getValue(){
        return value;
    }
}
