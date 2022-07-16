package com.goldcompany.apps.koreabike.ui.bike_map;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/goldcompany/apps/koreabike/ui/bike_map/BikeMapHandler;", "", "checkPermissionAndGetMyLocation", "", "setCategoryMarkOverlay", "code", "", "app_debug"})
public abstract interface BikeMapHandler {
    
    public abstract void setCategoryMarkOverlay(@org.jetbrains.annotations.NotNull()
    java.lang.String code);
    
    public abstract void checkPermissionAndGetMyLocation();
}