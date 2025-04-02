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
        JButton button = new JButton("Bubble Sort");
        button.addActionListener((ActionEvent e) -> {
            label4.setText("Bubble Sort");
            grapher.bubbleSort();
        });
        JButton button_heapsort = new JButton("Heap Sort");
        button_heapsort.addActionListener((ActionEvent e) -> {
            label4.setText("Heap Sort");
            grapher.heapSort();
        });
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
                    .addComponent(button_heapsort)
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
                        .addComponent(button_heapsort)
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

