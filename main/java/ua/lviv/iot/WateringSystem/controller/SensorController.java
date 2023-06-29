package ua.lviv.iot.WateringSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.WateringSystem.logic.SensorService;
import ua.lviv.iot.WateringSystem.model.Sensor;

import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;


    @PostMapping
    public Sensor addSensor(@RequestBody Sensor sensor) {
        return sensorService.addSensor(sensor);
    }

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.findAllSensors();
    }

    @GetMapping("/{id}")
    public Sensor getSensorById(@PathVariable Integer id) {
        return sensorService.findSensorById(id);
    }


    @PutMapping("/{id}")
    public Sensor updateSensor(@PathVariable Integer id, @RequestBody Sensor sensor) {
        return sensorService.updateSensor(id, sensor);
    }

    @DeleteMapping("/{id}")
    public Sensor deleteSensor(@PathVariable Integer id) {
        return sensorService.deleteSensor(id);
    }

}
