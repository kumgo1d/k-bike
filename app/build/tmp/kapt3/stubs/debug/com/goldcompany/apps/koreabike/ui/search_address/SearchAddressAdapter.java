package com.goldcompany.apps.koreabike.ui.search_address;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/search_address/SearchAddressAdapter;", "Landroidx/paging/PagingDataAdapter;", "Lcom/goldcompany/apps/koreabike/data/search_address/AddressItem;", "Lcom/goldcompany/apps/koreabike/ui/search_address/SearchAddressAdapter$ViewHolder;", "viewModel", "Lcom/goldcompany/apps/koreabike/ui/search_address/SearchAddressViewModel;", "(Lcom/goldcompany/apps/koreabike/ui/search_address/SearchAddressViewModel;)V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
public final class SearchAddressAdapter extends androidx.paging.PagingDataAdapter<com.goldcompany.apps.koreabike.data.search_address.AddressItem, com.goldcompany.apps.koreabike.ui.search_address.SearchAddressAdapter.ViewHolder> {
    private final com.goldcompany.apps.koreabike.ui.search_address.SearchAddressViewModel viewModel = null;
    
    public SearchAddressAdapter(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.ui.search_address.SearchAddressViewModel viewModel) {
        super(null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.goldcompany.apps.koreabike.ui.search_address.SearchAddressAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.ui.search_address.SearchAddressAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/search_address/SearchAddressAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/goldcompany/apps/koreabike/databinding/ItemSearchAddressBinding;", "(Lcom/goldcompany/apps/koreabike/ui/search_address/SearchAddressAdapter;Lcom/goldcompany/apps/koreabike/databinding/ItemSearchAddressBinding;)V", "bind", "", "item", "Lcom/goldcompany/apps/koreabike/data/search_address/AddressItem;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.goldcompany.apps.koreabike.data.search_address.AddressItem item) {
        }
    }
}