package com.goldcompany.apps.koreabike.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u0004\u0018\u00010\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u00182\u0006\u0010\t\u001a\u00020\u0014H\u0016J\u0019\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ!\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J/\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0014H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'J\u0019\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020*H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006,"}, d2 = {"Lcom/goldcompany/apps/koreabike/data/KBikeRepository;", "Lcom/goldcompany/apps/koreabike/data/BaseRepository;", "kBikeRemoteDataSource", "Lcom/goldcompany/apps/koreabike/data/KBikeRemoteDataSource;", "kBikeLocalDataSource", "Lcom/goldcompany/apps/koreabike/data/KBikeLocalDataSource;", "(Lcom/goldcompany/apps/koreabike/data/KBikeRemoteDataSource;Lcom/goldcompany/apps/koreabike/data/KBikeLocalDataSource;)V", "deleteAddress", "", "address", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "(Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAddress", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAddress", "", "getNavigationPath", "Lcom/goldcompany/apps/koreabike/util/Resource;", "Lcom/goldcompany/apps/koreabike/data/driving/ResultPath;", "start", "", "end", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSearchAddressStream", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/goldcompany/apps/koreabike/data/search_address/AddressItem;", "insertAddress", "searchAddress", "Lcom/goldcompany/apps/koreabike/data/search_address/Addresses;", "page", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNearbyPlacesMarker", "Lcom/goldcompany/apps/koreabike/util/Result;", "Lcom/goldcompany/apps/koreabike/data/place_marker/PlaceMarker;", "code", "longitude", "latitude", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAddressUnselect", "date", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@javax.inject.Singleton()
public final class KBikeRepository implements com.goldcompany.apps.koreabike.data.BaseRepository {
    private final com.goldcompany.apps.koreabike.data.KBikeRemoteDataSource kBikeRemoteDataSource = null;
    private final com.goldcompany.apps.koreabike.data.KBikeLocalDataSource kBikeLocalDataSource = null;
    
    @javax.inject.Inject()
    public KBikeRepository(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.data.KBikeRemoteDataSource kBikeRemoteDataSource, @org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.data.KBikeLocalDataSource kBikeLocalDataSource) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object searchAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address, int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.data.search_address.Addresses> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.goldcompany.apps.koreabike.data.search_address.AddressItem>> getSearchAddressStream(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object searchNearbyPlacesMarker(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String latitude, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.util.Result<com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getNavigationPath(@org.jetbrains.annotations.NotNull()
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    java.lang.String end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.util.Resource<com.goldcompany.apps.koreabike.data.driving.ResultPath>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getAllAddress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getAddress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object updateAddressUnselect(long date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insertAddress(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object deleteAddress(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress address, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}