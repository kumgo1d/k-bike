// Generated by Dagger (https://dagger.dev).
package com.goldcompany.apps.koreabike.ui.navigation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NavigationViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static NavigationViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(NavigationViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final NavigationViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new NavigationViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
