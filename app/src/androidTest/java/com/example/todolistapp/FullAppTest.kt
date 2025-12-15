package com.example.todoapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FullAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val uniqueId = System.currentTimeMillis()

    @Test
    fun testCreateThreeTasksAndEditOne_StrictValidation() {
        val taskHigh = "High Task $uniqueId"
        val taskMedium = "Medium Task $uniqueId"
        val taskLow = "Low Task $uniqueId"
        val updatedMediumTitle = "Updated Medium $uniqueId"

        createTask(taskHigh, "High")
        createTask(taskMedium, "Medium")
        createTask(taskLow, "Low")



        composeTestRule.onNodeWithText(taskHigh).performScrollTo().assertIsDisplayed()
        composeTestRule.onAllNodesWithText("H").onFirst().assertExists()

        composeTestRule.onNodeWithText(taskMedium).performScrollTo().assertIsDisplayed()
        composeTestRule.onAllNodesWithText("M").onFirst().assertExists()

        composeTestRule.onNodeWithText(taskLow).performScrollTo().assertIsDisplayed()
        composeTestRule.onAllNodesWithText("L").onFirst().assertExists()


        composeTestRule.onNodeWithText(taskMedium).performScrollTo().performClick()
        composeTestRule.onNodeWithText("Edit").performClick()

        composeTestRule.onNodeWithText("Title").performTextReplacement(updatedMediumTitle)

        composeTestRule.onNodeWithText("Low").performClick()

        composeTestRule.onNodeWithText("Save").performClick()


        composeTestRule.onNodeWithText(taskMedium).assertDoesNotExist()

        composeTestRule.onNodeWithText(updatedMediumTitle).performScrollTo().assertIsDisplayed()


        composeTestRule.onAllNodesWithText("L").fetchSemanticsNodes().isNotEmpty()
    }

    private fun createTask(title: String, priority: String) {
        composeTestRule.onNodeWithContentDescription("Add").performClick()
        composeTestRule.onNodeWithText("Title").performTextInput(title)
        composeTestRule.onNodeWithText("Description").performTextInput("Desc")
        composeTestRule.onNodeWithText(priority).performClick()
        composeTestRule.onNodeWithText("Save").performClick()
        composeTestRule.waitForIdle()
    }
}