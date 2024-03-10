package pl.mclojek.carpify.presentation.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Icons.Filled.Fish: ImageVector
    get() {
        if (_fish != null) {
            return _fish!!
        }
        _fish = ImageVector.Builder(
            name = "Fish",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 279.14F,
            viewportHeight = 279.14F,
        ).path(
            fill = SolidColor(Color(0xFFFF00FF)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(279.04F, 7.24F)
            curveToRelative(-0.18F, -3.87F, -3.28F, -6.97F, -7.15F, -7.15F)
            curveToRelative(-0.07F, -0.0F, -1.84F, -0.08F, -4.94F, -0.08F)
            curveToRelative(-20.36F, 0.0F, -87.22F, 3.25F, -134.5F, 42.85F)
            lineToRelative(-37.49F, -5.63F)
            curveToRelative(-0.96F, -0.14F, -1.94F, -0.22F, -2.9F, -0.22F)
            curveToRelative(-8.96F, 0.0F, -16.72F, 6.08F, -18.88F, 14.79F)
            lineTo(62.11F, 96.53F)
            curveToRelative(-1.28F, 5.19F, -0.42F, 10.53F, 2.43F, 15.05F)
            curveToRelative(2.85F, 4.52F, 7.3F, 7.6F, 12.54F, 8.68F)
            lineToRelative(6.1F, 1.25F)
            curveToRelative(-4.3F, 14.13F, -6.9F, 27.74F, -8.46F, 39.53F)
            lineToRelative(-62.35F, 14.04F)
            curveToRelative(-6.18F, 1.39F, -10.79F, 6.11F, -12.05F, 12.32F)
            reflectiveCurveToRelative(1.17F, 12.35F, 6.32F, 16.03F)
            curveToRelative(0.62F, 0.44F, 1.31F, 0.79F, 2.04F, 1.03F)
            lineToRelative(38.56F, 12.56F)
            curveToRelative(7.01F, 2.29F, 12.56F, 7.84F, 14.85F, 14.85F)
            lineToRelative(12.56F, 38.56F)
            curveToRelative(0.24F, 0.73F, 0.58F, 1.41F, 1.03F, 2.04F)
            curveToRelative(3.01F, 4.22F, 7.7F, 6.64F, 12.85F, 6.64F)
            curveToRelative(7.49F, 0.0F, 13.86F, -5.09F, 15.5F, -12.37F)
            lineToRelative(14.06F, -62.41F)
            curveToRelative(9.78F, -1.3F, 20.77F, -3.31F, 32.24F, -6.38F)
            lineToRelative(-0.88F, 18.83F)
            curveToRelative(-0.26F, 5.53F, 3.47F, 10.4F, 8.88F, 11.59F)
            lineToRelative(14.65F, 3.21F)
            curveToRelative(0.8F, 0.17F, 1.62F, 0.26F, 2.43F, 0.26F)
            curveToRelative(6.01F, 0.0F, 10.98F, -4.71F, 11.29F, -10.72F)
            lineToRelative(2.0F, -37.54F)
            curveToRelative(13.99F, -7.01F, 27.53F, -16.12F, 39.38F, -27.97F)
            curveTo(282.68F, 101.04F, 279.21F, 11.05F, 279.04F, 7.24F)

            moveTo(225.56F, 47.49F)
            lineToRelative(-1.22F, 0.73F)
            curveToRelative(-2.41F, 1.44F, -5.06F, 2.12F, -7.67F, 2.12F)
            curveToRelative(-5.11F, 0.0F, -10.09F, -2.61F, -12.9F, -7.31F)
            curveToRelative(-4.24F, -7.11F, -1.92F, -16.32F, 5.19F, -20.57F)
            lineToRelative(1.22F, -0.73F)
            curveToRelative(7.12F, -4.25F, 16.32F, -1.92F, 20.57F, 5.19F)
            curveTo(235.0F, 34.04F, 232.68F, 43.25F, 225.56F, 47.49F)
            close()
        }.build()
        return _fish!!
    }
private var _fish: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconFishPreview() {
    Icon(imageVector = Icons.Filled.Fish, contentDescription = null)
}