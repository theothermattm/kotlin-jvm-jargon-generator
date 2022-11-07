package com.theothermattm.model

import org.jetbrains.exposed.sql.*

data class Jargon(val id : Int, val type : String, val value : String)

object JargonEntries : Table() {
    val id = integer("id").autoIncrement()
    val type = varchar("type", 128)
    // TODO what to pass for value?
    val value = text("value" )

    override val primaryKey = PrimaryKey(id)
}