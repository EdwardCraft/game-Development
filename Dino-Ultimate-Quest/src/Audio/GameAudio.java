package Audio;
import javax.sound.sampled.*;


public class GameAudio{

	private Clip clip;
	
	public GameAudio(String audio) {
		
		try {
			
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream(
							audio
					)
				);

			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);

			AudioInputStream dais =
				AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}

		catch(Exception e) {
			e.printStackTrace();
		}


		
	}
	

	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}


	
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	


	public void close() {
		stop();
		clip.close();
	}


}