import java.util.Arrays;
import student.TestCase;

/**
 * Test Class for COVID 19 Tracking Manager functions
 * 
 * @author Fiona Tran (ftbaohan)
 * @author Aaron Ni (aaronn)
 * @version 2020-09-30
 *
 */
public class Covid19TrackingManagerTest extends TestCase {

    private Covid19TrackingManager ronaTracker;

    /**
     * Runs before each test
     */
    public void setUp() {
        ronaTracker = new Covid19TrackingManager();
    }

    // test void methods by testing the methods that call the
    // void methods
    // everything is void except for sorts
    // print out message for every command or scanner closed issue


    /**
     * Tests the implementation of the main function and methods
     * 
     * @throws Exception
     * @throws NumberFormatException
     */
    public void testMain() throws NumberFormatException, Exception {
        ronaTracker.main(new String[] { "test_input_file.txt" });
        String[] output = { "Low quality data rejected for MD \n",
            "Finished loading test1.csv file\n", "9 records have been loaded\n",
            "1 records are printed out for the state of maryland\n",
            "date positive negative hospitalized onVentilatorCurrently "
                + "onVentilatorCumulative recovered dataQualityGrade "
                + "death\n",
            "08/15/2020 99,693 1,027,959 13,556 0 0 5,995 A 3,636 \n",
            "There are 3 records on 08/18/2020 \n",
            "state positive negative hospitalized onVentilatorCurrently "
                + "onVentilatorCumulative recovered dataQualityGrade "
                + "death\n",
            "FL 579,932   3,699,108  35,112  0 0  0  B   9,893   \n",
            "IN 81,847 846,767    10,037 94 0  61,518  A+    3,165\n",
            "WI 71,424    1,075,397  5,380   0 0 57,382 A+ 1,059   \n",
            "Discard invalid command name \n",
            "There are 3 records on 08/18/2020 \n",
            "state positive negative hospitalized onVentilatorCurrently "
                + "onVentilatorCumulative recovered dataQualityGrade "
                + "death\n",
            "FL 579,932   3,699,108  35,112 0  0    0  B   9,893   \n",
            "IN 81,847    846,767    10,037 94 0 61,518 A+    3,165   \n",
            "WI 71,424  1,075,397  5,380   0   0 57,382 A+  1,059   \n",
            "Invalid command. # of records has to be positive \n",
            "Discard invalid command name \n",
            "Discard invalid command name \n",
            "State of thing does not exist! \n", "Data Summary for 9 states:\n",
            "State   Total Case   Total Death   Total Hospitalized\n",
            "AS      0            0             0      \n",
            "AZ      193,537      4,506         20,755  \n",
            "DC      13,220       597           0    \n",
            "FL      579,932      9,893         35,112  \n",
            "IN      81,847       3,165         10,037  \n",
            "MD      99,693       3,636         13,556 \n",
            "MA      123,200      8,829         12,173 \n",
            "WI 71,424 1,059 5,380\n", "WY 3,331 33 195 \n",
            "Total Cases: 1,166,184\n", "Total Death: 31,718\n",
            "Total Hospitalized: 97,208\n", "Discard invalid command name \n",
            "Discard invalid command name \n",
            "Low quality data rejected for WI \n",
            "Low quality data rejected for FL \n",
            "Finished loading head_100_random_30.csv file \n",
            "28 records have been loaded \n",
            "37 records have been saved in the test5.csv file\n" };
        assertFuzzyEquals(Arrays.toString(output), systemOut().getHistory());
        System.out.println();
    }


    /**
     * Tests update, reject, discard edge cases
     * 
     * @throws Exception
     * @throws NumberFormatException
     * 
     */
    public void testWrongCommands() throws NumberFormatException, Exception {
        ronaTracker.main(new String[] { "wrong_commands.txt" });
        String[] output = {
            "Data has been updated for the missing data in AS \n",
            "Low quality data rejected for MD\n",
            "Finished loading test2.csv file \n",
            "5 records have been loaded \n", "Discard invalid record \n",
            "Low quality data rejected for MD \n",
            "Data has been updated for MD 20200815 \n",
            "Discard invalid record \n", "Low quality data rejected for DC \n",
            "Data has been updated for AS 20200815 \n",
            "Finished loading test3.csv file \n",
            "5 records have been loaded \n" };
        assertFuzzyEquals(Arrays.toString(output), systemOut().getHistory());
    }
}
