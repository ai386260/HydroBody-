package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WaterDrop
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
fun GroupsScreen(
  uiState: HydroBuddyUiState,
  onChallengePakkaPiya: (String) -> Unit,
  onCheerMember: (String) -> Unit,
  onSendWater: (String) -> Unit,
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
    // Top Title: Leaderboard + LIVE pill
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Leaderboard,
          contentDescription = "Leaderboard",
          tint = HydroEmeraldDeep,
          modifier = Modifier.size(24.dp)
        )
        Text(
          text = "Leaderboard",
          style = MaterialTheme.typography.headlineLarge.copy(
            color = HydroEmeraldDeep,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
          )
        )
      }

      Surface(
        shape = RoundedCornerShape(14.dp),
        color = HydroDewPillLight
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
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
            text = "LIVE",
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

    // 1. Leaderboard Card: solid white, 1.dp border using DewMintCardBorder, rounded 20.dp, elevation 2.dp, 16dp padding
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
        uiState.leaderboard.forEachIndexed { index, user ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              if (user.rank > 1) {
                Text(
                  text = "#${user.rank}",
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSecondary
                  ),
                  modifier = Modifier.width(26.dp)
                )
              }

              Box(
                modifier = Modifier
                  .size(46.dp)
                  .clip(CircleShape)
                  .background(
                    if (user.rank == 1) HydroEmeraldDeep else DewMintLight
                  ),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = user.initials,
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (user.rank == 1) TextWhite else HydroEmeraldDeep
                  )
                )
                if (user.rank == 1) {
                  Text(
                    text = "👑",
                    fontSize = 14.sp,
                    modifier = Modifier
                      .align(Alignment.TopStart)
                      .padding(top = 1.dp, start = 1.dp)
                  )
                }
              }

              Column {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                  Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                      fontSize = 16.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = HydroEmeraldDeep
                    )
                  )
                  if (user.rank == 1) {
                    Surface(
                      shape = RoundedCornerShape(8.dp),
                      color = HydroEmeraldDark
                    ) {
                      Text(
                        text = "#1",
                        style = MaterialTheme.typography.labelSmall.copy(
                          fontSize = 12.sp,
                          fontWeight = FontWeight.SemiBold,
                          color = TextWhite
                        ),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                      )
                    }
                  } else if (user.rank == 2) {
                    Text("🥈", fontSize = 14.sp)
                  } else if (user.rank == 3) {
                    Text("🥉", fontSize = 14.sp)
                  }
                }

                Spacer(modifier = Modifier.height(2.dp))

                if (user.isPendingVerification) {
                  Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFFEF3C7)
                  ) {
                    Text(
                      text = "⏳ ${user.unconfirmedMl}ml unconfirmed",
                      style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFB45309)
                      ),
                      modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                  }
                  Text(
                    text = "${user.loggedMl} ml (Pending friend verify)",
                    style = MaterialTheme.typography.bodySmall.copy(
                      fontSize = 13.sp,
                      fontWeight = FontWeight.Medium,
                      color = TextSecondary
                    )
                  )
                } else {
                  Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = HydroDewPillLight
                  ) {
                    Text(
                      text = "🛡️ ${user.verifiedBadgeText}",
                      style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = HydroPillText
                      ),
                      modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                  }
                  Text(
                    text = "${user.loggedMl} ml (${user.loggedMl * 100 / user.goalMl}%) • Cap 5.0L/day",
                    style = MaterialTheme.typography.bodyMedium.copy(
                      fontSize = 13.sp,
                      fontWeight = FontWeight.Normal,
                      color = TextSecondary
                    )
                  )
                }
              }
            }

            Column(
              horizontalAlignment = Alignment.End,
              verticalArrangement = Arrangement.Center
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = String.format("%.1fL", user.loggedMl / 1000f),
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroEmeraldDeep
                  )
                )
                if (user.rank == 1) {
                  Text("🏆", fontSize = 14.sp)
                }
              }
              Spacer(modifier = Modifier.height(6.dp))
              Box(
                modifier = Modifier
                  .width(76.dp)
                  .height(6.dp)
                  .clip(RoundedCornerShape(3.dp))
                  .background(DewMintLight)
              ) {
                Box(
                  modifier = Modifier
                    .fillMaxWidth(fraction = (user.loggedMl.toFloat() / user.goalMl.toFloat()).coerceIn(0f, 1f))
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(HydroEmerald)
                )
              }
            }
          }

          if (index < uiState.leaderboard.size - 1) {
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

    // 2. Squad Feed Section Header
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.WaterDrop,
          contentDescription = null,
          tint = HydroEmerald,
          modifier = Modifier.size(20.dp)
        )
        Text(
          text = "Squad Feed & Pakka Piya?",
          style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = HydroEmeraldDeep
          )
        )
      }

      Surface(
        shape = RoundedCornerShape(12.dp),
        color = HydroDewPillLight
      ) {
        Text(
          text = "5 Friends",
          style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = HydroPillText
          ),
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 3. Squad Feed List Card: 16dp padding
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
        uiState.squadFeed.forEachIndexed { index, feedItem ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 8.dp),
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
                  .size(42.dp)
                  .clip(CircleShape)
                  .background(DewMintLight),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = feedItem.initials,
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroEmeraldDeep
                  )
                )
              }

              Column {
                Text(
                  text = feedItem.name,
                  style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = HydroEmeraldDeep
                  )
                )
                Spacer(modifier = Modifier.height(2.dp))

                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = when (feedItem.statusColorType) {
                    "warning" -> Color(0xFFFEE2E2)
                    "verified" -> HydroDewPillLight
                    else -> DewMintLight
                  }
                ) {
                  Text(
                    text = when (feedItem.statusColorType) {
                      "warning" -> "⚠️ ${feedItem.statusBadge}"
                      "verified" -> "🛡️ ${feedItem.statusBadge}"
                      else -> feedItem.statusBadge
                    },
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontSize = 13.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = when (feedItem.statusColorType) {
                        "warning" -> AlertRed
                        "verified" -> HydroPillText
                        else -> TextSecondary
                      }
                    ),
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                  )
                }

                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = feedItem.message,
                  style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextMuted
                  )
                )
              }
            }

            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              if (feedItem.canChallengePakkaPiya) {
                Button(
                  onClick = { onChallengePakkaPiya(feedItem.id) },
                  shape = RoundedCornerShape(16.dp),
                  colors = ButtonDefaults.buttonColors(
                    containerColor = HydroEmeraldDark,
                    contentColor = TextWhite
                  ),
                  contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                  modifier = Modifier
                    .height(36.dp)
                    .testTag("pakka_piya_btn_${feedItem.id}")
                ) {
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Text("🛡️", fontSize = 12.sp)
                    Text("Pakka Piya?", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextWhite))
                  }
                }
              } else if (feedItem.hasCheered) {
                Surface(
                  shape = RoundedCornerShape(16.dp),
                  color = HydroDewPillLight,
                  border = androidx.compose.foundation.BorderStroke(1.dp, HydroEmerald),
                  modifier = Modifier
                    .height(36.dp)
                    .clickable { onCheerMember(feedItem.id) }
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Text("💧", fontSize = 13.sp)
                    Text("Cheer (${feedItem.cheersCount})", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText))
                  }
                }
              } else if (feedItem.id == "f5") {
                Surface(
                  shape = RoundedCornerShape(14.dp),
                  color = DewMintLight,
                  modifier = Modifier
                    .height(36.dp)
                    .clickable { onChallengePakkaPiya(feedItem.id) }
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = HydroEmeraldDark, modifier = Modifier.size(14.dp))
                    Text("Self-\nVerify", style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText, lineHeight = 13.sp))
                  }
                }

                Button(
                  onClick = { onSendWater(feedItem.name) },
                  shape = RoundedCornerShape(14.dp),
                  colors = ButtonDefaults.buttonColors(containerColor = HydroEmeraldDark),
                  contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                  modifier = Modifier.height(36.dp)
                ) {
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                  ) {
                    Text("🧃", fontSize = 12.sp)
                    Text("Send", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextWhite))
                  }
                }
              } else {
                Surface(
                  shape = RoundedCornerShape(16.dp),
                  color = HydroDewPillLight,
                  modifier = Modifier
                    .height(36.dp)
                    .clickable { onSendWater(feedItem.name) }
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Text("💧", fontSize = 13.sp)
                    Text("Send", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroPillText))
                  }
                }
              }

              Box(
                modifier = Modifier
                  .size(34.dp)
                  .clip(CircleShape)
                  .background(DewMintLight)
                  .clickable { onSendWater(feedItem.name) },
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.ChatBubbleOutline,
                  contentDescription = "Chat",
                  tint = HydroEmeraldDark,
                  modifier = Modifier.size(16.dp)
                )
              }
            }
          }

          if (index < uiState.squadFeed.size - 1) {
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

    // 4. Invite Squad Friends Floating Card: 16dp padding
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = DewMintCardSurface,
      border = androidx.compose.foundation.BorderStroke(1.dp, DewMintCardBorder),
      shadowElevation = 2.dp,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("invite_squad_card")
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
              .size(42.dp)
              .clip(CircleShape)
              .background(HydroEmeraldDark),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.PersonAdd,
              contentDescription = null,
              tint = TextWhite,
              modifier = Modifier.size(22.dp)
            )
          }

          Column {
            Text(
              text = "Invite Squad Friends",
              style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = HydroEmeraldDeep
              )
            )
            Text(
              text = "hydrobuddy.app/i...",
              style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = TextMuted
              )
            )
          }
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = DewMintLight,
            modifier = Modifier
              .clickable { onSendWater("Squad link copied!") }
              .testTag("copy_link_btn")
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy", tint = HydroEmeraldDeep, modifier = Modifier.size(14.dp))
              Text("Copy", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = HydroEmeraldDeep))
            }
          }

          Button(
            onClick = { onSendWater("Inviting squad via Share sheet...") },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = HydroEmeraldDark),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
            modifier = Modifier
              .height(38.dp)
              .testTag("share_link_btn")
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(imageVector = Icons.Default.Share, contentDescription = "Share", tint = TextWhite, modifier = Modifier.size(14.dp))
              Text("Share", style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextWhite))
            }
          }
        }
      }
    }
  }
}
