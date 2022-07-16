package com.goldcompany.apps.koreabike.ui.bike_map;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0004\b\u0007\u0018\u0000 32\u00020\u00012\u00020\u0002:\u00013B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001a\u0010&\u001a\u00020\u001b2\u0006\u0010\'\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010(\u001a\u00020\u001b2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\b\u0010+\u001a\u00020\u001bH\u0002J\b\u0010,\u001a\u00020\u001bH\u0002J\b\u0010-\u001a\u00020\u001bH\u0002J\u0018\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000200H\u0002J\b\u00102\u001a\u00020\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015\u00a8\u00064"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/bike_map/BikeMapFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/naver/maps/map/OnMapReadyCallback;", "()V", "binding", "Lcom/goldcompany/apps/koreabike/databinding/FragmentBikeMapBinding;", "handler", "Lcom/goldcompany/apps/koreabike/ui/bike_map/BikeMapHandler;", "locationMarker", "Lcom/naver/maps/map/overlay/Marker;", "locationSource", "Lcom/naver/maps/map/util/FusedLocationSource;", "naverMap", "Lcom/naver/maps/map/NaverMap;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "viewModel", "Lcom/goldcompany/apps/koreabike/ui/bike_map/BikeMapViewModel;", "getViewModel", "()Lcom/goldcompany/apps/koreabike/ui/bike_map/BikeMapViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkLocationPermission", "", "getAddress", "", "initMapSettings", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "onViewCreated", "view", "setCameraPosition", "address", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "setCameraPositionToMyLocation", "setUpSearchAddress", "setUpSearchNavigation", "setUserLocationMarker", "latitude", "", "longitude", "startMap", "Companion", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class BikeMapFragment extends androidx.fragment.app.Fragment implements com.naver.maps.map.OnMapReadyCallback {
    @org.jetbrains.annotations.NotNull()
    public static final com.goldcompany.apps.koreabike.ui.bike_map.BikeMapFragment.Companion Companion = null;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private com.naver.maps.map.util.FusedLocationSource locationSource;
    private com.goldcompany.apps.koreabike.databinding.FragmentBikeMapBinding binding;
    private com.naver.maps.map.NaverMap naverMap;
    private com.naver.maps.map.overlay.Marker locationMarker;
    private final kotlin.Lazy viewModel$delegate = null;
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> requestPermissionLauncher = null;
    private final com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler handler = null;
    
    public BikeMapFragment() {
        super();
    }
    
    private final com.goldcompany.apps.koreabike.ui.bike_map.BikeMapViewModel getViewModel() {
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
    
    private final void startMap() {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpSearchNavigation() {
    }
    
    private final void setUpSearchAddress() {
    }
    
    @java.lang.Override()
    public void onMapReady(@org.jetbrains.annotations.NotNull()
    com.naver.maps.map.NaverMap naverMap) {
    }
    
    private final void initMapSettings() {
    }
    
    private final void getAddress() {
    }
    
    private final void setCameraPosition(com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress address) {
    }
    
    private final boolean checkLocationPermission() {
        return false;
    }
    
    private final void setUserLocationMarker(double latitude, double longitude) {
    }
    
    private final void setCameraPositionToMyLocation() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/bike_map/BikeMapFragment$Companion;", "", "()V", "LOCATION_PERMISSION_REQUEST_CODE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}