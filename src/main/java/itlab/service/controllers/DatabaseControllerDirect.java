package itlab.service.controllers;

import itlab.model.Database;
import itlab.model.Row;
import itlab.model.Scheme;
import itlab.model.exceptions.*;
import itlab.model.types.Types;
import itlab.service.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseControllerDirect implements DatabaseController {
    private static DatabaseControllerDirect ourInstance = new DatabaseControllerDirect();
    private Map<String, Database> dbInMemory = new HashMap<>();

    public static DatabaseControllerDirect getInstance() {
        return ourInstance;
    }

    public DatabaseControllerDirect() {
    }

    @Override
    public void createDatabase(String name) {
        dbInMemory.put(name, new Database(name));
    }

    @Override
    public void deleteDatabase(String name) {
        dbInMemory.remove(name);
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        new Database(name).delete();
    }

    @Override
    public Database getDatabase(String name) {
        return dbInMemory.get(name);
    }

    @Override
    public void saveDatabase(String name) {
        dbInMemory.get(name).save();
    }

    @Override
    public void loadDatabase(String name) {
        if (!dbInMemory.containsKey(name)) {
            dbInMemory.put(name, new Database(name));
            dbInMemory.get(name).load();
        }
    }

    @Override
    public void addTable(String databaseName, String tableName, Map<String, String> columns) throws TableAlreadyExsists {
        Database db = dbInMemory.get(databaseName);
        if (db != null) {
            Map<String, Types> collumnsTypes = new HashMap<>();
            for (Map.Entry<String, String> col : columns.entrySet()
                    ) {
                collumnsTypes.put(col.getKey(), Types.valueOf(col.getValue()));
            }
            Scheme scheme = new Scheme(collumnsTypes);
            db.createTable(tableName, scheme);

        }
    }

    @Override
    public Map<String, String> getTableRows(String databaseName, String tableName) throws NonExistingTable {
        Map<String, Row> rows = dbInMemory.get(databaseName).getTable(tableName).getRows();
        Map<String, String> rowsMap = new HashMap<>();
        for (Map.Entry<String, Row> row : rows.entrySet()
                ) {
            rowsMap.put(row.getKey(), row.getValue().toString());
        }
        return rowsMap;
    }

    @Override
    public Map<String, Map<String, String>> getTableRowsAsMap(String databaseName, String tableName) throws NonExistingTable {
        Map<String, Row> rows = dbInMemory.get(databaseName).getTable(tableName).getRows();
        Map<String, Map<String, String>> rowsMap = new HashMap<>();
        for (Map.Entry<String, Row> row : rows.entrySet()
                ) {
            rowsMap.put(row.getKey(), row.getValue().getValues());
        }
        return rowsMap;
    }


    @Override
    public Map<String, String> getTableScheme(String databaseName, String tableName) throws NonExistingTable {
        Map<String, String> collums = new HashMap<>();
        for (Map.Entry<String, Types> col : dbInMemory.get(databaseName).getTable(tableName).getScheme().getColumns().entrySet()
                ) {
            collums.put(col.getKey(), col.getValue().toString());
        }
        return collums;
    }

    @Override
    public void removeTable(String databaseName, String tableName) {
            dbInMemory.get(databaseName).deleteTable(tableName);
    }

    @Override
    public void renameTable(String databaseName, String tableNameCurrent, String tableNameNew) throws NonExistingTable {
        dbInMemory.get(databaseName).getTable(tableNameCurrent).setName(tableNameNew);
    }

    @Override
    public String addRowToTable(String databaseName, String tableName, Map<String, String> collumnValues) throws UnsupportedValueException, NonExistingTable {
        return dbInMemory.get(databaseName).getTable(tableName).addRow(collumnValues);
    }

    @Override
    public void removeRowFromTable(String databaseName, String tableName, String rowUUID) throws NonExistingTable {
        dbInMemory.get(databaseName).getTable(tableName).deleteRow(rowUUID);
    }

    @Override
    public void updateRowInTable(String databaseName, String tableName, String rowUUID, Map<String, String> collumnValues) throws UnsupportedValueException, NonExistingTable, NonExistingColumn {
        dbInMemory.get(databaseName).getTable(tableName).updateRow(rowUUID, collumnValues);
    }

    @Override
    public void tableIntersection(String table1, String table2, String newTableName) throws NonExistingTable {

    }

    @Override
    public void tableIntersection(String database, String table1, String table2, String newTableName) throws NonExistingTable, TableAlreadyExsists {
        dbInMemory.get(database).intersectionTable(table1, table2, newTableName);
    }

    @Override
    public void tableDifference(String table1, String table2, String newTableName) throws NonExistingTable {

    }

    @Override
    public void tableDifference(String database, String table1, String table2, String newTableName) throws NonExistingTable, TableAlreadyExsists {
        dbInMemory.get(database).substractTables(table1, table2, newTableName);
    }

    @Override
    public List<String> getAllDatabases() {
        return DatabaseHelper.getAllSavedDatabases();
    }

    @Override
    public List<String> getAllTables(String databaseName) {
        List<String> tables = new ArrayList<>(dbInMemory.get(databaseName).getTables().keySet());
        return tables;
    }

    @Override
    public List<String> getAllTypes() {
        Types[] types = Types.values();
        List<String> typesStrings = new ArrayList<>();
        for (Types t : types
                ) {
            typesStrings.add(t.toString());
        }
        return typesStrings;
    }

    @Override
    public String tableToString(String database, String table) throws NonExistingTable {
        return dbInMemory.get(database).getTable(table).toString();
    }

    @Override
    public Map<String, String> getRow(String database, String table, String UUID) throws NonExistingRow, NonExistingTable {
        return dbInMemory.get(database).getTable(table).getRow(UUID).getValues();
    }
}