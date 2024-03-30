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
@NoArgsConstructor
public class SideOfTheWorld implements Serializable {

    Map<String, Integer[]> sidesOfTheWorld = new HashMap<>();

    public Map<String, String> getSideByDegree(int degree) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Integer[]> entry : sidesOfTheWorld.entrySet()) {
            Integer[] degrees = entry.getValue();
            if(degree > 360) {
                result.put("Side", "Вводимый градус не должен превышать 360");
                return result;
            }
            if(degree < 0) {
                result.put("Side", "Вводимый градус не должен быть меньше 0");
                return result;
            }
            if(degrees[0] > degrees[1]) {
                if (degree <= degrees[0] && degree <= degrees[1]) {
                    result.put("Side", entry.getKey());
                    return result;
                } else if(degree >= degrees[0] && degree > 0) {
                    result.put("Side", entry.getKey());
                    return result;
                }
            } else if (degree >= degrees[0] && degree <= degrees[1]) {
                    result.put("Side", entry.getKey());
                    return result;
            }
        }
        result.put("Side", "Сторона света не найдена");
        return result;
    }

    public Map<String, String> refresh(Map<String, String> sideOfTheWorldDraft) {
        Map<String, String> result = new HashMap<>();
        boolean error = false;
        for (Map.Entry<String, String> entry : sideOfTheWorldDraft.entrySet()) {
            String value = entry.getValue();
            String[] degreesString;
            Integer[] degrees = new Integer[2];
            try {
                degreesString = value.split("-");
                degrees[0] = Integer.parseInt(degreesString[0]);
                degrees[1] = Integer.parseInt(degreesString[1]);

                sidesOfTheWorld.put(entry.getKey(), degrees);
            } catch (Exception ex) {
                error = true;
            }
        }

        if(error) {
            result.put("error", "Неверный формат данных");
        } else {
            result.put("result", "Стороны света успешно обновлены");
        }
        return result;
    }
}
