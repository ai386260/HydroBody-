package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CardSubtleHairline
import com.example.ui.theme.DewMintBackground
import com.example.ui.theme.DewMintCardBorder
import com.example.ui.theme.DewMintCardSurface
import com.example.ui.theme.DewMintLight
import com.example.ui.theme.GlassContainerBg
import com.example.ui.theme.GlassContainerBorder
import com.example.ui.theme.HydroDewPill
import com.example.ui.theme.HydroDewPillLight
import com.example.ui.theme.HydroEmerald
import com.example.ui.theme.HydroEmeraldDark
import com.example.ui.theme.HydroEmeraldDeep
import com.example.ui.theme.HydroPillText
import com.example.ui.theme.MummyBadge
import com.example.ui.theme.MummyBannerBg
import com.example.ui.theme.MummyBannerBorder
import com.example.ui.theme.MummyPill
import com.example.ui.theme.MummyText
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextWhite
import com.example.ui.theme.WaterGradientEnd
import com.example.ui.theme.WaterGradientStart
import com.example.ui.theme.WaterSurfaceLine
import kotlin.math.sin

/**
 * Top App Bar rendered with crisp vector icons, solid tokens, zero letter spacing, and no scale on text
 */
@Composable
fun HydroTopBar(
  currentScreenTitle: String = "HydroBuddy",
  showLiveSync: Boolean = true,
  mummyModeActive: Boolean = true,
  onMummyClick: () -> Unit = {},
  onNotificationClick: () -> Unit = {},
  onSettingsClick: () -> Unit = {},
  onProfileClick: () -> Unit = {},
  userInitials: String = "A",
  modifier: Modifier = Modifier
) {
  Surface(
    color = DewMintBackground,
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      // Left Branding & Header Text
      Row(
        modifier = Modifier.weight(1f, fill = false),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(HydroDewPill)
            .border(1.dp, CardSubtleHairline, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Canvas(modifier = Modifier.size(20.dp)) {
            val w = size.width
            val h = size.height
            val path = Path().apply {
              moveTo(w * 0.5f, h * 0.05f)
              cubicTo(w * 0.15f, h * 0.52f, w * 0.12f, h * 0.88f, w * 0.5f, h * 0.98f)
              cubicTo(w * 0.88f, h * 0.88f, w * 0.85f, h * 0.52f, w * 0.5f, h * 0.05f)
              close()
            }
            drawPath(path, color = HydroEmerald)

            val reflection = Path().apply {
              moveTo(w * 0.32f, h * 0.45f)
              cubicTo(w * 0.25f, h * 0.65f, w * 0.3f, h * 0.82f, w * 0.42f, h * 0.88f)
              cubicTo(w * 0.35f, h * 0.82f, w * 0.32f, h * 0.65f, w * 0.36f, h * 0.45f)
              close()
            }
            drawPath(reflection, color = Color.White)
          }
        }

        Column(
          modifier = Modifier.weight(1f, fill = false),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text = currentScreenTitle,
            style = MaterialTheme.typography.titleLarge.copy(
              color = HydroEmeraldDeep,
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
          if (showLiveSync) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(6.dp)
                  .clip(CircleShape)
                  .background(HydroEmerald)
              )
              Text(
                text = "LIVE SYNC",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = HydroEmeraldDark
                )
              )
            }
          }
        }
      }

      // Right Action Badges
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Surface(
          shape = RoundedCornerShape(20.dp),
          color = MummyBannerBg,
          border = androidx.compose.foundation.BorderStroke(1.dp, MummyBannerBorder),
          shadowElevation = 1.dp,
          modifier = Modifier
            .clickable(onClick = onMummyClick)
            .testTag("mummy_mode_badge")
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Text("👩‍👧", fontSize = 12.sp)
            Text(
              text = "Mummy",
              style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MummyText
              )
            )
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(if (mummyModeActive) HydroEmerald else TextMuted)
            )
          }
        }

        // Notification Bell
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(DewMintCardSurface)
            .border(1.dp, DewMintCardBorder, CircleShape)
            .clickable(onClick = onNotificationClick)
            .testTag("notifications_button"),
          contentAlignment = Alignment.Center
        ) {
          BadgedBox(
            badge = {
              Badge(
                containerColor = HydroEmerald,
                modifier = Modifier.size(6.dp)
              )
            }
          ) {
            Icon(
              imageVector = Icons.Outlined.Notifications,
              contentDescription = "Notifications",
              tint = TextSecondary,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        // User Avatar Circle
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(HydroDewPill)
            .border(1.5.dp, HydroEmerald, CircleShape)
            .clickable(onClick = onProfileClick)
            .testTag("avatar_button"),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = userInitials,
            style = MaterialTheme.typography.titleMedium.copy(
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = HydroEmeraldDeep
            )
          )
        }
      }
    }
  }
}

