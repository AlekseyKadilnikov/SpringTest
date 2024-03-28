package ru.appline.pet.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.pet.logic.Pet;
import ru.appline.pet.logic.PetModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PetController {
    private static final PetModel petModel = PetModel.getInstance();
    private final AtomicInteger newId = new AtomicInteger(1);

    @PostMapping(value = "/pet/createPet", consumes = "application/json", produces = "application/json")
    public Map<String, String> createPet(@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @DeleteMapping(value = "/pet/deletePet", consumes = "application/json", produces = "application/json")
    public Map<String, String> deletePet(@RequestBody Map<String, Integer> id) throws Exception {
        petModel.remove(id.get("id"));
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @PutMapping(value = "/pet/changePet/{id}", consumes = "application/json", produces = "application/json")
    public Pet changePet(@PathVariable("id") int id, @RequestBody Pet pet) throws Exception {
        petModel.refreshPet(pet, id);
        return petModel.getFromList(id);
    }

    @GetMapping(value = "/pet/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/pet/getPet", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) throws Exception {
        return petModel.getFromList(id.get("id"));
    }
}
