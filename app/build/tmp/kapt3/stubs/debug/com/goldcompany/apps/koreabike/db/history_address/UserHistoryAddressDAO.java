package com.goldcompany.apps.koreabike.db.history_address;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddressDAO;", "", "delete", "", "item", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;", "(Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAddress", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "insert", "updateAddressUnselect", "date", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface UserHistoryAddressDAO {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from user_address ORDER BY date DESC")
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from user_address WHERE selected = 1 LIMIT 1")
    public abstract java.lang.Object getAddress(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "UPDATE user_address SET selected = 0 WHERE date = :date")
    public abstract java.lang.Object updateAddressUnselect(long date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Delete()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}