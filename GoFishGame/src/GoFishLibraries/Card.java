package GoFishLibraries;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ruhan
 */
public class Card 
{
        public Card(Const.FaceValue ArgumentFaceValue, Const.Suit ArgumentSuit)
        {
            FaceValue = ArgumentFaceValue;
            Suit = ArgumentSuit;
        }

        public Const.FaceValue FaceValue;
        public Const.Suit Suit;    
}
