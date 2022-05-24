package app;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Locale;


/**
 * Třída, která uchovává let
 * @author Tomáš Kulhavý
 */
public class Flight implements Comparator<Flight> {
    private Plane plane;
    private String takeoff;
    private String landing;
    private LocalDate date;
    private LocalDateTime takeoffTime;
    private LocalDateTime landingTime;
    private int flightTimeMinutes;
    private int takeoffNo;
    private String typeOfFlight;
    private String typeOfTakeOff;
    private Pilot pilot;
    private FlightDiary flightDiary;

    public Flight() {
    }

    /**
     * Konstruktor pro větroně
     * @param plane Letadlo
     * @param takeoff Letiště odletu
     * @param landing Letiště příletu
     * @param date Datum letu
     * @param takeoffTime Čas odletu
     * @param landingTime Čas příletu
     * @param flightTimeMinutes Čas letu v minutách
     * @param takeoffNo Počet vzletů
     * @param typeOfTakeOff Způsob vzletu
     * @param typeOfFlight Typ letu
     * @param pilot Pilot letadla
     * @param flightDiary Letový zápisník
     * @param exist existuje už?
     * @throws java.io.IOException
     */
    public Flight(Plane plane, String takeoff, String landing, LocalDate date, LocalDateTime takeoffTime, LocalDateTime landingTime, int flightTimeMinutes, int takeoffNo, String typeOfFlight, String typeOfTakeOff, Pilot pilot, FlightDiary flightDiary, boolean exist) {
        this.plane = plane;
        this.takeoff = takeoff;
        this.landing = landing;
        this.date = date;
        this.takeoffTime = takeoffTime;
        this.landingTime = landingTime;
        this.flightTimeMinutes = flightTimeMinutes;
        this.takeoffNo = takeoffNo;
        this.typeOfFlight = typeOfFlight;
        this.typeOfTakeOff = typeOfTakeOff;
        this.pilot = pilot;
        this.flightDiary = flightDiary;
        if(!exist) saveNewFlight();
    }

    /**
     * Konstruktor pro motorové letadlo
     * @param plane Letadlo
     * @param takeoff Letiště odletu
     * @param landing Letiště příletu
     * @param date Datum letu
     * @param takeoffTime Čas odletu
     * @param landingTime Čas příletu
     * @param flightTimeMinutes Čas letu v minutách
     * @param takeoffNo Počet vzletů
     * @param typeOfFlight Typ letu
     * @param pilot Pilot letadla
     * @param flightDiary Letový zápisník
     * @param exist existuje už?
     * @throws java.io.IOException
     */
    public Flight(Plane plane, String takeoff, String landing, LocalDate date, LocalDateTime takeoffTime, LocalDateTime landingTime, int flightTimeMinutes, int takeoffNo, String typeOfFlight, Pilot pilot, FlightDiary flightDiary, boolean exist) {
        this.plane = plane;
        this.takeoff = takeoff;
        this.landing = landing;
        this.date = date;
        this.takeoffTime = takeoffTime;
        this.landingTime = landingTime;
        this.flightTimeMinutes = flightTimeMinutes;
        this.takeoffNo = takeoffNo;
        this.typeOfFlight = typeOfFlight;
        this.pilot = pilot;
        this.flightDiary = flightDiary;
        if(!exist) saveNewFlight();
    }

    /**
     * Ukládá nové lety do textového soubory letadla a zápisníku pilota
     * @throws java.io.IOException
     */
    public void saveNewFlight() {
        try {
            FileWriter myWriter = new FileWriter(pilot.getName().toLowerCase(Locale.ROOT) + "." + plane.getTypeOfLicence(), true);
            myWriter.write("\n" + plane.getName() +
                    ", " + plane.getRegistration() +
                    ", " + takeoff +
                    ", " + landing +
                    ", " + date +
                    ", " + takeoffTime.getHour() + ":" + takeoffTime.getMinute() +
                    ", " + landingTime.getHour() + ":" + landingTime.getMinute() +
                    ", " + flightTimeMinutes +
                    ", " + takeoffNo +
                    ", " + typeOfFlight +
                    ", " + pilot.getName() +
                    ", " + plane.getTypeOfLicence());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriterPlane = new FileWriter(plane.getRegistration() + ".plane", true);
            myWriterPlane.write("\n" +
                    takeoff +
                    ", " + landing +
                    ", " + date +
                    ", " + takeoffTime.getHour() + ":" + takeoffTime.getMinute() +
                    ", " + landingTime.getHour() + ":" + landingTime.getMinute() +
                    ", " + flightTimeMinutes +
                    ", " + takeoffNo +
                    ", " + typeOfFlight +
                    ", " + pilot.getName());
            myWriterPlane.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        flightDiary.setOverall(getTakeoffNo(), getFlightTimeMinutes(), plane.getTypeOfLicence());
        plane.setFlightTimeMinutes(getFlightTimeMinutes());
        plane.setTakeoffNo(getTakeoffNo());
        plane.setOverallPlane();
    }

    /**
     * Vrátí letiště odletu
     * @return Letiště odletu
     */
    public String getTakeoff() {
        return takeoff;
    }

    /**
     * Vrátí letiště příletu
     * @return Letiště příletu
     */
    public String getLanding() {
        return landing;
    }

    /**
     * Vrátí datum letu
     * @return Datum letu
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Vrátí čas odletu
     * @return Čas odletu
     */
    public LocalDateTime getTakeoffTime() {
        return takeoffTime;
    }

    /**
     * Vrátí čas příletu
     * @return Čas příletu
     */
    public LocalDateTime getLandingTime() {
        return landingTime;
    }

    /**
     * Vrátí způsob vzletu
     * @return Způsob vzletu
     */
    public String getTypeOfTakeOff() { return typeOfTakeOff; }

    /**
     * Vrátí čas letu v minutách
     * @return Čas letu v minutách
     */
    public int getFlightTimeMinutes() {
        return flightTimeMinutes;
    }

    /**
     * Vrátí počet vzletů
     * @return Počet vzletů
     */
    public int getTakeoffNo() {
        return takeoffNo;
    }

    /**
     * Vrátí typ letu
     * @return Typ letu
     */
    public String getTypeOfFlight() {
        return typeOfFlight;
    }

    /**
     * Vrátí pilota letadla
     * @return Pilot letadla
     */
    public String getPilot() {
        return pilot.getName();
    }

    /**
     * Vrátí letadlo
     * @return Letadlo
     */
    public Plane getPlane() {
        return plane;
    }

    /**
     * Seřadí lety podle datumu
     * @params o1 let 1
     * @params o2 let 2
     * @return Seřazený list
     */
    @Override
    public int compare(Flight o1, Flight o2) {
        int result = o1.getDate().compareTo(o2.getDate());
        result = ((-1) * result);
        if (0 == result) {
            result = o1.getTakeoffTime().compareTo(o2.getTakeoffTime());
        }
        return result;
    }
}
