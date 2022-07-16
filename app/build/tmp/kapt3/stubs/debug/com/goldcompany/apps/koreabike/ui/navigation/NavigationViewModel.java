package com.goldcompany.apps.koreabike.ui.navigation;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\n2\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020\u0014J\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010$\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001b\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/navigation/NavigationViewModel;", "Landroidx/lifecycle/ViewModel;", "kBikeRepository", "Lcom/goldcompany/apps/koreabike/data/KBikeRepository;", "(Lcom/goldcompany/apps/koreabike/data/KBikeRepository;)V", "_resultMessage", "Landroidx/lifecycle/MutableLiveData;", "", "currentQuery", "currentSearchResult", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/goldcompany/apps/koreabike/data/search_address/AddressItem;", "endAddress", "Lcom/goldcompany/apps/koreabike/ui/navigation/NavAddress;", "getEndAddress", "()Landroidx/lifecycle/MutableLiveData;", "endAddress$delegate", "Lkotlin/Lazy;", "isStart", "", "isStart$delegate", "resultMessage", "Landroidx/lifecycle/LiveData;", "getResultMessage", "()Landroidx/lifecycle/LiveData;", "startAddress", "getStartAddress", "startAddress$delegate", "getNavigationPath", "Lcom/goldcompany/apps/koreabike/data/driving/ResultPath;", "start", "end", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isAddressCorrect", "searchAddress", "address", "app_debug"})
public final class NavigationViewModel extends androidx.lifecycle.ViewModel {
    private final com.goldcompany.apps.koreabike.data.KBikeRepository kBikeRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy isStart$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy startAddress$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy endAddress$delegate = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _resultMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> resultMessage = null;
    private java.lang.String currentQuery;
    private kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.goldcompany.apps.koreabike.data.search_address.AddressItem>> currentSearchResult;
    
    @javax.inject.Inject()
    public NavigationViewModel(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.data.KBikeRepository kBikeRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isStart() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.goldcompany.apps.koreabike.ui.navigation.NavAddress> getStartAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.goldcompany.apps.koreabike.ui.navigation.NavAddress> getEndAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getResultMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.goldcompany.apps.koreabike.data.search_address.AddressItem>> searchAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getNavigationPath(@org.jetbrains.annotations.NotNull()
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    java.lang.String end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.goldcompany.apps.koreabike.data.driving.ResultPath>> continuation) {
        return null;
    }
    
    public final boolean isAddressCorrect() {
        return false;
    }
}