/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GoFishLibraries;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ruhan
 */
public class HumanPlayer extends Player
{
    public Card cardChosen;
    public boolean HasChosenCard;

    @Override
    public HashMap<Player, Card> DecideMove(ArrayList<Player> ArgumentPlayers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
