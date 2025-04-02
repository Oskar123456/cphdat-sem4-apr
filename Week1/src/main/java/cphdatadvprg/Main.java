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
        // Comparator<Integer> cmpr = Comparator.naturalOrder();
        // Random rng = new Random();
        // for (int i = 0; i < 1000; i++) {
        //     int sample_size = Math.abs(rng.nextInt() % 1000) + 1;
        //     Integer[] a = Utilities.randomIArray(sample_size);
            // Utilities.printArray(a);
            // System.out.printf(" --> ");
            // Sorts.heapSort(a, cmpr);
            // for (int j = 0; j < a.length - 1; j++) {
            //     assert(cmpr.compare(a[j], a[j + 1]) <= 0);
            // }
            // // Utilities.printArray(a);
            // System.out.println();
        // }

        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
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
    JButton button_bubblesort;
    JButton button_heapsort;
    JButton button_quicksort;
    JButton button_shuffle;
    JButton button_pause;
    JButton button_run_benchmarks;
    JSlider slider_n_elements;
    JSlider slider_n_benchmark;
    JSlider slider_n_benchmark_samples;
    JSlider slider_delay_ms;
    JLabel label;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JLabel label7;
    JLabel label8;

    public long sort_param_delay_ms = 100;

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
    }

    public void viewBenchmarks()
    {
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        button_bubblesort.setVisible(false);
        button_heapsort.setVisible(false);
        button_quicksort.setVisible(false);
        button_shuffle.setVisible(false);
        slider_n_elements.setVisible(false);
        slider_delay_ms.setVisible(false);
        grapher.setVisible(false);
        button_toggle_benchmarking.setVisible(false);
        button_run_benchmarks.setVisible(true);
        button_pause.setVisible(false);
        benchmarker.setVisible(true);
        button_toggle_sorting.setVisible(true);
        slider_n_benchmark.setVisible(true);
        slider_n_benchmark_samples.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(false);
        label7.setVisible(true);
        label8.setVisible(true);
    }

    public void viewSorting()
    {
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        label8.setVisible(false);
        button_toggle_sorting.setVisible(false);
        slider_n_benchmark.setVisible(false);
        slider_n_benchmark_samples.setVisible(false);
        benchmarker.setVisible(false);
        button_toggle_sorting.setVisible(false);
        button_run_benchmarks.setVisible(false);
        grapher.setVisible(true);
        button_pause.setVisible(true);
        button_toggle_benchmarking.setVisible(true);
        button_bubblesort.setVisible(true);
        button_heapsort.setVisible(true);
        button_quicksort.setVisible(true);
        button_shuffle.setVisible(true);
        slider_n_elements.setVisible(true);
        slider_delay_ms.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
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

        label = new JLabel("Sorting Algorithms");
        label2 = new JLabel("n: 15");
        label3 = new JLabel("sleep: 100ms");
        label4 = new JLabel("Bubble Sort");
        label5 = new JLabel("sample size: 200");
        label6 = new JLabel("sleep: 100ms");
        label7 = new JLabel("Benchmarks");
        label8 = new JLabel("iterations: 25");

        grapher = new Grapher(this, 15);
        benchmarker = new Benchmarker(this);

        button_pause = new JButton("pause");
        button_pause.addActionListener((ActionEvent e) -> {
            if (grapher.pause()) {
                button_pause.setText("unpause");
            } else {
                button_pause.setText("pause");
            }
        });
        button_bubblesort = new JButton("Bubble Sort");
        button_bubblesort.addActionListener((ActionEvent e) -> {
            int[] n_cmprs = new int[1];
            Integer[] bars = grapher.shuffle();
            Integer[] bars_copy = Arrays.copyOf(bars, bars.length);
            Queue<Integer[]> swaps = StepSorts.bubbleSort(bars_copy, Comparator.naturalOrder(), n_cmprs);
            label4.setText("Bubble Sort (" + n_cmprs[0] + " comparisons, " + swaps.size() + " swaps)");
            grapher.play(swaps);
        });
        button_heapsort = new JButton("Heap Sort");
        button_heapsort.addActionListener((ActionEvent e) -> {
            int[] n_cmprs = new int[1];
            Integer[] bars = grapher.shuffle();
            Integer[] bars_copy = Arrays.copyOf(bars, bars.length);
            Queue<Integer[]> swaps = StepSorts.heapSort(bars_copy, Comparator.naturalOrder(), n_cmprs);
            label4.setText("Bubble Sort (" + n_cmprs[0] + " comparisons, " + swaps.size() + " swaps)");
            grapher.play(swaps);
        });
        button_quicksort = new JButton("Quick Sort");
        button_quicksort.addActionListener((ActionEvent e) -> {
            int[] n_cmprs = new int[1];
            Integer[] bars = grapher.shuffle();
            Integer[] bars_copy = Arrays.copyOf(bars, bars.length);
            Queue<Integer[]> swaps = StepSorts.quickSort(bars_copy, Comparator.naturalOrder(), n_cmprs);
            label4.setText("Quick Sort (" + n_cmprs[0] + " comparisons, " + swaps.size() + " swaps)");
            grapher.play(swaps);
        });
        button_shuffle = new JButton("shuffle");
        button_shuffle.addActionListener((ActionEvent e) -> grapher.shuffle());
        button_toggle_benchmarking = new JButton("Benchmarks");
        button_toggle_benchmarking.addActionListener((ActionEvent e) -> {
            viewBenchmarks();
        });
        button_toggle_sorting = new JButton("Sorting");
        button_toggle_sorting.addActionListener((ActionEvent e) -> {
            viewSorting();
        });
        button_run_benchmarks = new JButton("Run benchmarks");
        button_run_benchmarks.addActionListener((ActionEvent e) -> {
            benchmarker.play();
        });

        slider_n_elements = new JSlider(JSlider.HORIZONTAL, 5, 100, 15);
        slider_n_elements.setPreferredSize(new Dimension(135, 100));
        slider_n_elements.addChangeListener((ChangeEvent e) -> {
            label2.setText("n: " + slider_n_elements.getValue());
            grapher.n_bars = slider_n_elements.getValue();
            grapher.shuffle();
        });

        slider_delay_ms = new JSlider(JSlider.HORIZONTAL, 5, 150, 100);
        slider_delay_ms.setPreferredSize(new Dimension(135, 100));
        slider_delay_ms.addChangeListener((ChangeEvent e) -> {
            label3.setText("sleep: " + slider_delay_ms.getValue() + "ms");
            grapher.setDelay(slider_delay_ms.getValue());
        });

        slider_n_benchmark = new JSlider(JSlider.HORIZONTAL, 50, 1000, 200);
        slider_n_benchmark.setPreferredSize(new Dimension(135, 100));
        slider_n_benchmark.addChangeListener((ChangeEvent e) -> {
            label5.setText("n: " + slider_n_benchmark.getValue());
            benchmarker.sample_size = slider_n_benchmark.getValue();
        });

        slider_n_benchmark_samples = new JSlider(JSlider.HORIZONTAL, 10, 100, 25);
        slider_n_benchmark_samples.setPreferredSize(new Dimension(135, 100));
        slider_n_benchmark_samples.addChangeListener((ChangeEvent e) -> {
            label8.setText("iterations: " + slider_n_benchmark_samples.getValue());
            benchmarker.iters = slider_n_benchmark_samples.getValue();
        });

        GroupLayout.SequentialGroup layout_sorting_hori = layout_sorting.createSequentialGroup()
            .addGroup(layout_sorting.createParallelGroup()
                    .addComponent(label)
                    .addGroup(layout_sorting.createParallelGroup()
                        .addComponent(button_run_benchmarks)
                        .addComponent(button_pause))
                    .addComponent(button_bubblesort)
                    .addComponent(button_heapsort)
                    .addComponent(button_quicksort)
                    .addComponent(button_shuffle)
                    .addComponent(label2)
                    .addComponent(label5)
                    .addGroup(layout_sorting.createParallelGroup()
                        .addComponent(slider_n_elements, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(slider_n_benchmark, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(label3)
                    .addComponent(label6)
                    .addComponent(label8)
                    .addGroup(layout_sorting.createParallelGroup()
                        .addComponent(slider_n_benchmark_samples, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(slider_delay_ms, 0, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(button_toggle_benchmarking)
                    .addComponent(button_toggle_sorting))
            .addGroup(layout_sorting.createParallelGroup()
                    .addGroup(layout_sorting.createParallelGroup()
                        .addComponent(label4)
                        .addComponent(label7))
                    .addGroup(layout_sorting.createParallelGroup()
                        .addComponent(benchmarker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(grapher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout.SequentialGroup layout_sorting_vert = layout_sorting.createSequentialGroup()
            .addGroup(layout_sorting.createParallelGroup()
                    .addGroup(layout_sorting.createSequentialGroup()
                        .addComponent(label)
                        .addGroup(layout_sorting.createParallelGroup()
                            .addComponent(button_run_benchmarks)
                            .addComponent(button_pause))
                        .addComponent(button_bubblesort)
                        .addComponent(button_heapsort)
                        .addComponent(button_quicksort)
                        .addComponent(button_shuffle)
                        .addComponent(label2)
                        .addComponent(label5)
                        .addGroup(layout_sorting.createParallelGroup()
                            .addComponent(slider_n_benchmark)
                            .addComponent(slider_n_elements))
                        .addComponent(label3)
                        .addComponent(label6)
                        .addComponent(label8)
                        .addGroup(layout_sorting.createParallelGroup()
                            .addComponent(slider_n_benchmark_samples)
                            .addComponent(slider_delay_ms))
                        .addComponent(button_toggle_benchmarking)
                        .addComponent(button_toggle_sorting))
                    .addGroup(layout_sorting.createSequentialGroup()
                        .addGroup(layout_sorting.createParallelGroup()
                            .addComponent(label4)
                            .addComponent(label7))
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

