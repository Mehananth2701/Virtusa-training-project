import java.io.*;
import java.util.*;

public class QuizManager {

    private List<Question> questions = new ArrayList<>();
    private final String fileName = "questions.txt";

    public QuizManager() {
        loadQuestionsFromFile();
    }

    public void addQuestion(Scanner scanner) {
        System.out.println("\n===== Add Question =====");

        System.out.print("Enter question: ");
        String q = scanner.nextLine();

        System.out.print("Option A: ");
        String a = scanner.nextLine();

        System.out.print("Option B: ");
        String b = scanner.nextLine();

        System.out.print("Option C: ");
        String c = scanner.nextLine();

        System.out.print("Option D: ");
        String d = scanner.nextLine();

        System.out.print("Correct Answer (A/B/C/D): ");
        char ans = scanner.nextLine().toUpperCase().charAt(0);

        Question question = new Question(q, a, b, c, d, ans);
        questions.add(question);
        saveQuestion(question);

        System.out.println("✅ Question saved!");
    }

    public void takeQuiz(Scanner scanner) {
        if (questions.isEmpty()) {
            System.out.println("⚠ No questions available!");
            return;
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        int score = 0;
        int correct = 0;
        int wrong = 0;

        long startTime = System.currentTimeMillis();
        int totalTime = questions.size() * 20;

        for (int i = 0; i < questions.size(); i++) {

            long elapsed = (System.currentTimeMillis() - startTime) / 1000;
            if (elapsed >= totalTime) {
                System.out.println("⏰ Time Over!");
                break;
            }

            Question q = questions.get(i);

            System.out.println("\nQ" + (i + 1) + ": " + q.getQuestionText());
            System.out.println("A. " + q.getOptionA());
            System.out.println("B. " + q.getOptionB());
            System.out.println("C. " + q.getOptionC());
            System.out.println("D. " + q.getOptionD());

            System.out.print("Answer: ");
            char userAns = scanner.nextLine().toUpperCase().charAt(0);

            if (userAns == q.getCorrectAnswer()) {
                score++;
                correct++;
            } else {
                wrong++;
            }
        }

        double percentage = (correct + wrong == 0) ? 0 : (score * 100.0 / (correct + wrong));

        System.out.println("\n===== RESULT =====");
        System.out.println("Name       : " + name);
        System.out.println("Score      : " + score);
        System.out.println("Correct    : " + correct);
        System.out.println("Wrong      : " + wrong);
        System.out.println("Percentage : " + String.format("%.2f", percentage) + "%");

        if (percentage >= 80)
            System.out.println("Performance: Excellent");
        else if (percentage >= 60)
            System.out.println("Performance: Good");
        else if (percentage >= 40)
            System.out.println("Performance: Average");
        else
            System.out.println("Performance: Improve!");
    }

    private void saveQuestion(Question q) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(q.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving question.");
        }
    }

    private void loadQuestionsFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");

                if (p.length == 6) {
                    questions.add(new Question(
                            p[0], p[1], p[2], p[3], p[4], p[5].charAt(0)
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading questions.");
        }
    }

    public void showQuestions() {
        if (questions.isEmpty()) {
            System.out.println("No questions found.");
            return;
        }

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ": " + q.getQuestionText());
            System.out.println("Correct Answer: " + q.getCorrectAnswer());
        }
    }
}