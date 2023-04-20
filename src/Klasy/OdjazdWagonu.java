package Klasy;

public class OdjazdWagonu implements Runnable{

    private static int counter = 0;

    @Override
    public void run(){
        System.out.println("Odliczanie startuje!");
        while (counter<=1){
            counter++;
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            if (counter==1){
                System.out.println("Pozostalo 15sekund do przyjazdu nowego wagonu!");
            }else{
                System.out.println("Nowy wagon przyjechal!");
            }
        }

    }

}
