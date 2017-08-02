package at.ac.tuwien.auto.iotsys.xacml.pdp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import an.xacml.adapter.file.XACMLParser;
import an.xacml.context.Decision;
import an.xacml.context.Request;
import an.xacml.context.Response;
import an.xacml.context.Result;
import an.xacml.engine.EvaluationContext;
import an.xacml.policy.AbstractPolicy;
import an.xacml.policy.Effect;
import at.ac.tuwien.auto.iotsys.commons.interceptor.Parameter;
import at.ac.tuwien.auto.iotsys.util.FileHelper;

public class EnterprisePDP implements Pdp {

	private Logger log = Logger.getLogger(EnterprisePDP.class.getName());

	private String resourcePrefix = "res/";

	private String requestTemplate = "";

	private HashMap<String, String> policies = new HashMap<>();

	public EnterprisePDP() {

	}

	public EnterprisePDP(String resourcePrefix) {
		this.resourcePrefix = resourcePrefix;
		try {
			String readFile = resourcePrefix + "request/request.xml";
			log.info("Reading files from " + readFile);
			requestTemplate = FileHelper.readFile(readFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Evaluates a decision request against the underlying XACML policy. The
	 * three String attributes are mandatory. Additional parameters can be set
	 * with the params Map.
	 * 
	 * @param resource
	 *            XACML resource-id
	 * @param subject
	 *            XACML subject-id
	 * @param action
	 *            XACML action-id
	 * @param params
	 *            additional parameters
	 * 
	 * @return in case the request is evaluated to Permit true, false otherwise.
	 */
	public synchronized boolean evaluate(String resource, String subject, String action,
			Map<Parameter, String> params) {
		log.fine("Resource: " + resource);
		log.fine("Subject: " + subject);
		log.fine("Action: " + action);

		if (params == null) {
			params = new HashMap<>();
		}
		params.put(Parameter.RESOURCE, resource);
		params.put(Parameter.SUBJECT, subject);
		params.put(Parameter.ACTION, action);

		try {
			System.setProperty(XACMLParser.CONTEXT_KEY_DEFAULT_SCHEMA_FILE, resourcePrefix + "xacml-2.0-context.xsd");
			System.setProperty(XACMLParser.POLICY_KEY_DEFAULT_SCHEMA_FILE, resourcePrefix + "xacml-2.0-policy.xsd");

			String xacmlRequest = requestTemplate.substring(0);

			xacmlRequest = replaceParams(xacmlRequest, params);
			// log.info(xacmlRequest);

			Request req = XACMLParser.parseRequest(new ByteArrayInputStream(xacmlRequest.getBytes()));

			String policyFileName = PDPInterceptorSettings.getInstance().getPolicyFile();
			String xacmlPolicy;
			synchronized (this) {
				if (!policies.containsKey(policyFileName)) {
					policies.put(policyFileName, FileHelper.readFile(resourcePrefix + "policies/" + policyFileName));
				}
				xacmlPolicy = policies.get(policyFileName);
			}

			// log.info(xacmlPolicy);

			AbstractPolicy policy = XACMLParser.parsePolicy(new ByteArrayInputStream(xacmlPolicy.getBytes()));

			Result result = policy.evaluate(new EvaluationContext(req));
			Response actualResponse = new Response(new Result[] { result });

			ByteArrayOutputStream tempOut = new ByteArrayOutputStream();
			XACMLParser.dumpResponse(actualResponse, tempOut);
			// log.finest("Dump actual response: " + tempOut.toString());
			Decision d = result.getDecision();

			log.fine(d.toString());
			if (!d.equals(Effect.Permit)) {
				return false;
			}
			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			log.severe("Exception occured ....");
			log.severe(e.getClass().getSimpleName());
			e.printStackTrace();
		}

		return false;
	}

	private String replaceParams(String str, Map<Parameter, String> params) {
		for (Parameter r : params.keySet()) {
			str = str.replaceAll("\\{\\$" + r + "\\}", params.get(r).trim());
		}
		str = str.replaceAll("\\{\\$(.)*\\}", "");
		return str;
	}
}
