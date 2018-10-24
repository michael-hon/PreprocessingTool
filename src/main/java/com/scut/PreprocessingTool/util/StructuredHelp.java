package com.scut.PreprocessingTool.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.scut.PreprocessingTool.Entity.standard_info;



public class StructuredHelp {
	
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
