import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains all the functions to perform on COVID Data
 * 
 * @author Fiona Tran (ftbaohan)
 * @author Aaron Ni (aaronn)
 * @version 2020-09-30
 */
public class CovidDataAnalyzer {

    // global list of all loaded and updated records
    private ArrayList<DataEntry> globalList = new ArrayList<DataEntry>();
    // length of globalList and length of new data loaded in
    private int recordCount = 0;

    /**
     * Loads data from csv file. If this is not the first csv file loaded into
     * the program, check for redundant data and update missing records.
     *
     * @param fileName
     *            file to pass in commands
     * @throws FileNotFoundException
     */
    public void load(String fileName) throws FileNotFoundException {
        int newLoadCount = 0;
        File temp = new File(fileName);
        boolean fileExists = temp.exists();
        // if file name is valid read in file
        if (fileExists) {
            Scanner sc = new Scanner(new File(fileName));
            // skip first line of column names
            sc.nextLine();
            String line = "";
            // for every line of csv file, split entries by comma and
            while (sc.hasNext()) {
                line = sc.nextLine();
                String[] value = line.split(",", -1);
                boolean containsData = false;

                // checks for empty data records
                for (int i = 0; i < value.length; i++) {
                    // if there is data detected
                    // trims data entry
                    value[i] = value[i].trim();
                    if (!value[i].equals("")) {
                        containsData = true;
                    }
                }

                if (containsData) {
                    // replaces all "" to -1 if storing as an int to show that
                    // it is blank
                    for (int i = 0; i < value.length; i++) {
                        if (i != 1 && i != 8 && value[i].equals("")) {
                            value[i] = "-1";
                        }
                    }

                    // checks if array is length 10
                    if (value.length == 10) {
                        boolean hasAll = true;
                        boolean isState = true;
                        // if missing state or date or quality grade or
                        // length of date is 0
                        if (value[0].equals("") || value[1].equals("")
                            || value[8].equals("") || (value[0]
                                .length() != 8)) { // cant combine?
                            hasAll = false;
                        }
                        // if state name does not exist
                        String state = getStateOrAbbreviation(value[1]);
                        if (state.equals("-1")) {
                            isState = false;
                        }

                        if (hasAll && isState) {
                            DataEntry newData = new DataEntry(Integer.parseInt(
                                value[0]), value[1], (int)Double.parseDouble(
                                    value[2]), (int)Double.parseDouble(
                                        value[3]), (int)Double.parseDouble(
                                            value[4]), (int)Double.parseDouble(
                                                value[5]), (int)Double
                                                    .parseDouble(value[6]),
                                (int)Double.parseDouble(value[7]), value[8],
                                (int)Double.parseDouble(value[9]));

                            if (recordCount == 0) {
                                globalList.add(newData);
                                recordCount++;
                                newLoadCount++;
                            }
                            else {
                                // boolean to determine if newData is added to
                                // globalList
                                boolean addToList = true;
                                for (int i = 0; i < globalList.size(); i++) {
                                    int replaced = 0;
                                    // if records match, compareRecords
                                    if (newData.compareRecords(globalList.get(
                                        i))) {
                                        addToList = false;

                                        // check grade val, if data quality
                                        // is
                                        // higher, replace and return output
                                        if (newData.getGradeVal() > globalList
                                            .get(i).getGradeVal()) {
                                            globalList.set(i, newData);
                                            System.out.println(
                                                "Data has been updated for "
                                                    + globalList.get(i)
                                                        .getState() + " "
                                                    + globalList.get(i)
                                                        .getDate());
                                            recordCount++;
                                            newLoadCount++;
                                            break;
                                        }
                                        else {
                                            // if grade vals are equal, check if
                                            // anything to
                                            // update and only print out data
                                            // has been updated

                                            // for all cols of data entry, check
                                            // if
                                            // anything
                                            // needs to be updated, if something
                                            // is
                                            // updated,

                                            // if gradevals are equal,
                                            // if
                                            // (newData.sameDataEntry(globalList
                                            // .get(i))) {
                                            // replaced = false;
                                            // continue;
                                            // }
                                            // else {

                                            // replace the number of
                                            // positive if
                                            // missing
                                            if (newData.getPos() != -1
                                                && globalList.get(i)
                                                    .getPos() == -1) {
                                                replaced += -1;
                                                globalList.get(i).setPos(newData
                                                    .getPos());
                                            }

                                            // replace the number of
                                            // negative if
                                            // missing
                                            if (newData.getNeg() != -1
                                                && globalList.get(i)
                                                    .getNeg() == -1) {
                                                replaced += -1;
                                                globalList.get(i).setNeg(newData
                                                    .getNeg());
                                            }

                                            // replace the number of
                                            // hospitalized if
                                            // missing
                                            if (newData.getHos() != -1
                                                && globalList.get(i)
                                                    .getHos() == -1) {
                                                replaced += -1;
                                                globalList.get(i).setHos(newData
                                                    .getHos());
                                            }

                                            // replace the number of
                                            // onVentCurrently
                                            // if
                                            // missing
                                            // System.out.println(newData
                                            // .getOnVentCurr());
                                            // System.out.println(globalList.
                                            // get(i)
                                            // .getOnVentCurr());
                                            if (newData.getOnVentCurr() != -1
                                                && globalList.get(i)
                                                    .getOnVentCurr() == -1) {
                                                replaced += -1;
                                                globalList.get(i).setOnVentCurr(
                                                    newData.getOnVentCurr());
                                            }

                                            // replace the number of
                                            // onVentCumulative if
                                            // missing
                                            if (newData.getOnVentCumu() != -1
                                                && globalList.get(i)
                                                    .getOnVentCumu() == -1) {
                                                replaced += -1;
                                                globalList.get(i).setOnVentCumu(
                                                    newData.getOnVentCumu());
                                            }

                                            // replace the number of
                                            // recovered
                                            // if
                                            // missing
                                            if (newData.getRecovered() != -1
                                                && globalList.get(i)
                                                    .getRecovered() == -1) {
                                                replaced += -1;
                                                globalList.get(i).setRecovered(
                                                    newData.getRecovered());
                                            }

                                            // replace the number of deaths
                                            // if
                                            // missing
                                            if (newData.getDeath() != -1
                                                && globalList.get(i)
                                                    .getDeath() == -1) {
                                                replaced += -1;
                                                globalList.get(i).setDeath(
                                                    newData.getDeath());
                                            }

                                            if (replaced < 0) {
                                                System.out.println(
                                                    "Data has been updated "
                                                        + "for the missing "
                                                        + "data in "
                                                        + globalList.get(i)
                                                            .getState());
                                                recordCount++;
                                                newLoadCount++;
                                            }
                                            else {
                                                System.out.println(
                                                    "Low quality data "
                                                        + "rejected for "
                                                        + globalList.get(i)
                                                            .getState());
                                            }

                                        } // else gradeval is <=

                                    } // end of compare record equal
                                }
                                if (addToList) {
                                    globalList.add(newData);
                                    recordCount++;
                                    newLoadCount++;
                                }
                            } // end of else (record count >0
                        } // end of hasAll && isState
                        else {
                            // Missing date/state/grade or date length is not 8
                            if (!hasAll) {
                                System.out.println("Discard invalid record");
                            }
                            else if (!isState) {
                                System.out.println("State of " + value[1]
                                    + " does not exist!");
                            }
                        }
                    } // end of if (value.length == 10) {
                      // if record does not have 10 data values
                    else {
                        System.out.println("Discard invalid record");
                    }
                }
            } // this is the end of the while loop

            System.out.println("Finished loading " + fileName + " file");
            System.out.println(newLoadCount + " records have been loaded");
            sc.close();
        }
        else

        {
            System.out.println("File " + fileName + " not found");
        }
    }


