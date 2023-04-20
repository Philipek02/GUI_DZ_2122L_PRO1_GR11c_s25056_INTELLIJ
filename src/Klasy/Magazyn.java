package Klasy;

import Kontenery.*;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Magazyn extends Thread {
        private final int maxIloscKontenerow;
        private int aktualnaIloscKontenerow = 0;
        private Thread next;
        HashMap<Kontener, LocalDate> magazyn = new HashMap<>();

        @Override
        public void run() {
            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //                  Kod bazuje na przykladzie uzytym pod linkiem:
//                https://stackoverflow.com/questions/886955/how-do-i-break-out-of-nested-loops-in-java
            outerLoop:
            while (!Thread.currentThread().isInterrupted()) {
//                System.out.println(Data.getNow());
                for (Map.Entry<Kontener,LocalDate> entry : magazyn.entrySet()){
                    LocalDate dataUtylizacji = entry.getKey().dataUtylizacji(entry.getValue());
                    if(dataUtylizacji == null){
                        continue;
                    }
                    if(dataUtylizacji.compareTo(Data.getNow()) < 0){
                        System.out.println("Zbyt dlugi czas magazynowania kontenera!");
                        try {
                            zutylizuj(entry.getKey());
                        } catch (IrresponsibleSenderWithDangerousGoodsException e) {
                            e.printStackTrace();
                            continue outerLoop;
                        }
                    }//CONCURRENT MODIFICATION EXCEPTION gdyby nie outerloop

                }
                synchronized (next){
                    next.notify();
                }
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
        public void setNext(Thread thread){
            this.next = thread;
        }


        //KONSTRUKTOR
        public Magazyn(int maxIloscKontenerow) {
            this.maxIloscKontenerow = maxIloscKontenerow;

        }


        //DODAWANIE KONTENERA DO MAGAZYNU
        public void addToMagazyn(Kontener kontener) throws MagazynOverloadExeption{
            if(kontener.getNadawca().iloscOstrzezen>=2){
                System.out.println("Nie mozna dodac kontenera. Nadawca wykorzystal juz 2 utylizacje. Odsylanie kontenera do nadawcy");
            }else {
                if (getAktualnaIloscKontenerow() >= getMaxIloscKontenerow()) {
                    throw new MagazynOverloadExeption("Magazyn pelny! Nie mozna dodac kolejnego kontenera");

                } else {
                    this.aktualnaIloscKontenerow++;

                    magazyn.put(kontener, Data.getNow());

                    System.out.println("Dodano " + kontener.toString() + " do magazynu");
                }
            }

        }



        public void removeFromMagazyn(Kontener kontener){
            magazyn.remove(kontener);
            System.out.println("usunieto z magazynu " + kontener);
            aktualnaIloscKontenerow--;



        }


        //METODA WYPISUJACA MAGAZYNOWANE KONTENERY
        public String wypiszKonteneryM(){
            StringBuilder s = new StringBuilder();
            int counter = 1;
            for (Map.Entry<Kontener, LocalDate> e : magazyn.entrySet()){
                s.append(counter++).append(". ").append(e.getKey()).append(" ===>  dodano ").append(e.getValue()).append("\n" );
                // System.out.println(e.getKey() + " dodano dnia " + e.getValue());
            }

            return "" + s;
        }


        public Kontener wyszukajKontenerPoID(int id) throws IncorrectKontenerIDException{
            Kontener wybranyKontener = null;
            for (Map.Entry<Kontener, LocalDate> e : magazyn.entrySet()) {
                if (id == e.getKey().getId()) {
                    wybranyKontener = e.getKey();
                    break;
                }

            }
            if(wybranyKontener == null) {
                throw new IncorrectKontenerIDException("Wprowadzono bledne ID. Nie dodano Kontenera.");
            }
            return wybranyKontener;
        }


        public void zutylizuj(Kontener kontener) throws IrresponsibleSenderWithDangerousGoodsException{

            kontener.getNadawca().iloscOstrzezen++;
            magazyn.remove(kontener);
            aktualnaIloscKontenerow--;
            System.out.println(kontener + " zutylizowany pomyslnie. \n" +
                    "Uwaga! " + kontener.getNadawca() + " otrzymuje ostrzezenie i ma " + kontener.getNadawca().iloscOstrzezen + " ostrzezen");
            throw new IrresponsibleSenderWithDangerousGoodsException("Uwaga! " + kontener.getNadawca() + " otrzymuje ostrzezenie");

        }



    //GETTERY

    public int getAktualnaIloscKontenerow() {
        return aktualnaIloscKontenerow;
    }

    public int getMaxIloscKontenerow() {
        return maxIloscKontenerow;
    }


    public HashMap<Kontener, LocalDate> getMagazyn() {
        return magazyn;
    }

//    public LocalDate getDataDodaniaDoMagazynu(int idKont){
//        LocalDate dataDodania = null;
//        try {
//            dataDodania = magazyn.get(wyszukajKontenerPoID(idKont));
//        } catch (IncorrectKontenerIDException e) {
//            e.printStackTrace();
//        }
//        return dataDodania;
//    }

    @Override
    public String toString() {
            if(magazyn.isEmpty()){
                return "Magazyn jest pusty";
            }else {
                return "Magazyn \n" +
                        "Aktualna ilosc magazynowanych kontenerow: " + getAktualnaIloscKontenerow() + "\n" +
                        "Maksymalna ilosc kontenerow: " + getMaxIloscKontenerow() + "\n" +
                        "Kontenery w magazynie: \n" +
                        wypiszKonteneryM();
            }
    }


}
