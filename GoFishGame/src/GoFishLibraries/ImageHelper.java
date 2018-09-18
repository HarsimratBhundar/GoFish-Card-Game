/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package GoFishLibraries;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ruhan
 */

public class ImageHelper 
{
    public BufferedImage GetFaceForCard(Card card) throws IOException
    {
        BufferedImage CardSet = ImageIO.read(new File("C:\\Users\\Ruhan\\Documents\\NetBeansProjects\\GoFishGame\\src\\images\\cardfaces.png"));
        
        int TopX = 0;
        int TopY = 0;
        
            if (card.Suit == Const.Suit.Clubs)
            {
                TopY = 0;
            }
            
            else if(card.Suit == Const.Suit.Spades) 
            {
                TopY = 98;
            }
            
            else if (card.Suit == Const.Suit.Hearts) 
            {
                TopY = 196;
            }
            else if (card.Suit == Const.Suit.Diamonds) 
            {
                TopY = 294;
            }        
        
            TopX = card.FaceValue.ordinal() * 73;
            
            BufferedImage CardFace = CardSet.getSubimage(TopX, TopY, 73, 97);
            
        return CardFace;
    }
    
    public BufferedImage GetBackForCard(Card card) throws IOException
    {
        return ImageIO.read(new File("C:\\Users\\Ruhan\\Documents\\NetBeansProjects\\GoFishGame\\src\\images\\card-back.jpg"));
    }
}
