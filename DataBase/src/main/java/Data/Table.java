package Data;

import Data.TableHelpers.TableShower;
import Data.TableHelpers.TypeCast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Table {
    private HashMap<String, String> parametersToTypes = new HashMap<>();
    private ArrayList<String> parameters;
    private HashMap<String, HashMap<String, Object>> records = new HashMap<>();
    private String primaryKeyName;
    private HashMap<String,HashMap<Integer, ArrayList<String>>> indexes = new HashMap<>();
    Table(String name, String primaryKeyName, String[] stringParameters, String[] types) {
        this.parameters = new ArrayList<String>(List.of(stringParameters));
        for (int i = 0; i < stringParameters.length; i++) {
            this.parametersToTypes.put(stringParameters[i], types[i]);
        }
        this.primaryKeyName = primaryKeyName;
    }

    public void insert(String[] stringParameters, String[] values) {
        HashMap<String, Object> record = new HashMap<>();
        for (int i = 0; i < stringParameters.length; i++) {
            String parameter = stringParameters[i];
            record.put(parameter, new TypeCast().cast(this.parametersToTypes.get(parameter), values[i]));
        }
        this.records.put((String) record.get(primaryKeyName), record);

    }

    public void insert(String[] values) {
        HashMap<String, Object> record = new HashMap<>();
        for (int i = 0; i < this.parameters.size(); i++) {
            String parameter = this.parameters.get(i);
            record.put(parameter, new TypeCast().cast(this.parametersToTypes.get(parameter), values[i]));
        }
        this.records.put((String) record.get(primaryKeyName), record);
    }

    public void alter(String primaryKey, String parameter, String value) {
        HashMap<String, Object> record = this.records.get(primaryKey);
        record.put(parameter, new TypeCast().cast(this.parametersToTypes.get(parameter), value));
    }

    public void removeRecord(String primaryKey) {
        this.records.remove(primaryKey);
    }

    public HashMap<String, HashMap<String, Object>> getRecords() {
        return records;
    }

    public void setRecords(HashMap<String, HashMap<String, Object>> records) {
        this.records = records;
    }

    public HashMap<String, String> getParametersToTypes() {
        return parametersToTypes;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void addNewHashIndex(String parameter){
        HashMap<Integer,ArrayList<String>> valueToPrimaryKeys = new HashMap<>();
        for(String key : this.records.keySet()){
            HashMap<String, Object> record = this.records.get(key);
            if(!valueToPrimaryKeys.containsKey(record.get(parameter).hashCode())){
                ArrayList<String> bucket = new ArrayList<String>();
                bucket.add(key);
                valueToPrimaryKeys.put(record.get(parameter).hashCode(),bucket);
            }else {
                valueToPrimaryKeys.get(record.get(parameter).hashCode()).add(key);
            }
        }
        this.indexes.put(parameter,valueToPrimaryKeys);
    }

    public void findByIndex(String parameter,Object wantedValue){
        for (String primaryKey :this.indexes.get(parameter).get(wantedValue.hashCode())){
            new TableShower(this).showRecordByPrimaryKey(primaryKey);
        }
    }

}


