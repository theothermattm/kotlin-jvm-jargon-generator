package com.theothermattm

import kotlin.random.Random
import com.theothermattm.model.*
import com.theothermattm.model.dao

class JargonGenerator {

    var techAdjectives: MutableSet<Jargon> = mutableSetOf()
    var jvmTechnology: MutableSet<Jargon> = mutableSetOf()
    var subTechType: MutableSet<Jargon> = mutableSetOf()
    var typesOfOrganizations: MutableSet<Jargon> = mutableSetOf()
    var sizeAdjectives: MutableSet<Jargon> = mutableSetOf()
    var values : MutableSet<Jargon> = mutableSetOf()

    suspend fun init() {
        println("initiating Jargon from database")
        val jargons = dao.allJargon()

        for( jargon in jargons ) {
            val theSetToAddTo : MutableSet<Jargon> = when (jargon.type) {
                "TECH_ADJECTIVE" -> techAdjectives
                "JVM_TECH" -> jvmTechnology
                "SUB_TECH_TYPE" -> subTechType
                "SIZE_ADJECTIVE" -> sizeAdjectives
                "TYPE_ORGANIZATION" -> typesOfOrganizations
                "VALUE" -> values
                else -> mutableSetOf()
            }
            theSetToAddTo.add(jargon)
        }
    }

    fun generateJargon(): JargonResponse {

        val jargon = techAdjectives.elementAt(Random.nextInt(0, techAdjectives.size)).value + " " +
                jvmTechnology.elementAt(Random.nextInt(0, jvmTechnology.size)).value + " " +
                subTechType.elementAt(Random.nextInt(0, subTechType.size)).value + " " +
                    " that provides " +
                sizeAdjectives.elementAt(Random.nextInt(0, sizeAdjectives.size)).value + " " +
                values.elementAt(Random.nextInt(0, values.size)).value + " for your " +
                typesOfOrganizations.elementAt(Random.nextInt(0, typesOfOrganizations.size)).value

        return JargonResponse( jargon )
    }

}
