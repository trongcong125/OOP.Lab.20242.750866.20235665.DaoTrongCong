package hust.soict.hedspi.aims.Media;

import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, float cost, String director, int length, String artist) {
        super(id, title, category, cost, director, length);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track added: " + track.getTitle());
        } else {
            System.out.println("Track already exists: " + track.getTitle());
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track removed: " + track.getTitle());
        } else {
            System.out.println("Track not found: " + track.getTitle());
        }
    }
    
    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing CD: " + this.getTitle());
            for (Track track : tracks) {
                try {
                    track.play();
                } catch (PlayerException e) {
                    System.err.println(e.getMessage());
                    throw e;
                }
            }
        } else {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }

    public String toString() {
        return "CD - " + getTitle() + " - " + getArtist() + " - " + getCost() + "$";
    }
}