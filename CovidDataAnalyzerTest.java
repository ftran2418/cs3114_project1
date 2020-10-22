import java.util.Arrays;
import student.TestCase;

/**
 * Test Class for COVID 19 Data Analyzer functions
 *
 * @author Fiona Tran (ftbaohan)
 * @author Aaron Ni (aaronn)
 * @version 2020-09-30
 */
public class CovidDataAnalyzerTest extends TestCase {

    private CovidDataAnalyzer ronaTest;
    private DataEntry entry;

    /**
     * Runs before each test
     */
    public void setUp() {
        ronaTest = new CovidDataAnalyzer();
        entry = new DataEntry(20200803, "VA", 1, 2, 3, 4, 5, 6, "A", 7);
    }


    /**
     * Tests switch case for getting States and Abbreviations
     * 
     * @throws Exception
     *             when state/territory not found
     * 
     */
    public void testGetStateOrAbbreviation() throws Exception {
        assertEquals(ronaTest.getStateOrAbbreviation("alabama"), "AL");
        assertEquals(ronaTest.getStateOrAbbreviation("ALASKA"), "AK");
        assertEquals(ronaTest.getStateOrAbbreviation("AMERICANSAMOA"), "AS");
        assertEquals(ronaTest.getStateOrAbbreviation("ARIZONA"), "AZ");
        assertEquals(ronaTest.getStateOrAbbreviation("ARKANSAS"), "AR");
        assertEquals(ronaTest.getStateOrAbbreviation("CALIFORNIA"), "CA");
        assertEquals(ronaTest.getStateOrAbbreviation("COLORADO"), "CO");
        assertEquals(ronaTest.getStateOrAbbreviation("CONNECTICUT"), "CT");
        assertEquals(ronaTest.getStateOrAbbreviation("DELAWARE"), "DE");
        assertEquals(ronaTest.getStateOrAbbreviation("DISTRICTOFCOLUMBIA"),
            "DC");
        assertEquals(ronaTest.getStateOrAbbreviation(
            "FEDERATED STATES OF MICRONESIA"), "FM");
        assertEquals(ronaTest.getStateOrAbbreviation("FLORIDA"), "FL");
        assertEquals(ronaTest.getStateOrAbbreviation("GEORGIA"), "GA");
        assertEquals(ronaTest.getStateOrAbbreviation("GUAM"), "GU");
        assertEquals(ronaTest.getStateOrAbbreviation("HAWAII"), "HI");
        assertEquals(ronaTest.getStateOrAbbreviation("IDAHO"), "ID");
        assertEquals(ronaTest.getStateOrAbbreviation("ILLINOIS"), "IL");
        assertEquals(ronaTest.getStateOrAbbreviation("INDIANA"), "IN");
        assertEquals(ronaTest.getStateOrAbbreviation("IOWA"), "IA");
        assertEquals(ronaTest.getStateOrAbbreviation("KANSAS"), "KS");
        assertEquals(ronaTest.getStateOrAbbreviation("KENTUCKY"), "KY");
        assertEquals(ronaTest.getStateOrAbbreviation("LOUISIANA"), "LA");
        assertEquals(ronaTest.getStateOrAbbreviation("MAINE"), "ME");
        assertEquals(ronaTest.getStateOrAbbreviation("MARSHALLISLANDS"), "MH");
        assertEquals(ronaTest.getStateOrAbbreviation("MARYLAND"), "MD");
        assertEquals(ronaTest.getStateOrAbbreviation("MASSACHUSETTS"), "MA");
        assertEquals(ronaTest.getStateOrAbbreviation("MICHIGAN"), "MI");
        assertEquals(ronaTest.getStateOrAbbreviation("MINNESOTA"), "MN");
        assertEquals(ronaTest.getStateOrAbbreviation("MISSISSIPPI"), "MS");
        assertEquals(ronaTest.getStateOrAbbreviation("MISSOURI"), "MO");
        assertEquals(ronaTest.getStateOrAbbreviation("MONTANA"), "MT");
        assertEquals(ronaTest.getStateOrAbbreviation("NEBRASKA"), "NE");
        assertEquals(ronaTest.getStateOrAbbreviation("NEVADA"), "NV");
        assertEquals(ronaTest.getStateOrAbbreviation("NEWHAMPSHIRE"), "NH");
        assertEquals(ronaTest.getStateOrAbbreviation("NEWJERSEY"), "NJ");
        assertEquals(ronaTest.getStateOrAbbreviation("NEWMEXICO"), "NM");
        assertEquals(ronaTest.getStateOrAbbreviation("NEWYORK"), "NY");
        assertEquals(ronaTest.getStateOrAbbreviation("NORTHCAROLINA"), "NC");
        assertEquals(ronaTest.getStateOrAbbreviation("NORTHDAKOTA"), "ND");
        assertEquals(ronaTest.getStateOrAbbreviation(
            "northern mariana islands"), "MP");
        assertEquals(ronaTest.getStateOrAbbreviation("OHIO"), "OH");
        assertEquals(ronaTest.getStateOrAbbreviation("OKLAHOMA"), "OK");
        assertEquals(ronaTest.getStateOrAbbreviation("OREGON"), "OR");
        assertEquals(ronaTest.getStateOrAbbreviation("PALAU"), "PW");
        assertEquals(ronaTest.getStateOrAbbreviation("PENNSYLVANIA"), "PA");
        assertEquals(ronaTest.getStateOrAbbreviation("PUERTORICO"), "PR");
        assertEquals(ronaTest.getStateOrAbbreviation("RHODEISLAND"), "RI");
        assertEquals(ronaTest.getStateOrAbbreviation("SOUTHCAROLINA"), "SC");
        assertEquals(ronaTest.getStateOrAbbreviation("SOUTHDAKOTA"), "SD");
        assertEquals(ronaTest.getStateOrAbbreviation("TENNESSEE"), "TN");
        assertEquals(ronaTest.getStateOrAbbreviation("TEXAS"), "TX");
        assertEquals(ronaTest.getStateOrAbbreviation("UTAH"), "UT");
        assertEquals(ronaTest.getStateOrAbbreviation("VERMONT"), "VT");
        assertEquals(ronaTest.getStateOrAbbreviation("VIRGINISLANDS"), "VI");
        assertEquals(ronaTest.getStateOrAbbreviation("VIRGINIA"), "VA");
        assertEquals(ronaTest.getStateOrAbbreviation("WASHINGTON"), "WA");
        assertEquals(ronaTest.getStateOrAbbreviation("WESTVIRGINIA"), "WV");
        assertEquals(ronaTest.getStateOrAbbreviation("WISCONSIN"), "WI");
        assertEquals(ronaTest.getStateOrAbbreviation("WYOMING"), "WY");
        assertEquals(ronaTest.getStateOrAbbreviation("AL"), "ALABAMA");
        assertEquals(ronaTest.getStateOrAbbreviation("AK"), "ALASKA");
        assertEquals(ronaTest.getStateOrAbbreviation("AS"), "AMERICANSAMOA");
        assertEquals(ronaTest.getStateOrAbbreviation("AZ"), "ARIZONA");
        assertEquals(ronaTest.getStateOrAbbreviation("AR"), "ARKANSAS");
        assertEquals(ronaTest.getStateOrAbbreviation("CA"), "CALIFORNIA");
        assertEquals(ronaTest.getStateOrAbbreviation("CO"), "COLORADO");
        assertEquals(ronaTest.getStateOrAbbreviation("CT"), "CONNECTICUT");
        assertEquals(ronaTest.getStateOrAbbreviation("DE"), "DELAWARE");
        assertEquals(ronaTest.getStateOrAbbreviation("DC"),
            "DISTRICTOFCOLUMBIA");
        assertEquals(ronaTest.getStateOrAbbreviation("FM"),
            "FEDERATEDSTATESOFMICRONESIA");
        assertEquals(ronaTest.getStateOrAbbreviation("FL"), "FLORIDA");
        assertEquals(ronaTest.getStateOrAbbreviation("GA"), "GEORGIA");
        assertEquals(ronaTest.getStateOrAbbreviation("GU"), "GUAM");
        assertEquals(ronaTest.getStateOrAbbreviation("HI"), "HAWAII");
        assertEquals(ronaTest.getStateOrAbbreviation("ID"), "IDAHO");
        assertEquals(ronaTest.getStateOrAbbreviation("IL"), "ILLINOIS");
        assertEquals(ronaTest.getStateOrAbbreviation("IN"), "INDIANA");
        assertEquals(ronaTest.getStateOrAbbreviation("IA"), "IOWA");
        assertEquals(ronaTest.getStateOrAbbreviation("KS"), "KANSAS");
        assertEquals(ronaTest.getStateOrAbbreviation("KY"), "KENTUCKY");
        assertEquals(ronaTest.getStateOrAbbreviation("LA"), "LOUISIANA");
        assertEquals(ronaTest.getStateOrAbbreviation("ME"), "MAINE");
        assertEquals(ronaTest.getStateOrAbbreviation("MH"), "MARSHALLISLANDS");
        assertEquals(ronaTest.getStateOrAbbreviation("MD"), "MARYLAND");
        assertEquals(ronaTest.getStateOrAbbreviation("MA"), "MASSACHUSETTS");
        assertEquals(ronaTest.getStateOrAbbreviation("MI"), "MICHIGAN");
        assertEquals(ronaTest.getStateOrAbbreviation("MN"), "MINNESOTA");
        assertEquals(ronaTest.getStateOrAbbreviation("MS"), "MISSISSIPPI");
        assertEquals(ronaTest.getStateOrAbbreviation("MO"), "MISSOURI");
        assertEquals(ronaTest.getStateOrAbbreviation("MT"), "MONTANA");
        assertEquals(ronaTest.getStateOrAbbreviation("NE"), "NEBRASKA");
        assertEquals(ronaTest.getStateOrAbbreviation("NV"), "NEVADA");
        assertEquals(ronaTest.getStateOrAbbreviation("NH"), "NEWHAMPSHIRE");
        assertEquals(ronaTest.getStateOrAbbreviation("NJ"), "NEWJERSEY");
        assertEquals(ronaTest.getStateOrAbbreviation("NM"), "NEWMEXICO");
        assertEquals(ronaTest.getStateOrAbbreviation("NY"), "NEWYORK");
        assertEquals(ronaTest.getStateOrAbbreviation("NC"), "NORTHCAROLINA");
        assertEquals(ronaTest.getStateOrAbbreviation("ND"), "NORTHDAKOTA");
        assertEquals(ronaTest.getStateOrAbbreviation("MP"),
            "NORTHERNMARIANAISLANDS");
        assertEquals(ronaTest.getStateOrAbbreviation("OH"), "OHIO");
        assertEquals(ronaTest.getStateOrAbbreviation("OK"), "OKLAHOMA");
        assertEquals(ronaTest.getStateOrAbbreviation("OR"), "OREGON");
        assertEquals(ronaTest.getStateOrAbbreviation("PW"), "PALAU");
        assertEquals(ronaTest.getStateOrAbbreviation("PA"), "PENNSYLVANIA");
        assertEquals(ronaTest.getStateOrAbbreviation("PR"), "PUERTORICO");
        assertEquals(ronaTest.getStateOrAbbreviation("RI"), "RHODEISLAND");
        assertEquals(ronaTest.getStateOrAbbreviation("SC"), "SOUTHCAROLINA");
        assertEquals(ronaTest.getStateOrAbbreviation("SD"), "SOUTHDAKOTA");
        assertEquals(ronaTest.getStateOrAbbreviation("TN"), "TENNESSEE");
        assertEquals(ronaTest.getStateOrAbbreviation("TX"), "TEXAS");
        assertEquals(ronaTest.getStateOrAbbreviation("UT"), "UTAH");
        assertEquals(ronaTest.getStateOrAbbreviation("VT"), "VERMONT");
        assertEquals(ronaTest.getStateOrAbbreviation("VI"), "VIRGINISLANDS");
        assertEquals(ronaTest.getStateOrAbbreviation("VA"), "VIRGINIA");
        assertEquals(ronaTest.getStateOrAbbreviation("WA"), "WASHINGTON");
        assertEquals(ronaTest.getStateOrAbbreviation("WV"), "WESTVIRGINIA");
        assertEquals(ronaTest.getStateOrAbbreviation("WI"), "WISCONSIN");
        assertEquals(ronaTest.getStateOrAbbreviation("WY"), "WYOMING");
        assertEquals(ronaTest.getStateOrAbbreviation(""), "-1");
    }


