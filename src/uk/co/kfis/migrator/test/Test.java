package uk.co.kfis.migrator.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import uk.co.cdl.model.common.Code;
import uk.co.cdl.model.common.MetaType;
import uk.co.cdl.model.common.MetaTypeCodes;
import uk.co.cdl.model.common.Metadata;
import uk.co.cdl.model.context.Context;
import uk.co.cdl.model.enrichments.EnrichmentFactors;
import uk.co.cdl.model.parameters.UDLType;
import uk.co.cdl.model.parameters.UDLTypeValues;
import uk.co.cdl.model.parameters.UDLValue;
import uk.co.cdl.model.party.ClientSearchDetail;
import uk.co.cdl.model.policy.Policy;
import uk.co.cdl.model.risk.MotorRisk;
import uk.co.cdl.service.metadata.v11.GetMetadata;
import uk.co.cdl.service.metadata.v11.GetMetadataResponse;
import uk.co.cdl.service.party.v11.ClientSearch;
import uk.co.cdl.service.party.v11.ClientSearchResponse;
import uk.co.cdl.service.policy.v11.RecallPolicy;
import uk.co.cdl.service.policy.v11.RecallPolicyResponse;
import uk.co.cdl.service.policy.v11.RetrievePolicyTypeSchema;
import uk.co.cdl.service.policy.v11.RetrievePolicyTypeSchemaResponse;
import uk.co.cdl.service.policy.v11.SchemaDoc;
import uk.co.cdl.service.quote.v11.QuoteGrade;
import uk.co.cdl.service.quote.v11.QuoteStore;
import uk.co.cdl.service.quote.v11.QuoteStoreResponse;
import uk.co.cdl.service.strataintegration.v11.StrataIntegrationPortType;
import uk.co.cdl.service.strataintegration.v11.StrataIntegrationServiceV11;
import uk.co.cdl.service.strataintegration.v11.WebServiceException;
import uk.co.cdl.service.webuser.v11.GetWebUserResponse;
import uk.co.kfis.migrator.data.DataConstants;
import uk.co.kfis.migrator.soap.SoapHandler;

public class Test {
	
	StrataIntegrationPortType port;
	
