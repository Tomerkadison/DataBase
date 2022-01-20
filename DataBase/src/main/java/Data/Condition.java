package Data;

public class Condition {
    private String value1;
    private String operator;
    private String value2;

    public Condition(Object value1, String operator, Object value2) {
        this.value1 = String.valueOf(value1);
        this.operator = operator;
        this.value2 = String.valueOf(value2);
    }

    public boolean check(){
        if(this.operator.equals("=")){
            return this.value1.equals(this.value2);
        }else if (this.operator.equals("!=")) {
            return !this.value1.equals(this.value2);
        }else{
            Integer num1 = Integer.parseInt(this.value1);
            Integer num2 = Integer.parseInt(value2);
            if (this.operator.equals(">")){
                return num1 > num2;
            }
            if (this.operator.equals("<")){
                return num1 < num2;
            }
        }
        return false;

    }
}
