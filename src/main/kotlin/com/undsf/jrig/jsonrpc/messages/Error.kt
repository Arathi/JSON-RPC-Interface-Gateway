package com.undsf.jrig.jsonrpc.messages

class Error<D>(
    var code: Int,
    var message: String? = null,
    var data: D? = null
) {
    init {
        if (message == null) {
            message = Messages.getOrDefault(code, DefaultErrorMessage)
        }
    }

    companion object {
        const val CodeParseError = -32700
        const val CodeInvalidRequest = -32600
        const val CodeMethodNotFound = -32601
        const val CodeInvalidParams = -32602
        const val CodeInternalError = -32603

        const val DefaultErrorMessage = "Unknown error"

        private val Messages: MutableMap<Int, String> = mutableMapOf()
        init {
            Messages[CodeParseError] = "Parse error"
            Messages[CodeInvalidRequest] = "Invalid Request"
            Messages[CodeMethodNotFound] = "Method not found"
            Messages[CodeInvalidParams] = "Invalid params"
            Messages[CodeInternalError] = "Internal error"
        }
    }
}