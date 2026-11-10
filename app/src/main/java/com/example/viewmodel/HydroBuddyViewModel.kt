package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.BeverageItem
import com.example.model.HydrationRelic
import com.example.model.LeaderboardUser
import com.example.model.NudgeIntensity
import com.example.model.Quest
import com.example.model.ReminderVoice
import com.example.model.SquadFeedItem
import com.example.model.WaterTemp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HydroBuddyUiState(
  // Hydration Daily
  val currentIntakeMl: Int = 2100,
  val dailyGoalMl: Int = 3200,
  val nextSipTime: String = "3:15 PM",
  val intervalMinutes: Int = 45,
  val aheadPercentage: Int = 14,
  val selectedTemp: WaterTemp = WaterTemp.CHILLED_EARTHEN,
  
  // Paudha Buddy
  val paudhaStage: Int = 3,
  val paudhaStatus: String = "Level 3 Blooming",
  val paudhaSubtext: String = "Hydrated & Happy",

  // Beverages
  val selectedBeverageIndex: Int = 0,
  val beverages: List<BeverageItem> = listOf(
    BeverageItem("Coconut Water (200ml)", "🥥", 200, 100),
    BeverageItem("Nimbu Paani (250ml)", "🍋", 250, 95),
    BeverageItem("Jeera Water (300ml)", "🌾", 300, 100),
    BeverageItem("Herbal Green Tea (180ml)", "🍵", 180, 85),
    BeverageItem("Electrolyte Chia (250ml)", "✨", 250, 100)
  ),

  // Mummy Mode & Desi Nudges
  val mummyModeEnabled: Boolean = true,
  val nudgeIntensity: NudgeIntensity = NudgeIntensity.STRICT_DESI,
  val currentDesiPrompt: String = "\"Bhai kidney ro rahi hai, paani piyo!\"",
  val profileDesiPrompt: String = "\"Oye Aman! Subah se 1 glass paani nahi piya, bas phone me laga rehta hai! Drink now! 💧\"",
  val reminderVoice: ReminderVoice = ReminderVoice.MUMMY_VOICE,
  val isDesiPromptPlaying: Boolean = false,

  // App & Profile Preferences
  val userName: String = "Aman Sharma",
  val userHandle: String = "@amansharma",
  val userLevel: Int = 7,
  val memberSince: String = "Member since Mar 2024",
  val primaryGoal: String = "Goal: Pure Focus & Marathon Training",
  val location: String = "Mumbai, India (ISD)",
  val activeHours: String = "Active: 7:00 AM - 10:30 PM",
  val dailyStreakDays: Int = 18,
  val hydrationScorePercent: Int = 89,
  val totalLoggedLiters: Double = 142.5,
  val bottlesSavedEco: Int = 285,
  val avgDailyMl: Int = 2420,
  val smartClimateSyncActive: Boolean = true,
  val appleHealthGoogleFitSync: Boolean = true,
  val unitSystem: String = "ml", // "ml" or "fl oz"
  val appearanceTheme: String = "Dew Light",
  val selectedLanguage: String = "Hinglish (Mix)",

  // Quests & Challenges
  val activeTabQuestCategory: String = "All Quests",
  val morningBoosterDayCompleted: Int = 5,
  val quests: List<Quest> = listOf(
    Quest("q1", "Early-Bird Sip", "500ml before 9:00 AM completed", 500, 500, "ml", 50, isCompleted = true, isClaimed = true),
    Quest("q2", "Midday Refuel", "Reach 1,000ml before 2:00 PM", 750, 1000, "ml", 80, isCompleted = false, isClaimed = false),
    Quest("q3", "Mummy's Warning Honored", "Log water within 15m of reminder", 1, 2, "prompts", 60, isCompleted = false, isClaimed = false),
    Quest("q4", "Zero Sugar Streak", "Pure water or herbal infusions only", 0, 1, "day", 40, isCompleted = false, isClaimed = false, actionLabel = "Log Tea")
  ),
  val squadCurrentLiters: Double = 12.4,
  val squadTargetLiters: Double = 15.0,
  val relics: List<HydrationRelic> = listOf(
    HydrationRelic("r1", "Aqua Starter", "Unlocked 18d ago", "+5% Hydration XP", "💧"),
    HydrationRelic("r2", "14-Day Hydro King", "Unlocked 4d ago", "Flame Avatar Border", "👑"),
    HydrationRelic("r3", "Night Sip Master", "Unlocked 7d ago", "Bedtime Zen", "🌙"),
    HydrationRelic("r4", "Desi Chai Balancer", "Unlocked yesterday", "1:1 Tea Flush", "☕")
  ),

  // Leaderboard & Squad Feed
  val leaderboard: List<LeaderboardUser> = listOf(
    LeaderboardUser("u1", "PS", "Priya Sharma", 1, 2800, 2500, true, "Pakka Piya ✓"),
    LeaderboardUser("u2", "AR", "Aryan Roy", 2, 2400, 2400, true, "100% verified"),
    LeaderboardUser("u3", "SV", "Sameer Verma", 3, 2150, 2500, true, "Verified"),
    LeaderboardUser("u4", "NK", "Neha Kapur", 4, 1850, 2500, false, "800ml unconfirmed", isPendingVerification = true, unconfirmedMl = 800)
  ),
  val squadFeed: List<SquadFeedItem> = listOf(
    SquadFeedItem(
      "f1", "SV", "Sameer Verma",
      "2.15L (86%) • Logged 350ml • 7d streak",
      "Logged 350ml chilled matka water",
      "Confirmed by Aryan", "verified",
      canChallengePakkaPiya = false
    ),
    SquadFeedItem(
      "f2", "NK", "Neha Kapur",
      "Logged 800ml at once • Rehydrate warning",
      "Logged 800ml dump",
      ">750ml Unconfirmed", "warning",
      canChallengePakkaPiya = true
    ),
    SquadFeedItem(
      "f3", "VR", "Vikram Rathore",
      "1.40L (56%) • Needs squad encouragement",
      "Logged 900ml dump",
      "900ml Dump", "warning",
      canChallengePakkaPiya = true
    ),
    SquadFeedItem(
      "f4", "PS", "Priya Sharma",
      "2.80L (112%) • Daily goal met 🎉",
      "Hit 112% target",
      "Pakka Piya ✓", "verified",
      canChallengePakkaPiya = false,
      hasCheered = true
    ),
    SquadFeedItem(
      "f5", "RV", "Rahul Verma",
      "1.20L (48%) • Just logged 250ml",
      "Logged 250ml",
      "Logged 250ml", "info",
      canChallengePakkaPiya = false
    )
  ),

  // Notification / Toast Message
  val snackbarMessage: String? = null
)

class HydroBuddyViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(HydroBuddyUiState())
  val uiState: StateFlow<HydroBuddyUiState> = _uiState.asStateFlow()

  private val desiPromptsList = listOf(
    "\"Bhai kidney ro rahi hai, paani piyo!\"",
    "\"Subah se bas reels dekh rahe ho, ek ghoont paani bhi pi lo!\"",
    "\"Khana kha liya aur paani bhool gaye? Beta dehydration pakad lega!\"",
    "\"Oye Hero! Glowing skin chahiye aur paani se dosti nahi?\"",
    "\"Ek glass thanda matka paani piyo, dimaag shant rahega!\""
  )

  fun logWater(amountMl: Int = 250) {
    _uiState.update { current ->
      val newAmount = current.currentIntakeMl + amountMl
      val newLoggedLiters = ((newAmount / 1000.0) + 140.4)
      val newAhead = (newAmount * 100 / current.dailyGoalMl) - 52
      current.copy(
        currentIntakeMl = newAmount,
        totalLoggedLiters = (newLoggedLiters * 10).toInt() / 10.0,
        aheadPercentage = newAhead.coerceAtLeast(0),
        snackbarMessage = "+${amountMl}ml logged! Shabash, keep going!"
      )
    }
  }

  fun updateDailyGoal(newGoalMl: Int) {
    _uiState.update { it.copy(dailyGoalMl = newGoalMl) }
  }

  fun selectWaterTemp(temp: WaterTemp) {
    _uiState.update { it.copy(selectedTemp = temp) }
  }

  fun selectBeverage(index: Int) {
    _uiState.update { it.copy(selectedBeverageIndex = index) }
  }

  fun toggleMummyMode(enabled: Boolean) {
    _uiState.update {
      it.copy(
        mummyModeEnabled = enabled,
        snackbarMessage = if (enabled) "Mummy Mode activated! Prepare for desi love & reality checks." else "Mummy Mode paused."
      )
    }
  }

  fun setNudgeIntensity(intensity: NudgeIntensity) {
    _uiState.update { it.copy(nudgeIntensity = intensity) }
  }

  fun shuffleDesiPrompt() {
    _uiState.update { current ->
      val currentIdx = desiPromptsList.indexOf(current.currentDesiPrompt)
      val nextIdx = (currentIdx + 1) % desiPromptsList.size
      current.copy(
        currentDesiPrompt = desiPromptsList[nextIdx],
        profileDesiPrompt = desiPromptsList[nextIdx]
      )
    }
  }

  fun playVoicePrompt() {
    _uiState.update { it.copy(isDesiPromptPlaying = true, snackbarMessage = "Playing: Mummy reminder audio (\"Paani Piya?\")") }
    viewModelScope.launch {
      kotlinx.coroutines.delay(2000)
      _uiState.update { it.copy(isDesiPromptPlaying = false) }
    }
  }

  fun setReminderVoice(voice: ReminderVoice) {
    _uiState.update { it.copy(reminderVoice = voice) }
  }

  fun toggleHealthSync(enabled: Boolean) {
    _uiState.update { it.copy(appleHealthGoogleFitSync = enabled) }
  }

  fun toggleClimateSync(enabled: Boolean) {
    _uiState.update { it.copy(smartClimateSyncActive = enabled) }
  }

  fun setUnitSystem(unit: String) {
    _uiState.update { it.copy(unitSystem = unit) }
  }

  fun checkInMorningBooster() {
    _uiState.update { current ->
      if (current.morningBoosterDayCompleted < 7) {
        val nextDay = current.morningBoosterDayCompleted + 1
        current.copy(
          morningBoosterDayCompleted = nextDay,
          currentIntakeMl = current.currentIntakeMl + 500,
          snackbarMessage = "Day $nextDay Check-in Complete! +500ml & +250 Dew XP!"
        )
      } else {
        current.copy(snackbarMessage = "7-Day Morning Hydration Booster Already Completed!")
      }
    }
  }

  fun claimQuestReward(questId: String) {
    _uiState.update { current ->
      val updatedList = current.quests.map { q ->
        if (q.id == questId) q.copy(isClaimed = true, isCompleted = true) else q
      }
      current.copy(
        quests = updatedList,
        snackbarMessage = "Claimed XP reward!"
      )
    }
  }

  fun cheerSquad(memberId: String) {
    _uiState.update { current ->
      val updatedFeed = current.squadFeed.map { item ->
        if (item.id == memberId) {
          item.copy(
            hasCheered = !item.hasCheered,
            cheersCount = if (!item.hasCheered) item.cheersCount + 1 else item.cheersCount - 1
          )
        } else item
      }
      current.copy(
        squadFeed = updatedFeed,
        snackbarMessage = "Cheered squad member! 👏"
      )
    }
  }

  fun challengePakkaPiya(feedItemId: String) {
    _uiState.update { current ->
      val updatedFeed = current.squadFeed.map { item ->
        if (item.id == feedItemId) {
          item.copy(
            statusBadge = "Pakka Piya Verified!",
            statusColorType = "verified",
            canChallengePakkaPiya = false
          )
        } else item
      }
      current.copy(
        squadFeed = updatedFeed,
        snackbarMessage = "Challenged 'Pakka Piya?' - verification requested!"
      )
    }
  }

  fun sendSquadWater(name: String) {
    _uiState.update { it.copy(snackbarMessage = "Sent hydration reminder to $name! 💧") }
  }

  fun clearSnackbar() {
    _uiState.update { it.copy(snackbarMessage = null) }
  }
}
