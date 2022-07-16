package com.goldcompany.apps.koreabike.ui.navigation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\u001a\u0010 \u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u000eH\u0002J\b\u0010%\u001a\u00020\u000eH\u0002J\b\u0010&\u001a\u00020\u000eH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006\'"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/navigation/NavigationFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/goldcompany/apps/koreabike/ui/navigation/NavigationAdapter;", "binding", "Lcom/goldcompany/apps/koreabike/databinding/FragmentNavigationBinding;", "viewModel", "Lcom/goldcompany/apps/koreabike/ui/navigation/NavigationViewModel;", "getViewModel", "()Lcom/goldcompany/apps/koreabike/ui/navigation/NavigationViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "addressEditTextListener", "", "view", "Landroid/widget/EditText;", "isStart", "", "addressNameObserve", "checkAddressAndNavigateApi", "clearFocus", "observeResultMessage", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onStop", "onViewCreated", "searchAddress", "address", "", "searchNavAddress", "setAdapter", "setTouchListener", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class NavigationFragment extends androidx.fragment.app.Fragment {
    private final kotlin.Lazy viewModel$delegate = null;
    private com.goldcompany.apps.koreabike.databinding.FragmentNavigationBinding binding;
    private com.goldcompany.apps.koreabike.ui.navigation.NavigationAdapter adapter;
    
    public NavigationFragment() {
        super();
    }
    
    private final com.goldcompany.apps.koreabike.ui.navigation.NavigationViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void observeResultMessage() {
    }
    
    private final void setAdapter() {
    }
    
    private final void addressNameObserve() {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void setTouchListener() {
    }
    
    private final void clearFocus() {
    }
    
    private final void searchNavAddress() {
    }
    
    private final void addressEditTextListener(android.widget.EditText view, boolean isStart) {
    }
    
    private final void searchAddress(java.lang.String address) {
    }
    
    private final void checkAddressAndNavigateApi() {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
}