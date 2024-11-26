// Définir une fonction pour interagir avec le serveur et récupérer un ticket Jira
function fetchJiraIssue(issueKey) {
    // Vérifier si l'issueKey est vide
    if (!issueKey) {
        alert('Veuillez spécifier une clé de ticket Jira');
        return;
    }

    // Appel AJAX pour récupérer les données du ticket Jira via l'API REST
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/axelor/rest/api/jira/fetchIssue', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    // Envoi de la requête avec l'issueKey en JSON
    xhr.send(JSON.stringify({ "issueKey": issueKey }));

    // Traitement de la réponse après la requête
    xhr.onload = function() {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                alert('Ticket Jira récupéré avec succès');
                // Mettez à jour l'interface avec les informations récupérées
                document.getElementById('ticket-summary').innerText = response.data.summary;
                document.getElementById('ticket-status').innerText = response.data.status;
            } else {
                alert('Erreur lors de la récupération du ticket Jira');
            }
        } else {
            alert('Erreur de communication avec le serveur');
        }
    };
}

// Fonction pour gérer l'action sur le bouton dans l'interface
function onFetchTicketClick() {
    var issueKey = document.getElementById('issueKeyInput').value;
    fetchJiraIssue(issueKey);
}

// Ajouter un événement de clic au bouton
document.getElementById('fetchTicketButton').addEventListener('click', onFetchTicketClick);
