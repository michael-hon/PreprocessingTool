package com.scut.PreprocessingTool.Entity;

import java.util.ArrayList;
import java.util.List;

public class Ref_files {
	private String title = "";
	private List<String> files = new ArrayList<String>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
	
}
