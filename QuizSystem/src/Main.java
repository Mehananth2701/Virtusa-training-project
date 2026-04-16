import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        QuizManager qm = new QuizManager();

        while (true) {
            System.out.println("\n===== QUIZ SYSTEM =====");
            System.out.println("1. Add Question (Admin)");
            System.out.println("2. Take Quiz (User)");
            System.out.println("3. View Questions");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            String ch = sc.nextLine();

            switch (ch) {
                case "1":
                    qm.addQuestion(sc);
                    break;
                case "2":
                    qm.takeQuiz(sc);
                    break;
                case "3":
                    qm.showQuestions();
                    break;
                case "4":
                    System.out.println("Exit...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}