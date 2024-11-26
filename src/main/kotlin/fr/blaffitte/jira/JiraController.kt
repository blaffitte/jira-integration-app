package com.axelor.jira

import com.axelor.rpc.ActionRequest
import com.axelor.rpc.ActionResponse

class JiraController {

    private val jiraService = JiraService()

    // Action pour récupérer un ticket Jira
    fun fetchJiraIssue(request: ActionRequest, response: ActionResponse) {
        val issueKey = request.context["issueKey"] as String?
        if (issueKey != null) {
            val jiraData = jiraService.getJiraIssue(issueKey)
            if (jiraData != null) {
                response.setAlert("Ticket Jira récupéré avec succès : $jiraData")
            } else {
                response.setAlert("Erreur lors de la récupération du ticket Jira.")
            }
        } else {
            response.setAlert("Veuillez spécifier une clé de ticket Jira.")
        }
    }
}
