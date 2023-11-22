package dev.omedia;

import dev.omedia.annotations.Column;
import dev.omedia.annotations.Id;
import dev.omedia.annotations.Table;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyEntityManager<E> {

    public boolean insert(Object o) {
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            String table = getTable(o);
            ArrayList<String> columns = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();
            getColumnAndValues(o, columns, values);
            String sql = String.format("INSERT INTO %s (%s) VALUES (%s);",
                    table, String.join(", ", columns), String.join(", ", values));
            Statement statement = conn.createStatement();
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Object o) {
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            String table = getTable(o);
            ArrayList<String> columns = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();
            getColumnAndValues(o, columns, values);
            StringBuilder forUpdate = new StringBuilder("");
            for (int i = 0; i < columns.size(); i++) {
                String format;
                if (i != columns.size() - 1) {
                    format = "%s = %s,";
                } else {
                    format = "%s = %s";
                }
                forUpdate.append(String.format(format, columns.get(i), values.get(i)));
            }
            Field id = Arrays.stream(o.getClass().getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(Id.class))
                    .findFirst().orElseThrow();
            id.setAccessible(true);
            String sql = String.format("UPDATE %s SET %s WHERE %s = %s;",
                    table, forUpdate.toString(), id.getAnnotation(Column.class).name(), id.get(o));
            Statement statement = conn.createStatement();
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Object o) {
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            String table = getTable(o);
            Field id = Arrays.stream(o.getClass().getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(Id.class))
                    .findFirst().orElseThrow();
            id.setAccessible(true);
            String sql = String.format("DELETE FROM %s WHERE %s = %s;",
                    table, id.getAnnotation(Column.class).name(), id.get(o));
            Statement statement = conn.createStatement();
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Class<E> o, Object id) {
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            String table = getTableFromClass(o);
            String idFieldName = getIdField(o);
            String sql = String.format("DELETE FROM %s WHERE %s = %s;",
                    table, idFieldName, id);
            Statement statement = conn.createStatement();
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<E> select (Class<E>o){
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            List<E> results = new ArrayList<>();
            String table = getTableFromClass(o);
            String[] fields = getFieldNames(o);
            Class<?>[] fieldTypes = getFieldTypes(o);
            Constructor<? extends E> constructor = o.getConstructor(fieldTypes);

            String sql = String.format("SELECT * FROM %s;", table);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Object[] object = new Object[fields.length];
                for (int i = 0; i < object.length; i++) {
                    object[i] = castObject(fieldTypes[i].getSimpleName(), resultSet.getString(fields[i]));
                }
                results.add(constructor.newInstance(object));
            }
            return results;
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public E selectById (Class<E>o, Object id){
        try (Connection conn = DBConnection.INSTANCE.getConnection()) {
            E result = null;
            String table = String.format("%s.%s", o.getAnnotation(Table.class).schema(),
                    o.getAnnotation(Table.class).name());
            String[] fields = getFieldNames(o);
            Class<?>[] fieldTypes = getFieldTypes(o);
            Constructor<? extends E> constructor = o.getConstructor(fieldTypes);
            String idFieldName = getIdField(o);

            String sql = String.format("SELECT * FROM %s WHERE %s = %s;", table, idFieldName, id);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Object[] object = new Object[fields.length];
                for (int i = 0; i < object.length; i++) {
                    object[i] = castObject(fieldTypes[i].getSimpleName(), resultSet.getString(fields[i]));
                }
                result = constructor.newInstance(object);
            }
            return result;
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private String getIdField(Class<E> o) {
        return  Arrays.stream(o.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .map(field -> field.getAnnotation(Column.class).name())
                .findFirst().orElseThrow();
    }

    private Class<?>[] getFieldTypes(Class<E> o) {
        return Arrays.stream(o.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(Field::getType).toArray(Class[]::new);
    }

    private String[] getFieldNames(Class<E> o) {
        return Arrays.stream(o.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotation(Column.class).name())
                .toArray(String[]::new);
    }

    private void getColumnAndValues(Object o, ArrayList<String> columns, ArrayList<String> values) {
        Arrays.stream(o.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class) && !f.isAnnotationPresent(Id.class))
                .forEach(f -> {
                    try {
                        columns.add(f.getAnnotation(Column.class).name());
                        f.setAccessible(true);
                        values.add(f.get(o).toString());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private String getTableFromClass(Class<E> o) {
        return String.format("%s.%s", o.getAnnotation(Table.class).schema(),
                o.getAnnotation(Table.class).name());
    }

    private String getTable(Object o) {
        return String.format("%s.%s", o.getClass().getAnnotation(Table.class).schema(),
                o.getClass().getAnnotation(Table.class).name());
    }

    private Object castObject(String typeName, String obj) {
        switch (typeName) {
            case "int":
            case "Integer":
                return Integer.valueOf(obj);
            case "double":
            case "Double":
                return Double.valueOf(obj);
            case "long":
            case "Long":
                return Long.valueOf(obj);
            case "String":
                return obj;
            default:
                try {
                    Class<?> aClass = Class.forName(typeName);
                    Constructor<?> constructor = aClass.getConstructor(String.class);
                    return constructor.newInstance(obj);
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                         InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
