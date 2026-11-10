package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.LocalDrink
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DewMintCardBorder
import com.example.ui.theme.DewMintCardSurface
import com.example.ui.theme.HydroDewPillLight
import com.example.ui.theme.HydroEmerald
import com.example.ui.theme.HydroEmeraldDeep
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary

enum class HydroNavTab(
  val route: String,
  val label: String,
  val activeIcon: ImageVector,
  val inactiveIcon: ImageVector
) {
  HOME("home", "Home", Icons.Filled.LocalDrink, Icons.Outlined.LocalDrink),
  GROUPS("groups", "Group", Icons.Filled.Group, Icons.Outlined.Group),
  CHALLENGES("challenges", "Challenges", Icons.Filled.EmojiEvents, Icons.Outlined.EmojiEvents),
  PROFILE("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

/**
 * Bottom Navigation Bar: solid white, 1.dp border using DewMintCardBorder,
 * rounded 20.dp, elevation 2.dp.
 * Selected tab state: 2.dp HydroEmerald border with HydroDewPillLight fill.
 * Text minimum 13.sp label with FontWeight.Medium/SemiBold and zero negative letter spacing.
 */
@Composable
fun HydroBottomBar(
  currentTab: HydroNavTab,
  onTabSelected: (HydroNavTab) -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    color = DewMintCardSurface,
    border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
    shadowElevation = 2.dp,
    modifier = modifier
      .fillMaxWidth()
      .navigationBarsPadding()
      .testTag("bottom_nav_bar")
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp, vertical = 8.dp),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      HydroNavTab.entries.forEach { tab ->
        val isSelected = tab == currentTab

        val pillColor by animateColorAsState(
          targetValue = if (isSelected) HydroDewPillLight else Color.Transparent,
          animationSpec = tween(200),
          label = "pill_color"
        )
        val iconColor by animateColorAsState(
          targetValue = if (isSelected) HydroEmeraldDeep else TextMuted,
          animationSpec = tween(200),
          label = "icon_color"
        )

        Column(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = { onTabSelected(tab) })
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .testTag("nav_${tab.route}"),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = pillColor,
            border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, HydroEmerald) else null,
            modifier = Modifier.size(width = 56.dp, height = 34.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = if (isSelected) tab.activeIcon else tab.inactiveIcon,
                contentDescription = tab.label,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = tab.label,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
              color = if (isSelected) HydroEmeraldDeep else TextSecondary,
              letterSpacing = 0.sp
            )
          )
        }
      }
    }
  }
}
