import Klasy.*;
import Kontenery.*;


import java.io.*;
import java.time.LocalDate;
import java.util.*;


public class Main {
    private static ArrayList<Statek> statki = new ArrayList<>();
    private static ArrayList<Nadawca> nadawcy = new ArrayList<>();

    public static void main(String[] args) {

        int statkiIndexCounter = 0;
        int nadawcyIndexcounter = 0;

        Magazyn magazyn = new Magazyn(6);
        Wagon wagon = new Wagon();


        //////////////////////////
        System.out.println("\n");
        ///////////////////////////

        Data date = new Data();
        date.setNext(magazyn);
        magazyn.setNext(date);
        date.start();
        magazyn.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (magazyn){
            magazyn.notify();
        }



        Scanner sc = new Scanner(System.in);




        //START
        System.out.println("Wczytac dane w pliku? \n" +
                "1. Tak \n" +
                "2. Nie");
        int wyborWczyt = sc.nextInt();
        if(wyborWczyt == 2){
            System.out.println("Uruchomiono aplikacje z domyslnymi danymi \n\n");

            Statek statek1 = new Statek("Gdansk", "Pogoria", 43000, 2, 5, 4, 2);
            statki.add(statkiIndexCounter++, statek1);
            Statek statek2 = new Statek("Sopot", "Globemaster", 23000, 5, 2, 3, 3);
            statki.add(statkiIndexCounter++, statek2);


            Nadawca nadawca1 = new Nadawca("02302306732", "Jakub", "Nowak");
            nadawcy.add(nadawca1);
            Nadawca nadawca2 = new Nadawca("79052039977", "Tomasz", "Baranski");
            nadawcy.add(nadawca2);

            Kontener k1 = new KontenerPodstawowy(nadawca1, 1000, 2000);
            Kontener k2 = new KontenerCiezki(nadawca2, 5000, 6000, 2);
            Kontener k3 = new KontenerChlodniczy(nadawca1, 3000, 4000, 1);
            Kontener k4 = new KontenerToksycznyCiekly(nadawca2, 4000, 5000, "brak", 4, 100);
            Kontener k5 = new KontenerWybuchowy(nadawca1, 5000, 6000, 1234, 4);
            Kontener k6 = new KontenerToksycznySypki(nadawca2, 4000, 5000, "folia", 4);
            Kontener k7 = new KontenerChlodniczy(nadawca1, 2000, 3000, 6) ;







            try {
                statek1.addK(k5);
                statek1.addK(k6);
                System.out.println();
            } catch (StatekOverloadExeption e) {
                e.printStackTrace();
            }

            try {
                magazyn.addToMagazyn(k1);
                magazyn.addToMagazyn(k2);
                magazyn.addToMagazyn(k3);
                magazyn.addToMagazyn(k4);
                magazyn.addToMagazyn(k7);
            } catch (MagazynOverloadExeption e) {
                e.printStackTrace();
            }
            //WCZYTYWANIE
        }else {
            //DATA

            try {
                FileReader fr = new FileReader("Data.txt");
                BufferedReader fileScanner = new BufferedReader(fr);
                String nextLine = fileScanner.readLine();
                int year = Integer.parseInt(nextLine.substring(0,4));
                int month = Integer.parseInt(nextLine.substring(5,7));
                int day = Integer.parseInt(nextLine.substring(8,10));


                fileScanner.close();
                Data.setNow(LocalDate.of(year, month, day));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //MAGAZYN


            try {
                FileReader fr = new FileReader("Magazyn.txt");
                BufferedReader fileScanner = new BufferedReader(fr);
                String nextLine = fileScanner.readLine();
//                while ()






            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        // MENU DLA UZYTKOWNIKA

        String przywitanie = "Witamy w programie do obslugi portu morskiego Pogoria! ";
        String wybieranieFunkcji = "Wybierz dzialanie ktore chcesz wykonac wybierajac jego numer i zatwierdz ENTERem \n" +
                "1. Przejrzyj aktualny stan magazynu \n" +
                "2. Przejrzyj aktualny stan statku \n" +
                "3. Zaladuj kontener na statek \n" +
                "4. Zaladuj kontener ze statku do magazynu lub na wagon kolejowy \n" +
                "5. Zutylizuj kontener z magazynu \n" +
                "6. Dodaj nowy statek \n" +
                "7. Dodaj nowy kontener \n" +
                "8. Zapisz aktualny stan portu  \n" +
                "9. Zakoncz dzialanie programu \n" +
                "10.Wyswietl aktualna date \n" +
                "11.Dodaj Nadawce \n" +
                "12.Wypusc statek z portu";
        int numerFunkcji;

        do {
            System.out.println(Data.getNow());
            System.out.println(przywitanie);
            System.out.println(wybieranieFunkcji);


            numerFunkcji = sc.nextInt();


            switch (numerFunkcji) {
                case 1 -> System.out.println(magazyn);
                case 2 -> {
                    System.out.println("Wybierz ID statku o ktorym informacje chcesz wyswietlic: ");
                    for (int i = 0; i < statki.size(); i++) {
                        System.out.println(i + 1 + ". " + statki.get(i));
                    }
                    int idStatku = sc.nextInt();
                    System.out.println(wyszukajStatekPoID(idStatku).wyswietlSzczegoloweInfo());
                    wyszukajStatekPoID(idStatku).wypiszKonteneryS();
                }
                case 3 -> {
                    System.out.println("Wybierz ID statku na ktory chcesz dodac kontener: ");
                    for (int i = 0; i < statki.size(); i++) {
                        System.out.println(i + 1 + ". " + statki.get(i));
                    }
                    int idStatku = sc.nextInt();
                    Statek wybranyStatek = wyszukajStatekPoID(idStatku);
                    System.out.println("Wprowadz ID kontenera z magazynu ktory chcesz dodac: ");
                    System.out.println(magazyn.wypiszKonteneryM());
                    int nrKontenera = sc.nextInt();
//                    LocalDate temp =
                    try {
                        wyszukajStatekPoID(idStatku).addK(magazyn.wyszukajKontenerPoID(nrKontenera));
                        magazyn.removeFromMagazyn(magazyn.wyszukajKontenerPoID(nrKontenera));
                    } catch (StatekOverloadExeption | IncorrectKontenerIDException e) {
//                        magazyn.addToMagazynFromStatek();
                        e.printStackTrace();

                    }
                }

                case 4 -> {
                    System.out.println("Wybierz dokad chcesz rozladowac kontener ze statku: \n" +
                            "1. Magazyn \n" +
                            "2. Wagon kolejowy");
                    int wybor = sc.nextInt();
                    System.out.println("Wybierz ID statku z ktorego chcesz rozladowac kontener: ");
                    for (int i = 0; i < statki.size(); i++) {
                        System.out.println(i + 1 + ". " + statki.get(i));
                    }
                    int idStatku2 = sc.nextInt();
                    System.out.println("Wybierz ID kontenera do rozladowania: ");
                    wyszukajStatekPoID(idStatku2).wypiszKonteneryS();
                    int idKont = sc.nextInt();


                    if (wybor == 1) {
                        try {
                            Statek tmp = wyszukajStatekPoID(idStatku2);
                            magazyn.addToMagazyn(tmp.wyszukajKontenerPoID(idKont));
                            tmp.removeKont(idKont);
                        } catch (MagazynOverloadExeption | IncorrectKontenerIDException e) {
                            e.printStackTrace();
                        }
                    }else{

                        try {
                            Statek tmp2 = wyszukajStatekPoID(idStatku2);
                            wagon.addToWagonFromStatek(tmp2.wyszukajKontenerPoID(idKont));
                            tmp2.removeKont(idKont);
                        } catch (FullWagonException | IncorrectKontenerIDException e) {
                            e.printStackTrace();
                        }
                        System.out.println("aktualna ilosc kont na wagonie: " + wagon.getAktualnaIloscKontenerow());
                    }





                }



                case 5 -> {
                    System.out.println("Wybierz ID kontenera do zutylizowania: ");
                    System.out.println(magazyn.wypiszKonteneryM());
                    int idKontenera = sc.nextInt();
                    try {
                        magazyn.zutylizuj(magazyn.wyszukajKontenerPoID(idKontenera));
                    } catch (IrresponsibleSenderWithDangerousGoodsException | IncorrectKontenerIDException e) {
                        e.printStackTrace();
                    }
                }
                case 6 -> {
                    System.out.println("Tworzysz nowy statek. Podaj nastepujace parametry: ");
                    System.out.println("Nazwa: ");
                    String nazwa = sc.next();
                    System.out.println("Port macierzysty: ");
                    String portMacierzysty = sc.next();
                    System.out.println("Maksymalna ladownosc wagowa: ");
                    double maxLadownoscWagowa = sc.nextDouble();
                    System.out.println("Maksymalna ilosc kontenerow: ");
                    int maxIloscKontenerow = sc.nextInt();
                    System.out.println("Maksymalna ilosc kontenerow ciezkich: ");
                    int maxIloscKontenerowCiezkich = sc.nextInt();
                    System.out.println("Maksymalna ilosc kontenerow toksycznych i wybuchowych lacznie: ");
                    int maxIloscKontenerowToksycznychWybuchowych = sc.nextInt();
                    System.out.println("Maksymalna ilosc kontenerow potrzebujacych prad: ");
                    int maxIloscKontenerowPotrzebujacychPrad = sc.nextInt();
                    Statek statek = new Statek(portMacierzysty, nazwa, maxLadownoscWagowa, maxIloscKontenerow, maxIloscKontenerowCiezkich,
                            maxIloscKontenerowToksycznychWybuchowych, maxIloscKontenerowPotrzebujacychPrad);
                    statki.add(statkiIndexCounter++, statek);
                    System.out.println("Pomyslnie dodano " + statek);
                }
                case 7 -> {
                    System.out.println("Tworzysz nowy kontener. Wybierz jego typ: \n" +
                            "1. Podstawowy \n" +
                            "2. Ciezki \n" +
                            "3. Chlodniczy \n" +
                            "4. Na materialy ciekle \n" +
                            "5. Na materialy wybuchowe \n" +
                            "6. Na materialy toksyczne ciekle \n" +
                            "7. Na materialy toksyczne sypkie");
                    int wybranyTyp = sc.nextInt();
                    switch (wybranyTyp){
                        case 1 -> {
                            System.out.println("Tworzysz kontener Podstawowy.");
                            Kontener kontener = new KontenerPodstawowy(wyszukajNadawcePoID(pobierzID()),pobierzWageNetto(), pobierzWageBrutto());

                            try {
                                magazyn.addToMagazyn(kontener);
//                                System.out.println("Pomyslnie dodano "+ kontener);
                            }catch (MagazynOverloadExeption e ){
                                e.printStackTrace();
                            }
                        }
                        case 2 -> {
                            System.out.println("Tworzysz kontener Ciezki.");
                            Kontener kontener = new KontenerCiezki(wyszukajNadawcePoID(pobierzID()),pobierzWageNetto(), pobierzWageBrutto(), pobierzIloscPktStykowych());

                            try {
                                magazyn.addToMagazyn(kontener);
//                                System.out.println("Pomyslnie dodano "+ kontener);
                            }catch (MagazynOverloadExeption e ){
                                e.printStackTrace();
                            }
                        }

                        case 3 -> {
                            System.out.println("Tworzysz kontener Chlodniczy.");
                            Kontener kontener = new KontenerChlodniczy(wyszukajNadawcePoID(pobierzID()),pobierzWageNetto(), pobierzWageBrutto(), pobierzIloscPktStykowych());

                            try {
                                magazyn.addToMagazyn(kontener);
//                                System.out.println("Pomyslnie dodano "+ kontener);
                            }catch (MagazynOverloadExeption e ){
                                e.printStackTrace();
                            }
                        }
                        case 4 -> {
                            System.out.println("Tworzysz kontener na materialy ciekle.");
                            Kontener kontener = new KontenerCiekly(wyszukajNadawcePoID(pobierzID()),pobierzWageNetto(), pobierzWageBrutto(), pobierzObjetosc());

                            try {
                                magazyn.addToMagazyn(kontener);
//                                System.out.println("Pomyslnie dodano "+ kontener);
                            }catch (MagazynOverloadExeption e ){
                                e.printStackTrace();
                            }
                        }
                        case 5 -> {
                            System.out.println("Tworzysz kontener na materialy wybuchowe.");
                            Kontener kontener = new KontenerWybuchowy(wyszukajNadawcePoID(pobierzID()),pobierzWageNetto(), pobierzWageBrutto(), pobierzNrCertyfikatu(), pobierzIloscPktStykowych());

                            try {
                                magazyn.addToMagazyn(kontener);
//                                System.out.println("Pomyslnie dodano "+ kontener);
                            }catch (MagazynOverloadExeption e ){
                                e.printStackTrace();
                            }
                        }
                        case 6 -> {
                            System.out.println("Tworzysz kontener na materialy Toksyczne Ciekle.");
                            Kontener kontener = new KontenerToksycznyCiekly(wyszukajNadawcePoID(pobierzID()),pobierzWageNetto(), pobierzWageBrutto(), pobierzInfoZabezpieczenia(), pobierzIloscPktStykowych(), pobierzObjetosc());

                            try {
                                magazyn.addToMagazyn(kontener);
//                                System.out.println("Pomyslnie dodano "+ kontener);
                            }catch (MagazynOverloadExeption e ){
                                e.printStackTrace();
                            }
                        }
                        case 7 -> {
                            System.out.println("Tworzysz kontener na materialy Toksyczne Sypkie.");
                            Kontener kontener = new KontenerToksycznySypki(wyszukajNadawcePoID(pobierzID()),pobierzWageNetto(), pobierzWageBrutto(), pobierzInfoZabezpieczenia(), pobierzIloscPktStykowych());

                            try {
                                magazyn.addToMagazyn(kontener);
//                                System.out.println("Pomyslnie dodano "+ kontener);
                            }catch (MagazynOverloadExeption e ){
                                e.printStackTrace();
                            }
                        }
                    }


                }
                //CASE 8 --> ZAPISYWANIE DO PLIKU
                case 8 -> {
                    try {

                        BufferedWriter dt2 = new BufferedWriter(new FileWriter("Data.txt"));
                        BufferedWriter mg2 = new BufferedWriter(new FileWriter("Magazyn.txt"));
                        BufferedWriter st2 = new BufferedWriter(new FileWriter("Statek.txt"));
                        BufferedWriter nd2 = new BufferedWriter(new FileWriter("Nadawcy.txt"));
                        BufferedWriter wg2 = new BufferedWriter(new FileWriter("Wagon.txt"));
                        //DATA
                        dt2.write(Data.getNow() +"");

                        //MAGAZYN
                        for (Map.Entry<Kontener, LocalDate> e : magazyn.getMagazyn().entrySet()){
                            mg2.write(e.getKey().kontenerSave() + "|" + e.getValue() + "\n");
                        }

                        //STATEK
                        for(Statek statek : statki){
                            st2.write(statek.statekSave() +"\n");
                            for (Kontener kontener : statek.getKonteneryNaStatku()){
                                st2.write(kontener.kontenerSave() +"\n");
                            }
                            st2.write("\n");
                        }

                        //NADAWCY
                        for (Nadawca nadawca : nadawcy){
                            nd2.write(nadawca.getId() +"|"+nadawca.getPesel() +"|"+ nadawca.getIloscOstrzezen() + "\n");
                        }

                        //WAGON
                        wg2.write(wagon.getAktualnaIloscKontenerow() + "|\n");
                        for (Kontener kontener : wagon.getKonteneryNaWagonie()){
                            wg2.write(kontener.kontenerSave());
                        }

                        System.out.println("Stan aplikacji zapisano pomyslnie do plikow");
                        dt2.close();
                        mg2.close();
                        st2.close();
                        nd2.close();
                        wg2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                case 9 -> System.exit(0);
                case 10 -> System.out.println(Data.getNow());
                case 11 -> {
                    System.out.println("Tworzysz Nadawce. \n" +
                                        "Podaj jego pesel: ");
                    String peselN = sc.next();
                    System.out.println("Podaj imie: ");
                    String imie = sc.next();
                    System.out.println("Podaj nazwisko: ");
                    String nazwisko = sc.next();
                    Nadawca nadawca = new Nadawca(peselN, imie, nazwisko);
                        nadawcy.add(nadawcyIndexcounter++, nadawca);
                    System.out.println("Pomyslnie dodano nowego nadawce --> " + nadawca);
                }
                case 12 -> {
                    System.out.println("Wybierz ID statku do wypuszczenia z portu: ");
                    int j = 1;
                    for(Statek statek : statki){
                        System.out.println(j +". " + statek);
                    }
                    int idStatku3 = sc.nextInt();
                    System.out.println("Podaj dokad wypuszczasz statek: ");
                    String portDocelowy = sc.next();
                    wyszukajStatekPoID(idStatku3).setPortZrodlowy("Pogoria");
                    wyszukajStatekPoID(idStatku3).setPortDocelowy(portDocelowy);
                    statki.remove(wyszukajStatekPoID(idStatku3));

                }
            }
            System.out.println("\n \n");
        } while (numerFunkcji != 9);

        sc.close();
    }

    //METODY DO STATKU
    public static Statek wyszukajStatekPoID(int id){
        for(Statek statek : statki){
            if(statek.getId() == id){
                return statek;
            }
        }
        System.out.println("Nie znalezniono statku o podanym ID! ");
        return null;

    }


    //METODY DO TWORZENIA KONTENERA{
    public static void wyswietlNadawcow(){
        for (Nadawca nadawca : nadawcy) {
            System.out.println(nadawca.getId() + " " + nadawca + " ===> data urodzenia: " + nadawca.dataUrodzenia());
        }

    }

    public static Nadawca wyszukajNadawcePoID(int id){
        for (Nadawca nadawca : nadawcy){
            if(nadawca.getId() == id){
                return nadawca;
            }
        }
        System.out.println("Nie znaleziono nadawcy o podanym ID! ");
        return null;
    }

    public static int pobierzID(){
        wyswietlNadawcow();
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Wybierz nadawce wpisujac jego ID: ");
        int idNadawcy = sc2.nextInt();
        return idNadawcy;
    }

    public static double pobierzWageNetto(){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Podaj wage Netto");
        double wagaNetto = sc2.nextDouble();
        return wagaNetto;
    }

    public static double pobierzWageBrutto() {
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Podaj wage Brutto");
        double wagaBrutto = sc2.nextDouble();
        return wagaBrutto;
    }

    public static String pobierzInfoZabezpieczenia(){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Podaj zabezpieczenia: ");
        String zabezpieczenia = sc2.next();
        return zabezpieczenia;
    }

    public static int pobierzNrCertyfikatu(){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Podaj nr certyfikatu: ");
        int nr = sc2.nextInt();
        return nr;
    }

    public static int pobierzIloscPktStykowych(){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Podaj ilosc punktow stykowych: ");
        int ilosc = sc2.nextInt();
        return ilosc;
    }

    public static int pobierzObjetosc(){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Podaj objetosc(cm3): ");
        int objetosc = sc2.nextInt();
        return objetosc;
    }



}
