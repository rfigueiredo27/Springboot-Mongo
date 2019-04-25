package com.accenture.videobot.helper;

public abstract class Constants {
	
	public static final String FILE_LINK_VIDEO = "C:\\Users\\renan.r.figueiredo\\Desktop\\videobot.dim_welcome_oi_fibra.csv";
	public static final String TOKEN_MEM = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiVmlkZW9Cb3QiLCJpYXQiOjE1NTE5ODQyMzB9.vjGQHDzByLOV9FrEA00FXW353KbFHp_OoHuzLrSseoM";
	public static final String API_MEM = "http://10.58.4.132/api/input/message";
	public static final String DELIMITADOR_CSV = ";";
	public static final Integer DELAY_API = 1;
	public static final String API_VALIDATE_PHONE = "http://10.58.4.132/api/validation/phone";
	public static final String USER_API_WCL = "VIDEO_BOT";
	public static final String PWD_API_WCL = "VideoBot2019";
	
	public static final String API_LOGIN_HOMOL = "https://wcl-hml.oi.com.br/wcl/api/users/login"; 
	public static final String API_CONTACT_VALIDATE_HOMOL = "https://wcl-hml.oi.com.br/wcl/api/contacts"; 
	public static final String API_CONTACT_WCL_HOMOL = "https://wcl-hml.oi.com.br/wcl/api/messages"; 
	
	public static final String API_LOGIN_PROD = "https://wcl.oi.com.br/wcl/api/users/login"; 
	public static final String API_CONTACT_VALIDATE_PROD = "https://wcl.oi.com.br/wcl/api/contacts"; 
	public static final String API_CONTACT_WCL_PROD = "https://wcl.oi.com.br/wcl/api/messages"; 
	
	public static final String API_CONTACT_SHORT_LINK = "https://api.short.cm/links";

}
