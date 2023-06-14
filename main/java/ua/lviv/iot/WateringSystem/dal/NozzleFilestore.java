package ua.lviv.iot.WateringSystem.dal;

import org.springframework.stereotype.Component;
import ua.lviv.iot.WateringSystem.model.Nozzle;

import java.util.List;

@Component
public class NozzleFilestore extends Filestore<Nozzle> {
    @Override
    protected String getRecordName() {
        return "nozzle";
    }

    @Override
    protected Nozzle convert(List<String> values) {
        return new Nozzle(Integer.parseInt(values.get(0)), Double.parseDouble(values.get(1)), Integer.parseInt(values.get(2)));
    }
}