    /**
     * Searches for state records, non case-sensitive and abbreviations
     * accepted,
     * and prints out number of records found and all record details
     *
     * @param state
     *            state to pass in
     * @param numRecords
     *            maximun records to return for matched state
     */
    public void search(String state, int numRecords) {
        ArrayList<DataEntry> indexList = new ArrayList<DataEntry>();
        // returns error if state or territory isn't found
        if (getStateOrAbbreviation(state).equals("-1")) {
            System.out.println("There are no records from " + state);
        }

        String ogState = state;
        String currState = "";
        state = state.toUpperCase();
        if (state.length() > 2) {
            currState = getStateOrAbbreviation(state);
        }
        else {
            currState = state;
        }
        indexList = sortState(globalList);
        ArrayList<DataEntry> printState = new ArrayList<DataEntry>();

        // loop through sorted list and add to printState when there is a match
        // while the count of records is under capacity of requested numRecords
        for (int i = 0; i < indexList.size(); i++) {
            if (currState.equals(indexList.get(i).getState())) {
                printState.add(indexList.get(i));

            }
        }
        // print results
        if (printState.size() == 0) {
            System.out.println("There are no records from " + ogState);
        }
        else {

            if (printState.size() > numRecords) {
                System.out.println(numRecords
                    + " records are printed out for the state of " + ogState);
            }
            else {
                System.out.println(printState.size()
                    + " records are printed out for the state of " + ogState);
            }
            printMatchStateRecords(printState, numRecords);
        }
    }


    /**
     * Searches for all records associated with given date and print number of
     * records and all records associated. Prints error if no date is found or
     * entered incorrectly
     *
     * @param date
     *            date to search records for
     * @throws Exception
     */
    public void search(String date) throws Exception {
        ArrayList<DataEntry> indexList = new ArrayList<DataEntry>();

        // if there is no specific date given, print out data records for most
        // recent date
        if (date.equals("-1")) {
            // make sure globalList is sorted by most recent date
            indexList = sortDate(globalList);
            // print all records that match the most recent date
            boolean sameDate = true;
            ArrayList<DataEntry> printDate = new ArrayList<DataEntry>();
            printDate.add(indexList.get(0));
            int index = 1;
            while (sameDate) {
                if (indexList.get(0).getDate() == indexList.get(index)
                    .getDate()) {
                    printDate.add(indexList.get(index));
                    index++;
                }
                else {
                    sameDate = false;
                }
            }
            // convert recent date to mm/dd/yyyy
            String recentData = Integer.toString(indexList.get(0).getDate());
            String year = recentData.substring(0, 4);
            String month = recentData.substring(4, 6);
            String day = recentData.substring(6, 8);
            String newDate = month + "/" + day + "/" + year;

            System.out.println("There are " + index + " records on " + newDate);
            printMatchDateRecords(printDate);
        }
        // then check records for specific date
        else {
            // convert date from mm/dd/yyyy to yyyymmdd
            String year = date.substring(6, 10);
            String month = date.substring(0, 2);
            String day = date.substring(3, 5);
            String newDate = year + month + day;

            int recordCounts = 0;
            // iterate through list of records and check each time the date
            // matches a record
            int newDateInt = Integer.parseInt(newDate);
            for (int i = 0; i < globalList.size(); i++) {
                if (newDateInt == globalList.get(i).getDate()) {
                    recordCounts++;
                    indexList.add(globalList.get(i));
                }
            }
            // if date does not exist, print error
            if (recordCounts == 0) {
                System.out.println("There are no records on " + date);
            }

            else {
                System.out.println("There are " + recordCounts + " records on "
                    + date);
                // print function to print each record
                printMatchDateRecords(indexList);
            }
        }
    }


