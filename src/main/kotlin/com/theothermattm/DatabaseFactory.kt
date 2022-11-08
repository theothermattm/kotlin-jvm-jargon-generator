package com.theothermattm

import com.theothermattm.model.JargonEntries
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
    fun init(dbURLRaw : String, dbUser : String, dbPassword : String) {
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:${dbURLRaw}"
        val database = Database.connect(jdbcURL, driver = driverClassName, 
        user = dbUser, password = dbPassword)
        transaction(database) {
            SchemaUtils.create(JargonEntries)
        }
    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}