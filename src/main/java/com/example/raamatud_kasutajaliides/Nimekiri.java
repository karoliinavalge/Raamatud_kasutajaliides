package com.example.raamatud_kasutajaliides;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nimekiri {
    protected List<Raamat> raamatud;

    //Konstruktor
    public Nimekiri() {
        this.raamatud = new ArrayList<Raamat>();
    }

    protected void lisaRaamat(String pealkiri, String autor, int lehekülgi, int loetudLehekülgi) {
        Raamat uus_raamat = new Raamat(pealkiri, autor, lehekülgi, loetudLehekülgi);
        raamatud.add(uus_raamat);
    }

    protected void lisaRaamat(Raamat uus_raamat) {
        raamatud.add(uus_raamat);
    }

    protected void eemaldaRaamat(Raamat raamat) {
        raamatud.remove(raamat);
    }

    protected Raamat valiSuvaline() {
        Random rand = new Random();
        int indeks = rand.nextInt(0, raamatud.size());
        return raamatud.get(indeks);
    }
}
