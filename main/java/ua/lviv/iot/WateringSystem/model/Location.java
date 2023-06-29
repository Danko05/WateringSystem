package ua.lviv.iot.WateringSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Location extends Model {

    private Integer id;
    private String coordinates;
    private List<Integer> sensorIds;
    private List<Integer> pumpsIds;
    private List<Integer> nozzleIds;

    @Override
    public String getHeaders() {
        return "Id, Coordinates, Sensor ids, Pump ids, Nozzle ids";
    }

    @Override
    public String toCSV() {
        return this.getId() + ", " + this.getCoordinates() + ", " + this.getSensorIds() + ", " + this.getPumpsIds() + ", " + this.getNozzleIds();
    }

}
