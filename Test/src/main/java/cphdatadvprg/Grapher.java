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

class Grapher extends JPanel
{
    Random rng;
    public Integer[] bars;
    public Integer bars_min, bars_max;
    public int n_bars;
    public int delay_ms = 100;

    javax.swing.Timer timer;
    Queue<Integer[]> swaps;
    long sort_state_start_ns, sort_state_end_ns, sort_state_total_ns;
    int sort_state_swap_i = -1, sort_state_swap_j = -1;

    MainFrame container;

    public Grapher(MainFrame container, int n_bars)
    {
        super();

        this.container = container;
        this.n_bars = n_bars;
        shuffle();

        setSize(400, 400);
        setPreferredSize(new Dimension(400, 400));
        setVisible(true);
    }

    public Integer[] shuffle()
    {
        reset();
        rng = new Random(System.nanoTime());
        bars = new Integer[n_bars];
        bars_min = 0; bars_max = Math.max(n_bars / 2, 5);
        for (int i = 0; i < n_bars; i++) {
            bars[i] = (Math.abs(rng.nextInt()) % (bars_max - 1)) + 1;
        }
        container.repaint(10);
        return bars;
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;
        Rectangle rect = getBounds(null);

        graphics2d.draw(new Rectangle(0, 0, rect.width - 2, rect.height - 2));

        float padding_x = rect.width / 20;
        float padding_y = rect.height / 20;
        float w = (rect.width - 2 * padding_x) / (n_bars * 2);
        float h = rect.height - 2 * padding_x;
        for (int i = 0; i < n_bars; i++) {
            float h_i = ((float)bars[i] / bars_max) * h;
            Rectangle2D.Float r = new Rectangle2D.Float(i * w * 2 + padding_x, rect.y + rect.height - 2 * padding_y - h_i, w, h_i);
            if (i == sort_state_swap_i || i == sort_state_swap_j) {
                graphics2d.setColor(Color.red);
            } else {
                graphics2d.setColor(Color.gray);
            }
            graphics2d.fill(r);
        }
    }

    public void play(Queue<Integer[]> swaps)
    {
        this.swaps = swaps;
        timer = new javax.swing.Timer(delay_ms, (ActionEvent e) -> {
            if (swaps.size() < 1) {
                reset();
                return;
            }
            Integer[] swp = swaps.poll();
            swap(swp[0], swp[1]);
        });
        timer.start();
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

    public void swap(int i, int j)
    {
        int t = bars[i];
        bars[i] = bars[j];
        bars[j] = t;
        sort_state_swap_i = i;
        sort_state_swap_j = j;
        container.repaint();
    }

    public void reset()
    {
        if (timer != null) {
            timer.stop();
            timer = null;
        }

        sort_state_end_ns = System.nanoTime();
        sort_state_total_ns = sort_state_end_ns - sort_state_start_ns;
        sort_state_start_ns = System.nanoTime();
        sort_state_swap_i = -1;
        sort_state_swap_j = -1;

        container.repaint();
    }

    public void setDelay(int ms)
    {
        delay_ms = ms;
        if (timer != null) {
            timer.setDelay(ms);
        }
    }
}

