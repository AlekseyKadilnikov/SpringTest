package ru.appline.pet.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {
    private static final PetModel instance = new PetModel();

    private final Map<Integer, Pet> model;

    public PetModel() {
        model = new HashMap<>();
    }

    public static PetModel getInstance() {
        return instance;
    }

    public void add(Pet pet, int id) {
        model.put(id, pet);
    }

    public void refreshPet(Pet pet, int id) throws Exception {
        if(model.containsKey(id)) {
            Pet refreshedPet = model.get(id);
            refreshedPet.setAge(pet.getAge());
            refreshedPet.setType(pet.getType());
            refreshedPet.setName(pet.getName());
        } else {
            throw new Exception();
        }
    }

    public void remove(int id) throws Exception {
        if(model.containsKey(id)) {
            model.remove(id);
        } else {
            throw new Exception();
        }
    }

    public Pet getFromList(int id) throws Exception {
        if(model.containsKey(id)) {
            return model.get(id);
        } else {
            throw new Exception();
        }
    }

    public Map<Integer, Pet> getAll() {
        return model;
    }
}
