package com.example.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NudgeIntensity
import com.example.model.ReminderVoice
import com.example.ui.theme.AlertRed
import com.example.ui.theme.CardSubtleHairline
import com.example.ui.theme.DewMintBackground
import com.example.ui.theme.DewMintCardBorder
import com.example.ui.theme.DewMintCardSurface
import com.example.ui.theme.DewMintLight
import com.example.ui.theme.HydroDewPillLight
import com.example.ui.theme.HydroEmerald
import com.example.ui.theme.HydroEmeraldDark
import com.example.ui.theme.HydroEmeraldDeep
import com.example.ui.theme.HydroPillText
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextWhite
import com.example.viewmodel.HydroBuddyUiState

@Composable
fun ProfileScreen(
  uiState: HydroBuddyUiState,
  onUpdateDailyGoal: (Int) -> Unit,
  onToggleMummyMode: (Boolean) -> Unit,
  onNudgeIntensityChange: (NudgeIntensity) -> Unit,
  onShuffleDesiPrompt: () -> Unit,
  onPlayVoicePrompt: () -> Unit,
  onSelectVoice: (ReminderVoice) -> Unit,
  onToggleHealthSync: (Boolean) -> Unit,
  onToggleClimateSync: (Boolean) -> Unit,
  onSelectUnitSystem: (String) -> Unit,
  onActionToast: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(DewMintBackground)
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .padding(bottom = 32.dp)
  ) {
    // 1. User Profile Header Card: 16dp padding
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("profile_header_card")
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Box(
          modifier = Modifier.size(88.dp),
          contentAlignment = Alignment.Center
        ) {
          Box(
            modifier = Modifier
              .size(80.dp)
              .clip(CircleShape)
              .background(DewMintLight)
              .border(2.dp, HydroEmerald, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Text("🧔🏽‍♂️", fontSize = 42.sp)
          }

          Surface(
            shape = RoundedCornerShape(10.dp),
            color = HydroEmeraldDark,
            modifier = Modifier.align(Alignment.BottomCenter)
          ) {
            Text(
              text = "LVL ${uiState.userLevel}",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
              ),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = uiState.userName,
            style = MaterialTheme.typography.titleLarge.copy(
              fontSize = 20.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroEmeraldDeep
            )
          )
          Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit Profile",
            tint = TextSecondary,
            modifier = Modifier
              .size(18.dp)
              .clickable { onActionToast("Editing profile details...") }
          )
        }

        Text(
          text = "${uiState.userHandle} • ${uiState.memberSince}",
          style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = TextSecondary
          )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
          shape = RoundedCornerShape(12.dp),
          color = HydroDewPillLight
        ) {
          Text(
            text = "Hydration Master",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroPillText
            ),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
          )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Surface(
          shape = RoundedCornerShape(16.dp),
          color = DewMintLight,
          border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp)
          ) {
            Text(
              text = "🏁 ${uiState.primaryGoal}",
              style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = HydroEmeraldDeep
              )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "📍 ${uiState.location}",
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Normal,
                  color = TextSecondary
                )
              )
              Text(
                text = "⏱️ ${uiState.activeHours}",
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Normal,
                  color = TextSecondary
                )
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          ProfileStatPill(title = "2.5L", subtitle = "DAILY GOAL", modifier = Modifier.weight(1f))
          ProfileStatPill(title = "${uiState.dailyStreakDays} Days", subtitle = "STREAK", modifier = Modifier.weight(1f))
          ProfileStatPill(title = "${uiState.hydrationScorePercent}%", subtitle = "SCORE", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("MILESTONE BADGES", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary))
          Text("🏆 3 Unlocked", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDark))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          BadgeCard(label = "30-Day Aqua", status = "UNLOCKED", isAchieved = true, modifier = Modifier.weight(1f))
          BadgeCard(label = "Morning Pioneer", status = "EARLY BIRD", isAchieved = true, modifier = Modifier.weight(1f))
          BadgeCard(label = "Clean Streak", status = "MASTER", isAchieved = true, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
          shape = RoundedCornerShape(16.dp),
          color = HydroDewPillLight,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text("Total Logged", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
              Text("${uiState.totalLoggedLiters} L", style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text("Bottles Saved", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
              Text("${uiState.bottlesSavedEco} Eco", style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text("Avg. Daily", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
              Text("${uiState.avgDailyMl} ml", style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
          horizontalArrangement = Arrangement.spacedBy(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          listOf("💬", "📸", "📘", "🎵").forEach { iconStr ->
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(DewMintLight)
                .clickable { onActionToast("Sharing to social channel...") },
              contentAlignment = Alignment.Center
            ) {
              Text(iconStr, fontSize = 16.sp)
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 2. Hydration Goals Card
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(DewMintLight),
              contentAlignment = Alignment.Center
            ) {
              Icon(imageVector = Icons.Default.Tune, contentDescription = null, tint = HydroEmerald, modifier = Modifier.size(18.dp))
            }
            Column {
              Text("Hydration Goals", style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
              Text("Personalized Intake Rhythm", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
            }
          }

          Surface(shape = RoundedCornerShape(12.dp), color = HydroDewPillLight) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(HydroEmerald))
              Text("Active Plan", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText))
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
          shape = RoundedCornerShape(18.dp),
          color = DewMintLight,
          border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("DAILY TARGET INTAKE", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary))
              Surface(shape = RoundedCornerShape(10.dp), color = DewMintCardSurface) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text("${uiState.dailyGoalMl}", style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
                  Text(" ml / day", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
                }
              }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(34.dp)
                  .clip(CircleShape)
                  .background(DewMintCardSurface)
                  .clickable { onUpdateDailyGoal((uiState.dailyGoalMl - 100).coerceAtLeast(1000)) },
                contentAlignment = Alignment.Center
              ) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease", tint = TextSecondary, modifier = Modifier.size(18.dp))
              }

              Slider(
                value = uiState.dailyGoalMl.toFloat(),
                onValueChange = { onUpdateDailyGoal(it.toInt()) },
                valueRange = 1000f..4500f,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                  thumbColor = HydroEmeraldDark,
                  activeTrackColor = HydroEmerald,
                  inactiveTrackColor = DewMintCardBorder
                )
              )

              Box(
                modifier = Modifier
                  .size(34.dp)
                  .clip(CircleShape)
                  .background(DewMintCardSurface)
                  .clickable { onUpdateDailyGoal((uiState.dailyGoalMl + 100).coerceAtMost(5000)) },
                contentAlignment = Alignment.Center
              ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Increase", tint = TextSecondary, modifier = Modifier.size(18.dp))
              }
            }

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text("1,000 ml", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextMuted))
              Text("• Optimal Target", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDark))
              Text("4,500 ml", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextMuted))
            }
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Surface(
          shape = RoundedCornerShape(16.dp),
          color = DewMintLight,
          border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              modifier = Modifier.weight(1f),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(32.dp)
                  .clip(CircleShape)
                  .background(DewMintCardSurface),
                contentAlignment = Alignment.Center
              ) {
                Text("✨", fontSize = 16.sp)
              }
              Column {
                Text("Smart Climate & Activity Sync", style = MaterialTheme.typography.titleSmall.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
                Text("+350 ml auto-adjust on hot & workout days", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
              }
            }

            Surface(
              shape = RoundedCornerShape(12.dp),
              color = if (uiState.smartClimateSyncActive) HydroDewPillLight else DewMintCardSurface,
              modifier = Modifier.clickable { onToggleClimateSync(!uiState.smartClimateSyncActive) }
            ) {
              Text(
                text = if (uiState.smartClimateSyncActive) "Active" else "Off",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = if (uiState.smartClimateSyncActive) HydroPillText else TextMuted
                ),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 3. Mummy Mode & Nudges Card
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(DewMintLight),
              contentAlignment = Alignment.Center
            ) {
              Icon(imageVector = Icons.Default.RecordVoiceOver, contentDescription = null, tint = HydroEmerald, modifier = Modifier.size(18.dp))
            }
            Column {
              Text("Mummy Mode & Nudges", style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
              Text("Assertive Loving Accountability", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
            }
          }

          Switch(
            checked = uiState.mummyModeEnabled,
            onCheckedChange = onToggleMummyMode,
            colors = SwitchDefaults.colors(
              checkedThumbColor = TextWhite,
              checkedTrackColor = HydroEmeraldDark
            ),
            modifier = Modifier.testTag("mummy_mode_switch")
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nudge Personality Intensity", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary))
        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          NudgeIntensity.entries.forEach { intensity ->
            val isSelected = intensity == uiState.nudgeIntensity
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = if (isSelected) HydroEmeraldDeep else DewMintLight,
              border = androidx.compose.foundation.BorderStroke(
                if (isSelected) 2.dp else 1.dp,
                if (isSelected) HydroEmerald else DewMintCardBorder
              ),
              modifier = Modifier
                .weight(1f)
                .clickable { onNudgeIntensityChange(intensity) }
                .testTag("nudge_${intensity.name}")
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Text(
                  text = intensity.label,
                  style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) TextWhite else HydroEmeraldDeep
                  )
                )
                Text(
                  text = intensity.subtitle,
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isSelected) TextWhite else TextSecondary
                  )
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
          shape = RoundedCornerShape(16.dp),
          color = DewMintLight,
          border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(shape = RoundedCornerShape(6.dp), color = HydroDewPillLight) {
                Text("CURRENT PROMPT", style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText), modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
              }

              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable { onShuffleDesiPrompt() }
              ) {
                Text("🔀", fontSize = 13.sp)
                Text("Shuffle", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = uiState.profileDesiPrompt,
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Medium,
                  color = HydroEmeraldDeep,
                  lineHeight = 20.sp
                ),
                modifier = Modifier.weight(1f)
              )

              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(CircleShape)
                  .background(if (uiState.isDesiPromptPlaying) HydroEmeraldDark else HydroEmeraldDeep)
                  .clickable { onPlayVoicePrompt() },
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                  contentDescription = "Play voice",
                  tint = TextWhite,
                  modifier = Modifier.size(20.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text("Voice synthesizer: Hindi-English vernacular", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextMuted))
              Text("Every 45m", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary))
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Reminder Audio Voice", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary))
        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          ReminderVoice.entries.forEach { voice ->
            val isSelected = voice == uiState.reminderVoice

            Surface(
              shape = RoundedCornerShape(16.dp),
              color = if (isSelected) HydroDewPillLight else DewMintCardSurface,
              border = androidx.compose.foundation.BorderStroke(
                if (isSelected) 2.dp else 1.dp,
                if (isSelected) HydroEmerald else DewMintCardBorder
              ),
              modifier = Modifier
                .weight(1f)
                .clickable { onSelectVoice(voice) }
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Box(
                  modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) HydroEmeraldDark else DewMintLight),
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    when (voice) {
                      ReminderVoice.MUMMY_VOICE -> "👩‍👧"
                      ReminderVoice.ZEN_CHIME -> "🔔"
                      ReminderVoice.WATER_RIPPLE -> "💧"
                    },
                    fontSize = 15.sp
                  )
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(voice.title, style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
                Text(voice.soundDesc, style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))

                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = if (isSelected) DewMintLight else Color(0xFFF3F4F6)
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                  ) {
                    if (isSelected) {
                      Text("✓", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText)
                    }
                    Text(
                      text = if (isSelected) "Active" else voice.chip,
                      style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSelected) HydroPillText else TextMuted
                      )
                    )
                  }
                }
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 4. App Preferences Card
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Box(
            modifier = Modifier
              .size(34.dp)
              .clip(CircleShape)
              .background(DewMintLight),
            contentAlignment = Alignment.Center
          ) {
            Icon(imageVector = Icons.Default.Security, contentDescription = null, tint = HydroEmerald, modifier = Modifier.size(18.dp))
          }
          Text("App Preferences", style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
        }

        Spacer(modifier = Modifier.height(16.dp))

        PreferenceRow(
          icon = "🔤",
          title = "Language",
          subtitle = "Interface & Verbal reminders",
          trailingContent = {
            Surface(shape = RoundedCornerShape(12.dp), color = DewMintLight, border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder)) {
              Text(uiState.selectedLanguage, style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep), modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
            }
          }
        )

        Spacer(modifier = Modifier.height(12.dp))

        PreferenceRow(
          icon = "📈",
          title = "Apple Health / Google Fit",
          subtitle = "Synced 2 mins ago",
          trailingContent = {
            Switch(
              checked = uiState.appleHealthGoogleFitSync,
              onCheckedChange = onToggleHealthSync,
              colors = SwitchDefaults.colors(checkedThumbColor = TextWhite, checkedTrackColor = HydroEmeraldDark)
            )
          }
        )

        Spacer(modifier = Modifier.height(12.dp))

        PreferenceRow(
          icon = "📐",
          title = "Unit System",
          subtitle = "Milliliters / Fluid Ounces",
          trailingContent = {
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = DewMintLight,
              border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder)
            ) {
              Row(modifier = Modifier.padding(2.dp)) {
                Surface(
                  shape = RoundedCornerShape(14.dp),
                  color = if (uiState.unitSystem == "ml") HydroEmeraldDark else Color.Transparent,
                  modifier = Modifier.clickable { onSelectUnitSystem("ml") }
                ) {
                  Text("ml", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = if (uiState.unitSystem == "ml") TextWhite else TextSecondary), modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                }
                Surface(
                  shape = RoundedCornerShape(14.dp),
                  color = if (uiState.unitSystem == "fl oz") HydroEmeraldDark else Color.Transparent,
                  modifier = Modifier.clickable { onSelectUnitSystem("fl oz") }
                ) {
                  Text("fl oz", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = if (uiState.unitSystem == "fl oz") TextWhite else TextSecondary), modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
              }
            }
          }
        )

        Spacer(modifier = Modifier.height(12.dp))

        PreferenceRow(
          icon = "☀️",
          title = "Appearance",
          subtitle = "Serene Dew Light Theme",
          trailingContent = {
            Surface(shape = RoundedCornerShape(12.dp), color = HydroDewPillLight) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(HydroEmerald))
                Text(uiState.appearanceTheme, style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText))
              }
            }
          }
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 5. Community & Support Card
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Text("Community & Support", style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
        Spacer(modifier = Modifier.height(14.dp))

        CommunityRow(icon = "👥", title = "Invite Friends & Hydration Squad", subtitle = "Earn Live Badges together", onClick = { onActionToast("Opening squad invitation...") })
        Spacer(modifier = Modifier.height(10.dp))
        CommunityRow(icon = "📥", title = "Export Hydration Logs", subtitle = "Download CSV or clinical PDF report", onClick = { onActionToast("Exporting hydration logs to CSV...") })
        Spacer(modifier = Modifier.height(10.dp))
        CommunityRow(icon = "❓", title = "Help & FAQs", subtitle = "Hydration metrics & troubleshooting", onClick = { onActionToast("Opening HydroBuddy Help Center...") })

        Spacer(modifier = Modifier.height(18.dp))

        Surface(
          shape = RoundedCornerShape(18.dp),
          color = DewMintCardSurface,
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFCA5A5)),
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onActionToast("Logged out successfully!") }
            .testTag("logout_btn")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = AlertRed, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Log Out", style = MaterialTheme.typography.labelMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = AlertRed))
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text("HydroBuddy v2.4.1 (DewBuild)", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextMuted))
      Text("Breathe Gently • Drink Bountifully", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextMuted))
    }
  }
}

