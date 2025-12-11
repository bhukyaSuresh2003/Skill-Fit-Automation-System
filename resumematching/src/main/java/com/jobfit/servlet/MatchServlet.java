package com.jobfit.servlet;

import com.jobfit.util.SkillMatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/match")
public class MatchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String resumeText = request.getParameter("resumeText");
        String jobSkillsCsv = request.getParameter("jobSkills");

        List<String> candidateSkills = SkillMatcher.extractSkillsFromResume(resumeText);
        List<String> requiredSkills = SkillMatcher.parseRequiredSkills(jobSkillsCsv);

        double score = SkillMatcher.calculateMatchPercent(candidateSkills, requiredSkills);
        List<String> missingSkills = SkillMatcher.findMissingSkills(candidateSkills, requiredSkills);

        request.setAttribute("score", score);
        request.setAttribute("candidateSkills", candidateSkills);
        request.setAttribute("requiredSkills", requiredSkills);
        request.setAttribute("missingSkills", missingSkills);
        request.setAttribute("resumeText", resumeText);
        request.setAttribute("jobSkillsCsv", jobSkillsCsv);

        request.getRequestDispatcher("jobfit.jsp").forward(request, response);
    }
}
