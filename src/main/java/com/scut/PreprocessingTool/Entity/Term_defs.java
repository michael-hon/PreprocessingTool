package com.scut.PreprocessingTool.Entity;

import java.util.ArrayList;
import java.util.List;

public class Term_defs {
	private String title = "";
	private String description = "";
	private List<Terms> terms = new ArrayList<Terms>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Terms> getTerms() {
		return terms;
	}
	public void setTerms(List<Terms> terms) {
		this.terms = terms;
	}
	
}