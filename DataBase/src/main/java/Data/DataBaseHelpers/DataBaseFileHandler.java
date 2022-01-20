package Data.DataBaseHelpers;

import Data.DataBase;

import java.io.*;
import java.util.HashMap;

public class DataBaseFileHandler {
    private DataBase dataBase;

    public DataBaseFileHandler(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void writeDataBaseToFile() {
        for (String tableName : this.dataBase.getTables().keySet()) {
            saveTableToFile(tableName);
        }
        saveMetaData();
    }

    public void saveTableToFile(String tableName) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(this.dataBase.getName() + "/" + tableName + ".txt"))) {
            os.writeObject(this.dataBase.getTables().get(tableName).getRecords());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, HashMap<String, Object>> readTableFromFile(String tableName) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.dataBase.getName() + "/" + tableName + ".txt"))) {
            return (HashMap<String, HashMap<String, Object>>) is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveMetaData() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("metadata.txt"))) {
            os.writeObject(this.dataBase.getMetaData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMetaData() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("metadata.txt"))) {
            this.dataBase.setMetaData((HashMap<String, HashMap<String, Object>>) is.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
