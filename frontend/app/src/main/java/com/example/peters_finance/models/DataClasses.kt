package com.example.peters_finance.models

/* TODO: Change API classes and the structure of data.json to match these classes (if even possible)
 *  For now just keep it in app, ignore backend (outside of fetching users of course)
 *  ONLY DO THIS IF LITERALLY EVERYTHING ELSE IS DONE
 */

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
    var expenses: MutableList<Expense>? = null
)

//TODO: Use the map to calculate the amount each user owes, get the payers from the group
data class Expense(
    var name: String,
    var description: String,
    var amount: Number = 0,
    var payers: MutableList<User>,
    var split: MutableMap<User, Number>? = null,
)