    /**
     * Calculates the total cases, deaths, and hospitalized for each state
     *
     * @throws Exception
     */
    public void summarydata() throws Exception {
        int numStates = 0;
        int totalCases = 0;
        int totalDeaths = 0;
        int totalHos = 0;

        // sort globalList by state and then get first state
        ArrayList<DataEntry> sortedList = sortState(globalList);
        String setState = sortedList.get(0).getState();

        // convert -1 entries to 0
        for (int i = 0; i < globalList.size(); i++) {
            if (sortedList.get(i).getPos() < 0) {
                sortedList.get(i).setPos(0);
            }
            if (sortedList.get(i).getDeath() < 0) {
                sortedList.get(i).setDeath(0);
            }
            if (sortedList.get(i).getHos() < 0) {
                sortedList.get(i).setHos(0);
            }
        }

        // array lists to hold totals
        ArrayList<String> stateList = new ArrayList<String>();
        ArrayList<Integer> casesList = new ArrayList<Integer>();
        ArrayList<Integer> deathList = new ArrayList<Integer>();
        ArrayList<Integer> hosList = new ArrayList<Integer>();

        // add first data records to lists
        stateList.add(setState);
        casesList.add(sortedList.get(0).getPos());
        deathList.add(sortedList.get(0).getDeath());
        hosList.add(sortedList.get(0).getHos());
        totalCases += sortedList.get(0).getPos();
        totalDeaths += sortedList.get(0).getDeath();
        totalHos += sortedList.get(0).getHos();

        // for entire global list, while current entry state is equal to set
        // state, add to total counts
        for (int i = 1; i < globalList.size(); i++) {
            // while states match add records to total
            if (setState.equals(sortedList.get(i).getState())) {
                casesList.set(numStates, casesList.get(numStates) + sortedList
                    .get(i).getPos());
                deathList.set(numStates, deathList.get(numStates) + sortedList
                    .get(i).getDeath());
                hosList.set(numStates, hosList.get(numStates) + sortedList.get(
                    i).getHos());
                // add to totals
                totalCases += sortedList.get(i).getPos();
                totalDeaths += sortedList.get(i).getDeath();
                totalHos += sortedList.get(i).getHos();
            }
            //
            else {
                // states no longer match, update to next set of states
                numStates++;
                setState = sortedList.get(i).getState();
                stateList.add(setState);
                casesList.add(sortedList.get(i).getPos());
                deathList.add(sortedList.get(i).getDeath());
                hosList.add(sortedList.get(i).getHos());

                // add to totals
                totalCases += sortedList.get(i).getPos();
                totalDeaths += sortedList.get(i).getDeath();
                totalHos += sortedList.get(i).getHos();

            }
        }
        // account for first state
        numStates += 1;

        // print results - titles
        System.out.println("Data Summary for " + numStates + " states:");
        System.out.printf("%-7s %-12s %-13s %-25s \n", "State", "Total Case",
            "Total Death", "Total Hospitalized");

        // print data
        for (int i = 0; i < numStates; i++) {
            String casesNumber = String.format("%,d", casesList.get(i));
            String deathNumber = String.format("%,d", deathList.get(i));
            String hosNumber = String.format("%,d", hosList.get(i));

            System.out.printf("%-7s %-12s %-13s %-25s \n", stateList.get(i),
                casesNumber, deathNumber, hosNumber);
        }

        // print totals
        String caseNumberTot = String.format("%,d", totalCases);
        String deathNumberTot = String.format("%,d", totalDeaths);
        String hosNumberTot = String.format("%,d", totalHos);
        System.out.println("Total Cases: " + caseNumberTot);
        System.out.println("Total Death: " + deathNumberTot);
        System.out.println("Total Hospitalized: " + hosNumberTot);
    }


