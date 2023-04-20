package Kontenery;

import Klasy.Nadawca;

    public class KontenerToksycznySypki extends KontenerToksyczny{

        public KontenerToksycznySypki(Nadawca nadawca, double wagaNetto, double wagaBrutto, String zabezpieczenia, int iloscPunktowStykowych) {
            super(nadawca, wagaNetto, wagaBrutto, zabezpieczenia, iloscPunktowStykowych);
            super.typ = "ToksycznySypki";
        }

        @Override
        public String kontenerSave() {
            return super.kontenerSave()+super.getZabezpieczenia()+"|"+super.getIloscPunktowStykowych()+"|";
        }

    }
