package com.goldcompany.apps.koreabike.databinding;
import com.goldcompany.apps.koreabike.R;
import com.goldcompany.apps.koreabike.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSearchAddressBindingImpl extends FragmentSearchAddressBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.search_address_tool_bar, 1);
        sViewsWithIds.put(R.id.navigation_back_button, 2);
        sViewsWithIds.put(R.id.search_address_input, 3);
        sViewsWithIds.put(R.id.search_address_button, 4);
        sViewsWithIds.put(R.id.favorite_address_button, 5);
        sViewsWithIds.put(R.id.divider, 6);
        sViewsWithIds.put(R.id.search_address_loading, 7);
        sViewsWithIds.put(R.id.search_address_list, 8);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentSearchAddressBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentSearchAddressBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.view.View) bindings[6]
            , (com.google.android.material.card.MaterialCardView) bindings[5]
            , (android.widget.ImageButton) bindings[2]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (com.google.android.material.button.MaterialButton) bindings[4]
            , (androidx.appcompat.widget.AppCompatEditText) bindings[3]
            , (androidx.recyclerview.widget.RecyclerView) bindings[8]
            , (android.widget.ProgressBar) bindings[7]
            , (android.widget.LinearLayout) bindings[1]
            );
        this.parentLayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}