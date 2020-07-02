

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FormFilmler extends JFrame {
    private JButton btnAra;
    private JPanel rootPanel;
    private JTextField txtArama;
    private JLabel lblfilmBaslik;
    private JLabel lblfilmYonetmen;
    private JLabel lblfilmYazar;
    private JLabel lblfilmOyuncu;
    private JList JlFilmListe;
    private JList JlDirektor;
    private JList JlYazar;
    private JList JlOyuncu;

    DefaultListModel direktorModel = new DefaultListModel();
    DefaultListModel yazarModel = new DefaultListModel();
    DefaultListModel OyuncuModel = new DefaultListModel();
    public FormFilmler() {
        add(rootPanel);
        setTitle("Filmler");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JList<String> jList = new JList<>();


        btnAra.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if (filmlers.size()>0){
                    filmlers.clear();
                    direktorModel.removeAllElements();
                    yazarModel.removeAllElements();
                    OyuncuModel.removeAllElements();
                }

                Document doc = null;
                try {
                    doc = Jsoup.connect("https://www.imdb.com/find?q=" + txtArama.getText() + "&ref_=nv_sr_sm").get();
                    // String html = Jsoup.connect("https://www.imdb.com/find?q=hababam&ref_=nv_sr_sm").get().html();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                Element tablo = doc.select("table[class=findList]").first();
                //bulunan linkler
                for (Element element : tablo.select("td:lt(1)")) {
                    linkIslem(element.select("a").attr("href"));
                    System.out.println("\n");

                }


                yazdirma(filmlers);

            }
        });




        JlFilmListe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // super.mouseClicked(e);

                direktorModel.removeAllElements();
                yazarModel.removeAllElements();
                OyuncuModel.removeAllElements();
                for (int x = 0; x < filmlers.get(JlFilmListe.getSelectedIndex()).getDirektors().size(); x++) {
                    //System.out.println("Direktörler : " + flm.get(i).getDirektors().get(x).getIsim());

                    direktorModel.addElement(filmlers.get(JlFilmListe.getSelectedIndex()).getDirektors().get(x).getIsim());


                }
                for (int k = 0; k < filmlers.get(JlFilmListe.getSelectedIndex()).getYazarlars().size(); k++) {
                    //System.out.println("Direktörler : " + flm.get(i).getDirektors().get(x).getIsim());

                    yazarModel.addElement(filmlers.get(JlFilmListe.getSelectedIndex()).getYazarlars().get(k).getIsim());


                }

                for (int z = 0; z < filmlers.get(JlFilmListe.getSelectedIndex()).getOyunculars().size(); z++) {
                    //System.out.println("Direktörler : " + flm.get(i).getDirektors().get(x).getIsim());

                    OyuncuModel.addElement(filmlers.get(JlFilmListe.getSelectedIndex()).getOyunculars().get(z).getIsim());


                }


                JlDirektor.setModel(direktorModel);
                JlYazar.setModel(yazarModel);
                JlOyuncu.setModel(OyuncuModel);


            }
        });
    }


    List<String> oyuncular = new ArrayList<>();
    List<String> yazar = new ArrayList<>();
    List<String> direktor = new ArrayList<>();


    List<FilmVeriler> filmlers = new ArrayList<>();


    public void linkIslem(String link) {
        FilmVeriler filmVeriler = new FilmVeriler();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.imdb.com" + link).get();
            // String html = Jsoup.connect("https://www.imdb.com/find?q=hababam&ref_=nv_sr_sm").get().html();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        filmVeriler.setFilmAdi(doc.select("div[class=title_wrapper] h1").text());//Film adı

     Elements filmBilgi = doc.select("div[class=credit_summary_item] a[href*=/name/]");//İlgili yerin html kodları.

        for (Element element : filmBilgi) {

            if (element.outerHtml().contains("tt_ov_dr")) {
                direktor.add(element.outerHtml());

            } else if (element.outerHtml().contains("tt_ov_wr")) {
                yazar.add(element.outerHtml());

            } else if (element.outerHtml().contains("tt_ov_st_sm")) {

                oyuncular.add(element.outerHtml());
            }
        }
        ServicesStars servisStar = new ServicesStars();
        List<Oyuncular> oyunclarss = servisStar.getStars(oyuncular);
        filmVeriler.setOyunculars(oyunclarss);

        ServicesDirektor servisDirektor = new ServicesDirektor();
        List<Direktor> direktors = servisDirektor.getDirektor(direktor);
        filmVeriler.setDirektors(direktors);

        ServicesYazar servisYazar = new ServicesYazar();
        List<Yazarlar> yazarlars = servisYazar.getYazar(yazar);
        filmVeriler.setYazarlars(yazarlars);

        filmlers.add(filmVeriler);
        oyuncular.clear();
        direktor.clear();
        yazar.clear();


    }



    public void yazdirma(List<FilmVeriler> flm) {


        DefaultListModel filmModel = new DefaultListModel();
        filmModel.removeAllElements();


       JlFilmListe.setModel(filmModel);


        int size = flm.size();

        for (int k = 0; k < size; k++) {

            filmModel.addElement(flm.get(k).getFilmAdi());
        }


        JlFilmListe.setModel(filmModel);


        for (int i = 0; i < size; i++) {
            System.out.println("Film adi : " + flm.get(i).getFilmAdi());

            for (int x = 0; x < flm.get(i).getDirektors().size(); x++) {
                System.out.println("Direktörler : " + flm.get(i).getDirektors().get(x).getIsim());

            }


            for (int x = 0; x < flm.get(i).getYazarlars().size(); x++) {
                System.out.println("Yazarlar : " + flm.get(i).getYazarlars().get(x).getIsim());

            }
            for (int x = 0; x < flm.get(i).getOyunculars().size(); x++) {
                System.out.println("Oyuncular : " + flm.get(i).getOyunculars().get(x).getIsim());

            }

            System.out.println("");

        }

    }
}