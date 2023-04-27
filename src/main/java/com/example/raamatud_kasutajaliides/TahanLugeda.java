package com.example.raamatud_kasutajaliides;

import java.io.*;

public class TahanLugeda extends Nimekiri {
    public TahanLugeda() {
        super();
    }

    protected void lisaRaamat(String pealkiri, String autor, int lehekülgi) {
        super.lisaRaamat(pealkiri, autor, lehekülgi, 0);
    }

    protected void loeFailist(String failinimi) throws IOException {
        try (FileInputStream fis = new FileInputStream(failinimi);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)){

            while (true){
                String rida = br.readLine();
                if (rida == null) break;
                String[] osad = rida.trim().split(";");
                lisaRaamat(osad[0], osad[1], Integer.parseInt(osad[2]));
            }
        }
    }

    protected void kirjutaFaili(String failinimi) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(failinimi);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw)) {
            for (Raamat raamat: raamatud){
                bw.write(raamat.getPealkiri() + ";" + raamat.getAutor() + ";" + raamat.getLehekülgi() + "\n");
            }
        }
    }
}
