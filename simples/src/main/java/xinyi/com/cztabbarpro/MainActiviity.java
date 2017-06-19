package xinyi.com.cztabbarpro;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import xinyi.com.cztabbar.CZTabBar;
import xinyi.com.cztabbar.CZTabBarAdapter;

/**
 * Created by 陈章 on 2017/6/17 0017.
 * func:
 */

public class MainActiviity extends Activity implements CZTabBar.OnTabChangedListener {
    private CZTabBar cztab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cztab = (CZTabBar) findViewById(R.id.cztab);
        cztab.setBuilder(new CZTabBar.PropertyBuilder()
                .cornerRadius(20)
                .borderColor(Color.BLACK)
                .borderWidth(1)
                .txtColor(Color.BLACK)
                .txtGravity(Gravity.CENTER)
                .widthMode(CZTabBar.WidthMode.EQUAL)        //选项卡的宽度策略
                .build());
        cztab.setOnTabChangedListener(this);
        

        MyOptionBean[] myOptionBeen = {new MyOptionBean(),new MyOptionBean(),new MyOptionBean()};
        CZTabBarAdapter<MyOptionBean> adapter = new CZTabBarAdapter<>(this, myOptionBeen);
        cztab.setAdapter(adapter);

    }


    @Override
    public void onTabChanged(int position) {
        Toast.makeText(this, "p = " + position, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cztab.setCurrentTab(0);
            }
        },2000);
    }
}
