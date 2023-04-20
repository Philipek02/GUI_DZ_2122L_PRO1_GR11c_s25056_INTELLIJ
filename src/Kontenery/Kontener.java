package Kontenery;


import Klasy.Data;
import Klasy.Magazyn;
import Klasy.Nadawca;

import java.time.LocalDate;

public abstract class Kontener {
     private static int idStart = 10;
     //DANE PODSTAWOWE KONTENERA
     private int id;
     public String typ;
     private Nadawca nadawca;
     private double tara;
     private double wagaNetto;
     private double wagaBrutto;



    //KONSTRUKTOR
     public Kontener(Nadawca nadawca, double wagaNetto, double wagaBrutto){
        this.nadawca = nadawca;
        this.tara = wagaBrutto-wagaNetto;
        this.wagaNetto = wagaNetto;
        this.wagaBrutto = wagaBrutto;
        this.typ = null;
        this.id = idStart++;
    }


    public LocalDate dataUtylizacji(LocalDate data) {
        switch (getTyp()){
            case "Wybuchowy" -> {
                return data.plusDays(5);
            }
            case "ToksycznyCiekly" -> {
                return data.plusDays(10);
            }
            case "ToksycznySypki" -> {
                return data.plusDays(14);
            }
            default -> {
                return null;
            }
        }

    }


    public String kontenerSave(){
         return getId()+"|"+getTyp()+"|"+getIDNadawcy()+"|"+getWagaBrutto()+"|"+getWagaNetto()+"|";
    }




    //GETTERY
    public double getWagaBrutto() {
        return wagaBrutto;
    }

    public String getTyp() {
        return typ;
    }

    public int getId() {
        return id;
    }

    public Nadawca getNadawca() {
        return nadawca;
    }

    public int getIDNadawcy(){
         return nadawca.getId();
    }

    public double getWagaNetto() {
        return wagaNetto;
    }

    @Override
    public String toString() {
        return "Kontener nr. "+ getId()+ " "  + getTyp() + " o wadze " + this.getWagaBrutto() + "kg od " +getNadawca() ;
    }



}
