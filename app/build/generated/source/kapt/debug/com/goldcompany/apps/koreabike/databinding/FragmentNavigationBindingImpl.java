package com.goldcompany.apps.koreabike.databinding;
import com.goldcompany.apps.koreabike.R;
import com.goldcompany.apps.koreabike.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentNavigationBindingImpl extends FragmentNavigationBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(9);
        sIncludes.setIncludes(0, 
            new String[] {"item_app_bar"},
            new int[] {1},
            new int[] {com.goldcompany.apps.koreabike.R.layout.item_app_bar});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.navigation_start_edit_layout, 2);
        sViewsWithIds.put(R.id.start, 3);
        sViewsWithIds.put(R.id.navigation_end_edit_layout, 4);
        sViewsWithIds.put(R.id.end, 5);
        sViewsWithIds.put(R.id.navigate_button, 6);
        sViewsWithIds.put(R.id.navigation_address_loading, 7);
        sViewsWithIds.put(R.id.address_recycler_view, 8);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentNavigationBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentNavigationBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.recyclerview.widget.RecyclerView) bindings[8]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[5]
            , (com.google.android.material.button.MaterialButton) bindings[6]
            , (android.widget.ProgressBar) bindings[7]
            , (com.goldcompany.apps.koreabike.databinding.ItemAppBarBinding) bindings[1]
            , (com.google.android.material.card.MaterialCardView) bindings[4]
            , (com.google.android.material.card.MaterialCardView) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[3]
            );
        setContainedBinding(this.navigationAppBar);
        this.parentLayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        navigationAppBar.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (navigationAppBar.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        navigationAppBar.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeNavigationAppBar((com.goldcompany.apps.koreabike.databinding.ItemAppBarBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeNavigationAppBar(com.goldcompany.apps.koreabike.databinding.ItemAppBarBinding NavigationAppBar, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.navigationAppBar.setTitle(getRoot().getResources().getString(R.string.navigation_fragment_title));
        }
        executeBindingsOn(navigationAppBar);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): navigationAppBar
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}