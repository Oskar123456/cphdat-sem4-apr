package cphdatadvprg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
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

public class Main
{
    private static void createAndShowGUI()
    {
        MainFrame frame = new MainFrame("HelloWorldSwing");
    }

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

class MainFrame extends JFrame
{
    public long sort_param_delay_ms = 100;

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
    }

    public MainFrame(String name)
    {
        super(name);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel label = new JLabel("Sorting Algorithms");
        JLabel label2 = new JLabel("n: 15");
        JLabel label3 = new JLabel("sleep: 100ms");
        JLabel label4 = new JLabel("Bubble Sort");

        Grapher grapher = new Grapher(this, 15);

        JButton button_pause = new JButton("pause");
        button_pause.addActionListener((ActionEvent e) -> {
            if (grapher.pause()) {
                button_pause.setText("unpause");
            } else {
                button_pause.setText("pause");
            }
        });
        JButton button = new JButton("bubble sort");
        button.addActionListener((ActionEvent e) -> grapher.bubbleSort());
        JButton button_shuffle = new JButton("shuffle");
        button_shuffle.addActionListener((ActionEvent e) -> grapher.shuffle());

        JSlider slider_n_elements = new JSlider(JSlider.HORIZONTAL, 5, 100, 15);
        slider_n_elements.setPreferredSize(new Dimension(135, 100));
        slider_n_elements.addChangeListener((ChangeEvent e) -> {
            label2.setText("n: " + slider_n_elements.getValue());
            grapher.n_bars = slider_n_elements.getValue();
            grapher.shuffle();
        });

        JSlider slider_delay_ms = new JSlider(JSlider.HORIZONTAL, 5, 150, 100);
        slider_delay_ms.setPreferredSize(new Dimension(135, 100));
        slider_delay_ms.addChangeListener((ChangeEvent e) -> {
            label3.setText("sleep: " + slider_delay_ms.getValue() + "ms");
            grapher.setDelay(slider_delay_ms.getValue());
        });

        var layout_group_hori = layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                    .addComponent(label)
                    .addComponent(button_pause)
                    .addComponent(button)
                    .addComponent(button_shuffle)
                    .addComponent(label2)
                    .addComponent(slider_n_elements, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3)
                    .addComponent(slider_delay_ms, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup()
                    .addComponent(label4)
                    .addComponent(grapher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        var layout_group_vert = layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(button_pause)
                        .addComponent(button)
                        .addComponent(button_shuffle)
                        .addComponent(label2)
                        .addComponent(slider_n_elements)
                        .addComponent(label3)
                        .addComponent(slider_delay_ms))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label4)
                        .addComponent(grapher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        layout.setHorizontalGroup(layout_group_hori);
        layout.setVerticalGroup(layout_group_vert);

        pack();
        setVisible(true);
    }
}

class Grapher extends JPanel
{
    Random rng;
    public Integer[] bars;
    public Integer bars_min, bars_max;
    public int n_bars;
    public int delay_ms = 100;

    Timer timer;
    long sort_state_start_ns, sort_state_end_ns, sort_state_total_ns;
    int sort_state_swap_i = -1, sort_state_swap_j = -1;
    int bubble_sort_state_i, bubble_sort_state_j;
    boolean bubble_sort_state_swapped;

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

    public void shuffle()
    {
        reset();

        rng = new Random(System.nanoTime());
        bars = new Integer[n_bars];
        bars_min = 0; bars_max = Math.max(n_bars / 2, 5);
        for (int i = 0; i < n_bars; i++) {
            bars[i] = (Math.abs(rng.nextInt()) % (bars_max - 1)) + 1;
        }
        container.repaint(10);
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

    public void bubbleSort()
    {
        reset();
        bubble_sort_state_swapped = false;
        timer = new Timer(delay_ms, (ActionEvent e) -> bubbleSortStep());
        timer.start();
    }

    public void bubbleSortStep()
    {
        if (bubble_sort_state_i == bars.length) {
            if (!bubble_sort_state_swapped) {
                timer.stop();
                timer = null;
                reset();
                // System.out.printf("bubbleSort completed in %dms%n", sort_state_total_ns / 1000000);
                return;
            } else {
                bubble_sort_state_swapped = false;
                bubble_sort_state_i = 1;
            }
        }

        if (bars[bubble_sort_state_i - 1] > bars[bubble_sort_state_i]) {
            swap(bubble_sort_state_i - 1, bubble_sort_state_i);
            bubble_sort_state_swapped = true;
        }

        container.repaint();
        ++bubble_sort_state_i;
    }

    public void swap(int i, int j)
    {
        int t = bars[i];
        bars[i] = bars[j];
        bars[j] = t;
        sort_state_swap_i = i;
        sort_state_swap_j = j;
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
        bubble_sort_state_i = 1;
        bubble_sort_state_j = 0;

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
