package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.model.WaterTemp
import com.example.ui.components.HydroWaterGlassCard
import com.example.ui.components.MummyModeBanner
import com.example.ui.components.PaudhaBuddyCard
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
fun HomeScreen(
  uiState: HydroBuddyUiState,
  onLogWater: (Int) -> Unit,
  onTempSelected: (WaterTemp) -> Unit,
  onBeverageSelected: (Int) -> Unit,
  onShuffleDesiPrompt: () -> Unit,
  onToggleMummyMode: (Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(DewMintBackground)
      .verticalScroll(scrollState)
      .padding(bottom = 24.dp)
  ) {
    // 1. Mummy Mode Banner
    if (uiState.mummyModeEnabled) {
      MummyModeBanner(
        promptText = uiState.currentDesiPrompt,
        onDrinkClick = { onLogWater(250) },
        onShuffleClick = onShuffleDesiPrompt,
        onDismiss = { onToggleMummyMode(false) }
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 2. Schedule Pill Card + Water Glass & Paudha Buddy
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        // Next sip pill header
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = HydroDewPillLight,
          border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = "Next sip",
                tint = HydroEmeraldDark,
                modifier = Modifier.size(18.dp)
              )
              Text(
                text = "Next sip at ${uiState.nextSipTime}",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontSize = 15.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = HydroEmeraldDeep
                )
              )
            }
            Text(
              text = "• Every ${uiState.intervalMinutes} mins",
              style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = TextSecondary
              )
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Side-by-side: Water Level Glass Card + Paudha Buddy
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          HydroWaterGlassCard(
            currentMl = uiState.currentIntakeMl,
            goalMl = uiState.dailyGoalMl,
            scorePercent = (uiState.currentIntakeMl * 100 / uiState.dailyGoalMl).coerceAtMost(100),
            modifier = Modifier
              .weight(1.15f)
              .height(240.dp)
          )

          PaudhaBuddyCard(
            level = uiState.paudhaStage,
            status = uiState.paudhaStatus,
            mood = uiState.paudhaSubtext,
            modifier = Modifier
              .weight(0.85f)
              .height(240.dp)
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val remainingMl = (uiState.dailyGoalMl - uiState.currentIntakeMl).coerceAtLeast(0)
        val remainingL = String.format("%.1fL", remainingMl / 1000f)

        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "\"Shabash! Sirf $remainingL aur bacha hai\" 👏",
            style = MaterialTheme.typography.titleLarge.copy(
              fontSize = 20.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroEmeraldDeep
            )
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "You are ${uiState.aheadPercentage}% ahead of your regular daily pace.",
            style = MaterialTheme.typography.bodyMedium.copy(
              fontSize = 15.sp,
              fontWeight = FontWeight.Normal,
              color = TextSecondary
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 3. Water Temperature Selection Section
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
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
              Icon(
                imageVector = Icons.Outlined.Thermostat,
                contentDescription = "Temperature",
                tint = HydroEmerald,
                modifier = Modifier.size(20.dp)
              )
            }
            Column {
              Text(
                text = "Water Temperature",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontSize = 20.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = HydroEmeraldDeep
                )
              )
              Text(
                text = "Optimizes body absorption",
                style = MaterialTheme.typography.bodySmall.copy(
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Normal,
                  color = TextSecondary
                )
              )
            }
          }

          // Matka Cooled Pill
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = HydroDewPillLight
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(6.dp)
                  .clip(CircleShape)
                  .background(HydroEmerald)
              )
              Text(
                text = "Matka Cooled ~18°C",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = HydroPillText
                )
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          WaterTemp.entries.forEach { temp ->
            val isSelected = temp == uiState.selectedTemp

            Surface(
              shape = RoundedCornerShape(20.dp),
              color = if (isSelected) HydroDewPillLight else DewMintCardSurface,
              border = androidx.compose.foundation.BorderStroke(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) HydroEmerald else DewMintCardBorder
              ),
              shadowElevation = 2.dp,
              modifier = Modifier
                .weight(1f)
                .clickable { onTempSelected(temp) }
                .testTag("temp_${temp.name}")
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 14.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Box(
                  modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(
                      when (temp) {
                        WaterTemp.CHILLED_EARTHEN -> if (isSelected) HydroEmeraldDark else DewMintLight
                        WaterTemp.ROOM_TEMP -> Color(0xFFE0F2FE)
                        WaterTemp.WARM_DIGESTION -> Color(0xFFFEF3C7)
                      }
                    ),
                  contentAlignment = Alignment.Center
                ) {
                  when (temp) {
                    WaterTemp.CHILLED_EARTHEN -> Icon(
                      imageVector = Icons.Default.AcUnit,
                      contentDescription = null,
                      tint = if (isSelected) Color.White else HydroEmerald,
                      modifier = Modifier.size(20.dp)
                    )
                    WaterTemp.ROOM_TEMP -> Icon(
                      imageVector = Icons.Default.WaterDrop,
                      contentDescription = null,
                      tint = Color(0xFF0284C7),
                      modifier = Modifier.size(20.dp)
                    )
                    WaterTemp.WARM_DIGESTION -> Icon(
                      imageVector = Icons.Default.LocalFireDepartment,
                      contentDescription = null,
                      tint = Color(0xFFD97706),
                      modifier = Modifier.size(20.dp)
                    )
                  }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = temp.title,
                  style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) HydroEmeraldDeep else TextPrimary
                  )
                )
                Text(
                  text = temp.subtitle,
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    lineHeight = 16.sp
                  )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = if (isSelected) DewMintLight else Color(0xFFF3F4F6)
                ) {
                  Text(
                    text = temp.tempLabel,
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontSize = 13.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = if (isSelected) HydroPillText else TextMuted
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                  )
                }
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 4. Beverage Hydration Index Section
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "BEVERAGE HYDRATION INDEX",
          style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextSecondary
          )
        )
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(
            text = "Swipe",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroEmeraldDark
            )
          )
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Swipe beverages",
            tint = HydroEmeraldDark,
            modifier = Modifier.size(16.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        uiState.beverages.forEachIndexed { index, bev ->
          val isSelected = index == uiState.selectedBeverageIndex

          Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isSelected) HydroDewPillLight else DewMintCardSurface,
            border = androidx.compose.foundation.BorderStroke(
              if (isSelected) 2.dp else 1.dp,
              if (isSelected) HydroEmerald else DewMintCardBorder
            ),
            shadowElevation = 2.dp,
            modifier = Modifier
              .clickable { onBeverageSelected(index) }
              .testTag("beverage_item_$index")
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(text = bev.iconEmoji, fontSize = 18.sp)
              Text(
                text = bev.name,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontSize = 15.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = HydroEmeraldDeep
                )
              )
              Surface(
                shape = RoundedCornerShape(10.dp),
                color = DewMintLight
              ) {
                Text(
                  text = "${bev.hydrationPercent}% Hydration",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroPillText
                  ),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 5. Main Primary Log Active Glass Action Button
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ) {
      Button(
        onClick = { onLogWater(250) },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = HydroEmerald,
          contentColor = TextWhite
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp, pressedElevation = 1.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(60.dp)
          .testTag("log_active_glass_button")
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(HydroEmeraldDark),
              contentAlignment = Alignment.Center
            ) {
              Text("🥛", fontSize = 18.sp)
            }
            Column {
              Text(
                text = "Log Active Glass",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontSize = 16.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = TextWhite
                )
              )
              Text(
                text = "Standard Serving • +250ml",
                style = MaterialTheme.typography.bodySmall.copy(
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Medium,
                  color = TextWhite
                )
              )
            }
          }

          Surface(
            shape = RoundedCornerShape(14.dp),
            color = HydroEmeraldDark
          ) {
            Text(
              text = "+250ml +",
              style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
              ),
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
            )
          }
        }
      }
    }
  }
}
