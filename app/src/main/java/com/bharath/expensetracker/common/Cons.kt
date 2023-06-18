package com.bharath.expensetracker.common

import androidx.compose.ui.graphics.Color
import com.bharath.expensetracker.ui.theme.*

object Cons {
const val name_db="Name_Data_Base"
const val database_Name="DB_Transactions"
    const val Rd_data_baseName="RD_Db_transactions"




    val incomeListCategories = listOf(
        "Salary",
        "Business Profits",
        "Shares & Stocks",
        "CashBack's",
        "Gifts",
        "Passive Income"
    )
    val colorMap :Map<String,Color> = mapOf(
        Pair("Salary", Color(0xE710E823)),
        Pair("Business Profits",Color(0xEFB4ED15)),
        Pair("Shares & Stocks",Color(0xEF2BED80)),
        Pair("Passive Income",Color(0xEFE9F521)),
        Pair("CashBack's",Color(0xF310F5C7)),
        Pair("Gifts",Color(0xF3BEDA7C)),
        Pair("Clothing & Apparel",Color(0xF3D65E5E)),
        Pair("Food",Color(0xF3E478A5)),
        Pair("Rent & Monthly Expenses",Color(0xF3EB791D)),
        Pair("Movie's & Other",Color(0xF3E80A0A)),
        Pair("Vehicle & Accessories",Color(0xF3F89557)),
        Pair("Travel",Color(0xF3F857B8)),
        Pair("Restaurant's and Party's",Color(0xF3CD1414)),
        Pair("Utilities & Essentials",Color(0xF3CD8314)),
        Pair("Electronics",Color(0xF3B23352)),
        Pair("Recharges &Bill Payments",Color(0xF3C919A6)),
        Pair("Furniture & Home Equipment",Color(0xF3D6280E)),
        Pair("Other",Color(0xF363261A)),


    )

    val expenseListCategories = listOf(
        "Clothing & Apparel",
        "Food",
        "Rent & Monthly Expenses",
        "Movie's & Other",
        "Vehicle & Accessories",
        "Travel",
        "Restaurant's and Party's",
        "Utilities & Essentials",
        "Electronics",
        "Recharges &Bill Payments",
        "Furniture & Home Equipment",
        "Other"
    )

    val colorList = listOf(
        colorBar1,
        colorBar2,
        colorBar3,
        colorBar4,
        colorBar5,
        colorBar6,
        colorBar7,
        colorBar8,
        colorBar9,
        colorBar10,
        colorBar11,
        colorBar12,



        )
    val ColorSchemes  = listOf(

ColorSchemes(colorBar1cs , colorBar1Secondary, colorBar1Container),
ColorSchemes(colorBar2cs, colorBar2Secondary, colorBar2Container),
ColorSchemes(colorBar3cs, colorBar3Secondary, colorBar3Container),
ColorSchemes(colorBar4cs, colorBar4Secondary, colorBar4Container),
ColorSchemes(colorBar5cs, colorBar5Secondary, colorBar5Container),
ColorSchemes(colorBar6cs, colorBar6Secondary, colorBar6Container),

        )
    val defaultColorSchme =
        ColorSchemes(colorBar5, colorBar5Secondary, colorBar5Container)












}

