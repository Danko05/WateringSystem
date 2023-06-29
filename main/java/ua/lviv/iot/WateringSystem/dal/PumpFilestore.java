package ua.lviv.iot.WateringSystem.dal;

import org.springframework.stereotype.Component;
import ua.lviv.iot.WateringSystem.model.Pump;

import java.util.List;

@Component
public class PumpFilestore extends Filestore<Pump> {
    @Override
    protected String getRecordName() {
        return "pump";
    }

    @Override
    protected Pump convert(List<String> values) {
        return new Pump(Integer.parseInt(values.get(0)), String.valueOf(values.get(1)), Integer.parseInt(values.get(2)));
    }
}
