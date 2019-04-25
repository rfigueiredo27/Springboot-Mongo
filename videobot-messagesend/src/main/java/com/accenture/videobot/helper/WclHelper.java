package com.accenture.videobot.helper;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.accenture.videobot.model.wcl.ContactValidate;
import com.accenture.videobot.model.wcl.Login;
import com.accenture.videobot.model.wcl.ShortUrl;
import com.accenture.videobot.model.wcl.login.BodyLogin;
import com.accenture.videobot.model.wcl.shortUrl.BodyShortUrl;
import com.accenture.videobot.model.wcl.validateContact.BodyContactValidate;
import com.google.gson.Gson;

public class WclHelper {

	RestTemplate restTemplate;
	
	
	public String getToken() {

		restTemplate = new RestTemplate();

		VideobotMessagesendHelper helper = new VideobotMessagesendHelper();
		Login login = new Login();
		login.setUser(Constants.USER_API_WCL);
		login.setPassword(Constants.PWD_API_WCL);
		
		String requestJson = helper.convertToJson(login);
		System.out.println(requestJson);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String answer = restTemplate.postForObject(Constants.API_LOGIN_HOMOL, entity, String.class);

		BodyLogin answerAPI = new BodyLogin();
		Gson gson = new Gson();
		answerAPI = gson.fromJson(answer, BodyLogin.class);
		String teste = gson.toJson(answerAPI);
		System.out.println(teste);
		return answerAPI.getData().getToken();
	}
	
	public boolean validateContact(String number) {
		VideobotMessagesendHelper helper = new VideobotMessagesendHelper();
		ContactValidate contactValidate = new ContactValidate();
		ArrayList<String> contacts = new ArrayList<String>();
		contacts.add(number);
		contactValidate.setContacts(contacts);
		
		String requestJson = helper.convertToJson(contactValidate);
		
		System.out.println(requestJson);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(getToken());
		
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String answer = restTemplate.postForObject(Constants.API_CONTACT_VALIDATE_HOMOL, entity, String.class);
		
		BodyContactValidate answerAPI = new BodyContactValidate();
		answerAPI = helper.convertJsonToObject(answer, BodyContactValidate.class);
		System.out.println(answerAPI);
		
		com.accenture.videobot.model.wcl.validateContact.contacts contactsObject = new com.accenture.videobot.model.wcl.validateContact.contacts();
		contactsObject = answerAPI.getData().getContacts().get(0);
		if(contactsObject.getStatus().equals("valid"))
		{
			System.out.println("true");
			return true;
		}
		else {
			System.out.println("false");
			return false;
		}
	}
	
	public String getShortURL(String urlOriginal) {

		restTemplate = new RestTemplate();

		VideobotMessagesendHelper helper = new VideobotMessagesendHelper();
		ShortUrl url = new ShortUrl();
		url.setOriginalURL(urlOriginal);
		url.setDomain("oi.digital");
		
		
		String requestJson = helper.convertToJson(url);
		System.out.println(requestJson);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "bgWBOQQjksrOykir");
		
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String answer = restTemplate.postForObject(Constants.API_CONTACT_SHORT_LINK, entity, String.class);

		BodyShortUrl answerAPI = new BodyShortUrl();
		Gson gson = new Gson();
		answerAPI = gson.fromJson(answer, BodyShortUrl.class);
		System.out.println(answerAPI.getShortURL());
		return answerAPI.getShortURL();
	}
	
	
//	public static void main(String[] args) {
//		WclHelper wclHelper = new WclHelper();
//		wclHelper.getToken();
//		wclHelper.validateContact("21998597050");
//		wclHelper.getShortURL("http://www.google.com.br");
//	}
}
