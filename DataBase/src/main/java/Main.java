import Data.DataBase;
import Data.Table;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase("dataDir");
        dataBase.showTable("people.txt");
    }
}
