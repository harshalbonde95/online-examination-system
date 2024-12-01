import java.sql.*;

import java.util.Scanner;

public class EvaluateExam {

private static final String URL = "jdbc:mysql://localhost:3306/OnlineExam";

private static final String USER = "root";

private static final String PASSWORD = "password";

public static void main(String[] args) {

try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

Scanner scanner = new Scanner(System.in);

System.out.println("Enter User ID:");

int userld - scanner.nextInt();
String query = "SELECT * FROM questions";

PreparedStatement stmt = conn.prepareStatement(query);

ResultSet rs = stmt.executeQuery();

int score = 0;

while (rs.next()) {

System.out.println(rs.getString("question"));

System.out.println("A: " + rs.getString("option_a"));

System.out.println("B: " + rs.getString("option_b"));

System.out.println("C: " + rs.getString("option_c"));

System.out.println("D: "+ rs.getString("option_d"));

System.out.print("Your Answer: ");

char answer = scanner.next().toUpperCase().charAt(0);

if (answer-rs.getString("correct option").charAt(0)) {

score++;
  }
}

String resultQuery = "INSERT INTO results (user_id, score) VALUES (?, ?)";

PreparedStatement resultStmt = conn.prepareStatement(resultQuery);

resultStmt.setInt(1, userId);

resultStmt.setInt(2, score);

resultStmt.executeUpdate();System.out.println("Exam completed! Your score: " + score);

} catch (Exception e) {

e.printStackTrace();

}
 }
}