package uk.co.nozzer.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	private String resource;

	public Audio(String resource) {
		this.resource = resource;
	}

	public void play() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					InputStream audioSrc = getClass().getResourceAsStream(Audio.this.resource);
					InputStream bufferedIn = new BufferedInputStream(audioSrc);
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
					clip.open(audioStream);
					clip.start();
				} catch (LineUnavailableException exception) {
					exception.printStackTrace();
				} catch (IOException exception) {
					exception.printStackTrace();
				} catch (UnsupportedAudioFileException exception) {
					exception.printStackTrace();
				}
			}
		}).start();
	}
}
