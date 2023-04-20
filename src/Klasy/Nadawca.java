package Klasy;



import java.time.DateTimeException;
import java.time.LocalDate;


public class Nadawca {
    private static int idStart = 1;
    //POLA NADAWCY
    private int id;
    private final int[] peselTab = new int[11]; //PESEL w tablicy
    private final String pesel; //PESEL w Stringu
    int iloscOstrzezen = 0;
    private String imie;
    private String nazwisko;






    //KONSTRUKTOR
    // String PESEL --> tab PESEL
    public Nadawca(String pesel, String imie, String nazwisko){
        this.pesel = pesel;
        for (int i = 0; i < 11; i++) {

            this.peselTab[i] = Integer.parseInt(pesel.substring(i,i+1));

        }
        this.id = idStart++;
        this.imie = imie;
        this.nazwisko = nazwisko;

    }

    public LocalDate dataUrodzenia() {
        int year, month, day;
        year = 10 * peselTab[0];
        year += peselTab[1];
        month = 10 * peselTab[2];
        month += peselTab[3];

        if(month>20){
            year = year + 2000;
        }else if(month<13){
            year = year +1900;
        }
        month = 10 * peselTab[2];
        month += peselTab[3];
        if(month>20){
            month = month-20;
        }

        day = 10 * peselTab[4];
        day += peselTab[5];
        try {
            return LocalDate.of(year, month, day);
        }catch (DateTimeException e){
            System.out.println("Niepoprawny nr pesel.");
            return null;
        }
    }

    //GETTERy


    public int getId() {
        return id;
    }

    public String getPesel() {
        return pesel;
    }

    public int getIloscOstrzezen() {
        return iloscOstrzezen;
    }

    @Override
    public String toString() {
        return "nadawca nr. "+getId()+ " " + imie + " ur. " + dataUrodzenia();
    }

}
