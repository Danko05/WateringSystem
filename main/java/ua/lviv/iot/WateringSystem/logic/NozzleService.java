package ua.lviv.iot.WateringSystem.logic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.WateringSystem.dal.NozzleFilestore;
import ua.lviv.iot.WateringSystem.model.Nozzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NozzleService {
    private Map<Integer, Nozzle> nozzles = new HashMap();
    private Integer index = 0;

    @Autowired
    private NozzleFilestore nozzleFilestore;

    public List<Nozzle> findAllNozzles() {
        return new ArrayList<>(nozzles.values());
    }

    public Nozzle findNozzleById(Integer id) {
        return nozzles.get(id);
    }

    public Nozzle addNozzle(Nozzle nozzle) {
        index += 1;
        nozzle.setId(index);
        nozzles.put(index, nozzle);
        return nozzle;
    }

    public Nozzle updateNozzle(Integer id, Nozzle nozzle) {
        nozzle.setId(id);
        nozzles.put(id, nozzle);
        return nozzle;
    }

    public Nozzle deleteNozzle(Integer id) {
        return nozzles.remove(id);
    }

    @PreDestroy
    private void saveNozzleData() throws IOException {
        List<Nozzle> list = nozzles.values().stream().toList();
        nozzleFilestore.saveRecords(list);
    }

    @PostConstruct
    private void nozzleDataToHashMap() throws IOException {
        if (nozzleFilestore.readRecords() != null) {
            List<Nozzle> list = nozzleFilestore.readRecords();
            for (Nozzle nozzle : list) {
                index += 1;
                if (nozzle.getId() > index) {
                    index = nozzle.getId();
                }
                nozzles.put(nozzle.getId(), nozzle);
            }
        }
    }
}
