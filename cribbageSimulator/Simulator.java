package cribbageSimulator;

public class Simulator {
	//some lookup tables for card fields
	static char names[] = {'A',2,3,4,5,6,7,8,9,10,'J','Q','K'};
	static int values[] = { 1, 2,3,4,5,6,7,8,9,10, 10, 10, 10};
	static char suits[]  = {'S','H','D','C'};
	
	public static Card[] createDeck() {
		Card deck[] = new Card[52];
		int count = 0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<13; j++) {
				Card card = new Card(count, j);
				card.setName(names[j]);
				card.setSuit(suits[i]);
				card.setValue(values[j]);
				deck[count] = card;
				count++;
			}
		}
		return deck;
	}
	
	public static int scoreHand(Card card[]) {
		return 0;
	}

	public static void main(String[] args) {
		Card deck[];
		
		//generate game deck
		deck = createDeck();
		
		
	}

}
