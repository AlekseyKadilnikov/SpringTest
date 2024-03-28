package ru.appline.compass.logic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class SideOfTheWorld implements Serializable {

    Map<String, Integer[]> sidesOfTheWorld;

    public SideOfTheWorld() {
        sidesOfTheWorld = new HashMap<String, Integer[]>() {{
            put("North", new Integer[]{0, 0});
            put("East", new Integer[]{0, 0});
            put("West", new Integer[]{0, 0});
            put("South", new Integer[]{0, 0});
            put("Northeast", new Integer[]{0, 0});
            put("Southeast", new Integer[]{0, 0});
            put("Northwest", new Integer[]{0, 0});
            put("Southwest", new Integer[]{0, 0});
        }};
    }

    public Map<String, String> getSideByDegree(int degree) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Integer[]> entry : sidesOfTheWorld.entrySet()) {
            Integer[] degrees = entry.getValue();
            if (degree >= degrees[0] && degree <= degrees[1]) {
                result.put("Side", entry.getKey());
                return result;
            }
        }
        result.put("Side", "Side not found");
        return result;
    }

    public void refresh(Map<String, Integer[]> sideOfTheWorld) {
        this.sidesOfTheWorld = sideOfTheWorld;
    }
}
