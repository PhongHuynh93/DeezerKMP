package com.wind.deezerkmp.androidApp.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import timber.log.Timber;

/**
 * Created by Phong Huynh on 7/21/2020
 */
public class DisableAppbarScrollingBehavior extends AppBarLayout.Behavior {

    public DisableAppbarScrollingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target,
                                       int nestedScrollAxes, int type) {
        if (target instanceof RecyclerView) {
            RecyclerView rcv = (RecyclerView) target;
            if (rcv.canScrollVertically(-1)) {
                return false;
            }
        }
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type);
    }
}