/**
 * Mummy Mode Alert Banner with exact Mummy colors, padded to 16.dp
 */
@Composable
fun MummyModeBanner(
  promptText: String,
  onDrinkClick: () -> Unit,
  onShuffleClick: () -> Unit,
  onDismiss: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    shape = RoundedCornerShape(20.dp),
    color = MummyBannerBg,
    border = androidx.compose.foundation.BorderStroke(1.dp, MummyBannerBorder),
    shadowElevation = 2.dp,
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp)
      .testTag("mummy_nudge_banner")
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        modifier = Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MummyBadge),
          contentAlignment = Alignment.Center
        ) {
          Text("👩‍👧", fontSize = 18.sp)
        }

        Column(modifier = Modifier.weight(1f)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Surface(
              shape = RoundedCornerShape(4.dp),
              color = MummyPill
            ) {
              Text(
                text = "MUMMY MODE",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = MummyText
                ),
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
            Icon(
              imageVector = Icons.Default.Refresh,
              contentDescription = "Shuffle Desi Prompt",
              tint = MummyBadge,
              modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .clickable(onClick = onShuffleClick)
            )
          }

          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = promptText,
            style = MaterialTheme.typography.bodyMedium.copy(
              fontSize = 15.sp,
              fontWeight = FontWeight.Medium,
              color = MummyText,
              lineHeight = 20.sp
            )
          )
        }
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Button(
          onClick = onDrinkClick,
          shape = RoundedCornerShape(20.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = HydroEmeraldDark,
            contentColor = TextWhite
          ),
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
          modifier = Modifier
            .height(38.dp)
            .testTag("mummy_quick_drink_button")
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Text("✓", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Text("Drink (+250ml)", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
          }
        }

        IconButton(
          onClick = onDismiss,
          modifier = Modifier.size(24.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Dismiss",
            tint = MummyBadge,
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }
  }
}

/**
 * HD Water Tank Card featuring real-time 60fps wave animation, solid container,
 * big number (2.1L) at 40.sp, zero negative letter spacing, and high contrast white text.
 */
