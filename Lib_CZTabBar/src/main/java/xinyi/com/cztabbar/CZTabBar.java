package xinyi.com.cztabbar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import xinyi.com.cztabbar.interfaces.ITab;

/**
 * Created by 陈章 on 2017/6/17 0017.
 * func:
 * 类似于TabLayout的自定义控件
 */
public class CZTabBar  extends LinearLayout implements ITab {
    private Context mContext;
    private PropertyBuilder builder;
    private CZTabBarAdapter czTabBarAdapter;
    private int mWidth;
    private OnTabChangedListener onTabChangedListener;


    public CZTabBar(Context context) {
        this(context,null);
    }

    public CZTabBar(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CZTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);
    }


    public void setAdapter(CZTabBarAdapter czTabBarAdapter){
        this.czTabBarAdapter = czTabBarAdapter;
        czTabBarAdapter.onAttachedToParent(this);
        int itemCount = czTabBarAdapter.getCount();
        for (int i = 0; i < itemCount; i++) {
            View itemView = czTabBarAdapter.getView(i, itemCount);
            addView(itemView);
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height =  LinearLayout.LayoutParams.MATCH_PARENT;
            itemView.setLayoutParams(layoutParams);
        }
    }

    public PropertyBuilder getBuilder() {
        return builder == null ? new PropertyBuilder() : builder;
    }

    public void setBuilder(PropertyBuilder builder) {
        this.builder = builder;
    }

    //属性构造器
    public static class PropertyBuilder{
         int checkedColor;
         int unCheckedColor;
         int borderColor;
         float borderWidth;
         float cornerRadius;
         WidthMode widthMode;
         int txtColor;
         int txtGravity;

        public PropertyBuilder() {
            checkedColor = Color.LTGRAY;
            unCheckedColor = Color.WHITE;
            borderColor = Color.BLACK;
            borderWidth = 0;
            cornerRadius = 0;
            widthMode = WidthMode.EQUAL;
            txtColor = Color.BLACK;
            txtGravity = Gravity.CENTER;
        }

        //选中时的颜色
        public PropertyBuilder checkedColor(int checkedColor){
            this.checkedColor = checkedColor;
            return this;
        }

        //未选中时的颜色
        public PropertyBuilder unCheckedColor(int uncheckedColor){
            this.unCheckedColor = uncheckedColor;
            return this;
        }

        //边框颜色
        public PropertyBuilder borderColor(int borderColor){
            this.borderColor =borderColor;
            return this;
        }

        //边框宽度
        public PropertyBuilder borderWidth(float borderWidth){
            this.borderWidth = borderWidth;
            return this;
        }

        //边框圆角半径
        public PropertyBuilder cornerRadius(float cornerRadius){
            this.cornerRadius = cornerRadius;
            return this;
        }

        //选项卡宽度
        public PropertyBuilder widthMode(WidthMode widthMode){
            this.widthMode = widthMode;
            return this;
        }


        //文字相关
        public PropertyBuilder txtColor(int txtColor){
            this.txtColor = txtColor;
            return this;
        }

        public PropertyBuilder txtGravity(int txtGravity){
            this.txtGravity = txtGravity;
            return this;
        }

        public PropertyBuilder build(){
            return this;
        }
    }


    //测量分重新分配子View的宽度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        List<View> childs = czTabBarAdapter.getViews();
        for (int i = 0; i < childs.size() ; i++) {
            childs.get(i).measure(0,0);
        }
        resetChildWidth(childs);
    }

    private void resetChildWidth(List<View> childs) {
        if(getBuilder().widthMode == WidthMode.EQUAL){
            int childNewWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (mWidth * 1.0f / childs.size()), MeasureSpec.EXACTLY);
            for (int i = 0; i < childs.size() ; i++) {
                childs.get(i).measure(childNewWidthMeasureSpec,0);
            }
        }
    }

    //宽度模式
    public enum  WidthMode{
        WRAP,       //包裹内容
        EQUAL       //按权重平均分配
    }

    //选项卡切换监听
    public interface OnTabChangedListener{
        void onTabChanged(int position);
    }

    public void setOnTabChangedListener(OnTabChangedListener onTabChangedListener) {
        this.onTabChangedListener = onTabChangedListener;
    }

    public OnTabChangedListener getOnTabChangedListener() {
        return onTabChangedListener;
    }

    @Override
    public void setCurrentTab(int position){
        czTabBarAdapter.setCurrentTab(position);
    }
}
