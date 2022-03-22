/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adnutils;

/**
 *
 * @author alumne
 */
public class Validation {
    
    public static double validDouble(String value){
        double result=-1;
        try{
            result=Double.parseDouble(value);
        }catch(NumberFormatException error1){
            
        }
        return result;    
    }
    
    public static int validInteger(String value){
        int result=-1;
        try{
            result=Integer.parseInt(value);
        }catch(NumberFormatException error1){
            
        }
        
        return result;
               
    }
    
}
