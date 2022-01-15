package com.housesigma

import android.os.Bundle

interface FragmentHost {
    fun reload()
    fun reload(bundle: Bundle?)
}
