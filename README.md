CZTabBar
===================================
仿TabLayout的选项卡切换，简单易用。

## Results demonstrate (效果演示)
![image](https://github.com/XinYiWorld/CZTabBar/blob/master/result.gif)
## Download (集成指南)
1. first,edit your application build.gradle<br />
```Groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
<br />
2. second,edit your module build.gradle<br />
```Groovy
dependencies {
    ...
    compile 'com.github.XinYiWorld:FormValidator:1.0.0'
}
```


## Use (使用指南)
```Java
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
```
 
## Thanks (特别感谢)
* [AValidations](https://github.com/xiaob/AValidations)

## Contact me (联系我)
* QQ邮箱:1349308479@qq.com

## Reward me (打赏)
  If you think it's helpful for you,you can reward me to show your encourage.(如果你觉得有用可以对我打赏以示鼓励)<br/>
  ![image](https://github.com/XinYiWorld/CZSuperAdapters/blob/master/wx.png)
  
