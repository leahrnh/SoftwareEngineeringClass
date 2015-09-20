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
	private File mOutputDir = new File("outputData");
	
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
		 
		  // retrieve the filename of the input file from the CAS
		  /*FSIterator it = jcas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
		  File outFile = null;
		  if (it.hasNext()) {
		    SourceDocumentInformation fileLoc = (SourceDocumentInformation) it.next();
		    File inFile;
		    try {
		      inFile = new File(new URL(fileLoc.getUri()).getPath());
		      String outFileName = inFile.getName();
		      if (fileLoc.getOffsetInSource() > 0) {
		        outFileName += ("_" + fileLoc.getOffsetInSource());
		      }
		      outFileName += ".xmi";
		      outFile = new File(mOutputDir, outFileName);
		      modelFileName = mOutputDir.getAbsolutePath() + 
		            "/" + inFile.getName() + ".ecore";
		    } catch (MalformedURLException e1) {
		      // invalid URL, use default processing below
		    }
		  }
		  if (outFile == null) {
		    outFile = new File(mOutputDir, "doc" + mDocNum++);
		  }
		  // serialize XCAS and write to output file
		  try {
		    writeXmi(jcas.getCas(), outFile, modelFileName);
		  } catch (IOException e) {
		    throw new ResourceProcessException(e);
		  } catch (SAXException e) {
		    throw new ResourceProcessException(e);
		  }*/

	}

}
