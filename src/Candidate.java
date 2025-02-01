import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Candidate {
    private int id;
    private String name;
    private int age;

    public Candidate(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void saveToDatabase() {
        String sql = "INSERT INTO candidates (name, age) VALUES (?, ?) RETURNING id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
                System.out.println("Candidate added: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showCandidates() {
        String sql = "SELECT * FROM candidates";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("List of Candidates:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Name: " + resultSet.getString("name") +
                        ", Age: " + resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCandidate(int id) {
        String sql = "DELETE FROM candidates WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Candidate deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCandidate(int id, String newName, int newAge) {
        String sql = "UPDATE candidates SET name = ?, age = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, newAge);
            statement.setInt(3, id);
            statement.executeUpdate();
            System.out.println("Candidate updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}