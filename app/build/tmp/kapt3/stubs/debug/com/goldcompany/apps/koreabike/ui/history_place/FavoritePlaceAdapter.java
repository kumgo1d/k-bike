package com.goldcompany.apps.koreabike.ui.history_place;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/history_place/FavoritePlaceAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "Lcom/goldcompany/apps/koreabike/ui/history_place/FavoritePlaceAdapter$ViewHolder;", "viewModel", "Lcom/goldcompany/apps/koreabike/ui/history_place/HistoryPlaceViewModel;", "(Lcom/goldcompany/apps/koreabike/ui/history_place/HistoryPlaceViewModel;)V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
public final class FavoritePlaceAdapter extends androidx.recyclerview.widget.ListAdapter<com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress, com.goldcompany.apps.koreabike.ui.history_place.FavoritePlaceAdapter.ViewHolder> {
    private final com.goldcompany.apps.koreabike.ui.history_place.HistoryPlaceViewModel viewModel = null;
    
    public FavoritePlaceAdapter(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.ui.history_place.HistoryPlaceViewModel viewModel) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.goldcompany.apps.koreabike.ui.history_place.FavoritePlaceAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.ui.history_place.FavoritePlaceAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/history_place/FavoritePlaceAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/goldcompany/apps/koreabike/databinding/ItemFavoritePlaceListBinding;", "(Lcom/goldcompany/apps/koreabike/ui/history_place/FavoritePlaceAdapter;Lcom/goldcompany/apps/koreabike/databinding/ItemFavoritePlaceListBinding;)V", "setDeleteButtonListener", "", "item", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "setItemClickListener", "setList", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.goldcompany.apps.koreabike.databinding.ItemFavoritePlaceListBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.goldcompany.apps.koreabike.databinding.ItemFavoritePlaceListBinding binding) {
            super(null);
        }
        
        public final void setList(@org.jetbrains.annotations.NotNull()
        com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress item) {
        }
        
        private final void setDeleteButtonListener(com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress item) {
        }
        
        private final void setItemClickListener(com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress item) {
        }
    }
}