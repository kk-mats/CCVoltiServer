package io.github.kk_mats.ccvoltiserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CCVoltiServerApplication

fun main(args:Array<String>)
{
	runApplication<CCVoltiServerApplication>(*args)
}
