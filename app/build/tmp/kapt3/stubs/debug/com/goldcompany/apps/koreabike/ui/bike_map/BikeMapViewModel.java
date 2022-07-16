package com.goldcompany.apps.koreabike.ui.bike_map;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J)\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aR!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\'\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u000e\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/bike_map/BikeMapViewModel;", "Landroidx/lifecycle/ViewModel;", "kBikeRepository", "Lcom/goldcompany/apps/koreabike/data/KBikeRepository;", "(Lcom/goldcompany/apps/koreabike/data/KBikeRepository;)V", "isMarked", "Landroidx/lifecycle/MutableLiveData;", "", "()Landroidx/lifecycle/MutableLiveData;", "isMarked$delegate", "Lkotlin/Lazy;", "markers", "", "Lcom/naver/maps/map/overlay/Marker;", "getMarkers", "markers$delegate", "getAddress", "Lkotlinx/coroutines/flow/Flow;", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNearbyPlacesMarker", "", "code", "", "longitude", "latitude", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class BikeMapViewModel extends androidx.lifecycle.ViewModel {
    private final com.goldcompany.apps.koreabike.data.KBikeRepository kBikeRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy markers$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy isMarked$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.goldcompany.apps.koreabike.ui.bike_map.BikeMapViewModel.Companion Companion = null;
    private static final int MARKER_WIDTH = 70;
    private static final int MARKER_HEIGHT = 100;
    private static final java.lang.String PHARMACY = "PM9";
    private static final java.lang.String CONVENIENCE_STORE = "CS2";
    private static final java.lang.String CAFE = "CE7";
    private static final java.lang.String ACCOMMODATION = "AD5";
    
    @javax.inject.Inject()
    public BikeMapViewModel(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.data.KBikeRepository kBikeRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.naver.maps.map.overlay.Marker>> getMarkers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isMarked() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchNearbyPlacesMarker(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String latitude, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAddress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress>> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/bike_map/BikeMapViewModel$Companion;", "", "()V", "ACCOMMODATION", "", "CAFE", "CONVENIENCE_STORE", "MARKER_HEIGHT", "", "MARKER_WIDTH", "PHARMACY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}