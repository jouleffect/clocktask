package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;

/**
 * <h1>MyTimer</h1>
 * Classe che estende una Jpanel per la creazione di un timer.
 *
 * @author  Giulia Maraventano
 * @version 1.0
 * @since   2021-03-30
 */
public class MyTimer extends JPanel{

    public int secondi;
    public int minuti;
    public int ore;
    protected final JLabel label_timer = new JLabel();
    protected final Icon icon = new ImageIcon("play.png");
    protected final Icon icon2 = new ImageIcon("pause.png");
    protected final JButton start = new JButton(icon);
    protected final JButton stop = new JButton(icon2);

    public MyTimer() {

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Timer"));
        this.setBackground(Color.BLACK);
        label_timer.setForeground(Color.GREEN);
        label_timer.setFont (label_timer.getFont().deriveFont(40.0f));
        label_timer.setHorizontalAlignment(0);
        start.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);
        this.add(start,BorderLayout.NORTH);
        this.add(label_timer,BorderLayout.CENTER);
        this.add(stop,BorderLayout.SOUTH);
        timer.setInitialDelay(0);
        start.addActionListener(e -> timer.start());
        stop.addActionListener(e -> timer.stop());
    }

    /**
     * Questo &egrave; il metodo che implementa l'oggetto Timer, comandato dai pulsanti
     * start e stop, per avviare o fermare lo scorrimento dei secondi.
     * @return Timer.
     */
    public final Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String secondo;
            String minuto;
            String ora;
            NumberFormat formatter = new DecimalFormat("00");
            if (secondi == 60) {
                secondi = 0;
                minuti++;
            }
            if (minuti == 59) {
                minuti = 0;
                ore++;
            }
            ora = formatter.format(ore);
            minuto = formatter.format(minuti);
            secondo = formatter.format(secondi);
            label_timer.setText(ora + "." + minuto + "." + secondo);
            secondi++;
        }
    });

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

}


