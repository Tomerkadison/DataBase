import Data.DataBase;
import Data.Table;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase("dataDir");
        Table people = dataBase.getTable("people");
        people.show();
        people.insert(new String[]{"itay","12","10/11/2009"});
        people.show();
    }
}
