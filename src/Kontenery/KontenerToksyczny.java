package Kontenery;

import Klasy.Nadawca;

    public abstract class KontenerToksyczny extends KontenerCiezki{
        private String zabezpieczenia;
        public KontenerToksyczny(Nadawca nadawca, double wagaNetto, double wagaBrutto, String zabezpieczenia, int iloscPunktowStykowych ) {
            super(nadawca, wagaNetto, wagaBrutto, iloscPunktowStykowych);
            super.typ = "Toksyczny";
            this.zabezpieczenia = zabezpieczenia;
        }

        @Override
        public String toString() {
            return "Kontener nr. "+ getId()+ " "  + getTyp() + " o wadze " + this.getWagaBrutto() + "kg od " +getNadawca() + " zabezpieczenia: " + getZabezpieczenia()+
                    ", ilosc punktow stykowych: " + getIloscPunktowStykowych() + " ";
        }

        public String getZabezpieczenia() {
            return zabezpieczenia;
        }


    }
