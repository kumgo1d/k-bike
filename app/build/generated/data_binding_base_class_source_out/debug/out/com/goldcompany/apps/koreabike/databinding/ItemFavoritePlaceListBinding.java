// Generated by data binding compiler. Do not edit!
package com.goldcompany.apps.koreabike.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.goldcompany.apps.koreabike.R;
import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemFavoritePlaceListBinding extends ViewDataBinding {
  @NonNull
  public final TextView placeItemAddress;

  @NonNull
  public final ImageButton placeItemDelete;

  @NonNull
  public final ImageView placeItemDrawable;

  @NonNull
  public final TextView placeItemKeyword;

  @Bindable
  protected UserHistoryAddress mAddress;

  protected ItemFavoritePlaceListBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView placeItemAddress, ImageButton placeItemDelete, ImageView placeItemDrawable,
      TextView placeItemKeyword) {
    super(_bindingComponent, _root, _localFieldCount);
    this.placeItemAddress = placeItemAddress;
    this.placeItemDelete = placeItemDelete;
    this.placeItemDrawable = placeItemDrawable;
    this.placeItemKeyword = placeItemKeyword;
  }

  public abstract void setAddress(@Nullable UserHistoryAddress address);

  @Nullable
  public UserHistoryAddress getAddress() {
    return mAddress;
  }

  @NonNull
  public static ItemFavoritePlaceListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_favorite_place_list, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemFavoritePlaceListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemFavoritePlaceListBinding>inflateInternal(inflater, R.layout.item_favorite_place_list, root, attachToRoot, component);
  }

  @NonNull
  public static ItemFavoritePlaceListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_favorite_place_list, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemFavoritePlaceListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemFavoritePlaceListBinding>inflateInternal(inflater, R.layout.item_favorite_place_list, null, false, component);
  }

  public static ItemFavoritePlaceListBinding bind(@NonNull View view) {
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
  public static ItemFavoritePlaceListBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemFavoritePlaceListBinding)bind(component, view, R.layout.item_favorite_place_list);
  }
}
