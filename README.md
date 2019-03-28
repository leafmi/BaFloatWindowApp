# BaFloatWindowApp
这个是一个浮窗工具，暂时不支持对浮窗权限的申请,所需的浮窗权限需要开发者自己添加申请
## 用法：

直接添加依赖
> implementation 'com.ba.support.lib:floatwindow:1.0.3'

## 代码使用：
>      new BaFloatWindow.Build(this)
>                   .setView(R.layout.layout_floatwindow)
>                   .setGravity(Gravity.TOP | Gravity.CENTER)
>                   .setAnimation(R.style.floatwindow_animation)
>                   .setOffset(0, 80)
>                   .build()
>                   .show();
