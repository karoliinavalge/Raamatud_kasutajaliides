package com.example.raamatud_kasutajaliides;


public class Lugeja {
    private String nimi;
    private LoetudRaamatud loetudRaamatud;
    private TahanLugeda tahanLugeda;
    private HetkelLoen hetkelLoen;

    public Lugeja(String nimi, LoetudRaamatud loetud, TahanLugeda tahan, HetkelLoen loen){
        this.nimi = nimi;
        loetudRaamatud = loetud;
        tahanLugeda = tahan;
        hetkelLoen = loen;
    }

    public void hakkanLugema(String pealkiri){
        for (Raamat raamat : tahanLugeda.raamatud) {
            if (raamat.getPealkiri().equals(pealkiri)) {
                if (tahanLugeda.raamatud.contains(raamat)) tahanLugeda.eemaldaRaamat(raamat);
            }
            hetkelLoen.lisaRaamat(raamat);
            break;
        }
    }

    public void lugesinLõpuni(String pealkiri){
        for (Raamat raamat : hetkelLoen.raamatud) {
            if (raamat.getPealkiri().equals(pealkiri)) {
                hetkelLoen.eemaldaRaamat(raamat);
                String hinnang = "-1";
                //Küsib kasutajalt hinnangut seni kuni kasutaja sisestab täisarvu vahemikus 1-5
                /**
                while (Integer.parseInt(hinnang) < 0 || Integer.parseInt(hinnang) > 5)
                    hinnang = JOptionPane.showInputDialog(null, "Sisestage hinnang loetud raamatule (1-5) ", "Andmete sisestamine",
                            JOptionPane.QUESTION_MESSAGE);
                 */
                raamat.setHinnang(Integer.parseInt(hinnang));
                loetudRaamatud.lisaRaamat(raamat);
                break;
            }
        }
    }

    public LoetudRaamatud getLoetudRaamatud() {
        return loetudRaamatud;
    }

    public TahanLugeda getTahanLugeda() {
        return tahanLugeda;
    }

    public HetkelLoen getHetkelLoen() {
        return hetkelLoen;
    }

    public String toString() {
        int hetkel = (hetkelLoen.raamatud == null ? 0 : hetkelLoen.raamatud.size());
        int loetud = (loetudRaamatud.raamatud == null ? 0 : loetudRaamatud.raamatud.size());
        int tahan = (tahanLugeda.raamatud == null ? 0 : tahanLugeda.raamatud.size());

        return "Lugeja " + nimi + "\nHetkel lugemisel: " + hetkel + "\nLoetud raamatuid: " +
                loetud + "\nTahan lugeda: " + tahan;
    }


}
