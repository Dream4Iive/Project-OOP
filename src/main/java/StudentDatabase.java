import java.sql.*;

public class StudentDatabase {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static void addStudent(String name, int age, String major) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO students (name, age, major) VALUES (?, ?, ?)")) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, major);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student added successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(String name, int age, String major, String newName, int newAge, String newMajor) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE students SET name=?, age=?, major=? WHERE name=? AND age=? AND major=?")) {

            statement.setString(1, newName);
            statement.setInt(2, newAge);
            statement.setString(3, newMajor);
            statement.setString(4, name);
            statement.setInt(5, age);
            statement.setString(6, major);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("No such student found with the specified name, age, and major.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void deleteStudent(String name, int age, String major) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE name=? AND age=? AND major=?")) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, major);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No such student found with the specified name, age, and major.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAllStudents() {
        StringBuilder result = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {

            result.append("List of students:\n");
            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id"))
                        .append(", Name: ").append(resultSet.getString("name"))
                        .append(", Age: ").append(resultSet.getInt("age"))
                        .append(", Major: ").append(resultSet.getString("major"))
                        .append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }





}