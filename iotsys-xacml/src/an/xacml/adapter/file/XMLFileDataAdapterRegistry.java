package an.xacml.adapter.file;

import java.util.Hashtable;
import java.util.Map;

import an.xacml.adapter.file.context.FileAdapterAttribute;
import an.xacml.adapter.file.context.FileAdapterMissingAttributeDetail;
import an.xacml.adapter.file.context.FileAdapterRequest;
import an.xacml.adapter.file.context.FileAdapterResourceContent;
import an.xacml.adapter.file.context.FileAdapterResponse;
import an.xacml.adapter.file.context.FileAdapterResult;
import an.xacml.adapter.file.context.FileAdapterStatus;
import an.xacml.adapter.file.context.FileAdapterStatusCode;
import an.xacml.adapter.file.context.FileAdapterStatusDetail;
import an.xacml.adapter.file.policy.FileAdapterActionMatch;
import an.xacml.adapter.file.policy.FileAdapterActions;
import an.xacml.adapter.file.policy.FileAdapterApply;
import an.xacml.adapter.file.policy.FileAdapterAttributeAssignment;
import an.xacml.adapter.file.policy.FileAdapterAttributeDesignator;
import an.xacml.adapter.file.policy.FileAdapterAttributeSelector;
import an.xacml.adapter.file.policy.FileAdapterAttributeValue;
import an.xacml.adapter.file.policy.FileAdapterCombinerParameter;
import an.xacml.adapter.file.policy.FileAdapterCombinerParameters;
import an.xacml.adapter.file.policy.FileAdapterCondition;
import an.xacml.adapter.file.policy.FileAdapterDefaults;
import an.xacml.adapter.file.policy.FileAdapterEnvironmentMatch;
import an.xacml.adapter.file.policy.FileAdapterEnvironments;
import an.xacml.adapter.file.policy.FileAdapterExpression;
import an.xacml.adapter.file.policy.FileAdapterFunction;
import an.xacml.adapter.file.policy.FileAdapterIdReference;
import an.xacml.adapter.file.policy.FileAdapterObligation;
import an.xacml.adapter.file.policy.FileAdapterObligations;
import an.xacml.adapter.file.policy.FileAdapterPolicy;
import an.xacml.adapter.file.policy.FileAdapterPolicyCombinerParameters;
import an.xacml.adapter.file.policy.FileAdapterPolicySet;
import an.xacml.adapter.file.policy.FileAdapterPolicySetCombinerParameters;
import an.xacml.adapter.file.policy.FileAdapterResourceMatch;
import an.xacml.adapter.file.policy.FileAdapterResources;
import an.xacml.adapter.file.policy.FileAdapterRule;
import an.xacml.adapter.file.policy.FileAdapterRuleCombinerParameters;
import an.xacml.adapter.file.policy.FileAdapterSubjectAttributeDesignator;
import an.xacml.adapter.file.policy.FileAdapterSubjectMatch;
import an.xacml.adapter.file.policy.FileAdapterSubjects;
import an.xacml.adapter.file.policy.FileAdapterTarget;
import an.xacml.adapter.file.policy.FileAdapterVariableDefinition;
import an.xacml.adapter.file.policy.FileAdapterVariableReference;
import an.xacml.context.Attribute;
import an.xacml.context.MissingAttributeDetail;
import an.xacml.context.Request;
import an.xacml.context.ResourceContent;
import an.xacml.context.Response;
import an.xacml.context.Result;
import an.xacml.context.Status;
import an.xacml.context.StatusCode;
import an.xacml.context.StatusDetail;
import an.xacml.policy.ActionMatch;
import an.xacml.policy.Actions;
import an.xacml.policy.Apply;
import an.xacml.policy.AttributeAssignment;
import an.xacml.policy.AttributeDesignator;
import an.xacml.policy.AttributeSelector;
import an.xacml.policy.AttributeValue;
import an.xacml.policy.CombinerParameter;
import an.xacml.policy.CombinerParameters;
import an.xacml.policy.Condition;
import an.xacml.policy.Defaults;
import an.xacml.policy.EnvironmentMatch;
import an.xacml.policy.Environments;
import an.xacml.policy.Function;
import an.xacml.policy.IdReference;
import an.xacml.policy.Obligation;
import an.xacml.policy.Obligations;
import an.xacml.policy.Policy;
import an.xacml.policy.PolicyCombinerParameters;
import an.xacml.policy.PolicySet;
import an.xacml.policy.PolicySetCombinerParameters;
import an.xacml.policy.ResourceMatch;
import an.xacml.policy.Resources;
import an.xacml.policy.Rule;
import an.xacml.policy.RuleCombinerParameters;
import an.xacml.policy.SubjectAttributeDesignator;
import an.xacml.policy.SubjectMatch;
import an.xacml.policy.Subjects;
import an.xacml.policy.VariableDefinition;
import an.xacml.policy.VariableReference;

