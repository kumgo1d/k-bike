package com.goldcompany.apps.koreabike.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bJE\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\b\b\u0001\u0010\b\u001a\u00020\u00052\b\b\u0003\u0010\t\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/goldcompany/apps/koreabike/api/NaverApiService;", "", "getPath", "Lcom/goldcompany/apps/koreabike/data/driving/ResultPath;", "apiKeyId", "", "apiKey", "start", "goal", "option", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface NaverApiService {
    @org.jetbrains.annotations.NotNull()
    public static final com.goldcompany.apps.koreabike.api.NaverApiService.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "map-direction/v1/driving")
    public abstract java.lang.Object getPath(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "X-NCP-APIGW-API-KEY-ID")
    java.lang.String apiKeyId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "X-NCP-APIGW-API-KEY")
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "start")
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "goal")
    java.lang.String goal, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "option")
    java.lang.String option, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.data.driving.ResultPath> continuation);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/goldcompany/apps/koreabike/api/NaverApiService$Companion;", "", "()V", "create", "Lcom/goldcompany/apps/koreabike/api/NaverApiService;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.goldcompany.apps.koreabike.api.NaverApiService create() {
            return null;
        }
    }
}