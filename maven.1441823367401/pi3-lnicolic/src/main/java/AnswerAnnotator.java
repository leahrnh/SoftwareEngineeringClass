

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import type.Answer;
import type.Question;

public class AnswerAnnotator extends JCasAnnotator_ImplBase {
	
	private Pattern answer = Pattern.compile("(A[0-9]) (0|1) (.+)");

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get document text
		String docText = aJCas.getDocumentText();
		int pos = 0;
	    // search for answers
	    Matcher matcher = answer.matcher(docText);
	    while (matcher.find(pos)) {
	      // found one - create annotation
	      Answer answer = new Answer(aJCas);
	      answer.setBegin(matcher.start());
	      answer.setEnd(matcher.end());
	      //set other parts of answer
	      answer.setId(matcher.group(1));
	      answer.setLabel(matcher.group(2).equals("1"));
	      answer.setSentence(matcher.group(3));
	      answer.addToIndexes();
	      pos = matcher.end();
	    }
	}
	
}