    /**
     * Writes current database into a specified filename reflecting all
     * previous manipulations associated with current database.
     * Prints number of records saved to the file.
     *
     * @param filename
     *            New file name where data is dumped
     * @throws Exception
     */
    public void dumpdata(String filename) throws Exception {
        // write title to new file
        FileWriter writer = new FileWriter(filename);
        writer.append("Date");
        writer.append(",");
        writer.append("State");
        writer.append(",");
        writer.append("Positive");
        writer.append(",");
        writer.append("Negative");
        writer.append(",");
        writer.append("Hospitalized");
        writer.append(",");
        writer.append("onVentilatorCurrently");
        writer.append(",");
        writer.append("onVentilatorCumulative");
        writer.append(",");
        writer.append("Recovered");
        writer.append(",");
        writer.append("dataQualityGrade");
        writer.append(",");
        writer.append("Death");
        writer.append("\n");
        // for each line in updated list, append info to file
        int count = 0;

        // sort by date latest to recent then by state
        ArrayList<DataEntry> sortedList = new ArrayList<DataEntry>();

        sortedList = sortDumpData(globalList);

        for (int i = 0; i < sortedList.size(); i++) {
            if (sortedList.get(i).getDate() == -1) {
                writer.append("");
            }
            else {
                writer.append(Integer.toString(sortedList.get(i).getDate()));
            }
            writer.append(",");

            writer.append(sortedList.get(i).getState());
            writer.append(",");

            if (sortedList.get(i).getPos() == -1) {
                writer.append("");
            }
            else {
                writer.append(Double.toString(sortedList.get(i).getPos()));
            }
            writer.append(",");

            if (sortedList.get(i).getNeg() == -1) {
                writer.append("");
            }
            else {
                writer.append(Double.toString(sortedList.get(i).getNeg()));
            }
            writer.append(",");

            if (sortedList.get(i).getHos() == -1) {
                writer.append("");
            }
            else {
                writer.append(Double.toString(sortedList.get(i).getHos()));
            }
            writer.append(",");

            if (sortedList.get(i).getOnVentCurr() == -1) {
                writer.append("");
            }
            else {
                writer.append(Double.toString(sortedList.get(i)
                    .getOnVentCurr()));
            }
            writer.append(",");

            if (sortedList.get(i).getOnVentCumu() == -1) {
                writer.append("");
            }
            else {
                writer.append(Double.toString(sortedList.get(i)
                    .getOnVentCumu()));
            }
            writer.append(",");

            if (sortedList.get(i).getRecovered() == -1) {
                writer.append("");
            }
            else {
                writer.append(Double.toString(sortedList.get(i)
                    .getRecovered()));
            }
            writer.append(",");

            writer.append(sortedList.get(i).getDataQualGrade());
            writer.append(",");

            if (sortedList.get(i).getDeath() == -1) {
                writer.append("");
            }
            else {
                writer.append(Double.toString(sortedList.get(i).getDeath()));
            }
            count++;
            writer.append("\n");
        }
        writer.flush();
        writer.close();
        System.out.println(count + " records have been saved in the " + filename
            + " file");
    }


    // Helper functions
    /**
     * Sorts globalList records by most recent data
     *
     * @param list
     *            list of data entries to be sorted
     * @return sorted globalList
     */
    public ArrayList<DataEntry> sortDate(ArrayList<DataEntry> list) {
        ArrayList<DataEntry> hold = new ArrayList<DataEntry>();
        for (int i = 0; i < list.size(); i++) {

            DataEntry copy = new DataEntry(list.get(i).getDate(), list.get(i)
                .getState(), list.get(i).getPos(), list.get(i).getNeg(), list
                    .get(i).getHos(), list.get(i).getOnVentCurr(), list.get(i)
                        .getOnVentCumu(), list.get(i).getRecovered(), list.get(
                            i).getDataQualGrade(), list.get(i).getDeath());
            hold.add(copy);

        }
        // Uses Selection Sort Algorithm because it is the most efficient for
        // partially sorted arrays
        // most recent data first
        for (int i = 0; i < hold.size(); i++) {
            int recent = hold.get(i).getDate();
            int recentIndex = i;
            for (int j = i + 1; j < hold.size(); j++) {
                if (hold.get(j).getDate() > recent) {
                    recent = hold.get(j).getDate();
                    recentIndex = j;
                }
            }
            DataEntry temp = hold.get(i);
            hold.set(i, hold.get(recentIndex));
            hold.set(recentIndex, temp);
        }
        return hold;
    }


    /**
     * Sorts globalList records by most recent data
     *
     * @param list
     *            list of data entries to be sorted
     * @return sorted globalList
     */
    public ArrayList<DataEntry> sortDateReverse(ArrayList<DataEntry> list) {
        ArrayList<DataEntry> hold = new ArrayList<DataEntry>();
        for (int i = 0; i < list.size(); i++) {
            DataEntry copy = new DataEntry(list.get(i).getDate(), list.get(i)
                .getState(), list.get(i).getPos(), list.get(i).getNeg(), list
                    .get(i).getHos(), list.get(i).getOnVentCurr(), list.get(i)
                        .getOnVentCumu(), list.get(i).getRecovered(), list.get(
                            i).getDataQualGrade(), list.get(i).getDeath());
            hold.add(copy);
        }
        // Uses Selection Sort Algorithm because it is the most efficient for
        // partially sorted arrays
        // most recent data first
        for (int i = 0; i < hold.size(); i++) {
            int recent = hold.get(i).getDate();
            int recentIndex = i;
            for (int j = i + 1; j < hold.size(); j++) {
                if (hold.get(j).getDate() < recent) {
                    recent = hold.get(j).getDate();
                    recentIndex = j;
                }
            }
            DataEntry temp = hold.get(i);
            hold.set(i, hold.get(recentIndex));
            hold.set(recentIndex, temp);
        }
        return hold;
    }


