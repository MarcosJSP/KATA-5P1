package kata.pkg5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
public class MailListReader {
    
    public List<String> read (String fileName){
        List<String> lista =  new LinkedList<String>();
        try(FileReader is = new FileReader(new File(fileName))){
            BufferedReader reader = new BufferedReader(is);
            while (reader.ready()){
                String email = reader.readLine();                   
                if (email.split("@").length == 2) lista.add(email);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
}

