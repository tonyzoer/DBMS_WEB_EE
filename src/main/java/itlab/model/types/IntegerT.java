package itlab.model.types;

import itlab.model.exceptions.UnsupportedValueException;

public class IntegerT extends Type {
    Integer value;

    IntegerT(String s) throws UnsupportedValueException {
        super(s);
    }

    @Override
    public void setValue(String s) throws UnsupportedValueException {
        try {
            if (s == null||s.equals("")) {
                value = Integer.MIN_VALUE;
            } else
                value = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new UnsupportedValueException(value + "not suported for class Integer", e);
        }
    }

    @Override
    public String getStringValue() {
        return value.toString();
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerT)) return false;

        IntegerT integerT = (IntegerT) o;

        return getValue() != null ? getValue().equals(integerT.getValue()) : integerT.getValue() == null;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }


    @Override
    public String toString() {
        return "IntegerT{" +
                "value=" + value +
                '}';
    }
}
