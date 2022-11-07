package com.theothermattm

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.http.content.*
import io.ktor.mustache.Mustache
import io.ktor.mustache.MustacheContent
import io.ktor.response.*
import io.ktor.routing.*

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
	val database = DatabaseFactory.init(dbURLRaw?.getString(), dbUser?.getString(), dbPassword?.getString())
	routing {
		static("/static") {
			resources("static")
		}

		get("/") {
			call.respond(MustacheContent("jargongenerator.mustache", mapOf("jargon" to JargonGenerator.generateJargon().jargon)))
		}
		get("/api/jargon") {
			call.respond(JargonGenerator.generateJargon())
		}
	}

}
