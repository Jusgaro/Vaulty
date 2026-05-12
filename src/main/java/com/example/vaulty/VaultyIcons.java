package com.example.vaulty;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

/**
 * Minimalistické outline ikonky pre Vaulty.
 * Každá metóda vracia Canvas, ktorý môžeš použiť ako grafiku v Button alebo Label.
 *
 * Príklad použitia:
 *   Button btn = new Button("Heslá");
 *   btn.setGraphic(VaultyIcons.key(20, "#6b5040"));
 */
public class VaultyIcons {

    // ── Farby z palety Vaulty ────────────────────────────────────────────────
    public static final String COLOR_DARK   = "#6b5040";
    public static final String COLOR_MEDIUM = "#9e8572";
    public static final String COLOR_LIGHT  = "#C4A882";
    public static final String COLOR_DANGER = "#9e6b5a";

    // ── Interný helper ───────────────────────────────────────────────────────
    private static GraphicsContext setup(Canvas c, double size, String hexColor) {
        GraphicsContext g = c.getGraphicsContext2D();
        g.clearRect(0, 0, size, size);
        g.setStroke(Color.web(hexColor));
        g.setLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        g.setLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        g.setFill(Color.TRANSPARENT);
        return g;
    }

    // ── 🔐 Zámok (logo / nadpis) ─────────────────────────────────────────────
    public static Canvas lock(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Oblúk (shackle)
        double bx = 7 * s, by = 11 * s, bw = 10 * s, bh = 6 * s;
        g.strokeArc(bx, by - bh, bw, bh * 2, 0, 180, javafx.scene.shape.ArcType.OPEN);

        // Telo zámku
        g.strokeRoundRect(4 * s, 11 * s, 16 * s, 11 * s, 3 * s, 3 * s);

        // Dierka
        g.strokeOval(10.5 * s, 14.5 * s, 3 * s, 3 * s);
        g.strokeLine(12 * s, 17.5 * s, 12 * s, 20 * s);

        return c;
    }

    // ── 🗝 Kľúč (menu: Heslá) ────────────────────────────────────────────────
    public static Canvas key(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Krúžok kľúča
        g.strokeOval(2 * s, 8 * s, 8 * s, 8 * s);

        // Rukoväť
        g.strokeLine(10 * s, 12 * s, 22 * s, 12 * s);

        // Zúbky
        g.strokeLine(18 * s, 12 * s, 18 * s, 15 * s);
        g.strokeLine(22 * s, 12 * s, 22 * s, 15 * s);

        return c;
    }

    // ── ➕ Pridať heslo (menu: Pridať heslo) ─────────────────────────────────
    public static Canvas add(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Kruh
        g.strokeOval(2 * s, 2 * s, 20 * s, 20 * s);

        // Kríž
        g.strokeLine(12 * s, 7 * s, 12 * s, 17 * s);
        g.strokeLine(7 * s, 12 * s, 17 * s, 12 * s);

        return c;
    }

    // ── 🚪 Odhlásiť (dvere so šípkou) ────────────────────────────────────────
    public static Canvas logout(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Dvere (rám)
        g.strokeRoundRect(2 * s, 3 * s, 12 * s, 18 * s, 2 * s, 2 * s);

        // Šípka von
        g.strokeLine(15 * s, 12 * s, 22 * s, 12 * s);
        g.strokeLine(19 * s, 9 * s, 22 * s, 12 * s);
        g.strokeLine(19 * s, 15 * s, 22 * s, 12 * s);

        return c;
    }

    // ── 🛡 Štít / Shield ─────────────────────────────────────────────────────
    public static Canvas shield(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Tvar štítu
        double[] sx = {12*s, 20*s, 20*s, 12*s, 4*s, 4*s};
        double[] sy = {2*s,  5*s,  12*s, 22*s, 12*s, 5*s};
        g.strokePolygon(sx, sy, 6);

        // Zaškrtnutie vnútri
        g.strokePolyline(
                new double[]{8*s, 11*s, 16*s},
                new double[]{12*s, 16*s, 8*s},
                3
        );

        return c;
    }

    // ── 👤 Používateľ ────────────────────────────────────────────────────────
    public static Canvas user(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Hlava
        g.strokeOval(7 * s, 2 * s, 10 * s, 10 * s);

        // Telo (oblúk)
        g.strokeArc(2 * s, 14 * s, 20 * s, 12 * s, 0, 180, javafx.scene.shape.ArcType.OPEN);

        return c;
    }

    // ── 📋 Kopírovať ─────────────────────────────────────────────────────────
    public static Canvas copy(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Spodný list
        g.strokeRoundRect(3 * s, 7 * s, 13 * s, 15 * s, 2.5 * s, 2.5 * s);

        // Horný list (prekrytý)
        g.setFill(Color.web("#EDE0CC")); // béžové pozadie Vaulty
        g.fillRoundRect(8 * s, 2 * s, 13 * s, 15 * s, 2.5 * s, 2.5 * s);
        g.strokeRoundRect(8 * s, 2 * s, 13 * s, 15 * s, 2.5 * s, 2.5 * s);

        return c;
    }

    // ── 🗑 Vymazať (kôš) ─────────────────────────────────────────────────────
    public static Canvas delete(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Veko
        g.strokeLine(3 * s, 6 * s, 21 * s, 6 * s);
        g.strokeRoundRect(8 * s, 2 * s, 8 * s, 4 * s, 1.5 * s, 1.5 * s);

        // Telo koša
        g.strokeRoundRect(5 * s, 7 * s, 14 * s, 15 * s, 2 * s, 2 * s);

        // Zvislé čiary vo vnútri
        g.strokeLine(9 * s, 10 * s, 9 * s, 18 * s);
        g.strokeLine(12 * s, 10 * s, 12 * s, 18 * s);
        g.strokeLine(15 * s, 10 * s, 15 * s, 18 * s);

        return c;
    }

    // ── 👁 Zobraziť / Skryť heslo ────────────────────────────────────────────
    public static Canvas eye(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Oko (oblúky)
        g.beginPath();
        g.moveTo(2 * s, 12 * s);
        g.bezierCurveTo(6 * s, 5 * s, 18 * s, 5 * s, 22 * s, 12 * s);
        g.bezierCurveTo(18 * s, 19 * s, 6 * s, 19 * s, 2 * s, 12 * s);
        g.stroke();

        // Zrenička
        g.strokeOval(9 * s, 9 * s, 6 * s, 6 * s);

        return c;
    }

    // ── ⚙ Nastavenia (ozubené koleso) ───────────────────────────────────────
    public static Canvas settings(double size, String hexColor) {
        Canvas c = new Canvas(size, size);
        GraphicsContext g = setup(c, size, hexColor);
        double s = size / 24.0;
        g.setLineWidth(1.6 * s);

        // Stredný kruh
        g.strokeOval(9 * s, 9 * s, 6 * s, 6 * s);

        // 8 zúbkov okolo
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45.0);
            double cos = Math.cos(angle), sin = Math.sin(angle);
            double cx = 12 * s, cy = 12 * s;
            double r1 = 8 * s, r2 = 10 * s;
            g.strokeLine(
                    cx + cos * r1, cy + sin * r1,
                    cx + cos * r2, cy + sin * r2
            );
        }

        return c;
    }
}