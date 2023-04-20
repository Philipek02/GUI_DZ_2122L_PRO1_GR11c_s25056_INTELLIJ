package Klasy;

import java.time.LocalDate;


public class Data extends Thread{

    //KONSTRUKTOR TWORZY OBJEKT DATA Z OBECNA DATA
    private static LocalDate now = LocalDate.now();
    private Thread next;

    public void run() {
//        System.out.println("przed while");
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (!Thread.currentThread().isInterrupted()) {

            now = now.plusDays(1);
//            System.out.println(now);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
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

//    LocalDate datadodaniadomag = Magazyn.getdata

    public void setNext(Thread thread){
        this.next = thread;
    }

    //GETTERY


    public static LocalDate getNow() {
        return now;
    }


    //SETTERY


    public static void setNow(LocalDate now) {
        Data.now = now;
    }
}
