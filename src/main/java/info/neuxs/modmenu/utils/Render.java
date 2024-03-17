package info.neuxs.modmenu.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Render {

    private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public static void drawLine(float x1, float y1, float x2, float y2, int width, Color color) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        Gdx.gl.glLineWidth(width);
        shapeRenderer.line(x1, y1, x2, y2);
        shapeRenderer.end();
    }

    public static void drawButton(float x, float y, float width, float height, float borderWidth, Color backgroundColor, Color borderColor) {

    }
}
