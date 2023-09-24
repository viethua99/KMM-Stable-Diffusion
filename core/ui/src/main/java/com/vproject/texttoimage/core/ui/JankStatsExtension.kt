package com.vproject.texttoimage.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.metrics.performance.PerformanceMetricsState
import androidx.metrics.performance.PerformanceMetricsState.Holder

/**
 * Convenience function to work with [PerformanceMetricsState] state that needs to be cleaned up.
 * The side effect is re-launched if any of the [keys] value is not equal to the previous composition.
 */
@Composable
fun TrackDisposableJank(
    vararg keys: Any?,
    reportMetric: DisposableEffectScope.(state: Holder) -> DisposableEffectResult,
) {
    val metrics = rememberMetricsStateHolder()
    DisposableEffect(metrics, *keys) {
        reportMetric(this, metrics)
    }
}

/**
 * Retrieves [PerformanceMetricsState.Holder] from current [LocalView] and
 * remembers it until the View changes.
 * @see PerformanceMetricsState.getHolderForHierarchy
 */
@Composable
fun rememberMetricsStateHolder(): Holder {
    val localView = LocalView.current

    return remember(localView) {
        PerformanceMetricsState.getHolderForHierarchy(localView)
    }
}