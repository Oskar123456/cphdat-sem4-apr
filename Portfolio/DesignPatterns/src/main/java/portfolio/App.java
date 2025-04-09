package portfolio;

import org.abstractica.javacsg.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * *********
 * *********
 * PORTFOLIO
 * *************************
 * *************************
 * [WEEK 2]: DESIGN PATTERNS
 * *************************
 * *************************
 */
public class App
{
    static Random rng = new Random(System.nanoTime());

    public static void main(String[] args)
    {
        JavaCSG csg = JavaCSGFactory.createDefault();

        double w = 10, l = 10, h = 10;
        double step = 1;

        List<Geometry3D> gs = new ArrayList<>();
        Geometry3D p = csg.box3D(w, l, step, true);

        int prec = 200;
        for (int i = 2; i < prec; i++) {
            Geometry3D g = csg.translate3D(csg.vector3D(0, 0, i * step))
                    .transform(p);
            g = csg.scale3D(prec * step - (i * step)).transform(g);
            g = csg.rotate3DZ(csg.degrees((360.0 / (prec - 2)) * (i - 2))).transform(g);
            gs.add(g);
        }

        Geometry3D ch = csg.union3D(gs);

        csg.view(ch);
    }

    public static Vector3D eulerToVec(JavaCSG csg, double yaw, double pitch)
    {
        return csg.normalized(csg.vector3D(Math.cos(yaw) * Math.cos(pitch),
                Math.sin(yaw) * Math.cos(pitch),
                Math.sin(pitch)));
    }

    public static Vector2D vecToEuler(JavaCSG csg, Vector3D v)
    {
        double pitch = Math.asin(v.z());
        double yaw = Math.atan2(v.y(), v.x());
        return csg.vector2D(pitch, yaw);
    }
}

















