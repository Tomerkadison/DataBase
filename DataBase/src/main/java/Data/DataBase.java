package Data;

import java.io.*;
import java.lang.reflect.Array;
import java.util.HashMap;

public class DataBase {
    private String name;
    private HashMap<String, Table> tables = new HashMap<>();
    private HashMap<String, HashMap<String, Object>> metaData = new HashMap<>();

    public DataBase(String name) {
        this.name = name;
        File tablesDir = new File(name);
        if (!tablesDir.exists()) {
            tablesDir.mkdirs();
        } else {
            for (File tableFile : tablesDir.listFiles()) {
                String tableName = tableFile.getName().substring(0,tableFile.getName().length()-4);
                HashMap<String, HashMap<String, Object>> records = this.readTableFromFile(tableName);
                readMetaData();
                this.tables.put(tableName,new Table(String.valueOf(this.metaData.get(tableName).get("name"))
                        ,String.valueOf(this.metaData.get(tableName).get("primaryKeyName"))
                        , parseToString(tableName,"stringParameters")
                        , parseToString(tableName,"types")));
                this.tables.get(tableName).setRecords(records);
            }
        }
    }

    public String[] parseToString(String tableName,String parameter){
        Object[] o = (Object[])this.metaData.get(tableName).get(parameter);
        String[] n = new String[o.length];
        for(int i = 0; i<o.length;i++){
            n[i] = String.valueOf(o[i]);
        }
        return n;
    }

    public void createTable(String name, String primaryKeyName, String[] stringParameters, String[] types) {
        this.tables.put(name, new Table(name, primaryKeyName, stringParameters, types));
    }

    public void removeTable(String name) {
        this.tables.remove(name);
    }

    public Table getTable(String tableName) {
        return this.tables.get(tableName);
    }

    public void showTable(String tableName) {
        this.tables.get(tableName).show();
    }

    public void writeDataBaseToFile() {
        for (String tableName : this.tables.keySet()) {
            saveTableToFile(tableName);
            HashMap<String, Object> tableMetaData = new HashMap<>();
            tableMetaData.put("name", tableName);
            tableMetaData.put("primaryKeyName", this.tables.get(tableName).getPrimaryKeyName());
            tableMetaData.put("stringParameters",(Object[]) this.tables.get(tableName).getParametersToTypes().keySet().toArray());
            tableMetaData.put("types", (Object[]) this.tables.get(tableName).getParametersToTypes().values().toArray());
            this.metaData.put(tableName, tableMetaData);
        }
        saveMetaData();
    }

    public void saveTableToFile(String tableName) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(this.name + "/" + tableName + ".txt"))) {
            os.writeObject(this.tables.get(tableName).getRecords());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, HashMap<String, Object>> readTableFromFile(String tableName) {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.name + "/" +tableName +".txt"))) {
            return (HashMap<String, HashMap<String, Object>>) is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveMetaData() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("metadata.txt"))) {
            os.writeObject(this.metaData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMetaData() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream( "metadata.txt"))) {
            this.metaData = (HashMap<String, HashMap<String, Object>>) is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
