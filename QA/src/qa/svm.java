/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author RF
 */
public class svm {
    
    
    public void TermFrequency(){
        int n = 1000;
        int m = 1000;
        int a = 1000;
        String text = "";
        StringTokenizer ST = new StringTokenizer(text);
        String word[] = new String[n];
        String unique[] = new String[m];
        String w;
        int count = -1;
        
        while(ST.hasMoreTokens()){
            count++;
            w = ST.nextToken();
            word[count] = w;
            
            System.out.println(count + ": "+word[count]);
            
        }
        
        for(int i = 0;i<a;i++){
            if((!Arrays.asList(unique).contains(word[i]))){
                unique[i] = word[i];
                
            }
        }
        int[] measure = new int[a];
        
        for(int z = 0;z < a;z++){
            if(unique[z] != null){
                for (int j = 0;j<a;j++){
                    if (unique[z].equals(word[j])){
                        measure[z] += 1;
                    }
                }
                System.out.println(unique[z] + " : "+measure[z]);
            }
        }
    }
    
}
