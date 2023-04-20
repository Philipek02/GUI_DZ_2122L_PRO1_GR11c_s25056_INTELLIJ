package Kontenery;

import Klasy.Nadawca;

    public class KontenerChlodniczy extends KontenerCiezki{

        private boolean czyPotrzebujePrąd = true;


        public KontenerChlodniczy(Nadawca nadawca, double wagaNetto, double wagaBrutto, int iloscPunktowStykowych) {
            super(nadawca, wagaNetto, wagaBrutto, iloscPunktowStykowych);
            this.czyPotrzebujePrąd = true;
            super.typ = "Chlodniczy";
        }


        @Override
        public String kontenerSave() {
            return super.kontenerSave()+"true|";
        }
    }
