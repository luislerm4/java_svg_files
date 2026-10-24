package mx.unison.svg.util;

import mx.unison.svg.data.Geometric;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class Polyline extends Geometric {
    private String pointsStr;
    private Color stroke;
    private Color fill;
    private float strokeWidth;

    public Polyline(String pointsStr, Color stroke, Color fill, float strokeWidth) {
        this.pointsStr = pointsStr;
        this.stroke = stroke;
        this.fill = fill;
        this.strokeWidth = strokeWidth;
    }

    public void draw(Graphics2D g2d) {
        GeneralPath path = new GeneralPath();

        if (pointsStr == null || pointsStr.trim().isEmpty()) {
            return;
        }

        String[] coords = pointsStr.trim().split("\\s+");

        for (int i = 0; i < coords.length; i++) {
            if (coords[i].isEmpty()) continue;
            String[] xy = coords[i].split(",");
            if (xy.length < 2) continue;

            float x = Float.parseFloat(xy[0]);
            float y = Float.parseFloat(xy[1]);

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        if (fill != null) {
            g2d.setColor(fill);
            g2d.fill(path);
        }

        if (stroke != null) {
            g2d.setColor(stroke);
            g2d.setStroke(new java.awt.BasicStroke(strokeWidth));
            g2d.draw(path);
        }
    }
}