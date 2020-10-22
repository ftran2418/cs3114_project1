
public class test1 {
    public int compare(K key) {
        // <date, state> or <state, date>
        if (key.length == 2) {

            // We don't check for identical keys because we will make sure that
            // there are
            // no redundant data when we add it in

            // if the first character in the first index of key is numeric
            // <date, state> key (compares dates first)
            if (key[0].substring(0, 1).matches("[0-9]+")) {
                // change the both strings to ints then compare
                int first = (int)Double.parseDouble(this.key[0]);
                int second = (int)Double.parseDouble(key[0]);

                // compares dates first
                if (first != second) {
                    if (first > second) {
                        return 1;
                    }
                    return -1;
                }
                // dates are equal, compare states
                else {
                    // this key is greater alphabetically
                    if (this.key[1].compareTo(key[1]) > 0) {
                        return 1;
                    }
                    return -1;
                }

            }

            // if the first character in the first index of key is alphabetical
            // <state, date>
            else {
                // compare the states

                // compares states first
                if (!this.key[0].equals(key[0])) {
                    if (this.key[1].compareTo(key[1]) > 0) {
                        return 1;
                    }
                    return -1;
                }

                // states are the same, compare dates
                else {
                    // change the both strings to ints then compare
                    int first = (int)Double.parseDouble(this.key[0]);
                    int second = (int)Double.parseDouble(key[0]);

                    if (first > second) {
                        return 1;
                    }
                    return -1;
                }
            }

        }
        // <qualitygrade, date, state>
        else {

            // checks qualitygrade first
            // probably will need to compare gradevalues
            return 0;
        }
    }
    
    
    
    class CBSTNode<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private CBSTNode left, right;

        private String[] flyKey = new String[0];
        private DataEntry flyVal;
        private FlyNode flyNode = new FlyNode(flyKey, flyVal);

        public CBSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = flyNode;
            this.right = flyNode;
        }


        public boolean isFly() {
            return false;
        }


        @SuppressWarnings({ "rawtypes", "unchecked" })
        public void put(K key, V value) {

            // if key of node adding in is greater than current key
            if (this.compare(key) > 0) {
                // if left is empty
                if (!left.isFly()) {
                    left.put(key, value);
                }
                else {
                    left = new CBSTNode(key, value);
                }
            }
            else if (this.compare(key) > 0) {
                if(!right.isFly()) {
                    right.put(key, value);
                }
                else {
                    right = new CBSTNode(key, value);
                }
            }
            else {
                //if there equal, but it shouldnt come here ya feel
            }
        }
        
    }
}
