/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JLabel;

/**
 *
 * @author Ruhan
 */
public class NotificationBox extends JLabel
{
    public NotificationBox(String txt)
    {
        setText(txt);
        setBounds(720, 300, 100, 10);
    }
}
