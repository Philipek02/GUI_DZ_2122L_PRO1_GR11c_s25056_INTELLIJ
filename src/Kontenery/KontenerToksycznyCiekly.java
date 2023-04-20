package Kontenery;

import Klasy.Nadawca;

public class KontenerToksycznyCiekly extends KontenerToksyczny implements Ciekly{

    int objetosc;
    public KontenerToksycznyCiekly(Nadawca nadawca, double wagaNetto, double wagaBrutto, String zabezpieczenia, int iloscPunktowStykowych, int objetosc) {
        super(nadawca, wagaNetto, wagaBrutto, zabezpieczenia, iloscPunktowStykowych);
        super.typ = "ToksycznyCiekly";
        this.objetosc = objetosc;
    }

    @Override
    public int getObjetosc() {
        return objetosc;
    }

    @Override
    public String kontenerSave() {
        return super.kontenerSave() + getObjetosc()+"|";
    }
}
