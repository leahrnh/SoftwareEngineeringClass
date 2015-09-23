package annotators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import type.Answer;
import type.Question;

/**
 * Annotates question lines from the input document
 *
 */
public class QuestionAnnotator extends JCasAnnotator_ImplBase {
	
	private Pattern question = Pattern.compile("Q (.+)");

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		// get document text
		String docText = aJCas.getDocumentText();
		// search for questions
		Matcher matcher = question.matcher(docText);
		int qid = 0;
		int pos = 0;
		while (matcher.find(pos)) {
			// found one - create annotation
			Question question = new Question(aJCas);
		    question.setBegin(matcher.start());
            question.setEnd(matcher.end());
            //set other parts of question
            question.setSentence(matcher.group(1));
            question.setId("Q"+qid);
            qid+=1;
            question.addToIndexes();
	        pos = matcher.end();
	    }
	}	

}
