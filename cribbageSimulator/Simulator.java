package cribbageSimulator;

import java.util.ArrayList;
import java.util.Arrays;

public class Simulator {
	//some lookup tables for card fields
	static char names[] = {'A','2','3','4','5','6','7','8','9','t','J','Q','K'};
	static int values[] = { 1, 2,3,4,5,6,7,8,9,10, 10, 10, 10};
	static char suits[]  = {'S','H','D','C'};
	//arrays of indices for scoring hands
	static int setsOf2[][] = { {0,1}, {0,2}, {0,3}, {0,4}, {1,2}, {1,3}, {1,4}, {2,3}, {2,4}, {3,4} };
	static int setsOf3[][] = { {0,1,2}, {0,1,3}, {0,1,4}, {0,2,3}, {0,2,4}, {0,3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {2,3,4} };
	static int setsOf4[][] = { {0,1,2,3}, {0,1,2,4}, {0,1,3,4}, {0,2,3,4}, {1,2,3,4} };
	static int allSets[][][] = { setsOf2, setsOf3, setsOf4 };
	static Card cutCard;
	
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
	
	public static int scoreHand(Card hand[]) {
		//TODO
		ArrayList<Card[]> scoring_hands = new ArrayList<Card[]>();
		Card hand_to_add[] = new Card[5];
		int index = 0;
		int sum15 = 0;
		int score = 0;
		boolean flush = true;
		
		//score pairs
		for(int i=0; i<setsOf2.length; i++) {
			if(hand[setsOf2[i][0]] == hand[setsOf2[i][1]]) {
				hand_to_add[0] = hand[setsOf2[i][0]];
				hand_to_add[1] = hand[setsOf2[i][1]];
				scoring_hands.add(hand_to_add);
				score += 2;
			}
		}
		
		//score 3 of a kinds
		for(int i=0; i<setsOf3.length; i++) {
			if(hand[setsOf3[i][0]] == hand[setsOf3[i][1]] && hand[setsOf3[i][1]] == hand[setsOf3[i][2]]) {
				hand_to_add[0] = hand[setsOf3[i][0]];
				hand_to_add[1] = hand[setsOf3[i][1]];
				hand_to_add[2] = hand[setsOf3[i][2]];
				scoring_hands.add(hand_to_add);
				score += 6;
			}
		}
		
		//score 4 of a kinds
		for(int i=0; i<setsOf4.length; i++) {
			if(hand[setsOf4[i][0]] == hand[setsOf4[i][1]] 
					&& hand[setsOf4[i][2]] == hand[setsOf4[i][3]]
					&& hand[setsOf4[i][1]] == hand[setsOf4[i][2]]) {
				hand_to_add[0] = hand[setsOf4[i][0]];
				hand_to_add[1] = hand[setsOf4[i][1]];
				hand_to_add[2] = hand[setsOf4[i][2]];
				hand_to_add[3] = hand[setsOf4[i][3]];
				scoring_hands.add(hand_to_add);
				score += 12;
			}
		}
		
		//score 15s
		for(int i=0; i<allSets.length; i++) {
			index = 0;
			sum15 = 0;
			for(int j=0; j<allSets[i].length; j++) {
				for(int k=0; k<allSets[i][j].length; k++) {
					sum15 += hand[allSets[i][j][k]].getValue();
					hand_to_add[index] = hand[allSets[i][j][k]];
					index++;
				}
				if(sum15 == 15) {
					scoring_hands.add(hand_to_add);
					score += 2;
					//reset the hand to add and index for next hand
					for(int s=index; s>0; s--)
						hand_to_add[s] = null;
					index = 0;
				}
			}
		}
		
		//score flushes
		for(int i=1; i<hand.length; i++) {
			if(hand[i].getSuit() != hand[i-1].getSuit()) {
				flush = false;
				break;
			}
		}
		//if we reach the end of the loop and flush is still true
		if(flush) {
			scoring_hands.add(hand);
			score += 4;
			//get 1 more point if match cut card too
			if(hand[0].getSuit() == cutCard.getSuit())
				score++;
		}
		
		//score runs
		//sort the hand first
		Arrays.sort(hand);
		
		//now loop and look for runs of 5, then 4, then 3.
		//if we find a longer run we can stop right away (given there are no
		//other runs of equal length)
		//runs are determined using the Ordinal value of a card

		
		//score nobs
		for(int i=0; i<hand.length; i++) {
			if(hand[i].getName() == 'J' && cutCard.getSuit() == hand[i].getSuit()) {
				hand_to_add[0] = hand[i];
				hand_to_add[1] = cutCard;
				scoring_hands.add(hand_to_add);
				score++;
			}
		}
		
		return score;
	}
	
	public static Card[][] dealCards() {
		Card hands[][] = new Card[2][5];
		//TODO
		// ...code...
		//assign cutCard here
		return hands;
	}

	public static void main(String[] args) {
		Card deck[];
		Card hands[][];
		int p1_score = 0;
		int p2_score = 0;
		
		//generate game deck
		deck = createDeck();
		
		int c=0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<13; j++) {
				deck[c].printCard();
				c++;
			}
			System.out.println("\n");
		}
		
		//deal player hands (2 players for now)
		//hands = dealCards();
		
		//score hands
		//p1_score = scoreHand(hands[0]);
		//p2_score = scoreHand(hands[1]);
	}

}