    /**
     * Sorts globalList records by State full name alphabetically
     *
     * @param list
     *            list of data entries to be sorted
     * @return sorted globalList
     */
    public ArrayList<DataEntry> sortState(ArrayList<DataEntry> list) {
        ArrayList<DataEntry> hold = new ArrayList<DataEntry>();
        for (int i = 0; i < list.size(); i++) {
            // switch abbrevs to full name
            // list.get(i).setState(getStateOrAbbreviation(
            // list.get(i).getState()));
            DataEntry copy = new DataEntry(list.get(i).getDate(), list.get(i)
                .getState(), list.get(i).getPos(), list.get(i).getNeg(), list
                    .get(i).getHos(), list.get(i).getOnVentCurr(), list.get(i)
                        .getOnVentCumu(), list.get(i).getRecovered(), list.get(
                            i).getDataQualGrade(), list.get(i).getDeath());
            hold.add(copy);
            hold.get(i).setState(getStateOrAbbreviation(hold.get(i)
                .getState()));
        }
        // Uses Selection Sort Algorithm because it is the most efficient for
        // partially sorted arrays
        for (int i = 0; i < hold.size(); i++) {
            String recent = hold.get(i).getState();
            int recentIndex = i;
            // if hold > recent (alphabetically)
            // hold.compareTo(recent) < 0
            for (int j = i + 1; j < hold.size(); j++) {
                if (hold.get(j).getState().compareTo(recent) < 0) {
                    recent = hold.get(j).getState();
                    recentIndex = j;
                }
            }
            DataEntry temp = hold.get(i);
            hold.set(i, hold.get(recentIndex));
            hold.set(recentIndex, temp);

        }
        // for loop to switch state names back to abbreviations
        for (int i = 0; i < hold.size(); i++) {
            // list.get(i).setState(getStateOrAbbreviation(list.get(i)
            // .getState()));
            hold.get(i).setState(getStateOrAbbreviation(hold.get(i)
                .getState()));
        }
        return hold;
    }


    /**
     * Sorts Data by Date from older to newer then by state for the dumpdata csv
     * 
     * @param list
     *            list of data entries to be sorted
     * @return sorted list
     * @throws Exception
     */
    public ArrayList<DataEntry> sortDumpData(ArrayList<DataEntry> list)
        throws Exception {
        // List of sorted globalList by date from oldest to newest
        ArrayList<DataEntry> sortedDate = sortDateReverse(list);
        // List to return sorted by state for each date
        ArrayList<DataEntry> returnList = new ArrayList<DataEntry>();
        // List that will hold data of each individual date
        ArrayList<DataEntry> idate = new ArrayList<DataEntry>();
        // Array version of idate
        DataEntry[] arrayDate;

        // add first entry into individual date list
        idate.add(sortedDate.get(0));
        int currDate = idate.get(0).getDate();

        for (int i = 1; i < sortedDate.size(); i++) {
            if (currDate == sortedDate.get(i).getDate()) {
                idate.add(sortedDate.get(i));
            }
            else {
                // sort idate by state
                idate = sortState(idate);

                // add the sorted individual date by state into the returnList
                for (int j = 0; j < idate.size(); j++) {
                    returnList.add(idate.get(j));
                }
                // start process over with new date
                idate.clear();
                idate.add(sortedDate.get(i));
                currDate = idate.get(0).getDate();
            }
        }
        // For the last date
        // make a new arrayDate and fill it up with idate
        // sort idate by state
        idate = sortState(idate);

        // add the sorted individual date by state into the returnList
        for (int j = 0; j < idate.size(); j++) {
            returnList.add(idate.get(j));
        }
        return returnList;
    }


    /**
     * Prints out state records
     *
     * @param list
     *            list to print out records
     * @throws Exception
     */
    public void printMatchDateRecords(ArrayList<DataEntry> list)
        throws Exception {
        // print titles
        System.out.printf(
            "%-7s %-9s %-10s %-14s %-23s %-24s %-11s %-17s %-7s \n", "state",
            "positive", "negative", "hospitalized", "onVentilatorCurrently",
            "onVentilatorCumulative", "recovered", "dataQualityGrade", "death");
        // load list into temp array and sort by state

        ArrayList<DataEntry> hold = new ArrayList<DataEntry>();
        hold = sortState(list);

        // convert -1 entries to 0
        for (int i = 0; i < hold.size(); i++) {
            if (hold.get(i).getPos() < 0) {
                hold.get(i).setPos(0);
            }
            if (hold.get(i).getNeg() < 0) {
                hold.get(i).setNeg(0);
            }
            if (hold.get(i).getHos() < 0) {
                hold.get(i).setHos(0);
            }
            if (hold.get(i).getOnVentCurr() < 0) {
                hold.get(i).setOnVentCurr(0);
            }
            if (hold.get(i).getOnVentCumu() < 0) {
                hold.get(i).setOnVentCumu(0);
            }
            if (hold.get(i).getRecovered() < 0) {
                hold.get(i).setRecovered(0);
            }
            if (hold.get(i).getDeath() < 0) {
                hold.get(i).setDeath(0);
            }
        }
        // print data for each record in list
        for (int i = 0; i < hold.size(); i++) {
            // format numbers
            String posF = String.format("%,d", hold.get(i).getPos());
            String negF = String.format("%,d", hold.get(i).getNeg());
            String hosF = String.format("%,d", hold.get(i).getHos());
            String onVentCurrF = String.format("%,d", hold.get(i)
                .getOnVentCurr());
            String onVentCumuF = String.format("%,d", hold.get(i)
                .getOnVentCumu());
            String recoveredF = String.format("%,d", hold.get(i)
                .getRecovered());
            String deathF = String.format("%,d", hold.get(i).getDeath());
            // print data
            System.out.printf(
                "%-7s %-9s %-10s %-14s %-23s %-24s %-11s %-17s %-7s \n", hold
                    .get(i).getState(), posF, negF, hosF, onVentCurrF,
                onVentCumuF, recoveredF, hold.get(i).getDataQualGrade(),
                deathF);
        }
    }


