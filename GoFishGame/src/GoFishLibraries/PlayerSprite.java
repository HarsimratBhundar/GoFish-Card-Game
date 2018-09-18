/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GoFishLibraries;

import java.awt.Component;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Ruhan
 */
public class PlayerSprite extends JPanel implements MouseInputListener
{
    
    public Card CardChosen;
    public boolean CardHasBeenChosen;
    
    public Player ThisPlayer;
    private JLabel ScoreBox;
    private JLabel PlayerName;
    
    private int top_x;
    private int top_y;
    
    private final int PanelWidth = 1180;
    private final int PanelHeight = 200;
    
    
    public PlayerSprite(Player player)
    {
        setLayout(null);
        ThisPlayer = player;
        
        if(player.ID == 0)
        {
            top_x = 20;
            top_y = 10;
            
            setBounds(top_x, top_y, PanelWidth, PanelHeight);
            ScoreBox = new JLabel("Score: ");
            ScoreBox.setBounds(top_x, top_y, 100, 30);
            PlayerName = new JLabel("Name: " + ThisPlayer.Name);
            PlayerName.setBounds(top_x + 100, top_y, 100, 30);
            add(ScoreBox);
            add(PlayerName);
        }
        
        else if(player.ID == 1)
        {
            top_x = 20;
            top_y = 590;
            
            setBounds(top_x, top_y, PanelWidth, PanelHeight);
            ScoreBox = new JLabel("Score: ");
            ScoreBox.setBounds(top_x , top_y, 100, 30);
            PlayerName = new JLabel("Name: " + ThisPlayer.Name);
            PlayerName.setBounds(top_x + 100, top_y, 100, 30);
            add(ScoreBox);
            add(PlayerName);
        }
    }

    public void DisplayHand()
    {
        removeAll();
        add(ScoreBox);
        add(PlayerName);
        if(ThisPlayer.ID == 0)
        {
            for(int IndexNumber = ThisPlayer.Hand.Cards.size() - 1; IndexNumber >= 0; IndexNumber--)
            {
                    CardSprite c = new CardSprite(ThisPlayer.Hand.Cards.get(IndexNumber), top_x + 20 + (IndexNumber * 15), top_y + 25);
                    c.DisplayBack();
                    add(c);
            }
        }
        else
        {
            for(int IndexNumber = ThisPlayer.Hand.Cards.size() - 1; IndexNumber >= 0; IndexNumber--)
            {
                    CardSprite c = new CardSprite(ThisPlayer.Hand.Cards.get(IndexNumber), top_x + 20 + (IndexNumber * 15), top_y + 25);
                    c.DisplayFace();
                    add(c);
            }
        }
        
        for(int IndexNumber = 0; IndexNumber < ThisPlayer.Books.size(); IndexNumber++)
        {
            CardSprite c = new CardSprite(ThisPlayer.Hand.Cards.get(IndexNumber), 1120, top_y + 25);
            c.DisplayBack();
            add(c);
        }
    }
        
    public void IncrementScore()
    {
        ThisPlayer.Score++;
        ScoreBox.setText("Score: " + ThisPlayer.Score);
    }
    
    public void DrawCard(DeckSprite decksprite)
    {
        ThisPlayer.DrawCard(decksprite.deck);
    }
    

    
    
        
    public void HandleBooks()
    {
        
        boolean bookMade = ThisPlayer.HandleBooks();
        int lastBookIndex = ThisPlayer.Books.size() - 1;

        if(bookMade)
        {
            IncrementScore();
            for(Component C : this.getComponents())
            {
                if(C instanceof CardSprite)
                {
                    Card card = ((CardSprite)C).GetCard();
                    if(card.FaceValue == ThisPlayer.Books.get(lastBookIndex).FaceValue)
                    {
                        ((CardSprite)C).DisplayBack();
                        int top_x = C.getX();
                        int top_y = C.getY();
                        
                        while(top_x < 1120)
                        {
                            top_x++;
                            try
                            {
                                Thread.sleep(5);
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                            }
                            C.setLocation(top_x, top_y);
                        }       
                    }
                }    
            }
        }
    }    

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        if(ThisPlayer.IsInTurn)
        {
            for(Component C : this.getComponents())
            {
                if(C instanceof CardSprite)
                {
                    CardChosen = ((CardSprite)C).GetCard();
                    CardHasBeenChosen = true;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
