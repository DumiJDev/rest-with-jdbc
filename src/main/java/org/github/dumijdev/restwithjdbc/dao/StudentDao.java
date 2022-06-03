package org.github.dumijdev.restwithjdbc.dao;

import org.github.dumijdev.restwithjdbc.model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class StudentDao {
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/rest_with_jdbc?serverTimezone=UTC";

    private static Connection createConnection() {
        try {
            var c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(true);
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Student save(Student student) {
        connection = createConnection();
        String sql = "INSERT INTO student(id, name, birth_date) values(?, ?, ?);";
        try {
            if (connection != null) {
                statement = connection.prepareStatement(sql);
                statement.setLong(1, student.getId());
                statement.setString(2, student.getName());
                statement.setString(3, student.getBirthDate().format(DateTimeFormatter.ISO_DATE));

                statement.execute();

                statement.close();
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return student;
    }

    public static List<Student> findAll(Page page) {
        connection = createConnection();
        int numberPage = page.getPage() == 0 ? 1 : page.getPage();
        int calcPage = (numberPage - 1) * page.getSize();
        List<Student> students;
        String sql = String.format("select * from student limit %d, %d;", calcPage, page.getSize());

        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            students = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = LocalDate.parse(resultSet.getString("birth_date"), DateTimeFormatter.ISO_DATE);

                var student = new Student(id, name, birthDate);

                students.add(student);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return students;
    }
}
