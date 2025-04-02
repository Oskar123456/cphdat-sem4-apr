package cphdatadvprg;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Float;
import java.awt.Graphics2D.*;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;

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
    int sample_size, iters, iters_left;
    ArrayList<Long[]> benchmarks_bubblesort;
    ArrayList<Long[]> benchmarks_heapsort;
    ArrayList<Long[]> benchmarks_quicksort;

    Color bubblesort_color = Color.green;
    Color heapsort_color = Color.red;
    Color quicksort_color = Color.blue;

    public Benchmarker(MainFrame container)
    {
        super();

        this.container = container;

        sample_size = 200;
        iters = 25;

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

        if (benchmarks_bubblesort == null || benchmarks_bubblesort.size() < 1) {
            return;
        }
        if (benchmarks_heapsort == null || benchmarks_heapsort.size() < 1) {
            return;
        }
        if (benchmarks_quicksort == null || benchmarks_quicksort.size() < 1) {
            return;
        }

        long max_time = 0, max_samples = benchmarks_bubblesort.get(benchmarks_bubblesort.size() - 1)[0];
        for (int i = 0; i < benchmarks_bubblesort.size(); i++) {
            max_time = Math.max(Math.max(benchmarks_bubblesort.get(i)[1], benchmarks_heapsort.get(i)[1]),
                    benchmarks_quicksort.get(i)[1]);
        }

        float dot_width = rect.width / (5 * benchmarks_bubblesort.size());
        float padding_x = rect.width / 20;
        float padding_y = rect.height / 20;
        float w = (rect.width - 2 * padding_x) / (benchmarks_bubblesort.size());

        graphics2d.setColor(bubblesort_color);
        graphics2d.drawString("Bubble Sort", padding_x, padding_y);
        for (int i = 0; i < benchmarks_bubblesort.size(); i++) {
            float ratio = (float)(benchmarks_bubblesort.get(i)[1] / (double)max_time);
            float h = ratio * (rect.height - 2 * padding_y);
            float pos = padding_y + ((rect.height - 2 * padding_y) - h);
            Rectangle2D.Float dot = new Rectangle2D.Float(padding_x + i * w, pos, dot_width, dot_width);
            graphics2d.fill(dot);
        }

        graphics2d.setColor(heapsort_color);
        graphics2d.drawString("Heap Sort", padding_x, padding_y * 2);
        for (int i = 0; i < benchmarks_heapsort.size(); i++) {
            float ratio = (float)(benchmarks_heapsort.get(i)[1] / (double)max_time);
            float h = ratio * (rect.height - 2 * padding_y);
            float pos = padding_y + ((rect.height - 2 * padding_y) - h);
            Rectangle2D.Float dot = new Rectangle2D.Float(padding_x + i * w, pos, dot_width, dot_width);
            graphics2d.fill(dot);
        }

        graphics2d.setColor(quicksort_color);
        graphics2d.drawString("Quick Sort", padding_x, padding_y * 3);
        for (int i = 0; i < benchmarks_quicksort.size(); i++) {
            float ratio = (float)(benchmarks_quicksort.get(i)[1] / (double)max_time);
            float h = ratio * (rect.height - 2 * padding_y);
            float pos = padding_y + ((rect.height - 2 * padding_y) - h);
            Rectangle2D.Float dot = new Rectangle2D.Float(padding_x + i * w, pos, dot_width, dot_width);
            graphics2d.fill(dot);
        }
    }

    public void play()
    {
        reset();
        iters_left = iters;
        benchmarks_bubblesort = new ArrayList<>();
        benchmarks_heapsort = new ArrayList<>();
        benchmarks_quicksort = new ArrayList<>();

        timer = new javax.swing.Timer(10, (ActionEvent e) -> { container.repaint(); });
        timer.start();

        System.out.printf("benchmarking...%n\t");
        for (int i = 0; i < iters; i++) {
            step(sample_size * (i + 1));
            System.out.printf("%d/%d ", i, iters);
        }
        System.out.printf("%n");
        reset();
    }

    public void step(int n)
    {
        int[] n_cmprs = new int[1];
        long pre_ns, post_ns;

        Integer[] a = Utilities.randomIArrayFull(n);
        pre_ns = System.nanoTime();
        StepSorts.bubbleSort(a, Comparator.naturalOrder(), n_cmprs);
        post_ns = System.nanoTime();
        benchmarks_bubblesort.add(new Long[] { (long)n, post_ns - pre_ns});

        a = Utilities.randomIArrayFull(n);
        pre_ns = System.nanoTime();
        StepSorts.heapSort(a, Comparator.naturalOrder(), n_cmprs);
        post_ns = System.nanoTime();
        benchmarks_heapsort.add(new Long[] { (long)n, post_ns - pre_ns});

        a = Utilities.randomIArrayFull(n);
        pre_ns = System.nanoTime();
        StepSorts.quickSort(a, Comparator.naturalOrder(), n_cmprs);
        post_ns = System.nanoTime();
        benchmarks_quicksort.add(new Long[] { (long)n, post_ns - pre_ns});
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

    public void reset()
    {
        if (timer == null) {
            return;
        }

        timer.stop();
        timer = null;

        if (benchmarks_bubblesort == null || benchmarks_bubblesort.size() < 1) {
            return;
        }
        if (benchmarks_heapsort == null || benchmarks_heapsort.size() < 1) {
            return;
        }
        if (benchmarks_quicksort == null || benchmarks_quicksort.size() < 1) {
            return;
        }

        try {
            Files.createDirectories(Paths.get("data"));

            FileWriter output_writer;
            String output_text;

            output_text = "";
            for (int i = 0; i < benchmarks_bubblesort.size(); i++) {
                output_text += benchmarks_bubblesort.get(i)[0] + " ";
            }
            output_text += System.lineSeparator();
            for (int i = 0; i < benchmarks_bubblesort.size(); i++) {
                output_text += benchmarks_bubblesort.get(i)[1] + " ";
            }
            output_writer = new FileWriter("data/benchmarks_bubblesort.txt");
            output_writer.write(output_text);
            output_writer.close();

            output_text = "";
            for (int i = 0; i < benchmarks_heapsort.size(); i++) {
                output_text += benchmarks_heapsort.get(i)[0] + " ";
            }
            output_text += System.lineSeparator();
            for (int i = 0; i < benchmarks_heapsort.size(); i++) {
                output_text += benchmarks_heapsort.get(i)[1] + " ";
            }
            output_writer = new FileWriter("data/benchmarks_heapsort.txt");
            output_writer.write(output_text);
            output_writer.close();

            output_text = "";
            for (int i = 0; i < benchmarks_quicksort.size(); i++) {
                output_text += benchmarks_quicksort.get(i)[0] + " ";
            }
            output_text += System.lineSeparator();
            for (int i = 0; i < benchmarks_quicksort.size(); i++) {
                output_text += benchmarks_quicksort.get(i)[1] + " ";
            }
            output_writer = new FileWriter("data/benchmarks_quicksort.txt");
            output_writer.write(output_text);
            output_writer.close();

            System.out.printf("Benchmark results output to folder: data%n");

            File dir = new File("data");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    System.out.printf("\t%s%n", child.getName());
                }
            }
            System.out.printf("%n");
        } catch (Exception e) {
            System.out.printf("PANICKING FILE STUFF%n");
        }

        container.repaint();
    }
}


