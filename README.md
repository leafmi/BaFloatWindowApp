# BaFloatWindowApp
这个是一个浮窗工具，暂时不支持对浮窗权限的申请,所需的浮窗权限需要开发者自己添加申请
## 用法：

直接添加依赖
> implementation 'com.ba.support.lib:floatwindow:1.0.4'

## 代码使用：
>      new BaFloatWindow.Build(this)
>                   .setView(R.layout.layout_floatwindow)
>                   .setGravity(Gravity.TOP | Gravity.CENTER)
>                   .setAnimation(R.style.floatwindow_animation)
>                   .setOffset(0, 80)
>                   .build()
>                   .show();

## 版本记录：
v1.0.6
- 支持添加点击事件
- 修改代码混淆导致不能用问题
v1.0.4
- 支持悬浮窗拖动

v1.0.3
- 支持动画
