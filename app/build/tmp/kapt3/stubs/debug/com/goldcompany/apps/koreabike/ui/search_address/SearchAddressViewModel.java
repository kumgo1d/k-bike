package com.goldcompany.apps.koreabike.ui.search_address;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0010\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/search_address/SearchAddressViewModel;", "Landroidx/lifecycle/ViewModel;", "kBikeRepository", "Lcom/goldcompany/apps/koreabike/data/KBikeRepository;", "(Lcom/goldcompany/apps/koreabike/data/KBikeRepository;)V", "currentQuery", "", "currentSearchResult", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/goldcompany/apps/koreabike/data/search_address/AddressItem;", "insertAddress", "", "userAddress", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "searchAddress", "address", "setCurrentAddress", "updateAddressUnselect", "date", "", "app_debug"})
public final class SearchAddressViewModel extends androidx.lifecycle.ViewModel {
    private final com.goldcompany.apps.koreabike.data.KBikeRepository kBikeRepository = null;
    private java.lang.String currentQuery;
    private kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.goldcompany.apps.koreabike.data.search_address.AddressItem>> currentSearchResult;
    
    @javax.inject.Inject()
    public SearchAddressViewModel(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.data.KBikeRepository kBikeRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.goldcompany.apps.koreabike.data.search_address.AddressItem>> searchAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return null;
    }
    
    public final void setCurrentAddress(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress userAddress) {
    }
    
    private final void updateAddressUnselect(long date) {
    }
    
    private final void insertAddress(com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress userAddress) {
    }
}