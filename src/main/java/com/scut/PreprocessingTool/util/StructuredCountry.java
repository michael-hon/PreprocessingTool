package com.scut.PreprocessingTool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.scut.PreprocessingTool.DAO.StandardMongoDAO;
import com.scut.PreprocessingTool.DAO.standard_infoDAO;
import com.scut.PreprocessingTool.Entity.Content;
import com.scut.PreprocessingTool.Entity.Range;
import com.scut.PreprocessingTool.Entity.Ref_files;
import com.scut.PreprocessingTool.Entity.Standard;
import com.scut.PreprocessingTool.Entity.Tech_requirements;
import com.scut.PreprocessingTool.Entity.Term_defs;
import com.scut.PreprocessingTool.Entity.Terms;
import com.scut.PreprocessingTool.Entity.standard_info;



public class StructuredCountry {
	
	@Resource
	private standard_infoDAO sid;
	
	@Resource
	private StandardMongoDAO strdid;
	
	public void StructuredFile(String filepath) throws Exception{
		File file = new File(filepath);
		BufferedReader br;

		br = new BufferedReader(new FileReader(file));
	
		String filename = file.getName();
		
		StructuredHelp structured = new StructuredHelp();
		List<standard_info> standard_infos = sid.select();
		standard_info st = new standard_info();
		st = structured.getRightStandard_info(filename, standard_infos);
		Standard strdi = new Standard();

		strdi.setISBN(st.getNumber());
		strdi.setCh_title(st.getC_name());
		strdi.setEn_title(st.getE_name());
		strdi.setIs_valid(st.getStatus());
		strdi.setCCS(st.getCcs());
		strdi.setPublish_date(st.getPublish_date());
		strdi.setCarray_out_date(st.getCarray_out_date());
		strdi.setFilename(filename);
		strdi.setType("");
		strdi.setLocation("");
		String line = "";
		List<Terms> terms = new ArrayList<Terms>();
		List<String> files = new ArrayList<String>();
		List<Tech_requirements> tech_requirements = new ArrayList<Tech_requirements>();
		int numSection = 0;
		boolean start = false;
		Range range = new Range();
		Ref_files ref_files = new Ref_files();
		Term_defs term_defs = new Term_defs();
		String name = null;
		String term_content = "";
		String term_c_name = "";
		String term_e_name = "";
		String term_se_num = "";
		String tech_requirements_title = "";
		String tech_requirements_name = "";
		String tech_requirements_content = "";
		while((line = br.readLine())!=null){
			line = structured.cleanLine(line);
			int num = judgeSection(line, term_defs.getTitle());
			switch (num) {
			case 1:
				start = true;
				range.setTitle(getSe_num(line));
				numSection = 1;
				break;
			case 2:
				start = true;
				ref_files.setTitle(getSe_num(line));
				numSection = 2;
				break;
			case 3:
				start = true;
				term_defs.setTitle(getSe_num(line));
				numSection = 3;
				break;
			case 4:
				start = true;
				numSection = 4;
				if(!tech_requirements_name.isEmpty()){
					Tech_requirements tech_requirement = new Tech_requirements();
					tech_requirement.setContent(tech_requirements_content);
					tech_requirement.setName(tech_requirements_name);
					tech_requirement.setTitle(tech_requirements_title);
					tech_requirements.add(tech_requirement);
				}
					
				tech_requirements_title = getSe_num(line);
				tech_requirements_name = getTitle(line);
				tech_requirements_content = "";
				break;
			case 0:
				String temp = "";
				if(start){
					switch (numSection) {
					case 1:
						
						temp = range.getContent() + line;
						range.setContent(temp);
						break;
					case 2:
						if(isRef_Files(line)){
							if(name == null){
								name = line;
							}
							else{
								files.add(name);
								name = line;
							}
							continue;
						}
						else {
							if(name == null){
								continue;
							}
							else
								name = name + line;
						}
						break; 
					case 3:
						boolean judge_term_result = judgeTermTitle(line);
						if (judge_term_result) {
							if(!term_se_num.isEmpty()){
								Terms term = new Terms();
								term.setCname(term_c_name);
								term.setContent(term_content);
								term.setEname(term_e_name);
								term.setTitle(term_se_num);
								terms.add(term);
							}
							String line_next = br.readLine();
							term_se_num = line;
							term_c_name = structured.getC_title(line_next);
							term_content= "";
							term_e_name = structured.getE_title(line_next);
						}
						else{ 
							//前面的描述
							if(term_se_num.isEmpty()){
								String des_temp = term_defs.getDescription();
								des_temp = des_temp +line;
								term_defs.setDescription(des_temp);
								continue;
							}
							term_content = term_content + line;
						}
						break;
					case 4:
						tech_requirements_content = tech_requirements_content + line;
						break;
					default:
						break;
					}
				}
				break;
			default:
				break;
			}
		}
		files.add(name);
		Terms term = new Terms();
		term.setCname(term_c_name);
		term.setContent(term_content);
		term.setEname(term_e_name);
		term.setTitle(term_se_num);
		terms.add(term);
		Tech_requirements tech_requirement = new Tech_requirements();
		tech_requirement.setContent(tech_requirements_content);
		tech_requirement.setName(tech_requirements_name);
		tech_requirement.setTitle(tech_requirements_title);
		tech_requirements.add(tech_requirement);
		Content content = new Content();
		content.setRange(range);
		ref_files.setFiles(files);
		term_defs.setTerms(terms);
		content.setRef_files(ref_files);
		content.setTerm_defs(term_defs);
		content.setTech_requirements(tech_requirements);
		strdi.setContent(content);
		strdid.insertStandard(strdi);
	}
	
	
	
	
	
