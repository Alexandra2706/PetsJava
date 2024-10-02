package Service;

import java.util.List;

import Model.BasePet;

public interface ManagePets {
    void addPet(BasePet pet);

    BasePet getPetById(int id);

    List<BasePet> getPetList();
}
