package Klasy;

import Kontenery.Kontener;

import java.util.ArrayList;

public class Statek {

    private static int idStart = 100;


    //POLA - DANE PODSTAWOWE STATKU
    
    private int id;
    private final String nazwa;
    private final String portMacierzysty;
    private int aktualnaWagaZaladowanychKontenerow = 0;
    private int aktualnaIloscKontenerow = 0;
    private int aktualnaIloscKontenerowCiezkich = 0;
    private int aktualnaIloscKontenerowToksycznychWybuchowych = 0;
    private int aktualnaIloscKontenerowPotrzebujacychPrad = 0;
    private final double maxLadownoscWagowa;
    private final int maxIloscKontenerow;
    private final int maxIloscKontenerowCiezkich;
    private final int maxIloscKontenerowToksycznychWybuchowych;
    private final int maxIloscKontenerowPotrzebujacychPrad;

    ArrayList<Kontener> konteneryNaStatku = new ArrayList<>();


    // POLA KTORE ZMIENIAJA SIE PO KAZDYM ROZLADUNKU/ ZALADUNKU

    String portZrodlowy;
    String portDocelowy;


    //KONSTRUKTOR

    public Statek(String portMacierzysty, String nazwa, double maxLadownoscWagowa, int maxIloscKontenerow, int maxIloscKontenerowCiezkich, int maxIloscKontenerowToksycznychWybuchowych, int maxIloscKontenerowPotrzebujacychPrad){
        this.maxIloscKontenerow = maxIloscKontenerow;
        this.id = idStart++;
        this.nazwa = nazwa;
        this.portMacierzysty = portMacierzysty;
        this.maxLadownoscWagowa = maxLadownoscWagowa;
        this.maxIloscKontenerowCiezkich = maxIloscKontenerowCiezkich;
        this.maxIloscKontenerowToksycznychWybuchowych = maxIloscKontenerowToksycznychWybuchowych;
        this.maxIloscKontenerowPotrzebujacychPrad = maxIloscKontenerowPotrzebujacychPrad;

    }


    public void addK(Kontener kontener) throws StatekOverloadExeption {
        if (aktualnaIloscKontenerow < maxIloscKontenerow
            && (aktualnaWagaZaladowanychKontenerow + kontener.getWagaBrutto())<=maxLadownoscWagowa ) {
            switch (kontener.getTyp()) {
                case "Chlodniczy" -> {
                    if (aktualnaIloscKontenerowCiezkich < maxIloscKontenerowCiezkich && aktualnaIloscKontenerowPotrzebujacychPrad < maxIloscKontenerowPotrzebujacychPrad) {
                        this.aktualnaIloscKontenerowPotrzebujacychPrad++;
                        this.aktualnaIloscKontenerowCiezkich++;
                        this.aktualnaIloscKontenerow++;
                        konteneryNaStatku.add(kontener);
                        aktualnaWagaZaladowanychKontenerow += kontener.getWagaBrutto();
                    } else {
                        throw new StatekOverloadExeption("Limit kontenerow! Nie mozna zaladowac kontenera.");
                    }
                }
                case "Ciezki" -> {
                    if (aktualnaIloscKontenerowCiezkich < maxIloscKontenerowCiezkich) {
                        this.aktualnaIloscKontenerowCiezkich++;
                        this.aktualnaIloscKontenerow++;
                        konteneryNaStatku.add(kontener);
                        aktualnaWagaZaladowanychKontenerow += kontener.getWagaBrutto();
                    } else {
                        throw new StatekOverloadExeption("Limit kontenerow! Nie mozna zaladowac kontenera.");
                    }
                }
                case "Wybuchowy" -> {
                    if (aktualnaIloscKontenerowCiezkich < maxIloscKontenerowCiezkich && aktualnaIloscKontenerowToksycznychWybuchowych < maxIloscKontenerowToksycznychWybuchowych) {
                        this.aktualnaIloscKontenerow++;
                        this.aktualnaIloscKontenerowCiezkich++;
                        this.aktualnaIloscKontenerowToksycznychWybuchowych++;
                        konteneryNaStatku.add(kontener);
                        aktualnaWagaZaladowanychKontenerow += kontener.getWagaBrutto();
                    } else {
                        throw new StatekOverloadExeption("Limit kontenerow! Nie mozna zaladowac kontenera.");
                    }
                }
                case "Ciekly", "Podstawowy" -> {
                    this.aktualnaIloscKontenerow++;
                    konteneryNaStatku.add(kontener);
                    aktualnaWagaZaladowanychKontenerow += kontener.getWagaBrutto();
                }
                case "Toksyczny", "ToksycznySypki", "ToksycznyCiekly" -> {
                    if (aktualnaIloscKontenerowToksycznychWybuchowych < maxIloscKontenerowToksycznychWybuchowych) {
                        this.aktualnaIloscKontenerow++;
                        this.aktualnaIloscKontenerowToksycznychWybuchowych++;
                        konteneryNaStatku.add(kontener);
                        aktualnaWagaZaladowanychKontenerow += kontener.getWagaBrutto();
                    } else {
                        throw new StatekOverloadExeption("Limit kontenerow! Nie mozna zaladowac kontenera.");
                    }
                }
            }
            ;



            System.out.println("Zaladowano pomyslnie " + kontener + "na statek");
            System.out.println("aktualna waga: " + this.getAktualnaWagaZaladowanychKontenerow());
            System.out.println("aktualna liczba zaladowanych kontenerow: " + this.getAktualnaIloscKontenerow());

        }else{
            throw new StatekOverloadExeption("Ogolna ilosc kontenerow na statku jest maksymalna. Nie mozna dodac kontenera. ");
        }

    }

