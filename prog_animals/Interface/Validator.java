package Interface;

import java.util.Scanner;

public class Validator {

    public int keyValidation(Scanner scanner, int minValue, int maxValue) {
        int numberValidate;
        while (!scanner.hasNextInt() || (numberValidate = scanner.nextInt()) > maxValue || numberValidate < minValue) {
            // scanner.nextLine();
            System.out.println("Введены неверные данные, попробуйте еще");
            scanner.nextLine();
        }
        return numberValidate;
    }

}