@Composable
fun HydroWaterGlassCard(
  currentMl: Int,
  goalMl: Int,
  scorePercent: Int,
  modifier: Modifier = Modifier
) {
  val targetFill = (currentMl.toFloat() / goalMl.toFloat()).coerceIn(0.05f, 1f)
  val animatedFill by animateFloatAsState(
    targetValue = targetFill,
    animationSpec = tween(durationMillis = 600),
    label = "water_fill_anim"
  )

  val infiniteTransition = rememberInfiniteTransition(label = "wave_phase_loop")
  val wavePhase by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = (2 * Math.PI).toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 3500, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "wave_phase"
  )

  val litersText = String.format("%.1fL", currentMl / 1000f)
  val goalText = String.format("%.1fL GOAL", goalMl / 1000f)

  Box(
    modifier = modifier
      .clip(RoundedCornerShape(20.dp))
      .background(GlassContainerBg)
      .border(1.dp, GlassContainerBorder, RoundedCornerShape(20.dp))
      .padding(6.dp)
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(16.dp))
        .background(DewMintCardSurface)
        .border(1.dp, DewMintCardBorder, RoundedCornerShape(16.dp))
    ) {
      Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val waterBaseTop = h * (1f - animatedFill)
        val waveAmplitude = 5.dp.toPx()

        val wavePath = Path().apply {
          moveTo(0f, h)
          lineTo(0f, waterBaseTop)

          val steps = 40
          val stepWidth = w / steps
          for (i in 0..steps) {
            val x = i * stepWidth
            val angle = (x / w) * (2 * Math.PI).toFloat() + wavePhase
            val y = waterBaseTop + (sin(angle.toDouble()).toFloat() * waveAmplitude)
            lineTo(x, y)
          }

          lineTo(w, h)
          close()
        }

        drawPath(
          path = wavePath,
          brush = Brush.verticalGradient(
            colors = listOf(WaterGradientStart, WaterGradientEnd),
            startY = waterBaseTop - waveAmplitude,
            endY = h
          )
        )

        val surfaceHighlightPath = Path().apply {
          val steps = 40
          val stepWidth = w / steps
          for (i in 0..steps) {
            val x = i * stepWidth
            val angle = (x / w) * (2 * Math.PI).toFloat() + wavePhase
            val y = waterBaseTop + (sin(angle.toDouble()).toFloat() * waveAmplitude)
            if (i == 0) moveTo(x, y) else lineTo(x, y)
          }
        }
        drawPath(
          path = surfaceHighlightPath,
          color = WaterSurfaceLine,
          style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.5.dp.toPx())
        )
      }

      // Measurement markers
      Column(
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(top = 16.dp, end = 12.dp),
        horizontalAlignment = Alignment.End
      ) {
        listOf(goalMl / 1000f, (goalMl * 0.75f) / 1000f, (goalMl * 0.5f) / 1000f).forEachIndexed { idx, vol ->
          Text(
            text = String.format("%.1fL", vol),
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              color = TextSecondary
            )
          )
          if (idx < 2) {
            Spacer(modifier = Modifier.height(38.dp))
          }
        }
      }

      // Score Pill at top
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = DewMintCardSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
        shadowElevation = 2.dp,
        modifier = Modifier
          .align(Alignment.TopCenter)
          .padding(top = 12.dp)
      ) {
        Column(
          modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "$scorePercent%",
            style = MaterialTheme.typography.titleMedium.copy(
              fontSize = 15.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroEmeraldDeep
            )
          )
          Text(
            text = "Score",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium,
              color = TextSecondary
            )
          )
        }
      }

      // Large volume typography - 40.sp, zero negative letter spacing, pure white
      Column(
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = litersText,
          style = MaterialTheme.typography.displayLarge.copy(
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            letterSpacing = 0.sp
          )
        )
        Text(
          text = "OF $goalText",
          style = MaterialTheme.typography.labelMedium.copy(
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextWhite,
            letterSpacing = 0.sp
          )
        )
      }
    }
  }
}

/**
 * Paudha Buddy companion card - solid white card, 1dp border, 20dp corners, 2dp elevation, 16dp padding
 */
@Composable
fun PaudhaBuddyCard(
  level: Int = 3,
  status: String = "Level 3 Blooming",
  mood: String = "Hydrated & Happy",
  modifier: Modifier = Modifier
) {
  Surface(
    shape = RoundedCornerShape(20.dp),
    color = DewMintCardSurface,
    border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
    shadowElevation = 2.dp,
    modifier = modifier
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Box(
        modifier = Modifier
          .size(80.dp)
          .clip(CircleShape)
          .background(DewMintLight)
          .border(1.dp, DewMintCardBorder, CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Text("🌱", fontSize = 34.sp)
        Text(
          "✨",
          fontSize = 14.sp,
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(top = 8.dp, end = 8.dp)
        )
        Text(
          "💧",
          fontSize = 12.sp,
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(bottom = 12.dp, start = 8.dp)
        )
      }

      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = "Paudha\nBuddy",
        style = MaterialTheme.typography.titleLarge.copy(
          fontSize = 20.sp,
          fontWeight = FontWeight.SemiBold,
          color = HydroEmeraldDeep,
          lineHeight = 24.sp,
          textAlign = TextAlign.Center
        )
      )

      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = status,
        style = MaterialTheme.typography.bodyMedium.copy(
          fontSize = 15.sp,
          fontWeight = FontWeight.Medium,
          color = TextSecondary
        )
      )

      Spacer(modifier = Modifier.height(8.dp))
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = HydroDewPillLight
      ) {
        Text(
          text = mood,
          style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = HydroPillText
          ),
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
      }
    }
  }
}
