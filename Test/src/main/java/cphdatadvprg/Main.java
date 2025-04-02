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
import com.github.javafaker.Faker;

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

    public static void swap(int[] a, int i, int j)
    {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void heapSort(int[] a)
    {
        int start = a.length / 2;
        int end = a.length;
        while (end > 1) {
            if (start > 0) {
                --start;
            } else {
                --end;
                swap(a, 0, end);
            }
            int root = start;
            while (2 * root + 1 < end) {
                int child = 2 * root + 1;
                if (child + 1 < end && a[child] > a[child + 1]) {
                    child = child + 1;
                }
                if (a[root] > a[child]) {
                    swap(a, root, child);
                    root = child;
                } else {
                    break;
                }
            }
        }
    }

    public static <T> void printArrayGeneric(T[] a)
    {
        int len = a.length;
        System.out.printf("[");
        for (int i = 0; i < len; i++) {
            System.out.print(a[i]);
            if (i < len - 1) {
                System.out.printf(", ");
            }
        }
        System.out.printf("]");
    }

    public static void printArray(Integer[] a)
    {
        int len = a.length;
        System.out.printf("[");
        for (int i = 0; i < len; i++) {
            System.out.printf("%d", a[i]);
            if (i < len - 1) {
                System.out.printf(", ");
            }
        }
        System.out.printf("]");
    }

    public static Integer[] randomArray(int n)
    {
        Random rng = new Random(System.nanoTime());
        int len = Math.abs((rng.nextInt()) % n) + 1;
        Integer[] a = new Integer[len];
        for (int i = 0; i < len; i++) {
            a[i] = rng.nextInt() % 100;
        }
        return a;
    }

    public static void main(String[] args)
    {

        int trials = 100;
        for (int i = 0; i < trials; i++) {
            Integer[] a = randomArray(25);
            System.out.println();
            printArrayGeneric(a);
            // System.out.printf("%nheapSort:%n", args);
            StepSorts.heapSortGeneric(a);
            if (!StepSorts.verifySorted(a)) {
                System.out.printf("PANICK NOT SORTED");
            } else {
                System.out.printf("  --> ");
            }
            // StepSorts.heapify(a);
            // StepSorts.verifyHeap(a);
            // heapSort(a);
            printArrayGeneric(a);
            System.out.println();
        }

        Faker f = new Faker();
        for (int i = 0; i < trials; i++) {
            int len = Math.abs(new Random(System.nanoTime()).nextInt()) % 25 + 1;
            String[] strs = new String[len];
            for (int j = 0; j < len; j++) {
                strs[j] = f.name().firstName();
            }
            printArrayGeneric(strs);
            StepSorts.heapSortGenericCmpr(strs, Comparator.naturalOrder());
            System.out.printf("  --> ");
            printArrayGeneric(strs);
            System.out.println();

            for (int j = 0; j < len - 1; j++) {
                if (strs[j].compareTo(strs[j + 1]) < 0) {
                    System.out.printf("PANICK NOT SORTED");
                }
            }
        }

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

