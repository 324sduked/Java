package file.musicfile;

public class MP3MusicFile extends AbstractMusicFile{
    private int quality;
    public MP3MusicFile(String name, int size, String bandName, String title, int quality) {
        super(name, size, bandName, title);
        this.quality = quality;
    }

    @Override
    public void play() {
        System.out.println("Playing MP3 file");
    }

    public int getQuality() {
        return quality;
    }


    @Override
    public String getName() {
        return "Plik nazywa się: " + super.getName();
    }

    @Override
    public String toString() {
        return "MP3MusicFile{" +
                "Zespół: '" + bandName + '\'' +
                ", Tytuł: '" + title + '\'' +
                '}';
    }
}
