package GoFishLibraries;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
/**
 *
 * @author Ruhan
 */
public class Deck 
{
        public ArrayList<Card> Cards = new ArrayList<>();
        private Const.Suit[] Suits = new Const.Suit[] { Const.Suit.Clubs, Const.Suit.Diamonds, Const.Suit.Hearts, Const.Suit.Spades };
        private Const.FaceValue[] FaceValues = new Const.FaceValue[] { Const.FaceValue.Ace, Const.FaceValue.Two, Const.FaceValue.Three, Const.FaceValue.Four, Const.FaceValue.Five, Const.FaceValue.Six, Const.FaceValue.Seven, Const.FaceValue.Eight, Const.FaceValue.Nine, Const.FaceValue.Ten, Const.FaceValue.Jack, Const.FaceValue.Queen, Const.FaceValue.King };
        
        public Deck()
        {
            for(int SuitNumber = 0; SuitNumber < Suits.length; SuitNumber++)
            {
                for(int FaceValueNumber = 0; FaceValueNumber < FaceValues.length; FaceValueNumber++)
                {
                    Cards.add(new Card(FaceValues[FaceValueNumber], Suits[SuitNumber]));
                }
            }
        }

        public boolean IsEmpty()
        {
            if (Cards.isEmpty())
            {
                return true;
            }
            return false;
        }    
}
