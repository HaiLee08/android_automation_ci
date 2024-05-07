package com.example.loginregistrationfragmentapp.utilities

import java.util.Random

object StringUtil {

    fun randomString(length: Int): String {
        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = Random()
        return String(CharArray(length) { chars[random.nextInt(chars.length)] })
    }

    fun isNullOrEmpty(text: String?): Boolean {
        return text == null || text.trim().isEmpty()
    }
}