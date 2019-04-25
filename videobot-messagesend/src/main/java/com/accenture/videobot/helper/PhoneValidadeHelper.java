package com.accenture.videobot.helper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.accenture.videobot.model.phoneValidate.AnswerPhoneValidate;
import com.accenture.videobot.model.phoneValidate.Phone;
import com.google.gson.Gson;

public class PhoneValidadeHelper {
	
	RestTemplate restTemplate = new RestTemplate();
	
	public boolean validatePhone(String number) {
		
		VideobotMessagesendHelper helper = new VideobotMessagesendHelper();
		Phone phone = new Phone();
		phone.setNumber(number);
		String requestJson = helper.convertToJson(phone);
		System.out.println(requestJson);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(Constants.TOKEN_MEM);
		
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String answer = restTemplate.postForObject(Constants.API_VALIDATE_PHONE, entity, String.class);
		
		AnswerPhoneValidate answerAPI = new AnswerPhoneValidate();
		answerAPI = helper.convertJsonToObject(answer, AnswerPhoneValidate.class);
		System.out.println(answerAPI);
		if(answerAPI.getData().isValid() && answerAPI.getData().isMobile())
		{
			System.out.println("true");
			return true;
		}
		else {
			System.out.println("false");
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		PhoneValidadeHelper helper = new PhoneValidadeHelper();
		helper.validatePhone("+552199999999");
	}
}
