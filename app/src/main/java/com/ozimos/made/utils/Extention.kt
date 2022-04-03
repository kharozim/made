package com.ozimos.made.utils

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar
import com.ozimos.made.R

fun showSnackBar(view: View, message: String) {
    val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(view.context.getColor(R.color.teal_200))
        .setAction("tutup") {

        }
    val snackView = snackbar.view
    val layoutParam: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    )
    layoutParam.gravity = Gravity.TOP
    snackView.layoutParams = layoutParam

    snackbar.show()
}