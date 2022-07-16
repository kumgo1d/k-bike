package com.goldcompany.apps.koreabike.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\'\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00132\u0006\u0010\u0004\u001a\u00020\u000fH&J\u0019\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ/\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000fH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J\u0019\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\'"}, d2 = {"Lcom/goldcompany/apps/koreabike/data/BaseRepository;", "", "deleteAddress", "", "address", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "(Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAddress", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAddress", "", "getNavigationPath", "Lcom/goldcompany/apps/koreabike/util/Resource;", "Lcom/goldcompany/apps/koreabike/data/driving/ResultPath;", "start", "", "end", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSearchAddressStream", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/goldcompany/apps/koreabike/data/search_address/AddressItem;", "insertAddress", "searchAddress", "Lcom/goldcompany/apps/koreabike/data/search_address/Addresses;", "page", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNearbyPlacesMarker", "Lcom/goldcompany/apps/koreabike/util/Result;", "Lcom/goldcompany/apps/koreabike/data/place_marker/PlaceMarker;", "code", "longitude", "latitude", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAddressUnselect", "date", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface BaseRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address, int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.data.search_address.Addresses> continuation);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.goldcompany.apps.koreabike.data.search_address.AddressItem>> getSearchAddressStream(@org.jetbrains.annotations.NotNull()
    java.lang.String address);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchNearbyPlacesMarker(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String latitude, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.util.Result<com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNavigationPath(@org.jetbrains.annotations.NotNull()
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    java.lang.String end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.util.Resource<com.goldcompany.apps.koreabike.data.driving.ResultPath>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllAddress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAddress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAddressUnselect(long date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAddress(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAddress(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}