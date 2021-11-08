package com.punkapp.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty

fun <T : ViewBinding> View.binding(): T = DataBindingUtil.bind(this) ?: error("Does this view have DataBinding layout?")

fun <T : ViewBinding> Fragment.viewBinding() = ReadOnlyProperty<Fragment, T> { _, _ -> requireView().binding() }