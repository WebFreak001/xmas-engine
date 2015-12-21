package uk.co.nozzer.audio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

	private String path;
	private Clip clip;
	private AudioInputStream inputStream;
	
	public Audio(String path) {
		this.path = path;
		try {
			clip = AudioSystem.getClip();
			inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(path));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void play() {
		try {
			clip.open(inputStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}
}
