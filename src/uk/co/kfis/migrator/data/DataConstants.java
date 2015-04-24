package uk.co.kfis.migrator.data;

public class DataConstants {
	
	public static class Context {
		public static final String SCHEME_GROUP_PC = "KFITPC";
		public static final String SCHEME_GROUP_HC = "KFITHC";
		public static final String BRANCH_CODE = "KWIK";
		public static final String SOURCE_OF_ENQUIRY_WEB = "Website";
		public static final String STRATA_OPERATOR_UAT = "kft_webint";
		public static final String STRATA_OPERATOR_LIVE = "kft_webint";
	}

	public static class MaritalStatus {
		public static final String ID = "12";
		public static final String DIVORCED_CODE = "D";
		public static final String MARRIED_CODE = "M";
		public static final String NOT_KNOWN_CODE = "NotKnown";
		public static final String SINGLE_CODE = "S";
		public static final String CIVIL_PARTNERED_CODE = "V";
		public static final String WIDOWED_CODE = "W";
	}
	
	public static class PolicyType {
		public static final String ID = "2007";
		public static final String PRIVATE_CAR_CODE = "PC";
		public static final String HOUSEHOLD_CODE = "HH";
	}
	
	public static class PolicyFrequency {
		public static final String ID = "39";
		public static final String ANNUAL_CODE = "PlcyAnnual";
		public static final String ANNUAL_ID = "299";
		public static final String SINGLE_CODE = "Single";
		public static final String SINGLE_ID = "2070";
		public static final String VARIABLE_CODE = "Variable";
		public static final String VARIABLE_ID = "2072";
		public static final String SHORT_TERM_CODE = "ShortTerm";
		public static final String SHORT_TERM_ID = "2069";
		public static final String PERMANENT_CODE = "Permanent";
		public static final String PERMANENT_ID = "2068";
	}
	
	public static class ClassOfUse {
		public static final String ID = "38";
		public static final String CLASS_1 = "C1";
		public static final String CLASS_2 = "C2";
		public static final String CLASS_3 = "C3";
		public static final String DRIVING_TUITION = "DT";
		public static final String JOINT_CLASS_1 = "JC1";
		public static final String SDP = "SDP";
		public static final String SDP_EX_COMMUTING = "SDPexC";
		public static final String TRANSFERRED_CLASS_1 = "Transf";
	}
	
	public static class RelationshipToProposer {
		public static final String ID = "76";
		public static final String BLANK = "BLANK";
		public static final String COMMON_LAW_PARTNER = "COMSPOUSE";
		public static final String CIVIL_PARTNER = "CivPrtnr";
		public static final String OFFSPRING = "OFFSPRNG";
		public static final String PARENT = "PARENT";
		public static final String PROPOSER = "Proposer";
		public static final String SIBLING = "SIBLING";
		public static final String SPOUSE = "SPOUSEREL";
	}
	
	public static class PaymentPlanPaymentFrequency {
		public static final String ID = "2312";
		public static final String ANNUALLY = "A";
		public static final String DAILY = "D";
		public static final String HALF_YEARLY = "H";
		public static final String MONTHLY = "M";
		public static final String QUARTERLY = "R";
		public static final String WEEKLY = "W";
		
	}
	
	public static class RepeatFrequency {
		public static final String ID = "1437";
		public static final String DAILY = "D";
		public static final String MONTHLY = "M";
		public static final String ONCE_ONLY = "O";
		public static final String WEEKLY = "W";
		
	}
	
	public static class SchedullingFrequency {
		public static final String ID = "3009";
		public static final String DAILY = "Daily";
		public static final String MONTHLY = "Monthly";
		public static final String WEEKLY = "Weekly";
		
	}
	public static class InstallmentPlanFrequency {
		public static final String ID = "1422";
		public static final String MONTHLY = "OneMonth";
		public static final String WEEKLY = "OneWeek";
	}
	public static class PostAddressCategory {
		public static final String ID = "24";
		public static final String FLAT_CODE = "Flat";
		public static final String FLAT_ID = "227";
		public static final String HOUSE_CODE = "House";
		public static final String HOUSE_ID = "226";
	}
	
	public static class DriverType {
		public static final String ID = "";
		public static final String PROPOSER_CODE = "Prpsr";
		public static final String PROPOSER_ID = "359";
		public static final String NAMED_CODE = "NmdDrvr";
		public static final String NAMED_ID = "360";
		public static final String EXCLUDED_CODE = "XclDrvr";
		public static final String EXCLUDED_ID = "361";
	}
	
	public static class UDLFsaClass {
		public static final String ID = "-200";
		public static final String RETAIL_KEY = "RETAIL";
		public static final String RETAIL_ID = "-220";
		public static final String COMMERCIAL_KEY = "COMMERCIAL";
		public static final String COMMERCIAL_ID = "-221";
	}
	
	public static class DrivingRestriction {
		public static final String ID = "54";
		public static final String ANY_DRIVER_CODE = "ANYDRVR";
		public static final String ANY_DRIVER_ID = "375";
		
		public static final String ANY_DRIVER_16_CODE = "ANYDRVX16";
		public static final String ANY_DRIVER_16_ID = "23166";
		// ANYDRVX16 goes through to ANYDRVX99
		public static final String ANY_DRIVER_99_CODE = "ANYDRVX99";
		public static final String ANY_DRIVER_99_ID = "23241";
		
		public static final String INSURED_ONLY_CODE = "INSONLY";
		public static final String INSURED_ONLY_ID = "372";
		public static final String NAMED_DRIVERS_CODE = "NMDDRVRS";
		public static final String NAMED_DRIVERS_ID = "374";
	}
}
