package file.musicfile;

import file.AbstractFile;
import file.FileType;

public abstract class AbstractMusicFile extends AbstractFile implements MusicFile{
    protected String bandName;
    protected String title;

    protected AbstractMusicFile(String name, int size, String bandName, String title) {
        super(name,size);
        this.bandName = bandName;
        this.title = title;
    }

    public String getBandName() {
        return "Nazwa zespołu: " + bandName;
    }

    public String getTitle() {
        return "Tytuł: " + title ;
    }

    @Override
    public FileType getType() {
        return FileType.MUSIC;
    }
}
