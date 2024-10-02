package Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;

import Model.BasePet;

public class Pets implements ManagePets {
    private List<BasePet> pets;

    public Pets() {
        this.pets = new ArrayList<>();
    }

    @Override
    public void addPet(BasePet pet) {
        pets.add(pet);
        System.out.println("Запись добавлена\n");
    }

    @Override
    public List<BasePet> getPetList() {
        pets.sort(Comparator.comparing(BasePet::getBirthday));
        return pets;
    }

    @Override
    public BasePet getPetById(int id) {
        for (BasePet item : pets) {
            if (item.getId() == id) {
                return item;
            }
        }
        System.out.println("Такого животного нет");
        return null;
    }

    public boolean checkPet(String name, LocalDate birthday, int idPet) {
        for (BasePet item : pets) {
            if (item.getName().equals(name) && item.getBirthday().equals(birthday)
                    && item.getPetId() == idPet) {
                System.out.println("Это животное уже внесено в список");
                return false;
            }
        }
        return true;
    }

    public void addCommands(BasePet pet, List<String> newCommands) {
        List<String> commands = new ArrayList<String>(pet.getCommands());
        boolean commandsEmpty = (commands == null) || commands.isEmpty();
        boolean newCommandsEmpty = (newCommands == null) || newCommands.isEmpty();
        if (commandsEmpty & newCommandsEmpty) {
            pet.setCommands(new ArrayList<String>());
        } else if (commandsEmpty) {
            pet.setCommands(newCommands);
        } else if (newCommandsEmpty) {
            pet.setCommands(commands);
        } else {
            commands.addAll(newCommands);
            Set<String> set = new LinkedHashSet<>(commands);
            commands.clear();
            commands.addAll(set);
            pet.setCommands(commands);
        }
        System.out.println("Команды добавлены");
    }

}
