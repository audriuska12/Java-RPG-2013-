import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class BGM implements Runnable {
	private final int BUFFER_SIZE = 256000;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	public String fileName;
	private SourceDataLine sourceLine;
	private DataLine.Info info;
	private byte[] abData;
	private int nBytesRead;

	public void run() {
		while (true) {
			try {
				audioStream = AudioSystem.getAudioInputStream(Battle.class
						.getResource(fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			audioFormat = audioStream.getFormat();
			info = new DataLine.Info(SourceDataLine.class,
					audioFormat);
			try {
				sourceLine = (SourceDataLine) AudioSystem.getLine(info);
				sourceLine.open(audioFormat);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sourceLine.start();
			nBytesRead = 0;
			abData = new byte[BUFFER_SIZE];
			while (nBytesRead != -1) {
				try {
					nBytesRead = audioStream.read(abData, 0, abData.length);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (nBytesRead >= 0) {
					@SuppressWarnings("unused")
					int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
				}
			}
		}
	}

	public void playFile(String fileName) {
		stop();
		Instances.backgroundMusic.fileName = fileName;
		Instances.backgroundMusic.audioFormat = null;
		Instances.backgroundMusic.audioStream = null;
		Instances.backgroundMusic.sourceLine = null;
		Instances.backgroundMusic.nBytesRead = 0;
		Instances.backgroundMusic.abData = new byte[BUFFER_SIZE];
		Instances.bgm = new Thread(Instances.backgroundMusic);
		Instances.bgm.start();
	}

	public void stop() {
		try {
			sourceLine.stop();
			sourceLine.drain();
			sourceLine.close();
			Instances.bgm.interrupt();
		} catch (Exception e) {
		}
	}
}
