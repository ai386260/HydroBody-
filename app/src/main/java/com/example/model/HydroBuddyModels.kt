package com.example.model

enum class WaterTemp(
  val title: String,
  val subtitle: String,
  val tempLabel: String,
  val degree: Int
) {
  CHILLED_EARTHEN("Chilled", "Earthen\nMatka", "~18°C", 18),
  ROOM_TEMP("Room Temp", "Natural", "~24°C", 24),
  WARM_DIGESTION("Warm", "Digestion", "~38°C", 38)
}

enum class NudgeIntensity(val label: String, val subtitle: String) {
  GENTLE("Gentle", "Mindful"),
  STRICT_DESI("Strict Desi", "Assertive"),
  SAVAGE("Savage", "Raw Truth")
}

enum class ReminderVoice(val title: String, val soundDesc: String, val chip: String) {
  MUMMY_VOICE("Mummy Voice", "\"Paani piya?\"", "Active"),
  ZEN_CHIME("Zen Chime", "Tibetan Bell", "432 Hz"),
  WATER_RIPPLE("Water Ripple", "Natural Flow", "Stream")
}

data class BeverageItem(
  val name: String,
  val iconEmoji: String,
  val defaultVolumeMl: Int,
  val hydrationPercent: Int
)

data class LeaderboardUser(
  val id: String,
  val initials: String,
  val name: String,
  val rank: Int,
  val loggedMl: Int,
  val goalMl: Int,
  val isVerified: Boolean,
  val verifiedBadgeText: String,
  val isPendingVerification: Boolean = false,
  val unconfirmedMl: Int = 0,
  val isCurrentUser: Boolean = false
)

data class SquadFeedItem(
  val id: String,
  val initials: String,
  val name: String,
  val message: String,
  val details: String,
  val statusBadge: String,
  val statusColorType: String, // "verified", "warning", "info"
  val canChallengePakkaPiya: Boolean,
  val hasCheered: Boolean = false,
  val cheersCount: Int = 3
)

data class Quest(
  val id: String,
  val title: String,
  val description: String,
  val currentProgress: Int,
  val maxProgress: Int,
  val unit: String,
  val xpReward: Int,
  val isCompleted: Boolean,
  val isClaimed: Boolean,
  val actionLabel: String = ""
)

data class HydrationRelic(
  val id: String,
  val title: String,
  val unlockedDaysAgo: String,
  val perk: String,
  val iconEmoji: String
)
