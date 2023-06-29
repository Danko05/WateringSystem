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
public class Sensor extends Model {

    private Integer id;
    private float temperature;
    private float humidity;
    private double lightningInLuxes;
    private Integer locationId;

    @Override
    public String getHeaders() {
        return "Id, Temperature, Humidity, Lightning in luxes, Location id";
    }

    @Override
    public String toCSV() {
        return this.getId() + ", " + this.getTemperature() + ", " + this.getHumidity() + ", " + this.getLightningInLuxes() + ", " + this.getLocationId();
    }

}