public abstract class XMLFileDataAdapterRegistry {
	public static final String POLICY_TAG_ACTIONMATCH = "ActionMatchType";
	public static final String POLICY_TAG_ACTION = "ActionType";
	public static final String POLICY_TAG_ACTIONS = "ActionsType";
	public static final String POLICY_TAG_APPLY = "ApplyType";
	public static final String POLICY_TAG_ATTRIBUTEASSIGNMENT = "AttributeAssignmentType";
	public static final String POLICY_TAG_ATTRIBUTEDESIGNATOR = "AttributeDesignatorType";
	public static final String POLICY_TAG_ATTRIBUTESELECTOR = "AttributeSelectorType";
	public static final String POLICY_TAG_ATTRIBUTEVALUE = "AttributeValueType";
	public static final String POLICY_TAG_COMBINERPARAMETER = "CombinerParameterType";
	public static final String POLICY_TAG_COMBINERPARAMETERS = "CombinerParametersType";
	public static final String POLICY_TAG_CONDITION = "ConditionType";
	public static final String POLICY_TAG_DEFAULTS = "DefaultsType";
	public static final String POLICY_TAG_ENVIRONMENTMATCH = "EnvironmentMatchType";
	public static final String POLICY_TAG_ENVIRONMENT = "EnvironmentType";
	public static final String POLICY_TAG_ENVIRONMENTS = "EnvironmentsType";
	public static final String POLICY_TAG_EXPRESSION = "ExpressionType";
	public static final String POLICY_TAG_FUNCTION = "FunctionType";
	public static final String POLICY_TAG_IDREFERENCE = "IdReferenceType";
	public static final String POLICY_TAG_OBLIGATION = "ObligationType";
	public static final String POLICY_TAG_OBLIGATIONS = "ObligationsType";
	public static final String POLICY_TAG_POLICYCOMBINERPARAMETERS = "PolicyCombinerParametersType";
	public static final String POLICY_TAG_POLICYSETCOMBINERPARAMETERS = "PolicySetCombinerParametersType";
	public static final String POLICY_TAG_POLICYSET = "PolicySetType";
	public static final String POLICY_TAG_POLICY = "PolicyType";
	public static final String POLICY_TAG_RESOURCEMATCH = "ResourceMatchType";
	public static final String POLICY_TAG_RESOURCE = "ResourceType";
	public static final String POLICY_TAG_RESOURCES = "ResourcesType";
	public static final String POLICY_TAG_RULECOMBINERPARAMETERS = "RuleCombinerParametersType";
	public static final String POLICY_TAG_RULE = "RuleType";
	public static final String POLICY_TAG_SUBJECTATTRIBUTEDESIGNATOR = "SubjectAttributeDesignatorType";
	public static final String POLICY_TAG_SUBJECTMATCH = "SubjectMatchType";
	public static final String POLICY_TAG_SUBJECT = "SubjectType";
	public static final String POLICY_TAG_SUBJECTS = "SubjectsType";
	public static final String POLICY_TAG_TARGET = "TargetType";
	public static final String POLICY_TAG_VARIABLEDEFINITION = "VariableDefinitionType";
	public static final String POLICY_TAG_VARIABLEREFERENCE = "VariableReferenceType";

	public static final String CONTEXT_TAG_ACTION = "ActionType";
	public static final String CONTEXT_TAG_ATTRIBUTE = "AttributeType";
	public static final String CONTEXT_TAG_ENVIRONMENT = "EnvironmentType";
	public static final String CONTEXT_TAG_REQUEST = "RequestType";
	public static final String CONTEXT_TAG_RESOURCECONTENT = "ResourceContentType";
	public static final String CONTEXT_TAG_RESOURCE = "ResourceType";
	public static final String CONTEXT_TAG_SUBJECT = "SubjectType";

