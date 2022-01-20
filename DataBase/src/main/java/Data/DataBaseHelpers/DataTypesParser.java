package Data.DataBaseHelpers;

import Data.DataBase;

import java.util.ArrayList;

public class DataTypesParser {
    private DataBase dataBase;

    public DataTypesParser(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String[] parseToString(String tableName,String parameter){
        Object[] objectArray = (Object[])this.dataBase.getMetaData().get(tableName).get(parameter);
        String[] stringArray = new String[objectArray.length];
        for(int i = 0; i<objectArray.length;i++){
            stringArray[i] = String.valueOf(objectArray[i]);
        }
        return stringArray;
    }
}
