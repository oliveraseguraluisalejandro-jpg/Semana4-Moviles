package com.example.myapplication.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

public class MonthlyChartView extends View {

    private double income = 0.0;
    private double expense = 0.0;
    private float animationProgress = 0f;

    private Paint incomePaint;
    private Paint expensePaint;
    private Paint gridPaint;
    private Paint textPaint;
    private Paint labelPaint;
    private Paint emptyPaint;

    private final RectF incomeRect = new RectF();
    private final RectF expenseRect = new RectF();

    public MonthlyChartView(Context context) {
        super(context);
        init();
    }

    public MonthlyChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MonthlyChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        incomePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        incomePaint.setColor(Color.parseColor("#4CAF50")); // Green
        incomePaint.setStyle(Paint.Style.FILL);

        expensePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        expensePaint.setColor(Color.parseColor("#F44336")); // Red
        expensePaint.setStyle(Paint.Style.FILL);

        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setColor(Color.parseColor("#E0E0E0"));
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(2f);
        gridPaint.setPathEffect(new DashPathEffect(new float[]{10f, 10f}, 0));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.parseColor("#333333"));
        textPaint.setTextSize(spToPx(12));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true);

        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setColor(Color.parseColor("#666666"));
        labelPaint.setTextSize(spToPx(12));
        labelPaint.setTextAlign(Paint.Align.CENTER);

        emptyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        emptyPaint.setColor(Color.parseColor("#999999"));
        emptyPaint.setTextSize(spToPx(14));
        emptyPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setData(double income, double expense) {
        this.income = Math.max(0, income);
        this.expense = Math.max(0, expense);

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            animationProgress = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        if (width == 0 || height == 0) return;

        if (income == 0 && expense == 0) {
            canvas.drawText("Sin movimientos en este mes", width / 2f, height / 2f, emptyPaint);
            return;
        }

        float paddingLeft = dpToPx(16);
        float paddingRight = dpToPx(16);
        float paddingTop = dpToPx(32);
        float paddingBottom = dpToPx(48);

        float chartRight = width - paddingRight;
        float chartBottom = height - paddingBottom;
        float chartHeight = chartBottom - paddingTop;
        float chartWidth = chartRight - paddingLeft;

        // Draw background grid lines (3 lines: 100%, 50%, 0%)
        canvas.drawLine(paddingLeft, paddingTop, chartRight, paddingTop, gridPaint);
        canvas.drawLine(paddingLeft, paddingTop + chartHeight / 2f, chartRight, paddingTop + chartHeight / 2f, gridPaint);
        canvas.drawLine(paddingLeft, chartBottom, chartRight, chartBottom, gridPaint);

        double maxValCalculated = Math.max(income, expense);
        double maxVal = maxValCalculated == 0 ? 1 : maxValCalculated;

        float barWidth = Math.min(dpToPx(60), chartWidth / 4f);
        float bar1CenterX = paddingLeft + chartWidth * 0.3f;
        float bar2CenterX = paddingLeft + chartWidth * 0.7f;

        // Income Bar
        float targetIncomeHeight = (float) (income / maxVal) * chartHeight;
        float currentIncomeHeight = targetIncomeHeight * animationProgress;

        incomeRect.set(
                bar1CenterX - barWidth / 2f,
                chartBottom - currentIncomeHeight,
                bar1CenterX + barWidth / 2f,
                chartBottom
        );
        canvas.drawRoundRect(incomeRect, dpToPx(8), dpToPx(8), incomePaint);

        // Expense Bar
        float targetExpenseHeight = (float) (expense / maxVal) * chartHeight;
        float currentExpenseHeight = targetExpenseHeight * animationProgress;

        expenseRect.set(
                bar2CenterX - barWidth / 2f,
                chartBottom - currentExpenseHeight,
                bar2CenterX + barWidth / 2f,
                chartBottom
        );
        canvas.drawRoundRect(expenseRect, dpToPx(8), dpToPx(8), expensePaint);

        // Amounts above bars
        if (income > 0) {
            String incomeStr = String.format(Locale.getDefault(), "S/. %.2f", income);
            canvas.drawText(incomeStr, bar1CenterX, chartBottom - currentIncomeHeight - dpToPx(8), textPaint);
        }
        if (expense > 0) {
            String expenseStr = String.format(Locale.getDefault(), "S/. %.2f", expense);
            canvas.drawText(expenseStr, bar2CenterX, chartBottom - currentExpenseHeight - dpToPx(8), textPaint);
        }

        // Labels below bars
        double total = income + expense;
        String incomePercent = total > 0 ? String.format(Locale.getDefault(), " (%.0f%%)", (income / total) * 100) : "";
        String expensePercent = total > 0 ? String.format(Locale.getDefault(), " (%.0f%%)", (expense / total) * 100) : "";

        canvas.drawText("Ingresos" + incomePercent, bar1CenterX, chartBottom + dpToPx(20), labelPaint);
        canvas.drawText("Egresos" + expensePercent, bar2CenterX, chartBottom + dpToPx(20), labelPaint);
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private float spToPx(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
