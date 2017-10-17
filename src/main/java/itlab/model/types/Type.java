package itlab.model.types;

import itlab.model.exceptions.UnsupportedValueException;

import java.io.Serializable;

public abstract class Type implements Serializable {
    Type(String s) throws UnsupportedValueException { setValue(s);}
    public abstract void setValue(String s) throws UnsupportedValueException;
    public abstract String getStringValue();
    @Override
    public String toString() {
        return "Type{}";
    }
}
