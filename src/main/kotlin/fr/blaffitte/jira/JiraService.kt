package com.axelor.jira

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.CloseableHttpResponse
import org.apache.http.util.EntityUtils
import org.json.JSONObject
import java.util.Base64

class JiraService {

    private val apiUrl = getProp("jira.api.url") //"https://your-domain.atlassian.net/rest/api/3/issue/"
    private val apiToken = getProp("jira.api.token") //"your-api-token"
    private val email = getProd("jira.email") //"your-email@domain.com"

    @Suppress("UNCHECKED_CAST")
    const fun  <T> getProp(key: String): T {
        val props  = javaClass.classLoader.getResourceAsStream("application.properties").use {
            Properties().apply { load(it) }
        }
        return (props.getProperty(key) as T) ?: throw RuntimeException("could not find property $key")
    }

    // Récupérer un ticket Jira par sa clé
    fun getJiraIssue(issueKey: String): JSONObject? {
        val url = "$apiUrl$issueKey"
        val client: CloseableHttpClient = HttpClients.createDefault()
        val request = HttpGet(url)

        // Authentification via token API
        val authHeader = "Basic " + Base64.getEncoder().encodeToString("$email:$apiToken".toByteArray())
        request.addHeader("Authorization", authHeader)

        try {
            val response: CloseableHttpResponse = client.execute(request)
            val jsonResponse = EntityUtils.toString(response.entity)
            return JSONObject(jsonResponse)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
