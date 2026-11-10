package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.HydroBottomBar
import com.example.ui.components.HydroNavTab
import com.example.ui.components.HydroTopBar
import com.example.ui.screens.ChallengesScreen
import com.example.ui.screens.GroupsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.theme.DewMintBackground
import com.example.ui.theme.HydroBuddyTheme
import com.example.viewmodel.HydroBuddyViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      HydroBuddyTheme {
        HydroBuddyApp()
      }
    }
  }
}

@Composable
fun HydroBuddyApp(
  viewModel: HydroBuddyViewModel = viewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  var currentTab by remember { mutableStateOf(HydroNavTab.HOME) }
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  // Handle in-app feedback snackbars
  LaunchedEffect(uiState.snackbarMessage) {
    uiState.snackbarMessage?.let { msg ->
      snackbarHostState.showSnackbar(msg)
      viewModel.clearSnackbar()
    }
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(DewMintBackground),
    snackbarHost = { SnackbarHost(snackbarHostState) },
    topBar = {
      val (tabTitle, isLiveSync) = when (currentTab) {
        HydroNavTab.HOME -> "HydroBuddy" to true
        HydroNavTab.GROUPS -> "Squad Leaderboard" to true
        HydroNavTab.CHALLENGES -> "Challenges & Arena" to false
        HydroNavTab.PROFILE -> "Profile & Settings" to false
      }
      HydroTopBar(
        currentScreenTitle = tabTitle,
        showLiveSync = isLiveSync,
        mummyModeActive = uiState.mummyModeEnabled,
        onMummyClick = {
          currentTab = HydroNavTab.PROFILE
          scope.launch {
            snackbarHostState.showSnackbar("Opening Mummy Mode settings...")
          }
        },
        onNotificationClick = {
          scope.launch {
            snackbarHostState.showSnackbar("HydroBuddy: Time for a refreshing sip!")
          }
        },
        onProfileClick = {
          currentTab = HydroNavTab.PROFILE
        },
        modifier = Modifier.statusBarsPadding()
      )
    },
    bottomBar = {
      HydroBottomBar(
        currentTab = currentTab,
        onTabSelected = { selected ->
          currentTab = selected
        }
      )
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      when (currentTab) {
        HydroNavTab.HOME -> {
          HomeScreen(
            uiState = uiState,
            onLogWater = { amount -> viewModel.logWater(amount) },
            onTempSelected = { temp -> viewModel.selectWaterTemp(temp) },
            onBeverageSelected = { index -> viewModel.selectBeverage(index) },
            onShuffleDesiPrompt = { viewModel.shuffleDesiPrompt() },
            onToggleMummyMode = { enabled -> viewModel.toggleMummyMode(enabled) }
          )
        }
        HydroNavTab.GROUPS -> {
          GroupsScreen(
            uiState = uiState,
            onChallengePakkaPiya = { id -> viewModel.challengePakkaPiya(id) },
            onCheerMember = { id -> viewModel.cheerSquad(id) },
            onSendWater = { name -> viewModel.sendSquadWater(name) }
          )
        }
        HydroNavTab.CHALLENGES -> {
          ChallengesScreen(
            uiState = uiState,
            onCheckInMorningBooster = { viewModel.checkInMorningBooster() },
            onClaimQuestReward = { questId -> viewModel.claimQuestReward(questId) },
            onCheerSquad = {
              scope.launch {
                snackbarHostState.showSnackbar("Cheered the squad in Live Arena! 💧")
              }
            }
          )
        }
        HydroNavTab.PROFILE -> {
          ProfileScreen(
            uiState = uiState,
            onUpdateDailyGoal = { goal -> viewModel.updateDailyGoal(goal) },
            onToggleMummyMode = { enabled -> viewModel.toggleMummyMode(enabled) },
            onNudgeIntensityChange = { intensity -> viewModel.setNudgeIntensity(intensity) },
            onShuffleDesiPrompt = { viewModel.shuffleDesiPrompt() },
            onPlayVoicePrompt = { viewModel.playVoicePrompt() },
            onSelectVoice = { voice -> viewModel.setReminderVoice(voice) },
            onToggleHealthSync = { sync -> viewModel.toggleHealthSync(sync) },
            onToggleClimateSync = { sync -> viewModel.toggleClimateSync(sync) },
            onSelectUnitSystem = { unit -> viewModel.setUnitSystem(unit) },
            onActionToast = { msg ->
              scope.launch {
                snackbarHostState.showSnackbar(msg)
              }
            }
          )
        }
      }
    }
  }
}
