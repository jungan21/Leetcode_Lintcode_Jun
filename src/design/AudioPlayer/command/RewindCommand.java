package design.AudioPlayer.command;

import design.AudioPlayer.AudioPlayer;
import design.AudioPlayer.ICommand;

public class RewindCommand implements ICommand {

	private AudioPlayer myAudio;

	public RewindCommand(AudioPlayer audioPlayer) {
		myAudio = audioPlayer;
	}

	@Override
	public void execute() {
		myAudio.rewind();
	}

}
