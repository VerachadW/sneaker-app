package com.hannesdorfmann.mosby.mvp;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.AbsParcelableLceViewState;

import org.parceler.Parcels;

public class ParcelerDataLceViewState<D, V extends MvpLceView<D>>
        extends AbsParcelableLceViewState<D, V> {

    public static final Parcelable.Creator<ParcelerDataLceViewState> CREATOR =
            new Parcelable.Creator<ParcelerDataLceViewState>() {
                @Override
                public ParcelerDataLceViewState createFromParcel(Parcel source) {
                    return new ParcelerDataLceViewState(source);
                }

                @Override
                public ParcelerDataLceViewState[] newArray(int size) {
                    return new ParcelerDataLceViewState[size];
                }
            };

    private static final String BUNDLE_PARCELABLE_WORKAROUND =
            "com.hannesdorfmann.mosby.mvp.viewstate.lce.ParcelableLceViewState.workaround";

    public ParcelerDataLceViewState() {}

    private ParcelerDataLceViewState(Parcel source) {
        readFromParcel(source);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        assert loadedData.getClass().isAnnotationPresent(org.parceler.Parcel.class);

        dest.writeParcelable(Parcels.wrap(loadedData), flags);
        super.writeToParcel(dest, flags);
    }

    @Override
    protected void readFromParcel(Parcel source) {
        loadedData = Parcels.unwrap(source.readParcelable(getClassLoader()));
        super.readFromParcel(source);
    }

    /**
     * Get the ClassLoader that is used for deserialization
     */
    protected ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }
}

