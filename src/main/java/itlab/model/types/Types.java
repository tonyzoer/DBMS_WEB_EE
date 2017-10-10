package itlab.model.types;

public enum Types {
    CHAR,CHARINTERVAL,DOUBLE,INTEGER,STRING,TIMEINTERVAL,TIME;
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
