/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GoFishLibraries.CPUPlayer;
import GoFishLibraries.Card;
import GoFishLibraries.CardSprite;
import GoFishLibraries.Const;
import GoFishLibraries.Deck;
import GoFishLibraries.DeckSprite;
import GoFishLibraries.HumanPlayer;
import GoFishLibraries.Player;
import GoFishLibraries.PlayerSprite;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ruhan
 */
public class Main extends JFrame implements Runnable
{
    private MouseAdapter m = new MouseAdapter()
    {
        @Override
        public void mouseClicked(MouseEvent me) 
        {
            System.out.println("1");
            if(me.getComponent() instanceof CardSprite)
            {
                System.out.println("3");
                if(HPlayer.IsInTurn)
                {
                    if(HPlayer.CardExists(((CardSprite)me.getComponent()).GetCard()))
                    {
                        
                        HPlayer.cardChosen = ((CardSprite)me.getComponent()).GetCard();
                        System.out.println(HPlayer.cardChosen.FaceValue.toString());
                        //ShowMove(HPlayer);
                    }
                }
                else
                {
                }
            }
        }
    };
    
    
    private DeckSprite deckSprite;
    
    private PlayerSprite CPlayerSprite;
    private PlayerSprite HPlayerSprite;
    
    
    private final int Frame_x = 1200;
    
    private final int Frame_y = 800;
    
    private JFrame MainFrame;
    private JPanel ContentPane;
    
    private Deck deck;
    
    private CPUPlayer CPlayer;
    
    private HumanPlayer HPlayer;
    
    private JLabel CPUScoreBox;
    
    private JLabel HumanScoreBox;
    
    
    public Main()
    {
    }
    
    public void InitFrame()
    {
        MainFrame = new JFrame();
        MainFrame.setSize(Frame_x, Frame_y);
        MainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ContentPane = new JPanel();
        ContentPane.setBounds(0, 0, Frame_x, Frame_y);
        ContentPane.setLayout(null);
        MainFrame.setVisible(true);
        MainFrame.add(ContentPane);
        //ContentPane.addMouseListener(m);
    }
        
    public void InitGame()
    {
        InitPlayers();
        InitDeck();
        InitCards();
    }
    
    public void InitCards()
    {
        for(Component C : ContentPane.getComponents())
        {
            if(C instanceof CardSprite)
            {
                C.addMouseListener(m);
            }
        }
    }
    
    public void InitDeck()
    {
        for(int IndexNumber = 0; IndexNumber < deck.Cards.size(); IndexNumber++)
        {
            CardSprite c = new CardSprite(deck.Cards.get(IndexNumber), 560 + IndexNumber * 7, 350);
            c.DisplayBack();
            ContentPane.add(c);
        }        
    }
    
    public void InitPlayers()
    {
        final int InitalCardCount = 7;
        
        deck = new Deck();
        
        CPlayer = new CPUPlayer();
        CPlayer.ID = 0;
        CPlayer.Name = "CPU";
        
        HPlayer = new HumanPlayer();
        HPlayer.ID = 1;
        HPlayer.Name = "H";
        
        
        for(int CardNumber = 0; CardNumber < InitalCardCount; CardNumber++)
        {
            CPlayer.DrawCard(deck);
            HPlayer.DrawCard(deck);
        }
        
        
        JLabel CPUNameBox = new JLabel(CPlayer.Name);
        JLabel HumanNameBox = new JLabel(HPlayer.Name);
        CPUNameBox.setBounds(20, 10, 100, 30);
        HumanNameBox.setBounds(20, 590, 100, 30);
        
        CPUScoreBox = new JLabel("Score: 0");
        HumanScoreBox = new JLabel("Score: 0");
        CPUScoreBox.setBounds(120, 10, 100, 30);
        HumanScoreBox.setBounds(120, 590, 100, 30);
        
        ContentPane.add(CPUNameBox);
        ContentPane.add(HumanNameBox);
        ContentPane.add(CPUScoreBox);
        ContentPane.add(HumanScoreBox);
        
        CPlayer.IsInTurn = true;
        
        DisplayHand(CPlayer);
        DisplayHand(HPlayer);
    }
    
    public void RunGame()
    {
        HandleBooks(CPlayer);
        HandleBooks(HPlayer);
        
        while(!GameHasEnded())
        {
            if(CPlayer.IsInTurn)
            {
                ShowMove(CPlayer);
            }
            if(HPlayer.cardChosen != null)
            {
                ShowMove(HPlayer);
            }
        }
        Player winner;
        if(CPlayer.Score > HPlayer.Score)
        {
            winner = CPlayer;
        }
        else
        {
            winner = HPlayer;
        }
    }
    
