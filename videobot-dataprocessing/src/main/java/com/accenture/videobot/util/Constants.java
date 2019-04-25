package com.accenture.videobot.util;

public abstract class Constants {
	//Paths Arquivos
	public static final String APP_ROOT_FOLDER = "C:\\Users\\leonardo.del.giudice\\OneDrive - Accenture\\Accenture\\VideoBot\\Code\\App\\";
	public static final String FOLDER_SIEBEL8 = APP_ROOT_FOLDER + "Siebel8\\";
	public static final String FOLDER_MAILING_FIBRA = APP_ROOT_FOLDER + "Mailing\\";
	public static final String OUTPUT_FOLDER = APP_ROOT_FOLDER + "output\\";
	
	//Collections MongoDB
	public static final String STG_SIEBEL8_COLL_NAME = "StgSiebel8";
	public static final String STG_MAILING_FIBRA = "StgMailingFibra";
	public static final String DIM_WELCOME_OI_FIBRA = "DimWelcomeOiFibra";
	public static final String AUDIT_PROC_WELCOME_FIBRA = "AuditProcessingWelcomeFibra";
	public static final String CTL_EXECUTION_IDS = "CtlExecutionIds";
	public static final String HS_STG_MAILING_FIBRA = "HsStgMailingFibra";
	
	//Mensagens de Log
	public static final String MSG_MOTIVO_REJ_MULTIPLOS_CONTATOS = "CPF com duas ou mais ocorrencias no Mailing";
	public static final String MSG_MOTIVO_REJ_SEM_MOVEL = "CPF não possui número móvel associado";
	public static final String MSG_MOTIVO_REJ_SEM_CONTATO = "CPF não possui contato associado";
	public static final String MSG_MOTIVO_REJ_ALONE_BL = "CPF possui apenas serviço de internet FIBRA ALONE";
}
