import java.util.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    // Constructor to initialize question, options, and correct answer index
    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Getter methods to retrieve question, options, and correct answer index
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input
        List<QuizQuestion> questions = new ArrayList<>(); // Create a list to hold QuizQuestion objects

        // Add quiz questions with options and correct answers
        questions.add(new QuizQuestion("Which team won the ODI world cup 2023?",
                Arrays.asList("A. India", "B. south africa", "C. New zealand", "D. Australia"), 3));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                Arrays.asList("A. Mars", "B. Jupiter", "C. Saturn", "D. Venus"), 0));
        // Add more questions...

        int totalQuestions = questions.size(); // Get the total number of questions in the list
        int score = 0; // Initialize the user's score

        System.out.println("Welcome to the Quiz!");
        System.out.println("You will have 10 seconds to answer each question.");

        for (int i = 0; i < totalQuestions; i++) {
            QuizQuestion currentQuestion = questions.get(i); // Get the current question from the list

            // Display the current question number and the question itself
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions(); // Get options for the current question
            for (String option : options) {
                System.out.println(option); // Display each option for the user to choose from
            }

            Timer timer = new Timer(); // Create a Timer object
            TimerTask task = new TimerTask() { // Define a TimerTask to execute when time's up
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    timer.cancel(); // Cancel the timer when time's up
                }
            };

            timer.schedule(task, 10000); // Schedule the timer to execute after 10 seconds
            System.out.print("Enter your answer (A, B, C, or D): ");
            String userAnswer = scanner.nextLine().toUpperCase(); // Read user's answer and convert to uppercase

            timer.cancel(); // Stop the timer when user submits the answer

            int correctAnswerIndex = currentQuestion.getCorrectAnswerIndex(); // Get the correct answer index
            if (userAnswer.equals(String.valueOf((char) ('A' + correctAnswerIndex)))) {
                System.out.println("Correct!"); // Display correct if the user's answer is correct
                score++; // Increment the score for correct answers
            } else {
                System.out.println("Incorrect! The correct answer is: " + options.get(correctAnswerIndex));
                // Display correct answer if the user's answer is incorrect
            }
        }

        // Display the quiz completion message along with the final score
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score is: " + score + "/" + totalQuestions);

        scanner.close(); // Close the Scanner object to release resources
    }
}
