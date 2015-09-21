import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;


public class Consumer extends CasConsumer_ImplBase {
	
	private int mDocNum;
	private File mOutputDir = new File("src/main/resources/outputData");
	
	public void initialize() throws ResourceInitializationException {
		mDocNum = 0;
		//setup output directory?
	}

	@Override
	public void processCas(CAS aCAS) throws ResourceProcessException {
		  String modelFileName = null;

		  JCas jcas;
		  try {
		    jcas = aCAS.getJCas();
		  } catch (CASException e) {
		    throw new ResourceProcessException(e);
		  }

	}

}
