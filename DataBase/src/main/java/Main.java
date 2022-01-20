import Data.DataBase;
import Data.DataBaseHelpers.DataBaseFileHandler;
import Data.Table;
import Data.TableHelpers.TableSearcher;
import Data.TableHelpers.TableShower;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase("dataDir");
        Table table = dataBase.getTable("people");
        for (int i =1;i<=90;i++){
            table.insert(new String[]{ String.valueOf(i),String.valueOf(i),"23/04/1973"} );
        }
        table.addNewHashIndex("age");
        table.findByIndex("age","18");
    }
}
