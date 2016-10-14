package design.AudioPlayer;

/**
 * command invoker 角色
 *
 */
public class Keypad {
	private ICommand playCommand;
	private ICommand rewindCommand;
	private ICommand stopCommand;

	public void setPlayCommand(ICommand playCommand) {
		this.playCommand = playCommand;
	}

	public void setRewindCommand(ICommand rewindCommand) {
		this.rewindCommand = rewindCommand;
	}

	public void setStopCommand(ICommand stopCommand) {
		this.stopCommand = stopCommand;
	}

	/**
	 * 执行播放方法
	 */
	public void play() {
		playCommand.execute();
	}

	/**
	 * 执行倒带方法
	 */
	public void rewind() {
		rewindCommand.execute();
	}

	/**
	 * 执行播放方法
	 */
	public void stop() {
		stopCommand.execute();
	}
}
