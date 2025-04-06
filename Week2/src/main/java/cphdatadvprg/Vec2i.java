package cphdatadvprg;

import java.util.*;
import com.github.javafaker.Faker;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.*;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.*;

/*
 *
 * Exercise Template
 *
 */

public class Vec2i
{
    int i, j;
    public static final Vec2i north = new Vec2i(-1,  0);
    public static final Vec2i south = new Vec2i( 1,  0);
    public static final Vec2i east  = new Vec2i( 0,  1);
    public static final Vec2i west  = new Vec2i( 0, -1);
    public static final Vec2i[] dirs = new Vec2i[]{north, south, east, west};
    public Vec2i add(Vec2i v) { return new Vec2i(i + v.i, j + v.j); }
    public Vec2i right(Vec2i dir) { return new Vec2i( dir.j, -dir.i); }
    public Vec2i left(Vec2i dir)  { return new Vec2i(-dir.j,  dir.i); }
    public Vec2i(int i, int j) { this.i = i; this.j = j; }
    @Override public String toString() { return String.format("[%d,%d]", i, j); }
    @Override public boolean equals(Object v) { return v != null && v.getClass() == Vec2i.class && i == ((Vec2i) v).i && j == ((Vec2i) v).j; }
}

