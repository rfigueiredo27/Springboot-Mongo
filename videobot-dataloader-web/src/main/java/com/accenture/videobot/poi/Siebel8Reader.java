package com.accenture.videobot.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.accenture.videobot.model.RegistroSiebel8;
import com.accenture.videobot.mongo.Siebel8Writer;
import com.accenture.videobot.util.Constants;


public class Siebel8Reader {
	
	public void LoadExcelFile(MongoTemplate mongoTemplate, String excelFilePath) throws InvalidFormatException{
		
		ArrayList<RegistroSiebel8> rowList = new  ArrayList<RegistroSiebel8>();
		UtilPoi poi = new UtilPoi();
		
		Siebel8Writer sw = new Siebel8Writer();
		
		try {
			FileInputStream excelFile = new FileInputStream(new File(Constants.EXCEL_FILE));
			XSSFWorkbook wb = new XSSFWorkbook(excelFile);
			poi.setWs(wb.getSheet(Constants.DATA_SHEET_NAME));
			
			int lastRow = poi.GetLastUsedRow(2, 7);
			System.out.println("TOTAL DE LINHAS:"+lastRow);
					
			for(int row=2;row<=lastRow;row++) {
				rowList.add(ReadExcelRow(poi,row));
				if(rowList.size()>=1000||row==lastRow) {
					sw.WriteRegisters(rowList, mongoTemplate);
					rowList.clear();
					System.out.println("WRITE DB - LINE:"+row);
				}
			}
			
			wb.close();
			excelFile.close();
		}	
		catch (FileNotFoundException e) {
			System.out.println("Erro: File Not Found");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public RegistroSiebel8 ReadExcelRow(UtilPoi poi, int rowNum) {
		RegistroSiebel8 reg = new RegistroSiebel8();
		
		reg.setTelContato(poi.GetCellValue(rowNum, 1));
		reg.setPedido(poi.GetCellValue(rowNum, 2));
		reg.setDataPedido(poi.GetCellValue(rowNum, 3));
		reg.setStatusPedido(poi.GetCellValue(rowNum, 4));		
		reg.setDataStatus(poi.GetCellValue(rowNum, 5));
		reg.setCpf(poi.GetCellValue(rowNum, 6));
		reg.setNome(poi.GetCellValue(rowNum, 7));
		reg.setNumConta(poi.GetCellValue(rowNum,8));
		reg.setEndereco(poi.GetCellValue(rowNum,9));
		reg.setGpon(poi.GetCellValue(rowNum,10));
		reg.setIdBundle(poi.GetCellValue(rowNum,11));
		reg.setIdAcessoVoip(poi.GetCellValue(rowNum,12));
		reg.setNumeroVoip(poi.GetCellValue(rowNum,13));
		reg.setActionCd(poi.GetCellValue(rowNum,14));
		reg.setBandaLarga(poi.GetCellValue(rowNum,15));
		reg.setAcaoBl(poi.GetCellValue(rowNum,16));
		reg.setPlanoTv(poi.GetCellValue(rowNum,17));
		reg.setAcaoTv(poi.GetCellValue(rowNum,18));
		reg.setNumeroPortado(poi.GetCellValue(rowNum,19));
		reg.setOferta(poi.GetCellValue(rowNum,20));
		reg.setAcaoOferta(poi.GetCellValue(rowNum,21));
		reg.setClasseBundle(poi.GetCellValue(rowNum,22));
		reg.setAtividade(poi.GetCellValue(rowNum,23));
		reg.setUsuarioAbertura(poi.GetCellValue(rowNum,24));
		reg.setTipoCanal(poi.GetCellValue(rowNum,25));
		reg.setCodPdv(poi.GetCellValue(rowNum, 26));
		reg.setPontoVenda(poi.GetCellValue(rowNum,27));
		reg.setVendedor63(poi.GetCellValue(rowNum,28));
		reg.setCampanha(poi.GetCellValue(rowNum,29));
		reg.setCodMotivoCancPedido(poi.GetCellValue(rowNum,30));
		reg.setMotivoCancPedido(poi.GetCellValue(rowNum,31));
		reg.setMotivoPendencia(poi.GetCellValue(rowNum,32));
		
		return reg;
	}
	
}
