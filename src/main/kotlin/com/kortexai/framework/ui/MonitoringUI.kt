package com.kortexai.framework.ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class MonitoringUI : Application() {
    override fun start(stage: Stage) {
        val mainScene = Scene(createMainView(), 800.0, 600.0)
        stage.title = "KortexAI Monitor"
        stage.scene = mainScene
        stage.show()
    }
    
    private fun createMainView(): Parent {
        return BorderPane().apply {
            top = createMenuBar()
            center = createDashboard()
            bottom = createStatusBar()
        }
    }
}
