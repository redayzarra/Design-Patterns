import java.util.ArrayList;
import java.util.List;

// Create an interface for every audio device
interface AudioDevice {
    void playMusic();
    void stopMusic();
}

// Define a concrete class with unique methods
class Speaker {
    public void startPlayback() {
        System.out.println("Speaker has started playing music.");
    }

    public void endPlayback() {
        System.out.println("Speaker has stopped playing music.");
    }
}

class Headphones {
    public void beginAudio() {
        System.out.println("Headphones have started playing audio.");
    }

    public void stopAudio() {
        System.out.println("Headphones have stopped playing audio.");
    }
}

// Create an adapter for Speakers which allows them to be compatible
class SpeakerAdapter implements AudioDevice {
    private final Speaker speaker;

    public SpeakerAdapter(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void playMusic() {
        speaker.startPlayback();
    }

    @Override
    public void stopMusic() {
        speaker.endPlayback();
    }
}

// Create an adapter for headphones that make them compatible
class HeadphonesAdapter implements AudioDevice {
    private final Headphones headphones;

    public HeadphonesAdapter(Headphones headphones) {
        this.headphones = headphones;
    }

    @Override
    public void playMusic() {
        headphones.beginAudio();
    }

    @Override
    public void stopMusic() {
        headphones.stopAudio();
    }
}

// Test the design pattern
public class Adapter {
    public static void main(String[] args) {
        // Create Speaker and its adapter
        Speaker speaker = new Speaker();
        AudioDevice speakerAdapter = new SpeakerAdapter(speaker);

        // Create Headphones and its adapter
        Headphones headphones = new Headphones();
        AudioDevice headphonesAdapter = new HeadphonesAdapter(headphones);

        // Use the adapters to play and stop music
        List<AudioDevice> devices = new ArrayList<>();
        devices.add(speakerAdapter);
        devices.add(headphonesAdapter);

        for (AudioDevice device : devices) {
            device.playMusic();
            device.stopMusic();
        }
    }
}
