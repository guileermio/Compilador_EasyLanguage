import java.util.Scanner;

public class MainClass {
  public static void main(String args[]) {
    Scanner _key = new Scanner(System.in);

    double a;
    double b;

    a = _key.nextDouble();
    b = _key.nextDouble();

    a = 1 + 2 * 3 / b;

    while (a > b) {
      System.out.println(a);
      a = a - 1;
    }

    System.out.println("Fim do loop. Valor final de a: " + a);
  }
}
