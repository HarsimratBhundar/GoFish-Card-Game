package GoFishLibraries;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.String;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
/**
 *
 * @author Ruhan
 */
public abstract class Player 
{
    public String Name;
    public int ID;
    
    public boolean IsInTurn;
    public int Score;
    
    public Hand Hand = new Hand();
    
    public ArrayList<Card> Books = new ArrayList<>();
    
        public void CollectCard(Card ArgumentCard)
        {
            Hand.Cards.add(ArgumentCard);
        }

        public void RemoveCard(Card ArgumentCard)
        {
            Hand.Cards.remove(ArgumentCard);
        }

        public boolean HandleBooks()
        {
            boolean BooksWereMade = false;
            ArrayList<Const.FaceValue> FacevaluesOfBooks = new ArrayList<>();
            for (int i = 0; i < Hand.Cards.size() - 1; i++)
            { 
               if(!FacevaluesOfBooks.contains(Hand.Cards.get(i).FaceValue))
               {
                int Iterator = 1;
                for(int k = i + 1; k < Hand.Cards.size(); k++)
                {
                    if (Hand.Cards.get(i).FaceValue == Hand.Cards.get(k).FaceValue)
                    {
                            Iterator++;
                    }
                    if (Iterator == 4)
                    {
                        FacevaluesOfBooks.add(Hand.Cards.get(i).FaceValue);
                        BooksWereMade = true;
                    }
                }
               }
               
            }
            for(int FaceValueNumber = 0; FaceValueNumber < FacevaluesOfBooks.size(); FaceValueNumber++)
            {
                for(int CardNumber = 0; CardNumber < Hand.Cards.size(); CardNumber++)
                {
                    if (Hand.Cards.get(CardNumber).FaceValue == FacevaluesOfBooks.get(FaceValueNumber))
                    {
                        Books.add(Hand.Cards.get(CardNumber));
                    }
                }
            }
            for(int CardNumber = 0; CardNumber < Books.size(); CardNumber++)
            {
                RemoveCard(Books.get(CardNumber));
            }
            return BooksWereMade;
        }

        public void DrawCard(Deck ArgumentDeck)
        {
            Random RandNum = new Random();
            int CardIndex = RandNum.nextInt(ArgumentDeck.Cards.size() - 1);
            Hand.Cards.add(ArgumentDeck.Cards.get(CardIndex));
            ArgumentDeck.Cards.remove(CardIndex);
        }

        public boolean CardExists(Card ArgumentCard)
        {
            boolean CardExists = false;
            for(int CardNumber = 0; CardNumber < Hand.Cards.size(); CardNumber++)
            {
                if (Hand.Cards.get(CardNumber) == ArgumentCard)
                {
                    CardExists = true;
                    break;
                }
            }
            return CardExists;
        }
        
        public boolean FaceValueExists(Const.FaceValue faceVal)
        {
            for(int CardNumber = 0; CardNumber < Hand.Cards.size(); CardNumber++)
            {
                if (Hand.Cards.get(CardNumber).FaceValue == faceVal)
                {
                    return true;
                }
            }
            return false;
        }

        
        public abstract HashMap<Player, Card> DecideMove(ArrayList<Player> ArgumentPlayers);

        public boolean HandIsEmpty()
        {
            if (Hand.Cards.isEmpty())
            {
                return true;
            }
            return false;
        }
}