@Composable
fun ProfileStatPill(title: String, subtitle: String, modifier: Modifier = Modifier) {
  Surface(
    shape = RoundedCornerShape(16.dp),
    color = DewMintLight,
    border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
    modifier = modifier
  ) {
    Column(
      modifier = Modifier.padding(vertical = 12.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(title, style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
      Text(subtitle, style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary))
    }
  }
}

@Composable
fun BadgeCard(label: String, status: String, isAchieved: Boolean, modifier: Modifier = Modifier) {
  Surface(
    shape = RoundedCornerShape(16.dp),
    color = DewMintCardSurface,
    border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
    modifier = modifier
  ) {
    Column(
      modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(label, style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep), maxLines = 1)
      Spacer(modifier = Modifier.height(4.dp))
      Surface(shape = RoundedCornerShape(6.dp), color = DewMintLight) {
        Text(status, style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText), modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
      }
    }
  }
}

@Composable
fun PreferenceRow(
  icon: String,
  title: String,
  subtitle: String,
  trailingContent: @Composable () -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      modifier = Modifier.weight(1f),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Text(icon, fontSize = 18.sp)
      Column {
        Text(title, style = MaterialTheme.typography.titleSmall.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
        Text(subtitle, style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
      }
    }
    trailingContent()
  }
}

@Composable
fun CommunityRow(
  icon: String,
  title: String,
  subtitle: String,
  onClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .clickable(onClick = onClick)
      .padding(vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      modifier = Modifier.weight(1f),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Text(icon, fontSize = 18.sp)
      Column {
        Text(title, style = MaterialTheme.typography.titleSmall.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
        Text(subtitle, style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = TextSecondary))
      }
    }
    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = TextMuted, modifier = Modifier.size(16.dp))
  }
}