    /**
     * Prints out date records
     *
     * @param list
     *            list to print out records
     * @param num
     *            max number of records
     */
    public void printMatchStateRecords(ArrayList<DataEntry> list, int num) {
        // print titles
        System.out.printf(
            "%-12s %-9s %-10s %-14s %-23s %-24s %-11s %-17s %-7s \n", "date",
            "positive", "negative", "hospitalized", "onVentilatorCurrently",
            "onVentilatorCumulative", "recovered", "dataQualityGrade", "death");
        // load list into temp array and sort by date
        ArrayList<DataEntry> hold = new ArrayList<DataEntry>();
        hold = sortDate(list);
        // convert -1 entries to 0
        for (int i = 0; i < hold.size(); i++) {

            if (hold.get(i).getPos() < 0) {
                hold.get(i).setPos(0);
            }
            if (hold.get(i).getNeg() < 0) {
                hold.get(i).setNeg(0);
            }
            if (hold.get(i).getHos() < 0) {
                hold.get(i).setHos(0);
            }
            if (hold.get(i).getOnVentCurr() < 0) {
                hold.get(i).setOnVentCurr(0);
            }
            if (hold.get(i).getOnVentCumu() < 0) {
                hold.get(i).setOnVentCumu(0);
            }
            if (hold.get(i).getRecovered() < 0) {
                hold.get(i).setRecovered(0);
            }
            if (hold.get(i).getDeath() < 0) {
                hold.get(i).setDeath(0);
            }
        }
        // print data for each record in list
        for (int i = 0; i < hold.size(); i++) {
            // if i exceeds max num records, break
            if (i >= num) {
                break;
            }
            // format numbers
            String posF = String.format("%,d", hold.get(i).getPos());
            String negF = String.format("%,d", hold.get(i).getNeg());
            String hosF = String.format("%,d", hold.get(i).getHos());
            String onVentCurrF = String.format("%,d", hold.get(i)
                .getOnVentCurr());
            String onVentCumuF = String.format("%,d", hold.get(i)
                .getOnVentCumu());
            String recoveredF = String.format("%,d", hold.get(i)
                .getRecovered());
            String deathF = String.format("%,d", hold.get(i).getDeath());

            // reformat date
            String date = Integer.toString((hold.get(i).getDate()));
            String day = date.substring(6);
            String month = date.substring(4, 6);
            String year = date.substring(0, 4);
            String reformatted = month + "/" + day + "/" + year;
            // print data
            System.out.printf(
                "%-12s %-9s %-10s %-14s %-23s %-24s %-11s %-17s %-7s \n",
                reformatted, posF, negF, hosF, onVentCurrF, onVentCumuF,
                recoveredF, hold.get(i).getDataQualGrade(), deathF);
        }
    }


