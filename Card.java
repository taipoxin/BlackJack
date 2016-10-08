

import java.util.*;

/**
 * @author Dmitry Ermakovich
 */

interface Cards {
    enum  SUITS {Heart, Diamond, Club, Spade}

    HashMap<String, Integer> cardsvalue = new HashMap<>();

    SUITS getSuit();
    String getFace();
    Integer getScore();
    boolean getACE();



}

class Card implements Cards {
    private static Random random = new Random();
    private SUITS suit;
    private String face;
    private Integer score;
    private boolean ACE = false;

    public SUITS getSuit() {
        return suit;
    }

    public String getFace() {
        return face;
    }

    public Integer getScore() {
        return score;
    }

    public boolean getACE() {return ACE;}

    private void CardsvalueFiller() {
        cardsvalue.put("2", 2);
        cardsvalue.put("3", 3);
        cardsvalue.put("4", 4);
        cardsvalue.put("5", 5);
        cardsvalue.put("6", 6);
        cardsvalue.put("7", 7);
        cardsvalue.put("8", 8);
        cardsvalue.put("9", 9);
        cardsvalue.put("10", 10);
        cardsvalue.put("Jack", 10);
        cardsvalue.put("Queen", 10);
        cardsvalue.put("King", 10);
        cardsvalue.put("Ace", 11);
    }

    // Constructor
    public Card() {

        CardsvalueFiller();

        suit = SUITS.values()[random.nextInt(4)];
        face = (String) cardsvalue.keySet().toArray()[random.nextInt(13)];
        score = cardsvalue.get(face);

        if (face.equals("Ace"))
            ACE = true;

    }

}
