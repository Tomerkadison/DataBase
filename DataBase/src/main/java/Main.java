import Data.DataBase;
import Data.DataBaseHelpers.DataBaseFileHandler;
import Data.Table;
import Data.TableHelpers.TableSearcher;
import Data.TableHelpers.TableShower;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase("dataDir");
        Table table = dataBase.getTable("people");
        table.insert(new String[]{ "aba","48","23/04/1973"} );
    }
}
