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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
fun ChallengesScreen(
  uiState: HydroBuddyUiState,
  onCheckInMorningBooster: () -> Unit,
  onClaimQuestReward: (String) -> Unit,
  onCheerSquad: () -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(DewMintBackground)
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .padding(bottom = 24.dp)
  ) {
    // Header
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "Back",
        tint = HydroEmeraldDark,
        modifier = Modifier.size(16.dp)
      )
      Text(
        text = "ARENA & MILESTONES",
        style = MaterialTheme.typography.labelSmall.copy(
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = HydroEmeraldDark
        )
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(
        text = "Challenges &\nQuests",
        style = MaterialTheme.typography.headlineLarge.copy(
          fontSize = 24.sp,
          fontWeight = FontWeight.SemiBold,
          color = HydroEmeraldDeep,
          lineHeight = 28.sp
        )
      )

      // Streak Pill
      Surface(
        shape = RoundedCornerShape(20.dp),
        color = DewMintCardSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
        shadowElevation = 2.dp
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text("🔥", fontSize = 16.sp)
          Column {
            Text(
              text = "${uiState.dailyStreakDays}",
              style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = HydroEmeraldDeep
              )
            )
            Text(
              text = "Days STREAK",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary
              )
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Filter Chips
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      listOf("All Quests", "Daily", "Weekly Squad", "Comp").forEachIndexed { idx, label ->
        val isSelected = idx == 0
        Surface(
          shape = RoundedCornerShape(20.dp),
          color = if (isSelected) HydroEmeraldDeep else DewMintCardSurface,
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isSelected) HydroEmeraldDeep else DewMintCardBorder
          ),
          shadowElevation = 2.dp
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            if (idx == 0) {
              Text("⚏", fontSize = 12.sp, color = TextWhite)
            }
            Text(
              text = label,
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (isSelected) TextWhite else TextSecondary
              )
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 1. Featured Quest: 16dp padding
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("featured_quest_card")
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
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = DewMintLight
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(imageVector = Icons.Default.Schedule, contentDescription = null, tint = HydroEmeraldDark, modifier = Modifier.size(14.dp))
              Text("Featured Quest", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText))
            }
          }

          Surface(
            shape = RoundedCornerShape(12.dp),
            color = HydroDewPillLight
          ) {
            Text(
              text = "Day ${uiState.morningBoosterDayCompleted}/7",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = HydroPillText
              ),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
          text = "7-Day Morning Hydration\nBooster",
          style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = HydroEmeraldDeep,
            lineHeight = 24.sp
          )
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = "Drink 500ml within 30 mins of waking up to ignite your metabolism and clear morning brain fog.",
          style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = TextSecondary,
            lineHeight = 20.sp
          )
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Progress: Day ${uiState.morningBoosterDayCompleted} Completed",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroEmeraldDeep
            )
          )
          Text(
            text = "${(uiState.morningBoosterDayCompleted * 100) / 7}%",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroPillText
            )
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
          progress = { uiState.morningBoosterDayCompleted.toFloat() / 7f },
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp)),
          color = HydroEmerald,
          trackColor = DewMintLight,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          (1..7).forEach { dayNum ->
            val isDone = dayNum <= uiState.morningBoosterDayCompleted
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .size(10.dp)
                  .clip(CircleShape)
                  .background(if (isDone) HydroEmerald else DewMintCardBorder)
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = "D$dayNum",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 12.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = if (isDone) HydroEmeraldDeep else TextMuted
                )
              )
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
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(38.dp)
                  .clip(CircleShape)
                  .background(DewMintCardSurface),
                contentAlignment = Alignment.Center
              ) {
                Text("🏅", fontSize = 18.sp)
              }
              Column {
                Text(
                  text = "Morning Pioneer Gold Badge",
                  style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroEmeraldDeep
                  )
                )
                Text(
                  text = "+250 Dew XP & Ambient Aura",
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary
                  )
                )
              }
            }

            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = "Eligible",
              tint = HydroEmerald,
              modifier = Modifier.size(20.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
          onClick = onCheckInMorningBooster,
          shape = RoundedCornerShape(20.dp),
          colors = ButtonDefaults.buttonColors(containerColor = HydroEmerald),
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("drank_500ml_checkin_btn")
        ) {
          Text(
            text = "Drank 500ml • Check-In Today",
            style = MaterialTheme.typography.labelLarge.copy(
              fontSize = 15.sp,
              fontWeight = FontWeight.SemiBold,
              color = TextWhite
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 2. Daily Ritual Quests
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Text(
          text = "Daily Ritual Quests",
          style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = HydroEmeraldDeep
          )
        )
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = HydroDewPillLight
        ) {
          Text(
            text = "2/4 Done",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroPillText
            ),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Text(
        text = "Reset in 6h",
        style = MaterialTheme.typography.labelSmall.copy(
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = TextMuted
        )
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Quests list card: 16dp padding
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
        uiState.quests.forEachIndexed { index, quest ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 10.dp),
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
                  .clip(CircleShape)
                  .background(
                    if (quest.isClaimed) HydroDewPillLight else DewMintLight
                  ),
                contentAlignment = Alignment.Center
              ) {
                if (quest.isClaimed) {
                  Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = HydroEmeraldDark,
                    modifier = Modifier.size(20.dp)
                  )
                } else {
                  Text(
                    when (quest.id) {
                      "q2" -> "☀️"
                      "q3" -> "⏰"
                      "q4" -> "🍃"
                      else -> "💧"
                    },
                    fontSize = 18.sp
                  )
                }
              }

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = quest.title,
                  style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroEmeraldDeep
                  )
                )
                Text(
                  text = quest.description,
                  style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary
                  )
                )
                if (!quest.isClaimed && quest.maxProgress > 1 && quest.id != "q4") {
                  Spacer(modifier = Modifier.height(6.dp))
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                  ) {
                    LinearProgressIndicator(
                      progress = { quest.currentProgress.toFloat() / quest.maxProgress.toFloat() },
                      modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                      color = HydroEmerald,
                      trackColor = DewMintLight,
                    )
                    Text(
                      text = "${(quest.currentProgress * 100) / quest.maxProgress}%",
                      style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextSecondary
                      )
                    )
                  }
                }
              }
            }

            if (quest.isClaimed) {
              Column(horizontalAlignment = Alignment.End) {
                Text(
                  text = "+${quest.xpReward} XP",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroPillText
                  )
                )
                Text(
                  text = "Claimed",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextMuted
                  )
                )
              }
            } else if (quest.actionLabel.isNotEmpty()) {
              Surface(
                shape = RoundedCornerShape(14.dp),
                color = HydroDewPillLight,
                modifier = Modifier.clickable { onClaimQuestReward(quest.id) }
              ) {
                Text(
                  text = quest.actionLabel,
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroPillText
                  ),
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
              }
            } else {
              Surface(
                shape = RoundedCornerShape(12.dp),
                color = DewMintLight,
                modifier = Modifier.clickable { onClaimQuestReward(quest.id) }
              ) {
                Text(
                  text = "+${quest.xpReward} XP",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroPillText
                  ),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
            }
          }

          if (index < uiState.quests.size - 1) {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .height(1.dp)
                .background(CardSubtleHairline)
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 3. Squad Challenges
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Text(
          text = "Squad Challenges",
          style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = HydroEmeraldDeep
          )
        )
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = HydroDewPillLight
        ) {
          Text(
            text = "Live Arena",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroPillText
            ),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Text(
        text = "Find Squad",
        style = MaterialTheme.typography.labelSmall.copy(
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = HydroEmeraldDark
        )
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

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
          Text(
            text = "👥 HYDRO SQUAD #04",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = TextSecondary
            )
          )
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = HydroDewPillLight
          ) {
            Text(
              text = "${uiState.squadCurrentLiters}L / ${uiState.squadTargetLiters}L",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = HydroPillText
              ),
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = "Monsoon Squad\nShowdown",
          style = MaterialTheme.typography.titleMedium.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = HydroEmeraldDeep,
            lineHeight = 20.sp
          )
        )

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
          progress = { (uiState.squadCurrentLiters / uiState.squadTargetLiters).toFloat() },
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp)),
          color = HydroEmeraldDeep,
          trackColor = DewMintLight,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
          shape = RoundedCornerShape(12.dp),
          color = DewMintLight,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text("#2", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText))
            Surface(shape = RoundedCornerShape(8.dp), color = HydroEmerald) {
              Text("You", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextWhite), modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
            }
            Text("You (2,650ml)", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
            Text("• Leader: Priya (2,800ml) 👑", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Medium, color = TextSecondary))
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(horizontalArrangement = Arrangement.spacedBy((-6).dp)) {
            listOf("P", "Y", "R", "+3").forEach { letter ->
              Box(
                modifier = Modifier
                  .size(30.dp)
                  .clip(CircleShape)
                  .background(DewMintLight),
                contentAlignment = Alignment.Center
              ) {
                Text(letter, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep)
              }
            }
          }

          Surface(
            shape = RoundedCornerShape(16.dp),
            color = HydroDewPillLight,
            modifier = Modifier.clickable { onCheerSquad() }
          ) {
            Text(
              text = "Cheer Squad",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = HydroPillText
              ),
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
            Column {
              Text("STARTS TOMORROW", style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TextMuted))
              Text("Weekend 3L Pure Endurance", style = MaterialTheme.typography.titleSmall.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
              Text("🎖️ Limited 'Dew Walker' Badge", style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp, fontWeight = FontWeight.Medium, color = HydroEmeraldDark))
            }

            Button(
              onClick = onCheerSquad,
              shape = RoundedCornerShape(14.dp),
              colors = ButtonDefaults.buttonColors(containerColor = HydroEmeraldDark),
              contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
              modifier = Modifier.height(36.dp)
            ) {
              Text("Join In", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextWhite))
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 4. Hydration Relics Grid
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Text(
          text = "Hydration Relics",
          style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = HydroEmeraldDeep
          )
        )
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = HydroDewPillLight
        ) {
          Text(
            text = "12 Unlocked",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold,
              color = HydroPillText
            ),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Text(
        text = "View All",
        style = MaterialTheme.typography.labelSmall.copy(
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = HydroEmeraldDark
        )
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        RelicCard(relic = uiState.relics[0], modifier = Modifier.weight(1f))
        RelicCard(relic = uiState.relics[1], modifier = Modifier.weight(1f))
      }
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        RelicCard(relic = uiState.relics[2], modifier = Modifier.weight(1f))
        RelicCard(relic = uiState.relics[3], modifier = Modifier.weight(1f))
      }
    }
  }
}

@Composable
fun RelicCard(
  relic: com.example.model.HydrationRelic,
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
        .fillMaxWidth()
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Box(
        modifier = Modifier
          .size(48.dp)
          .clip(CircleShape)
          .background(DewMintLight),
        contentAlignment = Alignment.Center
      ) {
        Text(relic.iconEmoji, fontSize = 24.sp)
      }

      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = relic.title,
        style = MaterialTheme.typography.titleSmall.copy(
          fontSize = 15.sp,
          fontWeight = FontWeight.SemiBold,
          color = HydroEmeraldDeep
        )
      )
      Text(
        text = relic.unlockedDaysAgo,
        style = MaterialTheme.typography.bodySmall.copy(
          fontSize = 13.sp,
          fontWeight = FontWeight.Normal,
          color = TextMuted
        )
      )

      Spacer(modifier = Modifier.height(8.dp))
      Surface(
        shape = RoundedCornerShape(10.dp),
        color = HydroDewPillLight
      ) {
        Text(
          text = relic.perk,
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
