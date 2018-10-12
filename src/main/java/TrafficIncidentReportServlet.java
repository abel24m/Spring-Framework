
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrafficIncidentReportServlet {

	private xmlParsingService parser;

	public TrafficIncidentReportServlet() {

	}

	public TrafficIncidentReportServlet(xmlParsingService xmlParser) {
		this.parser = xmlParser;
	}

	@ResponseBody
	@RequestMapping(value = "/trafficincidentreports")
	public String Welcome() {
		return "Welcome to Traffic Incident Report statistics calculation page. Please provide issue_reported as query parameter.";
	}

	@ResponseBody
	@RequestMapping(value = "/trafficincidentreports", params = { "issue_reported" }, method = RequestMethod.GET)
	public String getIssue(@RequestParam("issue_reported") String issue) {
		int numOfIncidents = parser.calculateIssue(issue);
		return "Number of " + issue + " incidents: " + numOfIncidents + "." + parser.categorizeIssue(numOfIncidents);
	}


}
