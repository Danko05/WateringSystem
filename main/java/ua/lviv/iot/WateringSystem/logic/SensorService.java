package ua.lviv.iot.WateringSystem.logic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.WateringSystem.dal.SensorFilestore;
import ua.lviv.iot.WateringSystem.model.Sensor;


import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SensorService {
    private Map<Integer, Sensor> sensors = new HashMap();

    private Integer index = 0;

    @Autowired
    private SensorFilestore sensorFilestore;

    public List<Sensor> findAllSensors() {
        return new ArrayList<>(sensors.values());
    }

    public Sensor findSensorById(Integer id) {
        return sensors.get(id);
    }

    public Sensor addSensor(Sensor sensor) {
        index += 1;
        sensor.setId(index);
        sensors.put(index, sensor);
        return sensor;
    }

    public Sensor updateSensor(Integer id, Sensor sensor) {
        sensor.setId(id);
        sensors.put(id, sensor);
        return sensor;
    }

    public Sensor deleteSensor(Integer id) {
        return sensors.remove(id);
    }

    @PreDestroy
    private void saveSensorData() throws IOException {
        List<Sensor> list = sensors.values().stream().toList();
        sensorFilestore.saveRecords(list);
    }

    @PostConstruct
    private void sensorDataToHashMap() throws IOException {
        if (sensorFilestore.readRecords() != null) {
            List<Sensor> list = sensorFilestore.readRecords();
            for (Sensor sensor : list) {
                index += 1;
                if (sensor.getId() > index) {
                    index = sensor.getId();
                }
                sensors.put(sensor.getId(), sensor);
            }
        }
    }
}
