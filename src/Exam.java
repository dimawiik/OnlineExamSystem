import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Exam {
    private int id;
    private int candidateId;
    private int score;

    public Exam(int candidateId, int score) {
        this.candidateId = candidateId;
        this.score = score;
    }

    public void saveToDatabase() {
        String sql = "INSERT INTO exams (candidate_id, score) VALUES (?, ?) RETURNING id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, candidateId);
            statement.setInt(2, score);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
                System.out.println("Exam saved for Candidate ID: " + candidateId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showExams() {
        String sql = "SELECT exams.id, candidates.name, exams.score FROM exams " +
                "JOIN candidates ON exams.candidate_id = candidates.id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("List of Exams:");
            while (resultSet.next()) {
                System.out.println("Exam ID: " + resultSet.getInt("id") +
                        ", Candidate: " + resultSet.getString("name") +
                        ", Score: " + resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteExam(int id) {
        String sql = "DELETE FROM exams WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Exam deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateExam(int id, int newScore) {
        String sql = "UPDATE exams SET score = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newScore);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Exam updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}