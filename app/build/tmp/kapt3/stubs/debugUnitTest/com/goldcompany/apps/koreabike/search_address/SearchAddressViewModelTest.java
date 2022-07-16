package com.goldcompany.apps.koreabike.search_address;

import java.lang.System;

@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0007J\b\u0010\u0017\u001a\u00020\u0016H\u0007R\u001c\u0010\u0003\u001a\u00020\u00048GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u00020\n8G@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u00118\u0002@\u0002X\u0083.\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0002R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/goldcompany/apps/koreabike/search_address/SearchAddressViewModelTest;", "", "()V", "instantExecutorRule", "Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "getInstantExecutorRule", "()Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "setInstantExecutorRule", "(Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;)V", "mainCoroutineRule", "Lcom/goldcompany/apps/koreabike/MainCoroutineRule;", "getMainCoroutineRule$annotations", "getMainCoroutineRule", "()Lcom/goldcompany/apps/koreabike/MainCoroutineRule;", "setMainCoroutineRule", "(Lcom/goldcompany/apps/koreabike/MainCoroutineRule;)V", "repository", "Lcom/goldcompany/apps/koreabike/data/KBikeRepository;", "getRepository$annotations", "viewModel", "Lcom/goldcompany/apps/koreabike/ui/search_address/SearchAddressViewModel;", "searchAddress_pagingDataLoaded", "", "setup", "app_debug"})
@org.junit.runner.RunWith(value = androidx.test.ext.junit.runners.AndroidJUnit4.class)
@org.robolectric.annotation.Config(sdk = {android.os.Build.VERSION_CODES.O})
public final class SearchAddressViewModelTest {
    @org.jetbrains.annotations.NotNull()
    private androidx.arch.core.executor.testing.InstantTaskExecutorRule instantExecutorRule;
    @org.jetbrains.annotations.NotNull()
    private com.goldcompany.apps.koreabike.MainCoroutineRule mainCoroutineRule;
    private com.goldcompany.apps.koreabike.ui.search_address.SearchAddressViewModel viewModel;
    private com.goldcompany.apps.koreabike.data.KBikeRepository repository;
    
    public SearchAddressViewModelTest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.junit.Rule(order = 0)
    public final androidx.arch.core.executor.testing.InstantTaskExecutorRule getInstantExecutorRule() {
        return null;
    }
    
    public final void setInstantExecutorRule(@org.jetbrains.annotations.NotNull()
    androidx.arch.core.executor.testing.InstantTaskExecutorRule p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.junit.Rule(order = 1)
    public final com.goldcompany.apps.koreabike.MainCoroutineRule getMainCoroutineRule() {
        return null;
    }
    
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @java.lang.Deprecated()
    public static void getMainCoroutineRule$annotations() {
    }
    
    public final void setMainCoroutineRule(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.MainCoroutineRule p0) {
    }
    
    @io.mockk.impl.annotations.MockK()
    @java.lang.Deprecated()
    private static void getRepository$annotations() {
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    @org.junit.Test()
    public final void searchAddress_pagingDataLoaded() {
    }
}