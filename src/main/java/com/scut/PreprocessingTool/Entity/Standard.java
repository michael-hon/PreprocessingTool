package com.scut.PreprocessingTool.Entity;



public class Standard {
	
	private String ISBN;
	private String ch_title;
	private String en_title;
	private String is_valid;
	private String CCS;
	private String publish_date;
	private String carray_out_date;
	private String type;
	private String location;
	private String filename;
	private Content content;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getCh_title() {
		return ch_title;
	}
	public void setCh_title(String ch_title) {
		this.ch_title = ch_title;
	}
	public String getEn_title() {
		return en_title;
	}
	public void setEn_title(String en_title) {
		this.en_title = en_title;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	public String getCCS() {
		return CCS;
	}
	public void setCCS(String cCS) {
		CCS = cCS;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getCarray_out_date() {
		return carray_out_date;
	}
	public void setCarray_out_date(String carray_out_date) {
		this.carray_out_date = carray_out_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
    
   
}
