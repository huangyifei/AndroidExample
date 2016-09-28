## 认识SavedInstance

### onSaveInstanceState时系统做了些什么

在`Activity`被回收之前，系统会调用`onSaveInstanceState(Bundle outState)`来保存`View`的状态到传入的`outState`对象中。

1. 保存Window
2. 保存Fragment
3. 调用外部注册的回调方法

```Java
protected void onSaveInstanceState(Bundle outState) {
    outState.putBundle(WINDOW_HIERARCHY_TAG, mWindow.saveHierarchyState());
    Parcelable p = mFragments.saveAllState();
    if (p != null) {
        outState.putParcelable(FRAGMENTS_TAG, p);
    }
    getApplication().dispatchActivitySaveInstanceState(this, outState);
}
```

### onRestoreInstanceState时系统做了些什么

在`Activity`被重新创建时，`onCreate(Bundle savedInstanceState)`和`onRestoreInstanceState(Bundle savedInstanceState)`会传入保存的状态信息并恢复`View`的状态。

1. onCreate重建Fragment
2. onRestoreInstanceState恢复Window状态

```Java
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (DEBUG_LIFECYCLE) Slog.v(TAG, "onCreate " + this + ": " + savedInstanceState);
        if (mLastNonConfigurationInstances != null) {
            mFragments.restoreLoaderNonConfig(mLastNonConfigurationInstances.loaders);
        }
        if (mActivityInfo.parentActivityName != null) {
            if (mActionBar == null) {
                mEnableDefaultActionBarUp = true;
            } else {
                mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
            }
        }
        if (savedInstanceState != null) {
            Parcelable p = savedInstanceState.getParcelable(FRAGMENTS_TAG);
            mFragments.restoreAllState(p, mLastNonConfigurationInstances != null
                    ? mLastNonConfigurationInstances.fragments : null);
        }
        mFragments.dispatchCreate();
        getApplication().dispatchActivityCreated(this, savedInstanceState);
        if (mVoiceInteractor != null) {
            mVoiceInteractor.attachActivity(this);
        }
        mCalled = true;
    }
```

```Java
protected void onRestoreInstanceState(Bundle savedInstanceState) {
    if (mWindow != null) {
        Bundle windowState = savedInstanceState.getBundle(WINDOW_HIERARCHY_TAG);
        if (windowState != null) {
            mWindow.restoreHierarchyState(windowState);
        }
    }
}
```

### Window在save和restore时对View的处理包括。

1. Save时，遍历View的树状结构调用 `Parcelable onSaveInstanceState()`
2. 以View的id为key在Window的`SparseArray<Parcelable>`中保存这些 `Parcelable`
3. Restore时，Window从`savedInstanceState`获取View的`savedStates`
4. 遍历View的树状结构调用 `onRestoreInstanceState(Parcelable state)`
5. View根据id获取自己的state并恢复

### 小结

1. Save和Restore的机制主要是用于保存和恢复View的
2. 没有id的View是不会被保存状态的
3. 如果id重复，则View的状态会被覆盖
4. 被保存的Fragment会在onCreate中被自动创建和添加到FragmentActivity中
5. 被保存的View不会被自动创建，只是通过id获取savedInstance用于更新View

关于这部分的详细源码分析可参考[Android中SaveState原理分析](http://www.jianshu.com/p/520e6b47c57b)。

## 什么时候会Save和Restore

1. `onSaveInstanceState`在Activity不可见时就会被调用，除非被主动销毁的
2. `onRestoreInstanceState`只有在Activity被动销毁后才会调用，一般系统只会在资源不够时才会销毁Activity。

### 后台Task被销毁
1. 后台应用：应用处于后台，系统资源不够时
2. 后台Task：应用有多个Task，处于后台的Task

>通过InstanceActivityTwo演示一个包含ViewPager的Activity销毁和重建过程
>
>1. `ViewPager`通过`Adapter`重建的`Fragment`，不做特殊处理也不会重复
>2. `ViewPager`会记录销毁时的位置，以及`Fragment`的信息

### Task处于前台时

1. OutOfMemory时被销毁重建
2. FragmentStatePagerAdapter：和其它情况不同，但确实也要考虑Fragment的销毁和重建

>通过InstanceActivityOne演示OOM时的Activity销毁和重建过程
>
>1. 重建时会恢复Task的状态，但只会先重建可见的`Activity`
>2. 在`onCreate`判断是否为恢复的Activity，避免重复添加`Fragment`
>3. `InstanceImageFragment`通过`SaveInstance`保存图片大小

## SavedInstance和应用的内存问题

1. SaveInstantceState不能直接解决内存问题
2. 考虑好Save和Restore的机制，可以在异常时快速的使应用恢复正常状态
   * `Fragment`和`Activity`的`onCreate`中根据`savedInstatnceState`决定获取数据的逻辑
   * 运行时添加的`Fragment`要避免重复添加
   * 自定义的`View`要考虑Save和Restore机制
3. 合理的考虑应用的Task结构，可以利用这一机制节省内存开销
   * 多Task结构的应用可以使内存优先用于前台Task
   * 后台的Task被销毁后，可以依赖SavedInstance重建

##参考文献

* [Android中SaveState原理分析](http://www.jianshu.com/p/520e6b47c57b)
* [参考源码](https://gitlab.ws.netease.com/huangyifei/AndroidExamples.git)