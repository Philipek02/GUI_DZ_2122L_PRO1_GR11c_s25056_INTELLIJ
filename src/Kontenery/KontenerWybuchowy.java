package Kontenery;

import Klasy.Nadawca;

public class KontenerWybuchowy extends KontenerCiezki{
        private int nrCertyfikatu;
        public KontenerWybuchowy(Nadawca nadawca, double wagaNetto, double wagaBrutto, int nrCertyfikatu, int iloscPunktowStykowych){
            super(nadawca, wagaNetto, wagaBrutto, iloscPunktowStykowych);
            super.typ = "Wybuchowy";
            this.nrCertyfikatu = nrCertyfikatu;

        }

    @Override
    public String toString() {
        return "Kontener nr. "+ getId()+ " "  + getTyp() + " o wadze " +
                this.getWagaBrutto() + "kg od " +getNadawca() + " nr certyfikatu: " + getNrCertyfikatu() +
                ", ilosc punktow stykowych: " + getIloscPunktowStykowych() + " ";
    }

    public int getNrCertyfikatu() {
        return nrCertyfikatu;
    }

    @Override
    public String kontenerSave() {
        return super.kontenerSave()+getNrCertyfikatu()+"|";
    }
}