    /**
     * Switch method to return take in state name and return abbreviation or
     * abbreviation and return state
     *
     * @param name
     *            name of state or territory
     * @return string of converted name
     */
    public String getStateOrAbbreviation(String name) {
        // remove spaces and and then check matches
        // split by space, recreate string and then check WITH spacing
        name = name.replaceAll(" ", "");
        switch (name.toUpperCase()) {
            case "ALABAMA":
                return "AL";

            case "ALASKA":
                return "AK";

            case "AMERICANSAMOA":
                return "AS";

            case "ARIZONA":
                return "AZ";

            case "ARKANSAS":
                return "AR";

            case "CALIFORNIA":
                return "CA";

            case "COLORADO":
                return "CO";

            case "CONNECTICUT":
                return "CT";

            case "DELAWARE":
                return "DE";

            case "DISTRICTOFCOLUMBIA":
                return "DC";

            case "FEDERATEDSTATESOFMICRONESIA":
                return "FM";

            case "FLORIDA":
                return "FL";

            case "GEORGIA":
                return "GA";

            case "GUAM":
                return "GU";

            case "HAWAII":
                return "HI";

            case "IDAHO":
                return "ID";

            case "ILLINOIS":
                return "IL";

            case "INDIANA":
                return "IN";

            case "IOWA":
                return "IA";

            case "KANSAS":
                return "KS";

            case "KENTUCKY":
                return "KY";

            case "LOUISIANA":
                return "LA";

            case "MAINE":
                return "ME";

            case "MARSHALLISLANDS":
                return "MH";

            case "MARYLAND":
                return "MD";

            case "MASSACHUSETTS":
                return "MA";

            case "MICHIGAN":
                return "MI";

            case "MINNESOTA":
                return "MN";

            case "MISSISSIPPI":
                return "MS";

            case "MISSOURI":
                return "MO";

            case "MONTANA":
                return "MT";

            case "NEBRASKA":
                return "NE";

            case "NEVADA":
                return "NV";

            case "NEWHAMPSHIRE":
                return "NH";

            case "NEWJERSEY":
                return "NJ";

            case "NEWMEXICO":
                return "NM";

            case "NEWYORK":
                return "NY";

            case "NORTHCAROLINA":
                return "NC";

            case "NORTHDAKOTA":
                return "ND";

            case "NORTHERNMARIANAISLANDS":
                return "MP";

            case "OHIO":
                return "OH";

            case "OKLAHOMA":
                return "OK";

            case "OREGON":
                return "OR";

            case "PALAU":
                return "PW";

            case "PENNSYLVANIA":
                return "PA";

            case "PUERTORICO":
                return "PR";

            case "RHODEISLAND":
                return "RI";

            case "SOUTHCAROLINA":
                return "SC";

            case "SOUTHDAKOTA":
                return "SD";

            case "TENNESSEE":
                return "TN";

            case "TEXAS":
                return "TX";

            case "UTAH":
                return "UT";

            case "VERMONT":
                return "VT";

            case "VIRGINISLANDS":
                return "VI";

            case "VIRGINIA":
                return "VA";

            case "WASHINGTON":
                return "WA";

            case "WESTVIRGINIA":
                return "WV";

            case "WISCONSIN":
                return "WI";

            case "WYOMING":
                return "WY";

            case "AL":
                return "ALABAMA";

            case "AK":
                return "ALASKA";

            case "AS":
                return "AMERICANSAMOA";

            case "AZ":
                return "ARIZONA";

            case "AR":
                return "ARKANSAS";

            case "CA":
                return "CALIFORNIA";

            case "CO":
                return "COLORADO";

            case "CT":
                return "CONNECTICUT";

            case "DE":
                return "DELAWARE";

            case "DC":
                return "DISTRICTOFCOLUMBIA";

            case "FM":
                return "FEDERATEDSTATESOFMICRONESIA";

            case "FL":
                return "FLORIDA";

            case "GA":
                return "GEORGIA";

            case "GU":
                return "GUAM";

            case "HI":
                return "HAWAII";

            case "ID":
                return "IDAHO";

            case "IL":
                return "ILLINOIS";

            case "IN":
                return "INDIANA";

            case "IA":
                return "IOWA";

            case "KS":
                return "KANSAS";

            case "KY":
                return "KENTUCKY";

            case "LA":
                return "LOUISIANA";

            case "ME":
                return "MAINE";

            case "MH":
                return "MARSHALLISLANDS";

            case "MD":
                return "MARYLAND";

            case "MA":
                return "MASSACHUSETTS";

            case "MI":
                return "MICHIGAN";

            case "MN":
                return "MINNESOTA";

            case "MS":
                return "MISSISSIPPI";

            case "MO":
                return "MISSOURI";

            case "MT":
                return "MONTANA";

            case "NE":
                return "NEBRASKA";

            case "NV":
                return "NEVADA";

            case "NH":
                return "NEWHAMPSHIRE";

            case "NJ":
                return "NEWJERSEY";

            case "NM":
                return "NEWMEXICO";

            case "NY":
                return "NEWYORK";

            case "NC":
                return "NORTHCAROLINA";

            case "ND":
                return "NORTHDAKOTA";

            case "MP":
                return "NORTHERNMARIANAISLANDS";

            case "OH":
                return "OHIO";

            case "OK":
                return "OKLAHOMA";

            case "OR":
                return "OREGON";

            case "PW":
                return "PALAU";

            case "PA":
                return "PENNSYLVANIA";

            case "PR":
                return "PUERTORICO";

            case "RI":
                return "RHODEISLAND";

            case "SC":
                return "SOUTHCAROLINA";

            case "SD":
                return "SOUTHDAKOTA";

            case "TN":
                return "TENNESSEE";

            case "TX":
                return "TEXAS";

            case "UT":
                return "UTAH";

            case "VT":
                return "VERMONT";

            case "VI":
                return "VIRGINISLANDS";

            case "VA":
                return "VIRGINIA";

            case "WA":
                return "WASHINGTON";

            case "WV":
                return "WESTVIRGINIA";

            case "WI":
                return "WISCONSIN";

            case "WY":
                return "WYOMING";

            default:
                return "-1";

        }
    }
}




/**
 * Helper class to assign values to specific parameters
 * 
 * @author Fiona Tran (ftbaohan)
 * @author Aaron Ni (aaronn)
 * @version 2020-09-30
 */
class DataEntry {
    private int date = 0;
    private String state = "";
    private int pos = 0;
    private int neg = 0;
    private int hos = 0;
    private int onVentCurr = 0;
    private int onVentCumu = 0;
    private int recovered = 0;
    private String dataQualGrade = "";
    private int gradeVal = 0;
    private int death = 0;

    /**
     * Constructor for DataEntry class
     *
     * @param da
     *            date
     * @param st
     *            state
     * @param p
     *            positive
     * @param n
     *            negative
     * @param h
     *            hospitalized
     * @param ovcr
     *            onVentilatorCurrently
     * @param ovcm
     *            onVentilatorCumulative
     * @param r
     *            recovered
     * @param dqg
     *            dataQualityGrade
     * @param de
     *            death
     */
    public DataEntry(
        int da,
        String st,
        int p,
        int n,
        int h,
        int ovcr,
        int ovcm,
        int r,
        String dqg,
        int de) {
        date = da;
        state = st;
        pos = p;
        neg = n;
        hos = h;
        onVentCurr = ovcr;
        onVentCumu = ovcm;
        recovered = r;
        dataQualGrade = dqg;
        if (dqg.equals("F")) {
            gradeVal = 1;
        }
        else if (dqg.equals("F+")) {
            gradeVal = 2;
        }
        else if (dqg.equals("D")) {
            gradeVal = 3;
        }
        else if (dqg.equals("D+")) {
            gradeVal = 4;
        }
        else if (dqg.equals("C")) {
            gradeVal = 5;
        }
        else if (dqg.equals("C+")) {
            gradeVal = 6;
        }
        else if (dqg.equals("B")) {
            gradeVal = 7;
        }
        else if (dqg.equals("B+")) {
            gradeVal = 8;
        }
        else if (dqg.equals("A")) {
            gradeVal = 9;
        }
        else if (dqg.equals("A+")) {
            gradeVal = 10;
        }
        death = de;
    }


