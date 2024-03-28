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
    public void refreshDegrees(@RequestBody Map<String, Integer[]> sideOfTheWorld) {
        this.sideOfTheWorld.refresh(sideOfTheWorld);
    }

    /*
    Request body example
    {
        "North": [0, 10],
        "East": [11, 50],
        "South": [51, 100],
        "West": [101, 250],
        "Northwest": [251, 280],
        "Northeast": [281, 310],
        "Southwest": [311, 345],
        "Southeast": [346, 360]
    }
     */
    @GetMapping(value = "/compass/getSideByDegree", consumes = "application/json", produces = "application/json")
    public Map<String, String> getByDegree(@RequestBody Map<String, Integer> degree) {
        return sideOfTheWorld.getSideByDegree(degree.get("Degree"));
    }
}
