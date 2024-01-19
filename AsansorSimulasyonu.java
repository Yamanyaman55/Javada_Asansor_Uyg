/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package asansoruygulamasi1;


/**
 *
 * @author EXCALIBUR
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


interface AsansorKullanicisi {
    int getAgirlik();
    int getSuAnkiKat();
    int getHedefKat();
}


interface AsansorKontrol {
    void yukariGit();
    void asagiGit();
    void kullanciEkle(AsansorKullanicisi kullanci);
    void kullanciCikar(AsansorKullanicisi kullanci);
}


class Kullanici implements AsansorKullanicisi {
    private int agirlik;
    private int suAnkiKat;
    private int hedefKat;

    public Kullanici(int agirlik, int suAnkiKat, int hedefKat) {
        this.agirlik = agirlik;
        this.suAnkiKat = suAnkiKat;
        this.hedefKat = hedefKat;
    }

    public int getAgirlik() {
        return agirlik;
    }

    public int getSuAnkiKat() {
        return suAnkiKat;
    }

    public int getHedefKat() {
        return hedefKat;
    }
}


class Asansor implements AsansorKontrol {
    private int suAnkiKat;
    private int toplamAgirlik;
    private List<AsansorKullanicisi> kullanicilar;

    public Asansor() {
        this.suAnkiKat = 1;
        this.toplamAgirlik = 0;
        this.kullanicilar = new ArrayList<>();
    }

    @Override
    public void yukariGit() {
        if (suAnkiKat < 10) {
            suAnkiKat++;
            hareketEt();
        }
    }

    @Override
    public void asagiGit() {
        if (suAnkiKat > 1) {
            suAnkiKat--;
            hareketEt();
        }
    }

    @Override
    public void kullanciEkle(AsansorKullanicisi kullanci) {
        if (toplamAgirlik + kullanci.getAgirlik() <= 300) {
            kullanicilar.add(kullanci);
            toplamAgirlik += kullanci.getAgirlik();
            System.out.println("Kullanici eklendi. Toplam agirlik: " + toplamAgirlik + " kg");
        } else {
            System.out.println("Kullanici eklenemiyor. Agirlik limiti aşıldı.");
        }
    }

    @Override
    public void kullanciCikar(AsansorKullanicisi kullanci) {
        kullanicilar.remove(kullanci);
        toplamAgirlik -= kullanci.getAgirlik();
        System.out.println("Kullanıcı çıkarıldı. Toplam agirlik: " + toplamAgirlik + " kg");
    }

    private void hareketEt() {
    System.out.println("Asansor " + suAnkiKat + ". katta");

    Iterator<AsansorKullanicisi> iterator = kullanicilar.iterator();
    while (iterator.hasNext()) {
        AsansorKullanicisi kullanci = iterator.next();
        if (kullanci.getHedefKat() == suAnkiKat) {
            iterator.remove();
            toplamAgirlik -= kullanci.getAgirlik();
            System.out.println("Kullanici " + suAnkiKat + ". katta indi.");
        }
    }

    for (AsansorKullanicisi kullanci : kullanicilar) {
        if (kullanci.getSuAnkiKat() == suAnkiKat) {
            System.out.println("Kullanici " + suAnkiKat + ". katta bindi.");
        }
    }

    if (kullanicilar.size() > 0) {
        int hedefKat = kullanicilar.get(0).getHedefKat();
        if (suAnkiKat != hedefKat) {
            System.out.println("Asansor " + hedefKat + ". kata cikiyor.");
        }
    }
}


}

public class AsansorSimulasyonu {
    public static void main(String[] args) {
        AsansorKontrol asansor = new Asansor();

        AsansorKullanicisi kullanci1 = new Kullanici(20, 1, 5);
        AsansorKullanicisi kullanci2 = new Kullanici(70, 2, 3);
        AsansorKullanicisi kullanci3 = new Kullanici(90, 3, 5);
        
        
        asansor.kullanciEkle(kullanci1);
        asansor.yukariGit();

        asansor.kullanciEkle(kullanci2);
        asansor.yukariGit();

        asansor.kullanciEkle(kullanci3);
        asansor.asagiGit();
        
        
        
    }
}
