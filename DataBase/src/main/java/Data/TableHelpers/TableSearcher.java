package Data.TableHelpers;

import Data.Table;

import java.util.HashMap;

public class TableSearcher {
    private Table table;
    private TableShower tableShower;
    public TableSearcher(Table table) {
        this.table = table;
        tableShower = new TableShower(table);
    }
    public void search(String parameter, String operator, String value) {
        this.tableShower.showParameters();
        searchWithoutShowingParameters(parameter,operator,value);
    }

    public void orSearch(String[] parameters, String[] operators, String[] values) {
        this.tableShower.showParameters();
        for (int i = 0; i < parameters.length; i++) {
            searchWithoutShowingParameters(parameters[i], operators[i], values[i]);
            System.out.println("\n");
        }
    }

    public void searchWithoutShowingParameters(String parameter, String operator, String value){
        for (HashMap<String, Object> record : this.table.getRecords().values()) {
            Condition condition = new Condition(record.get(parameter), operator, value);
            if (condition.check()) {
                this.tableShower.showRecordByPrimaryKey((String) record.get(this.table.getPrimaryKeyName()));
            }
        }
    }

    public void andSearch(String[] parameters, String[] operators, String[] values) {
        this.tableShower.showParameters();
        for (HashMap<String, Object> record : this.table.getRecords().values()) {
            Condition condition = new Condition(record.get(parameters[0]), operators[0], values[0]);
            boolean total = true;
            if (condition.check()) {
                for (int i = 1; i < parameters.length; i++) {
                    Condition condition2 = new Condition(record.get(parameters[i]), operators[i], values[i]);
                    if (!condition.check()) {
                        total = false;
                    }
                }
                if (total) {
                    this.tableShower.showRecordByPrimaryKey((String) record.get(this.table.getPrimaryKeyName()));
                    System.out.println("\n");
                }
            }
        }
    }
}
