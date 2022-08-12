// Generated by Dagger (https://dagger.dev).
package com.goldcompany.apps.koreabike.ui.history_place;

import com.goldcompany.apps.koreabike.data.KBikeRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class HistoryPlaceViewModel_Factory implements Factory<HistoryPlaceViewModel> {
  private final Provider<KBikeRepository> kBikeRepositoryProvider;

  public HistoryPlaceViewModel_Factory(Provider<KBikeRepository> kBikeRepositoryProvider) {
    this.kBikeRepositoryProvider = kBikeRepositoryProvider;
  }

  @Override
  public HistoryPlaceViewModel get() {
    return newInstance(kBikeRepositoryProvider.get());
  }

  public static HistoryPlaceViewModel_Factory create(
      Provider<KBikeRepository> kBikeRepositoryProvider) {
    return new HistoryPlaceViewModel_Factory(kBikeRepositoryProvider);
  }

  public static HistoryPlaceViewModel newInstance(KBikeRepository kBikeRepository) {
    return new HistoryPlaceViewModel(kBikeRepository);
  }
}