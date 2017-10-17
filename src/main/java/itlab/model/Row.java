package itlab.model;

import itlab.model.exceptions.NonExistingColumn;
import itlab.model.exceptions.UnsupportedValueException;
import itlab.model.types.Type;
import itlab.model.types.Types;
import itlab.model.types.ValueTypeFabric;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Row implements Serializable {
    Map<String, Type> values;

    public Row(Scheme sc, Map<String, String> columnValue) throws UnsupportedValueException {
        values = new HashMap<>();
        Integer exceptionCounter = 0;
        Map<String, String> notPassedValues = new HashMap<>();
        String errorText = new String();
        for (Map.Entry<String, Types> col : sc.getColumns().entrySet()) {
            try {
                String value = columnValue.get(col.getKey());
                if (value != null) {
                    values.put(col.getKey(), ValueTypeFabric.getInstance().createCorrectType(col.getValue(), value));
                } else {
                    values.put(col.getKey(), ValueTypeFabric.getInstance().createCorrectType(col.getValue()));
                }
            } catch (UnsupportedValueException ex) {
                exceptionCounter++;
                notPassedValues.put(col.getKey(), columnValue.get(col.getKey()));
                errorText += ex.getMessage() + "\n";
            }
        }
        if (exceptionCounter > 0) {
            throw new UnsupportedValueException(errorText);
        }
    }

    public void setValue(String column, String value, Types type) throws NonExistingColumn, UnsupportedValueException {
        Type t = values.get(column);
        if (t != null) {
            t.setValue(value);
            values.replace(column, t);
        } else {
            if (values.containsKey(column)) {
                values.replace(column, ValueTypeFabric.getInstance().createCorrectType(type, value));
            } else {
                throw new NonExistingColumn("Column :" + column + "not exists in row");
            }
        }

    }

    public Map<String,String> getValues(){
        Map<String,String> stringMapValues=new HashMap<>();
        for (Map.Entry<String,Type> entry: values.entrySet()
             ) {
            stringMapValues.put(entry.getKey(),entry.getValue().getStringValue());
        }
        return stringMapValues;
    }
    @Override
    public String toString() {
        return "Row{" +
                "values=" + values.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;

        for(Map.Entry<String, Type> item : values.entrySet()){
            if(!item.getValue().equals(row.values.get(item.getKey()))) return false;
        }

        return true;
    }


    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }
}
