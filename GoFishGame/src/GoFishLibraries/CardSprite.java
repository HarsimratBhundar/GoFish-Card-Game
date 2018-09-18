/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GoFishLibraries;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Ruhan
 */
public class CardSprite extends JButton
{
    private Card TargetCard;
    
    private int top_x;
    private int top_y;
    
    public Card GetCard()
    {
        return TargetCard;
    }
    
    public CardSprite(Card card, int x, int y)
    {
        TargetCard = card;
        ImageIcon img = new ImageIcon("C:\\Users\\Ruhan\\Documents\\NetBeansProjects\\GoFishGame\\src\\images\\card-back.jpg");
        
        top_x = x;
        top_y = y;
        
        this.setBounds(top_x, top_y, 73, 97);
        this.setIcon(img);
    }
    
    public void DisplayFace()
    {
        ImageHelper buffer = new ImageHelper();
        try 
        {
            ImageIcon img = new ImageIcon(buffer.GetFaceForCard(TargetCard));
            this.setIcon(img);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(CardSprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DisplayBack()
    {
        ImageHelper buffer = new ImageHelper();
                try 
        {
            ImageIcon img = new ImageIcon(buffer.GetBackForCard(TargetCard));
            this.setIcon(img);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(CardSprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
