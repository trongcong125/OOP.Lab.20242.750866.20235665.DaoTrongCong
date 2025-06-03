package hust.soict.hedspi.aims.Media;

import hust.soict.hedspi.aims.exception.PlayerException;

public interface Playable {
    void play() throws PlayerException;
}