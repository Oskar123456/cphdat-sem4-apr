package cphdatadvprg;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Float;
import java.awt.Graphics2D.*;
import com.github.javafaker.Faker;

/*
 *
 * Exercise Template
 *
 */

public class Main
{
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        MainFrame frame = new MainFrame("HelloWorldSwing");
    }
}

class MainFrame extends JFrame
{
    String str_mem = "";

    Grapher grapher;
    Benchmarker benchmarker;
    JButton button_toggle_benchmarking;
    JButton button_toggle_sorting;

    public long sort_param_delay_ms = 100;

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
    }

    public void viewBenchmarks()
    {
        grapher.setVisible(false);
        button_toggle_benchmarking.setVisible(false);
        benchmarker.setVisible(true);
        button_toggle_sorting.setVisible(true);
    }

    public void viewSorting()
    {
        benchmarker.setVisible(false);
        button_toggle_sorting.setVisible(false);
        grapher.setVisible(true);
        button_toggle_benchmarking.setVisible(true);
    }

    public MainFrame(String name)
    {
        super(name);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GroupLayout layout_sorting = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout_sorting);
        layout_sorting.setAutoCreateGaps(true);
        layout_sorting.setAutoCreateContainerGaps(true);

        JLabel label = new JLabel("Sorting Algorithms");
        JLabel label2 = new JLabel("n: 15");
        JLabel label3 = new JLabel("sleep: 100ms");
        JLabel label4 = new JLabel("Bubble Sort");

        grapher = new Grapher(this, 15);
        benchmarker = new Benchmarker(this, 10000);

        JButton button_pause = new JButton("pause");
        button_pause.addActionListener((ActionEvent e) -> {
            if (grapher.pause()) {
                button_pause.setText("unpause");
            } else {
                button_pause.setText("pause");
            }
        });
        JButton button_bubblesort = new JButton("Bubble Sort");
        button_bubblesort.addActionListener((ActionEvent e) -> {
            int[] n_cmprs = new int[1];
            Integer[] bars = grapher.shuffle();
            Integer[] bars_copy = Arrays.copyOf(bars, bars.length);
            Queue<Integer[]> swaps = StepSorts.bubbleSort(bars_copy, Comparator.naturalOrder(), n_cmprs);
            label4.setText("Bubble Sort (" + n_cmprs[0] + " comparisons, " + swaps.size() + " swaps)");
            grapher.play(swaps);
        });
        JButton button_heapsort = new JButton("Heap Sort");
        button_heapsort.addActionListener((ActionEvent e) -> {
            int[] n_cmprs = new int[1];
            Integer[] bars = grapher.shuffle();
            Integer[] bars_copy = Arrays.copyOf(bars, bars.length);
            Queue<Integer[]> swaps = StepSorts.heapSort(bars_copy, Comparator.naturalOrder(), n_cmprs);
            label4.setText("Bubble Sort (" + n_cmprs[0] + " comparisons, " + swaps.size() + " swaps)");
            grapher.play(swaps);
        });
        JButton button_quicksort = new JButton("Quick Sort");
        button_quicksort.addActionListener((ActionEvent e) -> {
            int[] n_cmprs = new int[1];
            Integer[] bars = grapher.shuffle();
            Integer[] bars_copy = Arrays.copyOf(bars, bars.length);
            Queue<Integer[]> swaps = StepSorts.quickSort(bars_copy, Comparator.naturalOrder(), n_cmprs);
            label4.setText("Quick Sort (" + n_cmprs[0] + " comparisons, " + swaps.size() + " swaps)");
            grapher.play(swaps);
        });
        JButton button_shuffle = new JButton("shuffle");
        button_shuffle.addActionListener((ActionEvent e) -> grapher.shuffle());
        button_toggle_benchmarking = new JButton("Benchmarks");
        button_toggle_benchmarking.addActionListener((ActionEvent e) -> {
            viewBenchmarks();
        });
        button_toggle_sorting = new JButton("Sorting");
        button_toggle_sorting.addActionListener((ActionEvent e) -> {
            viewSorting();
        });

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

        GroupLayout.SequentialGroup layout_sorting_hori = layout_sorting.createSequentialGroup()
            .addGroup(layout_sorting.createParallelGroup()
                    .addComponent(label)
                    .addComponent(button_pause)
                    .addComponent(button_bubblesort)
                    .addComponent(button_heapsort)
                    .addComponent(button_quicksort)
                    .addComponent(button_shuffle)
                    .addComponent(label2)
                    .addComponent(slider_n_elements, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3)
                    .addComponent(slider_delay_ms, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_toggle_benchmarking)
                    .addComponent(button_toggle_sorting))
            .addGroup(layout_sorting.createParallelGroup()
                    .addComponent(label4)
                    .addGroup(layout_sorting.createParallelGroup()
                        .addComponent(benchmarker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(grapher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout.SequentialGroup layout_sorting_vert = layout_sorting.createSequentialGroup()
            .addGroup(layout_sorting.createParallelGroup()
                    .addGroup(layout_sorting.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(button_pause)
                        .addComponent(button_bubblesort)
                        .addComponent(button_heapsort)
                        .addComponent(button_quicksort)
                        .addComponent(button_shuffle)
                        .addComponent(label2)
                        .addComponent(slider_n_elements)
                        .addComponent(label3)
                        .addComponent(slider_delay_ms)
                        .addComponent(button_toggle_benchmarking)
                        .addComponent(button_toggle_sorting))
                    .addGroup(layout_sorting.createSequentialGroup()
                        .addComponent(label4)
                        .addGroup(layout_sorting.createParallelGroup()
                            .addComponent(benchmarker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(grapher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

        layout_sorting.setVerticalGroup(layout_sorting_vert);
        layout_sorting.setHorizontalGroup(layout_sorting_hori);
        pack();
        setVisible(true);
        viewSorting();
    }
}

