package Service;

public class Counter implements AutoCloseable {

    private static Integer sumPets = 0;

    public void add() {
        sumPets++;
    }

    public Integer getSumPets() {
        return sumPets;
    }

    @Override
    public void close() {
        System.out.println("Счетчик закрыт\n");
    }
}
