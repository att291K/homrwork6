import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;
import sbp.jdbc.Jdbc;
import sbp.jdbc.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcTests {

    @Test
    public void JdbcTest_create(){

        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:./test");
        ds.setUser("sa");
        ds.setPassword("sa");

        try (Connection connection = ds.getConnection()) {

            Jdbc.createTable(connection);

            Person personA = Person.builder()
                    .withName("Петя")
                    .withCity("Тула")
                    .withAge(21)
                    .build();
            Person personB = Person.builder()
                    .withName("Женя")
                    .withCity("Москва")
                    .withAge(20)
                    .build();
            Person personC = Person.builder()
                    .withName("Ваня")
                    .withCity("Орёл")
                    .withAge(25)
                    .build();

            Person personD = Person.builder()
                    .withName("Серёжа")
                    .withCity("Рязань")
                    .withAge(22)
                    .build();

            boolean add = Jdbc.insertToTable(connection,personA);
            System.out.println(add ? "Добавлена запись" : "Запись уже существует") ;
            add = Jdbc.insertToTable(connection,personB);
            System.out.println(add ? "Добавлена запись" : "Запись уже существует") ;
            add = Jdbc.insertToTable(connection,personC);
            System.out.println(add ? "Добавлена запись" : "Запись уже существует") ;
            add = Jdbc.insertToTable(connection,personB);
            System.out.println(add ? "Добавлена запись" : "Запись уже существует") ;

            System.out.println("Записи в БД");
            List<Person> list = Jdbc.readFromTable(connection,0);
            for (Person p : list){
                System.out.println(p.toString());
            }
            System.out.println(" ");

            Jdbc.deleteFromTable(connection, personB);

            Jdbc.updateRecordInTable(connection,personC,personD);

            System.out.println("Записи изменены");
            list = Jdbc.readFromTable(connection, 0);
            for (Person p : list){
                System.out.println(p.toString());
            }

            connection.createStatement().execute("TRUNCATE TABLE persons");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
