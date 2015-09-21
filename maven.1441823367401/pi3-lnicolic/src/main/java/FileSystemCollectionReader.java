import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;


public class FileSystemCollectionReader extends CollectionReader_ImplBase {
	
	private int mCurrentIndex;
	private ArrayList<File> mFiles;
	
	public void initialize() throws ResourceInitializationException {
		File directory = new File("src/main/resources/inputData");
		mCurrentIndex = 0; 
		  
		//get list of files (not subdirectories) in the specified directory
		mFiles = new ArrayList();
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++) {
		    if (!files[i].isDirectory()) {
		      mFiles.add(files[i]);  
		    }
		}
	}

	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
	    JCas jcas;
	    try {
	      jcas = aCAS.getJCas();
	    } catch (CASException e) {
	      throw new CollectionException(e);
	    }

	    // open input stream to file
	    File file = (File) mFiles.get(mCurrentIndex++);
	    String text = FileUtils.file2String(file, "utf8");
	      // put document in CAS
	    jcas.setDocumentText(text);
	    
	    jcas.setDocumentLanguage("English");

	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		return mCurrentIndex < mFiles.size();
	}

	@Override
	public Progress[] getProgress() {
		  return new Progress[]{
		     new ProgressImpl(mCurrentIndex,mFiles.size(),Progress.ENTITIES)};
	}

	@Override
	public void close() throws IOException {
	}

}
