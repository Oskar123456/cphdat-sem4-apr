package portfolio;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree
{
    Random rng;
    float r, h;
    int branch_factor = 2, branch_num = 50;
    int precision = 100;
    Tree parent;
    List<Tree> children;

    public Tree(Tree tree, float r, float h, int branch_factor, int branch_num)
    {
        this.parent = tree;
        this.r = r;
        this.h = h;
        this.branch_factor = branch_factor;
        this.branch_num = branch_num;
        this.children = new ArrayList<Tree>();
        rng = new Random(System.nanoTime());
        grow(r, h, branch_factor, branch_num);
    }

    void grow(float r, float h, int branch_factor, int branch_num)
    {
        if (h <= 0 || r <= 0 || branch_factor <= 0 || branch_num <= 0) {
            return;
        }
        int n = rng.nextInt(branch_num);
        for (int i = 0; i < n; i++) {
            Tree tree = new Tree(this, r, h, branch_factor, branch_num);
        }
    }
}















