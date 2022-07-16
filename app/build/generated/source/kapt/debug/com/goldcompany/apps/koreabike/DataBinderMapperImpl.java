package com.goldcompany.apps.koreabike;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.goldcompany.apps.koreabike.databinding.FragmentBikeMapBindingImpl;
import com.goldcompany.apps.koreabike.databinding.FragmentHistoryPlaceBindingImpl;
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationBindingImpl;
import com.goldcompany.apps.koreabike.databinding.FragmentNavigationResultBindingImpl;
import com.goldcompany.apps.koreabike.databinding.FragmentSearchAddressBindingImpl;
import com.goldcompany.apps.koreabike.databinding.ItemAppBarBindingImpl;
import com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBindingImpl;
import com.goldcompany.apps.koreabike.databinding.ItemFavoritePlaceListBindingImpl;
import com.goldcompany.apps.koreabike.databinding.ItemNetworkStateBindingImpl;
import com.goldcompany.apps.koreabike.databinding.ItemSearchAddressBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTBIKEMAP = 1;

  private static final int LAYOUT_FRAGMENTHISTORYPLACE = 2;

  private static final int LAYOUT_FRAGMENTNAVIGATION = 3;

  private static final int LAYOUT_FRAGMENTNAVIGATIONRESULT = 4;

  private static final int LAYOUT_FRAGMENTSEARCHADDRESS = 5;

  private static final int LAYOUT_ITEMAPPBAR = 6;

  private static final int LAYOUT_ITEMCATEGORYBUTTON = 7;

  private static final int LAYOUT_ITEMFAVORITEPLACELIST = 8;

  private static final int LAYOUT_ITEMNETWORKSTATE = 9;

  private static final int LAYOUT_ITEMSEARCHADDRESS = 10;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(10);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.fragment_bike_map, LAYOUT_FRAGMENTBIKEMAP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.fragment_history_place, LAYOUT_FRAGMENTHISTORYPLACE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.fragment_navigation, LAYOUT_FRAGMENTNAVIGATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.fragment_navigation_result, LAYOUT_FRAGMENTNAVIGATIONRESULT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.fragment_search_address, LAYOUT_FRAGMENTSEARCHADDRESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.item_app_bar, LAYOUT_ITEMAPPBAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.item_category_button, LAYOUT_ITEMCATEGORYBUTTON);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.item_favorite_place_list, LAYOUT_ITEMFAVORITEPLACELIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.item_network_state, LAYOUT_ITEMNETWORKSTATE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.goldcompany.apps.koreabike.R.layout.item_search_address, LAYOUT_ITEMSEARCHADDRESS);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTBIKEMAP: {
          if ("layout/fragment_bike_map_0".equals(tag)) {
            return new FragmentBikeMapBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_bike_map is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHISTORYPLACE: {
          if ("layout/fragment_history_place_0".equals(tag)) {
            return new FragmentHistoryPlaceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_history_place is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNAVIGATION: {
          if ("layout/fragment_navigation_0".equals(tag)) {
            return new FragmentNavigationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_navigation is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTNAVIGATIONRESULT: {
          if ("layout/fragment_navigation_result_0".equals(tag)) {
            return new FragmentNavigationResultBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_navigation_result is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSEARCHADDRESS: {
          if ("layout/fragment_search_address_0".equals(tag)) {
            return new FragmentSearchAddressBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_search_address is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMAPPBAR: {
          if ("layout/item_app_bar_0".equals(tag)) {
            return new ItemAppBarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_app_bar is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCATEGORYBUTTON: {
          if ("layout/item_category_button_0".equals(tag)) {
            return new ItemCategoryButtonBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_category_button is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMFAVORITEPLACELIST: {
          if ("layout/item_favorite_place_list_0".equals(tag)) {
            return new ItemFavoritePlaceListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_favorite_place_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMNETWORKSTATE: {
          if ("layout/item_network_state_0".equals(tag)) {
            return new ItemNetworkStateBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_network_state is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSEARCHADDRESS: {
          if ("layout/item_search_address_0".equals(tag)) {
            return new ItemSearchAddressBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_search_address is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(7);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "address");
      sKeys.put(2, "description");
      sKeys.put(3, "handler");
      sKeys.put(4, "icon");
      sKeys.put(5, "title");
      sKeys.put(6, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(10);

    static {
      sKeys.put("layout/fragment_bike_map_0", com.goldcompany.apps.koreabike.R.layout.fragment_bike_map);
      sKeys.put("layout/fragment_history_place_0", com.goldcompany.apps.koreabike.R.layout.fragment_history_place);
      sKeys.put("layout/fragment_navigation_0", com.goldcompany.apps.koreabike.R.layout.fragment_navigation);
      sKeys.put("layout/fragment_navigation_result_0", com.goldcompany.apps.koreabike.R.layout.fragment_navigation_result);
      sKeys.put("layout/fragment_search_address_0", com.goldcompany.apps.koreabike.R.layout.fragment_search_address);
      sKeys.put("layout/item_app_bar_0", com.goldcompany.apps.koreabike.R.layout.item_app_bar);
      sKeys.put("layout/item_category_button_0", com.goldcompany.apps.koreabike.R.layout.item_category_button);
      sKeys.put("layout/item_favorite_place_list_0", com.goldcompany.apps.koreabike.R.layout.item_favorite_place_list);
      sKeys.put("layout/item_network_state_0", com.goldcompany.apps.koreabike.R.layout.item_network_state);
      sKeys.put("layout/item_search_address_0", com.goldcompany.apps.koreabike.R.layout.item_search_address);
    }
  }
}
