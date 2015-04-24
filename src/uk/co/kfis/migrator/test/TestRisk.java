package uk.co.kfis.migrator.test;

import java.math.BigInteger;

import uk.co.cdl.model.address.PartyAddress;
import uk.co.cdl.model.common.StoreUserfield;
import uk.co.cdl.model.duq.DUQ;
import uk.co.cdl.model.party.Claims;
import uk.co.cdl.model.party.Conviction;
import uk.co.cdl.model.party.Convictions;
import uk.co.cdl.model.party.Licence;
import uk.co.cdl.model.party.MotorClaim;
import uk.co.cdl.model.party.Name;
import uk.co.cdl.model.party.Occupation;
import uk.co.cdl.model.party.Person;
import uk.co.cdl.model.party.Telephone;
import uk.co.cdl.model.party.Telephones;
import uk.co.cdl.model.risk.DUQs;
import uk.co.cdl.model.risk.MotorRisk;
import uk.co.cdl.model.risk.NCB;
import uk.co.cdl.model.risk.PreviousMotorInsurance;
import uk.co.cdl.model.risk.Userfields;
import uk.co.cdl.model.vehicle.Car;
import uk.co.kfis.migrator.data.DataConstants;

public class TestRisk {

	public MotorRisk getDefaultRisk() {
		MotorRisk m = new MotorRisk();
		m.setPolicyTypeCode(DataConstants.PolicyType.PRIVATE_CAR_CODE);
		m.setProposer(getProposer());
		m.setFrequency(DataConstants.PolicyFrequency.ANNUAL_CODE); // For some reason only Annual works kere
		m.setEndDate(Test.getDate(2016,1,1));
		m.setAffinity("");//"DIREWEB"
		m.setVolXS(new BigInteger("3000"));
		m.setStartDate(Test.getDate(2015, 4, 10));
		m.setQuoteDate(Test.getDate(2015, 4, 1));
		m.setQuoteType("NewQuotTyp");
		m.setPolicyInceptionDate(Test.getDate(2015, 3, 2));
		//m.setPreferredDeliveryMethod("Email");
		//m.setDuqs(getDuqs());
		//m.setUserfields(getPolicyUserFields());
		m.setPreviousInsurance(getPreviousInsurance());
		m.setCover("C");
		m.setTotalMileage(new BigInteger("9999"));
		m.setRestrict(DataConstants.DrivingRestriction.ANY_DRIVER_16_CODE);
		m.setClassOfUse(DataConstants.ClassOfUse.SDP);
		m.setVehicle(getVehicle());
		return m;
	}

	private Person getProposer() {
		Person p = new Person();
		p.setAddress(getProposerAddress());
		p.setName(getProposerName());
		p.setVatRegistered(false);
		p.setClientStatus("Active");
		p.setTelephoneNumbers(getTelephoneNumbers());
		p.setEmail("szymon.czaja@kfis.co.uk");
		p.setFsaClass(DataConstants.UDLFsaClass.RETAIL_KEY);
		p.setRelationshipToProposer(DataConstants.RelationshipToProposer.PROPOSER);
		//p.setUserfields(getProposerUserFields());
		p.setFullTimeOccupation(getProposerOccupation());
		p.setPartTimeOccupation(getProposerPartTimeOccupation());
		p.setLicence(getProposerLicence());
		p.setDateOfBirth(Test.getDate(1967, 1, 1));
		p.setGender("M");
		p.setResidentSince(Test.getDate(2012, 5, 7));
		p.setInsuranceRefused(false);
		p.setMaritalStatus(DataConstants.MaritalStatus.NOT_KNOWN_CODE);
		p.setNonRTAConviction(false);
		p.setSmoker(false);
		p.setAccessToOtherVehicles("BLANK");
		p.setVehicleUse("MainUser");
		p.setNumberOfOtherVehicles(new BigInteger("0"));
		p.setDriverType(DataConstants.DriverType.PROPOSER_CODE); // ?
		p.setIamCert(false);
		p.setStarRider("No");
		//p.setClaims(getClaims());
		return p;
	}

	private PartyAddress getProposerAddress() {
		PartyAddress a = new PartyAddress();
		a.setHouseNameOrNumber("Prospect House");
		a.setAddressLine1("Gordon Banks Drive");
		a.setAddressLine2("Stoke-on-Trent");
		a.setAddressLine3("Staffordshire");
		a.setPostCode("ST4 4TW");
		a.setAbodeType(DataConstants.PostAddressCategory.HOUSE_CODE); // These fields must be included to get a quote
		a.setUsage("Main"); // These fields must be included to get a quote
		a.setCountryCode("GB"); // These fields must be included to get a quote
		return a;
	}

	private NCB getNCB() {
		NCB n = new NCB();
		n.setYears(9);
		n.setProtectedBonus(true);
		n.setAppliedToOtherVehicle(false);
		return n;
	}

	private Telephones getTelephoneNumbers() {
		Telephone t = new Telephone();
		t.setAreaCode("0800");
		t.setPhoneNumber("0800800");
		t.setUsage("Home");
		Telephones tels = new Telephones();
		tels.getTelephone().add(t);
		return tels; // area, phone, usage
	}

	private Name getProposerName() {
		Name n = new Name();
		n.setForenames("Test");
		n.setTitle("Mr");
		n.setSurname("Test");
		n.setSalutation("Mr Test");
		return n;
	}

	private Occupation getProposerOccupation() {
		Occupation o = new Occupation();
		o.setType("Employee");
		o.setOccCode("50D");
		o.setEmpCode("077");
		return o;
	}

