package itlab.model;


import itlab.model.exceptions.NonExistingTable;
import itlab.model.exceptions.TableAlreadyExsists;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Database implements Serializable {
    Map<String, Table> tables;
    String name;

    public Database(String name, Map<String, Table> tables) {
        this.tables = tables;
        this.name = name;
    }

    public Database(String name) {
        this.name = name;
        tables = new HashMap<>();
    }

    public Map<String, Table> getTables() {
        return tables;
    }

    public String createTable(String name, Scheme scheme) throws TableAlreadyExsists {
        if (!tables.containsKey(name)) {
            Table t = new Table(name, scheme);
            tables.put(name, t);
            return name;
        } else {
            throw new TableAlreadyExsists(name + " table already exsists");
        }
    }

    public void deleteTable(String name) {
        tables.remove(name);
    }

    public Table getTable(String name) throws NonExistingTable {

        if (tables.containsKey(name))
            return tables.get(name);
        else throw new NonExistingTable("Table " + name + " in database " + this.name + " not exsists");
    }

    public void save() {
        try {
            String PATH = "/remote/dir/server/";
            String directoryName = PATH.concat("database/");
            String filename = name + ".db";

            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
                // If you require it to make the entire directory path including parents,
                // use directory.mkdirs(); here instead.
            }

            File f = new File(directoryName + "/" + filename);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.flush();
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            String PATH = "/remote/dir/server/";
            String directoryName = PATH.concat("database/");
            String filename = name + ".db";
            Object db = new ObjectInputStream(new FileInputStream(directoryName +filename)).readObject();
            if (db instanceof Database) {
                Database lDatabase = (Database) db;
                this.tables = lDatabase.tables;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        String PATH = "/remote/dir/server/";
        String directoryName = PATH.concat("database/");
        String filename = name + ".db";
        File f = new File(directoryName+filename);
        f.delete();
    }

    @Override
    public String toString() {
        return "Database{" +
                "tables=" + tables +
                ", name='" + name + '\'' +
                '}';
    }
}
