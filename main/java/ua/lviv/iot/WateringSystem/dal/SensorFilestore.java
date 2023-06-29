package ua.lviv.iot.WateringSystem.dal;

import org.springframework.stereotype.Component;
import ua.lviv.iot.WateringSystem.model.Sensor;

import java.util.List;

@Component
public class SensorFilestore extends Filestore<Sensor> {
    @Override
    protected String getRecordName() {
        return "sensor";
    }

    @Override
    protected Sensor convert(List<String> values) {
        return new Sensor(Integer.parseInt(values.get(0)), Float.parseFloat(values.get(1)),
                Float.parseFloat(values.get(2)), Double.parseDouble(values.get(3)), Integer.parseInt(values.get(4)));
    }
}
