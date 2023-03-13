package com.example.moviesapp.util

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.moviesapp.R
import com.google.android.material.snackbar.Snackbar

class PopUps {
    companion object {
        fun createAlertDialog(context: Context, style: Int): AlertDialog.Builder {
            val builder: AlertDialog.Builder =
                AlertDialog.Builder(context, style)
            builder.setCancelable(false)
            return builder
        }

        fun progressDialog(
            activity: Activity,
        ): ProgressDialog {
            val pd = ProgressDialog(activity, R.style.RoundedCornersDialog)
            pd.setMessage(activity.applicationContext.resources.getString(com.example.moviesapp.R.string.loading))
            pd.setCancelable(false)
            return pd
        }

        fun customSnackBar(str: String, view: View) {
            val snack: Snackbar = Snackbar.make(view, str, Snackbar.LENGTH_SHORT)
            val view = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            snack.show()
        }

        fun customSnackBarCoordinator(str: String, view: View) {
            val coordinatorLayout = view.layoutParams as CoordinatorLayout
            val snackbar = Snackbar.make(coordinatorLayout, "Text", Snackbar.LENGTH_LONG)
            val view = snackbar.view
            val params = view.layoutParams as CoordinatorLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            snackbar.show()
        }
    }
}
