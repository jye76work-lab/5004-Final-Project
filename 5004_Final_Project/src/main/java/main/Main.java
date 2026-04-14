package main;

import java.util.Scanner;
import repository.Repository;

public class Main {

    public static void main(String[] args) {

        Repository repo = new Repository();
        Scanner sc = new Scanner(System.in);

        System.out.println("====== Smart Version Control System ======");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Commit");
            System.out.println("2. Show History");
            System.out.println("3. Compare");
            System.out.println("4. Revert");
            System.out.println("5. Show Current");
            System.out.println("6. Demo Run");
            System.out.println("7. Exit");

            System.out.print("Input: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {

                System.out.print("Enter content: ");
                String content = sc.nextLine();

                System.out.print("Enter message: ");
                String msg = sc.nextLine();

                repo.commit(content, msg);

            } else if (choice.equals("2")) {

                repo.showHistory();

            } else if (choice.equals("3")) {

                System.out.print("Enter first commit id: ");
                String id1 = sc.nextLine();

                System.out.print("Enter second commit id: ");
                String id2 = sc.nextLine();

                repo.compare(id1, id2);

            } else if (choice.equals("4")) {

                System.out.print("Enter commit id to revert: ");
                String id = sc.nextLine();

                repo.revert(id);

            } else if (choice.equals("5")) {

                repo.showCurrent();

            } else if (choice.equals("6")) {

                repo.demoRun();

            } else if (choice.equals("7")) {

                System.out.println("Bye!");
                break;

            } else {

                System.out.println("Invalid input");
            }
        }

        sc.close();
    }
}