package Kontenery;

import Klasy.Nadawca;

public class KontenerCiezki extends KontenerPodstawowy {

    private int iloscPunktowStykowych;

    public KontenerCiezki(Nadawca nadawca, double wagaNetto, double wagaBrutto, int iloscPunktowStykowych) {
        super(nadawca, wagaNetto, wagaBrutto);
        super.typ = "Ciezki";
        this.iloscPunktowStykowych = iloscPunktowStykowych;
    }


    @Override
    public String toString() {
        return "Kontener nr. "+ getId()+ " "  + getTyp() + " o wadze " + this.getWagaBrutto() + "kg od " +getNadawca() + " ilosc punktow stykowych: " + getIloscPunktowStykowych() + " ";
    }

    public int getIloscPunktowStykowych() {
        return iloscPunktowStykowych;
    }
    @Override
    public String kontenerSave(){
        return super.kontenerSave() + "|" + getIloscPunktowStykowych()+"|";
    }
}
