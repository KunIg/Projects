import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Parser {

    static  List<String> lines = new ArrayList<String>();


    public Parser(List<String> lines)
    {
        this.lines = lines;
    }

    public static Parser parse(List<String> lines)
    {
        List<String> arrList = new ArrayList<String>();
        for (String s : lines)
        {
            arrList.add(s);
        }

        return new Parser(arrList);
    }

    public static Parser linecount()
    {	
    	int count=0;
    	List<String> counter = new ArrayList<String>();
        for (int i = 0; i < lines.size(); i++)
        {
            count++;
        }
        counter.add(new Integer(count).toString());
        return new Parser(counter);
    }


    public static Parser wordcount()
    {	
    	int count=0;
    	List<String> counter = new ArrayList<String>();
        for (String s : lines)
        {
            String[] splitted=s.split(" ");
            for (String k : splitted) {
            	 if(!k.equals("")){
            		    count++;
            	    }
            }
        }
        counter.add(new Integer(count).toString());
        return new Parser(counter);
    }


    public String toString()
    {

        String output = "";
        int i = 0;
        for (String s : lines)
        {
            if (i == lines.size()-1)
            {
                output += s;
            }
            else
            {
                output += s + "\n";
            }
            i++;
        }
        
        return output; 
    }



    public Parser grab(String str)
    {
        List<String> strList = new ArrayList<String>();

        for (String s : lines)
        {
            if (s.contains(str))
            {
                strList.add(s);
            }
        }
        return new Parser(strList);
    }

    public Parser echo()
    {	
    	List<String> strList = new ArrayList<String>();
    	String outputstring="";
        for (String s : lines)
        {	
        	String[]strippedarr=s.split("\\s+");
        	for (String i:strippedarr) {
        	if(i.equals("")) {
        			continue;
        		}
        	String temp=i.trim();
        	outputstring+= temp+" ";
        	}
        }
        strList.add(outputstring.trim());
        return new Parser(strList);
    }
    
    public Parser chop(int start, int end)
    {	if (start==0) {
    	start=1;
    }
    	List<String> strList = new ArrayList<String>();
        for (String s : lines) {
        	if (start>end || start >s.length()) {
        		strList.add("");
        		continue;
        	}
        	if (end>s.length()) {
        		strList.add(s.substring(start-1, s.length()));
        	} else {
        	strList.add(s.substring(start-1, end));
        	}
        	}
        return new Parser(strList);
    }
    
public Parser shuffle()
    {	
    	String begin;
    	String end;
    	String toBeShifted;
    	String shiftedString="";
    	String output;
    	int startIndex;
    	int endIndex;
    	String sentence;
    	List<String> strList = new ArrayList<String>();
        for (String s : lines) {
        	sentence="";
            String[] splitted=s.split("\\s+");
            for (String k : splitted) {
            	 if(!k.equals("")){
            		 if (k.length()>2) {
            		 if (k.charAt(k.length()-1)==44||k.charAt(k.length()-1)==46) {
            			 if(String.valueOf(k.charAt(k.length()-3)).equals("\'")) {
            				 end=String.valueOf(k.charAt(k.length()-3))+String.valueOf(k.charAt(k.length()-2))+String.valueOf(k.charAt(k.length()-1));
            				 endIndex=k.length()-3;
            			 } else {
            				 end=String.valueOf(k.charAt(k.length()-2))+String.valueOf(k.charAt(k.length()-1));
            				 endIndex=k.length()-2;
            			 }
            		 } else {
            			 if(String.valueOf(k.charAt(k.length()-2)).equals("\'")) {
            				 end=String.valueOf(k.charAt(k.length()-2))+String.valueOf(k.charAt(k.length()-1));
            				 endIndex=k.length()-2;
            			 } else {
            				 end=String.valueOf(k.charAt(k.length()-1));
            				 endIndex=k.length()-1;
            			 }
            			    }
            		 if (String.valueOf(k.charAt(1)).equals("\'")) {
            			 begin=String.valueOf(k.charAt(0))+String.valueOf(k.charAt(1));
            			 startIndex=2;
            		 } else {
            			 begin=String.valueOf(k.charAt(0));
            			 startIndex=1;
            		 } 
            		 toBeShifted=k.substring(startIndex, endIndex);
            		 shiftedString=toBeShifted.substring(1) + toBeShifted.substring(0, 1);
            		 output=begin+shiftedString+end;
            		 sentence+=output.trim()+" ";
            		 } else {
            			 sentence+=k.trim()+" ";
            		 }
            	 } 
            }
        	strList.add(sentence.trim());
        }
        return new Parser(strList);
    }     
    
    
}

