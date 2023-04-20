package Klasy;

import Kontenery.Kontener;

import java.util.ArrayList;

public class Wagon {
    private ArrayList<Kontener> konteneryNaWagonie = new ArrayList<>();
    private int aktualnaIloscKontenerow = 8;
//    private final int MaxIloscKontenerow = 10;


    public void addToWagonFromStatek(Kontener kontener) throws FullWagonException{
        if(getAktualnaIloscKontenerow() == 9){
            Thread odjazd = new Thread(new OdjazdWagonu());
            odjazd.start();
            aktualnaIloscKontenerow = 0;
            konteneryNaWagonie.clear();
            throw new FullWagonException("Wagon pelny! \n" +
                                        "Odczekaj 30s na przyjazd kolejnego wagonu.");
                            //watek sie odpala 30s na odjazd

        }else {
            this.aktualnaIloscKontenerow++;

            konteneryNaWagonie.add(kontener);

            System.out.println("Dodano "+ kontener.toString() + " na wagon");
        }


    }

    public int getAktualnaIloscKontenerow() {
        return aktualnaIloscKontenerow;
    }

    public ArrayList<Kontener> getKonteneryNaWagonie() {
        return konteneryNaWagonie;
    }




}




