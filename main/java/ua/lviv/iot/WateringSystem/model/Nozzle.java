package ua.lviv.iot.WateringSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Nozzle extends Model {

    private Integer id;
    private double maxWaterConsumption;
    private Integer locationId;

    @Override
    public String getHeaders() {
        return "Id, Maximum water consumption, Location id";
    }

    @Override
    public String toCSV() {
        return this.getId() + ", " + this.getMaxWaterConsumption() + ", " + this.getLocationId();
    }
}
