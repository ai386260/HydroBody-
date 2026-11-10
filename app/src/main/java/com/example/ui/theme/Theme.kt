package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
  primary = HydroEmerald,
  onPrimary = TextWhite,
  primaryContainer = HydroDewPill,
  onPrimaryContainer = HydroEmeraldDeep,
  secondary = HydroEmeraldDark,
  onSecondary = TextWhite,
  secondaryContainer = HydroDewPillLight,
  onSecondaryContainer = HydroEmeraldDeep,
  background = DewMintBackground,
  onBackground = TextPrimary,
  surface = DewMintCardSurface,
  onSurface = TextPrimary,
  surfaceVariant = DewMintLight,
  onSurfaceVariant = TextSecondary,
  outline = DewMintCardBorder
)

private val DarkColorScheme = darkColorScheme(
  primary = HydroMintAccent,
  onPrimary = Color(0xFF003824),
  primaryContainer = HydroEmeraldDark,
  onPrimaryContainer = Color(0xFFBCEBD4),
  secondary = HydroMintAccent,
  onSecondary = Color(0xFF003824),
  background = Color(0xFF0A1F16),
  onBackground = Color(0xFFE2F1E8),
  surface = Color(0xFF13291F),
  onSurface = Color(0xFFE2F1E8),
  surfaceVariant = Color(0xFF1E3A2D),
  onSurfaceVariant = Color(0xFFA1C6B6),
  outline = DewMintCardBorder
)

@Composable
fun HydroBuddyTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Disabled dynamicColor so Android never overrides palette
  content: @Composable () -> Unit
) {
  // Always use the Serene Dew Mint light color scheme to guarantee high contrast and visible header typography
  val colorScheme = LightColorScheme

  // Set status bar and navigation bar to match theme
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as? Activity)?.window
      window?.let { win ->
        win.statusBarColor = DewMintBackground.toArgb()
        win.navigationBarColor = DewMintCardSurface.toArgb()
        val insetsController = WindowCompat.getInsetsController(win, view)
        insetsController.isAppearanceLightStatusBars = true
        insetsController.isAppearanceLightNavigationBars = true
      }
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
