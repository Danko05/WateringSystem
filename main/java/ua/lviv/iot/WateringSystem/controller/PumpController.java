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
import ua.lviv.iot.WateringSystem.logic.PumpService;
import ua.lviv.iot.WateringSystem.model.Pump;

import java.util.List;

@RestController
@RequestMapping("/pump")
public class PumpController {
    @Autowired
    private PumpService pumpService;


    @PostMapping
    public Pump addPump(@RequestBody Pump pump) {
        return pumpService.addPump(pump);
    }

    @GetMapping
    public List<Pump> getAllPumps() {
        return pumpService.findAllPumps();
    }

    @GetMapping("/{id}")
    public Pump getPumpById(@PathVariable Integer id) {
        return pumpService.findPumpById(id);
    }


    @PutMapping("/{id}")
    public Pump updatePump(@PathVariable Integer id, @RequestBody Pump pump) {
        return pumpService.updatePump(id, pump);
    }

    @DeleteMapping("/{id}")
    public Pump deletePump(@PathVariable Integer id) {
        return pumpService.deletePump(id);
    }

}

