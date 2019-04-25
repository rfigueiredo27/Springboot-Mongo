package com.accenture.videobot.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.accenture.videobot.VideobotMessagesendController;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;

public class VideobotMessagesendHelper {

	public void delay(Integer segundos) {
//		Implementar posteriormente como parâmetro no banco
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<String> readCSV(String path) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//File file = new File(path);
		//file.delete();
		return list;
	}
	
	//métodos para facilitar a manipulação de JSON
	public String convertToJson(Object object){
		Gson gson = new Gson();
		String json = gson.toJson(object);
		return json;
	}
	
	public <T> T convertJsonToObject(String json, Class<T> classOfT) throws JsonSyntaxException {
		Gson gson = new Gson();
	    Object object = gson.fromJson(json, (Type) classOfT);
	    return Primitives.wrap(classOfT).cast(object);
	  }
	
}
