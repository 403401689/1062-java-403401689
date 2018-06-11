package Game_page;


import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public enum SoundEffect {
   HIT("sounds/hit.wav"),		// A Ball being hit
   SINK("sounds/sink.wav"),	// A Ball falling in a Pocket
   QUE("sounds/queue.wav");	// The queue Ball being hit.

   // Nested class for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.LOW;

   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;

   // Constructor to construct each element of the enum with its own sound file.
   SoundEffect(String soundFileName) {
      try {
    	  File dot = new File(".");
    	  System.out.println("current dir is: " + dot.getCanonicalPath() );
    	  
    	  String soundFileLocation = dot.getCanonicalPath() + "/" + soundFileName;
    	  File soundFile = new File( soundFileLocation );
    	  System.out.println("Sound file is: " + soundFile.getCanonicalPath() );
    	  if ( soundFile.exists() ) {
    		  System.out.println("File exists");
    		  if ( soundFile.canRead())  System.out.println("Can Read...");
    	  }
    	  
    	  Class cl = this.getClass();
    	  System.out.println("Class toString is:" + cl.toString());
    	  System.out.println("Class name is:" + cl.getCanonicalName());
    	  
         // Use URL (instead of File) to read from disk and JAR.
         URL url = this.getClass().getResource(soundFileName);
         //URL url = this.getClass().getClassLoader().getResource(soundFileLocation);
         
         System.out.println(" URL = " + url.getPath());
         
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( soundFile );
         
         System.out.println(" input stream is: " + audioInputStream.toString() );
         
         // Get a clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
      } 
      catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } 
      catch (IOException e) {
         e.printStackTrace();
      } 
      catch (LineUnavailableException e) {
         e.printStackTrace();
      }
      catch( Exception e ){
    	  System.out.println("ERROR!! " + e.getMessage());
    	  e.printStackTrace();
    	  System.exit(0);
      }
   }

   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void play() {
      if (volume != Volume.MUTE) {
         if (clip.isRunning())
            clip.stop();   // Stop the player if it is still running
         clip.setFramePosition(0); // rewind to the beginning
         clip.start();     // Start playing
      }
   }

   // Optional static method to pre-load all the sound files.
   static void init() {
      values(); // calls the constructor for all the elements
   }
}
