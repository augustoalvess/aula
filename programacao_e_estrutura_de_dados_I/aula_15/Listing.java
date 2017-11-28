import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Listing {
	public String caminho;
	ArrayList<FileTime> fileTimes = new ArrayList<FileTime>();
	ArrayList<FileSize> fileSizes = new ArrayList<FileSize>();

	public Listing(String caminho) {
		this.caminho = caminho;
		File file = new File(caminho);
		for (File arquivo: file.listFiles()) {
			this.fileTimes.add(new FileTime(arquivo.getName(), arquivo.lastModified()));
			this.fileSizes.add(new FileSize(arquivo.getName(), arquivo.length()));
		}
	}

	public void listByDate() {
		Collections.sort(this.fileTimes);
        System.out.println(this.fileTimes.toString());
	}

	public void listBySize() {
		Collections.sort(this.fileSizes);
        System.out.println(this.fileSizes.toString());
	}

}