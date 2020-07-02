import java.util.ArrayList;
import java.util.List;

public class ServicesStars {

    public List<Oyuncular> getStars(List<String> gelenStar) {

        int ilkBuyukIsaret, ilkKucukIsaret;
        String kalanKelime, kelime;
        List<Oyuncular> oyunculars = new ArrayList<>();

        for (String html : gelenStar) {
            Oyuncular oyuncular1 = new Oyuncular();

            ilkBuyukIsaret = html.indexOf('>');
            kalanKelime = html.substring((ilkBuyukIsaret + 1));
            ilkKucukIsaret = kalanKelime.indexOf('<');
            kelime = kalanKelime.substring(0, ilkKucukIsaret);
            oyuncular1.setIsim(kelime);

            oyunculars.add(oyuncular1);
        }

        return oyunculars;
    }
}
