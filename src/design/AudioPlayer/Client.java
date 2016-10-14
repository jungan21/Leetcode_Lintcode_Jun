package design.AudioPlayer;

import design.AudioPlayer.command.PlayCommand;
import design.AudioPlayer.command.RewindCommand;
import design.AudioPlayer.command.StopCommand;

public class Client {

	public static void main(String[] args) {
		// 创建接收者对象
		AudioPlayer audioPlayer = new AudioPlayer();
		// 创建命令对象
		ICommand playCommand = new PlayCommand(audioPlayer);
		ICommand rewindCommand = new RewindCommand(audioPlayer);
		ICommand stopCommand = new StopCommand(audioPlayer);

		// 创建请求者对象
		Keypad keypad = new Keypad();
		keypad.setPlayCommand(playCommand);
		keypad.setRewindCommand(rewindCommand);
		keypad.setStopCommand(stopCommand);

		// 测试
		keypad.play();
		keypad.rewind();
		keypad.stop();
		keypad.play();
		keypad.stop();

	}

}
