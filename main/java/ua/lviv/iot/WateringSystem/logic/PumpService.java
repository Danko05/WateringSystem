package ua.lviv.iot.WateringSystem.logic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.WateringSystem.dal.PumpFilestore;
import ua.lviv.iot.WateringSystem.model.Pump;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PumpService {
    private Map<Integer, Pump> pumps = new HashMap();
    private Integer index = 0;

    @Autowired
    private PumpFilestore pumpFilestore;

    public List<Pump> findAllPumps() {
        return new ArrayList<>(pumps.values());
    }

    public Pump findPumpById(Integer id) {
        return pumps.get(id);
    }

    public Pump addPump(Pump pump) {
        index += 1;
        pump.setId(index);
        pumps.put(index, pump);
        return pump;
    }

    public Pump updatePump(Integer id, Pump pump) {
        pump.setId(id);
        pumps.put(id, pump);
        return pump;
    }

    public Pump deletePump(Integer id) {
        return pumps.remove(id);
    }

    @PreDestroy
    private void savePumpData() throws IOException {
        List<Pump> list = pumps.values().stream().toList();
        pumpFilestore.saveRecords(list);
    }

    @PostConstruct
    private void pumpDataToHashMap() throws IOException {
        if (pumpFilestore.readRecords() != null) {
            List<Pump> list = pumpFilestore.readRecords();
            for (Pump pump : list) {
                index += 1;
                if (pump.getId() > index) {
                    index = pump.getId();
                }
                pumps.put(pump.getId(), pump);
            }
        }
    }
}
