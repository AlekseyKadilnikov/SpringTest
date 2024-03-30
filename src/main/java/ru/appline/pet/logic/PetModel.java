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

    public Map<String, String> add(Pet pet, int id) {
        model.put(id, pet);
        Map<String, String> result = new HashMap<>();
        result.put("result", "Питомец успешно создан");
        return result;
    }

    public Pet refreshPet(Pet pet, int id) throws PetNotFoundException {
        if(model.containsKey(id)) {
            Pet refreshedPet = model.get(id);
            refreshedPet.setAge(pet.getAge());
            refreshedPet.setType(pet.getType());
            refreshedPet.setName(pet.getName());
            return refreshedPet;
        } else {
            throw new PetNotFoundException();
        }
    }

    public Map<String, String> remove(int id) throws PetNotFoundException {
        Map<String, String> result = new HashMap<>();
        if(model.containsKey(id)) {
            model.remove(id);
            result.put("result", "Питомец успешно удален");
        } else {
            throw new PetNotFoundException();
        }
        return result;
    }

    public Pet getFromList(int id) throws PetNotFoundException {
        if(model.containsKey(id)) {
            return model.get(id);
        } else {
            throw new PetNotFoundException();
        }
    }

    public Map<Integer, Pet> getAll() {
        return model;
    }
}
