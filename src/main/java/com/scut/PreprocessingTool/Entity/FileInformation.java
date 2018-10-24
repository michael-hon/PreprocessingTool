package com.scut.PreprocessingTool.Entity;

public class FileInformation {
	
	private int file_id;                  //文件id,数据库主键
	
	private String filename;              //文件名
	
	private String upload_time;           //上传时间
	
	private String source_filepath;       //源文件路径
	
	private String text_filepath;         //解析文件路径
	
	private int is_analysis;              //是否解析
	
	private int is_clean;                 //是否清洗
	
	private int is_structure;             //是否结构化
	
	private int is_validation;            //是否校验

	public FileInformation(){
		
	}
	
	public FileInformation(String filename,String upload_time, String source_filepath,
			              String text_filepath, int is_analysis, int is_clean,
			              int is_structure, int is_validation){
		this.filename = filename;
		this.upload_time = upload_time;
		this.source_filepath = source_filepath;
		this.text_filepath = text_filepath;
		this.is_analysis = is_analysis;
		this.is_clean = is_clean;
		this.is_structure = is_structure;
		this.is_validation = is_validation;
	}
	
	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}

	public String getSource_filepath() {
		return source_filepath;
	}

	public void setSource_filepath(String source_filepath) {
		this.source_filepath = source_filepath;
	}

	public String getText_filepath() {
		return text_filepath;
	}

	public void setText_filepath(String text_filepath) {
		this.text_filepath = text_filepath;
	}

	public int getIs_analysis() {
		return is_analysis;
	}

	public void setIs_analysis(int is_analysis) {
		this.is_analysis = is_analysis;
	}

	public int getIs_clean() {
		return is_clean;
	}

	public void setIs_clean(int is_clean) {
		this.is_clean = is_clean;
	}

	public int getIs_structure() {
		return is_structure;
	}

	public void setIs_structure(int is_structure) {
		this.is_structure = is_structure;
	}

	public int getIs_validation() {
		return is_validation;
	}

	public void setIs_validation(int is_validation) {
		this.is_validation = is_validation;
	}
	
	
}
