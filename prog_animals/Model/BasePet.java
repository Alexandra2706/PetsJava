package Model;

import java.time.LocalDate;
import java.util.List;

public abstract class BasePet {
    protected int id;
    protected String name;
    protected LocalDate birthday;
    protected List<String> commands;
    protected int petId;

    public BasePet(int id, String name, LocalDate birthday, List<String> commands, int petId) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.commands = commands;
        this.petId = petId;
    }

    public BasePet() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getPetId() {
        return petId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public List<String> getCommands() {
        return commands;
    }

    @Override
    public String toString() {
        return id + ": " + PetType.getPetType(petId) + ": " + name + ", Дата рождения: " + birthday
                + ", выполняет команды: " + commands;
    }

}
