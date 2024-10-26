package com.app.lab5_iot.model;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

// Le pregunté a CHAT-GPT como crear un gráfico circular actualizable

public class CircularTMB extends View {

    private Paint paintBackground;
    private Paint paintForeground;
    private Paint paintText;
    private RectF rect;
    private float porcentaje;
    private String textCenter;

    public CircularTMB(Context context) {
        super(context);
        init();
    }

    public CircularTMB(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paintBackground = new Paint();
        paintBackground.setColor(Color.LTGRAY); // Color de fondo del anillo
        paintBackground.setStyle(Paint.Style.STROKE);
        paintBackground.setStrokeWidth(20);
        paintBackground.setAntiAlias(true);

        paintForeground = new Paint();
        paintForeground.setColor(Color.CYAN); // Color de progreso del anillo
        paintForeground.setStyle(Paint.Style.STROKE);
        paintForeground.setStrokeWidth(20);
        paintForeground.setAntiAlias(true);

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(40);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setAntiAlias(true);

        rect = new RectF();
        porcentaje = 0;
        textCenter = "0 / 0 cal";
    }

    public void setPorcentaje(float porcentaje, String textCenter) {
        this.porcentaje = porcentaje;
        this.textCenter = textCenter;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        float radius = Math.min(width, height) / 2f - 20;

        // Define el área de dibujo del anillo
        rect.set(width / 2f - radius, height / 2f - radius, width / 2f + radius, height / 2f + radius);

        // Dibuja el anillo de fondo
        canvas.drawArc(rect, 0, 360, false, paintBackground);

        // Dibuja el anillo de progreso
        canvas.drawArc(rect, -90, (porcentaje / 100) * 360, false, paintForeground);

        // Dibuja el texto en el centro
        canvas.drawText(textCenter, width / 2f, height / 2f + 10, paintText);
    }

}
