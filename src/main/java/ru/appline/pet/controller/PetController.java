package ru.appline.pet.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.pet.logic.Pet;
import ru.appline.pet.logic.PetModel;
import ru.appline.pet.logic.PetNotFoundException;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PetController {
    private static final PetModel petModel = PetModel.getInstance();
    private final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/pet/createPet", consumes = "application/json", produces = "application/json")
    public Map<String, String> createPet(@RequestBody Pet pet) {
        return petModel.add(pet, newId.getAndIncrement());
    }

    @DeleteMapping(value = "/pet/deletePet", consumes = "application/json", produces = "application/json")
    public Map<String, String> deletePet(@RequestBody Map<String, Integer> id) throws PetNotFoundException {
        return petModel.remove(id.get("id"));
    }

    @PutMapping(value = "/pet/changePet/{id}", consumes = "application/json", produces = "application/json")
    public Pet changePet(@PathVariable("id") int id, @RequestBody Pet pet) throws PetNotFoundException {
        return petModel.refreshPet(pet, id);
    }

    @GetMapping(value = "/pet/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/pet/getPet", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) throws PetNotFoundException {
        return petModel.getFromList(id.get("id"));
    }
}
