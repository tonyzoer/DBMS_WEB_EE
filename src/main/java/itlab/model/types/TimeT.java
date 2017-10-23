package itlab.model.types;

import itlab.model.exceptions.UnsupportedValueException;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeT extends Type {
    LocalTime value;


    public TimeT(String value) throws UnsupportedValueException {
        super(value);
    }

    public LocalTime getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeT)) return false;

        TimeT timeT = (TimeT) o;

        return getValue() != null ? getValue().equals(timeT.getValue()) : timeT.getValue() == null;
    }

    @Override
    public int hashCode() {
        return getValue() != null ? getValue().hashCode() : 0;
    }


    @Override
    public void setValue(String s) throws UnsupportedValueException {
        if (s == null||s.equals("")) {
            value = LocalTime.MIDNIGHT;
        } else
            try {
                value = LocalTime.parse(s, DateTimeFormatter.ISO_TIME);
            } catch (DateTimeException ex) {
                throw new UnsupportedValueException(s + " not suportet value for Time Type", ex);
            }
    }

    @Override
    public String getStringValue() {
        return value.toString();
    }

    @Override
    public String toString() {
        return "TimeT{" +
                "value=" + value +
                '}';
    }
}
