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
public class Pump extends Model {
    private Integer id;
    private String name;
    private Integer locationId;


    @Override
    public String getHeaders() {
        return "Id, Name, Location id";
    }

    @Override
    public String toCSV() {
        return this.getId() + ", " + this.getName() + ", " + this.getLocationId();
    }
}
