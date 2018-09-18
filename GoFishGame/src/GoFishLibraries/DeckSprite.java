/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GoFishLibraries;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ruhan
 */
public class DeckSprite extends JPanel
{
    
    public Deck deck;
    
    private final int top_x = 0;
    private final int top_y = 210;
    
    private final int PanelWidth = 1200;
    private final int PanelHeight = 380;
    
    public DeckSprite(Deck newDeck)
    {
        deck = newDeck;
        setLayout(null);
        setBounds(top_x, top_y, PanelWidth, PanelHeight);
        for(int IndexNumber = 0; IndexNumber < newDeck.Cards.size(); IndexNumber++)
        {
            CardSprite c = new CardSprite(deck.Cards.get(IndexNumber), 560 + IndexNumber * 7, top_y);
            c.DisplayBack();
            add(c);
        }
    }
    
    public void DisplayDeck()
    {
        removeAll();
        for(int IndexNumber = 0; IndexNumber < deck.Cards.size(); IndexNumber++)
        {
            CardSprite c = new CardSprite(deck.Cards.get(IndexNumber), 560 + IndexNumber * 7, top_y);
            c.DisplayBack();
            add(c);
        }
    }
}
