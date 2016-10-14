package design.AudioPlayer;

/**
 * 命令接收者
 *
 * http://www.cnblogs.com/java-my-life/archive/2012/06/01/2526972.html
 */
public class AudioPlayer {
	public void play() {
		System.out.println("播放...");
	}

	public void rewind() {
		System.out.println("倒带...");
	}

	public void stop() {
		System.out.println("停止...");
	}
}
