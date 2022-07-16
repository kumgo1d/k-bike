package com.goldcompany.apps.koreabike.navigation;

import java.lang.System;

@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0007J\b\u0010\u0019\u001a\u00020\u0018H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u00020\b8GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00020\u000e8G@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u00020\u00158\u0002@\u0002X\u0083.\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0002\u00a8\u0006\u001a"}, d2 = {"Lcom/goldcompany/apps/koreabike/navigation/NavigationViewModelTest;", "", "()V", "dao", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddressDAO;", "database", "Lcom/goldcompany/apps/koreabike/db/KBikeDatabase;", "instantExecutorRule", "Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "getInstantExecutorRule", "()Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "setInstantExecutorRule", "(Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;)V", "mainCoroutineRule", "Lcom/goldcompany/apps/koreabike/MainCoroutineRule;", "getMainCoroutineRule$annotations", "getMainCoroutineRule", "()Lcom/goldcompany/apps/koreabike/MainCoroutineRule;", "setMainCoroutineRule", "(Lcom/goldcompany/apps/koreabike/MainCoroutineRule;)V", "viewModel", "Lcom/goldcompany/apps/koreabike/ui/navigation/NavigationViewModel;", "getViewModel$annotations", "getNavigationPathTest", "", "setup", "app_debug"})
@org.junit.runner.RunWith(value = org.robolectric.RobolectricTestRunner.class)
@org.robolectric.annotation.Config(sdk = {android.os.Build.VERSION_CODES.O})
public final class NavigationViewModelTest {
    @org.jetbrains.annotations.NotNull()
    private androidx.arch.core.executor.testing.InstantTaskExecutorRule instantExecutorRule;
    @org.jetbrains.annotations.NotNull()
    private com.goldcompany.apps.koreabike.MainCoroutineRule mainCoroutineRule;
    private com.goldcompany.apps.koreabike.db.KBikeDatabase database;
    private com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddressDAO dao;
    private com.goldcompany.apps.koreabike.ui.navigation.NavigationViewModel viewModel;
    
    public NavigationViewModelTest() {
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
    private static void getViewModel$annotations() {
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    @org.junit.Test()
    public final void getNavigationPathTest() {
    }
}