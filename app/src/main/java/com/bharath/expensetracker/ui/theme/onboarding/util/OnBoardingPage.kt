package com.bharath.expensetracker.ui.theme.onboarding.util

import androidx.annotation.DrawableRes
import com.bharath.expensetracker.R

sealed class OnBoardingPage (
    @DrawableRes
    val image:Int,
    val title:String,
    val Desc:String
        ){
    object First:OnBoardingPage(
        image = R.drawable.notebook,
        title = "Organized",
        Desc = "Keeps Your All Expenses Organized and Keep Tracking on them !"

    )
    object Second:OnBoardingPage(
        image = R.drawable.piechart,
        title = "Manage",
        Desc = "We Will Format Your Expenses in Charts For your Better Understanding on your Transactions !"
    )
    object Third:OnBoardingPage(
        image = R.drawable.withlove,
        title = "Experience",
        Desc = "This App is Completely Free and Run With Minimal Advertisements and YES! they don't bother your experience"
    )
}