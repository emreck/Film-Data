import java.util.ArrayList;
import java.util.List;

public class ServicesYazar {

    public List<Yazarlar> getYazar(List<String> gelenYazar){

        int ilkBuyukIsaret, ilkKucukIsaret;
        String kalanKelime, kelime;
        List<Yazarlar> yazarlars = new ArrayList<>();

        for (String html : gelenYazar) {
            Yazarlar yazarlar = new Yazarlar();
            ilkBuyukIsaret = html.indexOf('>');
            kalanKelime = html.substring((ilkBuyukIsaret + 1));
            ilkKucukIsaret = kalanKelime.indexOf('<');
            kelime = kalanKelime.substring(0, ilkKucukIsaret);

            yazarlar.setIsim(kelime);
            yazarlars.add(yazarlar);
        }

        return yazarlars ;
    }
}
