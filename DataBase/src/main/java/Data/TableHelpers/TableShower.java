package Data.TableHelpers;

import Data.Table;

import java.util.HashMap;

public class TableShower{
    private Table table;

    public TableShower(Table table) {
        this.table = table;
    }

    public void show() {
        showParameters();
        for (String primaryKey : this.table.getRecords().keySet()) {
            showRecordByPrimaryKey(primaryKey);
            System.out.println("\n");
        }
    }

    public void showParameters() {
        for (String parameter : this.table.getParameters()) {
            System.out.print(parameter + "      ");
        }
        System.out.println("\n");
    }

    public void showRecordByPrimaryKey(String primaryKey) {
        HashMap<String, Object> record = this.table.getRecords().get(primaryKey);
        for (String parameter : this.table.getParameters()) {
            System.out.print(record.get(parameter) + "      ");
        }
        this.table.getRecords().get(primaryKey);
    }
}
