import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Candidate {
    private int id;
    private String name;
    private int age;

    public Candidate(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Candidate(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Save Candidate to Database
    public void saveToDatabase() {
        String sql = "INSERT INTO candidates (name, age) VALUES (?, ?) RETURNING id";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
                System.out.println("Candidate saved: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get Candidate by ID
    public static Candidate getById(int candidateId) {
        String sql = "SELECT * FROM candidates WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, candidateId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Candidate(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
