// Generated by data binding compiler. Do not edit!
package com.goldcompany.apps.koreabike.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.goldcompany.apps.koreabike.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentSearchAddressBinding extends ViewDataBinding {
  @NonNull
  public final View divider;

  @NonNull
  public final MaterialCardView favoriteAddressButton;

  @NonNull
  public final ImageButton navigationBackButton;

  @NonNull
  public final ConstraintLayout parentLayout;

  @NonNull
  public final MaterialButton searchAddressButton;

  @NonNull
  public final AppCompatEditText searchAddressInput;

  @NonNull
  public final RecyclerView searchAddressList;

  @NonNull
  public final ProgressBar searchAddressLoading;

  @NonNull
  public final LinearLayout searchAddressToolBar;

  protected FragmentSearchAddressBinding(Object _bindingComponent, View _root, int _localFieldCount,
      View divider, MaterialCardView favoriteAddressButton, ImageButton navigationBackButton,
      ConstraintLayout parentLayout, MaterialButton searchAddressButton,
      AppCompatEditText searchAddressInput, RecyclerView searchAddressList,
      ProgressBar searchAddressLoading, LinearLayout searchAddressToolBar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.divider = divider;
    this.favoriteAddressButton = favoriteAddressButton;
    this.navigationBackButton = navigationBackButton;
    this.parentLayout = parentLayout;
    this.searchAddressButton = searchAddressButton;
    this.searchAddressInput = searchAddressInput;
    this.searchAddressList = searchAddressList;
    this.searchAddressLoading = searchAddressLoading;
    this.searchAddressToolBar = searchAddressToolBar;
  }

  @NonNull
  public static FragmentSearchAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_search_address, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSearchAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentSearchAddressBinding>inflateInternal(inflater, R.layout.fragment_search_address, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentSearchAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_search_address, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSearchAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentSearchAddressBinding>inflateInternal(inflater, R.layout.fragment_search_address, null, false, component);
  }

  public static FragmentSearchAddressBinding bind(@NonNull View view) {
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
  public static FragmentSearchAddressBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentSearchAddressBinding)bind(component, view, R.layout.fragment_search_address);
  }
}