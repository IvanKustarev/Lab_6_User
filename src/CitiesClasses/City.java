package CitiesClasses;

import java.util.Date;

public class City {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer area; //Значение поля должно быть больше 0, Поле не может быть null
    private int population; //Значение поля должно быть больше 0
    private Long metersAboveSeaLevel;
    private java.util.Date establishmentDate;
    private Climate climate; //Поле может быть null
    private Government government; //Поле может быть null
    private Human governor; //Поле может быть null


    public City(String name, Coordinates coordinates, Date creationDate, Integer area, int population, Long metersAboveSeaLevel, Date establishmentDate, Climate climate, Government government, Human governor) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.establishmentDate = establishmentDate;
        this.climate = climate;
        this.government = government;
        this.governor = governor;
    }

    public void setId(int id) {
        this.id = id;
    }
}