	private Occupation getProposerPartTimeOccupation() {
		Occupation o = new Occupation();
		o.setType("None");
		return o;
	}

	private uk.co.cdl.model.party.Userfields getProposerUserFields() {
		StoreUserfield s1 = new StoreUserfield();
		s1.setCode("MRKEMAIL");
		s1.setResponse("N");
		s1.setShortResponse("N");

		StoreUserfield s2 = new StoreUserfield();
		s2.setCode("MRKTELE");
		s2.setResponse("N");
		s2.setShortResponse("N");

		StoreUserfield s3 = new StoreUserfield();
		s3.setCode("MRKSMS");
		s3.setResponse("N");
		s3.setShortResponse("N");

		StoreUserfield s4 = new StoreUserfield();
		s4.setCode("MRKPOST");
		s4.setResponse("N");
		s4.setShortResponse("N");

		StoreUserfield s5 = new StoreUserfield();
		s5.setCode("MRKSHARE");
		s5.setResponse("N");
		s5.setShortResponse("N");

		StoreUserfield s6 = new StoreUserfield();
		s6.setCode("TENURE");
		s6.setResponse("14");
		s6.setShortResponse("14");
		
		uk.co.cdl.model.party.Userfields fields = new uk.co.cdl.model.party.Userfields();
		fields.getUserfield().add(s1);
		fields.getUserfield().add(s2);
		fields.getUserfield().add(s3);
		fields.getUserfield().add(s4);
		fields.getUserfield().add(s5);
		fields.getUserfield().add(s6);
		return fields;
	}

	private Licence getProposerLicence() {
		Licence l = new Licence();
		l.setType("Full Licence");
		l.setDate(Test.getDate(1989, 10, 10));
		//l.setConvictions(getConvictions());
		l.setCategory("CatB");
		return l;
	}

	private PreviousMotorInsurance getPreviousInsurance() {
		PreviousMotorInsurance i = new PreviousMotorInsurance();
		i.setExpiryDate(Test.getDate(2012, 1, 5));
		i.setType("PC");
		i.setNcb(getNCB());
		i.setPreviousPolicyBonusProtected(true);
		return i;
	}

	private Car getVehicle() {
		Car c = new Car();
		c.setAbiCode("17568401");
		c.setAbiProductionYears("2010");
		c.setAlarm("FACT");
		c.setPurchased(Test.getDate(2012, 7, 8));
		c.setCc(new BigInteger("1596"));
		c.setFuel("FuelPetrol");
		c.setImmob("FACT");
		c.setManufacturer("FORD");
		//c.setModelDescription("FOCUS CC-1");
		c.setModelDescription("");
		c.setOwner("Proposer");
		c.setValue(new BigInteger("5000"));
		c.setPaid(new BigInteger("5000"));
		c.setKeeper(true);
		c.setTracker("NONE");
		c.setTransmission("TransMan");
		c.setWhereKept("Roadside");
		c.setKeptPostcode("ST4 4TW");
		c.setYearOfManufacture(Test.getDate(2014, 1, 1));
		c.setAbs(false);
		c.setBodyStyle("BLANK");
		c.setLhd(false);
		c.setSeats(new BigInteger("4"));
		return c;
	}

	private DUQs getDuqs() {
		DUQ d1 = new DUQ();
		d1.setQuestionNumber(new BigInteger("15"));
		d1.setResponse("No");
		d1.setShortResponse("N");
		DUQ d2 = new DUQ();
		d2.setQuestionNumber(new BigInteger("523"));
		d2.setResponse("Yes");
		d2.setShortResponse("Y");

		DUQs d = new DUQs();
		d.getDuq().add(d1);
		d.getDuq().add(d2);
		return d;
	}

	private Userfields getPolicyUserFields() {
		StoreUserfield s1 = new StoreUserfield();
		s1.setCode("SOE");
		s1.setResponse("Internet Policy");
		s1.setShortResponse("WEB");

		StoreUserfield s2 = new StoreUserfield();
		s2.setCode("Premier");
		s2.setResponse("N");
		s2.setShortResponse("N");

		StoreUserfield s3 = new StoreUserfield();
		s3.setCode("OPXPACK");
		s3.setResponse("1");
		s3.setShortResponse("1");

		Userfields u = new Userfields();
		u.getUserfield().add(s1);
		u.getUserfield().add(s2);
		u.getUserfield().add(s3);
		return u;
	}

	private Convictions getConvictions() {
		Conviction c1 = new Conviction();
		c1.setCode("2");
		c1.setDate(Test.getDate(1970, 1, 1));
		c1.setOffenceDate(Test.getDate(1970, 1, 1));
		c1.setFine(new BigInteger("10000"));
		c1.setPoints(new BigInteger("3"));
		c1.setDisqualification(new BigInteger("6"));

		Conviction c2 = new Conviction();
		c2.setCode("1");
		c2.setDate(Test.getDate(1970, 1, 1));
		c2.setOffenceDate(Test.getDate(1970, 1, 1));
		c2.setFine(new BigInteger("20000"));
		c2.setPoints(new BigInteger("3"));
		c2.setDisqualification(new BigInteger("12"));

		Convictions convistions = new Convictions();
		convistions.getConviction().add(c1);
		convistions.getConviction().add(c2);
		return convistions;
	}

	private Claims getClaims() {
		MotorClaim c = new MotorClaim();
		c.setClaimDate(Test.getDate(2012, 8, 7));
		c.setCode("test");
		c.setComments("test");
		c.setNcbPrejudiced(true);
		c.setPersonalInjury(true);
		c.setUnderPolicyholdersCover(false);
		Claims claims = new Claims();
		claims.getClaim().add(c);
		return claims;
	}
}
