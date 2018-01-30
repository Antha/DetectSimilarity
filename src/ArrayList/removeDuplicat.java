/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ArrayList;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author User
 */
public class removeDuplicat {
    
    
    public static ArrayList<String> removeDuplicatArrayList(ArrayList<String> data)
    {
        
      HashSet hs = new HashSet();
      hs.addAll(data);
      data.clear();
      data.addAll(hs);
       
      return data; 
    }
    
}
