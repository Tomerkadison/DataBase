package Data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

 public class Table {
    private HashMap<String, String> parametersToTypes = new HashMap<>();
    private ArrayList<String> parameters;
    private HashMap<String, HashMap<String, Object>> records = new HashMap<>();
    private String primaryKeyName;
    private int amount = 0;

    Table(String name,String primaryKeyName, String[] stringParameters, String[] types) {
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

    public void show() {
        showParameters();
        System.out.println("\n");
        for (String primaryKey : records.keySet()) {
            showRecordByPrimaryKey(primaryKey);
            System.out.println("\n");
        }
    }

    public void showParameters() {
        for (String parameter : this.parameters) {
            System.out.print(parameter + "      ");
        }
        System.out.println("\n");
    }

    public void showRecordByPrimaryKey(String primaryKey) {
        HashMap<String, Object> record = this.records.get(primaryKey);
        for (String parameter : this.parameters) {
            System.out.print(record.get(parameter) + "      ");
        }
        this.records.get(primaryKey);
    }

    public void search(String parameter, String operator, String value) {
        for (HashMap<String, Object> record : this.records.values()) {
            Condition condition = new Condition(record.get(parameter), operator, value);
            this.showParameters();
            if (condition.check()) {
                this.showRecordByPrimaryKey((String) record.get(this.primaryKeyName));
            }
        }
    }

    public void orSearch(String[] parameters, String[] operators, String[] values) {
        for (int i = 0; i < parameters.length; i++) {
            search(parameters[i], operators[i], values[i]);
        }
    }

    public void andSearch(String[] parameters, String[] operators, String[] values) {
        Condition condition = new Condition(parameters[0], operators[0], values[0]);
        for (HashMap<String, Object> record : this.records.values()) {
            boolean total = true;
            if (condition.check()) {
                for (int i = 1; i < parameters.length; i++) {
                    Condition condition2 = new Condition(parameters[i], operators[i], values[i]);
                    if (!condition.check()) {
                        total = false;
                    }
                }
                if (total) {
                    this.showParameters();
                    this.showRecordByPrimaryKey((String) record.get(this.primaryKeyName));
                }
            }
        }
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
 }


