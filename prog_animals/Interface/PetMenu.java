package Interface;

import java.time.LocalDate;
import java.util.Scanner;

public class PetMenu {
    final int LIFESPANDOG = 20;
    final int LIFESPANCAT = 25;
    final int LIFESPANHUMSTER = 3;

    private Validator validator;

    public PetMenu() {
        validator = new Validator();
    }

    public int getMainMenu(Scanner scanner) {
        int minValue = 0;
        int maxValue = 6;
        System.out.println("Выберите действие:\n" +
                "1 - Добавить новое животное\n" +
                "2 - Вывести список команд, которые выполняет животное\n" +
                "3 - Обучить животное новым командам\n" +
                "4 - Вывести список всех животных по дате рождения\n" +
                "5 - Вывести общее количество животных\n" +
                "0 - Выход из программы");
        return validator.keyValidation(scanner, minValue, maxValue);
    }

    public int getPetTypeMenu(Scanner scanner) {
        int minValue = 1;
        int maxValue = 3;
        System.out.println("Выберите тип животного:\n" +
                "1 - Кот/кошка\n" +
                "2 - Собака\n" +
                "3 - Хомяк");
        return validator.keyValidation(scanner, minValue, maxValue);
    }

    public LocalDate getPetBirthdayMenu(Scanner scanner, int idPet) {
        int minYear;
        int minMonth = 1;
        int maxMonth;
        int minDay = 1;
        int maxDay = 28;

        LocalDate currentDate = LocalDate.now();

        int maxYear = currentDate.getYear();
        if (idPet == 1) {
            minYear = maxYear - LIFESPANCAT;
        } else if (idPet == 2) {
            minYear = maxYear - LIFESPANDOG;
        } else {
            minYear = maxYear - LIFESPANHUMSTER;
        }
        System.out.println("Год (между " + minYear + " и " + maxYear + "): ");
        int year = validator.keyValidation(scanner, minYear, maxYear);

        if (year == maxYear) {
            maxMonth = currentDate.getMonthValue();
        } else {
            maxMonth = 12;
        }
        System.out.println("Месяц (от " + minMonth + " до " + maxMonth + "): ");
        int month = validator.keyValidation(scanner, minMonth, maxMonth);

        if (year == maxYear && month == maxMonth) {
            maxDay = currentDate.getDayOfMonth();
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            maxDay = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30;
        } else if (month == 2 && year % 4 == 0) {
            maxDay = 29;
        }

        System.out.println("День (от " + minDay + " до " + maxDay + "):");

        int day = validator.keyValidation(scanner, minDay, maxDay);
        return LocalDate.of(year, month, day);
    }
}
