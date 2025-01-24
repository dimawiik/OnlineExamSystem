import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question {
    private int id;
    private String questionText;
    private String[] options;
    private String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void saveToDatabase() {
        String sql = "INSERT INTO questions (question_text, options, correct_answer) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, questionText);
            statement.setArray(2, connection.createArrayOf("TEXT", options));
            statement.setString(3, correctAnswer);
            statement.executeUpdate();
            System.out.println("Question saved: " + questionText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
