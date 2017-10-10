package itlab.model;



import itlab.model.exceptions.NonExistingColumn;
import itlab.model.exceptions.UnsupportedValueException;
import itlab.model.types.Type;
import itlab.model.types.Types;
import itlab.model.types.ValueTypeFabric;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Row  implements Serializable {
    Map<String,Type> values;
    public Row(Scheme sc, Map<String,String> columnValue) throws UnsupportedValueException {
        values=new HashMap<>();
        Integer exceptionCounter=0;
        Map<String,String> notPassedValues=new HashMap<>();
        for (Map.Entry<String,Types> col :sc.getColumns().entrySet()
             ) {

            try {


                String value = columnValue.get(col.getKey());
                if (value != null) {
                    values.put(col.getKey(), ValueTypeFabric.getInstance().createCorrectType(col.getValue(), value));
                } else {
                    values.put(col.getKey(), ValueTypeFabric.getInstance().createCorrectType(col.getValue()));
                }
            }catch (UnsupportedValueException ex){
                exceptionCounter++;
                notPassedValues.put(col.getKey(),columnValue.get(col.getKey()));
            }
        }
        if (exceptionCounter>0){
            throw new UnsupportedValueException("Collumns"+notPassedValues+" didnt passed");
        }
    }
    public void setValue(String column,String value,Types type) throws NonExistingColumn, UnsupportedValueException {
        Type t= values.get(column);
        if (t!=null){
        t.setValue(value);
        values.replace(column,t);
        }else{
            if (values.containsKey(column)){
            values.replace(column,ValueTypeFabric.getInstance().createCorrectType(type,value));
            }else{
            throw new NonExistingColumn("Collumn :"+column+"not exsists in row");
            }
        }

    }


    @Override
    public String toString() {
        return "Row{" +
                "values=" + values.toString() +
                '}';
    }
}
