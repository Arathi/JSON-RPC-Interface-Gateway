package com.undsf.jrig.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.undsf.jrig.jsonrpc.BaseJsonRpcController
import com.undsf.jrig.jsonrpc.messages.Request
import com.undsf.jrig.jsonrpc.messages.RequestBasic
import com.undsf.jrig.jsonrpc.messages.Response
import com.undsf.jrig.plugins.RemoteProcedurePlugin
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

typealias JsonRpcError = com.undsf.jrig.jsonrpc.messages.Error<Any>

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("")
class JsonRpcController : BaseJsonRpcController() {
    @Autowired
    lateinit var gson: Gson

    val remoteProcedures: List<RemoteProcedurePlugin> = mutableListOf()

    @PostConstruct
    fun init() {

    }

    @PostMapping("/**")
    fun postWithPath(
        servletRequest: HttpServletRequest,
        @RequestBody reqBody: String) : Response<*> {
        val path = servletRequest.servletPath
        return post(path, reqBody)
    }

    protected fun post(path: String, reqBody: String) : Response<*> {
        var error: JsonRpcError? = null
        var reqMsg: RequestBasic? = null

        do {
            // 转换json
            reqMsg = gson.fromJson(reqBody, reqTypeBasic)
            if (reqMsg == null) {
                error = JsonRpcError(JsonRpcError.CodeParseError)
                break
            }

            logger.info { "PATH = $path" }
            logger.info { "JSON-RPC ${reqMsg.jsonrpc}" }
            logger.info { "    method: ${reqMsg.method}" }
            logger.info { "    params: ${reqMsg.params}" }
            logger.info { "    id: ${reqMsg.id}" }

            // 检查json-rpc版本
            if (Request.VERSION_2_0 != reqMsg.jsonrpc) {
                logger.warn { "版本不符" }
                error = JsonRpcError(JsonRpcError.CodeInvalidRequest)
                break
            }

            // 检查方法
            if (reqMsg.method == null) {
                logger.warn { "未填写方法" }
                error = JsonRpcError(JsonRpcError.CodeInvalidRequest)
                break
            }

            // 获取
        } while (false)

        return Response<Void>(
            jsonrpc = reqMsg?.jsonrpc ?: Request.VERSION_2_0,
            error = error,
            id = reqMsg?.id
        )
    }

    companion object {
        val reqTypeBasic = object : TypeToken<RequestBasic>() {}
    }
}