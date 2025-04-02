package cphdatadvprg;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Float;
import java.awt.Graphics2D.*;

/*
 *
 * Exercise Template
 *
 */

class Benchmarker extends JPanel
{
    Random rng;
    javax.swing.Timer timer;
    MainFrame container;
    int sample_size;

    public int delay_ms = 100;

    public Benchmarker(MainFrame container, int sample_size)
    {
        super();

        this.sample_size = sample_size;

        setSize(400, 400);
        setPreferredSize(new Dimension(400, 400));
        setVisible(true);
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;
        Rectangle rect = getBounds(null);

        graphics2d.draw(new Rectangle(0, 0, rect.width - 2, rect.height - 2));
    }

    public void play(Queue<Integer[]> swaps)
    {
    }

    public boolean pause()
    {
        if (timer == null) {
            return false;
        }
        if (timer.isRunning()) {
            timer.stop();
            return true;
        } else {
            timer.start();
            return false;
        }
    }

    public void setDelay(int ms)
    {
        delay_ms = ms;
        if (timer != null) {
            timer.setDelay(ms);
        }
    }
}


