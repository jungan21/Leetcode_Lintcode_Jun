package design.AudioPlayer.command;

import design.AudioPlayer.AudioPlayer;
import design.AudioPlayer.ICommand;

public class PlayCommand implements ICommand {
	private AudioPlayer myAudio;

	public PlayCommand(AudioPlayer audioPlayer) {
		myAudio = audioPlayer;
	}

	/**
	 * 执行方法
	 */
	@Override
	public void execute() {
		myAudio.play();
	}

}
