package ua.lviv.iot.WateringSystem.logic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.WateringSystem.dal.LocationFilestore;
import ua.lviv.iot.WateringSystem.model.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationService {
    private Map<Integer, Location> locations = new HashMap();
    private Integer index = 0;

    @Autowired
    private LocationFilestore locationFilestore;

    public List<Location> findAllLocations() {
        return new ArrayList<>(locations.values());
    }

    public Location findLocationById(Integer id) {
        return locations.get(id);
    }

    public Location addLocation(Location location) {
        index += 1;
        location.setId(index);
        locations.put(index, location);
        return location;
    }

    public Location updateLocation(Integer id, Location location) {
        location.setId(id);
        locations.put(id, location);
        return location;
    }

    public Location deleteLocation(Integer id) {
        return locations.remove(id);
    }

    @PreDestroy
    private void saveLocationData() throws IOException {
        List<Location> list = locations.values().stream().toList();
        locationFilestore.saveRecords(list);
    }

    @PostConstruct
    private void locationDataToHashMap() throws IOException {
        if (locationFilestore.readRecords() != null) {
            List<Location> list = locationFilestore.readRecords();
            for (Location location : list) {
                index += 1;
                if (location.getId() > index) {
                    index = location.getId();
                }
                locations.put(location.getId(), location);
            }
        }
    }
}
