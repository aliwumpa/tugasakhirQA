/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.BufferedInputStream; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileReader;
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Collection;
import java.util.Iterator; 
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFCell; 
import org.apache.poi.hssf.usermodel.HSSFSheet; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.hssf.usermodel.HSSFRow;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;





/**
 *
 * @author RF
 */
public class dataset {
  
    public StanfordCoreNLP pipeline;
    
    public void readfile(){
    try{
     InputStream input = new BufferedInputStream(new FileInputStream("E:/Tugas Akhir Ali/dataset.xls"));
     
     POIFSFileSystem filesystem = new POIFSFileSystem(input);
     HSSFWorkbook workbook = new HSSFWorkbook(filesystem);
     HSSFSheet sheet = workbook.getSheetAt(0);
     String a;
     String b;
     
      MaxentTagger tagger = new MaxentTagger("E:/Tugas Akhir Ali/stanford-postagger-2011-04-20/models/left3words-wsj-0-18.tagger");
      Iterator rows = sheet.rowIterator();
      while(rows.hasNext()){
          HSSFRow row = (HSSFRow) rows.next();
          System.out.println("\n");
          Iterator cells = row.cellIterator();
          while(cells.hasNext()){
              HSSFCell cell = (HSSFCell) cells.next();
              if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType())
              System.out.print(cell.getNumericCellValue()+"  ");
              else
                  if(HSSFCell.CELL_TYPE_STRING==cell.getCellType())
                      System.out.print(cell.getStringCellValue()+"  ");
                      
                      else
                        if(HSSFCell.CELL_TYPE_BLANK==cell.getCellType())
                            System.out.print("Blank");
                            else
                            System.out.print("Unknown");
          }
      }
    }
    catch (Exception e){
        e.printStackTrace();
    }
    
    }
    public void tokenization(){
    
    StringBuilder sb = new StringBuilder();
    String s;
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader fileInput = new BufferedReader(input);
    System.out.println("file name: ");
    
    try{
        String filename = fileInput.readLine();
        File file = new File(filename);
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        while((s=br.readLine())!= null){
            sb.append(s);
        }
        
        StreamTokenizer streamTokenizer = new StreamTokenizer(fr);
        int i=0;
        int numberOfTokensGenerated = 0;
            while(i !=StreamTokenizer.TT_EOF)
            {
                i = streamTokenizer.nextToken();
                numberOfTokensGenerated++;
            }
            System.out.println("Jumlah tokens = "+ numberOfTokensGenerated);
            for(int counter=0;counter<numberOfTokensGenerated;counter++)
            {
                char character = sb.toString().charAt(counter);
                if(character == ' ')
                    System.out.println();
                else
                    System.out.print(character);
            }
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    
    public static String casefolding(String value){
        
        char[] array = value.toCharArray();
        array[0] = Character.toLowerCase(array[0]);
        
        for (int i = 1; i<array.length;i++){
            if (Character.isWhitespace(array[i-1])){
                array[i] = Character.toLowerCase(array[i]);
            }
        }
        return new String(array);
    }
      public void lemmatization(){
          Properties props;
          props = new Properties();
          props.put("annotators","tokenize,ssplit,pos,lemma");
          this.pipeline = new StanfordCoreNLP(props);
}
      public List<String> lemmatize(String documentText){
          List<String> lemmas = new LinkedList<String>();
          Annotation document = new Annotation(documentText);
          this.pipeline.annotate(document);
          List<CoreMap> sentences = document.get(SentencesAnnotation.class);
          for(CoreMap sentence: sentences){
              for (CoreLabel token:sentence.get(TokensAnnotation.class)){
                  lemmas.add(token.get(LemmaAnnotation.class));
              }
              
          }
          return lemmas;
      }
}

  
    

        
    

