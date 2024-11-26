package com.axelor.jira

import com.axelor.core.db.Entity
import com.axelor.core.db.Table
import com.axelor.core.db.fields.StringField
import com.axelor.core.db.fields.Many2One
import com.axelor.core.db.fields.DateField
import com.axelor.auth.db.User

@Table(name = "jira_issue")
class JiraIssue : Entity() {

    @StringField
    var ticketId: String? = null

    @StringField
    var summary: String? = null

    @StringField
    var description: String? = null

    @StringField
    var status: String? = null

    @Many2One
    var assignee: User? = null

    @DateField
    var createdDate: java.util.Date? = null
}
