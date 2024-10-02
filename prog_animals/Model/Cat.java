package Model;

import java.time.LocalDate;
import java.util.List;

public class Cat extends BasePet {

    public Cat(int id, String name, LocalDate birthday, List<String> commands, int petId) {
        super(id, name, birthday, commands, petId);
    }

    public Cat() {
    }

}
