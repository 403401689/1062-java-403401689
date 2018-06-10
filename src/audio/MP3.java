package audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3 {
    private String filename;
    private Player player; 
    private boolean loop=false;
    private FileInputStream fis;
    private BufferedInputStream bis;
    
    private static Thread audioThread;

    // constructor that takes the name of an MP3 file   
    public MP3() {
    }
    
    public MP3(String filename) {
        this.filename = filename;
    }

    public void setLoop(boolean loop) {
    	this.loop = loop;
    }
    
    public void stop() {
    	loop = false;
    	System.out.println("close "+loop);
    	audioThread.stop();
    }

    public void resume() {
    	if(audioThread !=null) audioThread.stop();
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	loop = true;
    	System.out.println("resume: "+loop);
    	play();
    }
    
    // play the MP3 file to the sound card
    public void play() {
        // run in new thread to play in background
        audioThread = new Thread(new Runnable() {
            public void run() {
            	do {
                    try {
                        fis = new FileInputStream(filename);
                        bis = new BufferedInputStream(fis);
                        player = new Player(bis);
                    }
                    catch (Exception e) {
                        System.out.println("Problem playing file " + filename);
                        System.out.println(e);
                    }

            		try { player.play(); }
                    catch (Exception e) { System.out.println(e); }            		
            	}while(loop);
            }
        });
        audioThread.start();
        
    }


    // test client
    public static void main(String[] args) {
        //String filename = args[0];
    	String filename = "resources/audio/game_music.mp3";
        MP3 mp3 = new MP3(filename);
        mp3.setLoop(false);
        mp3.play();

        // do whatever computation you like, while music plays
        int N = 4000;
        double sum = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += Math.sin(i + j);
            }
        }
        System.out.println(sum);

        // when the computation is done, stop playing it
        mp3.stop();
        System.out.println("mp3 stopped");
        
        // play from the beginning
//        mp3 = new MP3(filename);
//        mp3.play();

    }

}

