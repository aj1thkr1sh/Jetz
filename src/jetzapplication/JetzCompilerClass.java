package jetzapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Krish
 */
public class JetzCompilerClass {
    
    StringTokenizer tokens=null;
    String temp="";
    String Line="";
    String line;
    HashMap<String,String> map=new HashMap<String,String>();
    
 
    void run(String className){
    
            PrintWriter writers=null;
        
            File f=new File("run.bat");
            
            try{
            
                FileOutputStream fos=new FileOutputStream(f);
            
                writers=new PrintWriter(f);
                //String first="javac "+className+".java";
                //writers.write(first);
                writers.write("\ncls");
                System.out.println("==========="+className);
                String second="java "+className;
                writers.write("\n"+second);
                
                writers.close();
                
                String Command[]={"cmd.exe","/c","Start","C:\\Users\\Krish\\Documents\\NetBeansProjects\\JetzApplication\\run.bat"};
                try {
                    Process run=Runtime.getRuntime().exec(Command);
                } catch (IOException ex) {
                    Logger.getLogger(Editorz.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                }catch(Exception e){
                    System.out.println(e.getMessage());
               }
    }
    
                
    String[] compile(String strings){
           
        
        String sentence="";
        String className="";
        int length,i,j,count=0,commentCount=0;
        Line+="";
        boolean flag=false;
       
        FileWriter writer=null;
        
        try {
            writer=new FileWriter("use_tempe.java",false);
        } catch (IOException ex) {
            Logger.getLogger(Editorz.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(rootPane,ex.getMessage());
        }
        
        
        
        tokens=new StringTokenizer(strings,"\n");
        
        
        
        
        
        while(tokens.hasMoreTokens()){
            //Line+=tokens.nextToken();
            Line="";
            temp="";
            String fileName="";
            
            //char ch[]=tokens.nextToken().toCharArray();
            sentence=tokens.nextToken();
           
            if(sentence.endsWith("~")){
                sentence+=" ";
            }
            
            if(sentence.trim().startsWith("print")){
                String qw=sentence;
                char qww[]=qw.toCharArray();
                sentence="";
                sentence+="zPrintLine(\"";
                for(int tw=0;tw<qww.length;++tw){
                    if(qww[tw]=='p' && qww[tw+1]=='r' && qww[tw+2]=='i' && qww[tw+3]=='n' && qww[tw+4]=='t' ){
                        tw+=6;
                        while(tw<qww.length){
                            sentence+=qww[tw];
                            ++tw;
                        }
                    }
                }
                sentence+="\")";
            }
            
            System.out.println(sentence);
            String ttemp=sentence;
            
            
            
            
            if(ttemp.contains("=")){
                int x,xx;
                String vsample="";
                String tttemp=ttemp.trim();
                char o[]=ttemp.toCharArray();
                int n=o.length;
                int fflag=0;
                for(x=0;x<n;++x){
                    if(Character.isAlphabetic(o[x])){
                        break;
                    }
                }
                System.out.println(x);
                for(xx=x;xx<n;++xx){
                    vsample+=o[xx];
                }
                
                System.out.println("&"+vsample+"&");
                
                StringTokenizer tottemp=new StringTokenizer(vsample,"=");
                String sa=tottemp.nextToken();
                System.out.println("@"+sa);
                StringTokenizer totot=new StringTokenizer(sa," ");
                totot.nextToken();
                String totot1=totot.nextToken();
                String totot2=tottemp.nextToken();
                totot1=totot1.trim();
                totot2=totot2.trim();
                System.out.println("$"+totot1+"$ $"+totot2+"$");
                if(!totot2.contains("new")){
                    map.put(totot1,totot2);
                    System.out.println(totot1+" and "+totot2+" inserted");
                }
                
            }
            
            if(  !sentence.endsWith("{") || !sentence.endsWith("}")  ){
                if(sentence.startsWith("use")){
                    sentence+=";";
                }
                else if(sentence.trim().startsWith("class")){
                }else if(sentence.trim().startsWith("zMain")){
                }else if(sentence.contains("for") || sentence.contains("do") || sentence.trim().startsWith("while")){
                }else if(sentence.startsWith("use")){
                    sentence+=";";
                }else if(!sentence.startsWith("while") && sentence.contains("while")){
                    sentence+=";";
                }else if(sentence.trim().startsWith("~") && sentence.trim().endsWith("~")){
                }else if( sentence.trim().startsWith("try")){
                }else if(sentence.trim().contains("catch")){
                }else if(sentence.contains("finally")){
                }else if(sentence.trim().startsWith("catch")){
                    sentence+=";";
                }else if(sentence.trim().endsWith(";")){
                
                }else if(sentence.endsWith("{")){
                
                }else if(sentence.endsWith("}")){
                
                }else{
                    sentence+=";";
                }
            }
            
            char ch[]=sentence.toCharArray();
            length=ch.length;
            //JOptionPane.showMessageDialog(rootPane,String.valueOf(ch)+" length is "+length);
            for(i=0;i<length;++i){
                if(ch[i]=='"'){
                    ++count;
                }
                if(ch[i]=='~'){
                    ++commentCount;
                }

                if( ch[i]==' ' || ch[i]=='.' || ch[i]=='(' || ch[i]==')' || ch[i]==';' || ch[i]==',' ){

                    Line+=process(temp.trim(),count,commentCount);
                    
                   
                    
                    //JOptionPane.showMessageDialog(rootPane,temp.trim());
                    Line+=ch[i];
                    
                    
                    //System.out.println(temp.trim());
                    
                     if(temp.trim().equals("class") && flag==false){
                        
                        //Line+="use_temp";
                        while(!Character.isAlphabetic(ch[i])){
                            System.out.println(ch[i]);
                            
                            i++;
                 
                        }
                        while(Character.isAlphabetic(ch[i]) || Character.isDigit(ch[i])){
                            System.out.println(ch[i]);
                            className+=ch[i];
                            
                            try{
                                
                                i++;
                                char cccccc=ch[i];
                            }catch(ArrayIndexOutOfBoundsException e){
                            break;
                            }
                            //i++;
                        }
                        System.out.println("Class Name is "+className+" Length is "+className.length());
                        --i;
                        Line+=className;

                    }
                    
                    
                    
                    
                    temp="";
                }else{
                    temp+=ch[i];
   
                }
            }
            Line+=temp+"\n";
            try {
                
                 
                  
                
                writer.write(Line);
            } catch (IOException ex) {
                Logger.getLogger(Editorz.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
        
        //outputArea.setText("Success");
        Line="";
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Editorz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         PrintWriter writers=null;
        try{
           
            File ff=new File("use_tempe.java");
            
            try{
                File fdel=new File(className+".java");
                fdel.delete();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
            File fff=new File(className+".java");
            
            ff.renameTo(fff);
            
            
            File f=new File("runExe.bat");
            
            
            FileOutputStream fos=new FileOutputStream(f);
            
            writers=new PrintWriter(f);
            String first="javac "+className+".java";
            writers.write(first);
            //writers.write("\ncls");
            //String second="java "+className;
            //writers.write("\n"+second);
            
           

            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            if(writers!=null){
                try{
                writers.close();
                }catch(Exception e){
                    System.out.println(e+"   TWO");
                }
            }
        }
    
        String error="";
        try{
        
            Process p=Runtime.getRuntime().exec("javac "+className+".java");
            BufferedReader reader=new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String line="";
		StringBuffer buffer=new StringBuffer();
		//line=reader.readLine();
		//Thread.sleep(6000);
		//System.out.println(line);
		while((line=reader.readLine())!=null){
			//Thread.sleep(5000);
			buffer.append(line+"\n");
			//System.out.println(line);
			
		}
                System.out.println("printing");
		System.out.println(buffer.toString());
                error=buffer.toString();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
              
        
/*
        
        String Command[]={"cmd.exe","/c","Start","C:\\Users\\Krish\\Documents\\NetBeansProjects\\JetzApplication\\runExe.bat"};
        try {
            Process run=Runtime.getRuntime().exec(Command);
        } catch (IOException ex) {
            Logger.getLogger(Editorz.class.getName()).log(Level.SEVERE, null, ex);
        }
  */      
        String[] results=new String[2];
        if(error.length()==0){
            System.out.println("NoError*****************");
            //return className;
            
        }
        results[0]=className;
        results[1]=error;
        return results;
        
        
    }
    
    
    String process(String s,int count,int cc){
        
        if(s.equals(" ")){
            return "";
        }
        
        if(s.equals("~") && cc%2!=0){
            System.out.println("came to /*  "+cc);
            return "/*";
        }

        
        if((count%2==0) && (cc%2==0)){
            
        if(s.equals("~")){
            return "*/";
        }
        if(s.equals("use")){
            return "import";
        }
        if(s.equals("jetz")){
            return "java";
        }
        if(s.equals("zMain")){
            return "public static void main";
        }
        if(s.equals("zInt")){
            return "int";
        }
        if(s.equals("zFloat")){
            return "float";
        }
        if(s.equals("zString")){
            return "String";
        }
        if(s.equals("zBoolean")){
            return "boolean";
        }
        if(s.equals("zDouble")){
            return "double";
        }
        if(s.equals("zStringArray")){
            return "String[]";
        }
        if(s.equals("zIntArray")){
            return "int[]";
        }      
        if(s.equals("zFloatArray")){
            return "float[]";
        }
        if(s.equals("zDoubleArray")){
            return "double[]";
        }
        if(s.equals("zCharArray")){
            return "char[]";
        }
        if(s.equals("zPrint")){
            return "System.out.print";
        }
        if(s.equals("zPrintLine")){
            return "System.out.println";
        }
        if(s.equals("zReader")){
            return "Scanner";
        }
        if(s.equals("nextzInt")){
            return "nextInt";
        }
        if(s.equals("nextzFloat")){
            return "nextFloat";
        }
        if(s.equals("nextzDouble"))
            return "nextDouble";
        }
        if(s.equals("iando")){
            return "io.*;import java.util.Scanner";
        }
        if(s.equals("zSystemInput")){
            return "System.in";
        }if(s.equals("nextzString")){
            return "next";
        }
        if(s.equals("nextzStringLine")){
            return "nextLine";
        }
        if(s.equals("zEqualTo")){
            return "=";
        }
        if(s.equals("zPlus")){
            return "+";
        }
        if(s.equals("zMinus")){
            return "-";
        }
        if(s.equals("zMultiply")){
            return "*";
        }
        if(s.equals("zDivide")){
            return "/";
        }
        if(s.equals("zLoop")){
            return "while";
        }
        if(s.startsWith("#") && s.endsWith("#")){
           int w,q=0;
           String g="";
           char parse[]=s.toCharArray();
           int n=parse.length;
           for(w=1;w<n-1;++w){
               g+=parse[w];
           }
           System.out.println("sbe is the map "+g+" is"+map.get(g));
           
           String ta=map.get(g);
           ta=map.get(ta);
           if(ta!=null){
               if(ta.startsWith("\"") && ta.endsWith("\"")){
                   char ba[]=ta.toCharArray();
                   String be="";
                   for(int e=1;e<ba.length-1;++e){
                       if(Character.isAlphabetic(ba[e]) || Character.isDefined(ba[e])){
                           be+=ba[e];
                       }
                   }
                   return be;
               }
           }
           
           if(map.get(g).trim().startsWith("\"") && map.get(g).trim().endsWith("\"")){
               String gcg=map.get(g).trim();
               char ba[]=gcg.toCharArray();
               String be="";
               for(int e=1;e<ba.length-1;++e){
                   if(Character.isAlphabetic(ba[e]) || Character.isDigit(ba[e])){
                       be+=ba[e];
                   }
               }
               System.out.println("be "+be);
               return be;
           }
           
           int tio,fl=1;
           String gg="",ggg="";
           System.out.println("____");
           while((g=map.get(g))!=null){
               gg=g;
               String grg="",grg1="";
               if(gg.startsWith("\"")){
                   char cgg[]=gg.toCharArray();
                   for(tio=0;tio<gg.length();++tio){
                       if(cgg[tio]=='+'){
                           ++tio;
                           while(tio<gg.length()){
                               
                               grg+=cgg[tio];
                               ++tio;
                           }
                       }else{
                           grg1+=cgg[tio];
                       }
                   }
                   if(map.get(grg)==null){
                       fl=0;
                   }else{
                       ggg=grg1+map.get(grg);
                   }
                   cgg=ggg.toCharArray();
                   ggg="";
                   for(tio=0;tio<cgg.length;++tio){
                        if(Character.isAlphabetic(cgg[tio]) || Character.isDigit(cgg[tio])){
                            ggg+=cgg[tio];
                        }
                   }
                   System.out.println("-%"+grg1+"-"+grg);
                   if(fl==0){
                   for(tio=1;tio<cgg.length;++tio){
                       if(Character.isAlphabetic(cgg[tio]) || Character.isDigit(cgg[tio])){
                        ggg+=cgg[tio];
                      }
                   }
                   ggg=ggg.trim();
                   }
                   System.out.println("%%"+ggg);
               }else if(gg.contains("+")){
                   String si=gg;
                   String ggg0;
                   char cgg[]=gg.toCharArray();
                   for(tio=0;tio<gg.length();++tio){
                       if(cgg[tio]=='+'){
                           break;
                       }else{
                           ggg+=cgg[tio];
                       }
                   }
                   System.out.println("()"+ggg);
                   ggg=map.get(ggg);
                   ggg0=ggg;
                   for(int ttio=tio+1;ttio<gg.length();++ttio){
                       if( cgg[ttio]=='\"' ){
                           continue;
                       }else{
                           ggg+=cgg[ttio];
                       }
                   }
                   System.out.println("><"+ggg);
                   char sii[]=si.toCharArray();
                   int sFlag=0;
                   String siiTemp="";
                   for(int re=0;re<sii.length;++re){
                       if(sii[re]=='+'){
                           ++re;
                           while(re<sii.length){
                               siiTemp+=sii[re];
                               ++re;
                           }
                       }
                   }
                   
                   if(map.get(siiTemp)==null){
                       sFlag=1;
                   }
                   
                   System.out.println(".."+siiTemp);
                   
                   if(sFlag==1){
                   char ccgg[]=ggg.toCharArray();
                   ggg="";
                   for(int ttio=0;ttio<ccgg.length;++ttio){
                       if(ccgg[ttio]!='"'){
                           ggg+=ccgg[ttio];
                       }
                   }
                   ccgg=ggg.toCharArray();
                   ggg="";
                   for(int ttio=0;ttio<ccgg.length;++ttio){
                       if(ccgg[ttio]!='+'){
                           ggg+=ccgg[ttio];
                       }
                   }
                   System.out.println(ggg+" "+tio);
                   //ggg=ggg.trim();
                   }else{
                       ggg=ggg0+map.get(siiTemp);
                       char gggs[]=ggg.toCharArray();
                       ggg="";
                       for(int ggs=0;ggs<gggs.length;++ggs){
                           if(gggs[ggs]!='"'){
                               ggg+=gggs[ggs];
                           }
                       }
                   }
               }
           }
           
           System.out.println("%"+ggg);
           return ggg;
        }
        
        
        
        
        
        return s;
    }
    
}