	public static final String CONTEXT_TAG_RESPONSE = "ResponseType";
	public static final String CONTEXT_TAG_RESULT = "ResultType";
	public static final String CONTEXT_TAG_STATUS = "StatusType";
	public static final String CONTEXT_TAG_STATUSCODE = "StatusCodeType";
	public static final String CONTEXT_TAG_STATUSDETAIL = "StatusDetailType";
	public static final String CONTEXT_TAG_MISSINGATTRIBUTEDETAIL = "MissingAttributeDetailType";

	/**
	 * Each XML element maps to a Java class. Following map provides Policy
	 * element mappings.
	 */
	private static Map<String, Class<?>> policyByXMLTag = new Hashtable<>();
	private static Map<Class<?>, Class<?>> policyByXACMLElement = new Hashtable<>();
	/**
	 * Following map provides Context element mappings.
	 */
	private static Map<String, Class<?>> contextByXMLTag = new Hashtable<>();
	private static Map<Class<?>, Class<?>> contextByXACMLElement = new Hashtable<>();

	static {
		policyByXMLTag.put(POLICY_TAG_ACTIONMATCH, FileAdapterActionMatch.class);
		policyByXMLTag.put(POLICY_TAG_ACTION, an.xacml.adapter.file.policy.FileAdapterAction.class);
		policyByXMLTag.put(POLICY_TAG_ACTIONS, FileAdapterActions.class);
		policyByXMLTag.put(POLICY_TAG_APPLY, FileAdapterApply.class);
		policyByXMLTag.put(POLICY_TAG_ATTRIBUTEASSIGNMENT, FileAdapterAttributeAssignment.class);
		policyByXMLTag.put(POLICY_TAG_ATTRIBUTEDESIGNATOR, FileAdapterAttributeDesignator.class);
		policyByXMLTag.put(POLICY_TAG_ATTRIBUTESELECTOR, FileAdapterAttributeSelector.class);
		policyByXMLTag.put(POLICY_TAG_ATTRIBUTEVALUE, FileAdapterAttributeValue.class);
		policyByXMLTag.put(POLICY_TAG_COMBINERPARAMETER, FileAdapterCombinerParameter.class);
		policyByXMLTag.put(POLICY_TAG_COMBINERPARAMETERS, FileAdapterCombinerParameters.class);
		policyByXMLTag.put(POLICY_TAG_CONDITION, FileAdapterCondition.class);
		policyByXMLTag.put(POLICY_TAG_DEFAULTS, FileAdapterDefaults.class);
		policyByXMLTag.put(POLICY_TAG_ENVIRONMENTMATCH, FileAdapterEnvironmentMatch.class);
		policyByXMLTag.put(POLICY_TAG_ENVIRONMENT, an.xacml.adapter.file.policy.FileAdapterEnvironment.class);
		policyByXMLTag.put(POLICY_TAG_ENVIRONMENTS, FileAdapterEnvironments.class);
		policyByXMLTag.put(POLICY_TAG_EXPRESSION, FileAdapterExpression.class);
		policyByXMLTag.put(POLICY_TAG_FUNCTION, FileAdapterFunction.class);
		policyByXMLTag.put(POLICY_TAG_IDREFERENCE, FileAdapterIdReference.class);
		policyByXMLTag.put(POLICY_TAG_OBLIGATION, FileAdapterObligation.class);
		policyByXMLTag.put(POLICY_TAG_OBLIGATIONS, FileAdapterObligations.class);
		policyByXMLTag.put(POLICY_TAG_POLICYCOMBINERPARAMETERS, FileAdapterPolicyCombinerParameters.class);
		policyByXMLTag.put(POLICY_TAG_POLICYSETCOMBINERPARAMETERS, FileAdapterPolicySetCombinerParameters.class);
		policyByXMLTag.put(POLICY_TAG_POLICYSET, FileAdapterPolicySet.class);
		policyByXMLTag.put(POLICY_TAG_POLICY, FileAdapterPolicy.class);
		policyByXMLTag.put(POLICY_TAG_RESOURCEMATCH, FileAdapterResourceMatch.class);
		policyByXMLTag.put(POLICY_TAG_RESOURCE, an.xacml.adapter.file.policy.FileAdapterResource.class);
		policyByXMLTag.put(POLICY_TAG_RESOURCES, FileAdapterResources.class);
		policyByXMLTag.put(POLICY_TAG_RULECOMBINERPARAMETERS, FileAdapterRuleCombinerParameters.class);
		policyByXMLTag.put(POLICY_TAG_RULE, FileAdapterRule.class);
		policyByXMLTag.put(POLICY_TAG_SUBJECTATTRIBUTEDESIGNATOR, FileAdapterSubjectAttributeDesignator.class);
		policyByXMLTag.put(POLICY_TAG_SUBJECTMATCH, FileAdapterSubjectMatch.class);
		policyByXMLTag.put(POLICY_TAG_SUBJECT, an.xacml.adapter.file.policy.FileAdapterSubject.class);
		policyByXMLTag.put(POLICY_TAG_SUBJECTS, FileAdapterSubjects.class);
		policyByXMLTag.put(POLICY_TAG_TARGET, FileAdapterTarget.class);
		policyByXMLTag.put(POLICY_TAG_VARIABLEDEFINITION, FileAdapterVariableDefinition.class);
		policyByXMLTag.put(POLICY_TAG_VARIABLEREFERENCE, FileAdapterVariableReference.class);

		contextByXMLTag.put(CONTEXT_TAG_ACTION, an.xacml.adapter.file.context.FileAdapterAction.class);
		contextByXMLTag.put(CONTEXT_TAG_ATTRIBUTE, FileAdapterAttribute.class);
		contextByXMLTag.put(CONTEXT_TAG_ENVIRONMENT, an.xacml.adapter.file.context.FileAdapterEnvironment.class);
		contextByXMLTag.put(CONTEXT_TAG_REQUEST, FileAdapterRequest.class);
		contextByXMLTag.put(CONTEXT_TAG_RESOURCECONTENT, FileAdapterResourceContent.class);
		contextByXMLTag.put(CONTEXT_TAG_RESOURCE, an.xacml.adapter.file.context.FileAdapterResource.class);
		contextByXMLTag.put(CONTEXT_TAG_SUBJECT, an.xacml.adapter.file.context.FileAdapterSubject.class);

		contextByXMLTag.put(CONTEXT_TAG_RESPONSE, FileAdapterResponse.class);
		contextByXMLTag.put(CONTEXT_TAG_RESULT, FileAdapterResult.class);
		contextByXMLTag.put(CONTEXT_TAG_STATUS, FileAdapterStatus.class);
		contextByXMLTag.put(CONTEXT_TAG_STATUSCODE, FileAdapterStatusCode.class);
		contextByXMLTag.put(CONTEXT_TAG_STATUSDETAIL, FileAdapterStatusDetail.class);
		contextByXMLTag.put(CONTEXT_TAG_MISSINGATTRIBUTEDETAIL, FileAdapterMissingAttributeDetail.class);
		contextByXMLTag.put(POLICY_TAG_OBLIGATION, FileAdapterObligation.class);
		contextByXMLTag.put(POLICY_TAG_OBLIGATIONS, FileAdapterObligations.class);

		policyByXACMLElement.put(an.xacml.policy.Action.class, an.xacml.adapter.file.policy.FileAdapterAction.class);
		policyByXACMLElement.put(ActionMatch.class, FileAdapterActionMatch.class);
		policyByXACMLElement.put(Actions.class, FileAdapterActions.class);
		policyByXACMLElement.put(Apply.class, FileAdapterApply.class);
		policyByXACMLElement.put(AttributeAssignment.class, FileAdapterAttributeAssignment.class);
		policyByXACMLElement.put(AttributeDesignator.class, FileAdapterAttributeDesignator.class);
		policyByXACMLElement.put(AttributeSelector.class, FileAdapterAttributeSelector.class);
		policyByXACMLElement.put(AttributeValue.class, FileAdapterAttributeValue.class);
		policyByXACMLElement.put(CombinerParameter.class, FileAdapterCombinerParameter.class);
		policyByXACMLElement.put(CombinerParameters.class, FileAdapterCombinerParameters.class);
		policyByXACMLElement.put(Condition.class, FileAdapterCondition.class);
		policyByXACMLElement.put(Defaults.class, FileAdapterDefaults.class);
		policyByXACMLElement.put(an.xacml.policy.Environment.class,
				an.xacml.adapter.file.policy.FileAdapterEnvironment.class);
		policyByXACMLElement.put(EnvironmentMatch.class, FileAdapterEnvironmentMatch.class);
		policyByXACMLElement.put(Environments.class, FileAdapterEnvironments.class);
		policyByXACMLElement.put(Function.class, FileAdapterFunction.class);
		policyByXACMLElement.put(IdReference.class, FileAdapterIdReference.class);
		policyByXACMLElement.put(Obligation.class, FileAdapterObligation.class);
		policyByXACMLElement.put(Obligations.class, FileAdapterObligations.class);
		policyByXACMLElement.put(Policy.class, FileAdapterPolicy.class);
		policyByXACMLElement.put(PolicyCombinerParameters.class, FileAdapterPolicyCombinerParameters.class);
		policyByXACMLElement.put(PolicySet.class, FileAdapterPolicySet.class);
		policyByXACMLElement.put(PolicySetCombinerParameters.class, FileAdapterPolicySetCombinerParameters.class);
		policyByXACMLElement.put(an.xacml.policy.Resource.class,
				an.xacml.adapter.file.policy.FileAdapterResource.class);
		policyByXACMLElement.put(ResourceMatch.class, FileAdapterResourceMatch.class);
		policyByXACMLElement.put(Resources.class, FileAdapterResources.class);
		policyByXACMLElement.put(Rule.class, FileAdapterRule.class);
		policyByXACMLElement.put(RuleCombinerParameters.class, FileAdapterRuleCombinerParameters.class);
		policyByXACMLElement.put(an.xacml.policy.Subject.class, an.xacml.adapter.file.policy.FileAdapterSubject.class);
		policyByXACMLElement.put(SubjectAttributeDesignator.class, FileAdapterSubjectAttributeDesignator.class);
		policyByXACMLElement.put(SubjectMatch.class, FileAdapterSubjectMatch.class);
		policyByXACMLElement.put(Subjects.class, FileAdapterSubjects.class);
		policyByXACMLElement.put(VariableDefinition.class, FileAdapterVariableDefinition.class);
		policyByXACMLElement.put(VariableReference.class, FileAdapterVariableReference.class);

		// Request related mappings.
		contextByXACMLElement.put(an.xacml.context.Action.class, an.xacml.adapter.file.context.FileAdapterAction.class);
		contextByXACMLElement.put(Attribute.class, FileAdapterAttribute.class);
		contextByXACMLElement.put(an.xacml.context.Environment.class,
				an.xacml.adapter.file.context.FileAdapterEnvironment.class);
		contextByXACMLElement.put(Request.class, FileAdapterRequest.class);
		contextByXACMLElement.put(ResourceContent.class, FileAdapterResourceContent.class);
		contextByXACMLElement.put(an.xacml.context.Resource.class,
				an.xacml.adapter.file.context.FileAdapterResource.class);
		contextByXACMLElement.put(an.xacml.context.Subject.class,
				an.xacml.adapter.file.context.FileAdapterSubject.class);

		// Response related mappings.
		contextByXACMLElement.put(MissingAttributeDetail.class, FileAdapterMissingAttributeDetail.class);
		contextByXACMLElement.put(Response.class, FileAdapterResponse.class);
		contextByXACMLElement.put(Result.class, FileAdapterResult.class);
		contextByXACMLElement.put(Status.class, FileAdapterStatus.class);
		contextByXACMLElement.put(StatusCode.class, FileAdapterStatusCode.class);
		contextByXACMLElement.put(StatusDetail.class, FileAdapterStatusDetail.class);
	}

	/**
	 * Get Java class of corresponding Policy XML element.
	 * 
	 * @param elemName
	 * @return
	 */
	public static Class<?> getPolicyDataAdapterClassByXMLType(String elemName) {
		return policyByXMLTag.get(elemName);
	}

	/**
	 * Get Java class of corresponding Context XML element.
	 * 
	 * @param elemName
	 * @return
	 */
	public static Class<?> getContextDataAdapterClassByXMLType(String elemName) {
		// System.out.println("ElementName
		// (getContextDataAdapterClassByXMLType): " + elemName);
		return contextByXMLTag.get(elemName);
	}

	public static Class<?> getPolicyDataAdapterClassByXACMLElementType(Class<?> engineType) {
		return policyByXACMLElement.get(engineType);
	}

	public static Class<?> getContextDataAdapterClassByXACMLElementType(Class<?> engineType) {
		return contextByXACMLElement.get(engineType);
	}
}