package sbp.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {

    //реализовать основные CRUD (create - update - delete) операции для этой таблицы
    public static void createTable(Connection connection) throws SQLException {
        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS persons (id long primary key auto_increment, name varchar(255), city varchar(255), age int)");
    }

    public static boolean insertToTable(Connection connection, Person person) throws SQLException {

        ResultSet rs = connection.createStatement().executeQuery("SELECT name, city FROM persons WHERE name = '"+person.getName()+"' AND city='"+person.getCity()+"'");
        rs.last();
        int sizeRs = rs.getRow();
        if ( sizeRs > 0) return false;

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO persons (name, city, age) VALUES ( ?, ?, ? )");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getCity());
        preparedStatement.setInt(3, person.getAge());
        preparedStatement.execute();
        return true;
    }

    public static List<Person> readFromTable(Connection connection, int firstRow) throws SQLException {

        String sql = "SELECT * FROM persons" +(firstRow==0 ? "" : " FETCH FIRST "+firstRow+" ROWS ONLY ");
        ResultSet rs= connection.createStatement().executeQuery(sql);
        List<Person> list = new ArrayList<>();
        while (rs.next()) {
            long  id = rs.getLong("id");
            String name = rs.getString("name");
            String city = rs.getString("city");
            int age = rs.getInt("age");
            Person person = Person.builder()
                    .withId(id)
                    .withName(name)
                    .withCity(city)
                    .withAge(age)
                    .build();
            list.add(person);
        }
        return list;
    }

    public static void deleteFromTable(Connection connection, Person person) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM persons WHERE name = ? AND city = ? ");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getCity());
        preparedStatement.execute();
    }

    public static void updateRecordInTable(Connection connection, Person personForUpdate, Person personNew) throws SQLException {

        PreparedStatement preparedStatementForNew = connection.prepareStatement("SELECT name, city FROM persons WHERE name = ? AND city=?");
        preparedStatementForNew.setString(1, personNew.getName());
        preparedStatementForNew.setString(2, personNew.getCity());
        ResultSet rs = preparedStatementForNew.executeQuery();
        rs.last();
        if ( rs.getRow() > 0) return;

        PreparedStatement preparedStatementForOld = connection.prepareStatement("SELECT name, city FROM persons WHERE name = ? AND city=?");
        preparedStatementForOld.setString(1, personForUpdate.getName());
        preparedStatementForOld.setString(2, personForUpdate.getCity());
        ResultSet rsForOld = preparedStatementForOld.executeQuery();
        rsForOld.last();
        if ( rsForOld.getRow() == 0) return;

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE persons SET name = ?, city = ?, age = ? WHERE name = ? AND city=? ");
        preparedStatement.setString(1, personNew.getName());
        preparedStatement.setString(2, personNew.getCity());
        preparedStatement.setInt(3, personNew.getAge());
        preparedStatement.setString(4, personForUpdate.getName());
        preparedStatement.setString(5, personForUpdate.getCity());
        preparedStatement.execute();
    }

}
