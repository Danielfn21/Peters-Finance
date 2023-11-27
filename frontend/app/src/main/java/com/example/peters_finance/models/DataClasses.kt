package com.example.peters_finance.models

data class User(
    var username: String,
    var phone_number: String,
    var password: String,
    var notifications: Boolean = true,
    var groups: MutableList<Group>? = null
)

data class Group(
    var name: String,
    var description: String,
    var members: MutableList<User>,
    var expenses: Expense? = null
)

data class Expense(
    var name: String,
    var amount: Number = 0,
    var payer: User,
    var split: MutableList<User>
)