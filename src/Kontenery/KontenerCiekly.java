package Kontenery;

import Klasy.Nadawca;

public class KontenerCiekly extends KontenerPodstawowy implements Ciekly{
    int objetosc;

    public KontenerCiekly(Nadawca nadawca, double wagaNetto, double wagaBrutto, int objetosc){
        super(nadawca, wagaNetto, wagaBrutto);
        super.typ = "Ciekly";
        this.objetosc = objetosc;

    }

    @Override
    public int getObjetosc() {
        return objetosc;
    }

    @Override
    public String kontenerSave() {
        return super.kontenerSave()+getObjetosc()+"|";
    }
}

