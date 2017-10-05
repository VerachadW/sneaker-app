package com.hannesdorfmann.mosby.mvp;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.AbsParcelableLceViewState;

public class VoidViewState<V extends MvpLceView<Void>> extends AbsParcelableLceViewState<Void, V> {

    public static final Parcelable.Creator<VoidViewState> CREATOR =
            new Parcelable.Creator<VoidViewState>() {
                @Override
                public VoidViewState createFromParcel(Parcel source) {
                    return new VoidViewState();
                }

                @Override
                public VoidViewState[] newArray(int size) {
                    return new VoidViewState[size];
                }
            };

}