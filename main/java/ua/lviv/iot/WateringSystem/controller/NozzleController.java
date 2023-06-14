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
import ua.lviv.iot.WateringSystem.logic.NozzleService;
import ua.lviv.iot.WateringSystem.model.Nozzle;

import java.util.List;

@RestController
@RequestMapping("/nozzle")
public class NozzleController {
    @Autowired
    private NozzleService nozzleService;


    @PostMapping
    public Nozzle addNozzle(@RequestBody Nozzle nozzle) {
        return nozzleService.addNozzle(nozzle);
    }

    @GetMapping
    public List<Nozzle> getAllNozzles() {
        return nozzleService.findAllNozzles();
    }

    @GetMapping("/{id}")
    public Nozzle getNozzleById(@PathVariable Integer id) {
        return nozzleService.findNozzleById(id);
    }


    @PutMapping("/{id}")
    public Nozzle updateNozzle(@PathVariable Integer id, @RequestBody Nozzle nozzle) {
        return nozzleService.updateNozzle(id, nozzle);
    }

    @DeleteMapping("/{id}")
    public Nozzle deleteNozzle(@PathVariable Integer id) {
        return nozzleService.deleteNozzle(id);
    }

}

