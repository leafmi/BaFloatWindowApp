# BaFloatWindowApp
这个是一个浮窗工具
## 用方：

直接添加依赖
> implementation 'com.ba.support.lib:floatwindow:1.0.3'

##代码使用：
>      new BaFloatWindow.Build(this)
>                   .setView(R.layout.layout_floatwindow)
>                   .setGravity(Gravity.TOP | Gravity.CENTER)
>                   .setAnimation(R.style.floatwindow_animation)
>                   .setOffset(0, 80)
>                   .build()
>                   .show();
