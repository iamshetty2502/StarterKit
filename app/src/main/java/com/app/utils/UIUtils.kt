package com.app.utils

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat.LAYER_TYPE_SOFTWARE
import android.view.Gravity
import android.view.View
import com.app.R


class UIUtils {

    companion object {
        fun getDrawable(context: Context, @ColorRes color: Int?): Drawable {
            val drawableTint = color ?: Color.WHITE
            val drawable = context.resources.getDrawable(R.drawable.background, null) as GradientDrawable
            drawable.setColor(drawableTint)
            return drawable
        }

        fun getDrawable(context: Context, @ColorRes color: Int?, @ColorRes drawable: Int?): Drawable {
            val drawableTint = color ?: Color.WHITE
            val drawableBg = drawable ?: R.drawable.background
            val drawableBackground = context.resources.getDrawable(drawableBg, null) as GradientDrawable
            drawableBackground.setColor(drawableTint)
            return drawableBackground
        }

        fun getBgDrawable(context: Context, @ColorRes backgroundColor: Int): Drawable {
            val cornerRadius: Float = context.resources.getDimension(R.dimen.corner_radius_15dp)
            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.cornerRadii = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius)
            shape.setColor(ContextCompat.getColor(context, backgroundColor))
            return shape
        }

        fun getBgDrawable(context: Context, @DimenRes strokeWidthRes: Int, @DimenRes cornerRadiusRes: Int,
                          @ColorRes strokeColorRes: Int,
                          @ColorRes backgroundColorRes: Int): Drawable {
            var radius = 0f
            var strokeWid = 0
            if (cornerRadiusRes != 0) {
                radius = context.resources.getDimension(cornerRadiusRes)
            }
            if (strokeWidthRes != 0) {
                strokeWid = context.resources.getDimension(strokeWidthRes).toInt()
            }
            val bgColor = ContextCompat.getColor(context, backgroundColorRes)
            val strokeC = ContextCompat.getColor(context, strokeColorRes)

            val shape = GradientDrawable()
            shape.shape = GradientDrawable.RECTANGLE
            shape.cornerRadii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
            shape.setColor(bgColor)
            shape.setStroke(strokeWid, strokeC)
            return shape
        }

        fun getIcon(context: Context, @DrawableRes icon: Int, state: Int): Drawable {
            val mDrawable = context.resources.getDrawable(icon, null)
                    .constantState.newDrawable().mutate()
            if (state > 0) {
                mDrawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_ATOP)
            } else {
                mDrawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, R.color.gray), PorterDuff.Mode.SRC_ATOP)
            }
            return mDrawable
        }

        fun getBgGradientDrawable(colors: IntArray, context: Context): GradientDrawable {
            val drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
            drawable.cornerRadius = context.resources.getDimension(R.dimen.corner_radius_15dp)
            return drawable
        }

        /**
         * TODO: To be improved
         */
        fun generateBackgroundWithShadow(view: View, @ColorRes backgroundColor: Int,
                                         @DimenRes cornerRadius: Int,
                                         @ColorRes shadowColor: Int,
                                         @DimenRes elevation: Int,
                                         shadowGravity: Int): Drawable {
            val cornerRadiusValue = view.getContext().getResources().getDimension(cornerRadius)
            val elevationValue = view.getContext().getResources().getDimension(elevation).toInt()
            val shadowColorValue = ContextCompat.getColor(view.getContext(), shadowColor)
            val backgroundColorValue = ContextCompat.getColor(view.getContext(), backgroundColor)

            val outerRadius = floatArrayOf(cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue)

            val backgroundPaint = Paint()
            backgroundPaint.setStyle(Paint.Style.FILL)
            backgroundPaint.setShadowLayer(cornerRadiusValue, 0f, 0f, 0)

            val shapeDrawablePadding = Rect()
            shapeDrawablePadding.left = elevationValue
            shapeDrawablePadding.right = elevationValue

            val DY: Int
            when (shadowGravity) {
                Gravity.CENTER -> {
                    shapeDrawablePadding.top = elevationValue
                    shapeDrawablePadding.bottom = elevationValue
                    DY = 0
                }
                Gravity.TOP -> {
                    shapeDrawablePadding.top = elevationValue * 2
                    shapeDrawablePadding.bottom = elevationValue
                    DY = -1 * elevationValue / 3
                }
                Gravity.BOTTOM -> {
                    shapeDrawablePadding.top = elevationValue
                    shapeDrawablePadding.bottom = elevationValue * 2
                    DY = elevationValue / 3
                }
                else -> {
                    shapeDrawablePadding.top = elevationValue
                    shapeDrawablePadding.bottom = elevationValue * 2
                    DY = elevationValue / 3
                }
            }

            val shapeDrawable = ShapeDrawable()
            shapeDrawable.setPadding(shapeDrawablePadding)

            shapeDrawable.paint.color = backgroundColorValue
            shapeDrawable.paint.setShadowLayer(cornerRadiusValue / 3, 0f, DY.toFloat(), shadowColorValue)

            view.setLayerType(LAYER_TYPE_SOFTWARE, shapeDrawable.paint)

            shapeDrawable.shape = RoundRectShape(outerRadius, null, null)

            val drawable = LayerDrawable(arrayOf<Drawable>(shapeDrawable))
            drawable.setLayerInset(0, elevationValue, elevationValue * 2, elevationValue, elevationValue * 2)

            return drawable
        }
    }

}