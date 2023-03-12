package com.undsf.jrig.jsonrpc.messages

class Response<R>(
    var jsonrpc: String = Request.VERSION_2_0,
    var result: R? = null,
    var error: Error<*>? = null,
    var id: String? = null
) {
}