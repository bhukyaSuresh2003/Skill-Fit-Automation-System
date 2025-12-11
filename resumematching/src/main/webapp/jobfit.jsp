<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job-Fit Recruitment AI</title>
</head>
<body>

<h2>Job-Fit Recruitment AI</h2>

<form method="post" action="match">
    <p><b>Paste Resume Text Here:</b></p>
    <textarea name="resumeText" rows="8" cols="80"><%= 
        request.getAttribute("resumeText") != null 
            ? request.getAttribute("resumeText") 
            : "" 
    %></textarea>

    <p><b>Enter Job Required Skills:</b> (comma separated)</p>
    <input type="text" name="jobSkills" size="80"
           value="<%= request.getAttribute("jobSkillsCsv") != null 
                ? request.getAttribute("jobSkillsCsv") : "" %>" />

    <p><button type="submit">Check Match</button></p>
</form>
<hr/>

<%
    Double score = (Double) request.getAttribute("score");
    List<String> candidateSkills = (List<String>) request.getAttribute("candidateSkills");
    List<String> requiredSkills  = (List<String>) request.getAttribute("requiredSkills");
    List<String> missingSkills   = (List<String>) request.getAttribute("missingSkills");

    if (score != null) {
%>
<h3>Match Result</h3>
<p><b>Match Score:</b> <%= String.format("%.2f", score) %> %</p>
<p><b>Candidate Skills:</b> <%= candidateSkills %></p>
<p><b>Required Skills:</b> <%= requiredSkills %></p>
<p><b>Missing Skills:</b> <%= missingSkills %></p>
<% } %>

</body>
</html>
