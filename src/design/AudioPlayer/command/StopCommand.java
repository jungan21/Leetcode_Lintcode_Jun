package design.AudioPlayer.command;

import design.AudioPlayer.AudioPlayer;
import design.AudioPlayer.ICommand;

public class StopCommand implements ICommand {
	private AudioPlayer myAudio;

	public StopCommand(AudioPlayer audioPlayer) {
		myAudio = audioPlayer;
	}

	@Override
	public void execute() {
		myAudio.stop();
	}

}
