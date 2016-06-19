# MulityClient
第一个集成客户端，将一些经典技术放入其中
##使用DrawLayout创建侧滑菜单栏
###首先对ToolBar进行布局
####添加如下属性
	android:minHeight="?attr/actionBarSize"
    android:background="@color/colorBlue"
    android:id="@+id/toolbar"
    android:popupTheme="@style/ThemeOverlay.AppCompat.Light"
    app:theme="@style/ThemeOverlay.AppCompat.ActionBar"
###对Drawlayout布局
注意layout_gravity