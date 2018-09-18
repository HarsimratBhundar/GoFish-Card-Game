package GoFishLibraries;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.WeakHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 *
 * @author Ruhan
 */
public class SmartCPUPlayer extends Player
{
    
    private WeakHashMap<Player, Card> Memory = new WeakHashMap();

        public boolean EntryExists(Player ArgumentPlayer, Card ArgumentCard)
        {
            if (Memory.containsKey(ArgumentPlayer) && Memory.get(ArgumentPlayer) == ArgumentCard)
            {
                    return true;
            }
            return false;
        }

        public void UpdateMemory(Player ArgumentPlayer, Card ArgumentCard)
        {
            if (EntryExists(ArgumentPlayer, ArgumentCard))
            {
                Memory.remove(ArgumentPlayer);
            }
            else
            {
                Memory.put(ArgumentPlayer, ArgumentCard);
            }
        }

        @Override
        public HashMap<Player, Card> DecideMove(ArrayList<Player> ArgumentPlayers)
        {
            HashMap<Player, Card> DecisionTarget = new HashMap();
            Player ChosenPlayer = null;
            Card ChosenCard = null;
            int ChosenPlayerIndex = 0;
            int ChosenPlayerID = 0;
            for (int PlayerNumber = 0; PlayerNumber < ArgumentPlayers.size(); PlayerNumber++)
            {
                for(int CardNumber = 0; CardNumber < Hand.Cards.size(); CardNumber++)
                {
                    if(EntryExists(ArgumentPlayers.get(PlayerNumber), Hand.Cards.get(CardNumber)));
                }
            }
            if (ChosenPlayer == null)
            {
                Random RandNum = new Random();
                do
                {
                    ChosenPlayerIndex = RandNum.nextInt(ArgumentPlayers.size() - 1);
                    ChosenPlayerID = ArgumentPlayers.get(ChosenPlayerIndex).ID;
                } while (ChosenPlayerID == this.ID);
                
                ChosenCard = Hand.Cards.get(RandNum.nextInt(Hand.Cards.size() - 1));
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
            DecisionTarget.put(ChosenPlayer, ChosenCard);
            return DecisionTarget;
        }
}
