package com.goldcompany.apps.koreabike.databinding;
import com.goldcompany.apps.koreabike.R;
import com.goldcompany.apps.koreabike.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentBikeMapBindingImpl extends FragmentBikeMapBinding implements com.goldcompany.apps.koreabike.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(11);
        sIncludes.setIncludes(1, 
            new String[] {"item_category_button", "item_category_button", "item_category_button", "item_category_button", "item_category_button"},
            new int[] {2, 3, 4, 5, 6},
            new int[] {com.goldcompany.apps.koreabike.R.layout.item_category_button,
                com.goldcompany.apps.koreabike.R.layout.item_category_button,
                com.goldcompany.apps.koreabike.R.layout.item_category_button,
                com.goldcompany.apps.koreabike.R.layout.item_category_button,
                com.goldcompany.apps.koreabike.R.layout.item_category_button});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.map, 7);
        sViewsWithIds.put(R.id.bike_map_search_layout, 8);
        sViewsWithIds.put(R.id.search_address_button, 9);
        sViewsWithIds.put(R.id.search_navigation_path_button, 10);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @Nullable
    private final com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding mboundView11;
    @Nullable
    private final com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding mboundView12;
    @Nullable
    private final com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding mboundView13;
    @Nullable
    private final com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding mboundView14;
    @Nullable
    private final com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding mboundView15;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentBikeMapBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private FragmentBikeMapBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[8]
            , (androidx.fragment.app.FragmentContainerView) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[9]
            , (com.google.android.material.button.MaterialButton) bindings[10]
            );
        this.mapLayout.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView11 = (com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding) bindings[2];
        setContainedBinding(this.mboundView11);
        this.mboundView12 = (com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding) bindings[3];
        setContainedBinding(this.mboundView12);
        this.mboundView13 = (com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding) bindings[4];
        setContainedBinding(this.mboundView13);
        this.mboundView14 = (com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding) bindings[5];
        setContainedBinding(this.mboundView14);
        this.mboundView15 = (com.goldcompany.apps.koreabike.databinding.ItemCategoryButtonBinding) bindings[6];
        setContainedBinding(this.mboundView15);
        setRootTag(root);
        // listeners
        mCallback5 = new com.goldcompany.apps.koreabike.generated.callback.OnClickListener(this, 5);
        mCallback3 = new com.goldcompany.apps.koreabike.generated.callback.OnClickListener(this, 3);
        mCallback4 = new com.goldcompany.apps.koreabike.generated.callback.OnClickListener(this, 4);
        mCallback1 = new com.goldcompany.apps.koreabike.generated.callback.OnClickListener(this, 1);
        mCallback2 = new com.goldcompany.apps.koreabike.generated.callback.OnClickListener(this, 2);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        mboundView11.invalidateAll();
        mboundView12.invalidateAll();
        mboundView13.invalidateAll();
        mboundView14.invalidateAll();
        mboundView15.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView11.hasPendingBindings()) {
            return true;
        }
        if (mboundView12.hasPendingBindings()) {
            return true;
        }
        if (mboundView13.hasPendingBindings()) {
            return true;
        }
        if (mboundView14.hasPendingBindings()) {
            return true;
        }
        if (mboundView15.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.handler == variableId) {
            setHandler((com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler) variable);
        }
        else if (BR.viewModel == variableId) {
            setViewModel((com.goldcompany.apps.koreabike.ui.bike_map.BikeMapViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setHandler(@Nullable com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler Handler) {
        this.mHandler = Handler;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }
    public void setViewModel(@Nullable com.goldcompany.apps.koreabike.ui.bike_map.BikeMapViewModel ViewModel) {
        this.mViewModel = ViewModel;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        mboundView11.setLifecycleOwner(lifecycleOwner);
        mboundView12.setLifecycleOwner(lifecycleOwner);
        mboundView13.setLifecycleOwner(lifecycleOwner);
        mboundView14.setLifecycleOwner(lifecycleOwner);
        mboundView15.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler handler = mHandler;
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView11.setDescription(getRoot().getResources().getString(R.string.my_location));
            this.mboundView11.setIcon(androidx.appcompat.content.res.AppCompatResources.getDrawable(getRoot().getContext(), R.drawable.ic_gps_fixed));
            this.mboundView11.getRoot().setOnClickListener(mCallback1);
            this.mboundView12.setDescription(getRoot().getResources().getString(R.string.pharmacy));
            this.mboundView12.setIcon(androidx.appcompat.content.res.AppCompatResources.getDrawable(getRoot().getContext(), R.drawable.ic_local_hospital));
            this.mboundView12.getRoot().setOnClickListener(mCallback2);
            this.mboundView13.setDescription(getRoot().getResources().getString(R.string.convenience_store));
            this.mboundView13.setIcon(androidx.appcompat.content.res.AppCompatResources.getDrawable(getRoot().getContext(), R.drawable.ic_local_convenience_store));
            this.mboundView13.getRoot().setOnClickListener(mCallback3);
            this.mboundView14.setDescription(getRoot().getResources().getString(R.string.cafe));
            this.mboundView14.setIcon(androidx.appcompat.content.res.AppCompatResources.getDrawable(getRoot().getContext(), R.drawable.ic_local_cafe));
            this.mboundView14.getRoot().setOnClickListener(mCallback4);
            this.mboundView15.setDescription(getRoot().getResources().getString(R.string.accommodation));
            this.mboundView15.setIcon(androidx.appcompat.content.res.AppCompatResources.getDrawable(getRoot().getContext(), R.drawable.ic_king_bed));
            this.mboundView15.getRoot().setOnClickListener(mCallback5);
        }
        executeBindingsOn(mboundView11);
        executeBindingsOn(mboundView12);
        executeBindingsOn(mboundView13);
        executeBindingsOn(mboundView14);
        executeBindingsOn(mboundView15);
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 5: {
                // localize variables for thread safety
                // handler
                com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler handler = mHandler;
                // handler != null
                boolean handlerJavaLangObjectNull = false;



                handlerJavaLangObjectNull = (handler) != (null);
                if (handlerJavaLangObjectNull) {



                    handler.setCategoryMarkOverlay("AD5");
                }
                break;
            }
            case 3: {
                // localize variables for thread safety
                // handler
                com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler handler = mHandler;
                // handler != null
                boolean handlerJavaLangObjectNull = false;



                handlerJavaLangObjectNull = (handler) != (null);
                if (handlerJavaLangObjectNull) {



                    handler.setCategoryMarkOverlay("CS2");
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // handler
                com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler handler = mHandler;
                // handler != null
                boolean handlerJavaLangObjectNull = false;



                handlerJavaLangObjectNull = (handler) != (null);
                if (handlerJavaLangObjectNull) {



                    handler.setCategoryMarkOverlay("CE7");
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // handler
                com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler handler = mHandler;
                // handler != null
                boolean handlerJavaLangObjectNull = false;



                handlerJavaLangObjectNull = (handler) != (null);
                if (handlerJavaLangObjectNull) {


                    handler.checkPermissionAndGetMyLocation();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // handler
                com.goldcompany.apps.koreabike.ui.bike_map.BikeMapHandler handler = mHandler;
                // handler != null
                boolean handlerJavaLangObjectNull = false;



                handlerJavaLangObjectNull = (handler) != (null);
                if (handlerJavaLangObjectNull) {



                    handler.setCategoryMarkOverlay("PM9");
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): handler
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}