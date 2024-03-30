package ru.appline.compass.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.appline.compass.logic.SideOfTheWorld;

import java.util.Map;

@RestController
public class CompassController {
    SideOfTheWorld sideOfTheWorld = new SideOfTheWorld();

    @PostMapping(value = "/compass/refresh", consumes = "application/json")
    public Map<String, String> refreshDegrees(@RequestBody Map<String, String> sideOfTheWorldDraft) {
        return sideOfTheWorld.refresh(sideOfTheWorldDraft);
    }

    @GetMapping(value = "/compass/getSideByDegree", consumes = "application/json", produces = "application/json")
    public Map<String, String> getByDegree(@RequestBody Map<String, Integer> degree) {
        return sideOfTheWorld.getSideByDegree(degree.get("Degree"));
    }
}
