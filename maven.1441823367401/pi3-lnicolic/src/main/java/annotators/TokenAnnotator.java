package annotators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import type.Token;

/**
 * Annotates individual tokens in the input document
 *
 */
public class TokenAnnotator extends JCasAnnotator_ImplBase {
	
	private Pattern tokenPattern = Pattern.compile(" ([a-zA-Z']+)");

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get document text
		String docText = aJCas.getDocumentText();
		int pos = 0;
	    // search for answers
	    Matcher matcher = tokenPattern.matcher(docText);
	    while (matcher.find(pos)) {
	      // found one - create annotation
	      Token token = new Token(aJCas);
	      token.setBegin(matcher.start()+1);
	      token.setEnd(matcher.end());
	      //set other parts of answer
	      token.addToIndexes();
	      pos = matcher.end();
	    }
	}
	
}
