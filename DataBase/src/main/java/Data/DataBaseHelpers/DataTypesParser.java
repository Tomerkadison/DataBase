package Data.DataBaseHelpers;

import Data.DataBase;

import java.util.ArrayList;

public class DataTypesParser {
    private DataBase dataBase;

    public DataTypesParser(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String[] getOrderedTypes(String tableName){
        ArrayList<String> parameters = this.dataBase.getTables().get(tableName).getParameters();
        String types[] = new String[parameters.size()];
        for(int i = 0;i<parameters.size();i++){
            types[i] = this.dataBase.getTables().get(tableName).getParametersToTypes().get(parameters.get(i));
        }
        return types;
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
