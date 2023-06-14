package ua.lviv.iot.WateringSystem.dal;

import org.springframework.stereotype.Component;
import ua.lviv.iot.WateringSystem.model.Location;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LocationFilestore extends Filestore<Location> {
    @Override
    protected String getRecordName() {
        return "location";
    }

    @Override
    protected Location convert(List<String> values) {
        List<Integer> sensorIds = new LinkedList<>();
        if (!Objects.equals(values.get(2), "")) {
            sensorIds = Arrays.stream(values.get(2).split(", "))
                    .map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        }
        List<Integer> pumpsIds = new LinkedList<>();
        if (!Objects.equals(values.get(3), "")) {
            pumpsIds = Arrays.stream(values.get(3).split(", "))
                    .map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        }
        List<Integer> nozzleIds = new LinkedList<>();
        if (!Objects.equals(values.get(4), "")) {
            nozzleIds = Arrays.stream(values.get(4).split(", "))
                    .map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
        }
        return new Location(Integer.parseInt(values.get(0)), String.valueOf(values.get(1)), sensorIds, pumpsIds, nozzleIds);
    }
}
