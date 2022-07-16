package com.goldcompany.apps.koreabike.db;

import java.lang.System;

@androidx.room.Database(entities = {com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddress.class}, version = 7, exportSchema = false)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/goldcompany/apps/koreabike/db/KBikeDatabase;", "Landroidx/room/RoomDatabase;", "()V", "userAddressDAO", "Lcom/goldcompany/apps/koreabike/db/history_address/UserHistoryAddressDAO;", "Companion", "app_debug"})
public abstract class KBikeDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.goldcompany.apps.koreabike.db.KBikeDatabase.Companion Companion = null;
    private static final androidx.room.migration.Migration MIGRATION_3_4 = null;
    private static final androidx.room.migration.Migration MIGRATION_4_5 = null;
    private static final androidx.room.migration.Migration MIGRATION_5_6 = null;
    private static final androidx.room.migration.Migration MIGRATION_6_7 = null;
    
    public KBikeDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddressDAO userAddressDAO();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/goldcompany/apps/koreabike/db/KBikeDatabase$Companion;", "", "()V", "MIGRATION_3_4", "Landroidx/room/migration/Migration;", "MIGRATION_4_5", "MIGRATION_5_6", "MIGRATION_6_7", "buildDatabase", "Lcom/goldcompany/apps/koreabike/db/KBikeDatabase;", "context", "Landroid/content/Context;", "getInstance", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.goldcompany.apps.koreabike.db.KBikeDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final com.goldcompany.apps.koreabike.db.KBikeDatabase buildDatabase(android.content.Context context) {
            return null;
        }
    }
}