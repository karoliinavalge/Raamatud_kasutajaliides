package com.example.raamatud_kasutajaliides;

import java.io.*;

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

    protected void loeFailist(String failinimi) throws IOException {
        try (FileInputStream fis = new FileInputStream(failinimi);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)){

            while (true){
                String rida = br.readLine();
                if (rida == null) break;
                String[] osad = rida.trim().split(";");
                lisaRaamat(osad[0], osad[1], Integer.parseInt(osad[2]), Integer.parseInt(osad[3]));
            }
        }
    }

    protected void kirjutaFaili(String failinimi) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(failinimi);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(osw)) {
            for (Raamat raamat: raamatud){
                bw.write(raamat.getPealkiri() + ";" + raamat.getAutor() + ";" + raamat.getLehekülgi() + ";" + raamat.getLoetudLehekülgi() + "\n");
            }
        }
    }
}

