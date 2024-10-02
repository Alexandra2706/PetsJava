package Model;

public enum PetType {
    CAT("Кот/Кошка"),
    DOG("Собака"),
    HUMSTER("Хомяк");

    private String petTitle;

    PetType(String petTitle) {
        this.petTitle = petTitle;
    }

    public static String getPetType(int petId) {
        if (petId == 1)
            return PetType.CAT.petTitle;
        else if (petId == 2)
            return PetType.DOG.petTitle;
        else if (petId == 3)
            return PetType.HUMSTER.petTitle;
        else
            return null;
    }
}
