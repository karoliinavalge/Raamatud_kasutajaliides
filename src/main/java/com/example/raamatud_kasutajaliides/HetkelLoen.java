package com.example.raamatud_kasutajaliides;

public class HetkelLoen extends Nimekiri {
    public HetkelLoen() {
        super();
    }

    public void lugesinJuurde(String pealkiri, int lehekülgi) {
        for (Raamat raamat : raamatud) {
            if (raamat.getPealkiri().equals(pealkiri)) {
                int lk_arv = raamat.getLoetudLehekülgi() + lehekülgi;
                raamat.setLoetudLehekülgi(lk_arv);
                break;
            }
        }
    }
}

