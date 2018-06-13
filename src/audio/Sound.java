package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	public static synchronized void play(int fileName) {
		final int hit = 0;
		final int sink = 1;
		final int game_music = 2;
		
		String[] address;
		address = new String [3];
		address[hit] = "resources/audio/hit.wav";
		address[sink] = "resources/audio/sink.wav";
		address[game_music] = "resources/audio/test.wav";
		
		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputstream = AudioSystem.getAudioInputStream(new File(address[fileName]));
					clip.open(inputstream);
					clip.start();
					
				}catch(Exception e) {
					System.out.println("play ERROR");
				}
				
			}
		}).start();
		
		

	}
	

}