    public void DisplayHand(Player p)
    {
        if(p instanceof CPUPlayer)
        {
            for(int IndexNumber = p.Hand.Cards.size() - 1; IndexNumber >= 0; IndexNumber--)
            {
                    CardSprite c = new CardSprite(p.Hand.Cards.get(IndexNumber), 40 + (IndexNumber * 15), 45);
                    c.DisplayBack();
                    ContentPane.add(c);
            }            
        }
        
        else if (p instanceof HumanPlayer)
        {
            for(int IndexNumber = p.Hand.Cards.size() - 1; IndexNumber >= 0; IndexNumber--)
            {
                    CardSprite c = new CardSprite(p.Hand.Cards.get(IndexNumber), 40 + (IndexNumber * 15), 615);
                    c.DisplayFace();
                    ContentPane.add(c);
            }            
        }
    }
    
    public void RearrangeHand(Player p)
    {
        for(int IndexNumber = 0; IndexNumber < p.Hand.Cards.size(); IndexNumber++)
        {
            for(Component C : ContentPane.getComponents())
            {
                if(C instanceof CardSprite)
                {
                    if(((CardSprite)C).GetCard() == p.Hand.Cards.get(IndexNumber))
                    {
                        int top_x = C.getX();
                        int top_y = C.getY();
                        
                        while(top_x < 40 + (IndexNumber * 15))
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
                        while(top_x > 40 + (IndexNumber * 15))
                        {
                            top_x--;
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
    
    public void HandleBooks(Player p)
    {
        boolean bookMade = p.HandleBooks();
        int lastBookIndex = p.Books.size() - 1;
        
        if(bookMade)
        {
            p.Score++;
            if(p instanceof CPUPlayer)
            {
                CPUScoreBox.setText("Score: " + CPlayer.Score);
            }

            else if (p instanceof HumanPlayer)
            {
                HumanScoreBox.setText("Score: " + HPlayer.Score);
            }
            
            for(int IndexNumber = lastBookIndex - 3; IndexNumber <= lastBookIndex; IndexNumber++)
            {
                for(Component C : ContentPane.getComponents())
                {
                    if(C instanceof CardSprite)
                    {
                        if(((CardSprite)C).GetCard() == p.Books.get(IndexNumber))
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
            RearrangeHand(p);
        }
    }
    
    public void DrawCard(Player p)
    {
            p.DrawCard(deck);
            Card target = p.Hand.Cards.get(p.Hand.Cards.size() - 1);
            for(Component C : ContentPane.getComponents())
            {
                if(C instanceof CardSprite)
                {
                    if(((CardSprite)C).GetCard() == target)
                    {
                        CardSprite c = new CardSprite(((CardSprite)C).GetCard(), C.getX(), C.getY());
                        ContentPane.remove(C);
                        ContentPane.add(c);
                        int top_x = c.getX();
                        int top_y = c.getY();
                        c.DisplayBack();                        
                        
                        int target_y = 45;
                        if(p instanceof HumanPlayer)
                        {
                            c.DisplayFace();
                            target_y = 615;
                        }
                        
                        while(top_x != ((p.Hand.Cards.size() - 1) * 15) + 40)
                        {
                            if(top_x < ((p.Hand.Cards.size() - 1) * 15) + 40)
                            {
                                top_x++;
                            }
                            else
                            {
                                top_x--;
                            }
                            try
                            {
                                Thread.sleep(5);
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                            }
                            c.setLocation(top_x, top_y);
                        }
                        while(top_y != target_y)
                        {
                            if(p instanceof HumanPlayer)
                            {
                                top_y++;
                            }
                            else
                            {
                                top_y--;
                            }
                            try
                            {
                                Thread.sleep(5);
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                            }                            
                            c.setLocation(top_x, top_y);
                        }
                        c.addMouseListener(m);
                    }
                }
            }
    }
    
    public void ShowMove(Player p)
    {
        if(p instanceof CPUPlayer)
        {
            Random randNum = new Random();
            Card cardChosen = CPlayer.Hand.Cards.get(randNum.nextInt(CPlayer.Hand.Cards.size()));
            if(HPlayer.FaceValueExists(cardChosen.FaceValue))
            {
                int CardsCollected = 0;
                for(int IndexNumber = 0; IndexNumber < HPlayer.Hand.Cards.size(); IndexNumber++)
                {
                    if(HPlayer.Hand.Cards.get(IndexNumber).FaceValue == cardChosen.FaceValue)
                    {
                        CPlayer.CollectCard(HPlayer.Hand.Cards.get(IndexNumber));
                        for(Component C : ContentPane.getComponents())
                        {
                            if(C instanceof CardSprite)
                            {
                                if(((CardSprite)C).GetCard() == HPlayer.Hand.Cards.get(IndexNumber))
                                {
                                    CardSprite c = new CardSprite(((CardSprite)C).GetCard(), C.getX(), C.getY());
                                    ContentPane.remove(C);
                                    ContentPane.add(c);
                                    int top_x = c.getX();
                                    int top_y = c.getY();
                                    c.DisplayBack();
                                    
                                    while(top_x != ((CPlayer.Hand.Cards.size() - 1) * 15) + 40)
                                    {
                                        if(top_x < ((CPlayer.Hand.Cards.size() - 1) * 15) + 40)
                                        {
                                            top_x++;
                                        }
                                        else
                                        {
                                            top_x--;
                                        }
                                        try
                                        {
                                            Thread.sleep(5);
                                        }
                                        catch(Exception e)
                                        {
                                            System.out.println(e);
                                        }                                        
                                        c.setLocation(top_x, top_y);
                                    }
                                    
                                    while(top_y > 45)
                                    {
                                        top_y--;
                                        try
                                        {
                                            Thread.sleep(5);
                                        }
                                        catch(Exception e)
                                        {
                                            System.out.println(e);
                                        }
                                        c.setLocation(top_x, top_y);
                                    }
                                }
                            }
                        }                    
                        CardsCollected++;
                    }
                }
                for(int IndexNumber = CPlayer.Hand.Cards.size() - CardsCollected; IndexNumber < CPlayer.Hand.Cards.size(); IndexNumber++)
                {
                    HPlayer.RemoveCard(CPlayer.Hand.Cards.get(IndexNumber));
                }
                RearrangeHand(HPlayer);
                CPlayer.IsInTurn = false;
                HPlayer.cardChosen = null;
                HPlayer.IsInTurn = true;
            }
            else
            {
                DrawCard(CPlayer);
                HPlayer.IsInTurn = true;
                HPlayer.cardChosen = null;
                CPlayer.IsInTurn = false;
            }
            HandleBooks(p);
        }
        
        else if (p instanceof HumanPlayer)
        {
            
            if(CPlayer.FaceValueExists(HPlayer.cardChosen.FaceValue))
            {
                int CardsCollected = 0;
                for(int IndexNumber = 0; IndexNumber < CPlayer.Hand.Cards.size(); IndexNumber++)
                {
                    if(CPlayer.Hand.Cards.get(IndexNumber).FaceValue == HPlayer.cardChosen.FaceValue)
                    {
                        HPlayer.CollectCard(CPlayer.Hand.Cards.get(IndexNumber));
                        for(Component C : ContentPane.getComponents())
                        {
                            if(C instanceof CardSprite)
                            {
                                if(((CardSprite)C).GetCard() == CPlayer.Hand.Cards.get(IndexNumber))
                                {
                                    CardSprite c = new CardSprite(((CardSprite)C).GetCard(), C.getX(), C.getY());
                                    ContentPane.remove(C);
                                    ContentPane.add(c);
                                    int top_x = c.getX();
                                    int top_y = c.getY();
                                    c.DisplayFace();
                                    
                                    while(top_x != ((HPlayer.Hand.Cards.size() - 1) * 15) + 40)
                                    {
                                        if(top_x < ((HPlayer.Hand.Cards.size() - 1) * 15) + 40)
                                        {
                                            top_x++;
                                        }
                                        else
                                        {
                                            top_x--;
                                        }
                                        try
                                        {
                                            Thread.sleep(5);
                                        }
                                        catch(Exception e)
                                        {
                                            System.out.println(e);
                                        }                                        
                                        c.setLocation(top_x, top_y);
                                    }
                                    
                                    while(top_y < 615)
                                    {
                                        top_y++;
                                        try
                                        {
                                            Thread.sleep(5);
                                        }
                                        catch(Exception e)
                                        {
                                            System.out.println(e);
                                        }
                                        c.setLocation(top_x, top_y);
                                    }
                                    c.addMouseListener(m);
                                }
                            }
                        }                    
                        CardsCollected++;
                    }
                }
                for(int IndexNumber = HPlayer.Hand.Cards.size() - CardsCollected; IndexNumber < HPlayer.Hand.Cards.size(); IndexNumber++)
                {
                    CPlayer.RemoveCard(HPlayer.Hand.Cards.get(IndexNumber));
                }
                RearrangeHand(CPlayer);
                CPlayer.IsInTurn = true;
                HPlayer.IsInTurn = false;
            }
            else
            {
                DrawCard(HPlayer);
                CPlayer.IsInTurn = true;
                HPlayer.IsInTurn = false;
            }
        }
    }
    
    
    public boolean GameHasEnded()
    {
        if(deck.IsEmpty() || CPlayer.HandIsEmpty() || HPlayer.HandIsEmpty())
        {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args)
    { 
        Main m = new Main();
        m.InitFrame();
        m.InitGame();
        m.RunGame();
        
    }
    
    public void Note(MouseEvent me)
    {
        
    }

    @Override
    public void run() 
    {
    }
}
