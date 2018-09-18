package GoFishLibraries;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ruhan
 */
public class CPUPlayer extends Player
{
    
    
    @Override
        public HashMap<Player, Card> DecideMove(ArrayList<Player> ArgumentPlayers)
        {
            HashMap<Player, Card> DecisionTarget = new HashMap();
            Random RandNum = new Random();
            Player ChosenPlayer = new CPUPlayer();
            Card ChosenCard;
            int ChosenPlayerID = 0;
            int ChosenPlayerIndex;
            do
            {
                ChosenPlayerIndex = RandNum.nextInt(ArgumentPlayers.size() - 1);
                ChosenPlayerID = ArgumentPlayers.get(ChosenPlayerIndex).ID;
            } while (ChosenPlayerID == this.ID);
            
            int ChosenCardIndex = RandNum.nextInt(Hand.Cards.size() - 1);
            ChosenCard = Hand.Cards.get(ChosenCardIndex);
            for(int PlayerNumber = 0; PlayerNumber < ArgumentPlayers.size(); PlayerNumber++)
            {
                if(ArgumentPlayers.get(PlayerNumber).ID == ChosenPlayerID)
                {
                    ChosenPlayer = ArgumentPlayers.get(PlayerNumber);
                }
            }
            DecisionTarget.put(ChosenPlayer, ChosenCard);
            return DecisionTarget;
        }
}