    // Testing DataEntry Class
    /**
     * Tests Setters and getters of Data Entry Class
     */
    public void testSettersAndGetters() {
        entry.setDate(20200302);
        assertEquals(entry.getDate(), 20200302);
        entry.setState("NC");
        assertEquals(entry.getState(), "NC");
        entry.setPos(100);
        assertEquals(entry.getPos(), 100);
        entry.setNeg(200);
        assertEquals(entry.getNeg(), 200);
        entry.setHos(300);
        assertEquals(entry.getHos(), 300);
        entry.setOnVentCurr(400);
        assertEquals(entry.getOnVentCurr(), 400);
        entry.setOnVentCumu(500);
        assertEquals(entry.getOnVentCumu(), 500);
        entry.setRecovered(600);
        assertEquals(entry.getRecovered(), 600);
        entry.setDataQualGrade("B");
        assertEquals(entry.getDataQualGrade(), "B");
        entry.setGradeVal(7);
        assertEquals(entry.getGradeVal(), 7);
        entry.setDeath(700);
        assertEquals(entry.getDeath(), 700);
    }


    /**
     * Tests compareRecords, getArrayData, and sameDataEntry functions
     */
    public void testDataEntryHelperFunctions() {
        DataEntry first = new DataEntry(20200803, "VA", 1, 2, 3, 4, 5, 6, "A",
            7);
        DataEntry second = new DataEntry(20200803, "VA", 1, 2, 3, 4, 5, 6, "A",
            7);
        DataEntry third = new DataEntry(20201001, "VA", 7, 6, 5, 4, 3, 2, "A",
            -1);
        DataEntry fourth = new DataEntry(20200803, "MD", 1, 2, 3, 4, 5, 6, "A",
            7);

        assertTrue(first.compareRecords(second));
        assertFalse(first.compareRecords(third));
        assertFalse(first.compareRecords(fourth));

        String[] copy = { "20201001", "VA", "7", "6", "5", "4", "3", "2", "A",
            "-1" };
        assertTrue(Arrays.toString(third.getArrayData()).equals(Arrays.toString(
            copy)));

        assertTrue(first.sameDataEntry(second));
        assertFalse(first.sameDataEntry(third));
    }
}
