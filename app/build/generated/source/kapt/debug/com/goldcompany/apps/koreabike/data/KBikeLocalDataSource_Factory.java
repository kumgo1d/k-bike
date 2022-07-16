// Generated by Dagger (https://dagger.dev).
package com.goldcompany.apps.koreabike.data;

import com.goldcompany.apps.koreabike.db.history_address.UserHistoryAddressDAO;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import javax.inject.Provider;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class KBikeLocalDataSource_Factory implements Factory<KBikeLocalDataSource> {
  private final Provider<UserHistoryAddressDAO> addressDaoProvider;

  public KBikeLocalDataSource_Factory(Provider<UserHistoryAddressDAO> addressDaoProvider) {
    this.addressDaoProvider = addressDaoProvider;
  }

  @Override
  public KBikeLocalDataSource get() {
    return newInstance(addressDaoProvider.get());
  }

  public static KBikeLocalDataSource_Factory create(
      Provider<UserHistoryAddressDAO> addressDaoProvider) {
    return new KBikeLocalDataSource_Factory(addressDaoProvider);
  }

  public static KBikeLocalDataSource newInstance(UserHistoryAddressDAO addressDao) {
    return new KBikeLocalDataSource(addressDao);
  }
}
