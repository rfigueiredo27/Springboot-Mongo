package com.accenture.videobot.run;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.videobot.model.RegistroSiebel8;
import com.accenture.videobot.mongo.Siebel8DAL;
import com.accenture.videobot.poi.Siebel8Reader;


@SpringBootApplication
public class TesteExecucao {
	
	private static Siebel8DAL s8d;

	public static void main(String[] args) {

		Siebel8Reader sr = new Siebel8Reader();
		ArrayList<RegistroSiebel8> regs = sr.ReadExcelFile();
		
		//for(int i=0;i<regs.size();i++) {
		for(int i=0;i<=20;i++) {
			System.out.println(i + " - " + regs.get(i).getTelContato() + " - " + regs.get(i).getPedido() + " - " + regs.get(i).getCpf());
			RegistroSiebel8 rr = s8d.saveRegister(regs.get(i));
		}

	}

}
