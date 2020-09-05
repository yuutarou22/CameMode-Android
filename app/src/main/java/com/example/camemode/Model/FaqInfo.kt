package com.example.camemode.Model

import kotlinx.serialization.Serializable

@Serializable
data class FaqInfo (
        var question: String,
        var answer: String
)