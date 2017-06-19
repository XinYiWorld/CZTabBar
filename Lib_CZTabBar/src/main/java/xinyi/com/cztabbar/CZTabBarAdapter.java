package xinyi.com.cztabbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xinyi.com.cztabbar.interfaces.IOptionBean;
import xinyi.com.cztabbar.interfaces.ITab;

/**
 * Created by 陈章 on 2017/6/17 0017.
 * func:指定
 */

public class CZTabBarAdapter<T extends IOptionBean> extends BaseAdapter implements ITab{
    private Context mContext;
    private List<T> options;
    private Map<Integer,RadioButton> views;
    private CZTabBar czTabBar;
    private CZTabBar.PropertyBuilder propertyBuilder;
    private CZTabBar.OnTabChangedListener onTabChangedListener;

    public CZTabBarAdapter(Context mContext, List<T> options) {
        this.mContext = mContext;
        this.options = options;
        views = new HashMap<>();
    }

    public CZTabBarAdapter(Context mContext, T[] options) {
        this(mContext, Arrays.asList(options));
    }

    public void onAttachedToParent(CZTabBar czTabBar){
        this.czTabBar = czTabBar;
        onTabChangedListener = czTabBar.getOnTabChangedListener();
        propertyBuilder = czTabBar.getBuilder();
        if(propertyBuilder == null) return;

    }

    @Override
    public int getCount() {
        return options == null ? 0 : options.size();
    }

    @Override
    public Object getItem(int position) {
        return options.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    public View getView(int position,int totalItemCount){
        T optionBean = options.get(position);
        final RadioButton btn;
        if(!views.containsKey(position)){
            btn  = new RadioButton(mContext);
            //背景
            btn.setButtonDrawable(null);
            Drawable bg;
            if(position == 0){
                bg = BgGenerator.createLeftDrawable(propertyBuilder);
            }else if(position == totalItemCount - 1 ){
                bg = BgGenerator.createRightDrawable( propertyBuilder);
            }else{
                bg = BgGenerator.createCenterDrawable(propertyBuilder);
            }
            btn.setBackgroundDrawable(bg);

            //文字
            btn.setTextColor(propertyBuilder.txtColor);
            btn.setGravity(propertyBuilder.txtGravity);


            btn.setText(optionBean.getTitle());
            views.put(position,btn);
        }else{
            btn =  views.get(position);
        }

        btn.setTag(position);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < views.size(); i++) {
                    RadioButton item = views.get(i);
                    if(item != btn){
                        item.setChecked(false);
                    }
                }
                if(onTabChangedListener != null){
                    onTabChangedListener.onTabChanged((Integer) btn.getTag());
                }
            }
        });
        return btn;
    }

    public List<RadioButton> getViews() {
        return new ArrayList<RadioButton>(views.values());
    }

    @Override
    public void setCurrentTab(int position) {
        for (int i = 0; i < views.size(); i++) {
            RadioButton item = views.get(i);
                item.setChecked(false);
        }
        views.get(position).setChecked(true);
    }
}
