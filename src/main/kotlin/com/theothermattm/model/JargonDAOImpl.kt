package com.theothermattm.model

import com.theothermattm.DatabaseFactory.dbQuery
import com.theothermattm.model.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*

class DAOFacadeImpl {
    private fun resultRowToJargon(row: ResultRow) = Jargon(
            id = row[JargonEntries.id],
            type = row[JargonEntries.type],
            value = row[JargonEntries.value]
        )

    suspend fun allJargon(): List<Jargon> = dbQuery {
        JargonEntries.selectAll().map(::resultRowToJargon)
    }
}