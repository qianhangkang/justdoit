import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num_work=0, num_mates=0;
        if (scanner.hasNextInt()) {
            num_work = scanner.nextInt();
        }
        if (scanner.hasNextInt()) {
            num_mates = scanner.nextInt();
        }
        System.out.println(num_work);
        System.out.println(num_mates);
        scanner = new Scanner(System.in);
        for (int i = 0; i < num_work; i++) {

        }


    }

}