    public void wypiszKonteneryS() {
        for(Kontener kontener : konteneryNaStatku) {
            System.out.println(kontener);
        }
    }

    public void removeKont(int id) throws IncorrectKontenerIDException {
        Kontener tmp = wyszukajKontenerPoID(id);
        konteneryNaStatku.remove(tmp);
        switch (tmp.getTyp()) {
            case "Chlodniczy" -> {
                    this.aktualnaIloscKontenerowPotrzebujacychPrad--;
                    this.aktualnaIloscKontenerowCiezkich--;
                    this.aktualnaIloscKontenerow--;

                    aktualnaWagaZaladowanychKontenerow -= tmp.getWagaBrutto();

                }
            case "Ciezki" -> {

                    this.aktualnaIloscKontenerowCiezkich--;
                    this.aktualnaIloscKontenerow--;
                    aktualnaWagaZaladowanychKontenerow -= tmp.getWagaBrutto();


            }
            case "Wybuchowy" -> {
                    this.aktualnaIloscKontenerow--;
                    this.aktualnaIloscKontenerowCiezkich--;
                    this.aktualnaIloscKontenerowToksycznychWybuchowych--;
                    aktualnaWagaZaladowanychKontenerow -= tmp.getWagaBrutto();

            }
            case "Ciekly", "Podstawowy" -> {
                this.aktualnaIloscKontenerow--;
                aktualnaWagaZaladowanychKontenerow -= tmp.getWagaBrutto();
            }
            case "Toksyczny", "ToksycznySypki", "ToksycznyCiekly" -> {
                    this.aktualnaIloscKontenerow--;
                    this.aktualnaIloscKontenerowToksycznychWybuchowych--;
                    aktualnaWagaZaladowanychKontenerow -= tmp.getWagaBrutto();
            }
        }
//        System.out.println("Usunieto pomyslnie " + tmp + "ze statku");
    }


    public Kontener wyszukajKontenerPoID(int id) throws IncorrectKontenerIDException{

        for (Kontener kontener : konteneryNaStatku) {
            if (kontener.getId() == id) {
               return kontener;

            }
        }

        throw new IncorrectKontenerIDException("Wprowadzono bledne ID! Nie dodano kontenera ");
    }

