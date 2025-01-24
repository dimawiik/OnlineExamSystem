import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exam {
    private int id;
    private Candidate candidate;
    private Question[] questions;
    private int score;

    public Exam(Candidate candidate, Question[] questions) {
        this.candidate = candidate;
        this.questions = questions;
        this.score = 0;
    }

    public void takeExam(String[] answers) {
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].getCorrectAnswer().equals(answers[i])) {
                score++;
            }
        }
    }

    public void saveToDatabase() {
        String sql = "INSERT INTO exams (candidate_id, score) VALUES (?, ?) RETURNING id";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, candidate.getId());
            statement.setInt(2, score);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
                System.out.println("Exam saved for candidate: " + candidate.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
