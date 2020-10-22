// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

// Fiona Tran (ftbaohan@vt.edu)
// Aaron Ni (aaronn@vt.edu)

import java.util.*;
import java.io.*;

/**
 * Runs the COVID 19 Tracking Manager Program
 *
 * @author Fiona Tran (ftbaohan)
 * @author Aaron Ni (aaronn)
 * @version 2020-09-30
 */
public class Covid19TrackingManager {

    /**
     * Main function that runs the CovidDataAnalyzer with inputs from .txt
     *
     * @param args
     *            passes in input file
     * @throws Exception
     * @throws NumberFormatException
     */
    public static void main(String[] args)
        throws NumberFormatException,
        Exception {
        CovidDataAnalyzer rona = new CovidDataAnalyzer();
        // remember to change back to
        // args[0]"test_input_file.txt""wrong_commands.txt""last_test.txt"
        Scanner sc = new Scanner(new File(args[0]));
        while (sc.hasNext()) // returns a boolean value
        {
            String line = sc.nextLine();
            String[] val = line.trim().split("\\s+");
            val[0] = val[0].toLowerCase();
            switch (val[0]) {
                case "load":
                    // checks if command is valid
                    if (val.length != 2) {
                        System.out.println("Discard invalid command name");
                    }
                    else {
                        rona.load(val[1]);
                    }
                    break;
                case "search":
                    // if no args, searches empty and returns most recent data
                    if (val.length == 1) {
                        rona.search("-1");
                        break;
                    }

                    // if one arg, search date by checking format
                    else if (val.length == 2) {
                        // check if date format format is correct, if not print
                        // error
                        if (val[1].trim().matches("\\d{2}/\\d{2}/\\d{4}")) {
                            rona.search(val[1]);
                            break;
                        }
                        // Invalid command when not correct date format or no
                        // number of records for state
                        else {
                            System.out.println("Discard invalid command name");
                            break;
                        }
                    }
                    // if more than one arg, make sure search state calls
                    // correctly
                    else {
                        // checks if last argument is neg or 0, return error
                        if (Integer.parseInt(val[val.length - 1]) <= 0) {
                            System.out.println("Invalid command. # of records "
                                + "has to be positive");
                            break;
                        }
                        else {
                            // checks last argument to be a number
                            if (val[val.length - 1].matches("[0-9]+")) {
                                int numRecords = Integer.parseInt(val[val.length
                                    - 1]);
                                // checks if numRecords is positive
                                if (numRecords > 0) {
                                    // the state is being searched for is more
                                    // than 1 word
                                    if (val.length > 3) {
                                        String fullState = "";
                                        String original = "";
                                        for (int i = 1; i < val.length
                                            - 1; i++) {
                                            fullState += val[i] + " ";
                                            original += val[i];
                                        }
                                        if (rona.getStateOrAbbreviation(
                                            fullState).equals("-1")) {
                                            System.out.println("state of "
                                                + original
                                                + " does not exist!");
                                            break;
                                        }
                                        else {
                                            rona.search(fullState, numRecords);
                                            break;
                                        }
                                    }
                                    // the state that is being searched for is
                                    // one word
                                    else {
                                        if (rona.getStateOrAbbreviation(val[1])
                                            .equals("-1")) {
                                            System.out.println("State of "
                                                + val[1] + " does not exist!");
                                            break;
                                        }
                                        else {
                                            rona.search(val[1], numRecords);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case "summarydata":
                    // checks if command is valid
                    if (val.length != 1) {
                        System.out.println("Discard invalid command name");
                    }
                    else {
                        rona.summarydata();
                    }
                    break;
                case "dumpdata":
                    // checks if command is valid
                    if (val.length != 2) {
                        System.out.println("Discard invalid command name");
                    }
                    else {
                        rona.dumpdata(val[1]);
                    }
                    break;
                case "":
                    break;
                default:
                    System.out.println("Discard invalid command name");
                    break;
            }
        }
        sc.close();
    }
}
