/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wargame;

import java.util.ArrayList;

/**
 *
 * @author Dalim
 */
public class WarGame {

    private ArrayList<Card> p1Hand;
    private ArrayList<Card> p2Hand;    
    
    public WarGame()
    {
        p1Hand = new ArrayList<>();
        p2Hand = new ArrayList<>();
        
        DeckOfCards theDeck = new DeckOfCards();
        theDeck.shuffle();
        
        dealTheCards(theDeck);
    }
    
    /**
     * This method will "deal" all of the cards to the players 
     */
    private void dealTheCards(DeckOfCards theDeck)
    {
        for (int cardNum=0; cardNum <52; cardNum++)
        {
            if (cardNum % 2 == 0)
                p1Hand.add(theDeck.dealTopCard());
            else
                p2Hand.add(theDeck.dealTopCard());
        }
    }
    
    /**
     * his method will simulate playing the game
      */
    
    public void playTheGame()
    {
        int count = 0;
        while(!p1Hand.isEmpty() && !p2Hand.isEmpty())
        {
            count++;
            playHand();
        }
        
        if(p1Hand.isEmpty())
            System.out.println("Player 2 is the winner " + count);
        else
            System.out.println("Player Juan is the winner " + count);
    }
    
    /**
     * This method will simulate playing 1 hand at the game of war 
     */
    private void playHand()
    {
        Card p1Card = p1Hand.remove(0);
        Card p2Card = p2Hand.remove(0);
        
        System.out.printf("Player 1: %s cards in hand: %d", p1Card, p1Hand.size()+1);
        System.out.printf("\tPlayer 2: %s cards in hand: %d%n", p2Card, p2Hand.size()+1);
        
        if(p1Card.getFaceValue() > p2Card.getFaceValue())
        {
            p1Hand.add(p1Card);
            p1Hand.add(p2Card);
        }
        else if(p2Card.getFaceValue() > p1Card.getFaceValue())
        {
            p2Hand.add(p1Card);
            p2Hand.add(p2Card);
        }
        else
        {
            ArrayList<Card> warPile = new ArrayList<>();
            warPile.add(p1Card);
            warPile.add(p2Card);
            playWarHand(warPile);
        }
    }
    /**
     * This method will simulate a fullout WAR
     */
    private void playWarHand(ArrayList<Card> warPile)
    {
        if(p1Hand.size() < 3)
        {
            p2Hand.addAll(p1Hand);
            p1Hand.clear();
            p2Hand.addAll(warPile);
            return;
        }
        
        if(p2Hand.size() < 3)
        {
            p1Hand.addAll(p2Hand);
            p2Hand.clear();
            p1Hand.addAll(warPile);
            return;
        }
        
        warPile.add(p1Hand.remove(0));
        warPile.add(p1Hand.remove(0));
        warPile.add(p2Hand.remove(0));
        warPile.add(p2Hand.remove(0));
        
        Card p1Card = p1Hand.remove(0);
        Card p2Card = p1Hand.remove(0);        
        
        if(p1Card.getFaceValue() > p2Card.getFaceValue())
        {
            p1Hand.addAll(warPile);
            p1Hand.add(p1Card);
            p1Hand.add(p2Card);
        }
        else if(p2Card.getFaceValue() > p1Card.getFaceValue())
        {
            p2Hand.addAll(warPile);
            p2Hand.add(p1Card);
            p2Hand.add(p2Card);
        }
        else
        {
            warPile.add(p1Card);
            warPile.add(p2Card);
            this.playWarHand(warPile);
        }
    }
    
    public static void main(String[] args)
    {
        WarGame theGame = new WarGame();
        theGame.playTheGame();
    }
}
