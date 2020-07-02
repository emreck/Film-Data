import java.util.List;

public class FilmVeriler {

    private String FilmAdi;
    private List<Direktor> direktors;
    private List<Oyuncular> oyunculars;
    private List<Yazarlar> yazarlars;

    public FilmVeriler() {
    }

    public FilmVeriler(String filmAdi, List<Direktor> direktors, List<Oyuncular> oyunculars, List<Yazarlar> yazarlars) {
        FilmAdi = filmAdi;
        this.direktors = direktors;
        this.oyunculars = oyunculars;
        this.yazarlars = yazarlars;
    }

    public String getFilmAdi() {
        return FilmAdi;
    }

    public void setFilmAdi(String filmAdi) {
        FilmAdi = filmAdi;
    }

    public List<Direktor> getDirektors() {
        return direktors;
    }

    public void setDirektors(List<Direktor> direktors) {
        this.direktors = direktors;
    }

    public List<Oyuncular> getOyunculars() {
        return oyunculars;
    }

    public void setOyunculars(List<Oyuncular> oyunculars) {
        this.oyunculars = oyunculars;
    }

    public List<Yazarlar> getYazarlars() {
        return yazarlars;
    }

    public void setYazarlars(List<Yazarlar> yazarlars) {
        this.yazarlars = yazarlars;
    }
}
