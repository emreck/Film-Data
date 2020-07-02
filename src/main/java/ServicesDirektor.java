import java.util.ArrayList;
import java.util.List;

public class ServicesDirektor {

    public List<Direktor> getDirektor(List<String> direktor){

        int ilkBuyukIsaret, ilkKucukIsaret;
        String kalanKelime, kelime;

        List<Direktor> direktors = new ArrayList<>();

        for (String html : direktor) {

            Direktor dr = new Direktor();
            ilkBuyukIsaret = html.indexOf('>');
            kalanKelime = html.substring((ilkBuyukIsaret + 1));
            ilkKucukIsaret = kalanKelime.indexOf('<');
            kelime = kalanKelime.substring(0, ilkKucukIsaret);
            dr.setIsim(kelime);
            direktors.add(dr);
        }

        return direktors ;
    }
}
