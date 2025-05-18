package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation {
    public static int validateIntBetween(Scanner sc, String message, int min, int max) {
        int value;

        while (true) {
            System.out.print(message);
            try{
                value = sc.nextInt();
                sc.nextLine();

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println("Introduza um número inteiro entre " + min + " e " + max);

            }catch (InputMismatchException e){
                System.out.println("Introduza um número inteiro entre " + min + " e " + max);
                sc.nextLine();
            }
        }
    }

    public static double validateDouble(Scanner sc, String message) {
        double value;

        while (true) {
            System.out.print(message);
            try{
                value = sc.nextDouble();
                sc.nextLine();
                return value;

            }catch (InputMismatchException e){
                System.out.print("Introduza um número: ");
                sc.nextLine();
            }
        }
    }

    public static int validateIntGT0(Scanner sc, String message) {
        int value;

        while (true) {
            System.out.print(message);
            try{
                value = sc.nextInt();
                sc.nextLine();

                if (value > 0) {
                    return value;
                }
                System.out.print("Introduza um número inteiro maior que 0: ");
            }catch (InputMismatchException e){
                System.out.print("Introduza um número inteiro maior que 0: ");
                sc.nextLine();
            }
        }
    }


    public static int validateInt(Scanner sc, String message) {
        int value;

        while (true) {
            System.out.print(message);
            try{
                value = sc.nextInt();
                sc.nextLine();

                return value;

            }catch (InputMismatchException e){
                System.out.print("Introduza um número inteiro maior que 0: ");
                sc.nextLine();
            }
        }
    }

    public static int validateIntGE0(Scanner sc, String message) {
        int value;
        while (true) {
            try{
                System.out.print(message);
                value = sc.nextInt();
                sc.nextLine();

                if (value >= 0) {
                    return value;
                }

                System.out.println("Introduza um número maior ou igual que 0");
            }catch (Exception e){
                System.out.println("Introduza um número maior ou igual que 0");
                sc.nextLine();
            }
        }
    }

    public static double validateDoubleBetween(Scanner sc, String message, double min, double max) {
        double value;

        while (true) {
            System.out.print(message);
            try{
                value = sc.nextInt();
                sc.nextLine();

                if (value > min || value < max) {
                    return value;
                }

                System.out.println("Introduza um número inteiro entre " + min + " e " + max);

            }catch (InputMismatchException e){
                System.out.println("Introduza um número inteiro entre " + min + " e " + max);
                sc.nextLine();
            }
        }
    }

    public static double validateDoubleGT0(Scanner sc, String message) {
        double value;
        while (true) {
            try{
                System.out.print(message);
                value = sc.nextDouble();
                sc.nextLine();

                if (value > 0) {
                    return value;
                }

                System.out.println("Introduza um número maior que 0");
            }catch (Exception e){
                System.out.println("Introduza um número maior que 0");
                sc.nextLine();
            }
        }
    }

    public static double validateDoubleGE0(Scanner sc, String message) {
        double value;
        while (true) {
            try{
                System.out.print(message);
                value = sc.nextDouble();
                sc.nextLine();

                if (value >= 0) {
                    return value;
                }

                System.out.println("Introduza um número maior que 0");
            }catch (Exception e){
                System.out.println("Introduza um número maior que 0");
                sc.nextLine();
            }
        }
    }

    public static LocalDate validateDate(Scanner sc, String message) {
        while (true) {
            try{
                System.out.print(message);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(sc.nextLine(), dateFormat);
            }catch (InputMismatchException e){
                System.out.println("Introduza uma data válida (dd/MM/yyyy): ");
            }
        }
    }

}
