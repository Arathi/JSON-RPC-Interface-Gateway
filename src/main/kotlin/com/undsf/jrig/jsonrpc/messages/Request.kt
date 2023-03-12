package com.undsf.jrig.jsonrpc.messages

open class Request<P>(
    var jsonrpc: String = VERSION_2_0,
    var method: String? = null,
    var params: P? = null,
    var id: String? = null,
) {
    companion object {
        const val VERSION_2_0 = "2.0"
    }
}