package View;

import java.util.Scanner;

public class Main {
    public static void printMenu(HardcodedExamples hrd) {
        System.out.println("Example 1: int v; v=2; Print(v)");
        System.out.println("Example 2: int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("Example 3: bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println();
        System.out.print("Enter example number which you want to run >> ");

        Scanner menu_scanner = new Scanner(System.in);
        Integer example_number;
        example_number = menu_scanner.nextInt();

        switch (example_number) {
            case 1:
                hrd.Example1();
                break;
            case 2:
                hrd.Example2();
                break;
            case 3:
                hrd.Example3();
                break;
        }

    }
    public static void main(String[] args) {
        HardcodedExamples hrd = new HardcodedExamples();
        printMenu(hrd);
    }
}
