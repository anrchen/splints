import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.*;

/**
 *
 * @author Kenneth Serrano from soen487-team08
 *
 */
public class RTSearch {


	/**
	 * Get Issue Details given IssueNumber
	 *
	 * @param issueNumber
	 * @throws Exception
	 */
	public static void getIssueDetails(String host, String query) throws Exception {

		JSONObject json;

		String urlprefix = "http://" + "/REST/1.0/search/ticket?query=" + query;

		try {
			//URL base = new URL(urlprefix);
			URL url = new URL(urlprefix);
			json = new JSONObject(getText(url));
			// System.out.println(response);

			String[] names = JSONObject.getNames(json);


			for (String str : names) {
				if (str.equals(query)) {
					System.out.println(str + ":" + json.get(str));
				}
			}

		} catch (MalformedURLException e) {
			System.err.println("ERROR: " + e.getMessage());
		}
	}

	/**
	 * Get Text given URL
	 *
	 * @param url
	 * @return response
	 */
	public static String getText(URL url) {
		StringBuilder response = new StringBuilder();

		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);

			in.close();

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return response.toString();
	}

}
