import Data.DataBase;
import Data.DataBaseHelpers.DataBaseFileHandler;
import Data.Table;
import Data.TableHelpers.TableSearcher;
import Data.TableHelpers.TableShower;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase("dataDir");
        dataBase.getTable("people").alter("1","name","0");
        new TableSearcher(dataBase.getTable("people")).search("name","=","9");
    }

}
