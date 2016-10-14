package design.card;

public class BlackJackCard extends Card {

	public BlackJackCard(int c, Suit s) {
		super(c, s);
	}

	public int value() {
		if (isAce()) { // Ace
			return 1;
		} else if (faceValue >= 11 && faceValue <= 13) { // Face card
			return 10;
		} else { // Number card
			return faceValue;
		}
	}

	public int minValue() {
		if (isAce()) { // Ace
			return 1;
		} else {
			return value();
		}
	}

	public int maxValue() {
		if (isAce()) { // Ace
			return 11;
		} else {
			return value();
		}
	}

	public boolean isAce() {
		return faceValue == 1;
	}
	// facecard means J Q K
	public boolean isFaceCard() {
		return faceValue >= 11 && faceValue <= 13;
	}

}
