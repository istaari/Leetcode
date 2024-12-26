package designPatterns;


interface MediaPlayer {
    void play(String audioType, String fileName);
}


class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else {
            MediaPlayer adapter = new MediaAdapter(audioType); // passing audio type
            adapter.play(audioType, fileName);
        }
    }
}


interface AdvancedMediaPlayer {
    void playAdvanced(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playAdvanced(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}

class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playAdvanced(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }

}


class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMediaPlayer;

    MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (advancedMediaPlayer != null) {
            advancedMediaPlayer.playAdvanced(fileName);
        } else {
            System.out.println("Unsupported audio type: " + audioType);
        }
    }

}

public class AdapterPattern {

    public static void main(String[] args) {
        MediaPlayer player = new AudioPlayer();

        player.play("mp3", "song.mp3");         // Output: Playing MP3 file: song.mp3
        player.play("mp4", "movie.mp4");       // Output: Playing MP4 file: movie.mp4
        player.play("vlc", "video.vlc");       // Output: Playing VLC file: video.vlc
        player.play("avi", "clip.avi");        // Output: Unsupported audio type: avi
    }

}
