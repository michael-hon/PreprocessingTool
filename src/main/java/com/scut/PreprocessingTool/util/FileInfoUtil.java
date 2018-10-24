package com.scut.PreprocessingTool.util;

public class FileInfoUtil {
	public static boolean judgeFileFormat(String filename){
		boolean re1 = filename.endsWith("pdf");
		boolean re2 = filename.endsWith("PDF");
		boolean re3 = filename.endsWith("doc");
		boolean re4 = filename.endsWith("DOC");
		boolean re5 = filename.endsWith("docx");
		boolean re6 = filename.endsWith("DOCX");
		return re1||re2||re3||re4||re5||re6;
	}
}
