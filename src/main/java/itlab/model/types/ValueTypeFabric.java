package itlab.model.types;


import itlab.model.exceptions.UnsupportedValueException;

public class ValueTypeFabric {

    private static ValueTypeFabric instance;

    private ValueTypeFabric() {
    }

    public static ValueTypeFabric getInstance() {
        if (instance == null) {
            instance = new ValueTypeFabric();
        }
        return instance;
    }

    public Type createCorrectType(Types t, String val) throws UnsupportedValueException {
        switch (t) {
            case CHAR:
//                if (CharT.supports(val)) {
                return new CharT(val);
//                }
//                break;
            case CHARINTERVAL:
//                if (CharInvT.supports(val)) {
                return new CharInvT(val);
//                }
//                break;
            case DOUBLE:
//                if (DoubleT.supports(val)) {
                return new DoubleT(val);
//                }
//                break;
            case INTEGER:
//                if (IntegerT.supports(val)) {
                return new IntegerT(val);
//                }
//                break;
            case STRING:
                return new StringT(val);
            case TIMEINTERVAL:
//                if (TimeInvT.supports(val))
                return new TimeInvT(val);
//                break;
            case TIME:
//                if (TimeT.supports(val)) {
                return new TimeT(val);
        }
//                break;
//        }
        return null;
    }

    public Type createCorrectType(Types t) throws UnsupportedValueException {
        switch (t) {
            case CHAR:
                return new CharT("");
            case CHARINTERVAL:
                return new CharInvT("");
            case DOUBLE:
                return new DoubleT("");
            case INTEGER:
                return new IntegerT("");
            case STRING:
                return new StringT("");
            case TIMEINTERVAL:
                return new TimeInvT("");
            case TIME:
                return new TimeT("");
        }
        return null;
    }
}
