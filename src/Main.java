public class Main {
    public static void main(String[] args) {
        Candidate candidate = new Candidate("Dimaw", 18);
        candidate.saveToDatabase();

        Question[] questions = {
                new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, "Paris"),
                new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, "4")
        };

        for (Question question : questions) {
            question.saveToDatabase();
        }

        Exam exam = new Exam(candidate, questions);
        exam.takeExam(new String[]{"Paris", "4"});
        exam.saveToDatabase();
    }
}
