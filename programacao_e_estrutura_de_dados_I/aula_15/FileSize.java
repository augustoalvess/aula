import java.util.*;

public class FileSize implements Comparable<FileSize> {
	String file;
	long size;

	public FileSize(String file, long size) {
		this.file = file;
		this.size = size;
	}

	public String toString() {
    	return this.size + " " + this.file;
    }

    public int compareTo(FileSize f) {
    	long compare = (this.size - f.size);
    	if (compare < 0) {
    		return -1;
    	} else if (compare == 0) {
    		return 0;
    	} else {
    		return 1;
    	}
    }
}