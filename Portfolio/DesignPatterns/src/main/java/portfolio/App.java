package portfolio;

import org.abstractica.javacsg.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import static java.util.stream.Collectors.toList;

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

        double w = 5, l = 5, h = 100;
        double scale = 35, s = 1;

        List<Tower> towers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Vector3D pos = csg.vector3D(i * w * 2 * scale, j * l * 2 * scale, 0);
                Tower t = new Tower(csg, pos, scale, w, l, h, 1);
//                towers.add(t);
            }
        }

        int deg_step = 45;
        double degs = 0, rad;
        for (int i = 0; i < 25; i++) {
            degs += rng.nextInt(deg_step) + deg_step;
            rad = (w * 2) * (int)(1 + degs / 360) * scale;
            double rads = degs * ((2 * Math.PI) / 360);
            Vector3D pos = csg.vector3D(Math.cos(rads) * rad, Math.sin(rads) * rad, 0);
            Tower t = new Tower(csg, pos, scale, w, l, h, 1);
            towers.add(t);
            System.out.printf("%s: %f %f%n", pos.toString(), degs, rad);
        }
        Geometry3D city = csg.union3D(towers.stream().map(t -> t.geom).toList());

        csg.view(city);
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

















