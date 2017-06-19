package xinyi.com.cztabbar;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by 陈章 on 2017/6/19 0019.
 * func:Drawable合成器
 * 1)setStroke怎么单独控制上下左右的线的宽度？否则button的邻边会显得很粗
 *
 */

public class BgGenerator {
    //获取中间无圆角的背景
    public static Drawable createCenterDrawable(CZTabBar.PropertyBuilder builder){
        StateListDrawable stateListDrawable = new StateListDrawable();

        GradientDrawable normalGradientDrawable = new GradientDrawable();
        normalGradientDrawable.setColor(builder.unCheckedColor);
        normalGradientDrawable.setStroke((int) builder.borderWidth,builder.borderColor);

        GradientDrawable statusGradientDrawable = new GradientDrawable();
        statusGradientDrawable.setColor(builder.checkedColor);
        statusGradientDrawable.setStroke((int) builder.borderWidth,builder.borderColor);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, statusGradientDrawable);

        // View.EMPTY_STATE_SET
        stateListDrawable.addState(new int[] {},  normalGradientDrawable);

        return stateListDrawable;
    }


    //获取左边可能有圆角的背景
    public static Drawable createLeftDrawable(  CZTabBar.PropertyBuilder builder){
        StateListDrawable stateListDrawable = new StateListDrawable();

        GradientDrawable normalGradientDrawable = new GradientDrawable();
        normalGradientDrawable.setColor(builder.unCheckedColor);
        normalGradientDrawable.setCornerRadii(new float[]{builder.cornerRadius,builder.cornerRadius,0f,0f,0f,0f,builder.cornerRadius,builder.cornerRadius});
        normalGradientDrawable.setStroke((int) builder.borderWidth,builder.borderColor);

        GradientDrawable statusGradientDrawable = new GradientDrawable();
        statusGradientDrawable.setColor(builder.checkedColor);
        statusGradientDrawable.setCornerRadii(new float[]{builder.cornerRadius,builder.cornerRadius,0f,0f,0f,0f,builder.cornerRadius,builder.cornerRadius});
        statusGradientDrawable.setStroke((int) builder.borderWidth,builder.borderColor);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, statusGradientDrawable);

        // View.EMPTY_STATE_SET
        stateListDrawable.addState(new int[] {},  normalGradientDrawable);

        return stateListDrawable;
    }


    //获取右边可能有圆角的背景
    public static Drawable createRightDrawable( CZTabBar.PropertyBuilder builder){
        StateListDrawable stateListDrawable = new StateListDrawable();

        GradientDrawable normalGradientDrawable = new GradientDrawable();
        normalGradientDrawable.setColor(builder.unCheckedColor);
        normalGradientDrawable.setCornerRadii(new float[]{0f,0f,builder.cornerRadius,builder.cornerRadius,builder.cornerRadius,builder.cornerRadius,0f,0f});
        normalGradientDrawable.setStroke((int) builder.borderWidth,builder.borderColor);

        GradientDrawable statusGradientDrawable = new GradientDrawable();
        statusGradientDrawable.setColor(builder.checkedColor);
        statusGradientDrawable.setCornerRadii(new float[]{0f,0f,builder.cornerRadius,builder.cornerRadius,builder.cornerRadius,builder.cornerRadius,0f,0f});
        statusGradientDrawable.setStroke((int) builder.borderWidth,builder.borderColor);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, statusGradientDrawable);

        // View.EMPTY_STATE_SET
        stateListDrawable.addState(new int[] {},  normalGradientDrawable);

        return stateListDrawable;
    }


}