    public String wyswietlSzczegoloweInfo(){
        return this.toString() + "\n Ilosc zaladowanych kontenerow: " + this.getAktualnaIloscKontenerow()+
                "\n Maksymalna ilosc kontenerow: " + this.getMaxIloscKontenerow()+
                "\n Aktualna waga zaladowanych kontenerow: " + this.getAktualnaWagaZaladowanychKontenerow()+
                "\n Maksymalna ladownosc wagowa: " + this.getMaxLadownoscWagowa()+
                "\n Kontenery na statku: \n";
    }

//    id;
//    private final String nazwa;
//    private final String portMacierzysty;
//    private int aktualnaWagaZaladowanychKontenerow = 0;
//    private int aktualnaIloscKontenerow = 0;
//    private int aktualnaIloscKontenerowCiezkich = 0;
//    private int aktualnaIloscKontenerowToksycznychWybuchowych = 0;
//    private int aktualnaIloscKontenerowPotrzebujacychPrad = 0;
//    private final double maxLadownoscWagowa;
//    private final int maxIloscKontenerow;
//    private final int maxIloscKontenerowCiezkich;
//    private final int maxIloscKontenerowToksycznychWybuchowych;
//    private final int maxIloscKontenerowPotrzebujacychPrad;



    public String statekSave(){
        return getId()+"|"+getNazwa()+"|"+getPortMacierzysty()+"|"+getAktualnaIloscKontenerow()+"|"+getAktualnaIloscKontenerowCiezkich()+"|"+
                getAktualnaIloscKontenerowToksycznychWybuchowych()+"|"+getAktualnaIloscKontenerowPotrzebujacychPrad()+"|"+getMaxLadownoscWagowa()+"|"+
                getMaxIloscKontenerow()+"|"+getMaxIloscKontenerowCiezkich()+"|"+getMaxIloscKontenerowToksycznychWybuchowych()+"|"+getMaxIloscKontenerowPotrzebujacychPrad();
    }


    //GETTERY

    public int getAktualnaIloscKontenerowCiezkich() {
        return aktualnaIloscKontenerowCiezkich;
    }

    public int getAktualnaIloscKontenerowPotrzebujacychPrad() {
        return aktualnaIloscKontenerowPotrzebujacychPrad;
    }

    public int getAktualnaIloscKontenerowToksycznychWybuchowych() {
        return aktualnaIloscKontenerowToksycznychWybuchowych;
    }

    public int getMaxIloscKontenerowCiezkich() {
        return maxIloscKontenerowCiezkich;
    }

    public int getMaxIloscKontenerowPotrzebujacychPrad() {
        return maxIloscKontenerowPotrzebujacychPrad;
    }

    public int getMaxIloscKontenerowToksycznychWybuchowych() {
        return maxIloscKontenerowToksycznychWybuchowych;
    }

    public int getAktualnaWagaZaladowanychKontenerow() {
        return aktualnaWagaZaladowanychKontenerow;
    }

    public int getId() {
        return id;
    }

    public String getPortMacierzysty() {
        return portMacierzysty;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getAktualnaIloscKontenerow() {
        return aktualnaIloscKontenerow;
    }

    public int getMaxIloscKontenerow() {
        return maxIloscKontenerow;
    }

    public double getMaxLadownoscWagowa() {
        return maxLadownoscWagowa;
    }

    public ArrayList<Kontener> getKonteneryNaStatku() {
        return konteneryNaStatku;
    }
    //SETTERY


    public void setPortDocelowy(String portDocelowy) {
        this.portDocelowy = portDocelowy;
    }

    public void setPortZrodlowy(String portZrodlowy) {
        this.portZrodlowy = portZrodlowy;
    }

    @Override
    public String toString() {
        return "Statek nr " + getId() + " \"" + getNazwa() + "\" o porcie macierzystym " +  getPortMacierzysty();
    }
}
