package com.goldcompany.apps.koreabike.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\'\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u00142\u0006\u0010\u0017\u001a\u00020\bJ!\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ/\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006$"}, d2 = {"Lcom/goldcompany/apps/koreabike/data/KBikeRemoteDataSource;", "", "kakaoApiService", "Lcom/goldcompany/apps/koreabike/api/KakaoApiService;", "naverApiService", "Lcom/goldcompany/apps/koreabike/api/NaverApiService;", "(Lcom/goldcompany/apps/koreabike/api/KakaoApiService;Lcom/goldcompany/apps/koreabike/api/NaverApiService;)V", "KAKAO_API_KEY", "", "NAVER_API_CLIENT_ID", "NAVER_API_KEY", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getNavigationPath", "Lcom/goldcompany/apps/koreabike/util/Resource;", "Lcom/goldcompany/apps/koreabike/data/driving/ResultPath;", "start", "end", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSearchAddressStream", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/goldcompany/apps/koreabike/data/search_address/AddressItem;", "address", "searchAddress", "Lcom/goldcompany/apps/koreabike/data/search_address/Addresses;", "page", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchNearbyPlacesMarker", "Lcom/goldcompany/apps/koreabike/util/Result;", "Lcom/goldcompany/apps/koreabike/data/place_marker/PlaceMarker;", "code", "longitude", "latitude", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@javax.inject.Singleton()
public final class KBikeRemoteDataSource {
    private final com.goldcompany.apps.koreabike.api.KakaoApiService kakaoApiService = null;
    private final com.goldcompany.apps.koreabike.api.NaverApiService naverApiService = null;
    private final java.lang.String KAKAO_API_KEY = "KakaoAK 27e15ec689adb799d47b3f7ae6a13926";
    private final java.lang.String NAVER_API_CLIENT_ID = "fe7iwsbkl5";
    private final java.lang.String NAVER_API_KEY = "BcecPfuxiMTGSEb74GdR0Z0fZhjIt5k2FQAIKoSQ";
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    
    @javax.inject.Inject()
    public KBikeRemoteDataSource(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.api.KakaoApiService kakaoApiService, @org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.api.NaverApiService naverApiService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address, int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.data.search_address.Addresses> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.goldcompany.apps.koreabike.data.search_address.AddressItem>> getSearchAddressStream(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchNearbyPlacesMarker(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String latitude, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.util.Result<com.goldcompany.apps.koreabike.data.place_marker.PlaceMarker>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getNavigationPath(@org.jetbrains.annotations.NotNull()
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    java.lang.String end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.util.Resource<com.goldcompany.apps.koreabike.data.driving.ResultPath>> continuation) {
        return null;
    }
}