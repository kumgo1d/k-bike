package com.goldcompany.apps.koreabike.ui.history_place;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/history_place/HistoryPlaceViewModel;", "Landroidx/lifecycle/ViewModel;", "kBikeRepository", "Lcom/goldcompany/apps/koreabike/data/KBikeRepository;", "(Lcom/goldcompany/apps/koreabike/data/KBikeRepository;)V", "_addressList", "Landroidx/lifecycle/LiveData;", "", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "addressList", "getAddressList", "()Landroidx/lifecycle/LiveData;", "deleteAddress", "", "address", "setCurrentAddress", "app_debug"})
public final class HistoryPlaceViewModel extends androidx.lifecycle.ViewModel {
    private final com.goldcompany.apps.koreabike.data.KBikeRepository kBikeRepository = null;
    private final androidx.lifecycle.LiveData<java.util.List<com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress>> _addressList = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress>> addressList = null;
    
    @javax.inject.Inject()
    public HistoryPlaceViewModel(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.data.KBikeRepository kBikeRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress>> getAddressList() {
        return null;
    }
    
    public final void setCurrentAddress(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress address) {
    }
    
    public final void deleteAddress(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress address) {
    }
}