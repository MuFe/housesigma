package com.housesigma.binding

import android.graphics.Bitmap
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter("isGone")
fun View.setIsGone(isGone: Boolean) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("isInvisible")
fun View.setIsInvisible(isInvisible: Boolean) {
    visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("drawableEnd")
fun setDrawableEnd(
    view: TextView,
    source: Int = 0
) {
    if (source != 0) {
        view.setCompoundDrawablesWithIntrinsicBounds(
            view.context.getDrawable(source),
            null,
            null,
            null
        )
    }
}

@BindingAdapter(
    value = ["textViewColor"],
    requireAll = false
)
fun setTextViewColor(
    view: TextView,
    source: Int = 0
) {
    if (source != 0) {
        view.setTextColor(view.context.resources.getColor(source))
    }
}

@BindingAdapter(
    value = ["textStr"],
    requireAll = false
)
fun setTextViewStr(
    view: TextView,
    source: Int = 0
) {
    if (source != 0) {
        view.setText(source)
    }
}

@BindingAdapter(
    value = ["textStyle"],
    requireAll = false
)
fun setTextStyle(
    view: TextView,
    source: Int = 0
) {
    if (source == 0) {
        view.setTypeface(null, Typeface.NORMAL);
    } else if (source == 1) {
        view.setTypeface(null, Typeface.BOLD);
    }
}

@BindingAdapter(
    value = ["backGround"],
    requireAll = false
)
fun setBackGround(
    view: View,
    source: Int = 0
) {
    if (source != 0) {
        view.background = view.context.getDrawable(source)
    }
}

@BindingAdapter(
    value = ["imageUrl", "placeholder", "error", "fallback", "loadWidth", "loadHeight", "cacheEnable", "radius", "isRound"],
    requireAll = false
)
fun setImageUrl(
    view: ImageView,
    source: Any? = null,
    placeholder: Drawable? = null,
    error: Drawable? = null,
    fallback: Drawable? = null,
    imageWidth: Int? = -1,
    imageHeight: Int? = -1,
    cacheEnable: Boolean? = true,
    radius: Int? = 0,
    isRound: Boolean? = false,
) {
    // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????Glide??????????????????override???????????? -1 ?????????
    val widthSize = (if ((imageWidth ?: 0) > 0) imageWidth else view.width) ?: -1
    val heightSize = (if ((imageHeight ?: 0) > 0) imageHeight else view.height) ?: -1
    val re = Glide.with(view.context)
        .asDrawable()
        .load(source)
        .placeholder(placeholder)
        .error(error)
        .fallback(fallback)
        .override(widthSize, heightSize)
        .transition(DrawableTransitionOptions.withCrossFade())
    if (isRound == true&&widthSize>0) {
        re.transform(CircleCrop()).into(view)
    } else if (radius != null) {
        re.transform(RoundedCorners(radius)).into(view)
    } else {
        re.into(view)
    }
}
