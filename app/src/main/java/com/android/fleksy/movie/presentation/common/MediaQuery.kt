package com.android.fleksy.movie.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class Dimensions {
    object Width : Dimensions()
    object Height : Dimensions()

    sealed class DimensionOperator {
        object LessThan : DimensionOperator()
        object GreaterThan : DimensionOperator()
    }

    sealed class Comparator {
        abstract fun compare(width: Dp, height: Dp): Boolean
        class DimensionComparator(
            private val operator: DimensionOperator,
            private val dimension: Dimensions,
            val otherDimension: Dimensions
        ): Comparator() {
            override fun compare(width: Dp, height: Dp): Boolean {
                return when (dimension) {
                    Width -> when (operator) {
                        DimensionOperator.LessThan -> width < height
                        DimensionOperator.GreaterThan -> width > height
                    }
                    Height -> when (operator) {
                        DimensionOperator.LessThan -> height < width
                        DimensionOperator.GreaterThan -> height > width
                    }
                }
            }
        }
        class DimensionValueComparator(
            private val operator: DimensionOperator,
            private val dimension: Dimensions,
            private val value: Dp
        ): Comparator() {
            override fun compare(width: Dp, height: Dp): Boolean {
                return when (dimension) {
                    Width -> when (operator) {
                        DimensionOperator.LessThan -> width < value
                        DimensionOperator.GreaterThan -> width > value
                    }
                    Height -> when (operator) {
                        DimensionOperator.LessThan -> height < value
                        DimensionOperator.GreaterThan -> height > value
                    }
                }
            }
        }
    }
}

@Composable
fun MediaQuery(
    comparator: Dimensions.Comparator,
    content: @Composable () -> Unit
) {
    if (comparator.compare(
            width = LocalContext.current.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density,
            height = LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
        )
    ) {
        content()
    }
}

@Composable
fun IsScreenModePortrait(): Boolean {
    return Dimensions.Comparator.DimensionComparator(
        operator = Dimensions.DimensionOperator.LessThan,
        dimension = Dimensions.Width,
        otherDimension = Dimensions.Height
    ).compare(
        width = LocalContext.current.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density,
        height = LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
    )
}

infix fun Dimensions.lessThan(otherDimension: Dimensions): Dimensions.Comparator {
    return Dimensions.Comparator.DimensionComparator(
        operator = Dimensions.DimensionOperator.LessThan,
        dimension = this,
        otherDimension = otherDimension
    )
}

infix fun Dimensions.greaterThan(otherDimension: Dimensions): Dimensions.Comparator {
    return Dimensions.Comparator.DimensionComparator(
        operator = Dimensions.DimensionOperator.GreaterThan,
        dimension = this,
        otherDimension = otherDimension
    )
}

infix fun Dimensions.lessThan(value: Dp): Dimensions.Comparator.DimensionValueComparator {
    return Dimensions.Comparator.DimensionValueComparator(
        operator = Dimensions.DimensionOperator.LessThan,
        dimension = this,
        value = value
    )
}

infix fun Dimensions.greaterThan(value: Dp): Dimensions.Comparator.DimensionValueComparator {
    return Dimensions.Comparator.DimensionValueComparator(
        operator = Dimensions.DimensionOperator.GreaterThan,
        dimension = this,
        value = value
    )
}