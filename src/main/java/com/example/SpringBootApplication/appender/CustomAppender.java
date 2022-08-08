package com.example.SpringBootApplication.appender;

import com.logicmonitor.logs.LMLogsApi;
import com.logicmonitor.logs.LMLogsApiException;
import com.logicmonitor.logs.LMLogsApiResponse;
import com.logicmonitor.logs.model.LogEntry;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import java.util.Arrays;
import java.util.List;
@Plugin(name = "CustomAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class CustomAppender extends AbstractAppender {
	public CustomAppender(String name, Filter filter, Layout patternLayout) {
		super(name, filter, patternLayout);
	}

	@PluginFactory
	public static CustomAppender createAppender(@PluginAttribute("name") String name,
												@PluginElement("Filter") final Filter filter, @PluginElement("pattern") final Layout patternLayout) {
		return new CustomAppender(name, filter,patternLayout);
	}

	@Override
	public void append(LogEvent event) {
		final byte[] bytes = getLayout().toByteArray(event);
		System.out.print("Custom Log: " + new String(bytes));
		initializeLmLogSdk(new String(bytes));
	}

	private void initializeLmLogSdk(String log) {

		LMLogsApi apiInstance = new LMLogsApi.Builder()
				.withCompany("qauattraces01")
				.withAccessId("A4s3yyVb55Xa6qfXi53S")
				.withAccessKey("8U37x4BTxf%vX3hM3rzL{RQ8~AIEbRS7f_Vjd2LV")
				.withUserAgentHeader("<platform>/<version>")
				.build();

		LogEntry entry = new LogEntry()
				.message(log)
				.putLmResourceIdItem("79358", "petclininc-petclinincService");
		List<LogEntry> logEntries = Arrays.asList(entry);

		LMLogsApiResponse<?> response;
		try {
			response = apiInstance.logIngestPostWithHttpInfo(logEntries);
		} catch ( LMLogsApiException e) {
			e.printStackTrace();
			response = e.getResponse();
		}

		System.out.println("Request ID: " + response.getRequestId());
		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Body: " + response.getData());
	}

}
