// Generated by data binding compiler. Do not edit!
package com.goldcompany.apps.koreabike.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.goldcompany.apps.koreabike.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentHistoryPlaceBinding extends ViewDataBinding {
  @NonNull
  public final ItemAppBarBinding appBar;

  @NonNull
  public final RecyclerView favoriteAddressList;

  protected FragmentHistoryPlaceBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ItemAppBarBinding appBar, RecyclerView favoriteAddressList) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appBar = appBar;
    this.favoriteAddressList = favoriteAddressList;
  }

  @NonNull
  public static FragmentHistoryPlaceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_history_place, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentHistoryPlaceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentHistoryPlaceBinding>inflateInternal(inflater, R.layout.fragment_history_place, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentHistoryPlaceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_history_place, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentHistoryPlaceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentHistoryPlaceBinding>inflateInternal(inflater, R.layout.fragment_history_place, null, false, component);
  }

  public static FragmentHistoryPlaceBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentHistoryPlaceBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentHistoryPlaceBinding)bind(component, view, R.layout.fragment_history_place);
  }
}
