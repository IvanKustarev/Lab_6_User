package CitiesClasses;

import WorkWithConsole.Scan;

import java.util.Date;

public class CitiesCreator {

    private static Scan scan;

    public static City createCity(Scan consoleWorker){
        String name = createName();
        Coordinates coordinates = createCoordinates();
        Date creationDate = createCreationDate();
        Integer areas = createAreas();
        int population = createPopulation();
        Long metersAboveSeaLevel = createMetersAboveSeaLevel();
        Date establishmentDate = createEstablishmentDate();
        Climate climate = createClimate();
        Government government = createGovernment();
        Human governor = createGovernor();

        City city = new City(name, coordinates, creationDate, areas, population, metersAboveSeaLevel, establishmentDate, climate, government, governor);
        return city;
    }

    private static String createName(){
        scan.write("Введите название города:");
        String name = scan.read();
        if(name.trim().equals("")){
            scan.write("У города обязательно должно быть название");
            return createName();
        }
        return name.trim();
    }

    private static Coordinates createCoordinates(){
        int x;
        float y;
        scan.write("Введите координату города по X");
        try {
            x = Integer.valueOf(scan.read());
        }catch (Exception e){
            scan.write("Координата X должна быть целым числом");
            return createCoordinates();
        }
        scan.write("Введите координату города по Y");
        try {
            y = Float.valueOf(scan.read());
        }catch (Exception e){
            scan.write("Координата Y должна быть целым или дробным числом");
            return createCoordinates();
        }
        Coordinates coordinates = new Coordinates(x, y);
        return coordinates;
    }

    private static Date createCreationDate(){
        return new Date();
    }

    private static Integer createAreas(){
        scan.write("Задайте область целым числом");
        int area;
        try {
            area = Integer.valueOf(scan.read());
        }catch (Exception e){
            scan.write("Область должна быть задана целым числом!");
            return createAreas();
        }
        return area;
    }

    private static int createPopulation(){
        scan.write("Задайте население целым числом");
        int population;
        try {
            population = Integer.valueOf(scan.read());
        }catch (Exception e){
            scan.write("Население должно быть задано целым числом!");
            return createPopulation();
        }
        return population;
    }

    private static Long createMetersAboveSeaLevel(){
        scan.write("Задайте количество метров над уровнем моря целым числом");
        long metersAboveSeaLevel;
        try {
            metersAboveSeaLevel = Long.valueOf(scan.read());
        }catch (Exception e){
            scan.write("Количество метров над уровнем моря должно быть задано целым числом!");
            return createMetersAboveSeaLevel();
        }
        return metersAboveSeaLevel;
    }

    private static Date createEstablishmentDate(){
        scan.write("Задайте дату основания города в формате yyyy:mm:dd");
        String birthdate = scan.read();
        try {
            String[] params = birthdate.trim().split(":");
            return new Date(Integer.valueOf(params[0]) - 1900, Integer.valueOf(params[1]), Integer.valueOf(params[2]));
        }catch (Exception e){
            scan.write("Дата основания задана некорректно!");
            return createEstablishmentDate();
        }
    }

    private static Climate createClimate(){
        scan.write("Задайте климат одной из следующих констант:");
        for (Climate climate : Climate.values()){
            scan.write(climate.name());
        }
        Climate climate;
        String str = scan.read();
        if(str.trim().equals("")){
            scan.write("Значение поля остаётся пустым!");
            return null;
        }
        try {
            climate = Climate.valueOf(str);
        }catch (Exception e){
            scan.write("Неизвестное значение!");
            return createClimate();
        }
        return climate;
    }

    private static Government createGovernment(){
        scan.write("Задайте власть одной из следующих констант:");
        for (Government government : Government.values()){
            scan.write(government.name());
        }
        Government government;
        String str = scan.read();
        if(str.trim().equals("")){
            scan.write("Значение поля остаётся пустым!");
            return null;
        }
        try {
            government = Government.valueOf(str);
        }catch (Exception e){
            scan.write("Неизвестное значение!");
            return createGovernment();
        }
        return government;
    }

    private static Human createGovernor(){
        scan.write("Задайте имя губернаотра");
        String name = scan.read();
        if(name.trim().equals("")){
            scan.write("У города нет губернатора!");
            return null;
        }
        scan.write("Задайте дату рождения в формате yyyy:mm:dd");
        String birthdate = scan.read();
        if(birthdate.trim().equals("")){
            scan.write("У города нет губернатора!");
            return null;
        }
        Date date;
        try {
            String[] params = birthdate.trim().split(":");
            date = new Date(Integer.valueOf(params[0]) - 1900, Integer.valueOf(params[2]), Integer.valueOf(params[2]));
        }catch (Exception e){
            scan.write("Не корректно задана дата рождения!");
            return createGovernor();
        }
        return new Human(name, date);
    }
}