    /**
     * Getter method for data entry date
     *
     * @return date
     */
    public int getDate() {
        return date;
    }


    /**
     * Setter method for data entry date
     *
     * @param da
     *            date
     */
    public void setDate(int da) {
        date = da;
    }


    /**
     * Getter method for data entry state
     *
     * @return state
     */
    public String getState() {
        return state;
    }


    /**
     * Setter method for data entry state
     *
     * @param st
     *            state
     */
    public void setState(String st) {
        state = st;
    }


    /**
     * Getter method for data entry positive cases
     *
     * @return positive cases
     */
    public int getPos() {
        return pos;
    }


    /**
     * Setter method for data entry positive cases
     *
     * @param p
     *            positive
     */
    public void setPos(int p) {
        pos = p;
    }


    /**
     * Getter method for data entry negative cases
     *
     * @return negative cases
     */
    public int getNeg() {
        return neg;
    }


    /**
     * Setter method for data entry negative cases
     *
     * @param n
     *            negative
     */
    public void setNeg(int n) {
        neg = n;
    }


    /**
     * Getter method for data entry hospitalized
     *
     * @return hospitalized
     */
    public int getHos() {
        return hos;
    }


    /**
     * Setter method for data entry hospitalized
     *
     * @param h
     *            hospitalized
     */
    public void setHos(int h) {
        hos = h;
    }


    /**
     * Getter method for data entry onVentilatorCurrently
     *
     * @return onVentilatorCurrently
     */
    public int getOnVentCurr() {
        return onVentCurr;
    }


    /**
     * Setter method for data entry onVentilatorCurrently
     *
     * @param ovcr
     *            onVentilatorCurrently
     */
    public void setOnVentCurr(int ovcr) {
        onVentCurr = ovcr;
    }


    /**
     * Getter method for data entry onVentilatorCumulative
     *
     * @return onVentilatorCumulative
     */
    public int getOnVentCumu() {
        return onVentCumu;
    }


    /**
     * Setter method for data entry onVentilatorCumulative
     *
     * @param ovcm
     *            onVentilatorCumulative
     */
    public void setOnVentCumu(int ovcm) {
        onVentCumu = ovcm;
    }


    /**
     * Getter method for data entry recovered
     *
     * @return recovered
     */
    public int getRecovered() {
        return recovered;
    }


    /**
     * Setter method for data entry recovered
     *
     * @param r
     *            recovered
     */
    public void setRecovered(int r) {
        recovered = r;
    }


    /**
     * Getter method for data entry dataQualityGrade
     *
     * @return dataQualityGrade
     */
    public String getDataQualGrade() {
        return dataQualGrade;
    }


    /**
     * Setter method for data entry dataQualityGrade
     *
     * @param dqg
     *            dataQualityGrade
     */
    public void setDataQualGrade(String dqg) {
        dataQualGrade = dqg;
    }


    /**
     * Getter method for data entry death
     *
     * @return gradeVal
     */
    public int getGradeVal() {
        return gradeVal;
    }


    /**
     * Setter method for data entry death
     *
     * @param gv
     *            gradeVal
     */
    public void setGradeVal(int gv) {
        gradeVal = gv;
    }


    /**
     * Getter method for data entry death
     *
     * @return death
     */
    public int getDeath() {
        return death;
    }


    /**
     * Setter method for data entry death
     *
     * @param de
     *            death
     */
    public void setDeath(int de) {
        death = de;
    }


    /**
     * Compares state and date records of two data entries (used for updating
     * and redundancy)
     *
     * @param data
     *            data to check for date and state
     * @return True if state and date match
     */
    public boolean compareRecords(DataEntry data) {
        return this.getState().equals(data.getState()) && this
            .getDate() == (data.getDate());
    }


    /**
     * Function to return String array of results for printing
     *
     * @return String array of data record
     */
    public String[] getArrayData() {
        String[] arrayVals = { Integer.toString(date), state, Integer.toString(
            pos), Integer.toString(neg), Integer.toString(hos), Integer
                .toString(onVentCurr), Integer.toString(onVentCumu), Integer
                    .toString(recovered), dataQualGrade, Integer.toString(
                        death) };
        // check if there are empty data values and restore
        for (int i = 0; i < arrayVals.length; i++) {
            if (arrayVals[i].equals("-1")) {
                arrayVals[i].equals("");
            }
        }
        return arrayVals;
    }


    /**
     * Compares all numerical data entries and return if there is match
     * duplicate
     * 
     * @param data
     *            data to check if is duplicate
     * @return true if matches
     */
    public boolean sameDataEntry(DataEntry data) {
        return this.getPos() == data.getPos() && this.getNeg() == data.getNeg()
            && this.getHos() == data.getHos() && this.getOnVentCurr() == data
                .getOnVentCurr() && this.getOnVentCumu() == data.getOnVentCumu()
            && this.getRecovered() == data.getRecovered() && this
                .getDeath() == data.getDeath();
    }
}
