package com.theothermattm

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.http.content.*
import io.ktor.mustache.Mustache
import io.ktor.mustache.MustacheContent
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.runBlocking

import io.ktor.serialization.json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

	install(ContentNegotiation) {
		json()
	}
	install(Mustache) {
		mustacheFactory = DefaultMustacheFactory("templates")
	}

	val dbUser = environment.config.property("ktor.database.DATABASE_USER")
	val dbPassword = environment.config.property("ktor.database.DATABASE_PASSWORD")
	val dbURLRaw = environment.config.property("ktor.database.DATABASE_URL_NO_USER")
	DatabaseFactory.init(dbURLRaw?.getString(), dbUser?.getString(), dbPassword?.getString())
	val jargonGenerator = JargonGenerator()
	runBlocking {
		jargonGenerator.init()
	}
	routing {
		static("/static") {
			resources("static")
		}

		get("/") {
			println("Loading jargon page")
			call.respond(MustacheContent("jargongenerator.mustache", mapOf("jargon" to jargonGenerator.generateJargon().jargon)))
		}
		get("/api/jargon") {
			println("Getting jargon")
			call.respond(jargonGenerator.generateJargon())
		}
	}

}
