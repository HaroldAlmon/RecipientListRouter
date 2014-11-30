package com.java;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/* Demonstrate how a message can be duplicated and send to multiple end-points.
 * The following program sends the same messages to the standard output 
 * and the logger channel. */ 
public class RecipientListRouter {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext(
					"spring/RecipientListRouter.xml");
			ErrorDemoGateway gateway = context.getBean(ErrorDemoGateway.class);
			context.registerShutdownHook();

			// This list is split and printed to the stdout as separate lines.
			// The list is also sent to an aggregator and the resulting list is
			// is sent to the logging channel.
			
			// Truth conquers all...
			gateway.process(Arrays.asList("Veritas", "omnia", "vincit"));
		} catch (final Exception e) {
			// handle exceptions properly here
			e.printStackTrace();
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}

	public interface ErrorDemoGateway {
		public Object process(List<?> data);
	}
}
