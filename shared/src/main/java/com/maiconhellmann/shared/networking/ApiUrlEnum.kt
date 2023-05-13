package com.maiconhellmann.shared.networking

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

/**
 * Enumeração que armazena URLs da API. Em um cenário real, isso pode vir de várias fontes diferentes, como arquivos de construção,
 * variáveis de sistema, strings.xml ou qualquer outra fonte dinâmica, em vez de uma simples classe estática.
 * */
enum class ApiUrlEnum(val baseUrl: String) : Qualifier {
    GITHUB("https://api.github.com");

    override val value: QualifierValue
        get() = this.name
}