	/*
	 * 使用半角符的.来代替全角符的.
	 */
	public String cleanLine(String line){
		line = line.trim();
		if(line.contains("．"))
			line = line.replaceAll("．", ".");
		line = line.trim();
		return line;
	}
	
	
	public String getC_title(String line){
		String result = "";
		int index = getE_title_begin_index(line);
		result = line.substring(0, index + 1);
		return result;
	}
	public String getE_title(String line){
		String result = "";
		int l = line.length();
		for(int i = l-1 ; i >= 0 ; i--){
			char ch = line.charAt(i);
			if(!isChinese(ch)){
				if(ch == ')'||ch == '）'||ch == ']'|| ch == '］'){
					char ch1 = line.charAt(i -1);
					if(isChinese(ch1))
						break;
				}
				String str = String.valueOf(ch);
				result = str + result;
			}
			else 
				break;
		}
		return result;
	}
	
	public int getE_title_begin_index(String line){
		String result = "";
		int l = line.length();
		int i = l - 1;
		for( ; i >= 0 ; i--){
			char ch = line.charAt(i);
			if(!isChinese(ch)){
				if(ch == ')'||ch == '）'||ch == ']'|| ch == '］'){
					char ch1 = line.charAt(i -1);
					if(isChinese(ch1))
						break;
				}
				String str = String.valueOf(ch);
				result = str + result;
			}
			else 
				break;
		}
		return i;
	}
	
	
	// 判断一个字符是否是中文
	public  boolean isChinese(char c) {
	      return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断
	}
	
	
	
	public standard_info getRightStandard_info(String filename, List<standard_info> standard_infos){
		standard_info result = new standard_info();
		for(standard_info si:standard_infos){
			String number = si.getNumber();
			String filename_tmp = getDigitFromString(filename);
			String number_tmp = getDigitFromString(number);
			if(number_tmp.equals(filename_tmp)){
				result = si;
				break;
			}
		}
		return result;
	}
	
	public String getDigitFromString(String str){
		String result = "";
		String regex = "[^0-9]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		result = m.replaceAll("").trim();
		return result;
	}
	
	
	
	public String getSe_num(String line){
		Pattern p1 = Pattern.compile("(\\s)+");
		Matcher m1 = p1.matcher(line);
		line = m1.replaceAll("");
		Pattern p2 = Pattern.compile("(　)+");
		Matcher m2 = p2.matcher(line);
		line = m2.replaceAll("");
		line = line.trim();
		String result = "";
		int l = line.length();
		for(int i = 0; i < l ; i++){
			char c = line.charAt(i);
			if((c >= '0' && c <= '9')||c == '.'||c == '．')
				result = result + String.valueOf(c);
			else
				break;
		}
		return result;
	}
	