	public Test() {
		StrataIntegrationServiceV11 service = new StrataIntegrationServiceV11();
		port = service.getStrataIntegrationPortV11();
		// add a Handler
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new SoapHandler());
		((BindingProvider) port).getBinding().setHandlerChain(handlerChain);
	}

	public void quoteStore() {
		String partyId = null;
		Context c = new Context();
		c.setBranchCode(DataConstants.Context.BRANCH_CODE);
		c.setOperatorLoginName(DataConstants.Context.STRATA_OPERATOR_UAT);
		//c.setPartyID(partyId);
		//c.setPolicyID(policyId);
		//c.setSchemeCode("BB");//BG PC BB No type // KW S6 AX IS AJ V8 ORA-02291: integrity constraint (KFIS0_TEST.FK_BSEVT_POL) violated - parent key not found
		c.setSchemeGroup(DataConstants.Context.SCHEME_GROUP_PC);
		c.setSourceOfBusiness(DataConstants.Context.SOURCE_OF_ENQUIRY_WEB);
		
		QuoteStore req = new QuoteStore();
		req.setContext(c);
		req.setEnrichments(new EnrichmentFactors());
		req.setFsaSaleType("");
		req.setMaxNumOfQuotes(new BigInteger("1"));
		req.setMaxNumOfQuotesToStore(new BigInteger("10"));
		req.setNameOfInsured("Name");
		//req.setOptionalExtensions(new StoreOptionalExtensions());
		req.setPolicyId(null); // Setting this to a values causes the 'Cannot find a policy Id Error'
		req.setQuoteGrade(QuoteGrade.ALL);
		req.setRisk(new TestRisk().getDefaultRisk());
		//req.setSubagentCodes(new SubagentCodes());
		QuoteStoreResponse qsr;
		try {
			qsr = port.quoteStore(req);
			String retPolicyId = qsr.getPolicyID();
			String retPolicyRef = qsr.getPolicyReference();
			String retProposerId = qsr.getProposerID();
			String retWebReference = qsr.getWebReference();
			System.out.println("POLIC ID: " + retPolicyId);
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void retrieveQuote() {
		String policyId = "6122887";
		Context c = new Context();
		c.setBranchCode(DataConstants.Context.BRANCH_CODE);
		c.setOperatorLoginName(DataConstants.Context.STRATA_OPERATOR_UAT);
		//c.setPartyID(partyId);
		c.setPolicyID(policyId);
		//c.setSchemeCode("BB");//BG PC BB No type // KW S6 AX IS AJ V8 ORA-02291: integrity constraint (KFIS0_TEST.FK_BSEVT_POL) violated - parent key not found
		//c.setSchemeGroup(DataConstants.Context.SCHEME_GROUP_PC);
		//c.setSourceOfBusiness(DataConstants.Context.SOURCE_OF_ENQUIRY_WEB);
		
		QuoteStore req = new QuoteStore();
		req.setContext(c);
		//req.setEnrichments(new EnrichmentFactors());
		//req.setFsaSaleType("");
		req.setMaxNumOfQuotes(new BigInteger("1"));
		//req.setMaxNumOfQuotesToStore(new BigInteger("10"));
		//req.setNameOfInsured("Name");
		//req.setOptionalExtensions(new StoreOptionalExtensions());
		req.setPolicyId(policyId); // Setting this to a values causes the 'Cannot find a policy Id Error'
		req.setQuoteGrade(QuoteGrade.ALL);
		MotorRisk risk = new MotorRisk();
		risk.setId(policyId);
		req.setRisk(risk);
		//req.setSubagentCodes(new SubagentCodes());
		QuoteStoreResponse qsr;
		try {
			qsr = port.quoteStore(req);
			String retPolicyId = qsr.getPolicyID();
			String retPolicyRef = qsr.getPolicyReference();
			String retProposerId = qsr.getProposerID();
			String retWebReference = qsr.getWebReference();
			System.out.println("POLICY ID: " + retPolicyId);
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getPCSchema() {
		RetrievePolicyTypeSchema request = new RetrievePolicyTypeSchema();
		request.setPolicyTypeCode("PC");
		RetrievePolicyTypeSchemaResponse rtps;
		try {
			rtps = port.retrievePolicyTypeSchema(request);
			SchemaDoc doc = rtps.getSchemaDoc();
			Object docAny = doc.getAny();
			System.out.println(docAny.toString());
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clientSearch() {
		String policyId = "6122887";
		ClientSearch req = new ClientSearch();
		req.setPolicyNumber(policyId);
		req.setSurname("Test");
		req.setPostcode("ST4 4TW");
		ClientSearchResponse res;
		try {
			res = port.clientSearch(req);
			List<ClientSearchDetail> csds = res.getResult();
			for (ClientSearchDetail csd : csds) {
				System.out.println("Status: " + csd.getClientStatus());
				System.out.println("Ref: " + csd.getWebReference());
			}
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recall () {
		RecallPolicy req = new RecallPolicy();
		req.setWebReference("77151-40813-87305");
		RecallPolicyResponse res;
		try {
			res = port.recallPolicy(req);
			Policy policy = res.getPolicy();
			String name = policy.getNameOfInsured();
			System.out.println("Name of insured is: " + name);
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printMeta(String codeId) {
		GetMetadata md = new GetMetadata();
		md.setOperation("getAllTypes");
		GetMetadataResponse mtr;
		try {
			mtr = port.getMetadata(md);
			List<Metadata> metaData = mtr.getItem();
			for (Metadata meta : metaData) {
				MetaType m = (MetaType) meta;
				if (m.getId().equals(codeId)) {
					MetaTypeCodes codes = m.getCodes();
					List<Code> metaCodes = codes.getCode();
					for (Code code : metaCodes) {
						System.out.println("ID: " + code.getId() + " CODE: " + code.getCode() + " DESC: "  + code.getDescription());			
					}	
				}
			}
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printUDLMeta() {
		GetMetadata md = new GetMetadata();
		md.setOperation("getAllUDLTypes");
		GetMetadataResponse mtr;
		try {
			mtr = port.getMetadata(md);
			List<Metadata> metaData = mtr.getItem();
			for (Metadata meta : metaData) {
				UDLType m = (UDLType) meta;
				UDLTypeValues udlValues = m.getValues();
				List<UDLValue> values = udlValues.getValue();
				for (UDLValue value : values) {
					System.out.println("TYPE: " + value.getType() + 
							" KEY: " + value.getKey() + 
							" ID: " + value.getId() + 
							" DESC: " + value.getDescription());
					
				}
				//System.out.println("UDL " + m.getId() + " " + m.getDescription() + " " + m.getCode());
			}
		} catch (WebServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static XMLGregorianCalendar getDate(int year, int month, int day) {
		GregorianCalendar c = new GregorianCalendar();
		c.set(year, month, day);
		XMLGregorianCalendar d = null;
		try {
			d = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			d.setTimezone( DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

}
