package com.example.grademanagement

class PModel(
    private var artistId: String,
    private var name: String,
    private var email: String,
    private var reg: String
) {
    // Empty constructor required for Firebase Realtime Database
    constructor() : this("", "", "", "")
    // Getter and setter methods for each property
    fun fetchArtistId(): String = artistId
    fun setArtistId(value: String) { artistId = value }

    fun getName(): String = name
    fun setName(value: String) { name = value }

    fun getEmail(): String = email
    fun setEmail(value: String) { email = value }

    fun getReg(): String = reg
    fun setReg(value: String) { reg = value }
}