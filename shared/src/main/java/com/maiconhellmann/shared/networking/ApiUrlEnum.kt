package com.maiconhellmann.shared.networking

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

/**
 * Enum that holds API URLs. In a real scenario this could come from many different sources like build files,
 * system variables, strings.xml or any other dynamic source instead of a simple static class.
 */
enum class ApiUrlEnum(val baseUrl: String) : Qualifier {
    GITHUB("https://api.github.com");

    override val value: QualifierValue
        get() = this.name
}