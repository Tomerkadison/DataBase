package Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TypeCast {

    public TypeCast() {}

    public Object cast(String type,String value){
        if(type =="STRING"){
            return value;
        }
        else if(type =="NUMBER"){
            try {
                return Integer.parseInt(value);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        else if(type =="DATE"){
            try {
                return new SimpleDateFormat("dd/MM/yyyy").parse(value).getTime()  ;
            } catch (ParseException e) {
                System.out.println("cant pars date.");
                e.printStackTrace();
            }
        }
        return null;
    }
}
