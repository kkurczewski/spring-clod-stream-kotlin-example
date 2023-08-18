package org.sanbodx.spring.integration.sandbox

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.cloud.function.context.PollableBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.support.GenericMessage
import reactor.core.publisher.Flux
import java.time.Instant
import java.time.temporal.ChronoUnit.SECONDS
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Configuration
class StreamConfiguration {
    //    @Bean("clock")
    @PollableBean
    fun pollableClock(): () -> Message<Instant> = { GenericMessage(Instant.now().truncatedTo(SECONDS)) }

    @Bean("clock")
    fun fluxClock(): () -> Flux<Instant> = {
        Flux.interval(1.seconds.toJavaDuration()).map { Instant.now().truncatedTo(SECONDS) }
    }

    @Bean
    fun convert(): suspend (Flow<Instant>) -> Flow<String> = { input ->
        input.map { "Current time is $it" }
    }

    @Bean
    fun log(): suspend (Flow<String>) -> Unit = { input -> input.collect { println(it) } }
}