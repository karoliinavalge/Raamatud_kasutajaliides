package com.example.raamatud_kasutajaliides;

import java.io.*;

public class LoetudRaamatud extends Nimekiri {
    public LoetudRaamatud() {
        super();
    }

    protected void lisaRaamat(String pealkiri, String autor, int lehekülgi, int hinnang) {
        Raamat uus_raamat = new Raamat(pealkiri, autor, lehekülgi, lehekülgi, hinnang);
        raamatud.add(uus_raamat);
    }

    protected void loeFailist(String failinimi) throws IOException {
        try (FileInputStream fis = new FileInputStream(failinimi);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)){

            while (true){
                String rida = br.readLine();
                if (rida == null) break;
                String[] osad = rida.trim().split(";");
                raamatud.add(new Raamat(osad[0], osad[1], Integer.parseInt(osad[2]), Integer.parseInt(osad[3])));
                System.out.println("lisan raamatu");
                System.out.println(raamatud.size());
            }
        }
    }

    protected void kirjutaFaili(String failinimi) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(failinimi);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(osw)) {
            for (Raamat raamat: raamatud){
                bw.write(raamat.getPealkiri() + ";" + raamat.getAutor() + ";" + raamat.getLehekülgi() + ";" + raamat.getHinnang() + "\n");
            }
        }
    }
}
