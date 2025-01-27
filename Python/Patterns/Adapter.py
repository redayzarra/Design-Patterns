from abc import ABC, abstractmethod


# Create an Adapater Pattern: Define an interface that every device needs
class AudioDevice(ABC):
    @abstractmethod
    def playMusic(self):
        pass

    @abstractmethod
    def stopMusic(self):
        pass


# Define a concrete class that has it's own functions
class Speaker:

    def startPlayback(self):
        print("Speaker has started playing music.")

    def endPlayback(self):
        print("Speaker has stopped playing music.")

# Define a concrete class that has it's own functions
class Headphones:
    def beginAudio(self):
        print("Headphones have started playing audio.")
    
    def stopAudio(self):
        print("Headphones have stopped playing audio.")
        
        
# Define a concrete adapter class for the speakers
class SpeakerAdapter(AudioDevice):
    def __init__(self, speaker: Speaker):
        self.speaker = speaker
        
    def playMusic(self):
        self.speaker.startPlayback()
        
    def stopMusic(self):
        self.speaker.endPlayback()
        
# Define a concrete adapter class for the headphones
class HeadphonesAdapter(AudioDevice):
    def __init__(self, headphones: Headphones):
        self.headphones = headphones
        
    def playMusic(self):
        self.headphones.beginAudio()
        
    def stopMusic(self):
        self.headphones.stopAudio()
        

# Test the system
speaker = Speaker()
speaker_adapter = SpeakerAdapter(speaker)

headphones = Headphones()
headphones_adapter = HeadphonesAdapter(headphones)

adapters: list[AudioDevice] = [speaker_adapter, headphones_adapter]
for adapter in adapters:
    adapter.playMusic()
    adapter.stopMusic()
    
    