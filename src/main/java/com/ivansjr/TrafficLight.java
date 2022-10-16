package com.ivansjr;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrafficLight extends Thread{
    private static Logger logger = Logger.getLogger(
            TrafficLight.class.getName());

    public static void main(String[] args){
        Runnable r = new Runnable() {
            private String isRepeated="s";
            private Thread thread = new Thread(new TrafficLight());
            private Scanner s = new Scanner(System.in);

            @Override
            public void run() {
                synchronized(thread){
                    try{
                        while(isRepeated.equals("s")){
                            logger.log(Level.INFO, "Sinal verde");
                            thread.wait(15000);
                            logger.log(Level.INFO, "Sinal vermelho");
                            thread.wait(15000);
                            logger.log(Level.INFO, "Sinal amarelo");
                            thread.wait(15000);
                            logger.log(Level.WARNING, "Digite 's' para repetir ou qualquer tecla para sair:");
                            isRepeated = s.nextLine();
                        }
                    }catch(InterruptedException e){
                        logger.log(Level.WARNING, "Interrupted!", e);
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        new Thread(r).start();
    }
}
