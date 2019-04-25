package com.accenture.videobot.poi;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class UtilPoi {
	
	private XSSFSheet ws;
	
	public UtilPoi(){}
	
	public int GetLastUsedRow(int startRow, int refColumn) {
		boolean flagData = true;
		int lastRow = startRow;
		
		while(flagData) {
			if(!(GetCellValue(lastRow,refColumn).isEmpty())) {
				lastRow++;
			}else {
				flagData=false;
			}
		}
		return lastRow-1;
	}
	
	public String GetCellValue(int row, int col) {
		String retValue;
		try {
			retValue = this.ws.getRow(row-1).getCell(col-1).getStringCellValue();
		}catch(NullPointerException npe){
			retValue = "";
		}
		
		return retValue; 
	}

	public XSSFSheet getWs() {
		return ws;
	}

	public void setWs(XSSFSheet ws) {
		this.ws = ws;
	}
	

}
