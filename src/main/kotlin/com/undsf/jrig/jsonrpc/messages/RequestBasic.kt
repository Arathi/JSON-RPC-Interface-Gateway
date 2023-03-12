package com.undsf.jrig.jsonrpc.messages

import com.google.gson.JsonElement

class RequestBasic(
    jsonrpc: String,
    method: String,
    params: JsonElement,
    id: String? = null,
) : Request<JsonElement>(jsonrpc, method, params, id) {
}