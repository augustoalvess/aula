import java.util.*;

public class FileTime implements Comparable<FileTime> {
	String file;
	long time;

	public FileTime(String file, long time) {
		this.file = file;
		this.time = time;
	}

	public String toString() {
    	return this.time + " " + this.file;
    }

    public int compareTo(FileTime f) {
    	long compare = (this.time - f.time);
    	if (compare < 0) {
    		return -1;
    	} else if (compare == 0) {
    		return 0;
    	} else {
    		return 1;
    	}
    }
}