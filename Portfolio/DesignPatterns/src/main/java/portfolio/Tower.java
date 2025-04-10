package portfolio;

import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class Tower
{
    public Geometry3D geom;

    public Tower(JavaCSG csg, Vector3D pos, double scale, double w, double l, double h, double step)
    {
        List<Geometry3D> gs = new ArrayList<>();
        Geometry3D p = csg.box3D(w, l, step, true);

        int prec = (int)(h / step);
        for (int i = 2; i < prec + 2; i++) {
            Geometry3D g = csg.translate3D(csg.vector3D(0, 0, i * step))
                    .transform(p);
            g = csg.scale3D(scale * (prec - i) / prec, scale * (prec - i) / prec, scale * (prec - i) / prec).transform(g);
            g = csg.rotate3DZ(csg.degrees((360.0 / (prec - 2)) * (i - 2))).transform(g);
            g = csg.translate3D(pos).transform(g);
            gs.add(g);
        }

        geom = csg.union3D(gs);
    }
}
