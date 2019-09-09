package com.target.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BarrenLandAnalysis {

    private int MAX_X = 400;
    private int MAX_Y = 600;

    /**
     * Calculate fertile land areas with the barrenLand input list marking the areas of barren land.
     *
     * @param barrenLand delimited list of barren land rectangles
     * @return sorted list of fertile land areas
     */
    public String calculateFertileLandAreas(String barrenLand) {

        //The farmLand array when true represents a counted or ignored square meter
        boolean[][] farmLand = new boolean[MAX_X][MAX_Y];

        List<Integer> results = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            //the input string looks like JSON - but it is not quite well formed JSON. If we replace the curly braces with brackets, it becomes valid JSON.
            List<String> barrenLandParsed = mapper.readValue(barrenLand.replace('{', '[').replace('}', ']'),
                    new TypeReference<List<String>>() {});

            //Now mark all barrenLand as visited
            //Each input string is barren land rectangle, which consists of four integers separated by single spaces, with no additional spaces in the string.
            //The first two integers are the coordinates of the bottom left corner in the given rectangle, and the last two integers are the coordinates of the top right corner.
            List<List<Integer>> parsed = barrenLandParsed.stream().map(
                    land -> Arrays.stream(land.split("\\s")).map(Integer::valueOf).collect(Collectors.toList()))
                    .collect(Collectors.toList());

            parsed.forEach(land -> markBarrenLand(farmLand, land.get(0), land.get(1), land.get(2), land.get(3)));

            //Now calculate the area of continuous sections of land
            for (int y = 0; y < MAX_Y; y++) {
                for (int x = 0; x < MAX_X; x++) {
                    if (!farmLand[x][y]) {
                        //we have not visited this square - calculate the area.
                        results.add(caculateContinuousArea(farmLand, x, y));
                    }
                }
            }

            Collections.sort(results);
            return results.stream().map(String::valueOf).collect(Collectors.joining(" "));

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return "ERROR : Input value is malformed";
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            return "ERROR : Could not parse the input string into integer coordinates";
        }

    }

    private int caculateContinuousArea(boolean[][] farmLand, int x, int y) {
        if (x < 0 || y < 0 || x >= MAX_X || y >= MAX_Y) {
            //not a vaild coordinate
            return 0;
        }
        if (farmLand[x][y]) {
            //already visited - or this is barren
            return 0;
        }
        //mark this square meter as visited
        farmLand[x][y] = true;
        //now add all this square meter plus all the continuous land around recursively
        return 1 + caculateContinuousArea(farmLand, x - 1, y)
                + caculateContinuousArea(farmLand, x + 1, y)
                + caculateContinuousArea(farmLand, x, y - 1)
                + caculateContinuousArea(farmLand, x, y + 1);
    }

    //Mark the barren areas as visited, this prevents them as being included in the area calculation
    private void markBarrenLand(boolean[][] farmLand, int x1, int y1, int x2, int y2) {
        for (int y = y1; y <= y2 && y < MAX_Y; y++) {
            for (int x = x1; x <= x2 && x < MAX_X; x++) {
                farmLand[x][y] = true;
            }
        }
    }

}
