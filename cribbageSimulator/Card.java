package cribbageSimulator;

public class Card implements Comparable<Card>{
	//deck index is 0->51 card's index in the deck
	//ace -> king (Spades, Hearts, Diamonds, Clubs)
	private int deckIndex;
	//Spades (S), Hearts (H), Diamonds (D), CLubs (C)
	private char suit;
	//name is A,2,3,4,5...J,Q,K
	private char name;
	//index within suit 0->12 
	private int ordinal;
	//value of card (i.e. A = 1, K = 10)
	private int value;
	
	//Constructors
	//card[0] = deck index
	//card[1] = suit
	//card[2] = name
	//card[3] = ordinal
	//card[4] = value
	public Card(String[] card) {
		this.deckIndex = Integer.parseInt(card[0]);
		this.suit = (char) Integer.parseInt(card[1]);
		this.name = (char) Integer.parseInt(card[2]);
		this.ordinal = Integer.parseInt(card[3]);
		this.value = Integer.parseInt(card[4]);
	}
	
	public Card(int idx, int ordinal) {
		this.deckIndex = idx;
		this.ordinal = ordinal;
	}
	
	public Card() {
		//blank constructor
	}
	
	public int getOrdinal() {
		return this.ordinal;
	}
	
	public int getIndex() {
		return this.deckIndex;
	}
	
	public void setSuit(char suit) {
		this.suit = suit;
	}
	
	public char getSuit() {
		return this.suit;
	}
	
	public void setName(char name) {
		this.name = name;
	}
	
	public char getName() {
		return this.name;
	}
	
	public void setValue(int val) {
		this.value = val;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void printCard() {
		System.out.println("Card #: " + this.deckIndex + "\tSuit: " + this.suit 
				+ "\t\tName: " + this.name + "\nOrdinal: " + this.ordinal 
				+ "\tValue: " + this.value);
	}

	@Override
	public int compareTo(Card arg0) {
		if(this.ordinal > arg0.ordinal)
			return 1;
		else if(this.ordinal < arg0.ordinal)
			return -1;
		return 0;
	}
}