	public int judgeSection(String line, String term_title){
		line = removeSpace(line);
		String regex1 = "1适用范围";
		String regex2 = "1.1范围";
		String regex3 = "1范围";
		String regex4 = "1.1规范性引用文件";
		String regex5 = "1.2规范性引用文件";
		String regex6 = "1.1引用标准";
		String regex7 = "2规范性引用文件";
		String regex8 = "2引用标准";
		String regex9 = "3定义";
		String regex10 = "3术语和定义";
		String regex11 = "2术语和定义";
		String regex12 = "4术语和定义";
		String regex13 = "3术语、定义、符号及缩写";
		//String regex14 = "^(\\d)+(\\.(\\d)+)+";
		String regex15 = "^[0-9](.|．[0-9])*(\\s)*.+$";
		String regex16 = "^(\\d)+\\).*";
		boolean re1 = line.matches(regex1);
		boolean re2 = line.matches(regex2);
		boolean re3 = line.matches(regex3);
		boolean re4 = line.matches(regex4);
		boolean re5 = line.matches(regex5);
		boolean re6 = line.matches(regex6);
		boolean re7 = line.matches(regex7);
		boolean re8 = line.matches(regex8);
		boolean re9 = line.matches(regex9);
		boolean re10 = line.matches(regex10);
		boolean re11 = line.matches(regex11);
		boolean re12 = line.matches(regex12);
		boolean re13 = line.matches(regex13);
		//boolean re14 = line.matches(regex14);
		boolean re15 = line.matches(regex15);
		boolean re16 = (line.length()<=37);
		boolean re17 = isContainChinese(line);
		boolean re18 = endswithpunctuationmark(line);
		boolean re19 = line.matches(regex16);
		//范围章节
		if(re1||re2||re3)
			return 1;
		//规范性引用文件章节
		if(re4||re5||re6||re7||re8)
			return 2;
		//术语章节
		if(re9||re10||re11||re12||re13)
			return 3;
		//指标章节
		if(re15&&re16&&re18&&!re19&&re17){
			if(term_title.isEmpty())
				return 4;
			String se_num = getSe_num(line);
			char ch = se_num.charAt(0);
			char ch1 = term_title.trim().charAt(0);
			if(ch != ch1)
				return 4;
		}
		//其他
		return 0;
	}
	
	
	public String removeSpace(String line){
		String li = line;
		Pattern p1 = Pattern.compile("(\\s)+");
		Matcher m1 = p1.matcher(li);
		li = m1.replaceAll("");
		Pattern p2 = Pattern.compile("(　)+");
		Matcher m2 = p2.matcher(li);
		li = m2.replaceAll("");
		li = li.trim();
		return li;
	}
	
	
	  /**
	   * 判断字符串中是否包含中文
	   * @param str
	   * 待校验字符串
	   * @return 是否为中文
	   * @warn 不能校验是否为中文标点符号 
	   */
	  public  boolean isContainChinese(String str) {
	   Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
	   Matcher m = p.matcher(str);
	   if (m.find()) {
	    return true;
	   }
	   return false;
	  }
	  
	  
	  
		public boolean endswithpunctuationmark(String line) {
			boolean r1 = line.endsWith("。");
			boolean r2 = line.endsWith("；");
			boolean r3 = line.endsWith("、");
			boolean r4 = line.endsWith("，");
			boolean r5 = line.endsWith("：");
			boolean r6 = line.endsWith(".");
			boolean r7 = line.endsWith(";");
			boolean r8 = line.endsWith(":");
			boolean r9 = line.endsWith(",");
			boolean r10 = line.endsWith("．");
			boolean r11 = line.endsWith("?");
			boolean r12 = line.endsWith("？");
			boolean r13 = line.endsWith("%");
			boolean r14 = line.endsWith("％");
			boolean result = (!r1) && (!r2) && (!r3) && (!r4) && (!r5) && (!r6) && (!r7) && (!r8) && (!r9)&&(!r10)&&(!r11)&&(!r12)&&(!r13)&&(!r14);
			return result;
		}

		
		public String getTitle(String line){
			String result = "";
			int l = line.length();
			for(int i = 0; i < l; i++){
				char c = line.charAt(i);
				if((c >= '0' && c <= '9')||c == '.'||c == '．'||c == ' '||c == '　'||c == '	')
					result = result + String.valueOf(c);
				else
					break;
			}
			String re = line.replace(result, "");
			return re;
		}
		
		public boolean isRef_Files(String line){
			line = removeSpace(line);
			String regex = "[a-zA-Z][a-zA-Z].*";
			boolean result = line.matches(regex);
			boolean result1 = isContainChinese(line);
			return result&&result1;
		}
		
		
		public boolean judgeTermTitle(String line){
			line = removeSpace(line);
			String regex1 = "^(\\d)+(\\.(\\d)+)+";
			boolean result = line.matches(regex1);
			return result;
		}
	
	
	
	
	
}
