import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Question {
    private int id;
    private String questionText;
    private String[] options;
    private String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void saveToDatabase() {
        String sql = "INSERT INTO questions (question_text, options, correct_answer) VALUES (?, ?, ?) RETURNING id";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, questionText);
            statement.setArray(2, connection.createArrayOf("TEXT", options));
            statement.setString(3, correctAnswer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
                System.out.println("Question added: " + questionText);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showQuestions() {
        String sql = "SELECT * FROM questions";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            System.out.println("List of Questions:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Question: " + resultSet.getString("question_text") +
                        ", Correct Answer: " + resultSet.getString("correct_answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Question deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateQuestion(int id, String newQuestionText, String[] newOptions, String newCorrectAnswer) {
        String sql = "UPDATE questions SET question_text = ?, options = ?, correct_answer = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newQuestionText);
            statement.setArray(2, connection.createArrayOf("TEXT", newOptions));
            statement.setString(3, newCorrectAnswer);
            statement.setInt(4, id);
            statement.executeUpdate();
            System.out.println("Question updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}