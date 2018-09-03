package com.scut.PreprocessingTool.Entity;

import java.util.ArrayList;
import java.util.List;

public class Content {
	private List<String> related_files = new ArrayList<String>();
	private Range range;
	private Ref_files ref_files;
	private Term_defs term_defs;
	private List<Tech_requirements> tech_requirements;
	public List<String> getRelated_files() {
		return related_files;
	}
	public void setRelated_files(List<String> related_files) {
		this.related_files = related_files;
	}
	public Range getRange() {
		return range;
	}
	public void setRange(Range range) {
		this.range = range;
	}
	public Ref_files getRef_files() {
		return ref_files;
	}
	public void setRef_files(Ref_files ref_files) {
		this.ref_files = ref_files;
	}
	public Term_defs getTerm_defs() {
		return term_defs;
	}
	public void setTerm_defs(Term_defs term_defs) {
		this.term_defs = term_defs;
	}
	public List<Tech_requirements> getTech_requirements() {
		return tech_requirements;
	}
	public void setTech_requirements(List<Tech_requirements> tech_requirements) {
		this.tech_requirements = tech_requirements;
	}
	
}

