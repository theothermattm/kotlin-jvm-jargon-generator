package com.theothermattm

import kotlin.random.Random

class JargonGenerator {

    companion object {

        val techAdjectives = setOf("Advanced", "Scalable", "Robust")
        val jvmTechnology = setOf("Scala", "Kotlin", "Groovy", "Java")
        val subTechType = setOf("Logging", "Data Pipelines", "Search", "SEO", "AI", "Machine Learning")
        val typesOfOrganizations = setOf("Enterprise", "Organization", "Business")
        val sizeAdjectives = setOf("massive", "huge", "fear-inducing", "threatenening levels of")
        val values = setOf("value", "profit", "satisfaction")

        fun generateJargon(): JargonResponse {

            val jargon = techAdjectives.elementAt(Random.nextInt(0, techAdjectives.size)) + " " +
                 jvmTechnology.elementAt(Random.nextInt(0, jvmTechnology.size)) + " " +
                    subTechType.elementAt(Random.nextInt(0, subTechType.size)) + " " +
                     " that provides " +
                    sizeAdjectives.elementAt(Random.nextInt(0, sizeAdjectives.size)) + " " +
                    values.elementAt(Random.nextInt(0, values.size)) + " for your " +
                    typesOfOrganizations.elementAt((Random.nextInt(0, typesOfOrganizations.size)))

            return JargonResponse( jargon )
        }
    }

}
