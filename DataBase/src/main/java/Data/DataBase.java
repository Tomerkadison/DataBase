package Data;

import Data.DataBaseHelpers.DataBaseFileHandler;
import Data.DataBaseHelpers.DataTypesParser;
import Data.TableHelpers.TableShower;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
                DataBaseFileHandler handler = new DataBaseFileHandler(this);
                DataTypesParser parser = new DataTypesParser(this);
                HashMap<String, HashMap<String, Object>> records = handler.readTableFromFile(tableName);
                handler.readMetaData();
                this.tables.put(tableName,new Table(String.valueOf(this.metaData.get(tableName).get("name"))
                        ,String.valueOf(this.metaData.get(tableName).get("primaryKeyName"))
                        , parser.parseToString(tableName,"stringParameters")
                        , parser.parseToString(tableName,"types")));
                this.tables.get(tableName).setRecords(records);
            }
        }
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
        new TableShower(this.tables.get(tableName)).show();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Table> getTables() {
        return tables;
    }

    public HashMap<String, HashMap<String, Object>> getMetaData() {
        return metaData;
    }

    public void setMetaData(HashMap<String, HashMap<String, Object>> metaData) {
        this.metaData = metaData;
    }
}
