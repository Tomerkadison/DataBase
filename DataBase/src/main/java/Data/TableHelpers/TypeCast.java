package Data.TableHelpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TypeCast {

    public TypeCast() {}

    public Object cast(String type,String value){
        if(type.equals("STRING")){
            return value;
        }
        else if(type.equals("NUMBER")){
            try {
                return Integer.parseInt(value);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        else if(type.equals("DATE")){
            try {
                 new SimpleDateFormat("dd/MM/yyyy").parse(value).getTime()  ;
                 return value;
            } catch (ParseException e) {
                System.out.println("cant pars date.");
                e.printStackTrace();
            }
        }
        return null;
    }
}
