package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

import Interface.PetMenu;
import Interface.Validator;
import Model.BasePet;
import Model.Cat;
import Model.Dog;
import Model.Hamster;
import Service.Counter;
import Service.Pets;
import View.ListView;
import View.PetView;
import View.Message;

public class Controller {

    private static Scanner scanner = new Scanner(System.in, "Cp866");

    private Pets pets;
    private ListView listView;
    private PetView petView;
    private PetMenu petMenu;
    private Message message;
    private Validator validator;

    public Controller() {
        pets = new Pets();
        listView = new ListView();
        petView = new PetView();
        petMenu = new PetMenu();
        message = new Message();
        validator = new Validator();
    }

    public void run() {

        boolean end = false;
        do {
            int menuPoint = petMenu.getMainMenu(scanner);
            switch (menuPoint) {
                case 1 -> addNewPet();
                case 2 -> getPetCommands();
                case 3 -> teachNewCommands();
                case 4 -> listView.printList(pets.getPetList()); // тут надо по дате рождения
                case 5 -> message.showMessage("Общее количество животных: " + getNumberPets());
                case 0 -> end = true;
            }
        } while (!end);

    }

    public void addNewPet() {
        System.out.println("----------Добавть новое животное----------");
        scanner.nextLine();
        int idPet = petMenu.getPetTypeMenu(scanner);
        scanner.nextLine();

        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.println("Введите дату рождения животного:");
        LocalDate birthday = petMenu.getPetBirthdayMenu(scanner, idPet);

        System.out.println("Введите через запятую команды, которые выполняет животное:");
        scanner.nextLine();
        List<String> commands = getList(scanner);
        try (Counter counter = new Counter();) {
            BasePet currentPet;
            if (name != "" && pets.checkPet(name, birthday, idPet)) {
                counter.add();
                int currentId = counter.getSumPets();
                if (idPet == 1) {
                    currentPet = new Cat(currentId, name, birthday, commands, idPet);
                } else if (idPet == 2) {
                    currentPet = new Dog(currentId, name, birthday, commands, idPet);
                } else {
                    currentPet = new Hamster(currentId, name, birthday, commands, idPet);
                }

                pets.addPet(currentPet);
            }

        } catch (RuntimeException e) {
            message.showMessage(e.getMessage());
        }
    }

    public int getNumberPets() {
        try (Counter counter = new Counter();) {
            return counter.getSumPets();
        } catch (RuntimeException e) {
            message.showMessage(e.getMessage());
        }
        return 0;
    }

    public int getPetId() {
        int numberPets = getNumberPets();
        if (numberPets == 0) {
            return 0;
        }
        System.out.println("Введите id животного от 1 до " + numberPets);
        return validator.keyValidation(scanner, 1, numberPets);
    }

    public int checkListPetAndId() {
        if (pets.getPetList() == null || pets.getPetList().isEmpty()) {
            System.out.println("Список пуст животных пуст");
            return 0;
        }
        int id = getPetId();
        if (id == 0 || pets.getPetById(id) == null) {
            System.out.println("Нет такого животного");
            return 0;
        }
        return id;
    }

    public List<String> getList(Scanner scanner) {
        String commandsString = scanner.nextLine();
        commandsString = commandsString.replaceAll("[,+|\\s+]*$", "");
        System.out.println("cS:" + commandsString);
        if (commandsString.length() == 0) {
            return new ArrayList<String>();
        }
        List<String> commands = new ArrayList<>(Arrays.asList(commandsString.split(",")));
        Set<String> set = new LinkedHashSet<>(commands);
        commands.clear();
        commands.addAll(set);
        System.out.println("commands:" + commands);
        return commands;
    }

    public void getPetCommands() {
        int id = checkListPetAndId();
        if (id != 0) {
            listView.printCommands(pets.getPetById(id).getCommands());
        }
    }

    public void teachNewCommands() {
        int id = checkListPetAndId();
        if (id != 0) {
            petView.printPet(pets.getPetById(id));
            System.out.println("Введите новые команды через запятую: ");
            scanner.nextLine();
            List<String> newCommands = getList(scanner);
            pets.addCommands(pets.getPetById(id), newCommands);
        }
    }

}