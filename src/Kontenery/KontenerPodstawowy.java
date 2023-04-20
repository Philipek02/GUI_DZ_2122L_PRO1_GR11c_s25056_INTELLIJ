package Kontenery;

import Klasy.Nadawca;

public class KontenerPodstawowy extends Kontener{
    


    public KontenerPodstawowy(Nadawca nadawca, double wagaNetto, double wagaBrutto){
        super(nadawca, wagaNetto, wagaBrutto);
        super.typ = "Podstawowy";
    }


}
