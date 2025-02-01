import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nOnline Exam System");
            System.out.println("1) Add Candidate");
            System.out.println("2) Show Candidates");
            System.out.println("3) Delete Candidate");
            System.out.println("4) Update Candidate");
            System.out.println("5) Add Question");
            System.out.println("6) Show Questions");
            System.out.println("7) Delete Question");
            System.out.println("8) Update Question");
            System.out.println("9) Add Exam");
            System.out.println("10) Show Exams");
            System.out.println("11) Delete Exam");
            System.out.println("12) Update Exam");
            System.out.println("13) Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter candidate name: ");
                    String candidateName = scanner.nextLine();
                    System.out.print("Enter candidate age: ");
                    int candidateAge = scanner.nextInt();
                    Candidate candidate = new Candidate(candidateName, candidateAge);
                    candidate.saveToDatabase();
                    break;
                case 2:
                    Candidate.showCandidates();
                    break;
                case 3:
                    System.out.print("Enter candidate ID to delete: ");
                    int candidateDeleteId = scanner.nextInt();
                    Candidate.deleteCandidate(candidateDeleteId);
                    break;
                case 4:
                    System.out.print("Enter candidate ID to update: ");
                    int candidateUpdateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new candidate name: ");
                    String newCandidateName = scanner.nextLine();
                    System.out.print("Enter new candidate age: ");
                    int newCandidateAge = scanner.nextInt();
                    Candidate.updateCandidate(candidateUpdateId, newCandidateName, newCandidateAge);
                    break;
                case 5:
                    System.out.print("Enter question text: ");
                    String questionText = scanner.nextLine();
                    System.out.print("Enter 4 answer options separated by commas: ");
                    String[] options = scanner.nextLine().split(",");
                    System.out.print("Enter correct answer: ");
                    String correctAnswer = scanner.nextLine();
                    Question question = new Question(questionText, options, correctAnswer);
                    question.saveToDatabase();
                    break;
                case 6:
                    Question.showQuestions();
                    break;
                case 7:
                    System.out.print("Enter question ID to delete: ");
                    int questionDeleteId = scanner.nextInt();
                    Question.deleteQuestion(questionDeleteId);
                    break;
                case 8:
                    System.out.print("Enter question ID to update: ");
                    int questionUpdateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new question text: ");
                    String newQuestionText = scanner.nextLine();
                    System.out.print("Enter new 4 answer options separated by commas: ");
                    String[] newOptions = scanner.nextLine().split(",");
                    System.out.print("Enter new correct answer: ");
                    String newCorrectAnswer = scanner.nextLine();
                    Question.updateQuestion(questionUpdateId, newQuestionText, newOptions, newCorrectAnswer);
                    break;
                case 9:
                    System.out.print("Enter candidate ID for the exam: ");
                    int examCandidateId = scanner.nextInt();
                    System.out.print("Enter exam score: ");
                    int examScore = scanner.nextInt();
                    Exam exam = new Exam(examCandidateId, examScore);
                    exam.saveToDatabase();
                    break;
                case 10:
                    Exam.showExams();
                    break;
                case 11:
                    System.out.print("Enter exam ID to delete: ");
                    int examDeleteId = scanner.nextInt();
                    Exam.deleteExam(examDeleteId);
                    break;
                case 12:
                    System.out.print("Enter exam ID to update: ");
                    int examUpdateId = scanner.nextInt();
                    System.out.print("Enter new exam score: ");
                    int newExamScore = scanner.nextInt();
                    Exam.updateExam(examUpdateId, newExamScore);
                    break;
                case 13:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 13);

        scanner.close();
    }
}