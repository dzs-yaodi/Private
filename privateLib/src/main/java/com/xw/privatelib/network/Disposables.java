package com.xw.privatelib.network;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 管理订阅对象。
 */
public class Disposables {

    //统一管理subscribe后返回的Disposable
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void  clear() {
        compositeDisposable.clear();
    }

}
